package io.renren.dao;

import io.renren.entity.GamePayImgEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 支付图片截图
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public interface GamePayImgDao extends BaseDao<GamePayImgEntity> {

    List<GamePayImgEntity> queryByMatchPrice(Map totalAmount);
}
