package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	private int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		size = 0;
		head = new LLNode<>(null);
		tail = new LLNode<>(null);
		head.next = tail;
		tail.prev = head;
	}

	private LLNode<E>getIndexedNode(int index){
        LLNode dataNode;
        if( (float)index / (float)size > .5 ){
            dataNode = tail.prev;
            for (int i = size - 1; i > index; i--)
                dataNode = dataNode.prev;
        } else {
            dataNode = head.next;
            for (int i = 0; i < index; i++)
                dataNode = dataNode.next;
        }
        return dataNode;
    }

    /**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) {
		LLNode dataNode = new LLNode<>(element);
        dataNode.setIndexes( tail , tail.prev );
        tail.prev.next = dataNode;
        tail.prev = dataNode;
        size++;
        return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) {
        if(index > size || size == 0 || index < 0)
            throw new IndexOutOfBoundsException("Data Index not in Linked List");
        LLNode dataNode = head.next;
        for(int i = 0; i < index; i++)
            dataNode = dataNode.next;
        return (E) dataNode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) {
        if (index >= size) {
            while (index > size)
                add(null);
            add(element);
        } else {
            LLNode tmpDataNode = getIndexedNode(index);
            LLNode dataNode = new LLNode<>(element);
            dataNode.setIndexes( tmpDataNode , tmpDataNode.prev );
            tmpDataNode.prev.next = dataNode;
            tmpDataNode.prev = dataNode;
            size++;
        }
	}


	/** Return the size of the list */
	public int size() {
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {
        if(index > size)
            throw new IndexOutOfBoundsException("Data Index not in Linked List");
        LLNode dataNode = getIndexedNode(index);
        dataNode.prev.next = dataNode.next;
        dataNode.next.prev = dataNode.prev;
        size--;
		return (E) dataNode.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) {
        if(index > size)
            throw new IndexOutOfBoundsException("Data Index not in Linked List");
        LLNode dataNode = getIndexedNode(index);
        E data = (E)dataNode.data;
        dataNode.data = element;
		return data;
	}   
}

class LLNode<E> 
{
    LLNode<E> prev;
    LLNode<E> next;
	E data;

    public void setIndexes(LLNode<E> next, LLNode<E> prev ){
        this.next = next;
        this.prev = prev;
    }

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
