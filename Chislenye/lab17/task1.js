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

function transMatrix(A) {
    let m = A.length, n = A[0].length, AT = [];
    for (let i = 0; i < n; i++)
     { AT[ i ] = [];
       for (let j = 0; j < m; j++) AT[ i ][j] = A[j][ i ];
     }
    return AT;
}

function multiplyMatrix(a, b) {
	let x = a.length,
		z = a[0].length,
		y = b[0].length;
	let productRow = Array.apply(null, new Array(y)).map(Number.prototype.valueOf, 0);
	let product = new Array(x);
	for (let p = 0; p < x; p++) {
		product[p] = productRow.slice();
	}
	for (let i = 0; i < x; i++) {
		for (let j = 0; j < y; j++) {
			for (let k = 0; k < z; k++) {
				product[i][j] += a[i][k] * b[k][j];
			}
		}
	}
	return product;
}

function maxEl(A) {
    let n = A.length;
    let max = Number.MIN_SAFE_INTEGER;
	let maxI = 0;
	let maxK = 0;
	for (let i = 0; i < n; i++) {
		for (let k = i + 1; k < n; k++) {
			if (Math.abs(A[i][k]) > max) {
				max = Math.abs(A[i][k]);
				maxI = i;
				maxK = k;
			}
		}
	}
    return {
        maxI: maxI,
        maxK: maxK,
    };
}

function createT(n, sinF, cosF, maxIndexes) {
    let retMatrix = [];
    let maxI = maxIndexes.maxI, maxK = maxIndexes.maxK;
    for(let i = 0; i < n; i++) {
        retMatrix.push([]);
        for(let k = 0 ; k < n; k++) {
            if(i == maxI && k == maxK) {
                retMatrix[i].push(-sinF);
            } else if(i == maxI && k == maxI) {
                retMatrix[i].push(cosF);
            } else if(i == maxK && k == maxK) {
                retMatrix[i].push(cosF);
            } else if(i == maxK && k == maxI) {
                retMatrix[i].push(sinF);
            } else if(i == k) {
                retMatrix[i].push(1);
            } else {
                retMatrix[i].push(0);
            }
        }
    }
    return retMatrix;
}

function check(A, e) {
    let n = A.length;
    let sum = 0
	for (let i = 0; i < n; i++) {
		for (let j = i + 1; j < n; j++) {
			sum = sum + Math.pow(A[i][j], 2)
		}
	}
    return Math.sqrt(sum) <= e;
}

let A = [[2, 1.2, -1, 1],
        [1.2, 0.5, 2, -1],
        [-1, 2, -1.5, 0.2],
        [1, -1, 0.2, 1.5]];
// let A = [[2.2, 1, 0.5, 2],
//         [1, 1.3, 2, 1],
//         [0.5, 2, 0.5, 1.6],
//         [2, 1, 1.6, 2]];
console.log("Начальная матрица:")
printMatrix(A)

let n = A.length;
let e1 = 0.001;
let S = null;

for(let j = 1; true; j++) {
    let maxIndexes = maxEl(A);
    let i = maxIndexes.maxI;
    let k = maxIndexes.maxK;
    let z = Math.sqrt(1 - (Math.pow(2 * A[i][k], 2)) / (Math.pow((A[i][i] - A[k][k]), 2) + Math.pow(2 * A[i][k], 2)))
    let e = A[i][i] === A[k][k] ? Math.sign(A[i][k]) : Math.sign((A[i][i] - A[k][k]) / A[i][k]);
    let cosF = Math.sqrt((1 + z) / 2);
    let sinF = e * Math.sqrt((1 - z) / 2);
    let T = createT(n, sinF, cosF, maxIndexes);
    let B = multiplyMatrix(multiplyMatrix(transMatrix(T), A), T);
    if(j == 1) {
        S = T;
    } else {
        S = multiplyMatrix(S, T);
    }
    // console.log("T" + j + ":");
    // printMatrix(T);
    // console.log("B" + j + ":");
    // printMatrix(B);
    if(check(B, e1)) {
        console.log("T" + j + ":");
        printMatrix(T);
        console.log("B" + j + ":");
        printMatrix(B);
        let g = [];
        for(let l = 0; l < n; l++) {
            g.push(B[l][l]);
        }
        console.log("Собственные значения:");
        console.log(g);
        console.log("S:");
        printMatrix(S);
        console.log("Собственные векторы:");
        let arrG = [];
        for(let l1 = 0; l1 < n; l1++) {
            arrG.push([]);
            for(let l2 = 0; l2 < n; l2++) {
                arrG[l1].push(S[l2][l1]);
            }
            console.log(arrG[l1]);
        }
        break;
    }
    A = B;
}

