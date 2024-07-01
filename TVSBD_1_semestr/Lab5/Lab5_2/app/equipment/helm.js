const RandomInteger = require('../randomInteger');

module.exports = function(){
    this.type = "helm";
    let type = RandomInteger(0, 100);
    if(type <= 80) {
        this.bonus = 1;
    } else if(type <= 95){
        this.bonus = RandomInteger(2, 3);
    } else {
        this.bonus = RandomInteger(4, 5);
    }
};

module.exports.prototype.toString = function() {
    return "Helm: luck + " + this.bonus;
};