package nutrino.model;

public class Plan {
	
	protected String day;
	protected String time;
	protected String label;
	
	public Plan(String day, String time, String recipe) {
		super();
		this.day = day;
		this.time = time;
		this.label = recipe;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String recipe) {
		this.label = recipe;
	}

}
