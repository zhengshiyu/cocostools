/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.lz.model;

import java.util.Collection;
import java.util.HashMap;

public class ModuleConfig {
	private String moduleName;
	private String folderName;
	private String realFolderName;
	private String jsonFileName;
	private String plist = "";
	private HashMap<String, String> plists = new HashMap();

	public void setPlist(String plist) {
		if ((!("Common".equals(plist))) && (!("global".equals(plist))) && (!(this.plists.containsKey(plist))))
			this.plists.put(plist, plist);
	}

	public String getPlist() {
		Collection list = this.plists.values();
		this.plist = "";

		for (Object string : list) {
			if ("".equals(this.plist))
				this.plist = "\"" + string + "\"";
			else {
				this.plist = this.plist + ",\"" + string + "\"";
			}
		}

		return this.plist;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getFolderName() {
		return this.folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getRealFolderName() {
		return this.realFolderName;
	}

	public void setRealFolderName(String realFolderName) {
		this.realFolderName = realFolderName;
	}

	public String getJsonFileName() {
		return this.jsonFileName;
	}

	public void setJsonFileName(String jsonFileName) {
		this.jsonFileName = jsonFileName.replace("csd", "csb");
	}
}