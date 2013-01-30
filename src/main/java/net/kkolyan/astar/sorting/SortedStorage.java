package net.kkolyan.astar.sorting;

public interface SortedStorage<T> {

	void add(T o);

	void remove(T o);

	T first();
}
