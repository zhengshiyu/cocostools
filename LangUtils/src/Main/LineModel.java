package Main;

import java.util.ArrayList;

public class LineModel {
	private String contentOrg;
	private String contentNew;
	private boolean isContainChinese = false;
	public boolean isContainChinese() {
		return isContainChinese;
	}
	public void setContainChinese(boolean isContainChinese) {
		this.isContainChinese = isContainChinese;
	}
	private ArrayList<LangModel> langModels;
	public String getContentOrg() {
		return contentOrg;
	}
	public void setContentOrg(String contentOrg) {
		this.contentOrg = contentOrg;
	}
	public String getContentNew() {
		return contentNew;
	}
	public void setContentNew(String contentNew) {
		this.contentNew = contentNew;
	}
	public ArrayList<LangModel> getLangModels() {
		return langModels;
	}
	public void setLangModels(ArrayList<LangModel> langModels) {
		this.langModels = langModels;
	}
	
}
