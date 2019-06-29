package com.xinnet.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Customer {
    private Long id;

    private String parentId;

    private Long recommendId;

    private String loginName;

    private String password;

    private Integer type;

    private String phone;

    private String email;

    private String title;

    private Date regTime;

    private Integer status;

    private String recommendCode;

    private BigDecimal income;

    private String name;

    private String licenceCode;

    private String orgCode;

    private String country;

    private String province;

    private String city;

    private String address;

    private String zip;

    private String website;

    private String vocation;

    private String identityLink;

    private String recType;

    private Integer favourateFlag;

    private String registerIp;

    private Integer preRegister;

    private String goid;

    private Date phoneCodeTime;

    private String newphoneCode;

    private Date newemailCodeTime;

    private String newemailCode;

    private String phoneCode;

    private String emailCode;

    private Date newphoneCodeTime;

    private Date emailCodeTime;

    private Long industryId;

    private String uuid;

    private String identityName;

    private String identityCode;

    private Integer arrowLimit;

    private Integer loadbalanceWhiteListLimit;

    private Integer whiteListLimit;

    private Long inviterId;

    private Date lockTime;

    private Date editTime;

    private Integer remindStrategy;

    private Boolean allowSecond;

    private Integer loadbalanceLimit;

    private Integer databaseLimit;

    private Integer floatLimit;

    private Integer floatWhiteListLimit;

    private String headImage;

    public Customer(Long id, String parentId, Long recommendId, String loginName, String password, Integer type, String phone, String email, String title, Date regTime, Integer status, String recommendCode, BigDecimal income, String name, String licenceCode, String orgCode, String country, String province, String city, String address, String zip, String website, String vocation, String identityLink, String recType, Integer favourateFlag, String registerIp, Integer preRegister, String goid, Date phoneCodeTime, String newphoneCode, Date newemailCodeTime, String newemailCode, String phoneCode, String emailCode, Date newphoneCodeTime, Date emailCodeTime, Long industryId, String uuid, String identityName, String identityCode, Integer arrowLimit, Integer loadbalanceWhiteListLimit, Integer whiteListLimit, Long inviterId, Date lockTime, Date editTime, Integer remindStrategy, Boolean allowSecond, Integer loadbalanceLimit, Integer databaseLimit, Integer floatLimit, Integer floatWhiteListLimit, String headImage) {
        this.id = id;
        this.parentId = parentId;
        this.recommendId = recommendId;
        this.loginName = loginName;
        this.password = password;
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.title = title;
        this.regTime = regTime;
        this.status = status;
        this.recommendCode = recommendCode;
        this.income = income;
        this.name = name;
        this.licenceCode = licenceCode;
        this.orgCode = orgCode;
        this.country = country;
        this.province = province;
        this.city = city;
        this.address = address;
        this.zip = zip;
        this.website = website;
        this.vocation = vocation;
        this.identityLink = identityLink;
        this.recType = recType;
        this.favourateFlag = favourateFlag;
        this.registerIp = registerIp;
        this.preRegister = preRegister;
        this.goid = goid;
        this.phoneCodeTime = phoneCodeTime;
        this.newphoneCode = newphoneCode;
        this.newemailCodeTime = newemailCodeTime;
        this.newemailCode = newemailCode;
        this.phoneCode = phoneCode;
        this.emailCode = emailCode;
        this.newphoneCodeTime = newphoneCodeTime;
        this.emailCodeTime = emailCodeTime;
        this.industryId = industryId;
        this.uuid = uuid;
        this.identityName = identityName;
        this.identityCode = identityCode;
        this.arrowLimit = arrowLimit;
        this.loadbalanceWhiteListLimit = loadbalanceWhiteListLimit;
        this.whiteListLimit = whiteListLimit;
        this.inviterId = inviterId;
        this.lockTime = lockTime;
        this.editTime = editTime;
        this.remindStrategy = remindStrategy;
        this.allowSecond = allowSecond;
        this.loadbalanceLimit = loadbalanceLimit;
        this.databaseLimit = databaseLimit;
        this.floatLimit = floatLimit;
        this.floatWhiteListLimit = floatWhiteListLimit;
        this.headImage = headImage;
    }

    public Customer() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Long getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode == null ? null : recommendCode.trim();
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLicenceCode() {
        return licenceCode;
    }

    public void setLicenceCode(String licenceCode) {
        this.licenceCode = licenceCode == null ? null : licenceCode.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    public String getVocation() {
        return vocation;
    }

    public void setVocation(String vocation) {
        this.vocation = vocation == null ? null : vocation.trim();
    }

    public String getIdentityLink() {
        return identityLink;
    }

    public void setIdentityLink(String identityLink) {
        this.identityLink = identityLink == null ? null : identityLink.trim();
    }

    public String getRecType() {
        return recType;
    }

    public void setRecType(String recType) {
        this.recType = recType == null ? null : recType.trim();
    }

    public Integer getFavourateFlag() {
        return favourateFlag;
    }

    public void setFavourateFlag(Integer favourateFlag) {
        this.favourateFlag = favourateFlag;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    public Integer getPreRegister() {
        return preRegister;
    }

    public void setPreRegister(Integer preRegister) {
        this.preRegister = preRegister;
    }

    public String getGoid() {
        return goid;
    }

    public void setGoid(String goid) {
        this.goid = goid == null ? null : goid.trim();
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
        this.newphoneCode = newphoneCode == null ? null : newphoneCode.trim();
    }

    public Date getNewemailCodeTime() {
        return newemailCodeTime;
    }

    public void setNewemailCodeTime(Date newemailCodeTime) {
        this.newemailCodeTime = newemailCodeTime;
    }

    public String getNewemailCode() {
        return newemailCode;
    }

    public void setNewemailCode(String newemailCode) {
        this.newemailCode = newemailCode == null ? null : newemailCode.trim();
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode == null ? null : phoneCode.trim();
    }

    public String getEmailCode() {
        return emailCode;
    }

    public void setEmailCode(String emailCode) {
        this.emailCode = emailCode == null ? null : emailCode.trim();
    }

    public Date getNewphoneCodeTime() {
        return newphoneCodeTime;
    }

    public void setNewphoneCodeTime(Date newphoneCodeTime) {
        this.newphoneCodeTime = newphoneCodeTime;
    }

    public Date getEmailCodeTime() {
        return emailCodeTime;
    }

    public void setEmailCodeTime(Date emailCodeTime) {
        this.emailCodeTime = emailCodeTime;
    }

    public Long getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Long industryId) {
        this.industryId = industryId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName == null ? null : identityName.trim();
    }

    public String getIdentityCode() {
        return identityCode;
    }

    public void setIdentityCode(String identityCode) {
        this.identityCode = identityCode == null ? null : identityCode.trim();
    }

    public Integer getArrowLimit() {
        return arrowLimit;
    }

    public void setArrowLimit(Integer arrowLimit) {
        this.arrowLimit = arrowLimit;
    }

    public Integer getLoadbalanceWhiteListLimit() {
        return loadbalanceWhiteListLimit;
    }

    public void setLoadbalanceWhiteListLimit(Integer loadbalanceWhiteListLimit) {
        this.loadbalanceWhiteListLimit = loadbalanceWhiteListLimit;
    }

    public Integer getWhiteListLimit() {
        return whiteListLimit;
    }

    public void setWhiteListLimit(Integer whiteListLimit) {
        this.whiteListLimit = whiteListLimit;
    }

    public Long getInviterId() {
        return inviterId;
    }

    public void setInviterId(Long inviterId) {
        this.inviterId = inviterId;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Integer getRemindStrategy() {
        return remindStrategy;
    }

    public void setRemindStrategy(Integer remindStrategy) {
        this.remindStrategy = remindStrategy;
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

    public Integer getFloatLimit() {
        return floatLimit;
    }

    public void setFloatLimit(Integer floatLimit) {
        this.floatLimit = floatLimit;
    }

    public Integer getFloatWhiteListLimit() {
        return floatWhiteListLimit;
    }

    public void setFloatWhiteListLimit(Integer floatWhiteListLimit) {
        this.floatWhiteListLimit = floatWhiteListLimit;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage == null ? null : headImage.trim();
    }
}