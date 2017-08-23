/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.lz.generate;

import com.lz.model.ModuleConfig;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModuleConfigGenerate {
	private Set<ModuleConfig> modules;
	protected String outputFile;

	public void init(Set<ModuleConfig> modules) {
		String outputPath = Constants.moduleConfigPath;
		this.outputFile = outputPath + "ModuleConfigAuto.lua";
		this.modules = modules;

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
			Template template = configuration.getTemplate(Constants.templateModuleConfog);
			Map rootMap = new HashMap();

			rootMap.put("modules", this.modules);
			Writer writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(this.outputFile), Constants.FORMAT));
			template.process(rootMap, writer);
			writer.flush();
			writer.close();
			System.out.println("generate ModuleConfig: " + this.outputFile + "\n");
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}