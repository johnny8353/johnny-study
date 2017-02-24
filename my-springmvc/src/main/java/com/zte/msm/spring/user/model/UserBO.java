package com.zte.msm.spring.user.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserBO
{
    private Long id;

    private String name;

    private String account;

    private String pwd;

    private String token;

    private String enckey;

    private String enciv;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date effectiveDate;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date expirationDate;
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createdDate;
    
    private String email;

    private String mobileNo;

    private BigDecimal orgId;
    
    private String enableFlag;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getEnckey()
    {
        return enckey;
    }

    public void setEnckey(String enckey)
    {
        this.enckey = enckey;
    }

    public String getEnciv()
    {
        return enciv;
    }

    public void setEnciv(String enciv)
    {
        this.enciv = enciv;
    }

    public Date getEffectiveDate()
    {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo)
    {
        this.mobileNo = mobileNo;
    }

    public BigDecimal getOrgId()
    {
        return orgId;
    }

    public void setOrgId(BigDecimal orgId)
    {
        this.orgId = orgId;
    }

    public Date getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        this.createdDate = createdDate;
    }

    public String getEnableFlag()
    {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag)
    {
        this.enableFlag = enableFlag;
    }

    /**
    	POJO类必须写toString方法。使用工具类source> generate toString时，如果继承了另一个POJO类，注意在前面加一下super.toString。
		说明：在方法执行抛出异常时，可以直接调用POJO的toString()方法打印其属性值，便于排查问题。
     */
	@Override
	public String toString() {
		return "UserBO [id=" + id + ", name=" + name + ", account=" + account + ", pwd=" + pwd + ", token=" + token
				+ ", enckey=" + enckey + ", enciv=" + enciv + ", effectiveDate=" + effectiveDate + ", expirationDate="
				+ expirationDate + ", createdDate=" + createdDate + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", orgId=" + orgId + ", enableFlag=" + enableFlag + "]";
	}
    
    
}