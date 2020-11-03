package com.example.demo.beans;

import java.util.List;

public class Org {
	private String orgId;
	private String orgName;
	private String parentOrgId;
	private String parentOrgName;
	private String orgLevel;
	private String orgLevelName;
	public String getOrgLevelName() {
		return orgLevelName;
	}
	public void setOrgLevelName(String orgLevelName) {
		this.orgLevelName = orgLevelName;
	}


	private String treeId;
	private String numId;
	private Integer numberOfPlies;
	public Integer getNumberOfPlies() {
		return numberOfPlies;
	}
	public void setNumberOfPlies(Integer numberOfPlies) {
		this.numberOfPlies = numberOfPlies;
	}


	private List<Org> childrens;
	
	public List<Org> getChildrens() {
		return childrens;
	}
	public void setChildrens(List<Org> childrens) {
		this.childrens = childrens;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public String getParentOrgName() {
		return parentOrgName;
	}
	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getTreeId() {
		return treeId;
	}
	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
	public String getNumId() {
		return numId;
	}
	public void setNumId(String numId) {
		this.numId = numId;
	}
	
	
	@Override
	public String toString() {
		return "Org [orgId=" + orgId + ", orgName=" + orgName + ", parentOrgId=" + parentOrgId + ", parentOrgName="
				+ parentOrgName + ", orgLevel=" + orgLevel + ", orgLevelName=" + orgLevelName + ", treeId=" + treeId
				+ ", numId=" + numId + ", numberOfPlies=" + numberOfPlies + ", childrens=" + childrens + "]";
	}
	

}
