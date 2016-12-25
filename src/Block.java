package src; /**Data Structures.
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

/**creates a block of memory.*/
public class Block implements Comparable<Block> {

    /**the request number.*/
    private int reqNum;
    /**the size of the block.*/
    private int size;
    /**the address.*/
    private int address;
    /**the next block.*/
    private Block next;
    /**the hash function.*/
    private boolean hashSize;

    /**constructor.*/
    public Block() {

    }
    /**constructor.
     * @param rNum the request
     * @param s the size
     * @param a the address
     */
    public Block(int rNum, int s, int a) {
        this.reqNum = rNum;
        this.size = s;
        this.address = a;
        this.hashSize = true;
    }
    /**constructor.
     * @param rNum the request
     * @param s the size
     */
    public Block(int rNum, int s) {
        this.reqNum = rNum;
        this.size = s;
        this.address = 0;
        this.hashSize = true;
    }
    /**constructor.
     * @param a the address
     */
    public Block(int a) {
        a = this.address;
        this.hashSize = true;
    }
    /** sets the request number.
     * @param rNum the request number
     */
    public void setReqNumber(int rNum) {
        this.reqNum = rNum;
    }

    /** get the request number.
     * @return the request number
     */
    public int getReqNumber() {
        return this.reqNum;
    }

    /** set the size.
     * @param s the size
     */
    public void setSize(int s) {
        this.size = s;
    }

    /** gets the size of the block.
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /** sets the address.
     * @param a the address
     */
    public void setAddress(int a) {
        this.address = a;
    }

    /** gets the address.
     * @return the address
     */
    public int getAddress() {
        return this.address;
    }

    /** sets the next block.
     * @param n the next block
     */
    public void setNext(Block n) {
        this.next = n;
    }

    /** gets the next block.
     * @return the next block
     */
    public Block getNext() {
        return this.next;
    }
    /** compares based on address.
     * @param o a block
     * @return the difference of the addresses
     */
    public int compToAddress(Block o) {
        return (this.address - o.address);
    }

    @Override
    public int compareTo(Block o) {
        if (this.equals(o)) {
            return 0;
        }
        int ret = this.size - o.size;
        if (ret >= 0) {
            return ret + 1;
        }
        return ret;
    }

    /**returns size if hashSize is true else address.
     * @return int size or address
     */
    @Override
    public int hashCode() {
        if (this.hashSize) {
            return this.size;
        } else {
            return this.address;
        }
    }

    /** hashes by size.
     * @param s the size
     */
    public void hashBySize(boolean s) {
        this.hashSize = s;
    }

    /** overrides the toString method.
     * @return string representation of block
     */
    @Override
    public String toString() {
        return ("address: " + this.address + " size: "
                + this.size + " request number: " + this.reqNum + "\n");
    }
}
