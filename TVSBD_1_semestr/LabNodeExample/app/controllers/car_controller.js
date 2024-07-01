const notEmpty = function (str) {
  return str !== null && str !== undefined && str.trim().length > 0;
};

module.exports = new function() {
  this.init = (app, model) => {
    app.get('/car', async(req, res) => {
      try {
        let data = await model.readAll();
        res.render('car/index', {message: 'Список машин', cars: data});
      } catch(err) {
        res.status(500).send('Ошика работы с базой данных 1');
        console.log('Ошика работы с базой данных 1', err);
      }
    });

    app.get('/edit_car', async (req, res) => {
      try {
        let data = await model.read(req.query.id);
        let msg = 'Редактирование машины';
        if(data === null) {
          data = {mark: '', model: '', tonnage: ''};
          msg = 'Добавление машины';
        }
        res.render('car/edit', {message: msg, car: data});
      } catch(err) {
        res.status(500).send('Ошика работы с базой данных 2');
        console.log('Ошика работы с базой данных 2', err);
      }
    });

    app.post('/save_car', async (req, res) => {
      if(notEmpty(req.body.mark) && notEmpty(req.body.model) && notEmpty(req.body.tonnage)) {
        try {
          await model.save(req.body);
          res.redirect('/car');
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 3');
          console.log('Ошика работы с базой данных 3', err);
        }
      } else {
        res.status(400).send('Не переданы необходимые данные');
      }
    });

    app.post('/delete_car', async (req, res) => {
      try {
        await model.delete(req.body.id);
        res.redirect('/car');
      } catch(err) {
          res.status(500).send('Ошика работы с базой данных 4');
          console.log('Ошика работы с базой данных 4', err);
      }
    });
  };
};
