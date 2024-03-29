import java.util.Comparator;
import java.util.HashMap;

public class CarIndexableMinHeap {
    private Comparator<Car> comparator;
    private HashMap<String, Integer> indirectionTable;
    private Car[] heap;
    private int lastIndex = -1;
    private int size = 0;
    private static final int INIT_CAPACITY = 29;
    private static final float LOAD_FACTOR = 0.5f;

    /**
     * Default constructor
     * Sorting and ordering is by default based on Car price
     */
    public CarIndexableMinHeap() {
        heap = new Car[INIT_CAPACITY];
        indirectionTable = new HashMap<>(INIT_CAPACITY, LOAD_FACTOR);
        comparator = Car.priceComparator();
    }

    /**
     * Constructor that takes a capacity
     * This will set the initial capacity for the indirectionTable
     */

    public CarIndexableMinHeap(int capacity) {
        heap = new Car[capacity];
        indirectionTable = new HashMap<>(capacity, LOAD_FACTOR);
        comparator = Car.priceComparator();
    }
    /**
     * Constructor that takes a capacity and a comparator
     * This will set the initial capacity for the indirectionTable
     * also will set the comparator for the heap which be used to define the
     * ordering and sorting of the heap
     */

    public CarIndexableMinHeap(int capacity, Comparator<Car> comparator) {
        heap = new Car[capacity];
        indirectionTable = new HashMap<>(capacity, LOAD_FACTOR);
        this.comparator = comparator;
    }

    /**
     * Add Car to the heap
     */

    public int add(Car car){
        heap[++lastIndex] = car;
        indirectionTable.put(car.getVIN(),lastIndex);
        heapUp();
        ensureCapacity();
        return size++;
    }

    /**
     * Returns the minimum element of the heap as sorted and defined by
     * the comparator set.
     * May return null if there is nothing in the heap
     * @return the first element of the heap
     */
    public Car getMin() {
        if(lastIndex == -1) return null;
        return heap[0];
    }

    /**
     * Removes the minimum element of the heap as defined by the comparator
     * May return null if there is nothing in the heap
     * @return Car object that was minimum of heap
     */
    public Car removeMin() {
        if(heap[0] == null) return null;
        indirectionTable.remove(heap[0].getVIN());
        Car removed = heap[0];
        swap(0,lastIndex--);
        size--;
        heapUp();
        return removed;
    }

    /**
     * Removes a Car object from the heap using its VIN
     * Return null if the Car is not in the object
     * @param VIN
     * @return a Car object that corresponds to the VIN or null if not in the heap
     */
    public Car get(String VIN) {
        Integer index = indirectionTable.get(VIN);
        if(index != null) {
            return heap[index];
        } else {
            return null;
        }
    }
    /**
     * Removes a Car from the heap
     * Lookup Car based on VIN and removes the corresponding car
     */
    public Car remove(String VIN) {
        Integer remove = indirectionTable.remove(VIN);
        if(remove == null) return null;
        Car removed = heap[remove];
        swap(remove, lastIndex--);
        size--;
        heapDown();
        return removed;
    }

    public int getSize() {
        return size;
    }

    /*
     * Heap helper functions
     */

    /*
     * Turns the heap array into actual heap and satisfy the heap invariant
     * Ordering is based on comparator as defined during instantiation
     * The operation starts from the last element added and moves up the tree
     */

    private void heapUp() {
        int current = lastIndex;
        while(current > 0) {
            int parentIndex = parentOf(current);
            Car parent = heap[parentIndex];
            Car child = heap[current];
            if(comparator.compare(child,parent) < 0) {
                swap(parentIndex, current);
            }
            current = parentIndex;
        }
    }

    private void heapDown() {
        int current = 0;
        while (current < lastIndex) {
            int minChild = getMinChildIndex(current);
            if(minChild > -1) {
                Car parent = heap[current];
                Car child = heap[minChild];
                if (comparator.compare(parent,child) > 0) {
                    swap(current, minChild);
                }
            } else {
                break;
            }
            current = minChild;
        }
    }

    /*
     * Swaps two element in the heap array
     */
    private void swap(int parent, int child) {
        Car a = heap[parent];
        heap[parent] = heap[child];
        heap[child] = a;
        indirectionTable.put(heap[child].getVIN(), child);
        indirectionTable.put(heap[parent].getVIN(), parent);
    }

    /*
     * Expand the heap if all indices in the heap are consumed, there by ensuring capacity
     * Note this will not resize indirection table when the operation occurs
     */
    private void ensureCapacity() {
        if(lastIndex + 1 == heap.length) {
            Car[] temp = new Car[2 * heap.length + 1];
            System.arraycopy(heap,0,temp,0,heap.length);
            heap = temp;
        }
    }

    /*
     * Calculating children and parents of nodes in the heap
     */

    private int parentOf(int i) {
        return (i - 1)/2;
    }
    private int leftChildOf(int i) {
        return 2*i + 1;
    }
    private int rightChildOf(int i) {
        return 2*i + 2;
    }
    private int getMinChildIndex(int i) {
        int left = leftChildOf(i);
        if(left >= lastIndex) {
            return -1;
        } else {
            int right = rightChildOf(i);
            if(right >= lastIndex || comparator.compare(heap[right], heap[left]) > 0) {
                return left;
            } else {
                return right;
            }
        }
    }

}


// Car object as our key, it value is its position in the heap