package io.renren.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.renren.dao.GamePriceDao;
import io.renren.dao.GamePropertyValueDao;
import io.renren.entity.GamePriceEntity;
import io.renren.entity.GamePropertyValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.renren.dao.GameOrderDao;
import io.renren.entity.GameOrderEntity;
import io.renren.service.GameOrderService;
import org.springframework.transaction.annotation.Transactional;


@Service("gameOrderService")
public class GameOrderServiceImpl implements GameOrderService {
    @Autowired
    private GameOrderDao gameOrderDao;
    @Autowired
    private GamePropertyValueDao gamePropertyValueDao;
    @Autowired
    private GamePriceDao gamePriceDao;

    @Override
    public GameOrderEntity queryObject(Long id) {
        return gameOrderDao.queryObject(id);
    }

    @Override
    public List<JSONObject> queryList(Map<String, Object> map) {
        //找自定义数据
        List<GameOrderEntity> list = gameOrderDao.queryList(map);
        List<JSONObject> jsonList = new ArrayList<>();
        for (GameOrderEntity gameOrder : list) {
            List<GamePropertyValueEntity> propertyValueList = gamePropertyValueDao.findByOrderId(gameOrder.getId());
            JSONObject jsonObject = new JSONObject();
            for (GamePropertyValueEntity gamePropertyValue : propertyValueList) {
                jsonObject.put(gamePropertyValue.getName(), gamePropertyValue.getValue().toString());
            }
            jsonObject.put("id", gameOrder.getId().toString());
            jsonObject.put("name", gameOrder.getName().toString());
            jsonObject.put("account", gameOrder.getAccount().toString());
            jsonObject.put("isHandle", gameOrder.getIsHandle() == 1 ? "<font color=\"green\">已处理</font>" : "<font color=\"red\">未处理</font>");
            jsonObject.put("totalAmount", gameOrder.getTotalAmount().toString() + "元");
            jsonObject.put("gamePriceNumber", gameOrder.getGamePriceNumber().toString());
            GamePriceEntity price = gamePriceDao.queryObject(gameOrder.getGamePriceId().toString());
            jsonObject.put("gamePriceId", price.getId().toString());
            jsonObject.put("gamePrice", price.getPrice().toString() + "元");
            jsonObject.put("isPay", gameOrder.getIsPay() == 1 ? "<font color=\"green\">已支付</font>" : "<font color=\"red\">未支付</font>");
            jsonList.add(jsonObject);
        }
        return jsonList;
    }

    @Override
    public List<GameOrderEntity> queryListForBackup(Map<String, Object> map) {
        //找自定义数据
        List<GameOrderEntity> list = gameOrderDao.queryList(map);
        return list;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return gameOrderDao.queryTotal(map);
    }

    @Override
    @Transactional
    public GameOrderEntity save(GameOrderEntity gameOrder, List<GamePropertyValueEntity> valueList) {
        gameOrderDao.insert(gameOrder);
        for (GamePropertyValueEntity value : valueList) {
            value.setCreatorId(-1);
            value.setGameOrderId(gameOrder.getId());
            gamePropertyValueDao.save(value);
        }
        return gameOrder;
    }

    @Override
    public void update(GameOrderEntity gameOrder) {
        gameOrderDao.update(gameOrder);
    }

    @Override
    public void delete(Long id) {
        gameOrderDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        gameOrderDao.deleteBatch(ids);
    }

    @Override
    public void handleBatch(Long[] ids) {
        gameOrderDao.handleBatch(ids);
    }

}
