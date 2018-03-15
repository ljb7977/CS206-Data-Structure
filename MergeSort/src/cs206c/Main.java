package cs206c;
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		LinkedList<Integer> input = readInputs(in);
		InPlaceMergeSort(input,0,input.size());
		for(int i = 0 ; i < input.size(); i++) 
			System.out.println(input.get(i));
	}
	
	public static LinkedList<Integer> readInputs(Scanner in) {
		int n =in.nextInt();
		LinkedList<Integer> integerEntry = new LinkedList<Integer>();
		for(int i =0; i<n; i++){
			int t = in.nextInt();
			integerEntry.add(t);
		}
		return integerEntry;
	}
	
	public static void MergeTwoSortedSubarrays(LinkedList<Integer> input, int A_start, int A_end , int B_start, int B_end) {
		int i = A_start, j = B_start, t = A_start;
		while(i<A_end && j<B_end){
			if(input.get(i) < input.get(j)){
				input.add(t, input.remove(i));
				t++;
				i++;
			}
			else {
				input.add(t, input.remove(j));
				t++;
				j++;
				i++;
				A_end++;
			}
		}
		
		if(i == A_end)
			while(j < B_end){
				input.add(t, input.remove(j));
				t++;
				j++;
			}
			
		else if (j == B_end)
			while(i < A_end){
				input.add(t, input.remove(i));
				t++;
				i++;
			}
			
		return;
	}

	public static void InPlaceMergeSort(LinkedList<Integer> input, int start, int end) {

		if(end-start <= 1) return;
		
		InPlaceMergeSort(input, start, (start+end)/2);
		InPlaceMergeSort(input, (start+end)/2, end);
		MergeTwoSortedSubarrays(input, start, (start+end)/2, (start+end)/2, end);
		return;
	}
}