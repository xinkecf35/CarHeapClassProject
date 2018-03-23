import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarIndexableMinHeapTest {

    private CarIndexableMinHeap priceHeap;
    private CarIndexableMinHeap mileageHeap;


    @Before
    public void setUp() throws Exception {
        priceHeap = new CarIndexableMinHeap(3, Car.priceComparator());
        mileageHeap = new CarIndexableMinHeap(3, Car.mileageComparator());
    }

    @After
    public void tearDown() throws Exception {
        priceHeap = null;
        mileageHeap = null;
    }

    @Test
    public void add() {
        Car car1 = new Car("1GCHK29U86E255778","Chevrolet", "Silverado 2500HD","Blue",12345,5000);
        Car car2 = new Car("1YVHP84DX55M13025","Mazda","MAZDA6","Red",15000,7500);
        Car car3 = new Car("2G1WD57C491198247", "Chevrolet", "Impala", "Green", 10000, 3000);
        Car car4 = new Car("2YVWD57C491198980", "Toyota","Camry","Ocean Blue", 5000, 25000);
        Car car5 = new Car("5NPEB4AC1DH576656","Hyundai","Sonata","Sea Green",5000,25000);
        priceHeap.add(car1);
        priceHeap.add(car2);
        assertTrue(priceHeap.getSize() == 2);
        assertTrue(car1.equals(priceHeap.getMin()));
        mileageHeap.add(car1);
        mileageHeap.add(car2);
        assertTrue(mileageHeap.getSize() == 2);
        assertTrue(car1.equals(mileageHeap.getMin()));
        priceHeap.add(car3);
        mileageHeap.add(car3);
        assertTrue(priceHeap.getSize() == 3);
        assertTrue(car3.equals(priceHeap.getMin()));
        assertTrue(mileageHeap.getSize() == 3);
        assertTrue(car3.equals(mileageHeap.getMin()));
        priceHeap.add(car4);
        mileageHeap.add(car4);
        assertTrue(priceHeap.getSize() == 4);
        assertTrue(car4.equals(priceHeap.getMin()));
        assertTrue(mileageHeap.getSize() == 4);
        assertTrue(car3.equals(mileageHeap.getMin()));
        priceHeap.add(car5);
        mileageHeap.add(car5);
        assertTrue(car4.equals(priceHeap.getMin()));
        assertTrue(car3.equals(mileageHeap.getMin()));
        car1.setPrice(1000);
        priceHeap.add(car1);
        assertTrue(car1.equals(priceHeap.getMin()));
    }

    @Test
    public void remove() {
        String VIN1 = "1GCHK29U86E255778";
        String VIN2 = "1YVHP84DX55M13025";
        String VIN3 = "2G1WD57C491198247";
        String VIN4 = "2YVWD57C491198980";
        Car car1 = new Car(VIN1,"Chevrolet", "Silverado 2500HD","Blue",12345,5000);
        Car car2 = new Car(VIN2,"Mazda","MAZDA6","Red",15000,7500);
        Car car3 = new Car(VIN3, "Chevrolet", "Impala", "Green", 10000, 3000);
        Car car4 = new Car(VIN4, "Toyota","Camry","Ocean Blue", 5000, 25000);
        priceHeap.add(car1);
        priceHeap.add(car2);
        priceHeap.add(car3);
        mileageHeap.add(car1);
        mileageHeap.add(car2);
        mileageHeap.add(car3);
        assertTrue(car3.equals(priceHeap.remove(VIN3)));
        assertTrue(priceHeap.getSize() == 2);
        assertTrue(car1.equals(priceHeap.getMin()));
        assertTrue(car3.equals(mileageHeap.remove(VIN3)));
        assertTrue(mileageHeap.getSize() == 2);
        assertTrue(car1.equals(mileageHeap.getMin()));

    }
    @Test
    public void removeMin() {
        String VIN1 = "1GCHK29U86E255778";
        String VIN2 = "1YVHP84DX55M13025";
        String VIN3 = "2G1WD57C491198247";
        Car car1 = new Car(VIN1,"Chevrolet", "Silverado 2500HD","Blue",12345,5000);
        Car car2 = new Car(VIN2,"Mazda","MAZDA6","Red",15000,7500);
        Car car3 = new Car(VIN3, "Chevrolet", "Impala", "Green", 10000, 3000);
        priceHeap.add(car1);
        priceHeap.add(car2);
        priceHeap.add(car3);
        mileageHeap.add(car1);
        mileageHeap.add(car2);
        mileageHeap.add(car3);
        assertTrue(car3.equals(priceHeap.removeMin()));
        assertTrue(priceHeap.getSize() == 2);
        assertTrue(car1.equals(priceHeap.getMin()));
        assertTrue(car3.equals(mileageHeap.removeMin()));
        assertTrue(mileageHeap.getSize() == 2);
        assertTrue(car1.equals(mileageHeap.getMin()));
    }
    @Test
    public void get() {
        String VIN1 = "1GCHK29U86E255778";
        String VIN2 = "1YVHP84DX55M13025";
        String VIN3 = "2G1WD57C491198247";
        String VIN4 = "2YVWD57C491198980";
        String VIN5 = "5NPEB4AC1DH576656";
        Car car1 = new Car(VIN1,"Chevrolet", "Silverado 2500HD","Blue",12345,5000);
        Car car2 = new Car(VIN2,"Mazda","MAZDA6","Red",15000,7500);
        Car car3 = new Car(VIN3, "Chevrolet", "Impala", "Green", 10000, 3000);
        Car car4 = new Car(VIN4, "Toyota","Camry","Ocean Blue", 5000, 25000);
        Car car5 = new Car(VIN5,"Hyundai","Sonata","Sea Green",5000,25000);
        priceHeap.add(car1);
        priceHeap.add(car2);
        priceHeap.add(car3);
        priceHeap.add(car4);
        priceHeap.add(car5);
        Car car_get = priceHeap.get(VIN1);
        assertNotNull(car_get);
        assertTrue(car1.equals(car_get));
        car_get = priceHeap.get(VIN2);
        assertNotNull(car_get);
        assertTrue(car2.equals(car_get));
        car_get = priceHeap.get(VIN3);
        assertNotNull(car_get);
        assertTrue(car3.equals(car_get));
        car_get = priceHeap.get(VIN4);
        assertNotNull(car_get);
        assertTrue(car4.equals(car_get));
        car_get = priceHeap.get(VIN5);
        assertNotNull(car_get);
        assertTrue(car5.equals(car_get));
        assertNull(priceHeap.get("1111111111"));
    }
}