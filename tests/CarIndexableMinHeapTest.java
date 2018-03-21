import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarIndexableMinHeapTest {

    private CarIndexableMinHeap priceHeap;
    private CarIndexableMinHeap mileageHeap;


    @Before
    public void setUp() throws Exception {
        priceHeap = new CarIndexableMinHeap(29, Car.priceComparator());
        mileageHeap = new CarIndexableMinHeap(29, Car.mileageComparator());
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
        assertTrue(mileageHeap.getSize() == 3);
        assertTrue(car3.equals(mileageHeap.getMin()));

    }

    @Test
    public void remove() {
    }
}