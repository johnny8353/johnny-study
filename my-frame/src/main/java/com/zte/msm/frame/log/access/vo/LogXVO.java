package com.zte.msm.frame.log.access.vo;

import java.util.Date;

import com.zte.msm.frame.base.BaseVO;

public class LogXVO extends BaseVO{
	private Long parentId;

	private Long id;

	private Date createDate;

	private Long createBy;

	private Date lastUpdDate;

	private Long lastUpdBy;

	private Long modificationNum;

	private String table;
	private String exception;

	private String inputParams;

	private String outputParams;
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getId() {
		return id;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
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

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception == null ? null : exception.trim();
	}

	public String getInputParams() {
		return inputParams;
	}

	public void setInputParams(String inputParams) {
		this.inputParams = inputParams == null ? null : inputParams.trim();
	}

	public String getOutputParams() {
		return outputParams;
	}

	public void setOutputParams(String outputParams) {
		this.outputParams = outputParams == null ? null : outputParams.trim();
	}

	@Override
	public String toString() {
		return " [id=" + id + ", exception=" + exception + ", inputParams=" + inputParams + ", outputParams="
				+ outputParams + "]";
	}

	public static void main(String[] args) {
		LogXVO vo = new LogXVO();
		System.out.println(vo.toString());
	}

}