const port = 3000;

const express = require('express');
const app = express();
app.use(require('body-parser').urlencoded({extended: true}));
app.use(express.static('css'));

const ect = require('ect');
const renderer = ect({
  watch: true,
  root: __dirname + '/views',
  ext: '.ect'
});
app.set('view engine', 'ect');
app.engine('ect', renderer.render);

const car_model = require('./app/models/car_model');
const car_copy_model = require('./app/models/car_copy_model');
const break_model = require('./app/models/break_model');
const role_model = require('./app/models/role_model');
const route_model = require('./app/models/route_model');
const user_model = require('./app/models/user_model');
const driver_model = require('./app/models/driver_model');
const repair_model = require('./app/models/repair_model');
const telephone_model = require('./app/models/telephone_model');
const vacation_model = require('./app/models/vacation_model');
const trip_model = require('./app/models/trip_model');
const requests_model = require('./app/models/requests_model');

const car_controller = require('./app/controllers/car_controller');
const car_copy_controller = require('./app/controllers/car_copy_controller')
const break_controller = require('./app/controllers/break_controller');
const role_controller = require('./app/controllers/role_controller');
const route_controller = require('./app/controllers/route_controller');
const user_controller = require('./app/controllers/user_controller');
const user_vs_role_controller = require('./app/controllers/user_vs_role_controller');
const driver_controller = require('./app/controllers/driver_controller');
const repair_controller = require('./app/controllers/repair_controller');
const repair_vs_break_controller = require('./app/controllers/repair_vs_break_controller');
const telephone_controller = require('./app/controllers/telephone_controller');
const vacation_controller = require('./app/controllers/vacation_controller');
const trip_controller = require('./app/controllers/trip_controller');
const requests_controller = require('./app/controllers/requests_controller');

car_controller.init(app, car_model);
car_copy_controller.init(app, car_copy_model, car_model);
break_controller.init(app, break_model);
role_controller.init(app, role_model);
route_controller.init(app, route_model);
user_controller.init(app, user_model);
user_vs_role_controller.init(app, role_model, user_model);
driver_controller.init(app, driver_model);
repair_controller.init(app, repair_model, car_copy_model);
repair_vs_break_controller.init(app, repair_model, break_model);
telephone_controller.init(app, telephone_model, driver_model);
vacation_controller.init(app, vacation_model, driver_model);
trip_controller.init(app, trip_model, driver_model, route_model, car_copy_model);
requests_controller.init(app, requests_model, driver_model, car_copy_model);

app.listen(port, (err) => {
  console.log(`Server running at http://localhost:${port}/`);
});

//driver_save new
//mnogie ko mnogim pravilno vybrat