package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yaoshuai
 *
 */
public	class FieldBean {
	private String type;
	private String name;
	public String comment;
	private List<FieldBean> fields;
	public boolean list = false;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void addFieldBean(FieldBean fieldBean) {
		if (fieldBean == null) {
			return;
		}
		if (fields==null) {
			fields = new ArrayList<>();
		}
		this.fields.add(fieldBean);
	}
	
	public void setFields(List<FieldBean> fieldBeans) {
		this.fields = fieldBeans;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean isList) {
		this.list = isList;
	}
	
	public List<FieldBean> getFields() {
		return this.fields;
	}
	public boolean isPrimitive() {
		return !(this.getType().indexOf(".") > 0);
	}
	
	public Map<String, FieldBean> getAllInheritFileBeans() {
		Map<String,FieldBean> _ownerBean = new LinkedHashMap<>(6);
		if (this.isList() || !this.isPrimitive()) {
			if (this.isPrimitive()) {
				_ownerBean.put(this.getType()+this.isList(), this);
			} else {
				_ownerBean.put(this.getType()+this.isList()+this.getName(), this);
			}
		} 
		//如果有子fieled 那么 遍历
		if (this.getFields() != null) {
			for (FieldBean _subFieldBean : this.getFields()) {
				_ownerBean.putAll(_subFieldBean.getAllInheritFileBeans());
			}
		}
		return _ownerBean;
	}

	@Override
	public String toString() {
		return "FieldBean [type=" + type + ", name=" + name + ", comment="
				+ comment + ", fields=" + fields + ", list=" + list + "]";
	}

	public void setId(String string) {
		
	}
	
}
