package com.zte.msm.frame.log.access.vo;

import java.util.Date;

import com.zte.msm.frame.base.BaseVO;

public class LogVO extends BaseVO{
    private Long id;

    private Date createDate;

    private Long createBy;

    private Date lastUpdDate;

    private Long lastUpdBy;

    private Long modificationNum;

    private String userId;
    private String linkId;

    private String userName;
    private String status;

    private String className;

    private String hsrClassName;

    private String methodName;

    private String requestHead;

    private String requestFullUrl;

    private String requestUrl;

    private String logDesc;

    private String spendTime;

    private String beginTime;

    private String endTime;

    private String clientInfo;

    private String serverInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getHsrClassName() {
        return hsrClassName;
    }

    public void setHsrClassName(String hsrClassName) {
        this.hsrClassName = hsrClassName == null ? null : hsrClassName.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public String getRequestHead() {
        return requestHead;
    }

    public void setRequestHead(String requestHead) {
        this.requestHead = requestHead == null ? null : requestHead.trim();
    }

    public String getRequestFullUrl() {
        return requestFullUrl;
    }

    public void setRequestFullUrl(String requestFullUrl) {
        this.requestFullUrl = requestFullUrl == null ? null : requestFullUrl.trim();
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl == null ? null : requestUrl.trim();
    }

    public String getLogDesc() {
        return logDesc;
    }

    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc == null ? null : logDesc.trim();
    }

    public String getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(String spendTime) {
        this.spendTime = spendTime == null ? null : spendTime.trim();
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime == null ? null : beginTime.trim();
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime == null ? null : endTime.trim();
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo == null ? null : clientInfo.trim();
    }

    public String getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo == null ? null : serverInfo.trim();
    }

	@Override
	public String toString() {
		return " [ userId="
				+ userId + ", userName=" + userName + ", className=" + className + ", hsrClassName=" + hsrClassName
				+ ", methodName=" + methodName + ", requestHead=" + requestHead + ", requestFullUrl=" + requestFullUrl
				+ ", requestUrl=" + requestUrl + ", logDesc=" + logDesc + ", spendTime=" + spendTime + ", beginTime="
				+ beginTime + ", endTime=" + endTime + ", clientInfo=" + clientInfo + ", serverInfo=" + serverInfo
				+ "]";
	}
}