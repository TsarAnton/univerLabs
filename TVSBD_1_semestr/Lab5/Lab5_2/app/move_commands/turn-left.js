module.exports = {
    help: 'повернуть налево',
    exec: function(game) {
      if(game.direction == 0) {
          game.direction = 3;
          return null;
      }
      if(game.direction == 1) {
          game.direction = 0;
          return null;
      }
      if(game.direction == 2) {
          game.direction = 1;
          return null;
      }
      if(game.direction == 3) {
          game.direction = 2;
          return null;
      }
    }
};