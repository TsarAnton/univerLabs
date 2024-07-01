module.exports = function(array) {
    array.sort(function(a, b){

        let str1;
        let str2;

        if(a.includes("ISBN-10")) {
            str1 = a[9];
        }
        else {
            str1 = a[13];
        }

        if(b.includes("ISBN-10")) {
            str2 = b[9];
        }
        else {
            str2 = b[13];
        }

        if(parseInt(str1) < parseInt(str2)) {
            return -1;
        }

        if(parseInt(str1) > parseInt(str2)) {
            return 1;
        }

        return 0;
    });

    return array;

}