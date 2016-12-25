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

/**class for hashtable generic is comparable.
 * @param <T> comparable object
 */
public class Hashtable<T extends Comparable<? super T>>
    implements MemoryStructure<T> {

    /**Magic Numbers.*/
    private final int twentyfive = 25;
    /**Magic Numbers.*/
    private final double threeforths = .75;
    /** Node used to store the data.*/
    private final class Node {
        /** next node in the linked list.*/
        private Node next;
        /** data held in the node.*/
        private T data;
        /** Constructor for the node.
         * @param d data stored in the node
         * @param n next node in the linked list
         */
        private Node(T d, Node n) {
            this.next = n;
            this.data = d;
        }
    }
    /** memory size of each partition.*/
    private int partitionSize;
    /** table that holds the blocks.*/
    private Object[] table;
    /** size is the number of elements in the hashtable. */
    private int size, memorySize;

    /** constructor for the hashtable.
     * @param tsize total memory
     */
    public Hashtable(int tsize) {
        this.size = 0;
        this.memorySize = tsize;
        this.table = new Object[this.twentyfive];
        this.partitionSize = (int) Math.ceil((double) this.memorySize
                / (double) (this.table.length - 1));
    }

    /** Constructor using the first node to enter into structure.
     * @param tsize total memory
     * @param first node to enter into the structure
     */
    public Hashtable(int tsize, T first) {
        this.size = 0;
        this.memorySize = tsize;
        this.table = new Object[this.twentyfive];
        this.partitionSize = ((int) Math.ceil((double) this.memorySize
                / (double) (this.table.length - 1)));
        this.add(first);
    }

    /** Constructor using an array of objects to enter.
     * @param tsize total memory
     * @param add array to add to the table
     */
    public Hashtable(int tsize, Object[] add) {
        this.size = 0;
        this.memorySize = tsize;
        this.table = new Object[this.twentyfive];
        this.partitionSize = (int) Math.ceil((double) this.memorySize
                / (double) (this.table.length - 1));
    }

    /** adds the node to the structure.
     * @param data node to add to the structure
     * @return best-fit close-fit or worst-fit depending on implementation
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean add(T data) {
        int partition = data.hashCode() / this.partitionSize;
        if (partition > this.table.length - 1) {
            partition = this.table.length - 1;
        }
        Node block = new Node(data, null);
        block.next = (Node) this.table[partition];
        this.table[partition] = (Node) block;
        this.size++;
        if ((double) this.size / (double) this.table.length
                > this.threeforths) {
            doubleTable();
        }
        return true;
    }

    /** removes the node from the structure and returns the node.
     * @param block - compare in structure to block to find one bigger based on
     * best-fit close-fit or worst-fit depending on implementation
     * @return node that is best-fit close-fit or worst-fit depending
     * on implementation
     */
    @SuppressWarnings("unchecked")
    @Override
    public T delete(T block) {
        int find = block.hashCode() / this.partitionSize;
        if (find > this.table.length - 1) {
            find = this.table.length - 1;
        }
        Node temp = (Node) this.table[find];
        if (temp != null) {
            if (temp.data != null && temp.data.hashCode() >= block.hashCode()) {
                this.table[find] = temp.next;
                this.size--;
                return temp.data;
            } else {
                if (temp != null) {
                    while (temp.next != null) {
                        if (temp.next.data.hashCode() >= block.hashCode()) {
                            Node place = temp.next;
                            temp.next = temp.next.next;
                            this.size--;
                            return place.data;
                        } else {
                            temp = temp.next;
                        }
                    }
                }
            }
        }
        for (int i = (find + 1); i < this.table.length; i++) {
            if (this.table[i] != null) {
                temp = (Node) this.table[i];
                this.table[i] = temp.next;
                this.size--;
                return temp.data;
            }
        }
        return null;
    }

    /** doubles the size of the table when the hash table gets oversaturated.
     */
    @SuppressWarnings("unchecked")
    public void doubleTable() {
        Object[] temp = this.table.clone();
        this.table = new Object[temp.length * 2];
        this.partitionSize = (int) Math.ceil((double) this.memorySize
                / (double) (this.table.length - 1));
        Node check;
        for (int i = 0; i < temp.length; i++) {
            check = (Node) temp[i];
            while (check != null) {
                this.size--;
                this.add((T) (((Node) check).data));
                check = check.next;
            }
        }
    }

    /** used to find the number of elements in the class.
     * @return size number of elements in table
     */
    public int size() {
        return this.size;
    }

    /** overrides isEmpty().
     * @return boolean true if empty else false
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /** returns the size of the table.
     * @return size of the table
     */
    public int tableSize() {
        return this.table.length;
    }

    /** returns the total size of memory.
     * @return int memorySize total memory
     */
    public int memTotal() {
        return this.memorySize;
    }

    /** Sorts the hashtable based on the compareto Method.
     * @return Object array of the sorted values
     */
    @SuppressWarnings("unchecked")
    public Object[] bucketSort() {
        Object[] temp = this.toArray();
        int curr;
        Object sw;
        for (int i = 0; i < temp.length; i++) {
            curr = i;
            while (((T) temp[curr]).compareTo((T) temp[curr - 1]) < 0) {
                sw = temp[curr];
                temp[curr] = temp[curr - 1];
                temp[curr - 1] = sw;
                curr--;
            }
        }
        return temp;
    }

    /** clears the class and reinitializes everything.
     */
    public void clear() {
        this.size = 0;
        this.table = new Object[this.twentyfive];
        this.partitionSize = (int) Math.ceil((double) this.memorySize
                / (double) (this.table.length - 1));
    }

    /** returns an array of the objects stored in the hashtable.
     * @return Object array of the data stored in the hashtable.
     */
    @SuppressWarnings("unchecked")
    public Object[] toArray() {
        if (this.isEmpty()) {
            return null;
        }
        Object[] temp = this.table.clone();
        Object[] re = new Object[this.size];
        int place = 0;
        Node n = null;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] != null) {
                n = (Node) temp[i];
                re[place++] = n.data;
                while (n.next != null) {
                    re[place++] = n.next.data;
                    n = n.next;
                }
            }
        }
        return re;
    }
}
