package io.renren.entity;

import io.renren.validator.group.AddGroup;
import io.renren.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;



/**
 * 属性表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public class GamePropertyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long id;
	//字段名称
	@NotBlank(message="字段名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	//字段label
	@NotBlank(message = "label不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String label;
	@NotBlank(message = "提示不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String placeholder;

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
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：属性名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：属性名称
	 */
	public String getName() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
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
}
