const notEmpty = function (str) {
    return str !== null && str !== undefined && str.trim().length > 0;
  };
  
  module.exports = new function() {
    this.init = (app, repairModel, breakModel) => {
      app.get('/repair_breaks', async(req, res) => {
        try {
          let repairBuff = await repairModel.read(req.query.id);
          let data = await breakModel.readByRepair(req.query.id);
          console.log(data)
          res.render('repair_vs_break/repair_breaks', {message: 'Список поломок', repair: repairBuff, breaks: data});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 1');
          console.log('Ошика работы с базой данных 1', err);
        }
      });

      app.get('/add_break_to_repair', async (req, res) => {
        try {
          let repairData = await repairModel.read(req.query.repairId);
          let data = await breakModel.readWithoutRepair(req.query.repairId);
          console.log(data);
          res.render('repair_vs_break/add_break_to_repair', {message: 'Добавление поломки', breaks: data, repair: repairData});
        } catch(err) {
          res.status(500).send('Ошика работы с базой данных 2');
          console.log('Ошика работы с базой данных 2', err);
        }
      });

      app.post('/save_break_to_repair', async (req, res) => {
        if(notEmpty(req.body.break)) {
          try {
            let breakId = req.body.break;
            let break1 = await breakModel.read(breakId);
            let repair = await repairModel.read(req.body.repairId);
            console.log(break1);
            await repairModel.saveRepairBreak(repair, break1);
            res.redirect('/repair_breaks?id=' + repair.id);
          } catch(err) {
            res.status(500).send('Ошика работы с базой данных 3');
            console.log('Ошика работы с базой данных 3', err);
          }
        } else {
          res.status(400).send('Не переданы необходимые данные');
        }
      });

      app.post('/delete_repair_breaks', async (req, res) => {
        try {
          await repairModel.deleteRepairBreak(req.body.repairId, req.body.breakId);
          res.redirect('/repair_breaks?id=' + req.body.repairId);
        } catch(err) {
            res.status(500).send('Ошика работы с базой данных 4');
            console.log('Ошика работы с базой данных 4', err);
        }
      });
    }
  };