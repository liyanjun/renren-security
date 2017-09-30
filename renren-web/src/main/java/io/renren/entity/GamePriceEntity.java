package io.renren.entity;

import io.renren.validator.group.AddGroup;
import io.renren.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 游戏价格
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-05-28 21:27:52
 */
public class GamePriceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//票面价格
	@NotNull(message = "票面价格不能为空", groups = {AddGroup.class, UpdateGroup.class})
	@DecimalMin(value = "0.00",message = "票面价格应该大于0", groups = {AddGroup.class, UpdateGroup.class})
	private BigDecimal price;
	//对应的充值虚拟货币
	@NotBlank(message = "虚拟货币不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String virtualExchange;

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
	 * 设置：票面价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：票面价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：对应的充值虚拟货币
	 */
	public void setVirtualExchange(String virtualExchange) {
		this.virtualExchange = virtualExchange;
	}
	/**
	 * 获取：对应的充值虚拟货币
	 */
	public String getVirtualExchange() {
		return virtualExchange;
	}
}
