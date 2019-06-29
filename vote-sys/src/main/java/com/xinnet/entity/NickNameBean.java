package com.xinnet.entity;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author meitao
 */
@Table(name = "com_nick_name")
public class NickNameBean {
	public static final NickNameBean[] EMPTY = new NickNameBean[0];
	/**
	 *（批量更新）
	 */
	@JsonIgnore
	java.lang.Long[] id_array;
	/**
	 *是否删除(0:否;1:是)
	 */
	Boolean deleted;
	/**
	 *
	 */
	@JsonProperty("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date createTime;
	/**
	 *昵称
	 */
	@JsonProperty("nick_name")
	String nickName;
	/**
	 *
	 */
	Long id;
	/**
	 *是否已使用(0:否;1:是)
	 */
	Boolean used;
	/**
	 *定制化查询条件
	 */
	@JsonIgnore
	String _where;

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
	 *是否删除(0:否;1:是)
	 */
	public final Boolean getDeleted() {
		return deleted;
	}

	/**
	 *是否删除(0:否;1:是)
	 */
	public final void setDeleted(Boolean deleted) {
		this.deleted = deleted;
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
	 *昵称
	 */
	public final String getNickName() {
		return nickName;
	}

	/**
	 *昵称
	 */
	public final void setNickName(String nickName) {
		this.nickName = nickName;
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
	 *是否已使用(0:否;1:是)
	 */
	public final Boolean getUsed() {
		return used;
	}

	/**
	 *是否已使用(0:否;1:是)
	 */
	public final void setUsed(Boolean used) {
		this.used = used;
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
}