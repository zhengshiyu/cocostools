/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.lz.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileUtil {
	public static String readFileToStr(String strFileName) {
		StringBuffer buf = null;
		BufferedReader breader = null;
		try {
			breader = new BufferedReader(
					new InputStreamReader(new FileInputStream(strFileName), Charset.forName("utf-8")));

			buf = new StringBuffer();
			while (breader.ready()) {
				buf.append((char) breader.read());
			}
			breader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

	public static boolean writeFile(String filePath, String content) {
		try {
			FileWriter fw = new FileWriter(filePath);
			fw.write(content, 0, content.length());
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}