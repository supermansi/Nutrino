package nutrino.model;

public class PlannerToRecipe {

	protected int pToRid;
	protected int recipeId;
	protected int planId;
	
	public PlannerToRecipe(int pToRid,int recipeId,int planId) {
		this.pToRid = pToRid;
		this.recipeId = recipeId;
		this.planId = planId;
	}

	public int getpToRid() {
		return pToRid;
	}

	public void setpToRid(int pToRid) {
		this.pToRid = pToRid;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}
	
}
