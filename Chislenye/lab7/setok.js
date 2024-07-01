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

function p(x) {
    return (4 * x) / (x * x + 1);
}

function g(x) {
    return - 1 / (x * x + 1);
}

function f(x) {
    return -3 / Math.pow(x * x + 1, 2);
}

function y(x) {
    return 1 / (x * x + 1);
}

let a = 0, b = 1;
let h = 0.1;
let n = Math.floor(((b - a) / h)) + 1;
let alpha1 = 0, alpha2 = 1, beta1 = 1, beta2 = 0, gama1 = 0, gama2 = 0.5;

let x = [];
for(let i = 0; i < n; i++) {
    x.push(a + h * i);
}

let matrix = [[alpha1 - beta1 / h, beta1 / h]];
for(let i = 0; i < n - 2; i++) {
    matrix[0].push(0);
}
matrix[0].push(gama1);
for(let i = 1; i < n - 1; i++) {
    matrix.push([]);
    for(let j = 0; j < n + 1; j++) {
        matrix[i].push(0);
    }
    matrix[i][i - 1] = 1 - (h * p(x[i])) / 2;
    matrix[i][i] = -2 + g(x[i]) * h * h;
    matrix[i][i + 1] = 1 + (h * p(x[i])) / 2;
    matrix[i][n] = f(x[i]) * h * h;
}
matrix.push([]);
for(let j = 0; j < n + 1; j++) {
    matrix[n - 1].push(0);
}
matrix[n - 1][n - 2] = - beta2 / h;
matrix[n - 1][n - 1] = alpha2 + beta2 / h;
matrix[n - 1][n] = gama2;

let Y = gauss(matrix);
console.log(Y);

let realY = [];
for(let i = 0; i < n; i++) {
    realY.push(y(x[i]));
}

console.log(realY);