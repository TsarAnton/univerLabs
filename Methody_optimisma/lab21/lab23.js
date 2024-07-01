function Point(x1, x2) {
    this.x1 = x1;
    this.x2 = x2;
    this.toStr = function() {
        let str = "( " + this.x1 + ", " + this.x2 + " )";
        return str;
    }
}

function f(p) {
    //return p.x1 * p.x1 + 2 * p.x2 * p.x2 + p.x1 * p.x2 - 7 * p.x1 - 7 * p.x2;
    return - 8 * p.x1 - 6 * p.x2 + 2 * p.x1 * p.x1 + 2 * p.x2 * p.x2;
}

function grad(p) {
    //return  new Point(2 * p.x1 + p.x2 - 7, 4 * p.x2 + p.x1 - 7);
    return new Point(-8 + 4 * p.x1, -6 + 4 * p.x2);
}

function gradient(x0, e1, e2, N) {
    O = []
    for(let i = 0; i < N; i++) {
        O.push(1);
    }
    let x = [x0];
    let gradF = [];
    for(let k = 0;; k++) {
        console.log("Итерация " + (k + 1));
        gradF.push(grad(x[k]));
        console.log("   grad f(x" + (k + 1) + ") = " + gradF[k].toStr());
        let check = Math.sqrt(gradF[k].x1 * gradF[k].x1 + gradF[k].x2 * gradF[k].x2);
        console.log("   ||grad f(x" + (k + 1) + ")|| = " + check);
        if(check < e1) {
            console.log("   Критерий остановки выполнен: " + check + " < " + e1);
            console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
            console.log("Максимум изначальной задачи: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + (-f(x[k])));
            return x[k];
        }
        console.log("   Критерий остановки не выполнен: " + check + " >= " + e1);
        if(k === N - 1) {
            console.log("   k = " + (k + 1) + " = " + N);
            console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
            console.log("Максимум изначальной задачи: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + (-f(x[k])));
            return x[k];
        }
        console.log("   k = " + (k + 1) + " != " + N);
        x.push(0);
        while(true) {
            console.log("\n   Величина шага O" + (k + 1) + " = " + O[k]);
            x[k + 1] = new Point(x[k].x1 - O[k] * gradF[k].x1, x[k].x2 - O[k] * gradF[k].x2);
            console.log("   x[" + (k + 2) + "] = " + x[k + 1].toStr());
            if(f(x[k + 1]) < f(x[k])) {
                console.log("   f(x" + (k + 2) + ") = " + f(x[k + 1]) + " < f(x" + (k + 1) + ") = " + f(x[k]))
                let prove2 = Math.sqrt(Math.pow(x[k + 1].x1 - x[k].x1, 2) + Math.pow(x[k + 1].x2 - x[k].x2, 2));
                if(prove2 >= e2) {
                    console.log("   Условие не выполняется: ||x[" + (k + 2) +"] - x[" + (k + 1) + "]|| = " + prove2 + " >= " + e2);
                    break;
                }
                console.log("   Условие выполняется: ||x[" + (k + 2) +"] - x[" + (k + 1) + "]|| = " + prove2 + " < " + e2);
                let prove4 = Math.abs(f(x[k + 1]) - f(x[k]));
                if(prove4 >= e2) {
                    console.log("   Условие не выполняется: ||f(x[" + (k + 2) +"]) - f(x[" + (k + 1) + "])| = " + prove4 + " >= " + e2);
                    break;
                }
                console.log("   Условие выполняется: ||f(x[" + (k + 2) +"]) - f(x[" + (k + 1) + "])|| = " + prove4 + " < " + e2);4
                console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
                console.log("Максимум изначальной задачи: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + (-f(x[k])));
                return x[k];
            } else {
                console.log("   f(x" + (k + 2) + ") = " + f(x[k + 1]) + " >= f(x" + (k + 1) + ") = " + f(x[k]))
                O[k] = O[k] / 2;
            }
        }
    }
}

function fast(x0, e1, e2, N) {
    let x = [x0];
    let gradF = [];
    for(let k = 0;; k++) {
        console.log("Итерация " + (k + 1));
        gradF.push(grad(x[k]));
        console.log("   grad f(x" + (k + 1) + ") = " + gradF[k].toStr());
        let check = Math.sqrt(gradF[k].x1 * gradF[k].x1 + gradF[k].x2 * gradF[k].x2);
        console.log("   ||grad f(x" + (k + 1) + ")|| = " + check);
        if(check < e1) {
            console.log("   Критерий остановки выполнен: " + check + " < " + e1);
            console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
            console.log("Максимум изначальной задачи: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + (-f(x[k])));
            return x[k];
        }
        console.log("   Критерий остановки не выполнен: " + check + " >= " + e1);
        if(k === N - 1) {
            console.log("   k = " + (k + 1) + " = " + N);
            console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
            console.log("Максимум изначальной задачи: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + (-f(x[k])));
            return x[k];
        }
        console.log("   k = " + (k + 1) + " != " + N);
        x.push(0);
        
        let O = (4 * x[k].x1 * gradF[k].x1 + 4 * x[k].x2 * gradF[k].x2 - 8 * gradF[k].x1 - 6 * gradF[k].x2) / (4 * Math.pow(gradF[k].x1, 2) + 4 * Math.pow(gradF[k].x2, 2));
        console.log("\n   Величина шага O" + (k + 1) + " = " + O);

        x[k + 1] = new Point(x[k].x1 - O * gradF[k].x1, x[k].x2 - O * gradF[k].x2);
        console.log("   x[" + (k + 2) + "] = " + x[k + 1].toStr());

        let prove2 = Math.sqrt(Math.pow(x[k + 1].x1 - x[k].x1, 2) + Math.pow(x[k + 1].x2 - x[k].x2, 2));
        if(prove2 >= e2) {
            console.log("   Условие не выполняется: ||x[" + (k + 2) +"] - x[" + (k + 1) + "]|| = " + prove2 + " >= " + e2);
            continue;
        }
        console.log("   Условие выполняется: ||x[" + (k + 2) +"] - x[" + (k + 1) + "]|| = " + prove2 + " < " + e2);
        let prove4 = Math.abs(f(x[k + 1]) - f(x[k]));
        if(prove4 >= e2) {
            console.log("   Условие не выполняется: ||f(x[" + (k + 2) +"]) - f(x[" + (k + 1) + "])| = " + prove4 + " >= " + e2);
            continue;
        }
        console.log("   Условие выполняется: ||f(x[" + (k + 2) +"]) - f(x[" + (k + 1) + "])|| = " + prove4 + " < " + e2);

        console.log("Минимум: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + f(x[k]));
        console.log("Максимум изначальной задачи: " + x[k].toStr() + ", f(" + x[k].toStr() + ") = " + (-f(x[k])));
        return x[k];
    }
}

let x0 = new Point(2, 1);
console.log("f(x) = -8 * x1 - 6 * x2 + 2 * x1 ^ 2 + 2 * x2 ^ 2");
console.log("grad f(x) = (-8 + 4 * x1, -6 + 4 * x2)");
console.log("Градиентный метод:")
console.log("x1 = (2, 1), e1 = 0.1, e2 = 0.1, N = 4");
gradient(x0, 0.1, 0.1, 4);

console.log("\n\nМетод наискорейшего спуска:")
console.log("x1 = (2, 1), e1 = 0.1, e2 = 0.1, N = 4");
fast(x0, 0.1, 0.1, 4);
//console.log(gradient(new Point(0, 0), 0.1, 0.1, 4, [1, 0.25, 0.25, 0.25]));