const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, repair_notes) => {
      app.get('/break', async(req, res) => {
        try {
          let data = await repair_notes.readAll();
          res.render('break/index', {message: 'Список поломок', breaks: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });
  
      app.get('/edit_break', async (req, res) => {
        try {
          let data = await repair_notes.read(req.query.id);
          let msg = 'Редактирование поломки';
          if(data === null) {
            data = {break_notes: '', repair_notes: ''};
            msg = 'Добавление поломки';
          }
          res.render('break/edit', {message: msg, break: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });
  
      app.post('/save_break', async (req, res) => {
        if(notEmpty(req.body.break_notes) && notEmpty(req.body.repair_notes)) {
          try {
            await repair_notes.save(req.body);
            res.redirect('/break');
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });
  
      app.post('/delete_break', async (req, res) => {
        try {
          await repair_notes.delete(req.body.id);
          res.redirect('/break');
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    };
  };
  