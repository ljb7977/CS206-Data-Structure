package cs206b;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        
        Scanner in = new Scanner(System.in);
        int line_num = in.nextInt();
        int[][] map = new int[line_num][5];
        
        for(int i=0; i<line_num; i++){
            for(int j=0; j<5; j++){
                map[i][j] = in.nextInt();
            }
        }
        int solution = coin_pick_player(map);
        System.out.println(solution);
    }
    
    public static int coin_pick_player(int[][] map){
        //implement here
    	int dj[] = {-1, 0, 1};
    	int len = map.length;
    	int[][] DP = new int[len][5];
    	int[][] DPChance = new int[len][5];
    	int answer=0;
    	
    	for(int i=0; i<5; i++){
    		if(i==2){
    			DP[len-1][i] = map[len-1][i];
    			if(map[len-1][i] == -1){
    				DPChance[len-1][i] = 1;
    			} else {
    				DPChance[len-1][i] = -1;
    			}
        		answer = Math.max(DP[len-1][i], answer);
        		answer = Math.max(DPChance[len-1][i], answer);
    		} else {
    			DP[len-1][i] = -1;
    			DPChance[len-1][i] = -1;
    		}
    	}
    	DP[len-2][0] = -1;
    	DPChance[len-2][0] = -1;
    	DP[len-2][4] = -1;
    	DPChance[len-2][4] = -1;
    	
    	for(int i=len-2; i>=0; i--){
    		for(int j=0; j<5; j++){
    			DP[i][j] = -1;
    			DPChance[i][j] = -1;
    			for(int t=0; t<3; t++){
    				if(j+dj[t] < 0 || j+dj[t] >=5) continue;
    				if(map[i][j] == 1){
	    				if(DP[i+1][j+dj[t]] != -1)
	    					DP[i][j] = Math.max(DP[i+1][j+dj[t]]+1, DP[i][j]);
	    				if(DPChance[i+1][j+dj[t]] != -1)
	    					DPChance[i][j] = Math.max(DPChance[i][j], DPChance[i+1][j+dj[t]]+1);
    				}
    				if(map[i][j] == 0){
	    				if(DP[i+1][j+dj[t]] != -1)
	    					DP[i][j] = Math.max(DP[i+1][j+dj[t]], DP[i][j]);
	    				if(DPChance[i+1][j+dj[t]] != -1)
	    					DPChance[i][j] = Math.max(DPChance[i][j], DPChance[i+1][j+dj[t]]);
    				}
    				if(map[i][j] == -1){
    					if(DP[i+1][j+dj[t]] != -1)
    						DPChance[i][j] = Math.max(DPChance[i][j], DP[i+1][j+dj[t]]+1);
    				}
    				answer = Math.max(DP[i][j], answer);
    				answer = Math.max(DPChance[i][j], answer);
    			}
    		}
    	}
    	
    	System.out.println("DP Table");
    	for(int i=0; i<len; i++){
    		for(int j=0; j<5; j++){
    			System.out.print(DP[i][j]+"\t");
    		}
    		System.out.println();
    	}
    	
    	System.out.println("\nDPChance Table");
    	for(int i=0; i<len; i++){
    		for(int j=0; j<5; j++){
    			System.out.print(DPChance[i][j]+"\t");
    		}
    		System.out.println();
    	}
    	
    	return answer;
    }
}

