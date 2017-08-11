package Main;

public class LangModel {
	private String contentkey;
	private String contentValue;
	private boolean isnew = false;
	
	public boolean isIsnew() {
		return isnew;
	}
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}
	public String getContentkey() {
		return contentkey;
	}
	public void setContentkey(String contentkey) {
		this.contentkey = contentkey;
	}
	public String getContentValue() {
		return contentValue;
	}
	public void setContentValue(String contentValue) {
		this.contentValue = contentValue;
	}
	
}
