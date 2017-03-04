package com.zte.msm.spring.user.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zte.msm.frame.base.BaseBO;

public class UserBO extends BaseBO
{
    private Long id;
    
    @Size(min=2,message="{userBO.name.Size}")    //字符
    private String name;
    
    @Size(min=8,message="{userBO.account.Size}")
    private String account;

    private String token;

    private String enckey;

    private String enciv;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date effectiveDate;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;

    private String email;

    private String mobileNo;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    
    @NotNull
    private Long createBy;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date lastUpdDate;
    private Long lastUpdBy;
    private Long modificationNum;
    private String enableFlag;
    @NotNull
    private Long orgId;


    /**
    	POJO类必须写toString方法。使用工具类source> generate toString时，如果继承了另一个POJO类，注意在前面加一下super.toString。
		说明：在方法执行抛出异常时，可以直接调用POJO的toString()方法打印其属性值，便于排查问题。
     */
    @Override
    public String toString() {
    	return "UserBO [id=" + id + ", name=" + name + ", account=" + account + ", token=" + token
    			+ ", enckey=" + enckey + ", enciv=" + enciv + ", effectiveDate=" + effectiveDate + ", expirationDate="
    			+ expirationDate + ", email=" + email + ", mobileNo=" + mobileNo + ", createDate=" + createDate
    			+ ", createBy=" + createBy + ", lastUpdDate=" + lastUpdDate + ", lastUpdBy=" + lastUpdBy
    			+ ", modificationNum=" + modificationNum + ", enableFlag=" + enableFlag + ", orgId=" + orgId + "]";
    }


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
		this.name = name;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getEnckey() {
		return enckey;
	}


	public void setEnckey(String enckey) {
		this.enckey = enckey;
	}


	public String getEnciv() {
		return enciv;
	}


	public void setEnciv(String enciv) {
		this.enciv = enciv;
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
		this.email = email;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
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
		this.enableFlag = enableFlag;
	}


	public Long getOrgId() {
		return orgId;
	}


	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
    
    
}