package cs206c;
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int key = Integer.parseInt(in.nextLine());
		int n = Integer.parseInt(in.nextLine());
		int[] A = new int[n];
		for(int i = 0; i < n ; i++) {
			A[i] = Integer.parseInt(in.nextLine());
		}
		
		int len = LongestSubarray(A,key);
		System.out.println(len);
	}
	

	
	public static int LongestSubarray(int[] A, int key) {
		int n = A.length;
		int t = 0;
		int[][] sumarray = new int[n][n];
		for(int i = 0; i<n; i++){
			for(int j = i; j<n; j++){
				if(j==0)
					sumarray[i][j] = A[j];
				else 
					sumarray[i][j] = sumarray[i][j-1]+A[j];
				
				if(sumarray[i][j] <= key){
					t = Math.max(t, j-i+1);
				}
			}
		}
		
		return t;
	}
}