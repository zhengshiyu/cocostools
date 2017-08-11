package Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Main {
	public static boolean isfirstBuild = false;
	public static Map<String, String> key_value = new HashMap<>();
	public static Map<String, String> value_key = new HashMap<>();

	public static Map<String, String> key_value_temp = new HashMap<>();
	public static Map<String, String> value_key_temp = new HashMap<>();

	static void walkFiles(String filePath) throws Exception {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				walkFiles(file.getAbsolutePath());
			} else {
				if(file.getAbsolutePath().endsWith(".lua"))
				ReadFile(file.getAbsolutePath());
			}
		}

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ReadExcel();
		walkFiles("/Users/douzi/Desktop/Lua/test");
		System.out.println("执行完毕");
	}

	private static void ReadFile(String filename) throws FileNotFoundException, IOException, Exception {
		// read file content from file
		StringBuffer sb = new StringBuffer("");
		File file = new File(filename);
		FileReader reader = new FileReader(filename);
		BufferedReader br = new BufferedReader(reader);

		String str = null;
		int linenum = 0;
		while ((str = br.readLine()) != null) {
			boolean locked = false;
			str = str.replaceAll("\r|\n", "");
			LineModel model = LangUtil.checkLines(str, file, key_value, value_key);
			for (int i = 0; i < model.getLangModels().size(); i++) {
				if (model.getLangModels().get(i).isIsnew()) {
					key_value.put(model.getLangModels().get(i).getContentkey(),
							model.getLangModels().get(i).getContentValue());
					value_key.put(model.getLangModels().get(i).getContentValue(),
							model.getLangModels().get(i).getContentkey());

					key_value_temp.put(model.getLangModels().get(i).getContentkey(),
							model.getLangModels().get(i).getContentValue());
					value_key_temp.put(model.getLangModels().get(i).getContentValue(),
							model.getLangModels().get(i).getContentkey());

				}
			}
			sb.append(model.getContentNew() + "\n");
			linenum = linenum + 1;
		}
		for (Map.Entry<String, String> entry : key_value_temp.entrySet()) {
			saveToExcel(entry.getKey(), entry.getValue());
			
		}
		
		key_value_temp.clear();
		value_key_temp.clear();

		br.close();
		reader.close();
		

		 //write string to file
		FileWriter writer = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(writer);
		bw.write(sb.toString());
		bw.close();
		writer.close();
	}

	public static void ReadExcel() throws Exception {
		File file = new File("/Users/douzi/Desktop/Lang.xls");

		if (isfirstBuild || file.exists() == false) {
			return;
		}
		InputStream is = new FileInputStream(file.getAbsolutePath());
		Workbook wb = Workbook.getWorkbook(is);
		Sheet sheet = wb.getSheet("Lang");
		for (int i = 0; i < sheet.getRows(); i++) {
			String key = sheet.getCell(0, i).getContents();
			String value = sheet.getCell(1, i).getContents();
			key_value.put(key, value);
			value_key.put(value, key);
		}

		wb.close();
		is.close();
	}

	public static void saveToExcel(String key, String value) throws Exception {
		File file = new File("/Users/douzi/Desktop/Lang.xls");
		if (!file.exists()) {
			isfirstBuild = true;
			file.createNewFile();
		}
		Workbook wb = null;
		WritableWorkbook workbook = null;
		if (isfirstBuild) {
			workbook = Workbook.createWorkbook(file);
			isfirstBuild = false;
		} else {
			wb = Workbook.getWorkbook(file);
			workbook = Workbook.createWorkbook(file, wb);
		}
		WritableSheet sheet = workbook.getSheet("Lang");
		if (sheet == null) { // 创建新的一页
			sheet = workbook.createSheet("Lang", 0);
		}
		Label _key = new Label(0, sheet.getRows(), key);
		Label _value = new Label(1, sheet.getRows(), value);
		sheet.addCell(_key);
		sheet.addCell(_value);

		System.out.println("new value |  " + key + "--------------" + value);

		workbook.write();
		workbook.close();
		if (wb != null) {
			wb.close();
		}

	}
}
