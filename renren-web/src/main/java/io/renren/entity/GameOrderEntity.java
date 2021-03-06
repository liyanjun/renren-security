package io.renren.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 订单表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public class GameOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //游戏名
    private String name;
    //游戏账号
    private String account;
    //其他字段信息
    private String infoJson;
    //是否处理1-已处理，-1-未处理
    private Integer isHandle = -1;
    //订单总额
    private BigDecimal totalAmount;
    //购买数量
    private Integer gamePriceNumber;
    //购买价位ID
    private Long gamePriceId;

    private Integer isPay;
    //
    private Long creatorId;
    //
    private Date createTime;
    //
    private Long updateId;
    //
    private Date updateTime;
    //软删除：1删 -1未删
    private Integer isDel = -1;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：游戏名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：游戏名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：游戏账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取：游戏账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置：其他字段信息
     */
    public void setInfoJson(String infoJson) {
        this.infoJson = infoJson;
    }

    /**
     * 获取：其他字段信息
     */
    public String getInfoJson() {
        return infoJson;
    }

    /**
     * 设置：是否处理1-已处理，-1-未处理
     */
    public void setIsHandle(Integer isHandle) {
        this.isHandle = isHandle;
    }

    /**
     * 获取：是否处理1-已处理，-1-未处理
     */
    public Integer getIsHandle() {
        return isHandle;
    }

    /**
     * 设置：
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取：
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置：
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 获取：
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取：
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：软删除：1删 -1未删
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取：软删除：1删 -1未删
     */
    public Integer getIsDel() {
        return isDel;
    }

    public Integer getGamePriceNumber() {
        return gamePriceNumber;
    }

    public void setGamePriceNumber(Integer gamePriceNumber) {
        this.gamePriceNumber = gamePriceNumber;
    }

    public Long getGamePriceId() {
        return gamePriceId;
    }

    public void setGamePriceId(Long gamePriceId) {
        this.gamePriceId = gamePriceId;
    }

    public Integer getIsPay() {
        return isPay;
    }

    public void setIsPay(Integer isPay) {
        this.isPay = isPay;
    }
}
