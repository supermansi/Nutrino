package nutrino.model;

public class RecipeToHealthLabels {

	protected int hId;
	protected String labelName;
	protected String recipeName;
	
	public RecipeToHealthLabels(int hId, String labelName, String recipeName) {
		this.hId = hId;
		this.labelName = labelName;
		this.recipeName = recipeName;
	}

	public int gethId() {
		return hId;
	}

	public void sethId(int hId) {
		this.hId = hId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	
	
}
