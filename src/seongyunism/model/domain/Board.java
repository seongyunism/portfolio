package seongyunism.model.domain;

public class Board {

	int pfNo;
	int pfProjectCategory;
	String pfProjectTitle;
	String pfProjectSubTitle;
	String pfProjectPeriod;
	String pfProjectPurpose;
	String pfProjectCollabo;
	int pfProjectDate;
	String pfProjectLink;
	String pfProjectMovAddr;
	String pfProjectImgAddr01;
	String pfProjectImgAddr02;
	String pfProjectImgAddr03;
	String pfProjectImgAddr04;
	String pfPostThumbnailAddr;
	String pfProjectMemo;
	String pfPostViewMode;
	int pfPostViews;
	int pfPostWrittenDate;
	int pfPostTotalComments;
	int pfPostTotalLike;
	
	public int getPfNo() {
		return pfNo;
	}
	public void setPfNo(int pfNo) {
		this.pfNo = pfNo;
	}
	public int getPfProjectCategory() {
		return pfProjectCategory;
	}
	public void setPfProjectCategory(int pfProjectCategory) {
		this.pfProjectCategory = pfProjectCategory;
	}
	public String getPfProjectTitle() {
		return pfProjectTitle;
	}
	public void setPfProjectTitle(String pfProjectTitle) {
		this.pfProjectTitle = pfProjectTitle;
	}
	public String getPfProjectSubTitle() {
		return pfProjectSubTitle;
	}
	public void setPfProjectSubTitle(String pfProjectSubTitle) {
		this.pfProjectSubTitle = pfProjectSubTitle;
	}
	public String getPfProjectPeriod() {
		return pfProjectPeriod;
	}
	public void setPfProjectPeriod(String pfProjectPeriod) {
		this.pfProjectPeriod = pfProjectPeriod;
	}
	public String getPfProjectPurpose() {
		return pfProjectPurpose;
	}
	public void setPfProjectPurpose(String pfProjectPurpose) {
		this.pfProjectPurpose = pfProjectPurpose;
	}
	public String getPfProjectCollabo() {
		return pfProjectCollabo;
	}
	public void setPfProjectCollabo(String pfProjectCollabo) {
		this.pfProjectCollabo = pfProjectCollabo;
	}
	public int getPfProjectDate() {
		return pfProjectDate;
	}
	public void setPfProjectDate(int pfProjectDate) {
		this.pfProjectDate = pfProjectDate;
	}
	public String getPfProjectLink() {
		return pfProjectLink;
	}
	public void setPfProjectLink(String pfProjectLink) {
		this.pfProjectLink = pfProjectLink;
	}
	public String getPfProjectMovAddr() {
		return pfProjectMovAddr;
	}
	public void setPfProjectMovAddr(String pfProjectMovAddr) {
		this.pfProjectMovAddr = pfProjectMovAddr;
	}
	public String getPfProjectImgAddr01() {
		return pfProjectImgAddr01;
	}
	public void setPfProjectImgAddr01(String pfProjectImgAddr01) {
		this.pfProjectImgAddr01 = pfProjectImgAddr01;
	}
	public String getPfProjectImgAddr02() {
		return pfProjectImgAddr02;
	}
	public void setPfProjectImgAddr02(String pfProjectImgAddr02) {
		this.pfProjectImgAddr02 = pfProjectImgAddr02;
	}
	public String getPfProjectImgAddr03() {
		return pfProjectImgAddr03;
	}
	public void setPfProjectImgAddr03(String pfProjectImgAddr03) {
		this.pfProjectImgAddr03 = pfProjectImgAddr03;
	}
	public String getPfProjectImgAddr04() {
		return pfProjectImgAddr04;
	}
	public void setPfProjectImgAddr04(String pfProjectImgAddr04) {
		this.pfProjectImgAddr04 = pfProjectImgAddr04;
	}
	public String getPfPostThumbnailAddr() {
		return pfPostThumbnailAddr;
	}
	public void setPfPostThumbnailAddr(String pfPostThumbnailAddr) {
		this.pfPostThumbnailAddr = pfPostThumbnailAddr;
	}
	public String getPfProjectMemo() {
		return pfProjectMemo;
	}
	public void setPfProjectMemo(String pfProjectMemo) {
		this.pfProjectMemo = pfProjectMemo;
	}
	public String getPfPostViewMode() {
		return pfPostViewMode;
	}
	public void setPfPostViewMode(String pfPostViewMode) {
		this.pfPostViewMode = pfPostViewMode;
	}
	public int getPfPostViews() {
		return pfPostViews;
	}
	public void setPfPostViews(int pfPostViews) {
		this.pfPostViews = pfPostViews;
	}
	public int getPfPostWrittenDate() {
		return pfPostWrittenDate;
	}
	public void setPfPostWrittenDate(int pfPostWrittenDate) {
		this.pfPostWrittenDate = pfPostWrittenDate;
	}
	public int getPfPostTotalComments() {
		return pfPostTotalComments;
	}
	public void setPfPostTotalComments(int pfPostTotalComments) {
		this.pfPostTotalComments = pfPostTotalComments;
	}
	public int getPfPostTotalLike() {
		return pfPostTotalLike;
	}
	public void setPfPostTotalLike(int pfPostTotalLike) {
		this.pfPostTotalLike = pfPostTotalLike;
	}
	
	public Board(int pfNo, int pfProjectCategory, String pfProjectTitle,
			String pfProjectSubTitle, String pfProjectPeriod,
			String pfProjectPurpose, String pfProjectCollabo,
			int pfProjectDate, String pfProjectLink, String pfProjectMovAddr,
			String pfProjectImgAddr01, String pfProjectImgAddr02,
			String pfProjectImgAddr03, String pfProjectImgAddr04,
			String pfPostThumbnailAddr, String pfProjectMemo,
			String pfPostViewMode, int pfPostViews, int pfPostWrittenDate,
			int pfPostTotalComments, int pfPostTotalLike) {
		super();
		this.pfNo = pfNo;
		this.pfProjectCategory = pfProjectCategory;
		this.pfProjectTitle = pfProjectTitle;
		this.pfProjectSubTitle = pfProjectSubTitle;
		this.pfProjectPeriod = pfProjectPeriod;
		this.pfProjectPurpose = pfProjectPurpose;
		this.pfProjectCollabo = pfProjectCollabo;
		this.pfProjectDate = pfProjectDate;
		this.pfProjectLink = pfProjectLink;
		this.pfProjectMovAddr = pfProjectMovAddr;
		this.pfProjectImgAddr01 = pfProjectImgAddr01;
		this.pfProjectImgAddr02 = pfProjectImgAddr02;
		this.pfProjectImgAddr03 = pfProjectImgAddr03;
		this.pfProjectImgAddr04 = pfProjectImgAddr04;
		this.pfPostThumbnailAddr = pfPostThumbnailAddr;
		this.pfProjectMemo = pfProjectMemo;
		this.pfPostViewMode = pfPostViewMode;
		this.pfPostViews = pfPostViews;
		this.pfPostWrittenDate = pfPostWrittenDate;
		this.pfPostTotalComments = pfPostTotalComments;
		this.pfPostTotalLike = pfPostTotalLike;
	}
	
}
