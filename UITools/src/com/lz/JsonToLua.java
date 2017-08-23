/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.lz;

import com.lz.generate.BaseGenerate;
import com.lz.generate.ControlGenerate;
import com.lz.generate.LogicGenerate;
import com.lz.generate.ModuleConfigGenerate;
import com.lz.model.ModuleConfig;
import com.lz.util.Constants;
import com.lz.util.ModuleProperty;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonToLua {
	public static List<BaseGenerate> getGenerateList() {
		List generateList = new ArrayList();
		generateList.add(new LogicGenerate());
		generateList.add(new ControlGenerate());
		return generateList;
	}

	public static String getFolderName(String fileFolder) {
		if (fileFolder.endsWith("view")) {
			fileFolder = fileFolder.substring(0, fileFolder.length() - 4);
		}
		String firstChar = fileFolder.substring(0, 1).toLowerCase();
		fileFolder = firstChar + fileFolder.substring(1, fileFolder.length());

		return fileFolder;
	}

	public static String getModuleName(String jsonFileName) {
		String moduleName = jsonFileName.replace("view", "");

		moduleName = moduleName.substring(0, moduleName.indexOf("."));
		String firstChar = moduleName.substring(0, 1).toUpperCase();
		moduleName = firstChar + moduleName.substring(1, moduleName.length());

		return moduleName;
	}

	public static void main(String[] args) throws IOException {
		if (args.length > 0){
			Constants.jsonPath = args[0];
			Constants.outputPath = args[1] +  "script/oyeahgame/" ;
			Constants.moduleConfigPath = args[1] +  "script/mvc/instance/view/" ;
			
		}
		else {
			Constants.jsonPath = "~/work/qunying/share/trunk/UI/qunying/cocosstudio";
		}
		ModuleProperty.loadPanelConfig();

		File cocosFile = new File(Constants.jsonPath);

		Constants.jsonPath = cocosFile.getPath() + File.separator;

		List jsonFileList = new ArrayList();
		List folderNames = new ArrayList();

		loopFile(cocosFile, jsonFileList, folderNames);
		if (jsonFileList.size() != 0)
			generateLua(jsonFileList, folderNames);
	}

	private static void loopFile(File cocosFile, List<File> jsonFileList, List<String> folderNames) {
		if (cocosFile.isFile()) {
			if (((!(cocosFile.getName().endsWith(".json"))) && (!(cocosFile.getName().endsWith(".csd"))))
					|| (!(ModuleProperty.isModule(cocosFile.getName()))))
				return;
			System.out.println("loading json file: " + cocosFile.getPath());
			folderNames.add(ModuleProperty.getPackageName(cocosFile.getName()));
			jsonFileList.add(cocosFile);
		} else if (!(cocosFile.getName().startsWith("."))) {
			File[] fileArray = cocosFile.listFiles();
			int j = fileArray.length;
			for (int i = 0; i < j; ++i) {
				File fileFolder = fileArray[i];
				loopFile(fileFolder, jsonFileList, folderNames);
			}
		}
	}

	private static void generateLua(List<File> jsonFileList, List<String> folderNames) {
		deleteOutFile(new File("script"));

		List generateList = getGenerateList();

		Set modules = new HashSet();
		for (int i = 0; i < jsonFileList.size(); ++i) {
			File jsonFile = (File) jsonFileList.get(i);
			String folderName = (String) folderNames.get(i);
			ModuleConfig module = new ModuleConfig();
			String moduleName = getModuleName(jsonFile.getName());
			for (int j = 0; j < generateList.size(); j++) {
				BaseGenerate generate = (BaseGenerate) generateList.get(j);
				String jsonFilePath = jsonFile.getPath();
				System.out.println(
						"folderName=" + folderName + " ,moduleName=" + moduleName + ", jsonFilePath=" + jsonFilePath);
				generate.init(folderName, moduleName, jsonFilePath);
				if (jsonFile.getName().endsWith(".csd"))
					generate.readCocosCsd(module);
				else {
					generate.readCocosJson(module);
				}
				generate.writeLuaCode();
			}

			module.setModuleName(moduleName);
			module.setFolderName(folderName);

			String path = jsonFile.getParent();
			path = path.replace(Constants.jsonPath, "");
			try {
				path = path.replaceAll("\\\\", "/");
			} catch (Exception localException2) {
			}
			try {
				path = path.replaceAll("\\", "/");
			} catch (Exception localException3) {
			}
			module.setJsonFileName(path + "/" + jsonFile.getName());

			modules.add(module);
		}
		ModuleConfigGenerate moduleConfig = new ModuleConfigGenerate();
		moduleConfig.init(modules);
		moduleConfig.writeLuaCode();
	}

	private static void deleteOutFile(File path) {
		if (!(path.exists())) {
			return;
		}
		if (path.isFile()) {
			path.delete();
			return;
		}
		File[] _files = path.listFiles();
		File[] arrayOfFile1;
		int j = (arrayOfFile1 = _files).length;
		for (int i = 0; i < j; ++i) {
			File file = arrayOfFile1[i];
			deleteOutFile(file);
		}
		path.delete();
	}
}