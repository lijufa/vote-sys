package com.xinnet.entity;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 投票用户特权表
 * @author meitao
 */
@Table(name = "vote_user_privilege")
public class VoteUserPrivilegeBean {
	public static final VoteUserPrivilegeBean[] EMPTY = new VoteUserPrivilegeBean[0];
	/**
	 *
	 */
	@JsonProperty("star_name")
	String starName;
	/**
	 *自定义偶像图片
	 */
	@JsonProperty("idol_image")
	String idolImage;
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
	 *偶像寄语
	 */
	@JsonProperty("idol_message")
	String idolMessage;
	/**
	 *
	 */
	@JsonProperty("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date createTime;
	/**
	 * 用户名字
	 */
	@JsonProperty("user_name")
	String userName;
	/**
	 *定制化查询条件
	 */
	@JsonIgnore
	String _where;
	/**
	 *偶像社群号
	 */
	@JsonProperty("idol_flock_num")
	String idolFlockNum;
	/**
	 *偶像社群二维码
	 */
	@JsonProperty("idol_flock_image")
	String idolFlockImage;
	/**
	 *
	 */
	@JsonProperty("update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date updateTime;
	/**
	 *用户id
	 */
	@JsonProperty("user_id")
	String userId;
	/**
	 *
	 */
	String id;
	/**
	 *
	 */
	@JsonProperty("vote_num")
	Integer voteNum;

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
	 *自定义偶像图片
	 */
	public final String getIdolImage() {
		return idolImage;
	}

	/**
	 *自定义偶像图片
	 */
	public final void setIdolImage(String idolImage) {
		this.idolImage = idolImage;
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
	 *偶像寄语
	 */
	public final String getIdolMessage() {
		return idolMessage;
	}

	/**
	 *偶像寄语
	 */
	public final void setIdolMessage(String idolMessage) {
		this.idolMessage = idolMessage;
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
	 * 用户名字
	 */
	public final String getUserName() {
		return userName;
	}

	/**
	 * 用户名字
	 */
	public final void setUserName(String userName) {
		this.userName = userName;
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
	 *偶像社群号
	 */
	public final String getIdolFlockNum() {
		return idolFlockNum;
	}

	/**
	 *偶像社群号
	 */
	public final void setIdolFlockNum(String idolFlockNum) {
		this.idolFlockNum = idolFlockNum;
	}

	/**
	 *偶像社群二维码
	 */
	public final String getIdolFlockImage() {
		return idolFlockImage;
	}

	/**
	 *偶像社群二维码
	 */
	public final void setIdolFlockImage(String idolFlockImage) {
		this.idolFlockImage = idolFlockImage;
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
	 *用户id
	 */
	public final String getUserId() {
		return userId;
	}

	/**
	 *用户id
	 */
	public final void setUserId(String userId) {
		this.userId = userId;
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
	 *
	 */
	public final Integer getVoteNum() {
		return voteNum;
	}

	/**
	 *
	 */
	public final void setVoteNum(Integer voteNum) {
		this.voteNum = voteNum;
	}
}