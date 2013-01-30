package net.kkolyan.astar.demo;

import net.kkolyan.astar.Area;

import java.lang.Math;import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleArea implements Area<Point> {
	private Set<Point> obstacles = new HashSet<Point>();
	private int width;
	private int height;

	private static final List<Point> neighbourOffsets = Arrays.asList(
			new Point(-1, -1),
			new Point( 0, -1),
			new Point( 1, -1),
			new Point(-1,  0),
			new Point( 1,  0),
			new Point(-1,  1),
			new Point( 0,  1),
			new Point( 1,  1)
	);

	public SimpleArea(int width, int height, Set<Point> obstacles) {
		this.obstacles = obstacles;
		this.width = width;
		this.height = height;
	}

	public Collection<Point> getNeighbours(Point point) {
		List<Point> list = new ArrayList<Point>();
		for (Point offset: neighbourOffsets) {
			Point n = new Point(point.x + offset.x, point.y + offset.y);
			if (obstacles.contains(n)) {
				continue;
			}
			if (n.x < 0 || n.x >= width) {
				continue;
			}
			if (n.y < 0 || n.y >= height) {
				continue;
			}
			list.add(n);
		}
		return list;
	}

	public int getStepCost(Point from, Point to) {
		int dx = Math.abs(from.x - to.x);
		int dy = Math.abs(from.y - to.y);

		if (dx > 1 || dy > 1) {
			return -1;
		}
		if (dx != dy) {
			return 10;
		}
		if (dx == 1) {//and dy == 1 implicitly
			return 14;
		}
		return 0;
	}

	@Override
	public int getHeuristicCost(Point from, Collection<? extends Point> toAny) {
		List<Integer> points = new ArrayList<Integer>(toAny.size());
		for (Point to: toAny) {
			points.add(getHeuristicCost(from, to));
		}
		return Collections.min(points);
	}

	private int getHeuristicCost(Point from, Point to) {
		int dx = Math.abs(from.x - to.x);
		int dy = Math.abs(from.y - to.y);
		return (dx + dy) * 10;
	}

	@Override
	public String toString() {
		return "SimpleGridArea{" +
//				"obstacles=" + obstacles +
				", width=" + width +
				", height=" + height +
				'}';
	}
}
