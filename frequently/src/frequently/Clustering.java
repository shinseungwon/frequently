package frequently;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Clustering {
	// 클러스터 모체 클래스
	private String tag;
	private int numberOfPoint = 0;
	private ArrayList<Node> Nodes;
	private ArrayList<Cluster> Clusters;
	private int numberOfElement = 0;
	private int numberOfCluster = 0;
	private double changeRate = 10;

	Clustering(String tag, int Element, int Cluster) {
		this.tag = tag;
		this.numberOfCluster = Cluster;
		this.numberOfElement = Element;
		Nodes = new ArrayList<>();
		Clusters = new ArrayList<>();
	}

	public void addNode(String tag, double[] Elements) {
		if (numberOfElement == 0)
			return;
		if (numberOfElement != Elements.length)
			return;

		Nodes.add(new Node(tag, Elements));
		numberOfPoint++;
	}

	public void DoClustering() {
		if (numberOfCluster <= 0 || numberOfElement <= 0)
			return;

		double limit = 0.0;

		double[] mid = new double[numberOfElement];

		for (int i = 0; i < Nodes.size(); i++)
			for (int j = 0; j < numberOfElement; j++)
				mid[j] += Nodes.get(i).Elements[j];

		for (int j = 0; j < numberOfElement; j++)
			mid[j] /= Nodes.size();

		for (int i = 0; i < Nodes.size(); i++)
			limit += getDist(mid, Nodes.get(i).Elements);

		limit /= Nodes.size() * changeRate;

		SecureRandom R = new SecureRandom();
		double[] clusteraddr;
		for (int i = 0; i < numberOfCluster; i++) {
			clusteraddr = new double[numberOfElement];
			for (int j = 0; j < numberOfElement; j++)
				clusteraddr[j] = R.nextDouble() * limit * (100 / changeRate);

			Clusters.add(new Cluster("Cluster " + i, clusteraddr));
		}

		double change = limit + 1.0;
		Cluster tempc;
		while (change > limit) {
			change = 0.0;
			// Find Nearest Cluster
			for (int i = 0; i < numberOfCluster; i++)
				Clusters.get(i).slaves.clear();

			for (int i = 0; i < Nodes.size(); i++) {
				tempc = getNearest(Nodes.get(i));
				Nodes.get(i).master = tempc;
				tempc.slaves.add(Nodes.get(i));
			}

			for (int i = 0; i < numberOfCluster; i++)
				change += Clusters.get(i).setAddress();

		}

	}

	private Cluster getNearest(Node n) {
		if (numberOfCluster == 0)
			return null;
		Cluster C = Clusters.get(0);
		double value = getDist(Clusters.get(0).Address, n.Elements);

		double temp;
		for (int i = 0; i < numberOfCluster; i++) {
			if ((temp = getDist(Clusters.get(i).Address, n.Elements)) < value) {
				C = Clusters.get(i);
				value = temp;
			}
		}

		return C;
	}

	private double getDist(double[] a, double[] b) {
		double sum = 0;

		for (int i = 0; i < numberOfElement; i++)
			sum += (a[i] - b[i]) * (a[i] - b[i]);

		return Math.sqrt(sum);
	}

	public void printAllNodes() {
		System.out.println(tag + " Nodes");

		for (int i = 0; i < numberOfPoint; i++) {
			System.out.print(Nodes.get(i).tag + " : ");
			for (int j = 0; j < numberOfElement; j++)
				System.out.print(
						"( " + j + " : " + Double.parseDouble(String.format("%.2f", Nodes.get(i).Elements[j])) + ") ");

			System.out.println();
		}
	}

	public void printAllCluster() {
		System.out.println(tag + " Clusters");

		for (int i = 0; i < numberOfCluster; i++) {
			System.out.println("Cluster " + i + " : " + Clusters.get(i).slaves.size());
			for (int j = 0; j < Clusters.get(i).slaves.size(); j++) {
				System.out.print(Clusters.get(i).slaves.get(j).tag + ",");
				if ((j + 1) % 5 == 0)
					System.out.println();
			}
			System.out.println();
		}
	}

	// inner classes

	// node
	private class Node {
		Cluster master;
		String tag;
		double[] Elements;

		Node(String tag, double[] Elements) {
			this.tag = tag;
			this.Elements = Elements;
		}
	}

	// cluster
	private class Cluster {
		ArrayList<Node> slaves;
		String tag;
		double[] Address;

		Cluster(String tag, double[] Address) {
			this.tag = tag;
			this.Address = Address;
			slaves = new ArrayList<>();
		}

		double[] temp;

		public double setAddress() {
			temp = new double[numberOfElement];

			for (int i = 0; i < numberOfElement; i++)
				temp[i] = 0.0;

			for (int i = 0; i < slaves.size(); i++)
				for (int j = 0; j < numberOfElement; j++)
					temp[j] += slaves.get(i).Elements[j];

			for (int i = 0; i < numberOfElement; i++)
				temp[i] /= slaves.size();

			double diff = getDist(Address, temp);

			Address = temp;

			return diff;
		}
	}
}
