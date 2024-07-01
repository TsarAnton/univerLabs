const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, vacationModel, driverModel) => {
      app.get('/vacation', async(req, res) => {
        try {
          let data = await vacationModel.readAll();
          res.render('vacation/index', {message: 'Список отпусков', vacations: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_vacation', async (req, res) => {
        try {
          let data = await vacationModel.read(req.query.id);
          let driversData = await driverModel.readAll();
          let msg = 'Редактирование отпуска';
          if(data === null) {
            data = {beg_date: '', end_date: ''};
            msg = 'Добавление отпуска';
          }
          res.render('vacation/edit', {message: msg, vacation: data, drivers: driversData});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });
  
      app.post('/save_vacation', async (req, res) => {
        if(notEmpty(req.body.driver) && notEmpty(req.body.beg_date) && notEmpty(req.body.end_date)) {
          try {
            let driver_id = req.body.driver;
            let vacation = {
              id: req.body.id,
              beg_date: req.body.beg_date,
              driver_id: driver_id,
              end_date: req.body.end_date
            }
            await vacationModel.save(vacation);
            res.redirect('/vacation');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_vacation', async (req, res) => {
        try {
          await vacationModel.delete(req.body.id);
          res.redirect('/vacation');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };