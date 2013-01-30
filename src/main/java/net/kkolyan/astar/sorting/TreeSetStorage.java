package net.kkolyan.astar.sorting;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetStorage<T> implements SortedStorage<T> {
	private TreeSet<T> treeSet;

	public TreeSetStorage(Comparator<T> comparator) {
		treeSet = new TreeSet<T>(comparator);
	}

	@Override
	public void add(T o) {
		treeSet.add(o);
	}

	@Override
	public void remove(T o) {
		treeSet.remove(o);
	}

	@Override
	public T first() {
		if (treeSet.isEmpty()) {
			return null;
		}
		return treeSet.first();
	}
}
