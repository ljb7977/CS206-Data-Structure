package cs206c;

import java.util.List;
import java.util.ArrayList;

public class Person {
	public String name;
	public List<Person> children;
	public Person parent;

	public Person(String name){
		this.name = name;
		this.children = new ArrayList<Person>();
	}

	public Person parent() {
		return parent;
	}

	public List<Person> children() {
		return children;
	}

	public int numChildren() {
		return children.size();
	}

	public String toString() {
		// Feel free to extend this function
		return "[Name: " + this.name + "]";
	}
}
