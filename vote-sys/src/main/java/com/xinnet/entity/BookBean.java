package com.xinnet.entity;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author meitao
 */
@Table(name = "book")
public class BookBean {
	public static final BookBean[] EMPTY = new BookBean[0];
	/**
	 *库存数量
	 */
	Integer number;
	/**
	 *图书照片
	 */
	String image;
	/**
	 *（批量更新）
	 */
	@JsonIgnore
	java.lang.Long[] id_array;
	/**
	 *
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date createTime;
	/**
	 *书名
	 */
	String name;
	/**
	 *
	 */
	Long id;
	/**
	 *定制化查询条件
	 */
	@JsonIgnore
	String _where;
	/**
	 *图书描述
	 */
	String desc;

	/**
	 *库存数量
	 */
	public final Integer getNumber() {
		return number;
	}

	/**
	 *库存数量
	 */
	public final void setNumber(Integer number) {
		this.number = number;
	}

	/**
	 *图书照片
	 */
	public final String getImage() {
		return image;
	}

	/**
	 *图书照片
	 */
	public final void setImage(String image) {
		this.image = image;
	}

	/**
	 *（批量更新）
	 */
	public final java.lang.Long[] getId_array() {
		return id_array;
	}

	/**
	 *（批量更新）
	 */
	public final void setId_array(java.lang.Long[] id_array) {
		this.id_array = id_array;
	}

	/**
	 *
	 */
	public final java.util.Date getCreateTime() {
		return createTime;
	}

	/**
	 *
	 */
	public final void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 *书名
	 */
	public final String getName() {
		return name;
	}

	/**
	 *书名
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 */
	public final Long getId() {
		return id;
	}

	/**
	 *
	 */
	public final void setId(Long id) {
		this.id = id;
	}

	/**
	 *定制化查询条件
	 */
	public final String get_Where() {
		return _where;
	}

	/**
	 *定制化查询条件
	 */
	public final void set_Where(String _where) {
		this._where = _where;
	}

	/**
	 *图书描述
	 */
	public final String getDesc() {
		return desc;
	}

	/**
	 *图书描述
	 */
	public final void setDesc(String desc) {
		this.desc = desc;
	}
}