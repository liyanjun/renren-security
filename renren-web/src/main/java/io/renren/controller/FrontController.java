package io.renren.controller;

import io.renren.entity.*;
import io.renren.service.*;
import io.renren.utils.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public String submit(GameOrderEntity gameOrderEntity, HttpServletRequest request, Model model) {
        List<GamePropertyValueEntity> valueList = new ArrayList<>();
        List<GamePropertyEntity> gamePropertyList = gamePropertyService.queryAll();
        for (GamePropertyEntity gamePropertyEntity : gamePropertyList) {
            String value = request.getParameter(gamePropertyEntity.getName());
            GamePropertyValueEntity gamePropertyValueEntity = new GamePropertyValueEntity();
            gamePropertyValueEntity.setGamePropertyId(gamePropertyEntity.getId());
            gamePropertyValueEntity.setValue(value);
            valueList.add(gamePropertyValueEntity);
        }
        GamePriceEntity gamePriceEntity = gamePriceService.queryObject(gameOrderEntity.getGamePriceId());
        gameOrderEntity.setTotalAmount(gamePriceEntity.getPrice().multiply(BigDecimal.valueOf(gameOrderEntity.getGamePriceNumber())));
        gameOrderEntity.setCreatorId(-1L);
        gameOrderService.save(gameOrderEntity, valueList);
        int type = 0;
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if (userAgent.indexOf("micromessenger") > -1) {//微信客户端
            type = 1;
        } else {
            type = 2;
        }
        List<GamePayImgEntity> list = gamePayImgService.queryByMatchPrice(gameOrderEntity.getTotalAmount(), type);
        if (list.size() < 0) {
            throw new RRException("没有配置付款图片。");
        }

        model.addAttribute("img", list.get(0));
        return "pay.html";
    }

}
