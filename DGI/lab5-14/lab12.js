function transposition(message, n, m) {
    let matrix = [], k = 0;
    for(let i = 0; i < n; i++) {
        matrix.push([]);
        let print = "";
        for(let j = 0; j < m; j++) {
            matrix[i].push("");
            if(k < message.length) {
                matrix[i][j] = message[k]
                k++;
            }
            print += matrix[i][j] + " ";
        }
        console.log(print);
    }
    let result = "";
    for(let i = 0; i < m; i++) {
        for(let j = 0; j < n; j++) {
            result += matrix[j][i];
        }
    }
    return result; 
}

function untransposition(message, n, m) {
    let nul = n * m - message.length;
    let matrix = [];
    for(let i = 0; i < n; i++) {
        matrix.push([]);
        if(i !== n - 1) {
            for(let j = 0; j < m; j++) {
                matrix[i].push(null);
            }
        } else {
            for(let j = 0; j < m - nul; j++) {
                matrix[i].push(null);
            }
        }
    }
    let k = 0;
    for(let i = 0; i < m; i++) {
        if(i < m - nul) {
            for(let j = 0; j < n; j++) {
                matrix[j][i] = message[k];
                k++;
            }
        } else {
            for(let j = 0; j < n - 1; j++) {
                matrix[j][i] = message[k];
                k++;
            }
        }
    }
    for(let i = 0; i < n; i++) {
        let print = "";
        for(let j = 0; j < m; j++) {
            print += matrix[i][j] + " ";
        }
        console.log(print);
    }
    let res = "";
    for(let i = 0; i < n; i++) {
        res += matrix[i].join("");
    }
    return res;
}

let message = "Hello, World!";
let n = 3;
let m = 6;
let secret = transposition(message, n, m);
console.log("Зашифрованное сообщение: " + secret);

console.log("Расшифрованное сообщение: " + untransposition(secret, n, m))