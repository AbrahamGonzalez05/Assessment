//this is the start of your Car class 
//

package assessmentCarProject;

public class Car {
	
	private String Car_ID;
	private String Brand;
	private String Model;
	private int Year;
	private String Fuel_Type;
	private String Color;
	private double Mileage_kmpl;
	
	public Car(String carId, String brand, String model, int year, String fueltype, String color, double mileagekmpl) {
		Car_ID = carId;
		Brand = brand;
		Model = model;
		Year = year;
		Fuel_Type = fueltype;
		Color = color;
		Mileage_kmpl = mileagekmpl;
	}
	
	public String getCar_ID() {
		return Car_ID;
	}
	public String getBrand() {
		return Brand;
	}
	public String getModel() {
		return Model;
	}
	public int getYear() {
		return Year;
	}
	public String getFuel_Type() {
		return Fuel_Type;
	}
	public String getColor() {
		return Color;
	}
	public double getMileage_kmpl() {
		return Mileage_kmpl;
	}
	@Override
	public String toString() {
		return Car_ID + " | " + Brand + " | " + Model + " | " + Year + " | " + Fuel_Type + " | " + Color + " | " + Mileage_kmpl + " kmpl";
	}
}


