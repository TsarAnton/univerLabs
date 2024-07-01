function Point(x1, x2) {
    this.x1 = x1;
    this.x2 = x2;
    this.toStr = function() {
        let str = "( " + this.x1 + ", " + this.x2 + " )";
        return str;
    }
}

function checkatel(x1, y1, c, p) {
    return x1 * p.x1 + y1 * p.x2 + c;
}

function f(p) {
    //return p.x1 * p.x1 + 2 * p.x2 * p.x2 + p.x1 * p.x2 - 7 * p.x1 - 7 * p.x2;
    return - 6 * p.x1 - 4 * p.x2 + p.x1 * p.x1 + p.x2 * p.x2;
}

function grad(p) {
    //return  new Point(2 * p.x1 + p.x2 - 7, 4 * p.x2 + p.x1 - 7);
    return new Point(-6 + 2 * p.x1, -4 + 2 * p.x2);
}

//x1 + x2 <= 2, x1, x2 >= 0
function gradientYslov(x0, e, N) {
    let x = [null, x0];
    let gradF = [null];
    let xCh = [null];
    let points = [new Point(0, 0), new Point(0, 2), new Point(2, 0)];
    for(let k = 1;; k++) {
        console.log("Итерация " + (k));
        if(k === N) {
            console.log("   k = " + (k) + " = " + N);
            console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
            return x[k];
        }
        console.log("   k = " + (k) + " != " + N);
        gradF.push(grad(x[k]));
        console.log("   grad f(x" + (k) + ") = " + gradF[k].toStr());
        let check = Math.sqrt(gradF[k].x1 * gradF[k].x1 + gradF[k].x2 * gradF[k].x2);
        console.log("   ||grad f(x" + (k) + ")|| = " + check);
        if(check < e) {
            console.log("   Критерий остановки выполнен: " + check + " < " + e);
            console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
            return x[k];
        }
        console.log("   Критерий остановки не выполнен: " + check + " >= " + e);
        x.push(0);

        let x1 = gradF[k].x1;
        let y1 = gradF[k].x2;
        let c = -gradF[k].x1 * x[k].x1 - gradF[k].x2 * x[k].x2;
        let num1 = checkatel(x1, y1, c, points[0]);
        let num2 = checkatel(x1, y1, c, points[1]);
        let num3 = checkatel(x1, y1, c, points[2]);
        if(num1 <= num2 && num1 <= num3) {
            xCh.push(points[0]);
        } else if(num2 <= num1 && num2 <= num3) {
            xCh.push(points[1]);
        } else if(num3 <= num1 && num3 <= num2) {
            xCh.push(points[2]);
        }
        console.log("   (grad f(x[" + k + "]), x - x[" + k + "]) = " + x1 + " * x1 + " + y1 + " * x2 + " + c + " -> min");
        //console.log(num1 + " " + num2 + " " + num3)
        console.log("   _");
        console.log("   x = " + xCh[k].toStr())
        
        let O = (2 * x[k].x1 * gradF[k].x1 + 2 * x[k].x2 * gradF[k].x2 - 6 * gradF[k].x1 - 4 * gradF[k].x2) / (2 * Math.pow(gradF[k].x1, 2) + 2 * Math.pow(gradF[k].x2, 2));
        //console.log("\n   Величина шага O" + k + " = " + O);

        x[k + 1] = new Point(x[k].x1 + O * (xCh[k].x1 - x[k].x1), x[k].x2 + O * (xCh[k].x2 - x[k].x2));
        console.log("   x[" + (k + 1) + "] = " + x[k + 1].toStr());
        console.log("   f(x[" + (k + 1) + "]) = " + f(x[k+1]))

        let prove2 = Math.sqrt(Math.pow(x[k + 1].x1 - x[k].x1, 2) + Math.pow(x[k + 1].x2 - x[k].x2, 2));
        if(prove2 >= e) {
            console.log("   Условие не выполняется: ||x[" + (k + 1) +"] - x[" + (k) + "]|| = " + prove2 + " >= " + e);
            continue;
        }
        console.log("   Условие выполняется: ||x[" + (k + 1) +"] - x[" + (k) + "]|| = " + prove2 + " < " + e);

        console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
        return x[k];
    }
}

let x0 = new Point(0, 0);
let N = 5;
console.log("x1 = " + x0.toStr() + ", N = " + N);
console.log("Метод условного градиента:")
gradientYslov(x0, 0.1, N);
//console.log(gradient(new Point(0, 0), 0.1, 0.1, 4, [1, 0.25, 0.25, 0.25]));