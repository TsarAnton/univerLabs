module.exports = function(vSize, hSize) {
  this.vSize = vSize;
  this.hSize = hSize;
  this.field = new Array(vSize);
  for(let vIndex = 0; vIndex < vSize; vIndex++) {
    this.field[vIndex] = new Array(hSize);
    for(let hIndex = 0; hIndex < hSize; hIndex++) {
      this.field[vIndex][hIndex] = {
        visible: false,//видна ли клетка
        top: false,//можно ли пойти вверх
        right: false,//можно ли пойти вправо
        bottom: false,//можно ли пойти вниз
        left: false,//можно ли пойти влево
        enemy_index: null,
        health_index: false,
        defence_index: false,
        attack_index: false,
        luck_index: false
      };
    }
  }
};

module.exports.prototype.isVisible = function(vIndex, hIndex) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    return this.field[vIndex][hIndex].visible;
  }
  throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
};

module.exports.prototype.setVisible = function(vIndex, hIndex, visible) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    this.field[vIndex][hIndex].visible = visible;
  } else {
    throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
  }
};

module.exports.prototype.isEnemy = function(vIndex, hIndex) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    if(this.field[vIndex][hIndex].enemy_index == null) {
      return false;
    }
    return true;
  }
  throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
};

module.exports.prototype.setEnemy = function(vIndex, hIndex, enemy_index) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    this.field[vIndex][hIndex].enemy_index = enemy_index;
  } else {
    throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
  }
};

module.exports.prototype.isPotion = function(vIndex, hIndex) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    if(this.field[vIndex][hIndex].defence_index || this.field[vIndex][hIndex].health_index || this.field[vIndex][hIndex].attack_index || this.field[vIndex][hIndex].luck_index) {
      return true;
    }
    return false;
  }
  throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
};

module.exports.prototype.setHealth = function(vIndex, hIndex, health_index) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    this.field[vIndex][hIndex].health_index = health_index;
  } else {
    throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
  }
};

module.exports.prototype.setDefence = function(vIndex, hIndex, defence_index) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    this.field[vIndex][hIndex].defence_index = defence_index;
  } else {
    throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
  }
};

module.exports.prototype.setAttack = function(vIndex, hIndex, attack_index) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    this.field[vIndex][hIndex].attack_index = attack_index;
  } else {
    throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
  }
};

module.exports.prototype.setLuck = function(vIndex, hIndex, luck_index) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    this.field[vIndex][hIndex].luck_index = luck_index;
  } else {
    throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
  }
};

module.exports.prototype.isMovePossible = function(vIndex, hIndex, direction) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    switch(direction) {
    case 0: return vIndex > 0 && this.field[vIndex][hIndex].top;//пойти вверх
    case 1: return hIndex < this.hSize - 1 && this.field[vIndex][hIndex].right;//пойти вправо
    case 2: return vIndex < this.vSize - 1 && this.field[vIndex][hIndex].bottom;//пойти вниз
    case 3: return hIndex > 0 && this.field[vIndex][hIndex].left;//пойти влево
    default:
      throw `direction = ${direction} is incorrect`;
    }
  } else {
    throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
  }
};

module.exports.prototype.setMovePossible = function(vIndex, hIndex, direction, possible) {
  if(vIndex >= 0 && vIndex < this.vSize && hIndex >= 0 && hIndex < this.hSize) {
    switch(direction) {
    case 0://вверх
      if(vIndex > 0) {
        this.field[vIndex][hIndex].top = possible;//можно перейти из данной вверх
        this.field[vIndex - 1][hIndex].bottom = possible;//можно перейти из верхней вниз
        break;
      }
      throw `movement to the top from cell with coordinates vIndex = ${vIndex} and hIndex = ${hIndex} is impossible`;
    case 1://вправо
      if(hIndex < this.hSize - 1) {
        this.field[vIndex][hIndex].right = possible;
        this.field[vIndex][hIndex + 1].left = possible;
        break;
      }
      throw `movement to the right from cell with coordinates vIndex = ${vIndex} and hIndex = ${hIndex} is impossible`;
    case 2://вниз
      if(vIndex < this.vSize - 1) {
        this.field[vIndex][hIndex].bottom = possible;
        this.field[vIndex + 1][hIndex].top = possible;
        break;
      }
      throw `movement to the bottom from cell with coordinates vIndex = ${vIndex} and hIndex = ${hIndex} is impossible`;
    case 3://влево
      if(hIndex > 0) {
        this.field[vIndex][hIndex].left = possible;
        this.field[vIndex][hIndex - 1].right = possible;
        break;
      }
      throw `movement to the left from cell with coordinates vIndex = ${vIndex} and hIndex = ${hIndex} is impossible`;
    default:
      throw `direction = ${direction} is incorrect`;
    }
  } else {
    throw `one of index vIndex = ${vIndex} or hIndex = ${hIndex} is incorrect`;
  }
};

module.exports.prototype.setCellToStringHook = function(cellToStringHook) {//если cellToStringHook функция
  if(typeof(cellToStringHook) === 'function') {
    this.cellToStringHook = cellToStringHook;//cellToStringHook = переданная функция
  }
};

module.exports.prototype.toString = function() {
  let result = ''; //result пустой
  let n = (this.vSize << 1) + 1; // this.vSize * 2 + 1
  let m = (this.hSize << 1) + 1; // this.vSize * 2 + 1
  let callback = this.cellToStringHook; // callback - текущая cellToStringHook
  for(let i = 0; i < n; i++) {
    let vIndex = (i - 1) >> 1; // (i - 1) / 2
    for(let j = 0; j < m; j++) {
      let hIndex = (j - 1) >> 1; // (j - 1) / 2
      if(i & 1) {// если i != 0
        if(j & 1) {// если j != 0
          if(this.field[vIndex][hIndex].visible) {//если поле видимо
            let object = ' ';
            if(this.field[vIndex][hIndex].enemy_index != null) {
              object = 'x';
            } else if(this.field[vIndex][hIndex].health_index) {
              object = 'h';
            } else if(this.field[vIndex][hIndex].defence_index) {
              object = 'd';
            } else if(this.field[vIndex][hIndex].luck_index) {
              object = 'l';
            } else if(this.field[vIndex][hIndex].attack_index) {
              object = 'a';
            }
            let cell = callback ? callback(vIndex, hIndex) : null;//есть ли персонаж в клетке или нет
            result += cell ? cell : ' ' + object + ' ';//если персонаж внутри - значок персонажа, иначе пустота
          } else {
            result += '███';//иначе туман
          }
        } else {//если j > 0
          if(j > 0 && this.field[vIndex][hIndex].visible || j < m - 1 && this.field[vIndex][hIndex + 1].visible) {//если видна клетка и клетка справа
            if(j > 0 && j < m - 1 && this.field[vIndex][hIndex].right) {//если можно пойти направо
              result += '│';
            } else {
              result += '║';
            }
          } else {//если клетка не видна
            result += '█';
          }
        }
      } else {// если i > 0
        if(j & 1) {// если j != 0
          if(i > 0 && this.field[vIndex][hIndex].visible || i < n - 1 && this.field[vIndex + 1][hIndex].visible) {//если видна клетка и клетка снизу
            if(i > 0 && i < n - 1 && this.field[vIndex][hIndex].bottom) {//если можно пойти вниз
                result += '═─═';
            } else {
                result += '═══';
            }
          } else {//если клетка не видна
            result += '███';
          }
        } else {//если j > 0
          let x = [
            i > 0 && j > 0 && this.field[vIndex][hIndex].visible,//0 - видно ли само поле, если оно не начальное
            i > 0 && j < m - 1 && this.field[vIndex][hIndex + 1].visible,//1 - видно ли поле справа
            i < n - 1 && j > 0 && this.field[vIndex + 1][hIndex].visible,//2 - видно ли поле внизу
            i < n - 1 && j < m - 1 && this.field[vIndex + 1][hIndex + 1].visible//3 - видно ли поле низ-право
          ];
          let index = 0;
          x.forEach(y => {
            index <<= 1;
            index |= y;
          });
          result += ['█', '╔', '╗', '╦', '╚', '╠', '╬', '╬', '╝', '╬', '╣', '╬', '╩', '╬', '╬', '╬'][index];//рамка
        }
      }
    }
    result += '\n';
  }
  return result;//отображение карты
};
