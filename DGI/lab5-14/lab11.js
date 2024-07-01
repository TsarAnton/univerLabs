let alphabet = "abcdefghijklmnopqrstuvwxyz".split("");
function cizar(message, key) {
    let result = "";
    for(let letter of message) {
        let newIndex = alphabet.indexOf(letter.toLowerCase()) + key;
        if(newIndex >= 26) {
            newIndex -= 26;
        }
        if(/[a-zA-Z]/.test(letter)) {
            if(letter === letter.toUpperCase()) {
                result += alphabet[newIndex].toUpperCase();
            } else {
                result += alphabet[newIndex];
            }
        } else {
            result += letter;
        }
    }
    return result;
}

let key = 3;
let message = "Hello, World!";
let secret = cizar(message, key)
console.log("Зашифрованное сообщение: " + secret);

console.log("Расшифрованное сообщение: " + cizar(secret, 26 - key));

