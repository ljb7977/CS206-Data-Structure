package cs206c;

import cs206c.Person;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) {
		// 1. Reads a set of edges and return the list of interconnected `Person`s
		List<Person> people = readRumorNetwork();

		// Check your list of people
		if (people != null) {
  		for (Person person : people) {
  		  System.out.println(person);
  		}
  	}

		// 2. `checkBinaryTree`: Return `true` when the constructed network is the binary tree
		boolean isBinaryTree = checkBinaryTree(people);
		if (isBinaryTree) {
		  System.out.println("The rumor network is valid");
		} else {
		  System.out.println("The rumor network is corrupted");
		}

		// 3. Return the `Person` who is responsible for the rumor spread
		if (isBinaryTree) {
			Person source = findSource(people);

			if (source != null) {
				System.out.println(source.name);
			}
		}
	}

	// Problem 1
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static List<Person> readRumorNetwork() {
		List<Person> people = new ArrayList<Person>();

		// Implement here
		Scanner scn = new Scanner(System.in);
		while(scn.hasNext()){
			String s = scn.nextLine();
			String t[] = s.split(" ");
			String parentname = t[0];
			String childname = t[1];
			
			//corruption check, is child a child of another person?
			for(Person p: people){
				for(Person child: p.children){
					if(child.name.equals(childname)){
						if(!p.name.equals(parentname)){
							return null;
						}
					}
				}
			}

			Person parent = new Person(parentname);
			Person child = new Person(childname);
			boolean parentExists = false;
			boolean childExists = false;
			
			for(Person p: people){
				if(p.name.equals(parentname)){
					parentExists = true;
					parent = p;
				}
				if(p.name.equals(childname)){
					childExists = true;
					child = p;
				}
			}
			
			parent.children.add(child);
			child.parent = parent;
			
			if(!parentExists) people.add(parent);
			if(!childExists) people.add(child);
			
		}
		scn.close();
		return people;
	}

	// Problem 2
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static boolean checkBinaryTree(List<Person> people) {
		// Implement here
		if(people == null || people.isEmpty()) return false;
		
		//finding root
		
		for(Person p: people){
			if(p.children.size() >= 3){
				return false;
			}
		}
		if(checkCycle(people) != people.size()) return false;

		return true;
	}
	
	public static int checkCycle(List<Person> people){
		ArrayList<Person> visited = new ArrayList<Person>();
		Person p = people.get(0);
		while(p.parent() != null){
			p = p.parent();
		}
		//now p is root.
		
		Stack<Person> s = new Stack<Person>();
		s.push(p);
		
		while(!s.isEmpty()){
			Person parent = s.pop();
			visited.add(parent);
			for(Person child : parent.children){
				if(findNode(visited, child.name)) return -1;
				s.push(child);
			}
		}
		return visited.size();
	}
	
	public static boolean findNode(List<Person> people, String name){
		for(Person p: people){
			if(p.name.equals(name)) return true;
		}
		return false;
	}

	// Problem 3
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static Person findSource(List<Person> people) {
		// Implement here
		Person p = people.get(0);
		while(p.parent() != null){
			p = p.parent();
		}
		return p;
	}
}
