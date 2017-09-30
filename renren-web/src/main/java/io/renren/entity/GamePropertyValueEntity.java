package io.renren.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 属性值表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public class GamePropertyValueEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//类目属性ID
	private Long gamePropertyId;
	//
	private Long gameOrderId;
	//属性名，查询时用
	private String name;
	//类目属性值
	private String value;
	//
	private Integer creatorId;
	//
	private Date createTime;

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
	 * 设置：类目属性ID
	 */
	public void setGamePropertyId(Long gamePropertyId) {
		this.gamePropertyId = gamePropertyId;
	}
	/**
	 * 获取：类目属性ID
	 */
	public Long getGamePropertyId() {
		return gamePropertyId;
	}
	/**
	 * 设置：
	 */
	public void setGameOrderId(Long gameOrderId) {
		this.gameOrderId = gameOrderId;
	}
	/**
	 * 获取：
	 */
	public Long getGameOrderId() {
		return gameOrderId;
	}
	/**
	 * 设置：类目属性值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：类目属性值
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：
	 */
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	/**
	 * 获取：
	 */
	public Integer getCreatorId() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
