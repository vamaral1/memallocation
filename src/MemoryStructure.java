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

/** Interface for a memory structure.
 * @param <T> comparable
 */
public interface MemoryStructure<T extends Comparable<? super T>> {

    /** adds the node to the structure.
     * @param block node to add to the structure
     * @return best-fit close-fit or worst-fit depending on implementation
     */
    boolean add(T block);

    /** removes the node from the structure and returns the node.
     * @param block - compare in structure to block to find one bigger based on
     * best-fit close-fit or worst-fit depending on implementation
     * @return node that is best-fit close-fit or worst-fit depending
     * on implementation
     */

    T delete(T block);

    /** puts the structure into an array.
     * @return an array
     */
    Object[] toArray();

    /** gets the total memory in the structure.
     * @return the total memory
     */
    int memTotal();
    /** gets the size.
     * @return the size
     */
    int size();
    /** checks to see if it's empty.
     * @return boolean either empty or not
     */
    boolean isEmpty();
    /** clears the data.
     */
    void clear();
}
