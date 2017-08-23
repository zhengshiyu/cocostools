/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.lz.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class ModuleProperty {
	private static List<String> configList;
	private static Map<String, String> configMap;

	public static void loadPanelConfig() throws IOException {
		configList = new ArrayList();
		configMap = new HashMap();

		Properties prop = new Properties();
		InputStream in = null;
		Reader re = null;
		try {
			in = new FileInputStream(Constants.moduleProperties);
			re = new InputStreamReader(in, "UTF-8");
			prop.load(re);
			addModule(prop);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (re != null) {
				re.close();
				re = null;
			}
			if (in != null) {
				in.close();
				in = null;
			}
		}
	}

	private static void addModule(Properties prop) {
		Set entrySet = prop.entrySet();
		for (Object entry1 : entrySet) {
			Map.Entry entry = (Map.Entry)entry1;
			configList.add(String.valueOf(entry.getValue()).trim());

			String key = String.valueOf(entry.getKey());
			int endIndex = key.lastIndexOf("_");
			String moduleFolderName = key.substring(0, endIndex);
			configMap.put(String.valueOf(entry.getValue()).trim(), moduleFolderName);
		}
	}

	public static boolean isModule(String jsonName) {
		return configList.contains(jsonName);
	}

	public static String getPackageName(String jsonName) {
		return ((String) configMap.get(jsonName));
	}
}