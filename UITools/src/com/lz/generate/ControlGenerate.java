/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.lz.generate;

import com.lz.model.GuiModel;
import com.lz.util.Constants;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlGenerate extends BaseGenerate {
	public void init(String folderName, String moduleName, String inputFile) {
		super.init(folderName, moduleName, inputFile);
		String outputPath = Constants.outputPath + this.folderName + "/control/";
		this.outputFile = outputPath + this.moduleName + "Control.lua";

		File output = new File(outputPath);
		if (!(output.exists()))
			output.mkdirs();
	}

	public void writeLuaCode() {
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding(Constants.FORMAT);
			configuration.setDirectoryForTemplateLoading(new File(Constants.templatePath));
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			Template template = configuration.getTemplate(Constants.templateControl);
			Map rootMap = new HashMap();

			rootMap.put("folderName", this.folderName);
			rootMap.put("moduleName", this.moduleName);
			rootMap.put("guiModelList", this.guiModelList);
			List reversalguiModelList = new ArrayList();
			for (GuiModel guiModel : this.guiModelList) {
				reversalguiModelList.add(0, guiModel);
			}
			rootMap.put("reversalguiModelList", reversalguiModelList);
			Writer writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(this.outputFile), Constants.FORMAT));
			template.process(rootMap, writer);
			writer.flush();
			writer.close();
			System.out.println("generate control: " + this.outputFile + "\n");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}