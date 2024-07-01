function isSimple(n) {
    for(let i = 2; i <= Math.sqrt(n); i++) {
        if(n % i === 0) {
            return false;
        }
    }
    return true;
}

let p = 23, q = 3;
let n = p * q; //модуль
let phi = (p - 1) * (q - 1);
let e;
for(let i = 3; i < phi; i++) {
    if(isSimple(i) && phi % i !== 0) {
        e = i;
        break;
    }
}
console.log("открытый ключ: {" + e + ", " + n + "}");

let d; //d - обратное e по модулю phi
for(let i = e + 1; true; i++) {
    if((i * e) % phi === 1) {
        d = i;
        break;
    }
}
console.log("закрытый ключ: {" + d + ", " + n + "}\n");

let P = 19;
let E = Math.pow(P, e) % n;
console.log("(" + P + " ^ " + e + ") % " + n + " = " + E);
console.log(P + " : " + E);

let D = Math.pow(E, d) % n;
console.log("(" + E + " ^ " + d + ") % " + n + " = " + D);
console.log(E + " : " + D);
console.log("\n");

let message = [17, 19, 17, 18];
let secret = message.map(x => Math.pow(x, e) % n);
console.log(message + " : " + secret);
let unsecret = secret.map(x => Math.pow(x, d) % n);
console.log(secret + " : " + unsecret);


