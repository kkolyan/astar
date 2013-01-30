

function NodeManager() {
    var nodeMap = {};

    var openedNodes = new SortedStorage(function(o1,o2) {
        return o1.costG + o1.costH - o2.costG - o2.costH;
    });
    var serialNumber = 0;

    this.getNodeMap = function() {
        return nodeMap;
    };

    this.getBestOpened = function() {
        return openedNodes.first();
    };

    this.getNodeState = function(point) {
        return nodeMap[point.toIdString()];
    };

    this.closeNode = function(node) {
        var nodeInfo = nodeMap[node.point.toIdString()];
        nodeInfo.opened = false;
        openedNodes.remove(node);
    };

    this.openNode = function(node) {
        var nodeInfo = {};
        nodeInfo.node = node;
        nodeInfo.opened = true;
        nodeMap[node.point.toIdString()] = nodeInfo;
        openedNodes.add(node);

        node.point.costG = node.costG;
        node.point.costH = node.costH;
        node.point.number = serialNumber ++;
    };
}
