module.exports = {
  help: 'справка по командам',
  exec: function(game) {
    let result = 'список доступных команд:\n\n';
    for(let commandName in game.move_commands) {//для всех команд в списке команд отобразить название и описание
        if(game.move_commands.hasOwnProperty(commandName)) {
          result += '    ' + commandName + '\n';
          result += '        ' + game.move_commands[commandName].help + '\n\n';
        }
    }
    result += '    i\n        открыть инвентарь\n\n';
    result += '    exit\n        выход из программы\n\n';
    return result;
  }
};