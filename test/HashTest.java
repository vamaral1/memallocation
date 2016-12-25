package test; /**
 * Data Structures
 * EN.600.226
 * HW# 8B
 * 11/20/2012
 *
 * Victor Amaralb
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

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.Block;
import src.Hashtable;


public class HashTest {
	Hashtable<Block> tone;
	
	@Before
	public void setupHash() {
		 tone = new Hashtable<Block>(100, new Block(0, 100));		
	}
	
	@Test
	public void testAdd() {
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 4)));
		Assert.assertEquals(true, tone.add(new Block(0, 8)));
		Assert.assertEquals(true, tone.add(new Block(0, 6)));
		Assert.assertEquals(true, tone.add(new Block(0, 2)));
		Assert.assertEquals(true, tone.add(new Block(0, 10)));
	}
	
	@Test
	public void testSize() {
		
	}
	
	@Test
	public void testTable() {
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 4)));
		Assert.assertEquals(true, tone.add(new Block(0, 8)));
		Assert.assertEquals(true, tone.add(new Block(0, 6)));
		Assert.assertEquals(true, tone.add(new Block(0, 2)));
		Assert.assertEquals(true, tone.add(new Block(0, 10)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 4)));
		Assert.assertEquals(true, tone.add(new Block(0, 8)));
		Assert.assertEquals(true, tone.add(new Block(0, 6)));
		Assert.assertEquals(true, tone.add(new Block(0, 2)));
		Assert.assertEquals(true, tone.add(new Block(0, 10)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 20)));
		Assert.assertEquals(true, tone.add(new Block(0, 4)));
		Assert.assertEquals(true, tone.add(new Block(0, 8)));
		Assert.assertEquals(true, tone.add(new Block(0, 6)));
		Assert.assertEquals(true, tone.add(new Block(0, 2)));
		Assert.assertEquals(true, tone.add(new Block(0, 10)));
		Assert.assertEquals(50, tone.tableSize());
	}
	
	@Test
	public void testDelete() {
		Assert.assertEquals(new Block(0, 100).getSize(), tone.delete(new Block(0, 10)).getSize());
		Assert.assertEquals(null, tone.delete(new Block(0, 10)));
		Assert.assertEquals(true, tone.add(new Block(0, 100)));
		Assert.assertEquals(new Block(0, 100).getSize(), tone.delete(new Block(0, 20)).getSize());
		Assert.assertEquals(null, tone.delete(new Block(0, 19)));
		Assert.assertEquals(true, tone.add(new Block(0, 18)));
		Assert.assertEquals(true, tone.add(new Block(0, 19)));
		Assert.assertEquals(true, tone.add(new Block(0, 19)));
		Assert.assertEquals(new Block(0, 19).getSize(), tone.delete(new Block(0, 18)).getSize());
		Assert.assertEquals(new Block(0, 19).getSize(), tone.delete(new Block(0, 19)).getSize());
		Assert.assertEquals(null, tone.delete(new Block(0, 19)));
		Assert.assertEquals(new Block(0, 18).getSize(), tone.delete(new Block(0, 1)).getSize());
		Assert.assertEquals(null, tone.delete(new Block(0, 1)));
		//assertEquals(new src.Block(0, 20).getSize(), tone.delete(new src.Block(0, 20)).getSize());
		//assertEquals(null, tone.delete(new src.Block(0, 20)));
	}

}
