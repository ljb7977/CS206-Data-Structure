package cs206c;
public class Process{
	private int period;
	private String name;
	private int length;
	private int loadedTime;
	
	public Process(int period, String name, int length, int loadedTime){
		this.period = period;
		this.name = name;
		this.length = length;
		this.loadedTime = loadedTime;
	}
	
	public int getPeriod(){
		return this.period;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public Integer getKey() {
		return this.length;
	}

	public Process getValue() {
		return this;
	}
	
	public void consumeTimeSlice(){
		this.length--;
	}
	
	public int getloadedTime(){
		return this.loadedTime;
	}

}