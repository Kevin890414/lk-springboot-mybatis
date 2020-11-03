package com.example.demo.beans;

import java.util.List;

public class Menu {
    public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getOrdered() {
		return ordered;
	}

	public void setOrdered(Integer ordered) {
		this.ordered = ordered;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	private String menuId;

    private String name;

    private String url;

    private String icon;

    private String parentId;

    private Integer ordered;

    private Boolean state;

    private List<Menu> children;

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", name=" + name + ", url=" + url + ", icon=" + icon + ", parentId="
				+ parentId + ", ordered=" + ordered + ", state=" + state + ", children=" + children + "]";
	}

}
