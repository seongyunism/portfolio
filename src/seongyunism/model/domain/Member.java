package seongyunism.model.domain;

public class Member {

	private int pfNo;
	private String pfMemberEmail;
	private String pfMemberPassword;
	private String pfMemberName;
	private int pfMemberConnectedSNS;
	private String pfMemberFacebook;
	
	public int getPfNo() {
		return pfNo;
	}
	public void setPfNo(int pfNo) {
		this.pfNo = pfNo;
	}
	public String getPfMemberEmail() {
		return pfMemberEmail;
	}
	public void setPfMemberEmail(String pfMemberEmail) {
		this.pfMemberEmail = pfMemberEmail;
	}
	public String getPfMemberPassword() {
		return pfMemberPassword;
	}
	public void setPfMemberPassword(String pfMemberPassword) {
		this.pfMemberPassword = pfMemberPassword;
	}
	public String getPfMemberName() {
		return pfMemberName;
	}
	public void setPfMemberName(String pfMemberName) {
		this.pfMemberName = pfMemberName;
	}
	public int getPfMemberConnectedSNS() {
		return pfMemberConnectedSNS;
	}
	public void setPfMemberConnectedSNS(int pfMemberConnectedSNS) {
		this.pfMemberConnectedSNS = pfMemberConnectedSNS;
	}
	public String getPfMemberFacebook() {
		return pfMemberFacebook;
	}
	public void setPfMemberFacebook(String pfMemberFacebook) {
		this.pfMemberFacebook = pfMemberFacebook;
	}
	
	public Member(int pfNo, String pfMemberEmail, String pfMemberPassword,
			String pfMemberName, int pfMemberConnectedSNS,
			String pfMemberFacebook) {
		super();
		this.pfNo = pfNo;
		this.pfMemberEmail = pfMemberEmail;
		this.pfMemberPassword = pfMemberPassword;
		this.pfMemberName = pfMemberName;
		this.pfMemberConnectedSNS = pfMemberConnectedSNS;
		this.pfMemberFacebook = pfMemberFacebook;
	}
}
