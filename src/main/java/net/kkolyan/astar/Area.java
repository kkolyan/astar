package net.kkolyan.astar;

import java.util.Collection;

public interface Area<P> {

	/**
	 *
	 * @param point -
	 * @return collection of points which can be reached at one step from the specified point. never null
	 */
	Collection<? extends P> getNeighbours(P point);

	/**
	 *
	 * @param from -
	 * @param to -
	 * @return moving cost or -1 if "to" can't be reached from "from" at one step.
	 */
	int getStepCost(P from, P to);

	/**
	 *
	 *
     * @param from -
     * @param toAny - collection of targets
     * @return estimated heuristic cost
	 */
	int getHeuristicCost(P from, Collection<? extends P> toAny);
}
