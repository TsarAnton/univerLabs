function printMatrix(matrix) {
    let print = ""
    for(let array of matrix) {
        for(let el of array) {
            print += el + " ";
        }
        print += "\n"
    }
    console.log(print)
}

function prove(matrix) {
    let n = matrix.length;
    for(let i = 0; i < n; i++) {
        let sum = 0;
        for(let j = 0; j < n; j++) {
            if(i !== j) {
                sum += Math.abs(matrix[i][j]);
            }
        }
        if(Math.abs(matrix[i][i]) <= sum) {
            return false;
        }
    }
    return true;
}

function method(matrix, e) {
    if(!prove(matrix)) {
        return "Условие диагонального преобладания не выполняется";
    } else {
        console.log("Условие диагонального преобладания выполняется");
    }

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
            let sum = 0;
            for(let k = 0; k < n; k++) {
                sum += X[i - 1][k] * B[j][k];
            }
            X[i].push(sum + g[j]);
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
    }
    console.log("Ответ:");
    return X[X.length - 1];
}

let matrix = [[4.6, 1.8, -1.7, 3.8],
                [2.7, -5.6, 1.9, 0.4],
                [1.5, 0.5, 3.3, -1.6]];
let e = 0.001;
// let matrix = [[10, 1, 1, 12],
//                 [2, 10, 1, 13],
//                 [2, 2, 10, 14]];
// let e = 0.01;
printMatrix(matrix);
console.log(method(matrix, e));