import java.util.Comparator;
import java.util.HashMap;

public class CarIndexableMinHeap {
    private Comparator<Car> comparator;
    private HashMap<String, Integer> indirectionTable;
    private Car[] heap;
    private int lastIndex = 0;
    private static final int INIT_CAPACITY = 25;
    private static final float LOAD_FACTOR = 0.5f;
    public CarIndexableMinHeap() {
        heap = new Car[INIT_CAPACITY];
        indirectionTable = new HashMap<String, Integer>(INIT_CAPACITY, LOAD_FACTOR);
    }

    public CarIndexableMinHeap(int capacity) {
        heap = new Car[capacity];
        indirectionTable = new HashMap<String, Integer>(capacity, LOAD_FACTOR);
    }

    public void add(Car car){
        heap[lastIndex++] = car;
        int index = heapify();

    }
    public Car getMin() {
        if(lastIndex == 0) {
            return null;
        }
        return heap[0];
    }
    public Car remove(String VIN) {
        return null;
    }
    private int heapify() {
        int index = 0;
        if(lastIndex == 0) {
            return -1;
        }
        return index;
    }

}


// Car object as our key, it value is its position in the heap