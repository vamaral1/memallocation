package src; /**
 * Data Structures
 * EN.600.226
 * HW# 8B
 * 11/20/2012
 *
 * Victor Amaral
 * Phone# 936-494-9224
 * JHED: vamaral1
 * Email: vamaral1@johnshopkins.edu
 *
 * Robert Davis
 * Phone# 410-585-4104
 * JHED: rdavisi1
 * Email: rdavisi1@jhu.edu
 *
 * Lucas Takatori
 * Phone# 425-347-9094
 * JHED: ltakato1
 * Email: ltakato1@johnshopkins.edu
 */

/**
 * src.Heap Class.  Creates a maximum heap of type T extends Comparable.
 * Implements Memory Structure.
 * Contains the following public classes:
 * isEmpty, size, memTotal, clear, max, add, delete, deleteMax,
 * toString, and toArray.
 * @param <T> extends Comparable.
 */
public class Heap<T extends Comparable<? super T>>
            implements MemoryStructure<T> {
    /**checkstyle.*/
    private final int m = 10;
    /** max is the maximum size of the src.Heap Array. */
    private int max = this.m;
    /** size is the current size of the heap - number of elements in heap.*/
    private int size = 0;
    /** arrayHeap is the Object array holding the elements in the heap.*/
    private Object[] arrayHeap = null;
    /** memTotal is the value of the original memory space to be allocated.*/
    private int memTotal;

    /**
     * The src.Heap() constructor.
     * Creates the arrayHeap as a new Object array of size max.
     * Sets size to 0.
     */
    public Heap() {
        this.size = 0;
        this.arrayHeap = new Object[this.max];
    }

    /**
     * The src.Heap(T obj) constructor.
     * Creates the arrayHeap as a new Object array of size max.
     * Assigns obj to arrayHeap[1] (arrayHeap[0] will always be empty.
     * Sets size to 1.
     * @param obj of type T.
     */
    public Heap(T obj) {
        this.arrayHeap = new Object[this.max];
        this.arrayHeap[1] = obj;
        this.size = 1;
    }

    /**
     * The src.Heap(int init, T obj) constructor.
     * Sets memTotal to init.
     * Creates the arrayHeap as a new Object array of size max.
     * Assigns obj to arrayHeap[1] (arrayHeap[0] will always be empty.
     * Sets size to 1.
     * @param obj the first object in the heap.
     * @param init the initial value of memTotal
     */
    public Heap(int init, T obj) {
        this.memTotal = init;
        this.arrayHeap = new Object[this.max];
        this.arrayHeap[1] = obj;
        this.size = 1;
    }

    /**
     * IsEmpty is the check as to whether or not the src.Heap is empty.
     * @return boolean test of size == 0.  If size is zero, return true,
     * if size != zero, return false.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Add adds a new element to the heap.
     * If the addition will make the array larger than max,
     * resizes the array to double its original size.
     * Accepts @param obj of type T.
     * Once the addition is done, calls the function bubbleUp to
     * maintain the heap consistency.
     * @return boolean - true, addition successful, false, addition
     * unsuccessful.
     *
     */
    public boolean add(T obj) {
        if (this.size == this.max - 1) {
            Object[] temp = new Object[this.max * 2];
            for (int i = 1; i < this.max; i++) {
                temp[i] = this.arrayHeap[i];
            }
            this.max = this.max * 2;
            this.arrayHeap = temp;
        }
        this.arrayHeap[this.size + 1] = obj;
        this.size++;
        this.bubbleUp();
        return true;
    }

    /**
     * DeleteMax is the helper method for delete(block).
     * DeleteMax removes the maximum value from the heap (the root)
     * and replaces it with the last item added to the heap.
     * After replacing the maximum with the last item added,
     * the function calls bubbleDown.
     * Accepts @param block of type T.
     * @return temp of type T, the maximum element in the heap.
     */
    @SuppressWarnings("unchecked")
    public T deleteMax(T block) {
        T temp = null;
        if (!this.isEmpty()) {
            temp = (T) this.arrayHeap[1];
            if (temp.compareTo(block) < 0) {
                return null;
            }
            this.arrayHeap[1] = this.arrayHeap[this.size];
            this.size--;
        }
        this.bubbleDown();
        return temp;
    }

    /**
     * BubbleUp is the private method called by add.
     * The method compares current to its parent.  If current
     * is larger than its parent, then they switch.
     * Accepts no @params and @returns void.
     */
    @SuppressWarnings("unchecked")
    private void bubbleUp() {
        int curr = this.size;
        int parent = this.size / 2;
        while (parent >= 1) {
            T currCont = (T) this.arrayHeap[curr];
            T parentCont = (T) this.arrayHeap[parent];
            if (currCont.compareTo(parentCont) > 0) {
                this.arrayHeap[parent] = this.arrayHeap[curr];
                this.arrayHeap[curr] = parentCont;
            }
            curr = parent;
            parent = parent / 2;
        }
    }

    /**
     * BubbleDown is the private method called by Remove.
     * It compares the current value to its two children.
     * If a child is larger than the current, the two switch.
     * If both children are larger than current, it switches current
     * with the larger of the two children.
     * Accepts no @params and @returns void.
     */
    @SuppressWarnings("unchecked")
    private void bubbleDown() {
        int curr = 1;
        int child1 = 2 * curr;
        int child2 = 2 * curr + 1;
        T currCont;
        T child1Cont;
        T child2Cont;
        while (child2 <= this.size) {
            currCont = (T) this.arrayHeap[curr];
            child1Cont = (T) this.arrayHeap[child1];
            child2Cont = (T) this.arrayHeap[child2];
            if (currCont.compareTo(child1Cont) < 0
                    && currCont.compareTo(child2Cont) < 0) {
                if (child1Cont.compareTo(child2Cont) < 0) {
                    this.arrayHeap[child2] = this.arrayHeap[curr];
                    this.arrayHeap[curr] = child2Cont;
                    curr = child2;
                } else {
                    this.arrayHeap[child1] = this.arrayHeap[curr];
                    this.arrayHeap[curr] = child1Cont;
                    curr = child1;
                }
            } else if (currCont.compareTo(child1Cont) < 0) {
                this.arrayHeap[child1] = this.arrayHeap[curr];
                this.arrayHeap[curr] = child1Cont;
                curr = child1;
            } else if (currCont.compareTo(child2Cont) < 0) {
                this.arrayHeap[child2] = this.arrayHeap[curr];
                this.arrayHeap[curr] = child2Cont;
                curr = child2;
            } else {
                return;
            }
            child1 = 2 * curr;
            child2 = 2 * curr + 1;
        }

    }

    /**
     * ToString converts the structure to a string.
     * @return temp, the string of items stored in the src.Heap.
     */
    @Override
    public String toString() {
        String temp = "[";
        for (int i = 1; i <= this.size; i++) {
            temp += this.arrayHeap[i];
            if (i < this.size) {
                temp += ", ";
            }
        }
        temp += "]";
        return temp;
    }

    /**
     * Delete is the public delete function for the Structure.
     * @param block of type T.
     * Calls the private helper method deleteMax(block).
     * @return the results of the call to deleteMax(block).
     */
    @Override
    public T delete(T block) {
        return this.deleteMax(block);
    }

    /**
     * ToArray returns an Object array.
     * Converts current array to Object array.  Removes the 0th element
     * from the original array.
     * @return temp, the result of the array copy.
     */
    @Override
    public Object[] toArray() {
        Object[] temp = new Object[this.size];
        if (this.size == 0) {
            temp = null;
            return temp;
        }
        for (int i = 1; i <= this.size; i++) {
            temp[i - 1] = this.arrayHeap[i];
        }
        return temp;
    }

    /**
     * MemTotal method returns the original size of the memory space.
     * This was provided by the first line of the input file.
     * @return memTotal.
     */
    @Override
    public int memTotal() {
        return this.memTotal;
    }

    /**
     * Size method returns the size of the src.Heap structure.
     * @return size
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Clear method removes all items from the current structure.
     * It does so by creating a new Object Array and assigning that
     * to arrayHeap.
     *
     */
    @Override
    public void clear() {
        this.size = 0;
        this.arrayHeap = new Object[this.max];
    }

    /**
     * Max returns the maximum item in the heap.  This is the root value.
     * @return temp of type T, the maximum element in array.
     */
    @SuppressWarnings("unchecked")
    public T max() {
        if (!this.isEmpty()) {
            return ((T) this.arrayHeap[1]);
        }
        T temp = null;
        return temp;
    }
}
