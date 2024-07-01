const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, repairModel, carCopyModel) => {
      app.get('/repair', async(req, res) => {
        try {
          let data = await repairModel.readAll();
          res.render('repair/index', {message: 'Список ремонтов', repairs: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_repair', async (req, res) => {
        try {
          let data = await repairModel.read(req.query.id);
          let carsCopyData = await carCopyModel.readAll();
          let msg = 'Редактирование ремонта';
          if(data === null) {
            data = {beg_date: '', end_date: '', plan_end_date: ''};
            msg = 'Добавление ремонта';
          }
          res.render('repair/edit', {message: msg, repair: data, car_copys: carsCopyData});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });
  
      app.post('/save_repair', async (req, res) => {
        if(notEmpty(req.body.car_copy) && notEmpty(req.body.beg_date) && notEmpty(req.body.plan_end_date) && notEmpty(req.body.end_date)) {
          try {
            let car_copy_id = req.body.car_copy;
            let repair = {
              id: req.body.id,
              beg_date: req.body.beg_date,
              plan_end_date: req.body.plan_end_date,
              end_date: req.body.end_date,
              car_copy_id: car_copy_id
            }
            await repairModel.save(repair);
            res.redirect('/repair');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_repair', async (req, res) => {
        try {
          await repairModel.delete(req.body.id);
          res.redirect('/repair');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };