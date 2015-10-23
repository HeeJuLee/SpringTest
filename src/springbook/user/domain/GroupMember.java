package springbook.user.domain;

public class GroupMember {
	int id;
	String groupName;
	String objectGuid;
	String member;
	
	public GroupMember() {
		
	}
	public GroupMember(int id, String groupName, String objectGuid, String member) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.objectGuid = objectGuid;
		this.member = member;
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
	public String getObjectGuid() {
		return objectGuid;
	}
	public void setObjectGuid(String objectGuid) {
		this.objectGuid = objectGuid;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	
	
}
