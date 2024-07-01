const RandomInteger = require('../randomInteger');

module.exports = {
    help: 'с некоторым шансом нанесети вдвое больший или меньший урон (зависит от удачи)',
    exec: function(hero, enemy) {
        let delta = hero.attack - enemy.defence;
        let damage = 0;
        if(delta > 0) {
            damage = delta + 1;
        } else {
            let p = parseInt(Math.pow(Math.E, delta/10) * 100);
            if(p >= RandomInteger(0, 100)) {
                damage = 1;
            }
        }
        let p = 1 - Math.pow(Math.E, 0 - hero.luck / 10);
        p = parseInt(p * 100);
        if(p >= RandomInteger(0, 100)) {
            damage *= 2;
        } else {
            if(!(damage == 1 || damage == 0)) {
                damage = parseInt(damage / 2);
            }
        }

        return damage;
    }
};