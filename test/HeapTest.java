package test; /**
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
import org.junit.Test;
import src.Heap;

public class HeapTest {

    /**
     * Test of the default constructor.
     */
    @Test
    public void testHeap() {
        Heap<Integer> test = new Heap<Integer>();
        assert(test.size() == 0);
    }

    /**
     * Test of the T type constructor.
     */
    @Test
    public void testHeapT() {
        Heap<Integer> test = new Heap<Integer>(10);
        assert(test.size() == 0);
        assert(test.max() == 10);
    }

    /**
     * Test of the int init, T type constructor.
     */
    @Test
    public void testHeapIntT() {
        Heap<Integer> test = new Heap<Integer>(10, 1000);
        assert(test.size() == 1);
        assert(test.memTotal() == 10);
        assert(test.max() == 1000);
    }

    /**
     * Test of the isEmpty() method.
     */
    @Test
    public void testIsEmpty() {
        Heap<Integer> test = new Heap<Integer>();
        assert(test.isEmpty());
        test.add(10);
        assert(!test.isEmpty());
    }

    /**
     * Test of the add(T) method.
     */
    @Test
    public void testAdd() {
        Heap<Integer> test = new Heap<Integer>();
        assert(test.isEmpty());
        assert(test.add(10));
        assert(!test.isEmpty());
        assert(test.max() == 10);
    }

    /**
     * Test of the DeleteMax(T) method.
     */
    @Test
    public void testDeleteMax() {
        Heap<Integer> test = new Heap<Integer>();
        test.add(10);
        test.add(40);
        test.add(30);
        test.add(50);
        test.add(20);
        assert(test.size() == 5);
        assert(test.max() == 50);
        assert(test.deleteMax(1) == 50);
        assert(test.max() == 40);
        assert(test.deleteMax(1) == 40);
        assert(test.max() == 30);
        assert(test.deleteMax(1) == 30);
        assert(test.max() == 20);
        assert(test.deleteMax(1) == 20);
        assert(test.max() == 10);
        assert(test.deleteMax(1) == 10);
        assert(test.max() == 0);
        assert(test.deleteMax(1) == 0);
        assert(test.size() == 0);
    }

    /**
     * Test of the toString() method.
     */
    @Test
    public void testToString() {
        Heap<Integer> test = new Heap<Integer>();
        assert(test.toString() == "[]");
        test.add(10);
        assert(test.toString() == "[10]");
        test.add(20);
        assert(test.toString() == "[20, 10]");
        test.add(30);
        assert(test.toString() == "[30, 20, 10]");
        assert(test.delete(1) == 30);
        assert(test.toString() == "[20, 10]");
    }

    /**
     * Test of the Delete(T) method.
     */
    @Test
    public void testDelete() {
        Heap<Integer> test = new Heap<Integer>();
        test.add(10);
        test.add(40);
        test.add(30);
        test.add(50);
        test.add(20);
        assert(test.size() == 5);
        assert(test.max() == 50);
        assert(test.delete(1) == 50);
        assert(test.max() == 40);
        assert(test.delete(1) == 40);
        assert(test.max() == 30);
        assert(test.delete(1) == 30);
        assert(test.max() == 20);
        assert(test.delete(1) == 20);
        assert(test.max() == 10);
        assert(test.delete(1) == 10);
        assert(test.max() == 0);
        assert(test.delete(1) == 0);
        assert(test.size() == 0);
    }

    /**
     * Test of the toArray() method.
     */
    @Test
    public void testToArray() {
        Heap<Integer> test = new Heap<Integer>();
        assert(test.toArray() == null);
        test.add(10);
        assert(test.toArray().length == 1);
        test.add(40);
        assert(test.toArray().length == 2);
        test.add(30);
        assert(test.toArray().length == 3);
        test.add(50);
        assert(test.toArray().length == 4);
        test.add(20);
        assert(test.toArray().length == 5);
        test.delete(1);
        assert(test.toArray().length == 4);
        test.delete(1);
        assert(test.toArray().length == 3);
    }

    /**
     * Test of the memTotal() method.
     */
    @Test
    public void testMemTotal() {
        Heap<Integer> test = new Heap<Integer>(10, 1000);
        assert(test.memTotal() == 10);
    }

    /**
     * Test of size() method.
     */
    @Test
    public void testSize() {
        Heap<Integer> test = new Heap<Integer>();
        assert(test.size() == 0);
        test.add(10);
        assert(test.size() == 1);
        test.add(20);
        assert(test.size() == 2);
        test.add(30);
        assert(test.size() == 3);
        test.add(40);
        assert(test.size() == 4);
        test.add(50);
        assert(test.size() == 5);
        test.delete(1);
        assert(test.size() == 4);
        test.delete(1);
        assert(test.size() == 3);
        test.delete(1);
        assert(test.size() == 2);
        test.delete(1);
        assert(test.size() == 1);
        test.delete(1);
        assert(test.size() == 0);
    }

    /**
     * Test of clear() method.
     */
    @Test
    public void testClear() {
        Heap<Integer> test = new Heap<Integer>();
        test.add(10);
        test.add(20);
        test.add(30);
        test.add(40);
        test.add(50);
        assert(test.size() == 5);
        test.clear();
        assert(test.size() == 0);
    }
}
