// Abraham Gonzalez
// 10/15/2026
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

		Scanner keyboard = new Scanner(System.in);

		int choice = 0;

		do {
			System.out.println("\n===== Car Menu =====");
			System.out.println("1. Sort Cars by Brand");
			System.out.println("2. Search Cars by Brand");
			System.out.println("3. Exit");
			System.out.println("Enter choice: ");

			choice = keyboard.nextInt();
			keyboard.nextLine();

			switch (choice) {
			case 1:
				selectionSortByBrand(working);

				System.out.println("\nFirst 10 Cars:");

				for (int i = 0; i < 10 && i < working.size(); i++) {
					System.out.println(working.get(i));
				}
				break;
			case 2:

				// Binary search requires sorted data
				selectionSortByBrand(working);

				System.out.print("Enter Brand: ");
				String brand = keyboard.nextLine();

				displayMatchingBrands(working, brand);

				break;

			case 3:

				System.out.println("Goodbye!");
				break;

			default:

				System.out.println("Invalid choice.");
			}

		} while (choice != 3);

		keyboard.close();
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

	public static void selectionSortByBrand(ArrayList<Car> working) {
		for (int i = 0; i < working.size() - 1; i++) {
			int minIndex = i;

			for (int j = i + 1; j < working.size(); j++) {
				if (working.get(j).getBrand().compareToIgnoreCase(working.get(minIndex).getBrand()) < 0) {

					minIndex = j;
				}
			}
			Car temp = working.get(i);
			working.set(i, working.get(minIndex));
			working.set(minIndex, temp);
		}
		System.out.println("Cars sorted by Brand.");
	}

	public static int binarySearchBrand(ArrayList<Car> working, String brand) {
		int low = 0;
		int high = working.size() - 1;
		
		while (low <= high) {
			int mid = (low + high) /2 ;
			
			int comparison = working.get(mid).getBrand().compareToIgnoreCase(brand);
			
			if(comparison == 0) {
				return mid;
			} else if (comparison < 0) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}

	public static void displayMatchingBrands(ArrayList<Car> working, String brand) {

		int index = binarySearchBrand(working, brand);

		if (index == -1) {

			System.out.println("No cars found for brand: " + brand);
			return;
		}

		int left = index;

		while (left >= 0 && working.get(left).getBrand().equalsIgnoreCase(brand)) {

			left--;
		}

		left++;

		System.out.println("\nMatching Cars:");

		while (left < working.size() && working.get(left).getBrand().equalsIgnoreCase(brand)) {

			System.out.println(working.get(left));
			left++;
		}
	}

}