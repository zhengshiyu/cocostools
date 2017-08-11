package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
	public static String fileInDir;
	public static String fileOutDir;

	public static String getMd5ByFile(File file) throws FileNotFoundException {
		String value = null;
		FileInputStream in = new FileInputStream(file);
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(
					FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	public static void SaveToTxt(String content) {
		FileWriter fw = null;
		try {
			File f = new File(fileOutDir + "/md5zh_CN.txt");
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.print(content + "\r");
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void walkFiles(String filePath) throws Exception {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				walkFiles(file.getAbsolutePath());
			} else {
				if(file.getAbsolutePath().endsWith(".DS_Store") || file.getAbsolutePath().endsWith(".meta") || file.getName().equals("seting.xml"))
				{
					
				}else
				{
					String fileMd5 = getMd5ByFile(file);
					long fileSize = file.length();
					String fileName = file.getAbsolutePath();
					int index = file.getAbsolutePath().indexOf("1.1.1");
					fileName = fileName.substring(index + 6,fileName.length());
					System.out.println(file.getParent());
					SaveToTxt(fileName + "@" + fileMd5 + "_" + fileSize);
					System.out.println(fileName + "@" + fileMd5 + "_" + fileSize);
				}
				
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		System.out.println("fileInDir---->" + fileInDir);
		System.out.println("fileOutDir---->" + fileOutDir);
		if (args.length == 2) {
			fileInDir = args[0];
			fileOutDir = args[1];
			FileWriter fw = null;
			try {
				File f = new File(fileOutDir + "/md5zh_CN.txt");
				if(f.exists())
				{
					f.delete();
				}
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			walkFiles(fileInDir);
		} else {
			System.out.println("filein and fileout dir not setting");
		}

	}

	private static void zip(String zipFileName, File inputFile)
			throws Exception {
		
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		BufferedOutputStream bo = new BufferedOutputStream(out);
		//compressFile(inputFile,out,fileOutDir);
	}

	
	private static void compressFile(File file, ZipOutputStream zos,

	String baseDir) {

		if (!file.exists())

			return;

		try {

			BufferedInputStream bis = new BufferedInputStream(

			new FileInputStream(file));

			ZipEntry entry = new ZipEntry(baseDir + file.getName());

			zos.putNextEntry(entry);

			int count;

			byte[] buf = new byte[4096];

			while ((count = bis.read(buf)) != -1) {

				zos.write(buf, 0, count);

			}

			bis.close();

		} catch (Exception e) {

			// TODO: handle exception

		}

	}
}
