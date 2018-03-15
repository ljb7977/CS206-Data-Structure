package cs206c;
import java.util.*;

public class Simulator {
    // Do not modify this
    private ProcessQueue pq;
    private int time;
    private ArrayList<Process> Ended;
    private ArrayList<Process> notEnded;
    
    // Do not modify this
    public Simulator(ProcessQueue pq){
        this.pq = pq;
        this.time = 0;
        this.Ended = new ArrayList<Process>();
        this.notEnded = new ArrayList<Process>();
        this.simulate(this.pq);
    }

	
	private void simulate(ProcessQueue pq){
        //Simulate EDF scheduling using a process queue
		Process now = null;
		ArrayList<Process> t = new ArrayList<Process>();
		PriorityQueue<Process> wait = new PriorityQueue<Process>(1, new Comparator<Process>() {
			@Override
			public int compare(Process p1, Process p2){
				if(p1.getLength() > p2.getLength()) return 1;
				else if (p1.getLength() < p2.getLength()) return -1;
				else return Integer.parseInt(p1.getName().substring(1)) - Integer.parseInt(p2.getName().substring(1));
			}
		});
		
		while(!pq.isEmpty())
			t.add(pq.getNextProcess());
		
		for(this.time=0;this.time<=50;this.time++){
			while(!t.isEmpty() && t.get(0).getloadedTime() == this.time)
				wait.add(t.remove(0));
			
			if(wait.isEmpty()) continue;
			
			if(this.time == 50) break;
			
			now = wait.poll();
			now = new Process(now.getPeriod(), now.getName(), now.getLength()-1, now.getloadedTime());
			if(now.getLength() == 0) this.Ended.add(now);
			else wait.add(now);
			now = null;
		}
		
		while(!wait.isEmpty())
			this.notEnded.add(wait.poll());
		
    }

	public int getLength(){
		return this.time;
	}
    public ArrayList<Process> getEndedProcess(){
        return this.Ended;
    }
    public ArrayList<Process> getNotEndedProcess(){
        return this.notEnded;
    }
}