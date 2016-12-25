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

/**class to allocate memory.
 * @param <T> extends the interface
 */
public class Allocator<T extends MemoryStructure<Block>>
    implements MemAllocation {

    /**the head of the list.*/
    private Block head;
    /**the request number.*/
    private int reqNum;
    /**the type of structure.*/
    private T structure;

    /** Constructor.
     * @param struct the data strucutre
     */
    public Allocator(T struct) {
        this.structure = struct;
        this.head = new Block(0, 0);
        this.reqNum = 1;
        this.head.setNext(null);
    }
    /** Allocate the memory.
     * @param size the size
     * @return true or false
     */
    public int alloc(int size) {
        if (size <= 0) {
            return -1;
        }
        Block n = new Block(this.reqNum++, size);
        Block temp = this.structure.delete(n);
        if (temp == null) {
            return -1;
        }
        n.setAddress(temp.getAddress());
        temp.setSize(temp.getSize() - size);
        temp.setAddress(temp.getAddress() + size);
        if (temp.getSize() > 0) {
            this.structure.add(temp);
        }
        n.setNext(this.head.getNext());
        this.head.setNext(n);
        return n.getAddress();
    }

    /** Deallocate the memory.
     * @param r the request number
     * @return true if succeeded else false
     */
    public boolean deAlloc(int r) {
        Block temp = this.head;
        while (temp.getNext() != null && temp.getNext().getReqNumber()
                > r) {
            temp = temp.getNext();
        }
        if (temp.getNext() == null || temp.getNext().getReqNumber() != r) {
            return false;
        }
        Block addto = temp.getNext();
        addto.setReqNumber(-1);
        temp.setNext(addto.getNext());
        addto.setNext(null);
        if (addto.getSize() > 0) {
            this.structure.add(addto);
        }
        return true;
    }

    /** Defragment using bucket sort.
     * @return int size of structure
     * @param b the block
     */
    public boolean defrag(Block[] b) {
        this.reqNum--;
        if (b == null || b.length == 0) {
            return false;
        }
        if (b.length < 2) {
            if (b[0].getSize() <= 0) {
                this.structure.clear();
            }
            return false;
        }
        Block curr = b[0];
        int place = 0;
        boolean defrag = false;
        Block[] def = new Block[b.length];
        for (int i = 1; i < b.length; i++) {
            if (b[i] != null) {
                if (curr.getAddress() + curr.getSize() == b[i].getAddress()) {
                    defrag = true;
                    curr.setSize(curr.getSize() + b[i].getSize());
                    b[i] = null;
                } else {
                    b[place] = curr;
                    curr = b[i];
                    place = i;
                }
            }
        }
        place = 0;
        checkstyle(b, place, def);
        return defrag;
    }
    /** cyclomatic complexity.
     * @param b block
     * @param place place
     * @param def block
     */
    public void checkstyle(Block[] b, int place, Block[] def) {
        for (int i = 0; i < b.length; i++) {
            if (b[i] != null) {
                def[place++] = b[i];
            }
        }
        this.structure.clear();
        for (int i = 0; i < def.length; i++) {
            if (def[i] != null) {
                if (def[i].getSize() > 0) {
                    this.structure.add(def[i]);
                }
            }
        }
    }

    /** sorts the array with a bucketsort based on the address.
     * @return block array of the blocks in sorted order
     */
    @Override
    public Block[] bucket() {
        Sorter sort = new Sorter();
        Object[] st = this.structure.toArray();
        if (st == null) {
            return null;
        }
        Block[] temp = new Block[st.length];
        for (int i = 0; i < st.length; i++) {
            temp[i] = (Block) st[i];
        }
        return sort.bucketsort(this.structure.memTotal(), temp);
    }

    /** sorts the array with quicksort based on address.
     * @return blocks sorted according to address
     */
    @Override
    public Block[] quick() {
        Sorter sort = new Sorter();
        Object[] st = this.structure.toArray();
        if (st == null) {
            return null;
        }
        Block[] temp = new Block[st.length];
        for (int i = 0; i < st.length; i++) {
            temp[i] = (Block) st[i];
        }
        return sort.quicksort(temp);
    }
    /** checks to see if the structure is empty.
     * @return boolean whether it is empty or not
     */
    public boolean structureEmpty() {
        return this.structure.isEmpty();
    }
}
