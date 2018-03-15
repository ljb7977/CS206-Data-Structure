package cs206c;

import cs206c.Node;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) {

		// Tree is given
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		Node F = new Node("F");

		A.addChild(B);
		A.addChild(C);
		C.addChild(D);
		C.addChild(E);
		C.addChild(F);

		// 2. Calculate the probability
		Node root = receiveCumulativeProbs(A);

		// 3. Hack the Pachinko!
		Node hackedRoot = calculateProbability(root);
		recursivelyPrintNode(hackedRoot);
	}

	// Problem 2
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static Node receiveCumulativeProbs(Node root) {
		// Implement here
		List<Node> nodearray = new ArrayList<Node>();
		Scanner scn = new Scanner(System.in);
		while(scn.hasNext()){
			String s = scn.nextLine();
			String[] t = s.split(" ");
			Node temp = new Node(t[0]);
			temp.cProb = Double.parseDouble(t[1]);
			nodearray.add(temp);
		}
		scn.close();
		for(Node n: nodearray){
			dfscProbSubst(root, n);
		}
		return root;
	}
		
	public static void dfscProbSubst(Node root, Node n){
		if(root == null) return;
		if(root.name.equals(n.name)) {
			root.cProb = n.cProb;
		}
		for(Node child: root.childrenList){
			dfscProbSubst(child, n);
		}
	}

	// Problem 3
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static Node calculateProbability(Node root) {
		// Implement here
		calculatecProbability(root);
		calcP(root);
		return root;
	}
	
	public static void calcP(Node root){
		if(root == null) return;
		if(root.isLeaf()) return;
		for(Node child: root.childrenList){
			child.prob = child.cProb/root.cProb;
			calcP(child);
		}
	}
	
	public static double calculatecProbability(Node root){
		double t=0;
		if(root == null) return 0;
		if(root.isLeaf()) return root.cProb;
		for(Node child: root.childrenList){
			t+=calculatecProbability(child);
		}
		root.cProb = t;
		return t;
	}

	public static void recursivelyPrintNode(Node node) {
	  System.out.println(node.name() + " " + node.prob);
	  for (Node child : node.children()) {
	    recursivelyPrintNode(child);
	  }
	}
}
