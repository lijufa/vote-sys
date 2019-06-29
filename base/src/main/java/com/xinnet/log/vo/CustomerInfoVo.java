package com.xinnet.log.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.codehaus.jackson.annotate.JsonProperty;
import com.xinnet.log.utils.Point;

public class CustomerInfoVo implements Serializable {
	private static final long serialVersionUID = 7231028343544219275L;
	@JsonProperty("zip")
	String zip;//邮政编码
	@JsonProperty("phone")
	String phone;//电话
	@JsonProperty("new_phone")
	String newPhone;//新电话
	@JsonProperty("website")
	String website;//网址
	@JsonProperty("status")
	Integer status;//客户状态(0:未验证;1:已验证;2:已冻结)
	@JsonProperty("reg_time")
	Date regTime;//注册时间
	@JsonProperty("org_code")
	String orgCode;//组织机构代码号
	@JsonProperty("type")
	Integer type;//客户类型(0:普通用户;1:企业用户;2:渠道用户)
	@JsonProperty("password")
	String password;//密码
	@JsonProperty("new_password")
	String newPassword;//新密码，修改密码用
	@JsonProperty("city")
	String city;//所在城市
	@JsonProperty("country")
	String country;//所在国家
	@JsonProperty("id")
	Long id;//客户编号
	@JsonProperty("vocation")
	Integer vocation;//所属行业
	@JsonProperty("licence_code")
	String licenceCode;//营业执照图片保存位置
	@JsonProperty("recommend_id")
	Long recommendId;//推荐人编号
	@JsonProperty("title")
	String title;//职务
	@JsonProperty("address")
	String address;//通讯地址
	@JsonProperty("email")
	String email;//邮箱
	@JsonProperty("new_email")
	String newEmail;//新邮箱
	@JsonProperty("login_name")
	String loginName;//客户登录名
	@JsonProperty("recommend_code")
	String recommendCode;//推荐码
	@JsonProperty("name")
	String name;//客户名称
	@JsonProperty("province")
	String province;//所在省份
	@JsonProperty("identity_link")
	String identityLink;//身份证图片存放位置
	@JsonProperty("ver_code")
	String verCode;// 手机/邮箱验证码
	@JsonProperty("new_ver_code")
	String newVerCode;// 新的手机/邮箱验证码
	@JsonProperty("send_mail_time")
	Date sendMailTime;//邮件发送时间
	@JsonProperty("business_id")
	Long businessId;//业务编号
	@JsonProperty("token")
	String token;//认证签名 暂时用来接收滑块验证的token
	@JsonProperty("sys_identity")
	String sysIdentity;//系统标识
	@JsonProperty("login")
	String login;
	@JsonProperty("charge")
	Integer charge;//0未充过值1充过值
	@JsonProperty("rec_type")
	String recType;//推荐类型
	@JsonProperty("cb")
	String cb;
	@JsonProperty("register_ip")
	String registerIp; //注册ip
	@JsonProperty("favourate_flag")
	Integer favourateFlag; //优惠返劵标记(0:否,1：是)
	@JsonProperty("pre_register")
	Integer preRegister; //是否预注册用户(0:否,1：是)
	@JsonProperty("recommend_count")
	Integer recommendCount; //推荐数量
	@JsonProperty("goid")
	String goid; //前端展示id
	@JsonProperty("income")
	Double income;//收益
	@JsonProperty("phone_code")
	String phoneCode;
	@JsonProperty("phone_code_time")
	Date phoneCodeTime;
	@JsonProperty("new_phone_code")
	String newphoneCode;
	@JsonProperty("new_phone_code_time")
	Date newphoneCodeTime;
	@JsonProperty("email_code")
	String emailCode;
	@JsonProperty("email_code_time")
	Date emailCodeTime;
	@JsonProperty("new_email_code")
	String newemailCode;
	@JsonProperty("new_email_code_time")
	Date newemailCodeTime;
	@JsonProperty("ver_code_flag")
	String verCodeFlag; //(验证码标记:validate为验证手机;update为用于修改手机)
	@JsonProperty("uuid")
	String uuid; //用户uuid
	@JsonProperty("industry_id")
	Long industryId; //所属行业id
	@JsonProperty("identity_name")
	String identityName;//上传省份证名称
	@JsonProperty("arrow_limit")
	Integer arrowLimit; //用户购买云主机剩余配额
	@JsonProperty("white_list_limit")
	Integer whitelistLimit; //用户每台云主机的白名单配额限制
	@JsonProperty("arrow_limit_rest")
	Integer arrowLimitRest; //用户购买云主机剩余配额
	@JsonProperty("identity_code")
	String identityCode;//身份证号
	String flag; //用户标识
	String referral_code; //推荐码
	String rc; //营销网站使用
	String invite_code; //邀请码
	@JsonProperty("inviter_id")
	Long inviterId; //邀请人编号
	Integer mark;//注册标识
	@JsonProperty("lock_minute")
	Integer lockMinute; //手机号验锁定时间（分钟）
	String term;//成语
	List<Point> position;//成语中3个随机汉字的位置坐标
	String points;//前台传进来的坐标，6个逗号分隔的字符串
	@JsonProperty("validate_token")
	String validateToken;//图片验证码token,暂时不用，就用token属性
	@JsonProperty("x")
	Integer x;//图片验证码坐标
	//提醒策略字段
	Integer remindStrategy;
	//是否能购买智能按秒的主机
	@JsonProperty("allow_second")
	Boolean allowSecond;
	@JsonProperty("loadbalance_limit")
	Integer loadbalanceLimit;//负载均衡配额限制
	@JsonProperty("database_limit")
	Integer databaseLimit;//数据库配额限制
	@JsonProperty("float_limit")
	Integer floatLimit;//弹性IP配额限制
	@JsonProperty("loadbalance_limit_rest")
	Integer loadbalanceLimitRest; //用户购买负载均衡剩余配额
	@JsonProperty("database_limit_rest")
	Integer databaseLimitRest; //用户购买云数据库剩余配额
	@JsonProperty("float_limit_rest")
	Integer floatLimitRest; //用户购买弹性IP剩余配额
	@JsonProperty("float_white_list_limit")
	Integer floatWhitelistLimit;//弹性ip白名单限制
	@JsonProperty("head_image")
	String headImage;
	@JsonProperty("openstack_token") //X-Subject-Token
	String openstackToken;
	@JsonProperty("openstack_id") //租户ID
	String openstackId;
	@JsonProperty("openstack_net_id") //网络ID
	String openstackNetId;
	@JsonProperty("security_group_id")
	String securityGroupId;
	@JsonProperty("hk_net_id")
	String hkNetId;
	@JsonProperty("hk_group_id")
	String hkGroupId;

	public Integer getRemindStrategy() {
		return remindStrategy;
	}

	public void setRemindStrategy(Integer remindStrategy) {
		this.remindStrategy = remindStrategy;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getLockMinute() {
		return lockMinute;
	}

	public void setLockMinute(Integer lockMinute) {
		this.lockMinute = lockMinute;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getVocation() {
		return vocation;
	}

	public void setVocation(Integer vocation) {
		this.vocation = vocation;
	}

	public String getLicenceCode() {
		return licenceCode;
	}

	public void setLicenceCode(String licenceCode) {
		this.licenceCode = licenceCode;
	}

	public Long getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(Long recommendId) {
		this.recommendId = recommendId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRecommendCode() {
		return recommendCode;
	}

	public void setRecommendCode(String recommendCode) {
		this.recommendCode = recommendCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getIdentityLink() {
		return identityLink;
	}

	public void setIdentityLink(String identityLink) {
		this.identityLink = identityLink;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSysIdentity() {
		return sysIdentity;
	}

	public void setSysIdentity(String sysIdentity) {
		this.sysIdentity = sysIdentity;
	}

	public Date getSendMailTime() {
		return sendMailTime;
	}

	public void setSendMailTime(Date sendMailTime) {
		this.sendMailTime = sendMailTime;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	public String getNewPhone() {
		return newPhone;
	}

	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public String getNewVerCode() {
		return newVerCode;
	}

	public void setNewVerCode(String newVerCode) {
		this.newVerCode = newVerCode;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
	}

	public String getCb() {
		return cb;
	}

	public void setCb(String cb) {
		this.cb = cb;
	}

	public String getRecType() {
		return recType;
	}

	public void setRecType(String recType) {
		this.recType = recType;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public Integer getFavourateFlag() {
		return favourateFlag;
	}

	public void setFavourateFlag(Integer favourateFlag) {
		this.favourateFlag = favourateFlag;
	}

	public Integer getPreRegister() {
		return preRegister;
	}

	public void setPreRegister(Integer preRegister) {
		this.preRegister = preRegister;
	}

	public Integer getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(Integer recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getGoid() {
		return goid;
	}

	public void setGoid(String goid) {
		this.goid = goid;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public Date getPhoneCodeTime() {
		return phoneCodeTime;
	}

	public void setPhoneCodeTime(Date phoneCodeTime) {
		this.phoneCodeTime = phoneCodeTime;
	}

	public String getNewphoneCode() {
		return newphoneCode;
	}

	public void setNewphoneCode(String newphoneCode) {
		this.newphoneCode = newphoneCode;
	}

	public Date getNewphoneCodeTime() {
		return newphoneCodeTime;
	}

	public void setNewphoneCodeTime(Date newphoneCodeTime) {
		this.newphoneCodeTime = newphoneCodeTime;
	}

	public String getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(String emailCode) {
		this.emailCode = emailCode;
	}

	public Date getEmailCodeTime() {
		return emailCodeTime;
	}

	public void setEmailCodeTime(Date emailCodeTime) {
		this.emailCodeTime = emailCodeTime;
	}

	public String getNewemailCode() {
		return newemailCode;
	}

	public void setNewemailCode(String newemailCode) {
		this.newemailCode = newemailCode;
	}

	public Date getNewemailCodeTime() {
		return newemailCodeTime;
	}

	public void setNewemailCodeTime(Date newemailCodeTime) {
		this.newemailCodeTime = newemailCodeTime;
	}

	public String getVerCodeFlag() {
		return verCodeFlag;
	}

	public void setVerCodeFlag(String verCodeFlag) {
		this.verCodeFlag = verCodeFlag;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Long industryId) {
		this.industryId = industryId;
	}

	public String getIdentityName() {
		return identityName;
	}

	public void setIdentityName(String identityName) {
		this.identityName = identityName;
	}

	public final Integer getArrowLimit() {
		return arrowLimit;
	}

	public final void setArrowLimit(Integer arrowLimit) {
		this.arrowLimit = arrowLimit;
	}

	public final Integer getWhitelistLimit() {
		return whitelistLimit;
	}

	public final void setWhitelistLimit(Integer whitelistLimit) {
		this.whitelistLimit = whitelistLimit;
	}

	public final Integer getArrowLimitRest() {
		return arrowLimitRest;
	}

	public final void setArrowLimitRest(Integer arrowLimitRest) {
		this.arrowLimitRest = arrowLimitRest;
	}

	public String getIdentityCode() {
		return identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getReferral_code() {
		return referral_code;
	}

	public void setReferral_code(String referral_code) {
		this.referral_code = referral_code;
	}

	public String getRc() {
		return rc;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

	public String getInvite_code() {
		return invite_code;
	}

	public void setInvite_code(String invite_code) {
		this.invite_code = invite_code;
	}

	public Long getInviterId() {
		return inviterId;
	}

	public void setInviterId(Long inviterId) {
		this.inviterId = inviterId;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<Point> getPosition() {
		return position;
	}

	public void setPosition(List<Point> position) {
		this.position = position;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getValidateToken() {
		return validateToken;
	}

	public void setValidateToken(String validateToken) {
		this.validateToken = validateToken;
	}

	public Boolean getAllowSecond() {
		return allowSecond;
	}

	public void setAllowSecond(Boolean allowSecond) {
		this.allowSecond = allowSecond;
	}

	public Integer getLoadbalanceLimit() {
		return loadbalanceLimit;
	}

	public void setLoadbalanceLimit(Integer loadbalanceLimit) {
		this.loadbalanceLimit = loadbalanceLimit;
	}

	public Integer getDatabaseLimit() {
		return databaseLimit;
	}

	public void setDatabaseLimit(Integer databaseLimit) {
		this.databaseLimit = databaseLimit;
	}

	public Integer getLoadbalanceLimitRest() {
		return loadbalanceLimitRest;
	}

	public void setLoadbalanceLimitRest(Integer loadbalanceLimitRest) {
		this.loadbalanceLimitRest = loadbalanceLimitRest;
	}

	public Integer getDatabaseLimitRest() {
		return databaseLimitRest;
	}

	public void setDatabaseLimitRest(Integer databaseLimitRest) {
		this.databaseLimitRest = databaseLimitRest;
	}

	public Integer getFloatLimit() {
		return floatLimit;
	}

	public void setFloatLimit(Integer floatLimit) {
		this.floatLimit = floatLimit;
	}

	public Integer getFloatLimitRest() {
		return floatLimitRest;
	}

	public void setFloatLimitRest(Integer floatLimitRest) {
		this.floatLimitRest = floatLimitRest;
	}

	public Integer getFloatWhitelistLimit() {
		return floatWhitelistLimit;
	}

	public void setFloatWhitelistLimit(Integer floatWhitelistLimit) {
		this.floatWhitelistLimit = floatWhitelistLimit;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getOpenstackToken() {
		return openstackToken;
	}

	public void setOpenstackToken(String openstackToken) {
		this.openstackToken = openstackToken;
	}

	public String getOpenstackId() {
		return openstackId;
	}

	public void setOpenstackId(String openstackId) {
		this.openstackId = openstackId;
	}

	public String getOpenstackNetId() {
		return openstackNetId;
	}

	public void setOpenstackNetId(String openstackNetId) {
		this.openstackNetId = openstackNetId;
	}

	public String getSecurityGroupId() {
		return securityGroupId;
	}

	public void setSecurityGroupId(String securityGroupId) {
		this.securityGroupId = securityGroupId;
	}

	public String getHkNetId() {
		return hkNetId;
	}

	public void setHkNetId(String hkNetId) {
		this.hkNetId = hkNetId;
	}

	public String getHkGroupId() {
		return hkGroupId;
	}

	public void setHkGroupId(String hkGroupId) {
		this.hkGroupId = hkGroupId;
	}
}
