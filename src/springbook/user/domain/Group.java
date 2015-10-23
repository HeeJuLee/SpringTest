package springbook.user.domain;

public class Group {
	int id;
	String groupName;
	String toolName;
	String roleName;
	
	public Group() {
		
	}
	
	public Group(int id, String groupName, String toolName, String roleName) {
		this.id = id;
		this.groupName = groupName;
		this.toolName = toolName;
		this.roleName = roleName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getToolName() {
		return toolName;
	}
	public void setToolName(String toolName) {
		this.toolName = toolName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
