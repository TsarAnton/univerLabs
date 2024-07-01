function get(element) {
    let str = "";
    str += element;
    if(str.length < 10) {
        len = 10 - str.length;
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

let f1 = function(x) {
    return 3 * x * x - 12 * x;
}

let f2 = function(x) {
    return Math.pow(Math.E, -x) + 9 * x * x;
}

function dichotomy(a, b, e, l, f) {
    printLine("k", ["ak", "bk", "bk - ak", "x1k", "x2k", "f(x1k)", "f(x2k)"]);
    for(let k = 1;;k++) {
        if(b - a < l) {
            let ret = (a + b) / 2;
            printLine(k, [a.toFixed(3), b.toFixed(3), (b - a).toFixed(3), "-", "-", "-", "-"]);
            console.log("Минимум: " + ret + ", f(" + ret + ") = " + f(ret))
            return ret;
        }
        let x1 = (a + b) / 2 - e;
        let x2 = (a + b) / 2 + e;
        printLine(k, [a.toFixed(3), b.toFixed(3), (b - a).toFixed(3), x1.toFixed(3), x2.toFixed(3), f(x1).toFixed(3), f(x2).toFixed(3)]);
        if(f(x1) <= f(x2)) {
            b = x2;
        } else {
            a = x1;
        }
    }
}

function gold(a, b, l, f) {
    printLine("k", ["ak", "bk", "bk - ak", "x1k", "x2k", "f(x1k)", "f(x2k)"]);
    let alpha = 0.618;
    let x1 = a + (1 - alpha) * (b - a);
    let x2 = a + alpha * (b - a);
    for(let k = 0;;k++) {
        printLine(k, [a.toFixed(3), b.toFixed(3), (b - a).toFixed(3), x1.toFixed(3), x2.toFixed(3), f(x1).toFixed(3), f(x2).toFixed(3)]);
        if(f(x1) <= f(x2)) {
            b = x2;
            x2 = x1;
            x1 = a + b - x1;
        } else {
            a = x1;
            x1 = x2;
            x2 = a + b - x2;
        }
        if(b - a <= l) {
            let ret = (a + b) / 2;
            printLine(k + 1, [a.toFixed(3), b.toFixed(3), (b - a).toFixed(3), "-", "-", "-", "-"]);
            console.log("Минимум: " + ret + ", f(" + ret + ") = " + f(ret))
            return ret;
        }
    }
}

function F(n) {
    if(n <= 1) {
        return 1;
    }
    let a = 1, b = 1;
    for(let i = 2; i <= n; i++) {
        let c = a;
        a += b;
        b = c;
    }
    return a;
}

let a1 = -1, b1 = 7;
let a2 = 0, b2 = 1;
let e1 = 0.1, l1 = 0.5;
let e2 = 0.01, l2 = 0.1;

console.log("Задача 1")
console.log("Метод дихотомического поиска:");
dichotomy(a1, b1, e1, l1, f1);
console.log("Метод 'золотого сечения':");
gold(a1, b1, l1, f1);

console.log("\nЗадача 2")
console.log("Метод дихотомического поиска:");
dichotomy(a2, b2, e2, l2, f2);
console.log("Метод 'золотого сечения':");
gold(a2, b2, l2, f2);
