const RandomInteger = require('../randomInteger');

module.exports = {
    help: 'с некоторым шансом (зависит от удачи) уклониться от атаки',
    exec: function(hero) {
        let p = 1 - Math.pow(Math.E, 0 - hero.luck / 10);
        p = parseInt(p * 100);

        if(p >= RandomInteger(0, 100)){
            return true;
        }

        return false;
    }
};