package net.kkolyan.astar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Finder<P> {
	private Area<P> field;
	private NodeManager<P> nodeManager = new DefaultNodeManager<P>();
	private Collection<P> destinationPoints;
	private P sourcePoint;
	private List<P> path;
	private boolean done;

	//===========================================================================

	public void setDestinationPoints(Collection<P> destinationPoints) {
		this.destinationPoints = destinationPoints;
	}

	public void setSourcePoint(P sourcePoint) {
		this.sourcePoint = sourcePoint;
	}

	public void setField(Area<P> field) {
		this.field = field;
	}

	//===========================================================================

	/**
	 *
	 * @return list of points. excludes start and includes finish. null if there no path.
	 */
	public List<P> getPath() {
		return path;
	}

	//===========================================================================

	public void doSearch() {
		Node<P> node = new Node<P>();
		node.point = sourcePoint;
		node.costG = 0;
		node.costH = field.getHeuristicCost(node.point, destinationPoints);
		node.parent = null;
		nodeManager.openNode(node);
		while (!done) {
			searchAroundBest();
		}
	}

	//===========================================================================

	private void searchAroundBest() {
		Node<P> currentNode = nodeManager.getBestOpened();
		if (currentNode == null) {
			done = true;
			return;
		}
		nodeManager.closeNode(currentNode);

		for (P neighborPoint: field.getNeighbours(currentNode.point)) {
			if (destinationPoints.contains(neighborPoint)) {
				constructPath(currentNode, neighborPoint);
				done = true;
				return;
			}
			NodeState<P> info = nodeManager.getNodeState(neighborPoint);
			if (info != null) {
				if (info.isOpened() && currentNode.costG < info.getNode().parent.costG) {
					info.getNode().parent = currentNode;
				}
			} else {
				Node<P> neighbor = new Node<P>();
				neighbor.parent = currentNode;
				neighbor.point = neighborPoint;
				neighbor.costG = currentNode.costG + field.getStepCost(currentNode.point, neighborPoint);
				neighbor.costH = field.getHeuristicCost(neighbor.point, destinationPoints);
				nodeManager.openNode(neighbor);
			}
		}
	}

	//===========================================================================

	private void constructPath(Node<P> parent, P destination) {
		List<P> list = new ArrayList<P>();
        list.add(destination);
		while (parent.parent != null) {
			list.add(parent.point);
			parent = parent.parent;
		}
		Collections.reverse(list);
		path = list;
	}

	//===========================================================================
}
