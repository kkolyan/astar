

function SimpleArea(width, height, obstacles) {
    var neighbourOffsets = [
        new Point(-1, -1),
        new Point( 0, -1),
        new Point( 1, -1),
        new Point(-1,  0),
        new Point( 1,  0),
        new Point(-1,  1),
        new Point( 0,  1),
        new Point( 1,  1)
    ];

    this.getHeuristicCost = function(from, to) {
        if (to instanceof Array) {
            var costs = [];
            for (var i in to) {
                costs.push(this.getHeuristicCost(from, to[i]));
            }
            return Math.min.apply(Math, costs);
        }

        var dx = Math.abs(from.x - to.x);
        var dy = Math.abs(from.y - to.y);
        return (dx + dy) * 10;
    };

    this.getNeighbours = function(point) {
        var list = [];
        for (var i in neighbourOffsets) {
            var n = new Point(point.x + neighbourOffsets[i].x, point.y + neighbourOffsets[i].y);

            if (obstacles[point.toIdString()]) continue;
            if (n.x < 0 || n.x >= width) continue;
            if (n.y < 0 || n.y >= height) continue;

            list.push(n);
        }
        return list;
    };

    this.getStepCost = function(from, to) {
        var dx = Math.abs(from.x - to.x);
        var dy = Math.abs(from.y - to.y);

        if (dx > 1 || dy > 1) {
            return -1;
        }
        if (dx == dy == 1) {
            return 14;
        }
        if (dx != dy) {
            return 10;
        }
        throw new Error("Attempt to get cost from "+JSON.stringify(from)+" to "+JSON.stringify(to));
    };
}