function findAB(matrix) {
    let n = matrix.length;

    let f = [];
    for(let i = 0; i < n; i++) {
        f.push(matrix[i][n]);
    }

    let a = null, b = matrix[0][0], c = matrix[0][1];
    let A = [-c / b];
    let B = [f[0] / b];
    for(let i = 1; i < n - 1; i++) {
        a = matrix[i][i - 1];
        b = matrix[i][i];
        c = matrix[i][i + 1];
        A.push(-c / (b + a * A[i - 1]));
        B.push((f[i] - a * B[i - 1]) / (b + a * A[i - 1]));
    }
    return { A: A, B: B };
}

function findX(matrix) {
    let n = matrix.length;
    let A = findAB(matrix).A;
    let B = findAB(matrix).B;
    console.log("A:");
    console.log(A);
    console.log("B:");
    console.log(B);

    let f = [];
    for(let i = 0; i < n; i++) {
        f.push(matrix[i][n]);
    }

    let x = [];
    for(let i = 0; i < n; i++) {
        x.push(null);
    }
    let a = matrix[n - 1][n - 2], b = matrix[n - 1][n - 1];
    x[n - 1] = (f[n - 1] - a * B[n - 2]) / (b + a * A[n - 2]);
    for(let i = n - 2; i >= 0; i--) {
        x[i] = A[i] * x[i + 1] + B[i];
    }
    console.log("X:");
    return x;
}

// let matrix = [[2, 1, 0, 0, 4],
//                 [2, 3, -1, 0, 9],
//                 [0, 1, -1, 3, 12],
//                 [0, 0, 1, -1, -4]];

let matrix = [[5, -2, 0, 0, 7],
                [1, -5, 2, 0, 13],
                    [0, 2.5, 3.5, 0.5, -2.5],
                    [0, 0, 1, -3, 7]];

console.log(findX(matrix));