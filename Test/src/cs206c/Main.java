package cs206c;

import cs206c.Block;
import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class Main {

	public static void main(String args[]) {
		// Get the list of blocks
		List<Block> blocks = readBlocks();
		//printBlocks(blocks);
		// Merge the blocks
		List<Block> mergedBlocks = mergeBlocks(blocks);

		// Print the merged blocks
		printBlocks(mergedBlocks);
	}

	// Problem 1
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static List<Block> readBlocks() {
		// Implement here
		
		List<Block> blocks = new ArrayList<Block>();
		
		int a=0, b=0;
		/*
		try {
			while(true){
				a = System.in.read();
				if(a==-1) break;
				b = System.in.read();
				if(b==-1) break;
				b = System.in.read();
				Block t = new Block(a-'0', b-'0');
				blocks.add(t);
				a = System.in.read();
				a = System.in.read();
			}
		} catch (IOException e) {
		}
		*/
		Scanner scn = new Scanner(System.in);
		while(scn.hasNextInt()){
			a = scn.nextInt();
			b = scn.nextInt();
			Block t = new Block(a, b);
			blocks.add(t);
		}
		scn.close();
		return blocks;
	}

	// Problem 2
	// DO NOT MODIFY THE FUNCTION DECLARATION
	public static List<Block> mergeBlocks(List<Block> blocks) {
		// Implement here
		PriorityQueue<Block> pq = new PriorityQueue<Block>(100,new Comparator<Block>() {
			@Override
			public int compare(Block o1, Block o2) {
				if(o1.start < o2.start) return -1;
				else if (o1.start > o2.start) return 1;
				return 0;
			}
		});
		
		List<Block> mergedBlocks = new ArrayList<Block>();
		Collections.sort(blocks, new Comparator<Block>() {
			@Override
			public int compare(Block o1, Block o2) {
				// TODO Auto-generated method stub
				if(o1.end < o2.end) return -1;
				else if (o1.end > o2.end) return 1;
				else {
					if(o1.start < o2.start) return -1;
					else return 1;
				}
			}
		});
		int len = blocks.size();
		int nowstart=blocks.get(0).start, nowend = blocks.get(0).end;
		for(int i=1; i<len; i++){
			if(blocks.get(i).start <= nowend){
				nowend = blocks.get(i).end;
				nowstart = Math.min(nowstart, blocks.get(i).start);
				while(!pq.isEmpty() && pq.peek().start >= nowstart){
					pq.poll();
				}
			} else {
				Block newblock = new Block(nowstart, nowend);
				pq.add(newblock);
				nowstart = blocks.get(i).start;
				nowend = blocks.get(i).end;
			}
		}
		Block t = new Block(nowstart, nowend);
		pq.add(t);
		mergedBlocks.addAll(pq);
		return mergedBlocks;
	}

	public static void printBlocks(List<Block> blocks) {
		for (Block block : blocks) {
			System.out.format("[%d - %d]\n", block.start, block.end);
		}
	}
}