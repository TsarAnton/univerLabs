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

function method(matrix, e) {
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
    
    let t = [null];
    let r = [];
    let X = [];
    X.push([]);
    for(let i = 0; i < n; i++) {
        X[0].push(0);
    }
    for(let k = 1;; k++) {
        X.push([]);
        r.push([]);
        for(let i = 0; i < n; i++) {
            let sum = 0;
            for(let j = 0; j < n; j++) {
                sum += A[i][j] * X[k - 1][j];
            }
            r[k - 1][i] = sum - f[i];
        }
        let sk1 = 0;
        let sk2 = 0;
        for(let i = 0; i < n; i++) {
            sk1 += r[k - 1][i] * r[k - 1][i];
            let sum = 0;
            for(let j = 0; j < n; j++) {
                sum += A[i][j] * r[k - 1][j]
            }
            sk2 += r[k - 1][i] * sum;
        }
        t.push(sk1 / sk2);
        for(let i = 0; i < n; i++) {
            X[k][i] = X[k - 1][i] - t[k] * r[k - 1][i];
        }
        let max = 0;
        for(let j = 0; j < n; j++) {
            let a = Math.abs(X[k - 1][j] - X[k][j]);
            if(a > max) {
                max = a;
            }
        }
        console.log("x" + k + ":");
        console.log(X[k]);
        console.log("||X" + k + " - X" + (k - 1) + "|| = " + max);
        if(max <= e) {
            console.log(max + " <= " + e);
            break;
        } else {
            console.log(max + " > " + e);
        }
        if(k > 100) {
            break;
        }
    }
    console.log("Ответ:");
    return X[X.length - 1];
}

let e = 0.001;

// let matrix = [[3.78, 1.08, -1.35, 0.35],
//                 [1.08, -2.28, 0.37, 1.27],
//                 [-1.35, 0.37, 2.86, 0.47]];

let matrix = [[2.5, -0.9, 0.2, -0.7],
                [-0.9, 3.8, -0.1, 2.5],
                [0.2, -0.1, 0.9, 0.1]];

console.log(method(matrix, e));