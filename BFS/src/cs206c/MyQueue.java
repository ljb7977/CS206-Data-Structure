package cs206c;

import java.util.*;
import cs206c.Element;

class MyQueue {
	private Element first;
	private Element last;
	private int size;

	// Define your own member variables to store elements, ...
	public MyQueue() {
		// Constructor
		first = null;
		last = null;
		size = 0;
	}

	public boolean isEmpty() {
		// implement here
		if (getFirst() != null)
			return false;
		return true;
	}

	public Element getFirst() {
		return first;
	}

	public void enqueue(Element e) { // do not modify the function declaration
		if (isEmpty()) {
			first = e;
			last = first;
		} else {
			last.next = e;
			last = e;
		}
		size++;
	}

	public Element dequeue() { // do not modify the function declaration
		if (this.isEmpty()) return null;
		Element t = first;
		first = first.next;
		size--;
		return t;
	}

	public Element peek() { // do not modify the function declaration
		if (this.isEmpty()) return null;
		return first;
	}

	public int size() { // do not modify the function declaration
		return size;
	}
}
