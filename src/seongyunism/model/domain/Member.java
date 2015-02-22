package seongyunism.model.domain;

public class Member {

	int pfNo;
	String pfMemberName;
	String pfMemberPassword;
	
	public int getPfNo() {
		return pfNo;
	}
	public void setPfNo(int pfNo) {
		this.pfNo = pfNo;
	}
	public String getPfMemberName() {
		return pfMemberName;
	}
	public void setPfMemberName(String pfMemberName) {
		this.pfMemberName = pfMemberName;
	}
	public String getPfMemberPassword() {
		return pfMemberPassword;
	}
	public void setPfMemberPassword(String pfMemberPassword) {
		this.pfMemberPassword = pfMemberPassword;
	}
	
	@Override
	public String toString() {
		return "Member [pfNo=" + pfNo + ", pfMemberName=" + pfMemberName
				+ ", pfMemberPassword=" + pfMemberPassword + "]";
	}
	
	
	
}
