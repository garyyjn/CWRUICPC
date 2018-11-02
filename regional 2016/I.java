import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class I {

	public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			while(sc.hasNextInt()) {
				int numChild = sc.nextInt();
				int numToy = sc.nextInt();
				int numCat = sc.nextInt();
				int[][] g = new int[2 + numChild + numToy + numCat][2 + numChild + numToy + numCat];
				for(int i = 0; i < numChild; i ++) {
					g[0][i + 1] = 1;
				}
				int firstToyIndex = numChild + 1;
				for(int i = 0; i < numChild; i ++) {
					int numT = sc.nextInt();
					for(int m = 0; m < numT; m ++) {
						g[i + 1][sc.nextInt() - 1 + firstToyIndex] = 1;
					}
				}
				int firstCatIndex = numChild + numToy + 1;
				boolean[] toyCatagorized = new boolean[numToy];
				for(int i = 0; i < numCat; i ++) {
					int numC = sc.nextInt();
					for(int m = 0; m < numC; m ++) {
						int temp = sc.nextInt();
						g[temp - 1 + firstToyIndex][firstCatIndex + i] = 1;
						toyCatagorized[temp - 1] = true;
					}
					g[firstCatIndex + i][numChild + numToy + numCat + 1] = sc.nextInt();
				}
				for(int m = 0; m < numToy; m++) {
					if(!toyCatagorized[m]) {
						g[m + firstToyIndex][numChild + numToy + numCat + 1] = 1;
					}
				}
				
				System.out.println(maxFlow(g, 0, numChild + numToy + numCat + 1));
			}
		
	}
	
	static int maxFlow(int[][] graph, int s, int t) {
		int numVer = graph.length;
		int[][] rGraph = new int[numVer][numVer];
		for(int i = 0; i < numVer; i++) {
			for(int j = 0; j < numVer; j ++) {
				rGraph[i][j] = graph[i][j];
			}
		}

		int[] parent = new int[numVer];
		int flow = 0;
		
		while(bfs(rGraph, s, t, parent)) {
			int pathFlow = Integer.MAX_VALUE;
			for(int i = t; i != s; i = parent[i]) {
				int prev = parent[i];
				pathFlow = Integer.min(rGraph[prev][i], pathFlow);
			}
			for(int i = t; i != s; i = parent[i]) {
				int prev = parent[i];
				rGraph[prev][i] -= pathFlow;
				rGraph[i][prev] += pathFlow; //Allow reverse
			}
			flow += pathFlow;
		}
		return flow;
	}
	
	static boolean bfs(int[][] graph, int s, int t, int[] parent) {
		int numVer = graph.length;
		boolean[] visited = new boolean[numVer];
		for(int i = 0; i < numVer; i ++) {
			visited[i] = false;
		}
		visited[s] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(s);
		while(!q.isEmpty()) {
			int temp = q.poll();
			visited[temp] = true;
			for(int i = 0; i < numVer; i ++) {
				if(graph[temp][i] > 0 && !visited[i]) {
					q.add(i);
					parent[i] = temp;
				}
			}
		}
		return visited[t] == true;
	}

}
