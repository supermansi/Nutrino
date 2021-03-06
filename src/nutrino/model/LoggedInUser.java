package nutrino.model;

public class LoggedInUser extends Users {
	
	protected float height;
	protected float weight;
	protected DietLabels diet;
	
	public enum DietLabels {
		Balanced, AlcoholFree, CeleryFree, CrustaceanFree, DairyFree,EggFree,FishFree,GlutenFree,KidneyFriendly,
		Kosher,LowPotassium,LupineFree,MustardFree,NoOilAdded,NoSugar,Paleo,PeanutFree,Pescatarian,PorkFree,RedMeatFree,
		SesameFree,ShellFishFree,SoyFree,SugarConscious,TeaNutsFree,Vegan,Vegetarian,WheatFree
	}
	
	public LoggedInUser(String username, float height, float weight, DietLabels diet) {
		super(username);
		this.height = height;
		this.weight = weight;
		this.diet = diet;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public DietLabels getDiet() {
		return diet;
	}

	public void setDiet(DietLabels diet) {
		this.diet = diet;
	}

}
