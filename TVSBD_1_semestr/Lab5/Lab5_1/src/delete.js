module.exports = function(array) {
    let re = /^ISBN-1(0: |3: \d{3}-)\d(-\d{4}){2}-\d$/;
    
    return array.filter(word => re.test(word));
}