const reader = require('readline-sync');
const array_sort = require('./src/sort');
const array_delete = require('./src/delete');

let N = Number(reader.question('Number of strings: '));

let array = [];

while(N){
    let str = reader.question();
    array.push(str);
    N--;
}

console.log("Array:\n");

array.forEach(function(item) {
    console.log(item);
})

array = array_delete(array);

console.log("\nArray after delete:\n");

array.forEach(function(item) {
    console.log(item);
})

array = array_sort(array);

console.log("\nArray after sort:\n")

array.forEach(function(item) {
    console.log(item);
})