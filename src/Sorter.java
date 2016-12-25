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
//Code adapted from Weiss textbook

import src.Block;
import src.BucketSort;

/**quicksort and bucket sort.*/
public class Sorter {
    /**helps decide whether to do insertion sort.*/
    private final int max = 3;

    /**used for bucketSort.*/
    private BucketSort bsort;

    /**checkstyle.*/
    public Sorter() {
    }

    /** used to sort with bucketsort.
     * @param totalMem total memory
     * @param a array of blocks to sort
     * @return block array sorted
     */
    public Block[] bucketsort(int totalMem, Block [] a) {
        if (a.length == 0) {
            return new Block[0];
        }
        this.bsort = new BucketSort(totalMem, a);
        Object[] in = this.bsort.toArray();
        Block[] almost = new Block[in.length];
        for (int i = 0; i < in.length; i++) {
            almost[i] = (Block) in[i];
        }
        for (int i = 0; i < almost.length; i++) {
            almost[i].hashBySize(true);
        }
        insertionSort(almost, 0 , almost.length - 1);
        return almost;
    }

    /** quicksort.
     * @param a an array of blocks.
     * @return the sorted block array
     */
    public Block[] quicksort(Block [] a) {
        this.quicksort(a, 0, a.length - 1);
        return a;
    }


    /** Swap two elements in an array.
     * @param a an array of blocks
     * @param i the index of the first object
     * @param j the index of the second object
     */
    public void swap(Block [] a, int i, int j) {
        Block temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


    /** return median of left, middle, right index to use as pivot.
     * @param a an array
     * @param left the first element
     * @param right the last element
     * @return a block
     */
    public Block median3(Block [] a, int left, int right) {
        int middle = (left + right) / 2;
        if (a[middle].compToAddress(a[left]) < 0) {
            this.swap(a, left, middle);
        }
        if (a[right].compToAddress(a[left]) < 0) {
            this.swap(a, left, right);
        }
        if (a[right].compToAddress(a[middle]) < 0) {
            this.swap(a, middle, right);
        }

        this.swap(a, middle, right - 1);
        return a[right - 1];
    }


    /** recursive quicksort helper.
     * @param a an array of Blocks.
     * @param left the index of the first object
     * @param right the index of the last object
     */
    public void quicksort(Block [] a, int left, int right) {
        if (left + this.max <= right) {
            Block pivot = this.median3(a, left, right);

            boolean decision = true;
            int i = left, j = right - 1;
            while (decision) {
                i++;
                while (a[i].compToAddress(pivot) < 0) { i++; }
                j--;
                while (a[j].compToAddress(pivot) > 0) { j--; }
                if (i < j) {
                    this.swap(a, i, j);
                } else {
                    decision = false;
                }
            }

            this.swap(a, i, right - 1);   // Restore pivot

            this.quicksort(a, left, i - 1);    // Sort small elements
            this.quicksort(a, i + 1, right);   // Sort large elements
        } else {
            insertionSort(a, left, right);
        }
    }
    /** insertion sort.
     * @param a an array of Blocks.
     * @param left the first index of the array.
     * @param right the last index of the array.
     */
    public void insertionSort(Block [] a, int left, int right) {
        Block block;
        for (int index = left + 1; index <= right; index++) {
            block = a[index];
            int j;
            for (j = index; j > left
                    && block.compToAddress(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = block;
        }
    }
}