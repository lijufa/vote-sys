package com.xinnet.entity;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 消息查看信息表
 * @author meitao
 */
@Table(name = "message_read_info")
public class MessageReadInfoBean {
	public static final MessageReadInfoBean[] EMPTY = new MessageReadInfoBean[0];
	/**
	 * 是否已读(0:否;1:是)
	 */
	Boolean readed;
	/**
	 *（批量更新）
	 */
	@JsonIgnore
	java.lang.String[] id_array;
	/**
	 *是否删除;0否；1是
	 */
	Boolean deleted;
	/**
	 *消息id
	 */
	@JsonProperty("message_id")
	String messageId;
	/**
	 *
	 */
	String id;
	/**
	 *定制化查询条件
	 */
	@JsonIgnore
	String _where;
	/**
	 *消息接收者id
	 */
	@JsonProperty("receive_id")
	String receiveId;

	/**
	 * 是否已读(0:否;1:是)
	 */
	public final Boolean getReaded() {
		return readed;
	}

	/**
	 * 是否已读(0:否;1:是)
	 */
	public final void setReaded(Boolean readed) {
		this.readed = readed;
	}

	/**
	 *（批量更新）
	 */
	public final java.lang.String[] getId_array() {
		return id_array;
	}

	/**
	 *（批量更新）
	 */
	public final void setId_array(java.lang.String[] id_array) {
		this.id_array = id_array;
	}

	/**
	 *是否删除;0否；1是
	 */
	public final Boolean getDeleted() {
		return deleted;
	}

	/**
	 *是否删除;0否；1是
	 */
	public final void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 *消息id
	 */
	public final String getMessageId() {
		return messageId;
	}

	/**
	 *消息id
	 */
	public final void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 *
	 */
	public final String getId() {
		return id;
	}

	/**
	 *
	 */
	public final void setId(String id) {
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
	 *消息接收者id
	 */
	public final String getReceiveId() {
		return receiveId;
	}

	/**
	 *消息接收者id
	 */
	public final void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
}