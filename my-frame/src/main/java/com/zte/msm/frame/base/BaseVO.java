package com.zte.msm.frame.base;

import java.util.Date;

public abstract class BaseVO {
	public abstract Date getCreateDate();
	public abstract void setCreateDate(Date createDate);
	public abstract Long getId();
	public abstract void setId(Long id);
	public abstract Long getCreateBy();
	public abstract void setCreateBy(Long createBy);
	public abstract Date getLastUpdDate();
	public abstract void setLastUpdDate(Date lastUpdDate);
	public abstract Long getLastUpdBy();
	public abstract void setLastUpdBy(Long lastUpdBy) ;
	public abstract Long getModificationNum();
	public abstract void setModificationNum(Long modificationNum) ;
	public String getEnableFlag(){return "";}
	public void setEnableFlag(String enableFlag){}
}
