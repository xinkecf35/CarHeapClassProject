import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class CarTest {

    private Car car1;
    private Car car2;

    @Before
    public void setUp() throws Exception {
        car1 = new Car("1GCHK29U86E255778","Chevrolet", "Silverado 2500HD","Blue",12345,5000);
        car2 = new Car("1YVHP84DX55M13025","Mazda","MAZDA6","Red",15000,7500);
    }

    @After
    public void tearDown() throws Exception {
        car1 = null;
        car2 = null;
    }

    @Test
    public void testSetColor() {
        String original = car1.getColor();
        car1.setColor("Red");
        assertNotEquals(original,car1.getColor());
    }

    @Test
    public void testSetPrice() {
        double original = car1.getPrice();
        car1.setPrice(16900);
        assertNotEquals(original, car1.getPrice());
    }

    @Test
    public void testSetMileage() {
        double original = car1.getMileage();
        car1.setMileage(7000);
        assertNotEquals(original,car1.getMileage());
    }

    @Test
    public void testSetEquals() {
        assertFalse(car1.equals(car2));
        Car car_copy = new Car("1GCHK29U86E255778","Chevrolet", "Silverado 2500HD","Blue",50000,5000);
        assertTrue(car1.equals(car_copy));
    }

    @Test
    public void testHashCode() {
        assertNotNull(car1.hashCode());
        assertNotNull(car2.hashCode());
    }

    @Test
    public void testToString() {
        System.err.println(car1.toString());
        System.err.println(car2.toString());
    }

    @Test
    public void testComparatorPrice() {
        Comparator<Car> comparator = Car.priceComparator();
        assertTrue(comparator.compare(car1,car2) < 0);
        assertTrue(comparator.compare(car2,car1) > 0);
        car1.setPrice(car2.getPrice());
        assertTrue(comparator.compare(car1,car2) == 0);
    }
    @Test
    public void  testComparatorMileage() {
        Comparator<Car> comparator = Car.mileageComparator();
        assertTrue(comparator.compare(car1,car2) < 0);
        assertTrue(comparator.compare(car2,car1) > 0);
        car1.setMileage(car2.getMileage());
        assertTrue(comparator.compare(car1,car2) == 0);
    }
}