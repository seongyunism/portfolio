package seongyunism.model.domain;

public class Guestbook {

	private int pfNo;
	private int pfMemberNo;
	private String pfGuestMemberName;
	private String pfGuestMemberPassword;
	private long pfGuestPostDate;
	private String pfGuestPostMemo;
	
	public int getPfNo() {
		return pfNo;
	}
	public void setPfNo(int pfNo) {
		this.pfNo = pfNo;
	}
	public int getPfMemberNo() {
		return pfMemberNo;
	}
	public void setPfMemberNo(int pfMemberNo) {
		this.pfMemberNo = pfMemberNo;
	}
	public String getPfGuestMemberName() {
		return pfGuestMemberName;
	}
	public void setPfGuestMemberName(String pfGuestMemberName) {
		this.pfGuestMemberName = pfGuestMemberName;
	}
	public String getPfGuestMemberPassword() {
		return pfGuestMemberPassword;
	}
	public void setPfGuestMemberPassword(String pfGuestMemberPassword) {
		this.pfGuestMemberPassword = pfGuestMemberPassword;
	}
	public long getPfGuestPostDate() {
		return pfGuestPostDate;
	}
	public void setPfGuestPostDate(long pfGuestPostDate) {
		this.pfGuestPostDate = pfGuestPostDate;
	}
	public String getPfGuestPostMemo() {
		return pfGuestPostMemo;
	}
	public void setPfGuestPostMemo(String pfGuestPostMemo) {
		this.pfGuestPostMemo = pfGuestPostMemo;
	}
	
	public Guestbook(int pfNo, int pfMemberNo, String pfGuestMemberName,
			String pfGuestMemberPassword, long pfGuestPostDate,
			String pfGuestPostMemo) {
		super();
		this.pfNo = pfNo;
		this.pfMemberNo = pfMemberNo;
		this.pfGuestMemberName = pfGuestMemberName;
		this.pfGuestMemberPassword = pfGuestMemberPassword;
		this.pfGuestPostDate = pfGuestPostDate;
		this.pfGuestPostMemo = pfGuestPostMemo;
	}
}
