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

export default function gaussJardan(matrix) {
    let n = matrix.length;
    for(let i = 0; i < n; i++) {
        printMatrix(matrix);

        let buff = matrix[i][i];
        for(let j = 0; j <= n; j++) {
            matrix[i][j] /= buff;
        }
        printMatrix(matrix);
        
        for(let j = 0; j < n; j++) {
            if(i !== j) {
                buff = -matrix[j][i];
                for(let k = 0; k <= n; k++) {
                    matrix[j][k] = matrix[i][k] * buff + matrix[j][k];
                }
            }
        }
    }
    printMatrix(matrix);
    let x = [];
    for(let i = 0; i < n; i++) {
        x.push(matrix[i][n]);
    }
    return x;
}

let matrix = [[0.32, -0.42, 0.85, 1.32],
                [0.63, -1.43, -0.58, -0.44],
                [0.84, -2.23, -0.52, 0.64]];

console.log(gaussJardan(matrix));