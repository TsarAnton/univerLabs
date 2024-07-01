const RandomInteger = require('../randomInteger');

module.exports = function(){
    this.type = "armor";
    let type = RandomInteger(0, 100);
    if(type <= 80) {
        this.bonus = RandomInteger(1, 2);
    } else if(type <= 95){
        this.bonus = RandomInteger(2, 3);
    } else {
        this.bonus = RandomInteger(3, 5);
    }
};

module.exports.prototype.toString = function() {
    return "Armor: health + " + this.bonus;
};