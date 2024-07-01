const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, userModel) => {  
      app.get('/user', async(req, res) => {
        try {
          let data = await userModel.readAll();
          res.render('user/index', {message: 'Список пользователей', users: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_user', async (req, res) => {
        try {
          let data = await userModel.read(req.query.id);
          let msg = 'Редактирование поломки';
          if(data === null) {
            data = {login: '', password: ''};
            msg = 'Добавление поломки';
          }
          res.render('user/edit', {message: msg, user: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });
  
      app.post('/save_user', async (req, res) => {
        if(notEmpty(req.body.login) && notEmpty(req.body.password)) {
          try {
            await userModel.save(req.body);
            res.redirect('/user');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_user', async (req, res) => {
        try {
          await userModel.delete(req.body.id);
          res.redirect('/user');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };