const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, requestsModel, driverModel, carCopyModel) => {
        app.get('/', (req, res) => {
            res.render('./index.hbs');
        });

        app.get('/requests', (req, res) => {
            res.render('./requests.hbs');
        })

      app.get('/request2_1', async(req, res) => {
        try {
          let data = await driverModel.readAll();
          res.render('requests/request2_1/edit', {message: 'Задание 2 (1)', drivers: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.post('/get_request2_1', async (req, res) => {
        if(notEmpty(req.body.driver)) {
          try {
            let driver_id = req.body.driver;
            let data = await requestsModel.request2_1(driver_id);
            res.render('requests/request2_1/index', {message: 'Задание 2 (1)', telephones: data})
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });

      app.get('/request2_2', async(req, res) => {
        try {
          let data = await driverModel.readAll();
          res.render('requests/request2_2/edit', {message: 'Задание 2 (2)', drivers: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.post('/get_request2_2', async (req, res) => {
        if(notEmpty(req.body.driver)) {
          try {
            let driver_id = req.body.driver;
            let data = await requestsModel.request2_2(driver_id);
            res.render('requests/request2_2/index', {message: 'Задание 2 (2)', datas: data})
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });

      app.get('/request2_3', async(req, res) => {
        try {
          let data = await carCopyModel.readAll();
          res.render('requests/request2_3/edit', {message: 'Задание 2 (3)', car_copys: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.post('/get_request2_3', async (req, res) => {
        if(notEmpty(req.body.car_copy)) {
          try {
            let car_copy_id = req.body.car_copy;
            let data = await requestsModel.request2_3(car_copy_id);
            res.render('requests/request2_3/index', {message: 'Задание 2 (3)', datas: data})
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });

      app.get('/request2_4', async(req, res) => {
        try {
          let data = await driverModel.readAll();
          res.render('requests/request2_4/edit', {message: 'Задание 2 (4)', drivers: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.post('/get_request2_4', async (req, res) => {
        if(notEmpty(req.body.driver)) {
          try {
            let driver_id = req.body.driver;
            let data = await requestsModel.request2_4(driver_id);
            res.render('requests/request2_4/index', {message: 'Задание 2 (4)', datas: data})
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });

      app.get('/request2_5', async(req, res) => {
        try {
          let data = await requestsModel.request2_5();
          res.render('requests/request2_5/index', {message: 'Задание 2 (5)', datas: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.get('/request3_1', async(req, res) => {
        try {
          let data = await requestsModel.request3_1();
          res.render('requests/request3_1/index', {message: 'Задание 3 (1)', datas: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.get('/request3_2', async(req, res) => {
        try {
          let data = await requestsModel.request3_2();
          res.render('requests/request3_2/index', {message: 'Задание 3 (2)', datas: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.get('/request3_3', async(req, res) => {
        try {
          let data = await requestsModel.request3_3();
          res.render('requests/request3_3/index', {message: 'Задание 3 (3)', datas: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.get('/request4_1', async(req, res) => {
        try {
          let data = await requestsModel.request4_1();
          res.render('requests/request4_1/index', {message: 'Задание 4 (1)', datas: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.get('/request4_2', async(req, res) => {
        try {
          let data = await requestsModel.request4_2();
          res.render('requests/request4_2/index', {message: 'Задание 4 (2)', datas: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.get('/request4_3', async(req, res) => {
        try {
          let data = await requestsModel.request4_3();
          res.render('requests/request4_3/index', {message: 'Задание 4 (3)', datas: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
    };
  };
  