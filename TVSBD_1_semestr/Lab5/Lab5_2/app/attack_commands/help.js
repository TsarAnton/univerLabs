module.exports = {
    help: 'справка по командам',
    exec: function(game) {
      let result = 'список доступных команд:\n\n';
      for(let commandName in game.attack_commands) {//для всех команд в списке команд отобразить название и описание
          if(game.attack_commands.hasOwnProperty(commandName)) {
            result += '    ' + commandName + '\n';
            result += '        ' + game.attack_commands[commandName].help + '\n\n';
          }
      }
      result += '    exit\n        выход из программы\n\n';
      return result;
    }
  };