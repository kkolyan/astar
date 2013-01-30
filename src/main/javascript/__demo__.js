System = eval("java.lang.System");

function contains(set,point) {
    for (var i = 0; i < set.length; i ++) {
        if (point.x == set[i].x && point.y == set[i].y) {
            return true;
        }
    }
    return false;
}

function print(s) {
    System.out.print(s);
}
function println(s) {
    System.out.println(s);
}


function printPath(fieldString) {
    var lines = fieldString.split("\n");
    var height = 0;
    var width = -1;
    var obstacles = {};
    var source = null;
    var destination;
    for (var lineN = 0; lineN < lines.length; lineN ++) {
        var line = lines[lineN];
        if (line.length > width) {
            width = line.length;
        }
        for (var charN = 0; charN < line.length; charN ++) {
            var ch = line[charN];
            var cell = new Point(charN, height);
            if (ch == 'X') {
                obstacles[cell.toIdString()] = true;
            }
            if (ch == 'A') {
                source = cell;
            }
            if (ch == 'Z') {
                destination = cell;
            }
        }
        height ++;
    }
    if (!destination) {
        throw new Error("destination not specified");
    }
    var area = new SimpleArea(width, height, obstacles);
    var finder = new Finder({
        source: source,
        destinations: [destination],
        area: area
    });

    finder.doSearch();

    print(" ");
    for (var i0 = 0; i0 < width; i0 ++)
        print("-");
    println("");

    for (var y = 0; y < height; y ++) {
        print("|");
        for (var x = 0; x < width; x ++) {
            var point = new Point(x, y);
            var c = ' ';
            if (obstacles[point.toIdString()]) {
                c = 'X';
            }
            if (contains(finder.path,point)) {
                c = 'O';
            }
            if (point.toIdString() == source.toIdString()) {
                c = 'A';
            }
            if (point.toIdString() == destination.toIdString()) {
                c = 'Z';
            }
            print(c);
        }
        print("|");
        println("");
    }
    print(" ");
    for (var i = 0; i < width; i ++)
        print("-");
    println("");
}


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