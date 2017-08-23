/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.lz.model;

public class GuiModel {
	private String parentName = "self";
	private String name;
	private String classname;
	private int tag;
	private boolean hasName;
	private String touchEnable = "False";
	private String controlClass;

	public boolean isButton() {
		boolean isButton = this.classname.equals("Button");
		return isButton;
	}

	public boolean isLabel() {
		boolean isLabel = this.classname.equals("Text");
		if (!(isLabel)) {
			isLabel = this.classname.equals("TextField");
		}
		return isLabel;
	}

	public boolean isTouchEnable() {
		return this.touchEnable.equals("True");
	}

	public String getFunctionName() {
		String firstChar = this.name.substring(0, 1).toUpperCase();
		String functionName = firstChar + this.name.substring(1, this.name.length());
		return functionName;
	}

	public boolean isHasName() {
		return this.hasName;
	}

	public void setHasName(boolean hasName) {
		this.hasName = hasName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getTouchEnable() {
		return this.touchEnable;
	}

	public void setTouchEnable(String touchEnable) {
		this.touchEnable = touchEnable;
	}

	public int getTag() {
		return this.tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getControlClass() {
		return this.controlClass;
	}

	public void setControlClass(String classname) {
		String[] classnames = classname.split("/");
		String moduleName = classnames[(classnames.length - 1)];
		String firstChar = moduleName.substring(0, 1).toUpperCase();
		moduleName = firstChar + moduleName.substring(1, moduleName.length());
		this.controlClass = moduleName;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}