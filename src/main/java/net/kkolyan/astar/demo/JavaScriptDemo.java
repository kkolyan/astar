package net.kkolyan.astar.demo;

import java.io.IOException;

public class JavaScriptDemo {

	public static void main(String[] args) throws IOException {
		JavaScript.evaluate("Finder.js", "NodeManager.js", "Point.js", "SimpleArea.js", "SortedList.js", "__demo__.js");
	}
}
