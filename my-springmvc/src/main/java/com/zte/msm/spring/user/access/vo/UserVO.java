package com.zte.msm.spring.user.access.vo;

import java.util.Date;

import com.zte.msm.frame.base.BaseVO;
/**
 * 继承BaseVO，保证有表的必须字段
 * @author JohnnyHuang黄福强
 */
public class UserVO extends BaseVO{
    private Long id;

    private String name;

    private String account;

    private String pwd;

    private String token;

    private String enckey;

    private String enciv;

    private Date effectiveDate;

    private Date expirationDate;

    private String email;

    private String mobileNo;

    private Date createDate;

    private Long createBy;

    private Date lastUpdDate;

    private Long lastUpdBy;

    private Long modificationNum;

    private String enableFlag;

    private Long orgId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getEnckey() {
        return enckey;
    }

    public void setEnckey(String enckey) {
        this.enckey = enckey == null ? null : enckey.trim();
    }

    public String getEnciv() {
        return enciv;
    }

    public void setEnciv(String enciv) {
        this.enciv = enciv == null ? null : enciv.trim();
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo == null ? null : mobileNo.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getLastUpdDate() {
        return lastUpdDate;
    }

    public void setLastUpdDate(Date lastUpdDate) {
        this.lastUpdDate = lastUpdDate;
    }

    public Long getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(Long lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

    public Long getModificationNum() {
        return modificationNum;
    }

    public void setModificationNum(Long modificationNum) {
        this.modificationNum = modificationNum;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag == null ? null : enableFlag.trim();
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", account=" + account + ", pwd=" + pwd + ", token=" + token
				+ ", enckey=" + enckey + ", enciv=" + enciv + ", effectiveDate=" + effectiveDate + ", expirationDate="
				+ expirationDate + ", email=" + email + ", mobileNo=" + mobileNo + ", createDate=" + createDate
				+ ", createBy=" + createBy + ", lastUpdDate=" + lastUpdDate + ", lastUpdBy=" + lastUpdBy
				+ ", modificationNum=" + modificationNum + ", enableFlag=" + enableFlag + ", orgId=" + orgId + "]";
	}
    
    
}