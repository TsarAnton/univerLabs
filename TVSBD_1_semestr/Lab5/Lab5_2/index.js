const reader = require('readline-sync');
const Game = require('./app/game');

let v = parseInt(reader.question('height> '));
let h = parseInt(reader.question('width> '));

let game = new Game(0, 0, v, h);

while(true) {
  console.clear();
  if(game.hero.health <= 0) {
    console.log("Game over");
    break;
  }
  console.log(game.toString());
  if(game.mode == 1) {
    game.printBattle();
  }
  if(game.mode == 2) {
    game.printInv();
  }
  if(game.attack_buffer.winmessage != null) {
    game.printWin();
  }
  let command = reader.question('command> ');
  if(command === 'exit') {
    break;
  }
  let result = game.execCommand(command);//выполняем введенную команду
  if(result) {
    console.log(result);
    reader.question('press Enter');
  }
}
