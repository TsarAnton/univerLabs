const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, telephoneModel, driverModel) => {
      app.get('/telephone', async(req, res) => {
        try {
          let data = await telephoneModel.readAll();
          res.render('telephone/index', {message: 'Список телефонных номеров', telephones: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_telephone', async (req, res) => {
        try {
          let data = await telephoneModel.read(req.query.id);
          let driversData = await driverModel.readAll();
          let msg = 'Редактирование номера';
          if(data === null) {
            data = {number: ''};
            msg = 'Добавление номера';
          }
          res.render('telephone/edit', {message: msg, telephone: data, drivers: driversData});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });
  
      app.post('/save_telephone', async (req, res) => {
        if(notEmpty(req.body.driver) && notEmpty(req.body.number)) {
          try {
            let driver_id = req.body.driver;
            let telephone = {
              id: req.body.id,
              number: req.body.number,
              driver_id: driver_id
            }
            await telephoneModel.save(telephone);
            res.redirect('/telephone');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_telephone', async (req, res) => {
        try {
          await telephoneModel.delete(req.body.id);
          res.redirect('/telephone');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };
  