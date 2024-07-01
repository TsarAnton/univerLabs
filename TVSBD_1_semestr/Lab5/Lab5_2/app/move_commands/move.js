module.exports = {
    help: 'ход',
    exec: function(game) {
      if(game.labyrinth.isMovePossible(game.vIndex, game.hIndex, 2) && game.direction == 2) {//если можно пойти вниз
        game.vIndex++;//положение песронажа +1 вниз
        game.labyrinth.setVisible(game.vIndex, game.hIndex, true);//сделать видимым
        return null;
      } else if(game.labyrinth.isMovePossible(game.vIndex, game.hIndex, 3) && game.direction == 3) {
        game.hIndex--;
        game.labyrinth.setVisible(game.vIndex, game.hIndex, true);
        return null;
      } else if(game.labyrinth.isMovePossible(game.vIndex, game.hIndex, 1) && game.direction == 1) {
        game.hIndex++;
        game.labyrinth.setVisible(game.vIndex, game.hIndex, true);
        return null;
      } else if(game.labyrinth.isMovePossible(game.vIndex, game.hIndex, 0) && game.direction == 0) {
        game.vIndex--;
        game.labyrinth.setVisible(game.vIndex, game.hIndex, true);
        return null;
      } else {
        return 'ход невозможен';
      }
    }
  };