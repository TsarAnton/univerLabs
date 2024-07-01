const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, roleModel, userModel) => {
      app.get('/role_users', async(req, res) => {
        try {
          let roleBuff = await roleModel.read(req.query.id);
          let data = await userModel.readByRole(req.query.id);
          res.render('user_vs_role/role_users', {message: 'Список пользователей', role: roleBuff, users: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.get('/user_roles', async(req, res) => {
        try {
          let userBuff = await userModel.read(req.query.id);
          let data = await roleModel.readByUser(req.query.id);
          res.render('user_vs_role/user_roles', {message: 'Список ролей', roles: data, user: userBuff});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.get('/add_user_to_role', async (req, res) => {
        try {
          let roleData = await roleModel.read(req.query.roleId);
          let data = await userModel.readWithoutRole(req.query.roleId);
          res.render('user_vs_role/add_user_to_role', {message: 'Добавление пользователя', users: data, role: roleData});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });

      app.get('/add_role_to_user', async (req, res) => {
        try {
          let userData = await userModel.read(req.query.userId);
          let data = await roleModel.readWithoutUser(req.query.userId);
          res.render('user_vs_role/add_role_to_user', {message: 'Добавление роли', roles: data, user: userData});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });

      app.post('/save_user_to_role', async (req, res) => {
        console.log(req.body.roleId);
        if(notEmpty(req.body.user)) {
          try {
            let userId = req.body.user;
            let user = await userModel.read(userId);
            let role = await roleModel.read(req.body.roleId);
            await roleModel.saveRoleUser(role, user);
            res.redirect('/role_users?id=' + role.id);
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });

      app.post('/save_role_to_user', async (req, res) => {
        console.log(req.body.role);
        if(notEmpty(req.body.role)) {
          try {
            let roleId = String(req.body.role).split('.')[0];
            let role = await roleModel.read(roleId);
            let user = await userModel.read(req.body.userId);
            await roleModel.saveRoleUser(role, user);
            res.redirect('/user_roles?id=' + user.id);
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });

      app.post('/delete_role_users', async (req, res) => {
        console.log("role = " + req.body.roleId);
        console.log("user = " + req.body.userId);
        try {
          await roleModel.deleteRoleUser(req.body.roleId, req.body.userId);
          res.redirect('/role_users?id=' + req.body.roleId);
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });

      app.post('/delete_user_roles', async (req, res) => {
        try {
          await roleModel.deleteRoleUser(req.body.roleId, req.body.userId);
          res.redirect('/user_roles?id=' + req.body.userId);
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    }
  };