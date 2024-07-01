function Point(x1, x2) {
    this.x1 = x1;
    this.x2 = x2;
    this.toStr = function() {
        let str = "( " + this.x1 + ", " + this.x2 + " )";
        return str;
    }
}
let asd = function(x1, y1, c, p) {
    return x1 * p.x1 + y1 * p.x2 + c;
}
let x1 = 2, y1 = 2, c = -6
console.log(x1 + " * x + " + y1 + " * y + " + c);
console.log(asd(x1, y1, c, new Point(2, 0)))
console.log(asd(x1, y1, c, new Point(0, 2)))
console.log(asd(x1, y1, c, new Point(0, 0)))