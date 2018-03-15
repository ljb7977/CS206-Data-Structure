package cs206c;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static int max_time = 50;
	
	public static void main(String[] args){
		//Do not modify this
        ArrayList<Entry<Integer, Process>> aL = new ArrayList<>();
        ProcessQueue pq = new ProcessQueue(aL);
        Scanner in = new Scanner(System.in);
        ArrayList<Process> processes = parseProcess(in);
        in.close();
        
        for(Process p : processes){
            pq.insert(p);
        }
        simulate(pq);
	}
	
	public static ArrayList<Process> parseProcess(Scanner in){
		ArrayList<Process> list = new ArrayList<Process>();
		while(in.hasNextLine()){
			String t = in.nextLine();
			String[] a = t.split(" ");
			for(int i=0; i+Integer.parseInt(a[3])<=50; i+=Integer.parseInt(a[1])){
				Process p = new Process(Integer.parseInt(a[1]), a[0], Integer.parseInt(a[2]), Integer.parseInt(a[3])+i);
				list.add(p);
			}
		}
		return list;
	}
	
    public static void simulate(ProcessQueue q){

        Simulator sim = new Simulator(q);
        System.out.println("*Finished Process, order preserved*");
        for(Process p : sim.getEndedProcess())
            System.out.print(p.getName()+ "(" + p.getloadedTime() + ") ");
        System.out.println("\n\n*Processes unable to finish in time*");
        for(Process p : sim.getNotEndedProcess())
            System.out.print(p.getName()+ "(" + p.getloadedTime() + ") ");
        return;
    }
}