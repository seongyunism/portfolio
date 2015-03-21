package seongyunism.model.domain;

public class Comment {

	private int pfNo;
	private int pfPostNo;
	private int pfMemberNo;
	private int pfCommentNo;
	private int pfCommentDepth;
	private int pfCommentMode;
	private long pfCommentDate;
	private int pfCommentTotalLike;
	private String pfCommentMemo;
	
	public int getPfNo() {
		return pfNo;
	}
	public void setPfNo(int pfNo) {
		this.pfNo = pfNo;
	}
	public int getPfPostNo() {
		return pfPostNo;
	}
	public void setPfPostNo(int pfPostNo) {
		this.pfPostNo = pfPostNo;
	}
	public int getPfMemberNo() {
		return pfMemberNo;
	}
	public void setPfMemberNo(int pfMemberNo) {
		this.pfMemberNo = pfMemberNo;
	}
	public int getPfCommentNo() {
		return pfCommentNo;
	}
	public void setPfCommentNo(int pfCommentNo) {
		this.pfCommentNo = pfCommentNo;
	}
	public int getPfCommentDepth() {
		return pfCommentDepth;
	}
	public void setPfCommentDepth(int pfCommentDepth) {
		this.pfCommentDepth = pfCommentDepth;
	}
	public int getPfCommentMode() {
		return pfCommentMode;
	}
	public void setPfCommentMode(int pfCommentMode) {
		this.pfCommentMode = pfCommentMode;
	}
	public long getPfCommentDate() {
		return pfCommentDate;
	}
	public void setPfCommentDate(long pfCommentDate) {
		this.pfCommentDate = pfCommentDate;
	}
	public int getPfCommentTotalLike() {
		return pfCommentTotalLike;
	}
	public void setPfCommentTotalLike(int pfCommentTotalLike) {
		this.pfCommentTotalLike = pfCommentTotalLike;
	}
	public String getPfCommentMemo() {
		return pfCommentMemo;
	}
	public void setPfCommentMemo(String pfCommentMemo) {
		this.pfCommentMemo = pfCommentMemo;
	}
	
	public Comment(int pfNo, int pfPostNo, int pfMemberNo, int pfCommentNo,
			int pfCommentDepth, int pfCommentMode, long pfCommentDate,
			int pfCommentTotalLike, String pfCommentMemo) {
		super();
		this.pfNo = pfNo;
		this.pfPostNo = pfPostNo;
		this.pfMemberNo = pfMemberNo;
		this.pfCommentNo = pfCommentNo;
		this.pfCommentDepth = pfCommentDepth;
		this.pfCommentMode = pfCommentMode;
		this.pfCommentDate = pfCommentDate;
		this.pfCommentTotalLike = pfCommentTotalLike;
		this.pfCommentMemo = pfCommentMemo;
	}
}
