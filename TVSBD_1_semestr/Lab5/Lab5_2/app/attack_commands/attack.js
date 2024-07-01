const RandomInteger = require('../randomInteger');

module.exports = {
    help: 'атака (урон зависит от атаки героя и защиты противника)',
    exec: function(hero, enemy) {
        let delta = hero.attack - enemy.defence;
        if(delta > 0) {
            return delta + 1;
        } else {
            let p = parseInt(Math.pow(Math.E, delta/10) * 100);

            if(p >= RandomInteger(0, 100)) {
                return 1;
            }
        }
        return 0;
    }
};