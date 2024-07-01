const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, tripModel, driverModel, routeModel, carCopyModel) => {  
      app.get('/trip', async(req, res) => {
        try {
          let data = await tripModel.readAll();
          res.render('trip/index', {message: 'Список поездок', trips: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_trip', async (req, res) => {
        try {
          let data = await tripModel.read(req.query.id);
          let driversData = await driverModel.readAll();
          console.log(driversData)
          let carCopysData = await carCopyModel.readAll();
          console.log(carCopysData)
          let routesData = await routeModel.readAll();
          console.log(routesData)
          let msg = 'Редактирование поездки';
          if(data === null) {
            data = {beg_date: '', end_date: '', weight: ''};
            msg = 'Добавление поездки';
          }
          res.render('trip/edit', {message: msg, trip: data, drivers: driversData, car_copys: carCopysData, routes: routesData});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошибка работы с базой данных 2', err);
        }
      });
  
      app.post('/save_trip', async (req, res) => {
        if(notEmpty(req.body.driver) && notEmpty(req.body.beg_date) && notEmpty(req.body.end_date) && notEmpty(req.body.weight) && notEmpty(req.body.car_copy) && notEmpty(req.body.route)) {
          try {
            let driver_id = req.body.driver;
            let car_copy_id = req.body.car_copy;
            let route_id = req.body.route;
            let trip = {
              id: req.body.id,
              beg_date: req.body.beg_date,
              end_date: req.body.end_date,
              weight: req.body.weight,
              driver_id: driver_id,
              car_copy_id: car_copy_id,
              route_id: route_id
            }
            console.log(trip);
            await tripModel.save(trip);
            res.redirect('/trip');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_trip', async (req, res) => {
        try {
          await tripModel.delete(req.body.id);
          res.redirect('/trip');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };