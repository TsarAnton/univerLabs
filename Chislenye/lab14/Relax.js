function printMatrix(matrix) {
    let print = ""
    for(let array of matrix) {
        for(let el of array) {
            print += el + " ";
        }
        print += "\n"
    }
    console.log(print);
}

function prove(matrix) {
    let n = matrix.length;
    let max = 0;
    for(let i = 0; i < n; i++) {
        let sum = 0;
        for(let j = 0; j < n; j++) {
            sum += Math.abs(matrix[i][j]);
        }
        if(sum > max) {
            max = sum;
        }
    }
    return max < 1;
}

function method(matrix, e, w) {
    let n = matrix.length;
    let B = [], g = [];
    for(let i = 0; i < n; i++) {
        B.push([]);
        for(let j = 0; j < n; j++) {
            if(i !== j) {
                B[i].push(-matrix[i][j] / matrix[i][i]);
            } else {
                B[i].push(0);
            }
        }
        g.push(matrix[i][n] / matrix[i][i])
    }

    if(!prove(B)) {
        return "Достаточное условие не выполняется";
    }

    console.log("B:");
    printMatrix(B);
    console.log("g:");
    console.log(g);

    let X = [];
    X.push([...g]);
    console.log("x0:");
    console.log(X[0]);

    for(let i = 1;;i++) {
        X.push([]);
        for(let j = 0; j < n; j++) {
            let sum1 = 0;
            for(let k = 0; k < j; k++) {
                sum1 += X[i][k] * B[j][k];
            }
            let sum2 = 0;
            for(let k = j; k < n; k++) {
                sum2 += X[i - 1][k] * B[j][k];
            }
            X[i].push((1 - w) * X[i - 1][j] + w * (sum1 + sum2 + g[j]));
        }
        let max = 0;
        for(let j = 0; j < n; j++) {
            let a = Math.abs(X[i - 1][j] - X[i][j]);
            if(a > max) {
                max = a;
            }
        }
        console.log("x" + i + ":");
        console.log(X[i]);
        console.log("||X" + i + " - X" + (i - 1) + "|| = " + max);
        if(max <= e) {
            console.log(max + " <= " + e);
            break;
        } else {
            console.log(max + " > " + e);
        }
        if(i > 100) {
            break;
        }
    }
    console.log("Ответ:");
    return X[X.length - 1];
}

let matrix = [[4.6, 1.8, -1.7, 3.8],
                [2.7, -5.6, 1.9, 0.4],
                [1.5, 0.5, 3.3, -1.6]];
let e = 0.001;
let w = 0.5;
// let matrix = [[10, 1, 1, 12],
//                 [2, 10, 1, 13],
//                 [2, 2, 10, 14]];
// let e = 0.01;
printMatrix(matrix);
console.log(method(matrix, e, w));