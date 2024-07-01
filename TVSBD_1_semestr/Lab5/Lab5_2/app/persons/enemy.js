const RandomInteger = require('../randomInteger');

module.exports = function() {
    let type = RandomInteger(0, 100);
    if(type <= 60) {
        this.health = RandomInteger(1, 4);
        this.attack = RandomInteger(1, 4);
        this.defence = RandomInteger(0, 1);
        this.time = 0;
    } else if(type <= 90){
        this.health = RandomInteger(3, 5);
        this.attack = RandomInteger(2, 5);
        this.defence = RandomInteger(1, 3);
        this.time = RandomInteger(0, 1);
    } else if(type <= 100){
        this.health = RandomInteger(5, 8);
        this.attack = RandomInteger(5, 7);
        this.defence = RandomInteger(3, 5);
        this.time = RandomInteger(1, 2);
    }
}