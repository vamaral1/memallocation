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

import src.Block;

/**Interface to allocate/deallocate and sort data.*/
public interface MemAllocation {

    /** Allocate the memory.
     * @param size the size
     * @return int address of allocation
     */
    int alloc(int size);

    /** Deallocate the memory.
     * @param reqNum the request number
     * @return true if succeeded else false
     */
    boolean deAlloc(int reqNum);

    /** Defragment using bucket sort.
     * @return int size of structure
     * @param b the block
     */
    boolean defrag(Block[] b);

    /** bucket sort.
     * @return sorted array
     */
    Block[] bucket();
    /** quick sort.
     * @return sorted array
     */
    Block[] quick();


}
