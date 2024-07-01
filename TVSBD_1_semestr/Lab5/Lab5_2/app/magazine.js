const Sword = require("./equipment/sword");
const Armor = require("./equipment/armor");
const Helm = require("./equipment/helm");
const Shield = require("./equipment/shield");

module.exports = function() {
    this.sword = new Sword();
    this.armor = new Armor();
    this.helm = new Helm();
    this.shield = new Shield();
}

module.exports.prototype.printMagazine = function() {
    console.log("Magazine");
    console.log("Armor: " + this.armor.toString() + " Cost: " + (this.find_cost(this.armor) * 2));
    console.log("Sword: " + this.sword.toString() + " Cost: " + (this.find_cost(this.sword) * 2));
    console.log("Shield: " + this.shield.toString() + " Cost: " + (this.find_cost(this.shield) * 2));
    console.log("Helm: " + this.helm.toString() + " Cost: " + (this.find_cost(this.helm) * 2));
}

module.exports.prototype.find_cost = function(item) {
    if(item.bonus == 1) {
        return 1;
    } else if(item.bonus == 2 || item.bonus == 3) {
        return 2;
    } else {
        return 3;
    }
}

module.exports.prototype.update = function() {
    this.sword = new Sword();
    this.armor = new Armor();
    this.helm = new Helm();
    this.shield = new Shield();
}