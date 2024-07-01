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

function gauss(matrix) {
    let n = matrix.length;
    for(let i = 0; i < n; i++) {
        printMatrix(matrix);
        let max = 0;
        let maxLine = 0;
        for(let j = i; j < n; j++) {
            if(Math.abs(matrix[j][i]) > max) {
                max = Math.abs(matrix[j][i]);
                maxLine = j;
            }
        }

        let buff = matrix[i];
        matrix[i] = matrix[maxLine];
        matrix[maxLine] = buff;
        printMatrix(matrix);

        let a = matrix[i][i];
        for(let j = i; j <= n; j++) {
            matrix[i][j] /= a;
        }
        printMatrix(matrix);

        for(let j = i + 1; j < n; j++) {
            a = -matrix[j][i];
            for(let k = 0; k <= n; k++) {
                matrix[j][k] = matrix[i][k] * a + matrix[j][k];
            }
        }
    }
    printMatrix(matrix);

    let x = [];
    x[n - 1] = matrix[n - 1][n];
    for(let i = n - 2; i >= 0; i--) {
        let sum = 0;
        for(let j = i + 1; j < n; j++) {
            sum += matrix[i][j] * x[j];
        }
        x[i] = matrix[i][n] - sum;
    }

    return x;
}

let matrix = [[0.32, -0.42, 0.85, 1.32],
                [0.63, -1.43, -0.58, -0.44],
                [0.84, -2.23, -0.52, 0.64]];

console.log(gauss(matrix))