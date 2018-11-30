package nutrino.model;

public class Analysis {
	
	private String Label;
	private float Quantity;
	private String Unit;
	
	public Analysis(String label, float quantity, String unit) {
		super();
		Label = label;
		Quantity = quantity;
		Unit = unit;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public float getQuantity() {
		return Quantity;
	}
	public void setQuantity(float quantity) {
		Quantity = quantity;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}

}
