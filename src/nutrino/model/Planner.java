package nutrino.model;

public class Planner {
	
	protected int planID;
	protected Users user;
	protected Day day;
	protected Time time;
	
	public enum Day {
		Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
	}
	
	public enum Time {
		Breakfast, Lunch, Dinner
	}
	
	

	public Planner(int planID, Users user, Day day, Time time) {
		this.planID = planID;
		this.user = user;
		this.day = day;
		this.time = time;
	}

	public int getPlanID() {
		return planID;
	}

	public void setPlanID(int planID) {
		this.planID = planID;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
