package net.kkolyan.astar.demo;

import java.io.IOException;

public class RhinoDemo {

	public static void main(String[] args) throws IOException {
		Rhino.evaluate("Finder.js", "NodeManager.js", "Point.js", "SimpleArea.js", "SortedList.js", "__demo__.js");
	}
}
