package cs206c;

import cs206c.Node;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) {

		// Tree is given
		Node A = new Node("A", 1);
		Node B = new Node("B");
		Node C = new Node("C");
		B.prob = 0.3;
		C.prob = 0.7;
		Node D = new Node("D", 0.4);
		Node E = new Node("E", 0.1);
		Node F = new Node("F", 0.5);

		A.addChild(B);
		A.addChild(C);
		C.addChild(D);
		C.addChild(E);
		C.addChild(F);

		// 2. Calculate the probability
		Node root = calculateProbability(A);

		// 3. Get the list of leaves
		List<Node> leaves = getLeaves(root);
		for (Node leaf : leaves) {
		  System.out.println(leaf.name() + " " + leaf.cProb);
		}
	}

	// Problem 2
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static Node calculateProbability(Node root) {
		// Implement here
		dfsProb(root, 1.0);
		return root;
	}
	
	public static void dfsProb(Node root, double parentprob){
		if(root == null) return;
		root.cProb = root.prob*parentprob;
		for(Node child: root.childrenList){
			dfsProb(child, root.cProb);
		}
	}

	// Problem 3
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static List<Node> getLeaves(Node root) {
		// Implement here
		List<Node> leaves = new ArrayList<Node>();
		dfsIsRoot(root, leaves);
		return leaves;
	}
	
	public static void dfsIsRoot(Node root, List<Node> leaves){
		if(root == null) return;
		if(root.isLeaf() == true) leaves.add(root);
		for(Node child: root.childrenList){
			dfsIsRoot(child, leaves);
		}
	}
}
