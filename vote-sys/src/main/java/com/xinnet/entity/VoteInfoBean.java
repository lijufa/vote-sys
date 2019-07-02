package com.xinnet.entity;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 投票信息表
 * @author meitao
 */
@Table(name = "vote_info")
public class VoteInfoBean {
	public static final VoteInfoBean[] EMPTY = new VoteInfoBean[0];
	/**
	 *
	 */
	@JsonProperty("star_name")
	String starName;
	/**
	 *（批量更新）
	 */
	@JsonIgnore
	java.lang.String[] id_array;
	/**
	 *明星id
	 */
	@JsonProperty("star_id")
	String starId;
	/**
	 *
	 */
	@JsonProperty("update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date updateTime;
	/**
	 *
	 */
	@JsonProperty("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date createTime;
	/**
	 *
	 */
	@JsonProperty("user_id")
	String userId;
	/**
	 *
	 */
	@JsonProperty("user_name")
	String userName;
	/**
	 *
	 */
	String id;
	/**
	 *投票数
	 */
	@JsonProperty("vote_num")
	Integer voteNum;
	/**
	 *定制化查询条件
	 */
	@JsonIgnore
	String _where;

	/**
	 *
	 */
	public final String getStarName() {
		return starName;
	}

	/**
	 *
	 */
	public final void setStarName(String starName) {
		this.starName = starName;
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
	 *明星id
	 */
	public final String getStarId() {
		return starId;
	}

	/**
	 *明星id
	 */
	public final void setStarId(String starId) {
		this.starId = starId;
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
	 *
	 */
	public final String getUserId() {
		return userId;
	}

	/**
	 *
	 */
	public final void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 *
	 */
	public final String getUserName() {
		return userName;
	}

	/**
	 *
	 */
	public final void setUserName(String userName) {
		this.userName = userName;
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
	 *投票数
	 */
	public final Integer getVoteNum() {
		return voteNum;
	}

	/**
	 *投票数
	 */
	public final void setVoteNum(Integer voteNum) {
		this.voteNum = voteNum;
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