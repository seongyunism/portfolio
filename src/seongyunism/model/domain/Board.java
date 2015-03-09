package seongyunism.model.domain;

public class Board {

	int pfNo; // 고유 번호
	int pfProjectCategory; // 프로젝트 분류
	String pfProjectTitle; // 프로젝트 타이틀
	String pfProjectSubTitle; // 프로젝트 서브타이틀
	String pfProjectPeriod; // 프로젝트 기간
	String pfProjectPurpose; // 프로젝트 목적
	String pfProjectCollabo; // 프로젝트 협업자
	String pfProjectLanguage; // 프로젝트 작성 언어
	int pfProjectDate; // 프로젝트 배포일자
	String pfProjectLink; // 관련 링크
	String pfProjectMovAddr; // 프로젝트 소개 영상 주소
	String pfProjectMovPreview; // 프로젝트 소개 영상 프리뷰 이미지 주소
	String pfProjectImgAddr01; // 프로젝트 소개 이미지 주소1
	String pfProjectImgAddr02; // 프로젝트 소개 이미지 주소2
	String pfProjectImgAddr03; // 프로젝트 소개 이미지 주소3
	String pfProjectImgAddr04; // 프로젝트 소개 이미지 주소4
	String pfPostThumbnailAddr; // 포스트 썸네일 이미지 주소
	String pfProjectMemo; // 프로젝트 설명
	int pfPostViewMode; // 화면 출력 여부 (0:일반, 1:화면에 보이는 비밀글, 2: 화면에 안보이는 비밀글)
	int pfPostViews; // 포스트 클릭 횟수
	int pfPostWrittenDate; // 포스트 작성 날짜
	int pfPostTotalComments; // 포스트 댓글 수
	int pfPostTotalLike; // 포스트 좋아요 수
	
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
	public String getPfProjectLanguage() {
		return pfProjectLanguage;
	}
	public void setPfProjectLanguage(String pfProjectLanguage) {
		this.pfProjectLanguage = pfProjectLanguage;
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
	public String getPfProjectMovPreview() {
		return pfProjectMovPreview;
	}
	public void setPfProjectMovPreview(String pfProjectMovPreview) {
		this.pfProjectMovPreview = pfProjectMovPreview;
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
	public int getPfPostViewMode() {
		return pfPostViewMode;
	}
	public void setPfPostViewMode(int pfPostViewMode) {
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
			String pfProjectLanguage, int pfProjectDate, String pfProjectLink,
			String pfProjectMovAddr, String pfProjectMovPreview,
			String pfProjectImgAddr01, String pfProjectImgAddr02,
			String pfProjectImgAddr03, String pfProjectImgAddr04,
			String pfPostThumbnailAddr, String pfProjectMemo,
			int pfPostViewMode, int pfPostViews, int pfPostWrittenDate,
			int pfPostTotalComments, int pfPostTotalLike) {
		super();
		this.pfNo = pfNo;
		this.pfProjectCategory = pfProjectCategory;
		this.pfProjectTitle = pfProjectTitle;
		this.pfProjectSubTitle = pfProjectSubTitle;
		this.pfProjectPeriod = pfProjectPeriod;
		this.pfProjectPurpose = pfProjectPurpose;
		this.pfProjectCollabo = pfProjectCollabo;
		this.pfProjectLanguage = pfProjectLanguage;
		this.pfProjectDate = pfProjectDate;
		this.pfProjectLink = pfProjectLink;
		this.pfProjectMovAddr = pfProjectMovAddr;
		this.pfProjectMovPreview = pfProjectMovPreview;
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
