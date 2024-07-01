function printMatrix(A) {
    let print = ""
    for(let array of A) {
        for(let el of array) {
            print += String(el).padEnd(22) + " ";
        }
        print += "\n"
    }
    console.log(print)
}
function transMatrix(A)
{
    var m = A.length, n = A[0].length, AT = [];
    for (var i = 0; i < n; i++)
     { AT[ i ] = [];
       for (var j = 0; j < m; j++) AT[ i ][j] = A[j][ i ];
     }
    return AT;
}
function multiplyMatrix(A,B)
{
    var rowsA = A.length, colsA = A[0].length,
        rowsB = B.length, colsB = B[0].length,
        C = [];
    if (colsA != rowsB) return false;
    for (var i = 0; i < rowsA; i++) C[ i ] = [];
    for (var k = 0; k < colsB; k++)
     { for (var i = 0; i < rowsA; i++)
        { var t = 0;
          for (var j = 0; j < rowsB; j++) t += A[ i ][j]*B[j][k];
          C[ i ][k] = t;
        }
     }
    return C;
}
function subMatrix(A,B)
{   
    var m = A.length, n = A[0].length, C = [];
    for (var i = 0; i < m; i++)
     { C[ i ] = [];
       for (var j = 0; j < n; j++) C[ i ][j] = A[ i ][j]-B[ i ][j];
     }
    return C;
}
function multMatrixNumber(a,A)
{   
    var m = A.length, n = A[0].length, B = [];
    for (var i = 0; i < m; i++)
     { B[ i ] = [];
       for (var j = 0; j < n; j++) B[ i ][j] = a*A[ i ][j];
     }
    return B;
}

function copyMatrix(A) {
    let B = [];
    for(let i = 0; i < A.length; i++) {
        B.push([]);
        for(let j = 0 ; j < A.length; j++) {
            B[i].push(A[i][j]);
        }
    }
    return B;
}

function check(A, e) {
    let n = A.length;
    let sum = 0
	for(let m = 0; m < n - 1; m++) {
        for(let i = m + 1; i < n; i++) {
            sum += Math.pow(A[i][m], 2);
        }
    }
    return Math.sqrt(sum) <= e;
}

let A = [[-16, -100, -190],
        [96, 380, 704],
        [-48, -182, -336]];
console.log("Начальная матрица:");
printMatrix(A)
let E = [[1, 0, 0],
        [0, 1, 0],
        [0, 0, 1]];
let e = 0.001;
let n = A.length;

for(let l = 0; true; l++) {
    let R = copyMatrix(A);
    let Q = null;
    for(let i = 0; i < n - 1; i++) {
        let S = 0;
        for(let j = i; j < n; j++) {
            S += Math.pow(R[j][i], 2);
        }
        S = Math.sqrt(S);
        let nu = Math.pow(2 * S * (S - R[i][i]), -0.5);
        let W = [];
        for(let j = 0; j < n; j++) {
            if(j < i) {
                W.push([0]);
            } else if (j == i) {
                W.push([nu * (R[i][i] - S)]);
            } else {
                W.push([nu * R[j][i]]);
            }
        }
        let H = subMatrix(E, multMatrixNumber(2, multiplyMatrix(W, transMatrix(W))));
        R = multiplyMatrix(H, R);
        if(i == 0) {
            Q = H;
        } else {
            Q = multiplyMatrix(Q, H);
        }
    }
    console.log("R" + l +":");
    printMatrix(R);
    console.log("Q" + l + ":");
    printMatrix(Q);
    A = multiplyMatrix(R, Q);
    if(check(A, e)) {
        console.log("A" + l + ":");
        printMatrix(A)
        console.log("Собственные значения:");
        for(let i = 0; i < n; i++) {
            console.log(A[i][i]);
        }
        break;
    }
}