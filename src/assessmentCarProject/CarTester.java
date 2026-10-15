package assessmentCarProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class CarTester {

	public static void main(String[] args) {
		ArrayList<Car> cars = loadCars("Car_data.csv");

        // Create working list AFTER loading all cars
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));

        System.out.println("Working list size: " + working.size());

        // Test output
        if (!working.isEmpty()) {
            System.out.println(working.get(0));
        }

	}
	public static ArrayList<Car> loadCars(String filename) {
	    ArrayList<Car> cars = new ArrayList<>();
	    try {
	        Scanner fileScanner = new Scanner(new File(filename));
	     // Skip header row
            if (fileScanner.hasNextLine()) {
                fileScanner.nextLine();
            }
            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                try {

                    String[] data = line.split(",");

                    // Skip malformed rows
                    if (data.length != 7) {
                        continue;
                    }

                    String carID = data[0].trim();
                    String brand = data[1].trim();
                    String model = data[2].trim();
                    int year = Integer.parseInt(data[3].trim());
                    String fuelType = data[4].trim();
                    String color = data[5].trim();
                    double mileageKmpl = Double.parseDouble(data[6].trim());

                    Car car = new Car(carID, brand, model, year, fuelType, color, mileageKmpl);

                    cars.add(car);

                } catch (Exception e) {
                    // Skip bad rows without crashing
                    continue;
                }
            }

            fileScanner.close();

            System.out.println("Total number of cars loaded: " + cars.size());
	    } catch (FileNotFoundException e) {
	        System.out.println("Error: file not found - " + filename);
	    }
	    return cars;
	}

}