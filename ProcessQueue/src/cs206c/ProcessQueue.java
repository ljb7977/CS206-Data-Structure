package cs206c;

import java.util.*;

public class ProcessQueue{

	// implement additional methods if you need

	private ArrayList<Entry<Integer, Process>> processes;
	
	public ProcessQueue(ArrayList<Entry<Integer, Process>> processes){
		this.processes = processes;
	}

	public boolean isEmpty(){
		return this.processes.isEmpty();
	}

    public void swap(int i, int j){
        //implement here
    	Entry<Integer, Process> tempi = this.processes.get(i);
    	Entry<Integer, Process> tempj = this.processes.get(j);
    	this.processes.remove(i);
    	this.processes.add(i, tempj);
    	this.processes.remove(j);
    	this.processes.add(j, tempi);
	}
    
    public int getithKey(int i){
    	return this.processes.get(i).getKey();
    }

    public void reArrangeHeapStructure(){
        //implement here
    	int len = this.processes.size();
    	if(len <= 1) return;
    	int last = len-1;
    	int i = last;
    	while(i != 0 && (getithKey(i) < getithKey((i-1)/2)) ){ //upheap
			swap(i, (i-1)/2);
			i = (i-1)/2;
		}
    	
    	i = 0;
    	int childind = 1;
    	while(childind <= last){ //downheap
    		if(childind+1 <= last)//자식 노드가 두개
    			if(getithKey(childind) > getithKey(childind+1))
    				childind++; //오른쪽 자식 선택
    		if(getithKey(i) > getithKey(childind)){
    			swap(i, childind);
    			i = childind;
    			childind = i*2+1;
			} else break;
    	}
	}
	
	public void insert(Process p){
        //implement here
		this.processes.add(new PQEntry<Integer, Process>(p.getloadedTime(), p));
		reArrangeHeapStructure();
	}

	public Process getNextProcess(){
        //implement here
		int len = this.processes.size();
		swap(0, len-1);
		Entry<Integer, Process> temp = this.processes.remove(len-1);
		reArrangeHeapStructure();
        return temp.getValue();
	}
	
	

	/* Do not modify this*/
	protected static class PQEntry<K,V> implements Entry<K,V> {
		private K k;  // key
		private V v;  // value

		public PQEntry(K key, V value) {
			k = key;
			v = value;
		}

		// methods of the Entry interface
		public K getKey() { return k; }
		public V getValue() { return v; }

		// utilities not exposed as part of the Entry interface
		protected void setKey(K key) { k = key; }
		protected void setValue(V value) { v = value; }
	}

}