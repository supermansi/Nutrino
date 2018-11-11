package nutrino.model;

public class Favorites {

	protected int favoriteID;
	protected String username;
	protected int recipeID;

	public Favorites(int favoriteID, String username, int recipeID)
	{
		this.favoriteID = favoriteID;
		this.username = username;
		this.recipeID = recipeID;
	}

	public int getFavoriteID() {
		return favoriteID;
	}

	public void setFavoriteID(int favoriteID) {
		this.favoriteID = favoriteID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRecipeID() {
		return recipeID;
	}

	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}
	
}
