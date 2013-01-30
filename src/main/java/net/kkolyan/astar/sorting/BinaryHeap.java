package net.kkolyan.astar.sorting;

import java.util.Comparator;

public class BinaryHeap<T> implements SortedStorage<T> {
	private Comparator<T> comparator;
	private Node root;

	public BinaryHeap(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	@Override
	public void add(T o) {
		root = attach(root, o);
	}

	private Node attach(Node node, T o) {
		if (node == null) {
			return new Node(o);
		}
		if (comparator.compare(node.element, o) < 0) {
			Node newNode = new Node(o);
			newNode.low = node;
			return newNode;
		}
		if (node.low == null) {
			node.low = new Node(o);
		} else if (node.high == null) {
			node.high = new Node(o);
		} else {
			
		}
		return node;
	}

	@Override
	public void remove(T o) {
	}

	@Override
	public T first() {
		return null;
	}

	private class Node {
		T element;
		Node high;
		Node low;

		private Node(T element) {
			this.element = element;
		}
	}
}
