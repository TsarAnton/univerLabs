function Pair(x, p) {
    this.x = x;
    this.p = p;
}

function get(element) {
    let str = "";
    str += element;
    if(str.length < 25) {
        len = 25 - str.length;
        for(let i = 0; i < len; i++) {
            str += " ";
        }
    }
    return str;
}

function printLine(name, array) {
    let str = "";
    str += get(name);
    for(let i = 0; i < array.length; i++) {
        if(array[i] !== null) {
            str += get(array[i]);
        } else {
            str += get("-");
        }
    }
    console.log(str);
}

function f(x) {
    return Math.log(x) + 0.1 * Math.sin(0.1 * x);
}
let a = 9, b = 11;

console.log("Метод перебора:");
printLine("i", ["x[i]", "f(x[i])"]);
let n = 100;
let h = (b - a) / n;
let minY = f(a), minX = a;
for(let i = 0; i <= n; i++) {
    let newX = a + h * i;
    printLine(i, [newX, f(newX)])
    if(f(newX) < minY) {
        minY = f(newX);
        minX = newX
    }
}

console.log("Минимум функции методом перебора f(" + minX + ") = " + minY);

console.log("\nМетод ломанной:")

let e = 0.01

// |f'(x)| = |1 / x + 0.01 * cos(0.1 * x)| < 0.12

let L = 0.12

let array = [];

array.push(new Pair(1 / (2 * L) * (f(a) - f(b) + L * (a + b)), 0.5 * (f(a) + f(b) + L * (a - b))));

printLine("n", ["Исключаемая пара (x, p)", "^n", "2 * L * ^n", "Включаемые пары (x, p)"]);

for(let k = 1;;k++) {
    let minP = array[0].p, minX = array[0].x, minIndex = 0;
    for(let i = 1; i < array.length; i++) {
        if(array[i].p < minP) {
            minP = array[i].p;
            minX = array[i].x;
            minIndex = i;
        }
    }
    let delta = 1 / (2 * L) * (f(minX) - minP);
    let newP = 0.5 * (f(minX) + minP);
    array.push(new Pair(minX - delta, newP));
    array.push(new Pair(minX + delta, newP));
    array.splice(minIndex, 1);
    if(2 * L * delta <= e) {
        printLine(k, ["(" + minX.toFixed(4) + ", " + minP.toFixed(4) + ")", delta.toFixed(4), (2 * L * delta).toFixed(4), "-", "-"])
        console.log("Минимум функции методом ломанной f(" + minX + ") = " + f(minX));
        break;
    } else {
        printLine(k, ["(" + minX.toFixed(4) + ", " + minP.toFixed(4) + ")", delta.toFixed(4), (2 * L * delta).toFixed(4), "(" + (minX - delta).toFixed(4) + ", " + newP.toFixed(4) + ")", "(" + (minX + delta).toFixed(4) + ", " + newP.toFixed(4) + ")"])
    }
}