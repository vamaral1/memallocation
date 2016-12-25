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
import src.Block;
import src.Sorter;

import static org.junit.Assert.assertEquals;

/** Set of Junit tests for the sorter.
 */
public class SorterTest {

    Block a = new Block(0, 20, 57);
    Block j = new Block(0, 50, 34);
    Block c = new Block(0, 40, 43);
    Block d = new Block(0, 10, 12);
    Block e = new Block(0, 25, 72);
    Block i = new Block(0, 60, 26);
    Block g = new Block(0, 80, 20);
    Block h = new Block(0, 100, 15);
    Block f = new Block(0, 5, 4);
    Block b = new Block(0, 1, 3);
    Block [] arr = {a, b, c, d, e, f, g, h, i, j};
    Sorter s = new Sorter();

    @Test
    public void TestbucketSort() {
        Block[] b = s.bucketsort(1000, arr);
        assertEquals(b[0].toString(), "address: 3 size: 1 request number: 0\n");
        assertEquals(b[1].toString(), "address: 4 size: 5 request number: 0\n");
        assertEquals(b[2].toString(), "address: 12 size: 10 request number: 0\n");
        assertEquals(b[3].toString(), "address: 15 size: 100 request number: 0\n");
        assertEquals(b[4].toString(), "address: 20 size: 80 request number: 0\n");
        assertEquals(b[5].toString(), "address: 26 size: 60 request number: 0\n");
        assertEquals(b[6].toString(), "address: 34 size: 50 request number: 0\n");
        assertEquals(b[7].toString(), "address: 43 size: 40 request number: 0\n");
        assertEquals(b[8].toString(), "address: 57 size: 20 request number: 0\n");
        assertEquals(b[9].toString(), "address: 72 size: 25 request number: 0\n");

    }
    @Test
    public void TestquickSort() {
        Block[] b = s.quicksort(arr);
        assertEquals(b[0].toString(), "address: 3 size: 1 request number: 0\n");
        assertEquals(b[1].toString(), "address: 4 size: 5 request number: 0\n");
        assertEquals(b[2].toString(), "address: 12 size: 10 request number: 0\n");
        assertEquals(b[3].toString(), "address: 15 size: 100 request number: 0\n");
        assertEquals(b[4].toString(), "address: 20 size: 80 request number: 0\n");
        assertEquals(b[5].toString(), "address: 26 size: 60 request number: 0\n");
        assertEquals(b[6].toString(), "address: 34 size: 50 request number: 0\n");
        assertEquals(b[7].toString(), "address: 43 size: 40 request number: 0\n");
        assertEquals(b[8].toString(), "address: 57 size: 20 request number: 0\n");
        assertEquals(b[9].toString(), "address: 72 size: 25 request number: 0\n");

    }
    @Test
    public void TestInsertionSort() {
        s.insertionSort(arr, 3, 7);
        assertEquals(arr[3].toString(), "address: 4 size: 5 request number: 0\n");
        assertEquals(arr[4].toString(), "address: 12 size: 10 request number: 0\n");
        assertEquals(arr[5].toString(), "address: 15 size: 100 request number: 0\n");
        assertEquals(arr[6].toString(), "address: 20 size: 80 request number: 0\n");
        assertEquals(arr[7].toString(), "address: 72 size: 25 request number: 0\n");

    }
    @Test
    public void TestSwap() {
        Block[] b = {a,f,d};
        s.swap(b, 0, 2);
        assertEquals(b[0].toString(), "address: 12 size: 10 request number: 0\n");
        assertEquals(b[1].toString(), "address: 4 size: 5 request number: 0\n");
        assertEquals(b[2].toString(), "address: 57 size: 20 request number: 0\n");

    }

    @Test
    public void Testmedian3() {
      Block[] b = {a,f,d};
      Block block = s.median3(b, 0, 2);
      assertEquals(block.toString(),b[1].toString());
    }


}