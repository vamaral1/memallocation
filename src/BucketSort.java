package src;

/** Data Structures.
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

public class BucketSort extends Hashtable<Block> {

    /** Constructor using an array of objects to enter.
     * @param tsize total memory
     * @param add array to add to the table
     */
    public BucketSort(int tsize, Block[] add) {
        super(tsize);
        for (int i = 0;  i < add.length; i++) {
            add[i].hashBySize(false);
            this.add(add[i]);
        }
    }
}
