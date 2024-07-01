const Labyrinth = require('./labyrinth');
const RandomInteger = require('./randomInteger');

module.exports = function(vIndex, hIndex, v, h) {
	let labyrinth = new Labyrinth(v, h);

	for(i = 0; i < labyrinth.vSize; i++) {
		for(j = 0; j < labyrinth.hSize; j++) {
			if(i == 0) {
				if(j != labyrinth.hSize - 1) {
					labyrinth.setMovePossible(i, j, 1, true);
				}
			} else {
				let rand = RandomInteger(0, 1);
				if(rand == 1 && j != labyrinth.hSize - 1) {
					labyrinth.setMovePossible(i, j, 1, true);
				} else {
					labyrinth.setMovePossible(i, j, 0, true);
				}
			}
		}
	}

	return labyrinth;
};