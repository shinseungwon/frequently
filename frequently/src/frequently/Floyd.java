package frequently;

import java.util.ArrayList;

public class Floyd {

	static final double inf = Double.MAX_VALUE;
	static final int nil = -1;

	private double[][] Network_Weight;
	private int[][] Pre_Route;

	private ArrayList<Integer> Route[][];

	int N;

	Floyd(double[][] Network_Weight) {
		this.Network_Weight = Network_Weight;

		N = this.Network_Weight.length;

		Pre_Route = new int[N][N];

		Route = new ArrayList[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Route[i][j] = new ArrayList<>();
				if (i == j)
					continue;

				if (Network_Weight[i][j] < inf) {
					Route[i][j].add(i);
					Route[i][j].add(j);
					Pre_Route[i][j] = i;
				}
			}
		}
	}

	public void DoFloyd() {

		for (int k = 0; k < N; k++)
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (Network_Weight[i][k] + Network_Weight[k][j] < Network_Weight[i][j]) {
						Pre_Route[i][j] = k;
						Network_Weight[i][j] = Network_Weight[i][k] + Network_Weight[k][j];
						Route[i][j].clear();

						for (int l = 0; l < Route[i][k].size(); l++)
							Route[i][j].add(Route[i][k].get(l));

						for (int l = 1; l < Route[k][j].size(); l++)
							Route[i][j].add(Route[k][j].get(l));

					}
	}

	public void printRoute(int start, int end) {
		if (start == end)
			return;

		System.out.print("Route " + start + " to " + end + " : ");

		for (int i = 0; i < Route[start][end].size(); i++)
			System.out.print(Route[start][end].get(i) + " ");

		System.out.println();
	}

	public void printAllRoute() {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				printRoute(i, j);
	}

	public void printWeight() {
		System.out.println("Weight Graph");
		for (int i = 0; i < Network_Weight.length; i++) {
			for (int j = 0; j < Network_Weight[i].length; j++) {
				if (Network_Weight[i][j] == Double.MAX_VALUE)
					System.out.print("inf ");
				else
					System.out.print(Network_Weight[i][j] + "  ");
			}
			System.out.println();
		}
	}

}
