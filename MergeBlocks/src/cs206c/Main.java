package cs206c;

import cs206c.Block;
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String args[]) {
		// Get the list of blocks
		List<Block> blocks = readBlocks();

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
		ArrayList<Block> mergedblocks = new ArrayList<Block>();
		
		Collections.sort(blocks, new Comparator<Block>() {
			public int compare(Block o1, Block o2) {
				if(o1.start > o2.start) return 1;
				else if (o1.start < o2.start) return -1;
				else {
					if(o1.end > o2.end ) return 1;
					else return -1;
				}
			}
		});
		
		int len = blocks.size();
		int nowstart = blocks.get(0).start, nowend = blocks.get(0).end;
		for(int i=1; i<len; i++){
			if(blocks.get(i).start <= nowend){
				nowend = Math.max(nowend, blocks.get(i).end);
			} else {
				Block b = new Block(nowstart, nowend);
				mergedblocks.add(b);
				nowstart = blocks.get(i).start;
				nowend = blocks.get(i).end;
			}
		}
		Block b = new Block(nowstart, nowend);
		mergedblocks.add(b);
		
		return mergedblocks;
	}

	public static void printBlocks(List<Block> blocks) {
		for (Block block : blocks) {
			System.out.format("[%d - %d]\n", block.start, block.end);
		}
	}
}
