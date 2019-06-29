package com.xinnet.entity;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 
 * @author meitao
 */
@Table(name = "account")
public class AccountBean {
	public static final AccountBean[] EMPTY = new AccountBean[0];
	/**
	 *
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date cratetime;
	/**
	 *（批量更新）
	 */
	@JsonIgnore
	java.lang.Integer[] id_array;
	/**
	 *密码
	 */
	@JsonProperty("pass_word")
	String passWord;
	/**
	 *
	 */
	Integer id;
	/**
	 *定制化查询条件
	 */
	@JsonIgnore
	String _where;
	/**
	 *账号
	 */
	String account;

	/**
	 *
	 */
	public final java.util.Date getCratetime() {
		return cratetime;
	}

	/**
	 *
	 */
	public final void setCratetime(java.util.Date cratetime) {
		this.cratetime = cratetime;
	}

	/**
	 *（批量更新）
	 */
	public final java.lang.Integer[] getId_array() {
		return id_array;
	}

	/**
	 *（批量更新）
	 */
	public final void setId_array(java.lang.Integer[] id_array) {
		this.id_array = id_array;
	}

	/**
	 *密码
	 */
	public final String getPassWord() {
		return passWord;
	}

	/**
	 *密码
	 */
	public final void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	/**
	 *
	 */
	public final Integer getId() {
		return id;
	}

	/**
	 *
	 */
	public final void setId(Integer id) {
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
	 *账号
	 */
	public final String getAccount() {
		return account;
	}

	/**
	 *账号
	 */
	public final void setAccount(String account) {
		this.account = account;
	}
}