package io.renren.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import io.renren.config.AlipayConfig;
import io.renren.entity.*;
import io.renren.service.*;
import io.renren.utils.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 前台表单逻辑
 *
 * @author liyanjun
 */
@Controller
@RequestMapping("/front")
public class FrontController extends AbstractController {

    Logger logger = LoggerFactory.getLogger(FrontController.class);

    @Autowired
    private GamePriceService gamePriceService;

    @Autowired
    private GamePropertyService gamePropertyService;

    @Autowired
    private GameOrderService gameOrderService;

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private GamePayImgService gamePayImgService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/gamePrice")
    public R gamePrice(@RequestParam int page, @RequestParam int limit) {
        //查询列表数据
        Query query = new Query(page, limit);

        List<GamePriceEntity> gamePriceList = gamePriceService.queryList(query);
        int total = gamePriceService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(gamePriceList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/gameProperty")
    public R gameProperty() {

        List<GamePropertyEntity> gamePropertyList = gamePropertyService.queryAll();
        String gameName = sysConfigService.getValue("gameName", "游戏名称");
        String advert = sysConfigService.getValue("advert", "广告");
        //查询列表数据
        Query query = new Query(1, 5);

        List<GamePriceEntity> gamePriceList = gamePriceService.queryList(query);
        int total = gamePriceService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(gamePriceList, total, query.getLimit(), query.getPage());
        return R.ok().put("gameProperty", gamePropertyList).put("gameName", gameName).put("page", pageUtil).put("advert", advert);
    }

    /**
     * 订单提交
     *
     * @return
     */
    @RequestMapping("/submit")
    public void submit(GameOrderEntity gameOrderEntity, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        List<GamePropertyValueEntity> valueList = new ArrayList<>();
        List<GamePropertyEntity> gamePropertyList = gamePropertyService.queryAll();
        String orderName = "";
        for (GamePropertyEntity gamePropertyEntity : gamePropertyList) {
            String value = request.getParameter(gamePropertyEntity.getName());
            orderName += value;
            GamePropertyValueEntity gamePropertyValueEntity = new GamePropertyValueEntity();
            gamePropertyValueEntity.setGamePropertyId(gamePropertyEntity.getId());
            gamePropertyValueEntity.setValue(value);
            valueList.add(gamePropertyValueEntity);
        }
        GamePriceEntity gamePriceEntity = gamePriceService.queryObject(gameOrderEntity.getGamePriceId());
        gameOrderEntity.setTotalAmount(gamePriceEntity.getPrice().multiply(BigDecimal.valueOf(gameOrderEntity.getGamePriceNumber())));
        gameOrderEntity.setCreatorId(-1L);
        gameOrderService.save(gameOrderEntity, valueList);
        //起调支付宝支付
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(gameOrderEntity.getId().toString().getBytes("ISO-8859-1"),"UTF-8");
        // 订单名称，必填
        String subject = new String((orderName+gameOrderEntity.getId()).getBytes("ISO-8859-1"),"UTF-8");
        // 付款金额，必填
        String total_amount=new String(gameOrderEntity.getTotalAmount().toString().getBytes("ISO-8859-1"),"UTF-8");
//        // 商品描述，可空
//        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
//        // 超时时间 可空
//        String timeout_express="2m";
        // 销售产品码 必填
        String product_code="QUICK_WAP_PAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setTotalAmount(total_amount);
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
//        model.setBody(body);
//        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipay_request.setReturnUrl(AlipayConfig.return_url);

        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = client.pageExecute(alipay_request).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException e) {
            logger.error("订单提交支付错误",e);
            // TODO Auto-generated catch block
        } catch (IOException e) {
            logger.error("订单提交支付错误",e);
        }
//        int type = 0;
//        String userAgent = request.getHeader("user-agent").toLowerCase();
//        if (userAgent.indexOf("micromessenger") > -1) {//微信客户端
//            type = 1;
//        } else {
//            type = 2;
//        }
//        List<GamePayImgEntity> list = gamePayImgService.queryByMatchPrice(gameOrderEntity.getTotalAmount(), type);
//        if (list.size() < 0) {
//            throw new RRException("没有配置付款图片。");
//        }
//
//        model.addAttribute("img", list.get(0));
//        return "pay.html";
    }

}
