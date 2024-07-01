function gauss(matrix) {
    let n = matrix.length;
    for(let i = 0; i < n; i++) {
        let max = 0;
        let maxLine = 0;
        for(let j = i; j < n; j++) {
            if(Math.abs(matrix[j][i]) > max) {
                max = Math.abs(matrix[j][i]);
                maxLine = j;
            }
        }
        let buff = matrix[i];
        matrix[i] = matrix[maxLine];
        matrix[maxLine] = buff;
        let a = matrix[i][i];
        for(let j = i; j <= n; j++) {
            matrix[i][j] /= a;
        }
        for(let j = i + 1; j < n; j++) {
            a = -matrix[j][i];
            for(let k = 0; k <= n; k++) {
                matrix[j][k] = matrix[i][k] * a + matrix[j][k];
            }
        }
    }
    let x = [];
    x[n - 1] = matrix[n - 1][n];
    for(let i = n - 2; i >= 0; i--) {
        let sum = 0;
        for(let j = i + 1; j < n; j++) {
            sum += matrix[i][j] * x[j];
        }
        x[i] = matrix[i][n] - sum;
    }
    return x;
}

function f(x, y, s) {
    return s;
}

function g1(x, y, s) {
    return (- 4 * x) / (x * x + 1) * s + 1 / (x * x + 1) * y - 3 / Math.pow(x * x + 1, 2);
    //return (- 1 / x) * s + 2 * y - 2 * x * x;
}

function g2(x, y, s) {
    return (- 4 * x) / (x * x + 1) * s + 1 / (x * x + 1) * y;
    //return (- 1 / x) * s + 2 * y
}

function y(x) {
    return 1 / (x * x + 1);
}

let a = 0, b = 1;
let h = 0.1;
let n = Math.floor(((b - a) / h)) + 1;
let alpha1 = 0, alpha2 = 1, beta1 = 1, beta2 = 0, gama1 = 0, gama2 = 0.5;
//let alpha1 = 0, alpha2 = 1, beta1 = 1, beta2 = 0, gama1 = 1, gama2 = 3;

let x = [];
for(let i = 0; i < n; i++) {
    x.push(a + h * i);
}

let Y0 = [0], S0 = [0], Y1 = [1], S1 = [0], Y2 = [0], S2 = [1];
for(let i = 0; i < n - 1; i++) {
    let k1 = f(x[i], Y0[i], S0[i]);
    let l1 = g1(x[i], Y0[i], S0[i]);
    let k2 = f(x[i] + h / 2, Y0[i] + (h / 2) * k1, S0[i] + (h / 2) * l1);
    let l2 = g1(x[i] + h / 2, Y0[i] + (h / 2) * k1, S0[i] + (h / 2) * l1);
    let k3 = f(x[i] + h / 2, Y0[i] + (h / 2) * k2, S0[i] + (h / 2) * l2);
    let l3 = g1(x[i] + h / 2, Y0[i] + (h / 2) * k2, S0[i] + (h / 2) * l2);
    let k4 = f(x[i] + h, Y0[i] + h * k3, S0[i] + h * l3);
    let l4 = g1(x[i] + h, Y0[i] + h * k3, S0[i] + h * l3);
    Y0[i + 1] = Y0[i] + (h / 6) * (k1 + 2 * k2 + 2 * k3 + k4);
    S0[i + 1] = S0[i] + (h / 6) * (l1 + 2 * l2 + 2 * l3 + l4);
}
for(let i = 0; i < n - 1; i++) {
    let k1 = f(x[i], Y1[i], S1[i]);
    let l1 = g2(x[i], Y1[i], S1[i]);
    let k2 = f(x[i] + h / 2, Y1[i] + (h / 2) * k1, S1[i] + (h / 2) * l1);
    let l2 = g2(x[i] + h / 2, Y1[i] + (h / 2) * k1, S1[i] + (h / 2) * l1);
    let k3 = f(x[i] + h / 2, Y1[i] + (h / 2) * k2, S1[i] + (h / 2) * l2);
    let l3 = g2(x[i] + h / 2, Y1[i] + (h / 2) * k2, S1[i] + (h / 2) * l2);
    let k4 = f(x[i] + h, Y1[i] + h * k3, S1[i] + h * l3);
    let l4 = g2(x[i] + h, Y1[i] + h * k3, S1[i] + h * l3);
    Y1[i + 1] = Y1[i] + (h / 6) * (k1 + 2 * k2 + 2 * k3 + k4);
    S1[i + 1] = S1[i] + (h / 6) * (l1 + 2 * l2 + 2 * l3 + l4);
}
for(let i = 0; i < n - 1; i++) {
    let k1 = f(x[i], Y2[i], S2[i]);
    let l1 = g2(x[i], Y2[i], S2[i]);
    let k2 = f(x[i] + h / 2, Y2[i] + (h / 2) * k1, S2[i] + (h / 2) * l1);
    let l2 = g2(x[i] + h / 2, Y2[i] + (h / 2) * k1, S2[i] + (h / 2) * l1);
    let k3 = f(x[i] + h / 2, Y2[i] + (h / 2) * k2, S2[i] + (h / 2) * l2);
    let l3 = g2(x[i] + h / 2, Y2[i] + (h / 2) * k2, S2[i] + (h / 2) * l2);
    let k4 = f(x[i] + h, Y2[i] + h * k3, S2[i] + h * l3);
    let l4 = g2(x[i] + h, Y2[i] + h * k3, S2[i] + h * l3);
    Y2[i + 1] = Y2[i] + (h / 6) * (k1 + 2 * k2 + 2 * k3 + k4);
    S2[i + 1] = S2[i] + (h / 6) * (l1 + 2 * l2 + 2 * l3 + l4);
}
console.log(Y0)
console.log(S0)
console.log(Y1)
console.log(S1)
console.log(Y2)
console.log(S2)

let matrix = [[alpha1, beta1, gama1],
                [alpha2 * Y1[n - 1] + beta2 * S1[n - 1], alpha2 * Y2[n - 1] + beta2 * S2[n - 1], gama2 - alpha2 * Y0[n - 1] - beta2 * S0[n - 1]]];
let c = gauss(matrix);
let c1 = c[0], c2 = c[1];
console.log("c1 = " + c1 + ", c2 = " + c2)

let Y = [];
for(let i = 0; i < n - 1; i++) {
    Y.push(Y0[i] + c1 * Y1[i] + c2 * Y2[i]);
}
Y.push(0.5)

let realY = [];
for(let i = 0; i < n; i++) {
    realY.push(y(x[i]));
}

console.log(Y);
//console.log(realY)