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

function check(matrix, i) {
    for(let j = 0; j < matrix[i].length - 1; j++) {
        if(matrix[i][j] !== 0) {
            return true;
        }
    }
    if(matrix[i][matrix[i].length - 1] !== 0) {
        return false;
    }
    return true;
}

function isZero(matrix, i) {
    for(let el of matrix[i]) {
        if(el !== 0) {
            return false;
        }
    }
    return true;
}

function compareArrays(arr1, arr2) {
    for(let i = 0; i < arr1.length; i++) {
        if(arr1[i] !== arr2[i]) {
            return false;
        }
    }
    return true;
}

function gauss(matrix) {
    let n = matrix.length;
    let inf = false;

    let x = [];
    for(let i = 0; i < n; i++) {
        x.push("x" + (i + 1));
    }

    printMatrix(matrix);
    let buff;
    let s = 0;
    for(let i = 0; i < matrix.length; i++) {
        let flag = false;
        if(!check(matrix, i)) {
            return "Система не имеет решений";
        }
        printMatrix(matrix);
        if(matrix[i][i + s] == 0) {
            inf = true;
            buff = 1;
            for(let j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] !== 0) {
                    buff = matrix[i][j];
                    break;
                }
            }
            for(let j = 0; j <= n; j++) {
                matrix[i][j] = matrix[i][j] / buff;
            }
            printMatrix(matrix);
            let changed = false;
            for(let j = i + 1; j < matrix.length; j++) {
                if(matrix[j][i + s] !== 0) {
                    let a = matrix[i];
                    matrix[i] = matrix[j];
                    matrix[j] = a;
                    i--;
                    changed = true;
                    break;
                }
            }
            if(!changed) {
                if(isZero(matrix, i)) {
                    let a = matrix[i];
                    matrix[i] = matrix[n - 1];
                    matrix[n - 1] = a;
                }
                s++;
                flag = true;
                i--;
            }
        } else if(matrix[i][i + s] !== undefined) {
            buff = matrix[i][i + s];
            for(let j = 0; j <= n; j++) {
                matrix[i][j] = matrix[i][j] / buff;
            }
        }
        if(!flag) {
            printMatrix(matrix);
            for(let j = i + 1; j < matrix.length; j++) {
                buff = -matrix[j][i + s];
                for(let k = 0; k < matrix[j].length; k++) {
                    matrix[j][k] = matrix[i][k] * buff + matrix[j][k];
                }
            }
            printMatrix(matrix);
        }
    }
    for(let i = 0; i < matrix.length; i++) {
        for(let j = 0; j < matrix.length; j++) {
            if(i != j && compareArrays(matrix[i], matrix[j])) {
                matrix.splice(j, 1);
                j--;
            }
        }
        if(isZero(matrix, i)) {
            matrix.splice(i, 1);
            i--; 
        }
    }
    printMatrix(matrix)
    
    if(inf) {
        for(let i = 0; i < matrix.length; i++) {
            x[matrix[i].indexOf(1)] = null;
        }
        for(let i = matrix.length - 1; i >= 0; i--) {
            let i1 = matrix[i].indexOf(1);
            if(x[i1] === null) {
                let str = "";
                for(let j = i1 + 1; j < n; j++) {
                    str += "-(" + matrix[i][j] + " * (" + x[j] + ")) + ";
                }
                str += matrix[i][n];
                x[i1] = str;
            }
        }
    } else {
        x[n - 1] = matrix[n - 1][n];
        for(let i = n - 2; i >= 0; i--) {
            let sum = 0;
            for(let j = i + 1; j < n; j++) {
                sum += matrix[i][j] * x[j];
            }
            x[i] = matrix[i][n] - sum;
        }
    }
    return x;
}

// let matrix =[[1.7, -1.8, 1.9, -57.4, 10],
//             [1.1, -4.3, 1.5, -1.7, 19],
//             [1.2, 1.4, 1.6, 1.8, 20],
//             [7.1, -1.3, -4.1, 5.2, 10]];
// let matrix = [[2, 3, -1, 1, 1],
//                 [8, 12, -9, 8, 3],
//                 [4, 6, 3, -2, 3],
//                 [2, 3, 9, -7, 3]];
// let matrix = [[1, 1, 1],
//                 [0, 0, 1]];
// let matrix= [[1, 2, 3, 4, 5, 6, 10],
//                 [2, 4, 6, 8, 10, 12, 20],
//                 [1, 1, 1, 1, 1, 1, 2],
//                 [2, 2, 2, 2, 2, 2, 4],
//                 [1, 2, 1, 2, 1, 2, 4],
//                 [2, 4, 2, 4, 2, 4, 8]];
// let matrix = [[1, 2, 3, 4, 10],
//                 [2, 4, 6, 8, 20],
//                 [1, 2, 1, 2, 6],
//                 [1, 2, 2, 1, 6]];
let matrix =[[1, 2, 3, 4, 10],
                [0, 0, 1, 1, 2],
                [0, 0, 2, 2, 4],
                [0, 0, 1, 1, 2]];


console.log(gauss(matrix))