package cs206c;

import java.util.*;

public class Node {
	// Freely declare your own member variables

	public double prob;
	public double cProb;
	
	public Node parent = null;
	public List<Node> childrenList;
	
	public String name;

	public Node(String name){
		prob = 0;
		cProb = 0;
		this.name = name;
		childrenList = new ArrayList<Node>();
	}

	public Node(String name, double nodeProb){
		prob = nodeProb;
		cProb = 0;
		this.name = name;
		childrenList = new ArrayList<Node>();
	}

	public String name() {
		return this.name;
	}

	public Node parent() {
		return this.parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> children() {
		return this.childrenList;
	}

	public void addChild(Node newChild) {
		this.childrenList.add(newChild);
	}

	public void removeChild(Node childToRemove) {
		this.childrenList.remove(childToRemove);
	}

	public int numChildren() {
		return this.childrenList.size();
	}

	public boolean isLeaf() {
		if(numChildren() == 0) return true;
		return false;
	}

	public String toString() {
		// For debugging purpose
		// Grader will not use this function
		// Feel free to extend
		return "[Name: " + this.name() + "]";
	}
}
