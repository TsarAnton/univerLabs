const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, model) => {
      app.get('/driver', async(req, res) => {
        try {
          let data = await model.readAll();
          res.render('driver/index', {message: 'Список водителей', drivers: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_driver', async (req, res) => {
        try {
          let data = await model.read(req.query.id);
          let msg = 'Редактирование водителя';
          if(data === null) {
            data = {name: '', surname: '', patronymic: '', category: '', license_num: '', license_date: ''};
            msg = 'Добавление водителя';
          }
          res.render('driver/edit', {message: msg, driver: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });
  
      app.post('/save_driver', async (req, res) => {
        if(notEmpty(req.body.name) && notEmpty(req.body.surname) && notEmpty(req.body.patronymic) && notEmpty(req.body.category) && notEmpty(req.body.license_num) && notEmpty(req.body.license_date)) {
          try {
            await model.save(req.body);
            res.redirect('/driver');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_driver', async (req, res) => {
        try {
          await model.delete(req.body.id);
          res.redirect('/driver');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };
  