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

function sqrtMethod(matrix) {
    let n = matrix.length;
    let A = [];
    let f = [];
    let S = [];
    for(let i = 0; i < n; i++) {
        A.push([]);
        S.push([]);
        for(let j = 0; j < n; j++) {
            S[i].push("-");
            A[i].push(matrix[i][j]);
        }
        f.push(matrix[i][n]);
    }

    printMatrix(S);
    for(let i = 0; i < n; i++) {
        for(let j = 0; j < n; j++) {
            printMatrix(S)
            if(i > j) {
                S[i][j] = 0;
            } else if(i < j) {
                let sum = 0;
                for(let k = 0; k < i; k++) {
                    sum += S[k][i] * S[k][j];
                }
                S[i][j] = (A[i][j] - sum) / S[i][i];
            } else {
                let sum = 0;
                for(let k = 0; k < i; k++) {
                    sum += S[k][i] * S[k][i];
                }
                S[i][i] = Math.sqrt(A[i][i] - sum);
            }
        }
    }
    console.log("Матрица S:");
    printMatrix(S);

    St = [];
    for(let i = 0; i < n; i++) {
        St.push([]);
        for(let j = 0; j < n; j++) {
            St[i].push(S[j][i]);
        }
    }
    console.log("Матрица S транспонированная:");
    printMatrix(St);

    let y = [];
    for(let i = 0; i < n; i++) {
        let sum = 0;
        for(let j = 0; j < i; j++) {
            sum += y[j] * St[i][j];
        }
        y.push((f[i] - sum) / St[i][i]);
    }
    console.log("Y:");
    console.log(y);

    let x = [];
    for(let i = 0; i < n; i++) {
        x.push(null);
    }
    x[n - 1] = y[n - 1] / S[n - 1][n - 1];
    for(let i = n - 2; i >= 0; i--) {
        let sum = 0;
        for(let j = i + 1; j < n; j++) {
            sum += S[i][j] * x[j];
        }
        x[i] = (y[i] - sum) / S[i][i];
    }
    console.log("X:");
    return x;
}

let matrix = [[3.14, -0.12, 1.17, 1.27],
                [-0.12, 2.32, -1.45, 2.13],
                [1.17, -1.45, 5.18, 3.14]];

console.log(sqrtMethod(matrix));