package com.xinnet.entity;

import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户表
 * @author meitao
 */
@Table(name = "vote_user")
public class VoteUserBean {
	public static final VoteUserBean[] EMPTY = new VoteUserBean[0];
	/**
	 *出生日期
	 */
	String birthday;
	/**
	 *备注
	 */
	String remark;
	/**
	 *定制化查询条件
	 */
	@JsonIgnore
	String _where;
	/**
	 *图像
	 */
	@JsonProperty("head_image")
	String headImage;
	/**
	 *
	 */
	@JsonProperty("update_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date updateTime;
	/**
	 *
	 */
	String password;
	/**
	 *星座
	 */
	String constellation;
	/**
	 *兴趣，爱好
	 */
	String interest;
	/**
	 * 擅长技能（多个以逗号分隔）
	 */
	String skill;
	/**
	 *
	 */
	String id;
	/**
	 *邮箱
	 */
	String email;
	/**
	 *身高
	 */
	Integer height;
	/**
	 *个人描述
	 */
	@JsonProperty("person_desc")
	String personDesc;
	/**
	 *（批量更新）
	 */
	@JsonIgnore
	java.lang.String[] id_array;
	/**
	 *地址
	 */
	String address;
	/**
	 *
	 */
	@JsonProperty("create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	java.util.Date createTime;
	/**
	 *组号 
	 */
	@JsonProperty("group_num")
	Integer groupNum;
	/**
	 *0男；  1女
	 */
	Boolean sex;
	/**
	 *体重
	 */
	Integer weight;
	/**
	 *电话验证码
	 */
	Integer phonecode;
	/**
	 *个人网址
	 */
	@JsonProperty("person_website")
	String personWebsite;
	/**
	 *首页显示顺序
	 */
	Integer sequence;
	/**
	 *是否删除;0否；1是
	 */
	Boolean deleted;
	/**
	 *电话
	 */
	Integer phone;
	/**
	 *昵称
	 */
	@JsonProperty("nick_name")
	String nickName;
	/**
	 * 姓名
	 */
	String name;
	/**
	 *
	 */
	Integer age;

	/**
	 *出生日期
	 */
	public final String getBirthday() {
		return birthday;
	}

	/**
	 *出生日期
	 */
	public final void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 *备注
	 */
	public final String getRemark() {
		return remark;
	}

	/**
	 *备注
	 */
	public final void setRemark(String remark) {
		this.remark = remark;
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
	 *图像
	 */
	public final String getHeadImage() {
		return headImage;
	}

	/**
	 *图像
	 */
	public final void setHeadImage(String headImage) {
		this.headImage = headImage;
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
	public final String getPassword() {
		return password;
	}

	/**
	 *
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 *星座
	 */
	public final String getConstellation() {
		return constellation;
	}

	/**
	 *星座
	 */
	public final void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	/**
	 *兴趣，爱好
	 */
	public final String getInterest() {
		return interest;
	}

	/**
	 *兴趣，爱好
	 */
	public final void setInterest(String interest) {
		this.interest = interest;
	}

	/**
	 * 擅长技能（多个以逗号分隔）
	 */
	public final String getSkill() {
		return skill;
	}

	/**
	 * 擅长技能（多个以逗号分隔）
	 */
	public final void setSkill(String skill) {
		this.skill = skill;
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
	 *邮箱
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 *邮箱
	 */
	public final void setEmail(String email) {
		this.email = email;
	}

	/**
	 *身高
	 */
	public final Integer getHeight() {
		return height;
	}

	/**
	 *身高
	 */
	public final void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 *个人描述
	 */
	public final String getPersonDesc() {
		return personDesc;
	}

	/**
	 *个人描述
	 */
	public final void setPersonDesc(String personDesc) {
		this.personDesc = personDesc;
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
	 *地址
	 */
	public final String getAddress() {
		return address;
	}

	/**
	 *地址
	 */
	public final void setAddress(String address) {
		this.address = address;
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
	 *组号 
	 */
	public final Integer getGroupNum() {
		return groupNum;
	}

	/**
	 *组号 
	 */
	public final void setGroupNum(Integer groupNum) {
		this.groupNum = groupNum;
	}

	/**
	 *0男；  1女
	 */
	public final Boolean getSex() {
		return sex;
	}

	/**
	 *0男；  1女
	 */
	public final void setSex(Boolean sex) {
		this.sex = sex;
	}

	/**
	 *体重
	 */
	public final Integer getWeight() {
		return weight;
	}

	/**
	 *体重
	 */
	public final void setWeight(Integer weight) {
		this.weight = weight;
	}

	/**
	 *电话验证码
	 */
	public final Integer getPhonecode() {
		return phonecode;
	}

	/**
	 *电话验证码
	 */
	public final void setPhonecode(Integer phonecode) {
		this.phonecode = phonecode;
	}

	/**
	 *个人网址
	 */
	public final String getPersonWebsite() {
		return personWebsite;
	}

	/**
	 *个人网址
	 */
	public final void setPersonWebsite(String personWebsite) {
		this.personWebsite = personWebsite;
	}

	/**
	 *首页显示顺序
	 */
	public final Integer getSequence() {
		return sequence;
	}

	/**
	 *首页显示顺序
	 */
	public final void setSequence(Integer sequence) {
		this.sequence = sequence;
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
	 *电话
	 */
	public final Integer getPhone() {
		return phone;
	}

	/**
	 *电话
	 */
	public final void setPhone(Integer phone) {
		this.phone = phone;
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
	 * 姓名
	 */
	public final String getName() {
		return name;
	}

	/**
	 * 姓名
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 *
	 */
	public final Integer getAge() {
		return age;
	}

	/**
	 *
	 */
	public final void setAge(Integer age) {
		this.age = age;
	}
}