package net.kkolyan.astar.demo;

import net.kkolyan.astar.Finder;

import java.lang.String;import java.lang.System;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SimpleTestTool {

	public static void main(String[] args) {
		printPath(
				"                 X      \n" +
				"        XXXXX    X      \n" +
				"            X    X      \n" +
				"            X    X  Z   \n" +
				"       A    X    X      \n" +
				"            X           "
		);
		printPath(
				"A           X           \n" +
				"            X      Z    \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"            X           \n" +
				"                        "
		);
	}
	
	public static void printPath(String fieldString) {
		Scanner scanner = new Scanner(fieldString);
		int height = 0;
		int width = -1;
		Set<Point> obstacles = new HashSet<Point>();
		Point source = null;
		Point destination = null;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			if (line.length() > width) {
				width = line.length();
			}
			for (int i = 0; i < line.length(); i ++) {
				char c = line.charAt(i);
				Point point = new Point(i, height);
				if (c == 'X') {
					obstacles.add(point);
				}
				if (c == 'A') {
					source = point;
				}
				if (c == 'Z') {
					destination = point;
				}
			}
			height ++;
		}
		SimpleArea area = new SimpleArea(width, height, obstacles);
		Finder<Point> finder = new Finder<Point>();
		finder.setSourcePoint(source);
		finder.setDestinationPoints(Arrays.asList(destination));
		finder.setField(area);

		finder.doSearch();


		System.out.print(" ");
		for (int i = 0; i < width; i ++)
			System.out.print("-");
		System.out.println();

		for (int y = 0; y < height; y ++) {
			System.out.print("|");
			for (int x = 0; x < width; x ++) {
				Point point = new Point(x, y);
				char c = ' ';
				if (obstacles.contains(point)) {
					c = 'X';
				}
				if (finder.getPath().contains(point)) {
					c = 'O';
				}
				if (point.equals(source)) {
					c = 'A';
				}
				if (point.equals(destination)) {
					c = 'Z';
				}
				System.out.print(c);
			}
			System.out.print("|");
			System.out.println();
		}
		System.out.print(" ");
		for (int i = 0; i < width; i ++)
			System.out.print("-");
		System.out.println();
	}
}
