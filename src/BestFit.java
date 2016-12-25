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

//import java.util.Random;
import java.util.TreeSet;


/**creates a BBST with best-fit characteristic.
 * @param <T> comparable
 */
public class BestFit<T extends Comparable<? super T>> extends TreeSet<Block>
    implements MemoryStructure<Block> {
    /**checkstyle.*/
    private static final long serialVersionUID = 1L;
    /**the amount of memory.*/
    private int memory;

    /** constructor.
     * @param size the amount of memory.
     * @param block a block
     */
    public BestFit(int size, Block block) {
        //src.Block b = new src.Block(0, mem, 0);
        this.add(block);
        this.memory = size;
    }
    /** constructor.
     * @param size the amount of memory.
     */
    public BestFit(int size) {
        this.memory = size;
    }

    @Override
    public boolean add(Block block) {
        super.add(block);
        return true;
    }
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }
    @Override
    public Block delete(Block block) {

        if (this.isEmpty()) {
            return null;
        }
        //removing - 1)
        block.setSize(block.getSize() - 1);
        Block b = this.ceiling(block);
        if (b == null) {
            return null;
        }
        //removing + 1)
        block.setSize(block.getSize() + 1);
        super.remove(b);
        return b;
    }
    @Override
    public int size() {
        return super.size();
    }

    @Override
    public String toString() {
        return super.toString();

    }

    @Override
    public Object[] toArray() {
        return super.toArray().clone();
    }

    @Override
    public int memTotal() {
        return this.memory;
    }

    @Override
    public void clear() {
        super.clear();
    }
}