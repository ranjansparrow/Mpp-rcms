package model.pojo;

public class MemberPojo {
	int member_id;
	int database_id;
	String fullname;
	String address;
	int member_type;
	int fee_package;
	
	public MemberPojo(int member_id, int database_id, String fullname, String address, int member_type,
			int fee_package) {
		this.member_id = member_id;
		this.database_id = database_id;
		this.fullname = fullname;
		this.address = address;
		this.member_type = member_type;
		this.fee_package = fee_package;
	}
	public MemberPojo(int member_id, String fullname, String address, int member_type, int fee_package) {
		this.member_id = member_id;
		this.fullname = fullname;
		this.address = address;
		this.member_type = member_type;
		this.fee_package = fee_package;
	}
	public MemberPojo(String fullname, String address, int member_type, int fee_package) {
		this.fullname = fullname;
		this.address = address;
		this.member_type = member_type;
		this.fee_package = fee_package;
	}
	
	public int getDatabase_id() {
		return database_id;
	}
	public void setDatabase_id(int database_id) {
		this.database_id = database_id;
	}
	public int getMember_id() {
		return member_id;
	}
	public String getFullname() {
		return fullname;
	}
	public String getAddress() {
		return address;
	}
	public int getMember_type() {
		return member_type;
	}
	public int getFee_package() {
		return fee_package;
	}
	
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setMember_type(int member_type) {
		this.member_type = member_type;
	}
	public void setFee_package(int fee_package) {
		this.fee_package = fee_package;
	}
	@Override
	public String toString() {
		return "MemberPojo [member_id=" + member_id + ", fullname=" + fullname + ", address=" + address
				+ ", member_type=" + member_type + ", fee_package=" + fee_package + "]";
	}
	
	
	
}
