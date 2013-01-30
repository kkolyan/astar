package net.kkolyan.astar;

public class Node<P> {
	public P point;
	public Node<P> parent;
	public int costG;
	public int costH;
}
