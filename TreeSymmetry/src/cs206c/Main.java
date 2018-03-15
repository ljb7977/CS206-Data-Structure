package cs206c;

import cs206c.Node;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) {
		// 1. Binary tree
		//      A
		//    /   \
		//   B     B
		//  / \   / \
		// C   D D   C
		//    /   \
		//   E     E
		Node lb = new Node("B", new Node("C"), new Node("D", new Node("E"), null));
		Node rb = new Node("B", new Node("D", null, new Node("E")), new Node("C"));
		Node root = new Node("A", lb, rb);

		// 2. Check if the binary tree is symmetric
		if (isTreeSymmetric(root)) {
			System.out.println("The tree is symmetric");
		} else {
			System.out.println("The tree is not symmetric");
		}
	}

	// Problem 2
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static boolean isTreeSymmetric(Node root) {
		// Implement here
		Stack<String> s = new Stack<String>();
		inorder(root.left, s);
		return inorderCheck(root.right, s);
	}
	
	static void inorder(Node root, Stack<String> s) {
		if(root == null) return;
		inorder(root.left, s);
		s.push(root.name);
		inorder(root.right, s);
	}
	
	static boolean inorderCheck(Node root, Stack<String> s) {
		boolean t = true;
		if(root == null) return true;
		t = t && inorderCheck(root.left, s);
		if(root.name != s.pop()) return false;
		t = t && inorderCheck(root.right, s);
		return t;
	}

}
