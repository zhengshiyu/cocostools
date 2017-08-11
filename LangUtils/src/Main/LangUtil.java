package Main;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LangUtil {
	
	public static LineModel checkLines(String con,File file,Map<String, String> key_value,Map<String, String> value_key) {
		
		String content = con;
		int index = 0;
		int indexone = 0;
		int indexTwo = 0;
		int counter = 1;
		
		int sigleindex = 0;   //单行注释
		int mutilsindex = 0;  //多行注释
		int mutilsendindex = 0;  //多行注释结束
		
		LineModel lineModel = new LineModel();
		ArrayList<LangModel> langModels = new ArrayList<>();	
		LockState.siglelinelock = false; 
		
		while (true) {
			int indexTag = content.indexOf("\'", index);			
			if (indexTag == -1) {
				indexTag = content.indexOf("\"", index);
			}
			
			sigleindex = content.indexOf("--");	//取得当前单行注释所在索引位置 并判断在""之外为有效注释
			mutilsindex = content.indexOf("--[[");//取得当前多行注释所在索引位置 并判断在""之外为有效注释
			mutilsendindex = content.indexOf("]]");
			
			if (indexTag == -1) {
				if(mutilsindex != -1)
				{
					LockState.mutilelinelock = true;
				}
				if(mutilsendindex != -1)
				{
					LockState.mutilelinelock = false;
				}
			}
			
			if (indexTag != -1) {
				if (counter % 2 == 1) {
					indexone = indexTag;
				} else {
					if(sigleindex != -1)
					{
						if(sigleindex < indexone) //单行有效注释
						{
							LockState.siglelinelock = true;
						}
					}
					
					if(mutilsindex != -1)
					{	
						if(mutilsindex < indexone) //多行有效注释结束
						{
							LockState.mutilelinelock = true;
						}
					}
					if(mutilsendindex != -1)
					{	
						if(mutilsendindex < indexone) //多行有效注释结束
						{
							LockState.mutilelinelock = false;
						}
					}
					
					indexTwo = indexTag;
					LangModel model = new LangModel();
					int i = 0;
					String key = file.getName().substring(0,file.getName().lastIndexOf("."));
					
					//检查已经存在的value
					
					if(value_key.get(content.substring(indexone, indexTwo + 1))!=null)
					{	key = value_key.get(content.substring(indexone, indexTwo + 1));
						
					}else{
						while(true)
						{	if(key_value.get(key + i) == null)
							{	
								model.setIsnew(true);
								key = key + i;
								break;
							}
							i ++;
						}
					}
					model.setContentkey(key);
					model.setContentValue(content.substring(indexone, indexTwo + 1));
					if (isContainChinese(content.substring(indexone, indexTwo + 1))) {
						if(!LockState.siglelinelock && !LockState.mutilelinelock)
						{
							langModels.add(model);
						}
					}

				}
				index = indexTag + 1;
				counter++;
			} else {
				break;
			}
		}

		lineModel.setLangModels(langModels);

		String result = content;
		for (int i = 0; i < lineModel.getLangModels().size(); i++) {
			String key = lineModel.getLangModels().get(i).getContentkey();
			String value = lineModel.getLangModels().get(i).getContentValue();
			result = content.replace(value, "langUtils.getText(\"" + key + "\")");
			content = result; 
			lineModel.setContainChinese(true);
		}
		lineModel.setContentOrg(content);
		lineModel.setContentNew(result);
		
//		if(true)
//		{
//			lineModel.setContentOrg(content);
//			lineModel.setContentNew(content);
//			lineModel.setContainChinese(false);
//			lineModel.setLangModels(new ArrayList<LangModel>());
//		}

		return lineModel;
	}

	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}
}
