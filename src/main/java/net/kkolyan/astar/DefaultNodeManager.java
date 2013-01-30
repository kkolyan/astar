package net.kkolyan.astar;

import net.kkolyan.astar.sorting.SortedStorage;
import net.kkolyan.astar.sorting.TreeSetStorage;

import java.lang.System;import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DefaultNodeManager<P> implements NodeManager<P> {
	private Map<P,NodeInfoImpl> nodeMap = new HashMap<P, NodeInfoImpl>();
	private SortedStorage<Node<P>> openedNodes = new TreeSetStorage<Node<P>>(new Comp());
	
	public NodeState<P> getNodeState(P point) {
		return nodeMap.get(point);
	}

	public void openNode(Node<P> node) {
		NodeInfoImpl nodeInfo = new NodeInfoImpl();
		nodeInfo.node = node;
		nodeInfo.opened = true;
		nodeMap.put(node.point, nodeInfo);

		openedNodes.add(node);
	}

	public void closeNode(Node<P> node) {
		NodeInfoImpl nodeInfo = nodeMap.get(node.point);
		nodeInfo.opened = false;
		openedNodes.remove(node);
	}

	public Node<P> getBestOpened() {
		return openedNodes.first();
	}

	//=============================================================

	private class Comp implements Comparator<Node<P>> {
		public int compare(Node<P> o1, Node<P> o2) {
			int delta = o1.costG + o1.costH - o2.costG - o2.costH;
			if (delta != 0) {
				return delta;
			}
			return System.identityHashCode(o1) - System.identityHashCode(o2);
		}
	}

	//=============================================================

	private class NodeInfoImpl implements NodeState<P> {
		Node<P> node;
		boolean opened;

		public boolean isOpened() {
			return opened;
		}

		public Node<P> getNode() {
			return node;
		}
	}

	//=============================================================
}
