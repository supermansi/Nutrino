package nutrino.model;

public class Search {

	protected int searchId;
	protected String userName;
	protected String keyword;
	public Search(int searchId, String userName, String keyword)
	{
		this.searchId = searchId;
		this.userName = userName;
		this.keyword = keyword;
	}
	public int getSearchId() {
		return searchId;
	}
	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
