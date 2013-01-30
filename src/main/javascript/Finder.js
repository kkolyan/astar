
function Finder(data) {
    var source = data["source"];
    var destinations = data["destinations"];
    var area = data["area"];
    
    var nodeManager = new NodeManager();
    var destinationsHash = {};
    
    for (var i = 0; i < destinations.length; i ++) {
        destinationsHash[destinations[i].toIdString()] = true;
    }

    this.getNodeManager = function () {
        return nodeManager;
    };

    this.doSearch = function() {
        var node = {};
        node.point = source;
        node.costG = 0;
        node.costH = area.getHeuristicCost(node.point, destinations);
        node.parent = null;
        nodeManager.openNode(node);
        while (!this.done) {
            this.searchAroundBest();
        }
    };

    this.searchAroundBest = function() {
        var currentNode = nodeManager.getBestOpened();
        if (currentNode == null) {
            this.done = true;
            return;
        }
        nodeManager.closeNode(currentNode);

        var neighbors = area.getNeighbours(currentNode.point);
        for (var i in neighbors) {
            var neighborPoint = neighbors[i];
            if (destinationsHash[neighborPoint.toIdString()]) {
                this.constructPath(currentNode);
                this.done = true;
                return;
            }
            var nodeState = nodeManager.getNodeState(neighborPoint);
            if (nodeState != null) {
                if (nodeState.opened && currentNode.costG < nodeState.node.parent.costG) {
                    nodeState.node.parent = currentNode;
                }
            } else {
                var neighbor = {};
                neighbor.parent = currentNode;
                neighbor.point = neighborPoint;
                neighbor.costG = currentNode.costG + area.getStepCost(currentNode.point, neighborPoint);
                neighbor.costH = area.getHeuristicCost(neighbor.point, destinations);
                nodeManager.openNode(neighbor);
            }
        }
    };

    this.constructPath = function(parent) {
        var reversedList = [];
        while (parent.parent != null) {
            reversedList.push(parent.point);
            parent = parent.parent;
        }
        reversedList.reverse();
        this.path = reversedList;
    };
}
