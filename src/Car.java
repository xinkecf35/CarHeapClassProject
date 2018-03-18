import java.util.Comparator;
import java.util.Objects;

public class Car {
    private String VIN;
    private String make;
    private String model;
    private String color;
    private double price;
    private int mileage;

    /**
     * Do not use
     */
    public Car() {

    }

    public Car(String VIN,
               String make,
               String model) {
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.color = "";
        this.price = 0;
        this.mileage = 0;
    }

    public Car(String VIN,
               String make,
               String model,
               String color,
               double price,
               int mileage) {
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.color = color;
        this.price = price;
        this.mileage = mileage;
    }

    public String getVIN() {
        return VIN;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(VIN, car.VIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VIN);
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("{\n");
       sb.append(String.format("\tReference:\t%d\n",System.identityHashCode(this)));
       sb.append(String.format("\tVIN:\t\t%s,\n",VIN));
       sb.append(String.format("\tMake:\t\t%s,\n",make));
       sb.append(String.format("\tModel:\t\t%s,\n",model));
       sb.append(String.format("\tColor:\t\t%s,\n",color));
       sb.append(String.format("\tPrice:\t\t%.2f,\n",price));
       sb.append(String.format("\tMileage:\t%d\n",mileage));
       sb.append("}");
       return sb.toString();

    }

    /**
     * Comparator
     */

    public static Comparator<Car> priceComparator() {
        return new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        };
    }

    public static Comparator<Car> mileageComparator() {
        return new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                return o1.getMileage()-o2.getMileage();
            }
        };
    }
}
