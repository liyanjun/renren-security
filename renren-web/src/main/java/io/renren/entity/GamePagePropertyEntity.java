package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 订单表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public class GamePagePropertyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//游戏名
	private String advert;
	//
	private Long updateId;
	//
	private Date updateTime;
	//软删除：1删 -1未删
	private Integer isDel;

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
	public void setAdvert(String advert) {
		this.advert = advert;
	}
	/**
	 * 获取：游戏名
	 */
	public String getAdvert() {
		return advert;
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
}
