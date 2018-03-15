package cs206c;
import cs206c.BinaryTree;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        BinaryTree tr = readTree();
        ArrayList<LinkedList<Integer>> result = allSequences(tr);
        for (LinkedList<Integer> l : result){
            for(int elem: l) System.out.print(elem+" ");
            System.out.println("");
        }
    }
    private static BinaryTree readTree() {
        BinaryTree tr = new BinaryTree(2, null, null);
        tr.left = new  BinaryTree(1, null, null);
        tr.right = new  BinaryTree(3, null, null);
        tr.right.right = new BinaryTree(4, null, null);
        return tr;
    }
    public static ArrayList<LinkedList<Integer>> allSequences(BinaryTree tr){
        // implement here
    	
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        ArrayList<BinaryTree> treeList = new ArrayList<BinaryTree>();
        LinkedList<Integer> temp = new LinkedList<Integer>();
        temp.add(tr.value);
        result.add(temp);
		if(tr.left != null) treeList.add(tr.left);
		if(tr.right != null) treeList.add(tr.right);
        
        return bfsPermu(result, treeList);
    }
    
    public static ArrayList<LinkedList<Integer>> bfsPermu(ArrayList<LinkedList<Integer>> list, ArrayList<BinaryTree> treeList){
    	ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
    	if(treeList.isEmpty()) return list;
    	
    	for(BinaryTree child: treeList){
    		ArrayList<BinaryTree> temp = new ArrayList<BinaryTree>();
    		temp.addAll(treeList);
    		temp.remove(child);
    		
    		ArrayList<LinkedList<Integer>> localResult = new ArrayList<LinkedList<Integer>>();
    		for(LinkedList<Integer> l: list){
    			LinkedList<Integer> newResult = new LinkedList<Integer>();
    			newResult.addAll(l);
    			newResult.add(child.value);
    			localResult.add(newResult);
    		}
    		if(child.left != null) temp.add(child.left);
    		if(child.right != null) temp.add(child.right);
    		result.addAll(bfsPermu(localResult, temp));
    	}
    	return result;
    }
    
}