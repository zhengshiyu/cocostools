package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MessageBean implements Comparable<MessageBean>{
	public String type;
	public String name;
	private short id;
	private String comment;
	private List<FieldBean> fields;
	
	private Map<String, FieldBean> allFields;

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

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<FieldBean> getFields() {
		return fields;
	}

	public void setFields(List<FieldBean> fields) {
		this.fields = fields;
	}
	
	public boolean isGc() {
		return this.getType().startsWith("GC_");
	}
	
	public boolean isCg() {
		return this.getType().startsWith("CG_");
	}

	
	public void patchUp() {
		StringBuilder sb = new StringBuilder(this.getName().toLowerCase());
		int index;
		while((index = sb.indexOf("_")) > -1) {
			sb.setCharAt(index+1, Character.toUpperCase(sb.charAt(index+1)));
			sb.deleteCharAt(index);
		}
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		sb.setCharAt(1, Character.toUpperCase(sb.charAt(1)));

		this.name = sb.toString();
		
		this.allFields = new LinkedHashMap<>();
		if (this.getFields() == null) {
			return;
		}
		for (FieldBean f : this.getFields()) {
			for (Map.Entry<String, FieldBean> _entry : f.getAllInheritFileBeans().entrySet()) {
				if (allFields.containsKey(_entry.getKey())) {
					allFields.remove(_entry.getKey());
				}
				allFields.put(_entry.getKey(), _entry.getValue());
			}
//			allFields.putAll(f.getAllInheritFileBeans());
		}
		
		ArrayList<String> _keyList = new ArrayList<String>();
		_keyList.addAll(allFields.keySet());
		Collections.reverse(_keyList);
		
		LinkedHashMap<String, FieldBean> _descMap = new LinkedHashMap<String, FieldBean>();
		for (String _key : _keyList) {
			FieldBean _bean = allFields.get(_key);
			if (_bean == null) {
				throw new RuntimeException("   ------ " + _key);
			}
			_descMap.put(_key, _bean);
		}
		allFields = _descMap;
	}
	
	public Map<String, FieldBean> getAllFields() {
		return allFields;
	}

	@Override
	public String toString() {
		return "MessageBean [type=" + type + ", name=" + name + ", id=" + id + ", comment=" + comment + ", fields="
				+ fields + "]";
	}

	@Override
	public int compareTo(MessageBean other) {
		return this.getId() > other.getId() ? 1 : -1;
	}

}