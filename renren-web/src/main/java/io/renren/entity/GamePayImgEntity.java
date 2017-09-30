package io.renren.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 支付图片截图
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public class GamePayImgEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private String url;
	//
	private BigDecimal matchPrice;
	//1：微信，2：支付宝
	private Integer type;
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
	 * 设置：
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：
	 */
	public void setMatchPrice(BigDecimal matchPrice) {
		this.matchPrice = matchPrice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMatchPrice() {
		return matchPrice;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
