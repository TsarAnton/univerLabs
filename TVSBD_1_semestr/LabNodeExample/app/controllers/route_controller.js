const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, model) => {
     app.get('/route', async(req, res) => {
        try {
          let data = await model.readAll();
          res.render('route/index', {message: 'Список маршрутов', routes: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_route', async (req, res) => {
        try {
          let data = await model.read(req.query.id);
          let msg = 'Редактирование маршрута';
          if(data === null) {
            data = {beg_point: '', end_point: '', time: ''};
            msg = 'Добавление маршрута';
          }
          res.render('route/edit', {message: msg, route: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });
  
      app.post('/save_route', async (req, res) => {
        if(notEmpty(req.body.beg_point) && notEmpty(req.body.end_point) && notEmpty(req.body.time)) {
          try {
            await model.save(req.body);
            res.redirect('/route');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_route', async (req, res) => {
        try {
          await model.delete(req.body.id);
          res.redirect('/route');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };
  