package cs206c;
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int targetPayroll = Integer.parseInt(in.nextLine());
		LinkedList<Integer> salaries = readSalaries(in);
		int maxCap = maxSuitableCap(targetPayroll,salaries);
		System.out.println(maxCap);
	}
	
	public static LinkedList<Integer> readSalaries(Scanner in) {
		LinkedList<Integer> salaries = new LinkedList<Integer>();
		int n = in.nextInt();
		for(int i = 0; i<n; i++)
			salaries.add(in.nextInt());
		
		return salaries;
	}
	
	public static int maxSuitableCap(int targetPayroll, LinkedList<Integer> salaries) {
		Collections.sort(salaries);
		salaries.add(0, 0);
		int sum = 0;
		int i,n = salaries.size();
		for(i = 1; i<n; i++){
			sum+=salaries.get(i);
			if(targetPayroll-sum <= salaries.get(i)) break;
		}
		if(i == n) 
			return salaries.get(n-1);
		
		int t = 0;
		for(; t<salaries.get(n-1); t++){
			int capsum = 0;
			for(int j = 0; j<n; j++){
				capsum+=Math.min(t, salaries.get(j));
			}
			if(capsum > targetPayroll) break;
		}
		return --t;
	}
	
}