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

function findU(A, L, U, i, k) {
    let sum = 0;
    for(let j = 0; j < i; j++) {
        sum += L[i][j] * U[j][k];
    }
    return (A[i][k] - sum) / L[i][i];
}

function findL(A, L, U, i, k) {
    let sum = 0;
    for(let j = 0; j < k; j++) {
        sum += L[i][j] * U[j][k];
    }
    return A[i][k] - sum;
}

function findLU(matrix) {
    let n = matrix.length;
    let A = [];
    for(let i = 0; i < n; i++) {
        A.push([]);
        for(let j = 0; j < n; j++) {
            A[i].push(matrix[i][j]);
        }
    }
    let f = [];
    for(let i = 0; i < n; i++) {
        f.push(matrix[i][n]);
    }

    let U = [], L = [];
    for(let i = 0; i < n; i++) {
        U.push([]);
        L.push([]);
        for(let j = 0; j < n; j++) {
            U[i].push(null);
            L[i].push(null);
        }
    }

    for(let i = 0; i < n; i++) {
        U[i][i] = 1;
        for(let j = i - 1; j >= 0; j--) {
            U[i][j] = 0;
        }
    }
    for(let i = 0; i < n; i++) {
        for(let j = i + 1; j < n; j++) {
            L[i][j] = 0;
        }
    }

    for(let k = 0; k < n; k++) {
        for(let i = 0; i < n; i++) {
            if(i < k) {
                U[i][k] = findU(A, L, U, i, k);
            } else {
                L[i][k] = findL(A, L, U, i, k);
            }
        }
    }

    return {
        L: L,
        U: U,
    };
}

function determinant(matrix) {
    let L = findLU(matrix).L;
    let det = 1;
    for(let i = 0; i < L.length; i++) {
        det *= L[i][i];
    }
    return det;
}

function matrixLU(matrix) {
    let n = matrix.length;
    let Y = [];
    let X = [];
    let E = [];
    for(let i = 0; i < n; i++) {
        Y.push([]);
        E.push([]);
        X.push([]);
        for(let j = 0; j < n; j++) {
            if(i === j) {
                E[i].push(1);
            } else {
                E[i].push(0);
            }
            Y[i].push("-");
            X[i].push("-");
        }
    }
    let L = findLU(matrix).L;
    let U = findLU(matrix).U;
    console.log("L:");
    printMatrix(L);
    console.log("U:");
    printMatrix(U);
    console.log("Y:");
    printMatrix(Y);

    for(let i = 0; i < n; i++) {
        for(let j = 0; j < n; j++) {
            let sum = 0;
            for(let k = 0; k < j; k++) {
                sum += Y[i][k] * L[j][k];
            }
            Y[i][j] = (E[i][j] - sum) / L[j][j];
        }
        printMatrix(Y)
    }

    console.log("X:")
    for(let i = 0; i < n; i++) {
        X[n - 1][i] = Y[i][n - 1];
        for(let j = n - 2; j >= 0; j--) {
            let sum = 0;
            for(let k = j + 1; k < n; k++) {
                sum += U[j][k] * X[k][i];
            }
            X[j][i] = Y[i][j] - sum;
        }
        printMatrix(X);
    }
    return X;
}

function matrixGardanGauss(matrix) {
    let n = matrix.length;
    for(let i = 0; i < n; i++) {
        for(let j = 0; j < n; j++) {
            if(i === j) {
                matrix[i].push(1);
            } else {
                matrix[i].push(0);
            }
        }
    }
    for(let i = 0; i < n; i++) {
        printMatrix(matrix);

        let buff = matrix[i][i];
        for(let j = 0; j < 2 * n; j++) {
            matrix[i][j] /= buff;
        }
        printMatrix(matrix);
        
        for(let j = 0; j < n; j++) {
            if(i !== j) {
                buff = -matrix[j][i];
                for(let k = 0; k < 2 * n; k++) {
                    matrix[j][k] = matrix[i][k] * buff + matrix[j][k];
                }
            }
        }
    }
    printMatrix(matrix)

    let result = [];
    for(let i = 0; i < n; i++) {
        result.push([]);
        for(let j = n; j < 2 * n; j++) {
            result[i].push(matrix[i][j]);
        }
    }
    return result;
}

let A = [[1, 2, 0, 1],
            [-1, -3, 3, -1],
            [0, 4, -10, 2],
            [1, -1, 2, -1]];
// let A = [[2, -4, 6],
//         [-3, 7, -5],
//         [-1, 4, 8]];

console.log("Определитель: " + determinant(A));
console.log("Обратная матрица LU-разложение:");
printMatrix(matrixLU(A));
console.log("Обратная матрица Жардан-Гаусс:");
printMatrix(matrixGardanGauss(A));