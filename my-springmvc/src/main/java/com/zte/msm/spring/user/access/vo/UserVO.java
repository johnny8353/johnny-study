package com.zte.msm.spring.user.access.vo;

import java.math.BigDecimal;
import java.util.Date;

public class UserVO {
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

    private BigDecimal createdBy;

    private Date createdDate;

    private BigDecimal lastUpdatedBy;

    private Date lastUpdatedDate;

    private String enableFlag;

    private BigDecimal orgId;

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

    public BigDecimal getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigDecimal createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag == null ? null : enableFlag.trim();
    }

    public BigDecimal getOrgId() {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId) {
        this.orgId = orgId;
    }

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", name=" + name + ", account=" + account + ", pwd=" + pwd + ", token=" + token
				+ ", enckey=" + enckey + ", enciv=" + enciv + ", effectiveDate=" + effectiveDate + ", expirationDate="
				+ expirationDate + ", email=" + email + ", mobileNo=" + mobileNo + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedDate="
				+ lastUpdatedDate + ", enableFlag=" + enableFlag + ", orgId=" + orgId + "]";
	}
	
    
    
}