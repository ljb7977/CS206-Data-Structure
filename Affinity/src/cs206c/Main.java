package cs206c;
import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		HashMap<String, ArrayList<String>> hmap = PageUserView(in);
		ArrayList<String> highestPairs = HighestAffinityPair(hmap);
		
		for(int i = 0 ; i < highestPairs.size(); i++) 
			System.out.println(highestPairs.get(i)) ;
    }
	
	public static HashMap<String, ArrayList<String>> PageUserView(Scanner in)  {
		HashMap<String, ArrayList<String>> hmap = new HashMap<String, ArrayList<String>>();
		while(in.hasNextLine()){
			String s = in.nextLine();
			String t[] = s.split(",");
			if(hmap.containsKey(t[0])){
				ArrayList<String> valuelist = hmap.remove(t[0]);
				if(!valuelist.contains(t[1]))
					valuelist.add(t[1]);
				Collections.sort(valuelist);
				hmap.put(t[0], valuelist);
			} else {
				ArrayList<String> valuelist = new ArrayList<String>();
				valuelist.add(t[1]);
				hmap.put(t[0], valuelist);
			}
		}
		return hmap;
	}
	
	public static ArrayList<String> HighestAffinityPair(HashMap<String, ArrayList<String>> hmap) {
		int max = 0;
		int n = hmap.size();
		int [][] P = new int[hmap.size()][hmap.size()];
		Object[] s= hmap.keySet().toArray();
		for(int i = 0; i<n; i++){
			for(int j = i+1; j<n; j++){
				ArrayList<String> userlist = hmap.get(s[i]);
				for(String user: userlist){
					if(hmap.get(s[j]).contains(user)){
						P[i][j]++;
					}
				}
				max = Math.max(max, P[i][j]);
			}
		}
		ArrayList<String> answer = new ArrayList<String>();
		
		for(int i=0; i<n; i++){
			for(int j = i+1; j<n; j++){
				if(P[i][j] == max){
					ArrayList<String> temp = new ArrayList<String>();
					temp.add(s[i].toString());
					temp.add(s[j].toString());
					Collections.sort(temp);
					answer.add(temp.get(0)+" "+temp.get(1));
				}
			}
		}
		Collections.sort(answer);
		return answer;
	}
}