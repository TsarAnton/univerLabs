function printMatrix(matrix) {
    let print = ""
    for(let array of matrix) {
        for(let el of array) {
            print += String(el).padEnd(22) + " ";
        }
        print += "\n"
    }
    console.log(print)
}

function multiplyMatrix(A, B) {
    let res = [];
    for(let i = 0; i < B.length; i++) {
        let el = 0;
        for(let j = 0; j < B.length; j++) {
            el += B[j] * A[i][j];
        }
        res.push(el);
    }
    return res;
}
let A = [[26.75, 54.875, 81.25, 134.625],
        [-36.5, -67.75, -95.5, -153.25],
        [-5.25, -14.125, -22.75, -40.375],
        [27.5, 43.25, 56.5, 83.75]];
let e = 0.001;
//let A = [[2, 1, 1], [1, 2.5, 1], [1, 1, 3]];
let n = 4;
let Y = [[]];
for(let i = 0; i < n; i++) {
    Y[0].push(1);
}
let a = [null];

for(let i = 1; true; i++) {
    Y.push(multiplyMatrix(A, Y[i - 1]));
    a.push(Y[i][0] / Y[i - 1][0]);
    if(i != 1 && Math.abs(a[i] - a[i - 1]) < e) {
        console.log("Итерация = " + i);
        console.log("Наибольшее по модулю собственное занчение = " + a[i]);
        console.log("Собственный вектор:");
        for(let j = 0; j < n; j++) {
            Y[i][j] /= Y[i][n - 1];
        }
        console.log(Y[i]);
        break;
    }
}