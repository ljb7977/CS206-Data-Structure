package cs206c;

import java.util.List;
import java.util.ArrayList;

public class Node {
	public String name;
	public Node left;
	public Node right;
	public Node parent;

	public Node(String name){
		this.name = name;
	}

	public Node(String name, Node left, Node right){
		this.name = name;
		this.left = left;
		this.right = right;
	}

	public Node parent() {
		return this.parent;
	}

	public Node left() {
	  return this.left;
	}

	public Node right() {
	  return this.right;
	}

	public String toString() {
		// Feel free to extend this function
		return "[Name: " + this.name + "]";
	}
}
