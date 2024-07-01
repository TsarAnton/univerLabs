module.exports = function() {
    this.health = 10;
    this.attack = 1;
    this.defence = 1;
    this.luck = 0;
    this.inventory = [];
    this.equipment = {
        "armor": null,
        "sword": null,
        "shield": null,
        "helm": null
    }
}

module.exports.prototype.arm = function(id) {
    if(!(id >= this.inventory.length || id < 0)){
        let item = this.inventory[id];
        if(item == undefined){
            return 3;
        }
        if(item.type === "armor") {
            if(this.equipment.armor == null) {
                this.equipment.armor = item;
                this.health += this.equipment.armor.bonus;
                this.inventory.splice(id, 1);
                return 1;
            } else {
                return 2;
            }
        } else if(item.type === "sword") {
            if(this.equipment.sword == null) {
                this.equipment.sword = item;
                this.attack += this.equipment.sword.bonus;
                this.inventory.splice(id, 1);
                return 1;
            } else {
                return 2;
            }
        } else if(item.type === "shield") {
            if(this.equipment.shield == null) {
                this.equipment.shield = item;
                this.defence += this.equipment.shield.bonus;
                this.inventory.splice(id, 1);
                return 1;
            } else {
                return 2;
            }
        } else {
            if(this.equipment.helm == null) {
                this.equipment.helm = item;
                this.luck += this.equipment.helm.bonus;
                this.inventory.splice(id, 1);
                return 1;
            } else {
                return 2;
            }
        }
    }
    return 3;
}

module.exports.prototype.disarm = function(name) {
    if(name == "he") {
        if(this.equipment.helm == null) {
            return 2;
        } else {
            this.inventory.push(this.equipment.helm);
            this.luck -= this.equipment.helm.bonus;
            this.equipment.helm = null;
            return 1;
        }
    } else if (name == "sw") {
        if(this.equipment.sword == null) {
            return 2;
        } else {
            this.inventory.push(this.equipment.sword);
            this.attack -= this.equipment.sword.bonus;
            this.equipment.sword = null;
            return 1;
        }
    } else if (name == "sh") {
        if(this.equipment.shield == null) {
            return 2;
        } else {
            this.inventory.push(this.equipment.shield);
            this.defence -= this.equipment.shield.bonus;
            this.equipment.shield = null;
            return 1;
        }
    } else if (name == "ar") {
        if(this.equipment.armor == null) {
            return 2;
        } else {
            this.inventory.push(this.equipment.armor);
            this.health -= this.equipment.armor.bonus;
            this.equipment.armor = null;
            return 1;
        }
    }
    return 3;
}

module.exports.prototype.sell = function(id) {
    if(!(id >= this.inventory.length || id < 0)){
        let item = this.inventory[id];
        if(item == undefined) {
            return null;
        }
        this.inventory.splice(id, 1);
        return item;
    }
    return null;
}

module.exports.prototype.buy = function(item) {
    this.inventory.push(item);
}

module.exports.prototype.printInventory = function() {
    console.log("Hero inventory:");
    this.inventory.forEach(function(item, index, array) {
        console.log(item.toString() + " (id:" + index + ")");
    })
}

module.exports.prototype.printEquipment = function() {
    console.log("Hero equipment:");
    let result = "Armor: ";
    if(this.equipment.armor != null) {
        result += this.equipment.armor.toString() + '\n';
    } else {
        result += "none\n";
    }
    result += "Sword: ";
    if(this.equipment.sword != null) {
        result += this.equipment.sword.toString() + '\n';
    } else {
        result += "none\n";
    }
    result += "Shield: ";
    if(this.equipment.shield != null) {
        result += this.equipment.shield.toString() + '\n';
    } else {
        result += "none\n";
    }
    result += "Helm: ";
    if(this.equipment.helm != null) {
        result += this.equipment.helm.toString();
    } else {
        result += "none";
    }
    console.log(result);
}