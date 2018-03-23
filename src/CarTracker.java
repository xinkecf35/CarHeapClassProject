import java.util.Scanner;

public class CarTracker {

    public static String[] getMakeModelFromUser(Scanner scanner) {
        String[] items = new String[2];
        System.out.println("Please enter make:");
        String make = scanner.next();
        items[0] = make.trim();
        System.out.println("Please enter model:");
        String model = scanner.next();
        items[1] = model.trim();
        return items;
    }

    public static String getVINFromUser(Scanner scanner) {
        System.out.println("Please enter a VIN:");
        String VIN = scanner.next();
        VIN = VIN.trim();
        return VIN;
    }

    public static Car updateCarUser(Scanner scanner, Car car) {
        System.out.println("What would like to update:\n1) Color\n 2)Price\n 3) Mileage");
        int input = scanner.nextInt();
        switch (input) {
            case 1:
                System.out.println("Please enter the color:");
                car.setColor(scanner.next().trim());
                break;
            case 2:
                System.out.println("Please enter the price:");
                car.setPrice(Double.parseDouble(scanner.next()));
                break;
            case 3:
                System.out.println("Please enter the mileage");
                car.setMileage(Integer.parseInt(scanner.next()));
                break;
            default:
                System.out.println("Invalid input, aborting\n");
                return null;
        }
        return car;
    }

    public static void main(String[] args) {
        // Initialize Data Stack and Application
        CarTrackerDataStack dataStack = new CarTrackerDataStack("cars.txt", 11);
        dataStack.addCarsToStackFromFile();
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        //Loops until user is done with CarTracker
        System.out.println("Welcome to xic90's Car Tracker");
        while(run) {
            System.out.println("Please select from the following:\n");
            System.out.println("1) Find lowest priced car");
            System.out.println("2) Find lowest mileage car");
            System.out.println("3) Find lowest priced car for a make and model");
            System.out.println("4) Find lowest mileage car for a make and model");
            System.out.println("5) Update a car");
            System.out.println("6) Remove a car");
            System.out.println("7) Add a car");
            System.out.println("0) Quit");
            int input = scanner.nextInt();
            if(dataStack.getSize() == 0) {
                // Catching if there is no cars in the data stack
                System.out.println("No cars in CarTracker, please add cars and try again\n");
                continue;
            }
            String[] description;
            Car car;
            //Options for the user
            switch (input) {
                case 1:
                    System.out.println("The Lowest Price Car is : "+ dataStack.getOverallPriceMin()+"\n");
                    break;
                case 2:
                    System.out.println("The Lowest Mileage Car is : "+ dataStack.getOverallMileageMin()+"\n");
                    break;
                case 3:
                    description = getMakeModelFromUser(scanner);
                    car = dataStack.getMakeModelPriceMin(description[0],description[1]);
                    if(car == null) {
                        System.out.println("Sorry, no data is available for that make and model");
                        System.out.println("Please check your input and try again\n");
                    } else {
                        System.out.print("The lowest priced car for this make and model is : ");
                        System.out.print(car.toString()+"\n");
                    }
                    break;
                case 4:
                    description = getMakeModelFromUser(scanner);
                    car = dataStack.getMakeModelMileageMin(description[0],description[1]);
                    if(car == null) {
                        System.out.println("Sorry, no data is available for that make and model");
                        System.out.println("Please check your input and try again\n");
                    } else {
                        System.out.print("The lowest mileage car for this make and model is : ");
                        System.out.print(car.toString()+"\n");
                    }
                    break;
                case 5:
                    car = dataStack.getCar(getVINFromUser(scanner));
                    if (car == null) {
                        System.out.println("Sorry, no data is available for that VIN");
                        System.out.println("Please check your input and try again\n");
                    } else {
                        car = updateCarUser(scanner, car);
                        if (car != null) {
                            dataStack.puts(car);
                            System.out.println("Updated car is now: " + dataStack.getCar(car.getVIN()));
                        }
                    }
                    break;
                case 6:
                    car = dataStack.remove(getVINFromUser(scanner));
                    if(car == null) {
                        System.out.println("Sorry, no data is available for that VIN");
                        System.out.println("Please check your input and try again\n");
                    } else {
                        System.out.println("Removed car: " +car.toString());
                    }
                    break;
                case 7:
                    // add car option here
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid input, try again\n");
                    continue;
            }
        }
    }
}
