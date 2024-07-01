function Point(x1, x2) {
    this.x1 = x1;
    this.x2 = x2;
    this.toStr = function() {
        let str = "( " + this.x1 + ", " + this.x2 + " )";
        return str;
    }
}

function f(p) {
    return p.x1 * p.x1 - 8 * p.x1 + p.x2 * p.x2;
}

function gradient(p) {
    return new Point(2 * p.x1 - 8, 2 * p.x2);
}

function gradientProection(x1, N, O) {
    let x = [null, x1];
    let gradF = [null];
    for(let k = 1;;k++) {
        console.log("Итерация " + k);
        if(k === N) {
            console.log("   k = " + k + " = " + N);
            console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
            return x[k];
        }
        console.log("   k = " + k + " != " + N);
        gradF.push(gradient(x[k]));
        console.log("   grad f(x" + k + ") = " + gradF[k].toStr());
        let check = Math.sqrt(gradF[k].x1 * gradF[k].x1 + gradF[k].x2 * gradF[k].x2);
        console.log("   ||grad f(x" + k + ")|| = " + check);
        if(check < 0.01) {
            console.log("   Критерий остановки выполнен: " + check + " < 0.01");
            console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
            return x[k];
        }
        console.log("   Критерий остановки не выполнен: " + check + " >= 0.01");
        //x1 ^ 2 + (x2 - 4) ^ 2 <= 9
        // x0 = (0, 4), R = 3
        console.log("   Величина шага O" + k + " = " + O[k]);

        let xBuff = new Point(x[k].x1 - O[k] * gradF[k].x1, x[k].x2 - O[k] * gradF[k].x2);
        if(xBuff.x1 * xBuff.x1 + Math.pow(xBuff.x2 - 4, 2) <= 9) {
            x.push(x);
        } else {
            let razn = new Point(xBuff.x1, xBuff.x2 - 4);
            let naz = Math.sqrt(razn.x1 * razn.x1 + razn.x2 * razn.x2);
            x.push(new Point((3 * razn.x1) / naz, 4 + (3 * razn.x2) / naz));
        }

        console.log("   x[" + (k + 1) + "] = " + x[k + 1].toStr());
        console.log("   f(x[" + (k + 1) + "]) = " + f(x[k+1]))
        let prove2 = Math.sqrt(Math.pow(x[k + 1].x1 - x[k].x1, 2) + Math.pow(x[k + 1].x2 - x[k].x2, 2));
        if(prove2 >= 0.01) {
            console.log("   Условие не выполняется: ||x[" + (k + 1) +"] - x[" + (k) + "]|| = " + prove2 + " >= 0.01");
            continue;
        }
        console.log("   Условие выполняется: ||x[" + (k + 1) +"] - x[" + (k) + "]|| = " + prove2 + " < 0.01");
        console.log("Минимум: " + x[k + 1].toStr() + ", f(" + x[k + 1].toStr() + ") = " + f(x[k + 1]));
    }
}

let x1 = new Point(1, 2);
let N = 5;
let O = [1, 1, 1, 1, 1];
console.log("x1 = " + x1.toStr() + ", N = " + N);
console.log("Метод проекции градиента:")
gradientProection(x1, N, O);