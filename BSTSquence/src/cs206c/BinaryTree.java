package cs206c;
import java.io.*;
import java.util.*;


public class BinaryTree {
   	public int value;
   	public BinaryTree left, right;
   	public BinaryTree(int e, BinaryTree l, BinaryTree r){
        this.value = e;
        this.left = l;
        this.right = r;
   	}
}
