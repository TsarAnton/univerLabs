const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, carCopyModel, carModel) => {
      app.get('/car_copy', async(req, res) => {
        try {
          let data = await carCopyModel.readAll();
          res.render('car_copy/index', {message: 'Список единиц машин', car_copys: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_car_copy', async (req, res) => {
        try {
          let data = await carCopyModel.read(req.query.id);
          let carsData = await carModel.readAll();
          let msg = 'Редактирование единицы машины';
          if(data === null) {
            data = {state_num: ''};
            msg = 'Добавление единицы машины';
          }
          res.render('car_copy/edit', {message: msg, car_copy: data, cars: carsData});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });
  
      app.post('/save_car_copy', async (req, res) => {
        if(notEmpty(req.body.car) && notEmpty(req.body.state_num)) {
          try {
            let car_id = req.body.car;
            let car_copy = {
              id: req.body.id,
              state_num: req.body.state_num,
              car_id: car_id
            }
            console.log(car_copy);
            await carCopyModel.save(car_copy);
            res.redirect('/car_copy');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_car_copy', async (req, res) => {
        try {
          await carCopyModel.delete(req.body.id);
          res.redirect('/car_copy');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };
  