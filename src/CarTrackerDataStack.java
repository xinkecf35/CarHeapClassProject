import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

public class CarTrackerDataStack {
    private final int INTIAL_CAPACITY = 29;
    private final float LOAD_FACTOR = 0.5f;

    private String filename;
    private HashMap<String, CarIndexableMinHeap> priceMakeModelHeaps;
    private HashMap<String, CarIndexableMinHeap> mileageMakeModeHeaps;
    private CarIndexableMinHeap overallPriceHeap;
    private CarIndexableMinHeap overallMileageHeap;

    public CarTrackerDataStack(String file) {
        this.filename = file;
        this.overallMileageHeap = new CarIndexableMinHeap(INTIAL_CAPACITY,Car.mileageComparator());
        this.overallPriceHeap = new CarIndexableMinHeap(INTIAL_CAPACITY,Car.priceComparator());
        this.priceMakeModelHeaps = new HashMap<>(INTIAL_CAPACITY, LOAD_FACTOR);
        this.mileageMakeModeHeaps = new HashMap<>(INTIAL_CAPACITY, LOAD_FACTOR);
    }

    public CarTrackerDataStack(String file, int capacity) {
        this.filename = file;
        this.overallMileageHeap = new CarIndexableMinHeap(capacity,Car.mileageComparator());
        this.overallPriceHeap = new CarIndexableMinHeap(capacity,Car.priceComparator());
        this.priceMakeModelHeaps = new HashMap<>(capacity, LOAD_FACTOR);
        this.mileageMakeModeHeaps = new HashMap<>(capacity, LOAD_FACTOR);
    }

    /**
     * Read car from file as intially set during instantiation of the data stack
     * Each line is consider a Car and expects the values in the following order:
     * VIN, Make, Model, Price, Mileage, Color
     * ':' is the expected delimiter between the values and '#' is the expected comment indicator
     */
    public void addCarsToStackFromFile() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);

            String currentLine;
            while((currentLine = bufferedReader.readLine()) != null) {
                if(currentLine.charAt(0) == '#') {
                    //Skip any lines with # out front
                    continue;
                }
                Car current = carFromArray(currentLine.split(":"));
                puts(current);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if(bufferedReader != null) bufferedReader.close();
                if(fileReader != null) fileReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Add a Car to data stack
     * Takes as a parameter a fully formed Car object and adds it to the Data Stack
     * The data stack will keep its ordering in relation to both its price and mileage overall
     * It will also keep track of its ordering for its make and model
     */
    public void puts(Car car) {
        if(car == null) throw new IllegalArgumentException("Cannot add null to data stack");
        String makeModel = car.getMake()+car.getModel();
        overallPriceHeap.add(car);
        overallMileageHeap.add(car);
        CarIndexableMinHeap priceMakeModelHeap = priceMakeModelHeaps.get(makeModel);
        if(priceMakeModelHeap != null) {
            priceMakeModelHeap.add(car);
        } else {
            priceMakeModelHeap = new CarIndexableMinHeap(11, Car.priceComparator());
            priceMakeModelHeap.add(car);
            priceMakeModelHeaps.put(makeModel, priceMakeModelHeap);
        }
        CarIndexableMinHeap mileageMakeModelHeap = mileageMakeModeHeaps.get(makeModel);
        if(mileageMakeModelHeap != null) {
            mileageMakeModelHeap.add(car);
        } else {
            mileageMakeModelHeap = new CarIndexableMinHeap(11, Car.mileageComparator());
            mileageMakeModelHeap.add(car);
            priceMakeModelHeaps.put(makeModel, mileageMakeModelHeap);

        }
    }

    /**
     * Removes a car from the data stack using its VIN
     * @param VIN
     * @return the car object removed or null if not in data stack
     */
    public Car remove(String VIN) {
        return null;
    }
    /*
     * Helper function to turn Car From String array
     * Expects array to have the following order:
     * VIN, Make, Model, Price, Mileage, Color
     */
    private Car carFromArray(String[] items) {
        String VIN = items[0];
        String make = items[1];
        String model = items[2];
        double price = Double.parseDouble(items[3]);
        int mileage = Integer.parseInt(items[4]);
        String color = items[5];
        return new Car(VIN, make, model, color, price, mileage);
    }
}