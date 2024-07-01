const generate = require('./labyrinth-generator');
const MakeHero = require('./persons/hero');
const RandomInteger = require('./randomInteger');
const Enemy = require('./persons/enemy');
const Sword = require("./equipment/sword");
const Armor = require("./equipment/armor");
const Helm = require("./equipment/helm");
const Shield = require("./equipment/shield");
const reader = require('readline-sync');
const Magazine = require("./magazine");

module.exports = function(vIndex, hIndex, v, h) {
  this.tick = 0;
  this.mode = 0;
  this.direction = 1;

  this.money = 1000;
  this.magazine = new Magazine();

  this.vIndex = vIndex;
  this.hIndex = hIndex;

  this.hero = new MakeHero();

  this.labyrinth = generate(vIndex, hIndex, v, h);//создаем лабиринт

  enemyCount = parseInt(this.labyrinth.vSize * this.labyrinth.hSize * 0.15);
	enemies = [];

	for(i = 0; i < enemyCount; i++) {
		while(true) {
			let x = RandomInteger(0, this.labyrinth.hSize - 1);
			let y = RandomInteger(0, this.labyrinth.vSize - 1);
			
			if(!this.labyrinth.isEnemy(y, x) && !(x == this.vIndex && y == this.hIndex) && !this.labyrinth.isPotion(y, x)) {
				let new_enemy = new Enemy();
				enemies.push(new_enemy);
				this.labyrinth.setEnemy(y, x, i);
				break;
			}
		}
	}

  for(i = 0; i < 4; i++) {
		while(true) {
			let x = RandomInteger(0, this.labyrinth.hSize - 1);
			let y = RandomInteger(0, this.labyrinth.vSize - 1);
			
			if(!this.labyrinth.isEnemy(y, x) && !(x == this.vIndex && y == this.hIndex) && !this.labyrinth.isPotion(y, x)) {
        if(i == 0){
				  this.labyrinth.setHealth(y, x, true);
        } else if(i == 1) {
          this.labyrinth.setDefence(y, x, true);
        } else if(i == 2) {
          this.labyrinth.setAttack(y, x, true);
        } else {
          this.labyrinth.setLuck(y, x, true);
        }
				break;
			}
		}
	}

  this.labyrinth.setCellToStringHook((vIndex, hIndex) => {//отображение героя
    if(this.vIndex === vIndex && this.hIndex === hIndex) {
      if(this.direction == 0) {
        return ' ↑ ';
      }
      if(this.direction == 1) {
        return ' → ';
      }
      if(this.direction == 2) {
        return ' ↓ ';
      }
      if(this.direction == 3) {
        return ' ← ';
      }
    }
    return null;
  });

  this.labyrinth.setVisible(vIndex, hIndex, true);//отобразить начальную клетку

  this.potions = {
    "health": 0,
    "defence": 0,
    "attack": 0,
    "luck": 0
  }

  this.potions_need = {
    "health": 1,
    "defence": 1,
    "attack": 1,
    "luck": 1
  }

  this.move_commands = {//список команд (объекты)
    "help": require('./move_commands/help'),
    "a": require('./move_commands/turn-left'),
    "d": require('./move_commands/turn-right'),
    "w": require('./move_commands/move'),
    "e": require('./move_commands/explore')
  };

  this.attack_commands = {//список команд (объекты)
    "help": require('./attack_commands/help'),
    "a": require('./attack_commands/attack'),
    "b": require('./attack_commands/block'),
    "d": require('./attack_commands/dodge'),
    "f": require('./attack_commands/fury')
  };

  this.attack_buffer = {
    "h_h": null,
    "h_d": null,
    "e_t": null,
    "enemy": null,
    "message": null,
    "winmessage": null,
    "dodge": false
  }
};

module.exports.prototype.delete_potion = function(mode) {
  if(mode == 0) {
      while(true) {
        let x = RandomInteger(0, this.labyrinth.hSize - 1);
        let y = RandomInteger(0, this.labyrinth.vSize - 1);
          
        if(!this.labyrinth.isEnemy(y, x) && !(x == this.hIndex && y == this.vIndex) && !this.labyrinth.isPotion(y, x)) {
          this.labyrinth.setHealth(y, x, true);
          this.labyrinth.setHealth(this.vIndex, this.hIndex, false);
          break;
        }
      }
  } else if(mode == 1) {
    while(true) {
      let x = RandomInteger(0, this.labyrinth.hSize - 1);
      let y = RandomInteger(0, this.labyrinth.vSize - 1);
        
      if(!this.labyrinth.isEnemy(y, x) && !(x == this.hIndex && y == this.vIndex) && !this.labyrinth.isPotion(y, x)) {
        this.labyrinth.setDefence(y, x, true);
        this.labyrinth.setDefence(this.vIndex, this.hIndex, false);
        break;
      }
    } 
  } else if(mode == 2) {
      while(true) {
        let x = RandomInteger(0, this.labyrinth.hSize - 1);
        let y = RandomInteger(0, this.labyrinth.vSize - 1);
          
        if(!this.labyrinth.isEnemy(y, x) && !(x == this.hIndex && y == this.vIndex) && !this.labyrinth.isPotion(y, x)) {
          this.labyrinth.setAttack(y, x, true);
          this.labyrinth.setAttack(this.vIndex, this.hIndex, false);
          break;
        }
      }
  } else if(mode == 3) {
    while(true) {
      let x = RandomInteger(0, this.labyrinth.hSize - 1);
      let y = RandomInteger(0, this.labyrinth.vSize - 1);
        
      if(!this.labyrinth.isEnemy(y, x) && !(x == this.hIndex && y == this.vIndex) && !this.labyrinth.isPotion(y, x)) {
        this.labyrinth.setLuck(y, x, true);
        this.labyrinth.setLuck(this.vIndex, this.hIndex, false);
        break;
      }
    }
  }
}

module.exports.prototype.execCommand = function(command) {
  if(this.mode == 0){//перемещение

    let commandObj = this.move_commands[command];
    if(commandObj && command !== "help") {
      commandObj.exec(this);
      if(this.labyrinth.field[this.vIndex][this.hIndex].enemy_index != null) {
        this.mode = 1;
        this.attack_buffer.h_h = this.hero.health;
        this.attack_buffer.h_d = this.hero.defence;
        this.attack_buffer.e_t = enemies[this.labyrinth.field[this.vIndex][this.hIndex].enemy_index].time;
        this.attack_buffer.enemy = enemies[this.labyrinth.field[this.vIndex][this.hIndex].enemy_index];
        this.attack_buffer.message = "Fight!";
      }
      if(this.labyrinth.isPotion(this.vIndex, this.hIndex)) {
        if(this.labyrinth.field[this.vIndex][this.hIndex].health_index) {
          this.potions.health += 1;
          this.attack_buffer.winmessage = "You received potion of health";
          this.delete_potion(0);
        } else if(this.labyrinth.field[this.vIndex][this.hIndex].defence_index) {
          this.potions.defence += 1;
          this.attack_buffer.winmessage = "You received potion of defence";
          this.delete_potion(1);
        } else if(this.labyrinth.field[this.vIndex][this.hIndex].attack_index) {
          this.potions.attack += 1;
          this.attack_buffer.winmessage = "You received potion of attack";
          this.delete_potion(2);
        } else {
          this.potions.luck += 1;
          this.attack_buffer.winmessage = "You received potion of luck";
          this.delete_potion(3);
        }
      }
      return null;
    } else if(command === "help") {
      return commandObj.exec(this);
    } else if(command === "i") {
      this.mode = 2;
      this.attack_buffer.winmessage = "Menu";
    } else {
      return 'неверная команда\n\n' + this.move_commands.help.exec(this);
    }
  } else if(this.mode == 1){//сражение

    if(command === "a") {
      let damage = this.attack_commands.a.exec(this.hero, this.attack_buffer.enemy);
      this.attack_buffer.enemy.health -= damage;
      this.attack_buffer.message = "Hero deals damage: " + damage;
    } else if(command === "b"){
      this.hero.defence += this.attack_commands.b.exec(this.hero);
      this.attack_buffer.message = "Hero defense + 1"
    } else if(command === "d"){
      if(this.attack_commands.d.exec(this.hero)){
        this.attack_buffer.dodge = true;
        this.attack_buffer.message = "Dodge: success";
      } else {
        this.attack_buffer.message = "Dodge: fail";
      }
    } else if(command === "f") {
      let damage = this.attack_commands.f.exec(this.hero, this.attack_buffer.enemy);
      this.attack_buffer.enemy.health -= damage;
      this.attack_buffer.message = "Hero deals damage: " + damage;
    } else if(command === "help") {
      return this.attack_commands.help.exec(this);
    } else {
      return 'неверная команда\n\n' + this.attack_commands.help.exec(this);
    }

    if(this.attack_buffer.enemy.health <= 0 ) {
      let new_enemy = new Enemy();
      enemies[this.labyrinth.field[this.vIndex][this.hIndex].enemy_index] = new_enemy;

      while(true) {
        let x = RandomInteger(0, this.labyrinth.hSize - 1);
        let y = RandomInteger(0, this.labyrinth.vSize - 1);
          
        if(!this.labyrinth.isEnemy(y, x) && !(x == this.hIndex && y == this.vIndex) && !this.labyrinth.isPotion(y, x)) {
          this.labyrinth.setEnemy(y, x, this.labyrinth.field[this.vIndex][this.hIndex].enemy_index);
          this.labyrinth.setEnemy(this.vIndex, this.hIndex, null);
          break;
        }
      }

      this.mode = 0;
      this.hero.defence = this.attack_buffer.h_d;
      this.hero.health = this.attack_buffer.h_h;
      this.magazine.update();

      this.attack_buffer.winmessage = "Win!\n";
      let p = RandomInteger(0, 100);
      if(p <= 30) {
        this.attack_buffer.winmessage += "You didn't receive any item";
      } else if (p <= 48) {
        let item = new Sword();
        this.hero.inventory.push(item);
        this.attack_buffer.winmessage += "You receive item " + item.toString();
      } else if (p <= 66) {
        let item = new Helm();
        this.hero.inventory.push(item);
        this.attack_buffer.winmessage += "You receive item " + item.toString();
      } else if(p <= 84) {
        let item = new Shield();
        this.hero.inventory.push(item);
        this.attack_buffer.winmessage += "You receive item " + item.toString();
      } else if(p <= 100){
        let item = new Armor();
        this.hero.inventory.push(item);
        this.attack_buffer.winmessage += "You receive item " + item.toString();
      }

    } else if(this.attack_buffer.enemy.time == 0) {
      if(!this.attack_buffer.dodge) {
        let delta = this.attack_buffer.enemy.attack - this.hero.defence;
        let damage = 0;
        if(delta > 0) {
          damage = delta + 1;
        } else {
            let p = parseInt(Math.pow(Math.E, delta/10) * 100);

            if(p >= RandomInteger(0, 100)) {
                damage = 1;
            }
        }

        this.hero.health -= damage;
        this.attack_buffer.message += '     Enemy deals damage: ' + damage;

        this.attack_buffer.enemy.time = this.attack_buffer.e_t;
      } else {
        this.attack_buffer.dodge = false;
      }
    } else {
      this.attack_buffer.enemy.time -= 1;
    }

  }else if(this.mode == 2) {
    if(command === "c") {
      this.mode = 0;
      return 0;
    } else if(command === "a") {
      let id = parseInt(reader.question('item id> '));
      let result = this.hero.arm(id);
      if(result == 1) {
          this.attack_buffer.winmessage = "Equipted";
      } else if(result == 2) {
        this.attack_buffer.winmessage = "Item of such type is already equipted";
      } else {
        this.attack_buffer.winmessage = `Item with id = ${id} does not exist`;
      }
    } else if(command === "d") {
      console.log("sh  - shield");
      console.log("sw  - sword");
      console.log("ar  - armor");
      console.log("he  - helm");
      console.log("ex - exit");
      let name = reader.question('item name> ');
      if(name === "ex") {
        this.attack_buffer.winmessage = "Menu";
        return null;
      }
      let result = this.hero.disarm(name);
      if(result == 1) {
        this.attack_buffer.winmessage = "Unequipted";
      }
      if(result == 2) {
        this.attack_buffer.winmessage = "Item of such type is already unequipted";
      } else {
        this.attack_buffer.winmessage = `Item ${name} does not exist`;
      }
    } else if(command === "s") {
      let id = parseInt(reader.question('item id> '));
      let item = this.hero.sell(id);
      if(item == null) {
        this.attack_buffer.winmessage = `Item with id = ${id} does not exist`;
      } else {
        let cost = this.magazine.find_cost(item);
        this.money += cost;
        this.attack_buffer.winmessage = `Item with id = ${id} sold: money + ${cost}`;
      }
    } else if(command === "b") {
      console.log(`\nMoney: ${this.money}`);
      this.magazine.printMagazine();
      console.log("sh  - shield");
      console.log("sw  - sword");
      console.log("ar  - armor");
      console.log("he  - helm");
      console.log("ex - exit");
      let name = reader.question('item name> ');
      if(name === "ex") {
        this.attack_buffer.winmessage = "Menu";
        return null;
      }
      let item = null;
      if(name === "sh") {
        item = this.magazine.shield;
        this.magazine.shield = new Shield();
      } else if(name === "sw") {
        item = this.magazine.sword;
        this.magazine.sword = new Sword();
      } else if(name === "he") {
        item = this.magazine.helm;
        this.magazine.helm = new Helm();
      } else if(name === "ar") {
        item = this.magazine.armor;
        this.magazine.armor = new Armor();
      }

      if(item == null) {
        this.attack_buffer.winmessage = `Item ${name} does not exist`;
      } else {
        let cost = this.magazine.find_cost(item);
        if(cost <= this.money) {
          this.hero.inventory.push(item);
          this.money -= cost;
          this.attack_buffer.winmessage = `Item ${item.toString()} bought: money - ${cost}`;
        } else {
          this.attack_buffer.winmessage = `Not enough money`;
        }
      }
    } else if(command === "help") {
      let result = "Справка по командам\n";
      result += 'список доступных команд:\n\n';
      result += '    c\n        закрыть инвентарь\n\n';
      result += '    а\n        экипировать предмет\n\n';
      result += '    d\n        убрать предмет\n\n';
      result += '    s\n        продать предмет\n\n';
      result += '    b\n        купить предмет\n\n';
      result += '    u\n        использовать зелье\n\n';
      result += '    exit\n        выход из программы\n\n';
      return result;
    } else if(command === "u") {
      console.log("Hero potions:");
      console.log(`Potion of health: ${this.potions.health} Need: ${this.potions_need.health}`);
      console.log(`Potion of attack: ${this.potions.attack} Need: ${this.potions_need.attack}`);
      console.log(`Potion of defense: ${this.potions.defence} Need: ${this.potions_need.defence}`);
      console.log(`Potion of luck: ${this.potions.luck} Need: ${this.potions_need.luck}\n`);
      console.log("h  - health");
      console.log("a  - attack");
      console.log("d  - defense");
      console.log("l  - luck");
      console.log("ex - exit");
      let name = reader.question('item name> ');
      if(name === "ex") {
        this.attack_buffer.winmessage = "Menu";
        return null;
      }
      if(name === "h") {
        if(this.potions.health >= this.potions_need.health) {
          this.hero.health += 1;
          this.potions.health -= this.potions_need.health;
          this.potions_need.health += 1;
          this.attack_buffer.winmessage = "Hero health + 1";
        } else {
          this.attack_buffer.winmessage = "Not enough potions";
        }
      } else if (name === "a") {
        if(this.potions.attack >= this.potions_need.attack) {
          this.hero.attack += 1;
          this.potions.attack -= this.potions_need.attack;
          this.potions_need.attack += 1;
          this.attack_buffer.winmessage = "Hero attack + 1";
        } else {
          this.attack_buffer.winmessage = "Not enough potions";
        }
      } else if(name === "d") {
        if(this.potions.defence >= this.potions_need.defence) {
          this.hero.defence += 1;
          this.potions.defence -= this.potions_need.defence;
          this.potions_need.defence += 1;
          this.attack_buffer.winmessage = "Hero defence + 1";
        } else {
          this.attack_buffer.winmessage = "Not enough potions";
        }
      } else if(name === "l") {
        if(this.potions.luck >= this.potions_need.luck) {
          this.hero.luck += 1;
          this.potions.luck -= this.potions_need.luck;
          this.potions_need.luck += 1;
          this.attack_buffer.winmessage = "Hero luck + 1";
        } else {
          this.attack_buffer.winmessage = "Not enough potions";
        }
      } else {
        this.attack_buffer.winmessage = `Potion ${name} does not exist`;
      }
    } else {
      this.attack_buffer.winmessage = "Меню";
      return 'неверная команда\n\n';
    }

  }
};

module.exports.prototype.toString = function() {//отобразить лабиринт
	return this.labyrinth.toString();
};

module.exports.prototype.printBattle = function() {
  let enemy = enemies[this.labyrinth.field[this.vIndex][this.hIndex].enemy_index];
  console.log(this.attack_buffer.message);
  console.log("\nEnemy status");
  console.log("Health: " + enemy.health);
  console.log("Attack: " + enemy.attack);
  console.log("Defence: " + enemy.defence);
  console.log("Time for attack: " + enemy.time + '\n');
  console.log("Hero status");
  console.log("Health: " + this.hero.health);
  console.log("Attack: " + this.hero.attack);
  console.log("Defence: " + this.hero.defence);
  console.log("Luck: " + this.hero.luck);
}

module.exports.prototype.printWin = function() {
  console.log(this.attack_buffer.winmessage);
  this.attack_buffer.winmessage = null;
}

module.exports.prototype.printInv = function() {
  console.log(this.attack_buffer.winmessage);
  this.hero.printInventory();
  console.log('\n');
  this.hero.printEquipment();
  this.attack_buffer.winmessage = null;
}
