package cs206c;
import java.io.*;
import java.util.*;

// our main class becomes a file but the main method is still found
public class Main
{
    public static void main(String[] args){
        int[] candidateTimes = {0,10,50,100,120,200};
        System.out.println(balanceSchedule(candidateTimes, 4));
        int[] candidateTimes2 = { 50, 100, 120, 140 };
        System.out.println(balanceSchedule(candidateTimes2, 2));
    }
    public static int balanceSchedule(int[] candidates, int count) {
        // Implement here
    	Arrays.sort(candidates);
    	int n = candidates.length, answer= 0;

    	if(count == 2){
    		return candidates[n-1]-candidates[0];
    	}

    	ArrayList<Integer> pivot = new ArrayList<Integer>(); //ºÐÇÒ pivot
    	pivot.add(0);
    	for(int i = 1; i <= n-count+1; i++){
    		pivot.add(i);
    		answer = Math.max(answer, recursion(candidates, count, 1, pivot));
    		pivot.remove(1);
    	}
    	return answer;
    }
    
    public static int recursion(int[] candidates, int count, int depth, ArrayList<Integer> pivot){
    	if(depth >= count-2)
    		return evalMin(candidates, pivot);
    	int n = candidates.length, answer = 0;
    	for(int i = pivot.get(pivot.size()-1)+1; i<=n-count+depth+1; i++){
    		pivot.add(i);
    		answer = Math.max(answer, recursion(candidates, count, depth+1, pivot));
    		pivot.remove(pivot.size()-1);
    	}
    	return answer;
    }
    
    public static int evalMin(int[] candidates, ArrayList<Integer> pivot){
    	pivot.add(candidates.length-1);
    	int answer = 2147483647;
    	
    	for(int i = 1; i<pivot.size(); i++){
    		answer = Math.min(answer, candidates[pivot.get(i)]-candidates[pivot.get(i-1)]);
    	}
    	pivot.remove(pivot.size()-1);
    	return answer;
    }
}