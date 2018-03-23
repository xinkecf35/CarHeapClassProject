public class CarTracker {

    public static void main(String[] args) {
        CarTrackerDataStack dataStack = new CarTrackerDataStack("cars.txt", 11);
        dataStack.addCarsToStackFromFile();
        System.out.println("Hyundai Elantra Lowest: " + dataStack.getMakeModelMileageMin("Hyundai","Elantra"));
        System.out.println("Hyundai Elantra Lowest: " + dataStack.getMakeModelPriceMin("Hyundai","Elantra"));

    }
}
