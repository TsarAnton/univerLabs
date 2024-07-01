// (-2.2, -2) (-0.3, -0.1) (2.3, 2.4)
// fi'(x) = 3/5 * x^2
// |fi'(x)| < 1
function fi(x) {
    return (Math.pow(x, 3) - 1) / 5;
}

let e = 0.001;
let x = [-0.1];
for(let i = 1; true; i++) {
    x.push(fi(x[i - 1]));
    if(Math.abs(x[i] - x[i - 1]) <= e) {
        console.log("Метод простых итераций:\n\tx = " + x[i] + "\n\ti = " + i);
        break;
    }
}