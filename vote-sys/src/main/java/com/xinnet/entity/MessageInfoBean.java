package com.xinnet.entity;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 消息表
 * @author meitao
 */
@Table(name = "message_info")
public class MessageInfoBean {
	public static final MessageInfoBean[] EMPTY = new MessageInfoBean[0];
	/**
	 *（批量更新）
	 */
	@JsonIgnore
	java.lang.String[] id_array;
	/**
	 *
	 */
	@JsonProperty("update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date updateTime;
	/**
	 *是否删除;0否；1是
	 */
	Boolean deleted;
	/**
	 *
	 */
	@JsonProperty("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date createTime;
	/**
	 *自动 auto；手动manual
	 */
	@JsonProperty("send_type")
	String sendType;
	/**
	 *
	 */
	String id;
	/**
	 *消息类型
	 */
	Integer type;
	/**
	 *消息标题 
	 */
	String title;
	/**
	 *消息发送条件（1投票完成 2投票次日）
	 */
	@JsonProperty("send_condition")
	Integer sendCondition;
	/**
	 *定制化查询条件
	 */
	@JsonIgnore
	String _where;
	/**
	 *消息内容
	 */
	String content;

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
	 *
	 */
	public final java.util.Date getUpdateTime() {
		return updateTime;
	}

	/**
	 *
	 */
	public final void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
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
	 *自动 auto；手动manual
	 */
	public final String getSendType() {
		return sendType;
	}

	/**
	 *自动 auto；手动manual
	 */
	public final void setSendType(String sendType) {
		this.sendType = sendType;
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
	 *消息类型
	 */
	public final Integer getType() {
		return type;
	}

	/**
	 *消息类型
	 */
	public final void setType(Integer type) {
		this.type = type;
	}

	/**
	 *消息标题 
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 *消息标题 
	 */
	public final void setTitle(String title) {
		this.title = title;
	}

	/**
	 *消息发送条件（1投票完成 2投票次日）
	 */
	public final Integer getSendCondition() {
		return sendCondition;
	}

	/**
	 *消息发送条件（1投票完成 2投票次日）
	 */
	public final void setSendCondition(Integer sendCondition) {
		this.sendCondition = sendCondition;
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
	 *消息内容
	 */
	public final String getContent() {
		return content;
	}

	/**
	 *消息内容
	 */
	public final void setContent(String content) {
		this.content = content;
	}
}