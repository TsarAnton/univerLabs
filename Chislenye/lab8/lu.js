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
    let str = "";
    for(let j = 0; j < i; j++) {
        sum += L[i][j] * U[j][k];
        str += L[i][j] + " * " + U[j][k] + " + "
    }
    console.log("u" + (i + 1) + (k + 1) + " = (" + A[i][k] + " - " + str + ") / " + L[i][i]);
    console.log("u" + (i + 1) + (k + 1) + " = " + ((A[i][k] - sum) / L[i][i]));
    return (A[i][k] - sum) / L[i][i];
}

function findL(A, L, U, i, k) {
    let sum = 0;
    let str = "";
    for(let j = 0; j < k; j++) {
        sum += L[i][j] * U[j][k];
        str += L[i][j] + " * " + U[j][k] + " + "
    }
    console.log("l" + (i + 1) + (k + 1) + " = " + A[i][k] + " - " + str);
    console.log("l" + (i + 1) + (k + 1) + " = " + ((A[i][k] - sum)));
    return A[i][k] - sum;
}

function lu(matrix) {
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
            U[i].push("-");
            L[i].push("-");
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
    console.log("U:");
    printMatrix(U);
    console.log("L:");
    printMatrix(L);

    for(let k = 0; k < n; k++) {
        for(let i = 0; i < n; i++) {
            if(i < k) {
                U[i][k] = findU(A, L, U, i, k);
            } else {
                L[i][k] = findL(A, L, U, i, k);
            }
            console.log("U:");
            printMatrix(U);
            console.log("L:");
            printMatrix(L);
        }
    }

    let g = [];
    for(let i = 0; i < n; i++) {
        let sum = 0;
        for(let j = 0; j < i; j++) {
            sum += g[j] * L[i][j];
        }
        g.push((f[i] - sum) / L[i][i]);
    }
    console.log(g)
    
    let x = [];
    for(let i = 0; i < n; i++) {
        x.push(null);
    }
    x[n - 1] = g[n - 1];
    for(let i = n - 2; i >= 0; i--) {
        let sum = 0;
        for(let j = i + 1; j < n; j++) {
            sum += U[i][j] * x[j];
        }
        x[i] = g[i] - sum;
    }
    return x;
}

let matrix =[[5, 4, -2, 5],
            [12, -2, 2, -18],
            [2, -1, 1, -5]];
// let matrix = [[3, -1, 0, 5],
// [-2, 1, 1, 0],
// [2, -1, 4, 15]];

console.log(lu(matrix))