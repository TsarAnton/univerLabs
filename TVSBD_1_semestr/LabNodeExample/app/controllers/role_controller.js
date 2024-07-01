const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, roleModel) => {
      app.get('/role', async(req, res) => {
        try {
          let data = await roleModel.readAll();
          res.render('role/index', {message: 'Список ролей', roles: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_role', async (req, res) => {
        try {
          let data = await roleModel.read(req.query.id);
          let msg = 'Редактирование роли';
          if(data === null) {
            data = {role_name: ''};
            msg = 'Добавление роли';
          }
          res.render('role/edit', {message: msg, role: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });
  
      app.post('/save_role', async (req, res) => {
        if(notEmpty(req.body.role_name)) {
          try {
            await roleModel.save(req.body);
            res.redirect('/role');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_role', async (req, res) => {
        try {
          await roleModel.delete(req.body.id);
          res.redirect('/role');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };