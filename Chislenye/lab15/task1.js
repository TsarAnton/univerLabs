//графический метод: x [0.5, 0.7]
function f(x) {
    return 3 * x - Math.cos(x) - 1;
}

function fP(x) {
    return 3 + Math.sin(x);
}

let a = -1, b = 1, n = 10;
let h = (b - a) / n;

let arr1 = [];
let arr2 = [];

for(let i = 0; i <= n; i++) {
    arr1.push(a + h * i);
    arr2.push(f(arr1[i]));
}

console.log("Табличный метод:");
let tableArr = [];
for(let i = 0; i < arr1.length - 1; i++) {
    if((arr2[i] < 0 && arr2[i + 1] > 0) || (arr2[i] > 0 && arr2[i + 1] < 0)) {
        tableArr.push(arr1[i], arr1[i + 1]);
        console.log("f(" + arr1[i] + ") = " + arr2[i] +", f(" + arr1[i + 1] + ") = " + arr2[i + 1]);
    }
}
console.log(tableArr);

let e = 0.001;
//метод половинного деления
a = tableArr[0];
b = tableArr[1];
for(let i = 1; true; i++) {
    let c = (a + b) / 2;
    if(f(c) === 0) {
        console.log("\nМетод половинного деления:\n\tx = " + c + "\n\ti = " + i);
        break;
    }
    if((b - a) / 2 <= e) {
        console.log("\nМетод половинного деления:\n\tx = " + c + "\n\ti = " + i);
        break;
    }
    if((f(a) < 0 && f(c) > 0) || (f(a) > 0 && f(c) < 0)) {
        b = c;
    } else {
        a = c;
    }
}

//метод Ньютона
let x = [tableArr[1]];
for(let i = 1; true; i++) {
    x.push(x[i - 1] - f(x[i - 1]) / fP(x[i - 1]));
    if(Math.abs(x[i - 1] - x[i]) <= e) {
        console.log("Метод Ньютона:\n\tx = " + x[i] + "\n\ti = " + i);
        break;
    }
}

//метод секущих
x = [tableArr[1], tableArr[1] - 0.01];
for(let i = 1; true; i++) {
    x.push(x[i] - (f(x[i]) * (x[i] - x[i - 1])) / (f(x[i]) - f(x[i - 1])));
    if(Math.abs(x[i + 1] - x[i]) <= e) {
        console.log("Метод секущих:\n\tx = " + x[i] + "\n\ti = " + i);
        break;
    }
}

//мето хорд
x = [tableArr[1], tableArr[1] - 0.01];
for(let i = 1; true; i++) {
    x.push(x[i] - (f(x[i]) * (x[i] - x[0])) / (f(x[i]) - f(x[0])));
    if(Math.abs(x[i + 1] - x[i]) <= e) {
        console.log("Метод хорд:\n\tx = " + x[i] + "\n\ti = " + i);
        break;
    }
}