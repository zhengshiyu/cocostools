package main;

/**
 * 
 * @author yaoshuai
 *
 */
public class MessageTypeBean implements Comparable<MessageTypeBean> {
	public String type;
	public String name;
	private short id;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	@Override
	public int compareTo(MessageTypeBean o) {
		return this.id < o.id ? - 1 : 1;
	}

	public void patchUpName() {
		StringBuilder sb = new StringBuilder(this.getType().toLowerCase());
		int index;
		while ((index = sb.indexOf("_")) > -1) {
			sb.setCharAt(index + 1, Character.toUpperCase(sb.charAt(index + 1)));
			sb.deleteCharAt(index);
		}
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		sb.setCharAt(1, Character.toUpperCase(sb.charAt(1)));

		this.name = sb.toString();
	}
	
	public boolean isGc() {
		return this.getType().startsWith("GC_");
	}
}
