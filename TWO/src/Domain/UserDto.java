package Domain;

public class UserDto {
	Number Member_id;
	String Member_name; 
	String Member_identity;
	String Member_grade;
	String Member_addr;
	String Member_phone;
	
	public UserDto(Number member_id, String member_name, String member_identity, String member_grade,
			String member_addr, String member_phone) {
		super();
		Member_id = member_id;
		Member_name = member_name;
		Member_identity = member_identity;
		Member_grade = member_grade;
		Member_addr = member_addr;
		Member_phone = member_phone;
	}

	public Number getMember_id() {
		return Member_id;
	}

	public void setMember_id(Number member_id) {
		Member_id = member_id;
	}

	public String getMember_name() {
		return Member_name;
	}

	public void setMember_name(String member_name) {
		Member_name = member_name;
	}

	public String getMember_identity() {
		return Member_identity;
	}

	public void setMember_identity(String member_identity) {
		Member_identity = member_identity;
	}

	public String getMember_grade() {
		return Member_grade;
	}

	public void setMember_grade(String member_grade) {
		Member_grade = member_grade;
	}

	public String getMember_addr() {
		return Member_addr;
	}

	public void setMember_addr(String member_addr) {
		Member_addr = member_addr;
	}

	public String getMember_phone() {
		return Member_phone;
	}

	public void setMember_phone(String member_phone) {
		Member_phone = member_phone;
	}

	@Override
	public String toString() {
		return "UserDto [Member_id=" + Member_id + ", Member_name=" + Member_name + ", Member_identity="
				+ Member_identity + ", Member_grade=" + Member_grade + ", Member_addr=" + Member_addr
				+ ", Member_phone=" + Member_phone + "]";
	}

	
}
