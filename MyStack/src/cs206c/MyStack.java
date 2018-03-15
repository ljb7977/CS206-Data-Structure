package cs206c;

import java.util.*;
import cs206c.Element;

class MyStack {
  // Define your own member variables to store elements, ...
	private ArrayList<Element> list;
	
	public MyStack() {
    // Constructor
		list = new ArrayList<Element>();
	}

	public void push(Element e) { // do not modify the function declaration
		list.add(e);
	}

	public Element pop() { // do not modify the function declaration
		if(this.isEmpty()) return null;
		int len = this.size();
		Element temp = this.top();
		list.remove(len-1);
		return temp;
	}

	public Element top() { // do not modify the function declaration
		if(this.isEmpty()) return null;
		int len = this.size();
		Element temp = list.get(len-1);
		return temp;
	}

	public int size() { // do not modify the function declaration
		return list.size();
	}

	public boolean isEmpty() { // do not modify the function declaration
		return list.isEmpty();
	}
}
