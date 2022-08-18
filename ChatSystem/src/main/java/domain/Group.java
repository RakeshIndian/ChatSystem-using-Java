package domain;

public class Group {
	private String gName;
	private String gCreator;
	private String gIp;
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public String getgCreator() {
		return gCreator;
	}
	public void setgCreator(String gCreator) {
		this.gCreator = gCreator;
	}
	public String getgIp() {
		return gIp;
	}
	public void setgIp(String gIp) {
		this.gIp = gIp;
	}
	public Group(String gName, String gCreator, String gIp) {
		super();
		this.gName = gName;
		this.gCreator = gCreator;
		this.gIp = gIp;
	}
	public Group() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Group [gName=" + gName + ", gCreator=" + gCreator + ", gIp=" + gIp + "]";
	}
	
	}
	

