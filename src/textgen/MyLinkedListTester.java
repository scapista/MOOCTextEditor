/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++) {
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());

        try {
            longerList.get(-1);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {

        }
        try {
            longerList.get(LONG_LIST_LENGTH);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {

        }

	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd(){

        assertEquals("Initial size check is correct",0,emptyList.size());
        emptyList.add(1);
        assertEquals("Initial size check is correct",1,emptyList.size());
        assertEquals("Initial get check is correct",Integer.valueOf(1),emptyList.get(0));
        emptyList.add(1000000000);
        assertEquals("Initial size check is correct",2,emptyList.size());
        assertEquals("Initial get check is correct",Integer.valueOf(1000000000),emptyList.get(1));
        emptyList.add(23121345);
        assertEquals("Initial size check is correct",3,emptyList.size());
        assertEquals("Initial get check is correct",Integer.valueOf(23121345),emptyList.get(2));

	}

	
	/** Test the size of the list */
	@Test
	public void testSize() {
        assertEquals("Initial size check is correct",2,shortList.size());
        assertEquals("Initial size check is correct",0,emptyList.size());
        assertEquals("Initial size check is correct",10,longerList.size());
        assertEquals("Initial size check is correct",3,list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex() {
		shortList.add(1,"C");
		shortList.add(1,"D");
        assertEquals("Initial size check is correct",4,shortList.size());


        while( shortList.get(shortList.size() - 1 ) == null || !shortList.get(shortList.size() - 1 ).equals("C"))
            shortList.remove(shortList.size() - 1 );
        assertEquals("Initial size check is Incorrect",3,shortList.size());
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet() {
        assertEquals("Initial size check is Incorrect",Integer.valueOf(0),longerList.get(0));
		longerList.set(1,1);
        assertEquals("Initial size check is Incorrect",Integer.valueOf(1),longerList.get(1));
		longerList.set(2,-1000000000);
        assertEquals("Initial size check is Incorrect",Integer.valueOf(-1000000000),longerList.get(2));
        try {
            longerList.set(-1,-23121345);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {

        }
        try {
            longerList.set(longerList.size(),-23121345);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {

        }

	}
   @Test
    public void addThenRemove() {
        assertEquals("Initial size check is Incorrect",0,emptyList.size());
        emptyList.add(1);
        assertEquals("Size check after Add is Incorrect 1",1,emptyList.size());
        assertEquals("Value check is Incorrect 1",Integer.valueOf(1),emptyList.get(0));
        emptyList.remove(0);
        assertEquals("Size check after Remove is Incorrect",0,emptyList.size());
        try {
            emptyList.get(0);
            fail("Check out of bounds");
        }
        catch (IndexOutOfBoundsException e) {

        }
        emptyList.add(5);
        assertEquals("Size check after Add Remove is Incorrect 2",1,emptyList.size());
        assertEquals("Initial size check is Incorrect 2",Integer.valueOf(5),emptyList.get(0));
    }
	
}
