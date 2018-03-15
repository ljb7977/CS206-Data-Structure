package cs206c;

import cs206c.Element;
import cs206c.MyQueue;
import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String args[]) {
		// Read the map from standard input
		char[][] map = readMap();

		// Verify the map
		printMap(map);

		// Find the closest battery
		System.out.println(findClosestBattery(map));
	}

	public static int findClosestBattery(char[][] map) {
		// Problem 4
		int N = map.length;

		// Get Nubjuk's starting point
		Element startingPoint = getStartingPoint(map);

		int dx[] = { 1, 0, 0, -1 };
		int dy[] = { 0, 1, -1, 0 };

		int distance[][] = new int[N][N];
		
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				distance[i][j] = 1000000000;
			}
		}

		// Initialize queue
		MyQueue queue = new MyQueue();
		queue.enqueue(startingPoint);

		while (!queue.isEmpty()) {
			Element t = queue.dequeue();
			for (int i = 0; i < 4; i++) {
				int yy = t.y + dy[i], xx = t.x + dx[i];
				if (yy >= N || xx >= N || xx < 0 || yy < 0)
					continue;
				if (map[yy][xx] != 'X') {
					if (distance[yy][xx] <= t.distance+1)
						continue;
					Element e = new Element(xx, yy, t.distance + 1);
					distance[yy][xx] = t.distance+1;
					queue.enqueue(e);
				}
			}
		}

		int mindist = 1000000000;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'B')
					mindist = Math.min(mindist, distance[i][j]);
			}
		}
		if (mindist == 1000000000)
			return -1;
		return mindist;
	}

	public static Element getStartingPoint(char[][] map) {
		// Find starting point
		Element startingPoint = null;

		int N = map.length;

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == 'N') {
					startingPoint = new Element(x, y, 0);
				}
			}
		}

		return startingPoint;
	}

	public static char[][] readMap() {
		Scanner scanner = new Scanner(System.in);

		try {
			// Read N
			int N = Integer.parseInt(scanner.nextLine());

			// Define a N x N array
			char[][] map = new char[N][N];

			for (int i = 0; i < N; i++) {
				String line = scanner.nextLine();
				String[] map_i = line.split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = map_i[j].charAt(0);
				}
			}

			return map;
		} catch (Exception e) {
			System.err.println("Please check your input!");
			e.printStackTrace();
		}

		return null;
	}

	public static void printMap(char[][] map) {
		int N = map.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
