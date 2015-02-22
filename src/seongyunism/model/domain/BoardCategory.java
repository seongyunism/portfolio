package seongyunism.model.domain;

public class BoardCategory {

	int pfNo;
	String pfCategoryName;
	String pfCategoryNameKor;
	
	public int getPfNo() {
		return pfNo;
	}
	public void setPfNo(int pfNo) {
		this.pfNo = pfNo;
	}
	public String getPfCategoryName() {
		return pfCategoryName;
	}
	public void setPfCategoryName(String pfCategoryName) {
		this.pfCategoryName = pfCategoryName;
	}
	public String getPfCategoryNameKor() {
		return pfCategoryNameKor;
	}
	public void setPfCategoryNameKor(String pfCategoryNameKor) {
		this.pfCategoryNameKor = pfCategoryNameKor;
	}
	
	public BoardCategory(int pfNo, String pfCategoryName, String pfCategoryNameKor) {
		super();
		this.pfNo = pfNo;
		this.pfCategoryName = pfCategoryName;
		this.pfCategoryNameKor = pfCategoryNameKor;
	}
	
}
