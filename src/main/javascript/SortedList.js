
function SortedStorage(comparator) {
    var list = [];

    this.add = function(o) {
        list.push(o);
    };

    this.first = function() {
        list.sort(comparator);
        return list[0];
    };

    this.remove = function(element) {
        for (var i = 0; i < list.length; i ++) {
            if (list[i] == element) {
                list[i] = list[0];
                list.shift();
                return;
            }
        }
    };
}