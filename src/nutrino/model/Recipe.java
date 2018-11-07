package nutrino.model;

public class Recipe {
	
	protected int id;
	protected String uri;
	protected String label;
	protected String image;
	protected String url;
	protected String calories;
	protected String ingredient_lines;
	protected String total_weight;
	protected String health_labels;
	
	public Recipe(int id, String uri, String label, String image, String url, String calories, String ingredient_lines,
			String total_weight, String health_labels) {
		super();
		this.id = id;
		this.uri = uri;
		this.label = label;
		this.image = image;
		this.url = url;
		this.calories = calories;
		this.ingredient_lines = ingredient_lines;
		this.total_weight = total_weight;
		this.health_labels = health_labels;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
	public String getIngredient_lines() {
		return ingredient_lines;
	}
	public void setIngredient_lines(String ingredient_lines) {
		this.ingredient_lines = ingredient_lines;
	}
	public String getTotal_weight() {
		return total_weight;
	}
	public void setTotal_weight(String total_weight) {
		this.total_weight = total_weight;
	}
	public String getHealth_labels() {
		return health_labels;
	}
	public void setHealth_labels(String health_labels) {
		this.health_labels = health_labels;
	}

}
