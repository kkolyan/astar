
function Point(x, y) {
    this.x = x;
    this.y = y;
}

Point.prototype.toIdString = function() {
    return this.x+","+this.y;
};