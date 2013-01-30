package net.kkolyan.astar;

public interface NodeManager<P> {

	/**
	 *
	 * @param point -
	 * @return node or null
	 */
	NodeState<P> getNodeState(P point);

	void openNode(Node<P> node);

	void closeNode(Node<P> node);

	Node<P> getBestOpened();

}
