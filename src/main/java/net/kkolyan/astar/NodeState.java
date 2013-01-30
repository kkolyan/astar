package net.kkolyan.astar;

public interface NodeState<P> {
	boolean isOpened();
	Node<P> getNode();
}
