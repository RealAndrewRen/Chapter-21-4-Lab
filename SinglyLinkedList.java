package singlyLinkedListLab;

// Implements a singly-linked list.

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList implements Iterable<Object>
{
  private ListNode head;
  private int nodeCount;

  // Constructor: creates an empty list
  public SinglyLinkedList()
  {
    head = null;
    nodeCount = 0;
  }

  // Constructor: creates a list that contains
  // all elements from the array values, in the same order
  public SinglyLinkedList(Object[] values)
  {
    ListNode tail = null;
    for (Object value : values) // for each value to insert
    {
      ListNode node = new ListNode(value, null);
      if (head == null)
        head = node;
      else
        tail.setNext(node);
      tail = node;    // update tail
    }

    nodeCount = values.length;
  }

  // Returns true if this list is empty; otherwise returns false.
  public boolean isEmpty()
  {
	  return head == null;
  }

  // Returns the number of elements in this list.
  public int size()
  {
    return nodeCount;
  }
  // Returns true if this list contains an element equal to obj;
  // otherwise returns false.
  public boolean contains(Object obj)
  {
    Iterator<Object> iterator = this.iterator();
    while (iterator.hasNext()) {
		if (obj == (Object) iterator.next()) {
			return true;
		}
	}
    return false;
  }

  // Returns the index of the first element in equal to obj;
  // if not found, returns -1.
  public int indexOf(Object obj)
  {
    Iterator<Object> iterator = this.iterator();
    int index = 0;
    while (iterator.hasNext()) {
    	index++;
		if (obj == (Object) iterator.next()) {
			return index;
		};		
	}
    return -1;
  }

  // Adds obj to this collection.  Returns true if successful;
  // otherwise returns false.
  public boolean add(Object obj)
  {
    ListNode node = new ListNode(obj, null);
    if (head == null) {
    	head = node;
    }
    else {
    	ListNode current = head;
    	while (current.getNext() != null) {
			current = current.getNext();
		}
    	current.setNext(node);
    	nodeCount++;
	}
    return true;
  }

  // Removes the first element that is equal to obj, if any.
  // Returns true if successful; otherwise returns false.
  public boolean remove(Object obj)
  {
    if (head == null) {
    	return false;
    }
    if (head.getValue().equals(obj)) {
    	head = head.getNext();
    	nodeCount--;
    	return true;
    }
    else {
		ListNode current = head;
		while (current.getNext() != null && !obj.equals(current.getNext().getValue())) {
			current = current.getNext();
		}
		if (current.getNext() == null) {
			return false;
			}
		current.setNext(current.getNext().getNext());
		nodeCount--;
		return true;
    }
  }
  

  // Returns the i-th element.
  public Object get(int i)
  {
	if (head == null) {
		throw new NullPointerException();
	}
	else if (i < 0 || i >= nodeCount) {
		throw new IndexOutOfBoundsException();
	}
	ListNode current = head;
	for (int j = 0; j < i; j++) {
		current = current.getNext();
	}
	return current.getValue();
	}

  // Replaces the i-th element with obj and returns the old value.
  public Object set(int i, Object obj)
  {
	if (head == null) {
		throw new NullPointerException();
	}
	else if (i < 0 || i >= nodeCount) {
		throw new IndexOutOfBoundsException();
	}
	ListNode current = head;
	for (int j = 0; j < i; j++) {
		current = current.getNext();
	}
	Object temp = current.getValue();
	current.setValue(obj);
	return temp;
  }

  // Inserts obj to become the i-th element. Increments the size
  // of the list by one.
  public void add(int i, Object obj)
  {
		ListNode node = new ListNode(obj);
		if (head == null) {
			head = node;
		}
		else if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = head;
		for (int j = 0; j < i - 1; j++) {
			current = current.getNext();
		}
		node.setNext(current.getNext());
		current.setNext(node);
		nodeCount++;
  }

  // Removes the i-th element and returns its value.
  // Decrements the size of the list by one.
  public Object remove(int i)
  {
		if (head == null) {
			throw new NullPointerException();
		}
		else if (i < 0 || i >= nodeCount) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current = head;
		for (int j = 0; j < i - 1; j++) {
			current = current.getNext();
		}
		Object obj = current.getNext().getValue();
		current.setNext(current.getNext().getNext());
		nodeCount--;
		return obj;
  }

  // Returns a string representation of this list.
  public String toString()
  {
	  StringBuilder sb = new StringBuilder();
	  sb.append("[");
	  ListNode current = head;
	  while (current != null) {
		  sb.append(current.getValue());
		  if (current.getNext() != null) {
			  sb.append(", ");
			  }
		  current = current.getNext();
		  }
	  sb.append("]");
	  return sb.toString();
	  }

  // Returns an iterator for this collection.
  public Iterator<Object> iterator()
  {
    return new SinglyLinkedListIterator(head);
  }

private class SinglyLinkedListIterator implements Iterator<Object>
{
  private ListNode nextNode;

  // Constructor
  public SinglyLinkedListIterator(ListNode head)
  {
    nextNode = head;
  }

  public boolean hasNext()
  {
    return nextNode != null;
  }

  public Object next()
  {
    if (nextNode == null)
      throw new NoSuchElementException();

    Object value = nextNode.getValue();
    nextNode = nextNode.getNext();
    return value;
  }

  // Not implemented.
  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}
}

