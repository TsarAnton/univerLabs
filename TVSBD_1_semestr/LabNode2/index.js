const express = require("express");
const pg = require('pg');
const { Pool } = require("pg/lib");

const app = express();
const urlencodedParser = express.urlencoded({extended: false});
app.set("view engine", "hbs");

const pgConfig = {
    user: 'postgres',         
    database: 'trip_db',       
    password: '2003',      
    host: 'localhost',       
    port: '5432'                
};
const pool = new pg.Pool(pgConfig);



app.get('/', function (req, res) {
    res.render("index.hbs");
}); 
app.get('/work', function (req, res) {
    res.render("work.hbs");
}); 


//break
app.get("/break", function(req, res){
    pool.query("SELECT id,break_notes FROM public.break",function(err,data){
        if(err) return console.log(err);
        res.render("break/break.hbs",{
          break: data.rows
        })
      })
  });   
app.get('/edit_break/:break_id', function (req, res) {
    const break_id = req.params.break_id;
    pool.query("SELECT * FROM break WHERE id=$1", [break_id], function(err, data) {
        if(err) return console.log(err);
        res.render("break/edit_break.hbs",{
            break: data.rows[0]
        })
    });
});
app.post("/edit_break/:break_id",urlencodedParser, function (req, res) {
    if(!req.body) return res.sendStatus(400);
        const break_id = req.params.break_id;
        const break_notes = req.body.break_notes;
        const repair_notes = req.body.repair_notes;
        pool.query("UPDATE break SET break_notes=$1, repair_notes=$2 WHERE id=$3", [break_notes, repair_notes, break_id], function(err, data) {
            if(err) return console.log(err);
            res.redirect('/break');
        });
  })
app.get('/add_break', function (req, res) {
    res.render("break/add_break.hbs");
});  
app.post("/add_break",urlencodedParser, function (req, res) {
    if(!req.body) return res.sendStatus(400);
    const break_notes = req.body.break_notes;
    const repair_notes = req.body.repair_notes;
    pool.query("INSERT INTO break (break_notes, repair_notes) VALUES ($1,$2)", [break_notes, repair_notes], function(err, data) {
        if(err) return console.log(err);
        res.redirect('/break');
    });
});
app.post("/delete_break/:break_id",urlencodedParser, function(req, res){
    const break_id = req.params.break_id;
    pool.query("DELETE FROM break WHERE id=$1", [break_id], function(err, data) {
    if(err) return console.log(err);
        res.redirect("/break");
    });
});


//car
app.get("/car", function(req, res){
    pool.query("SELECT id,mark,model FROM car",function(err,data){
        if(err) return console.log(err);
        res.render("car/car.hbs",{
          car: data.rows
        })
      })
  });   
app.get('/edit_car/:car_id', function (req, res) {
    const car_id = req.params.car_id;
    pool.query("SELECT * FROM car WHERE id=$1", [car_id], function(err, data) {
        if(err) return console.log(err);
        res.render("car/edit_car.hbs",{
            car: data.rows[0]
        })
    });
});
app.post("/edit_car/:car_id",urlencodedParser, function (req, res) {
    if(!req.body) return res.sendStatus(400);
        const car_id = req.params.car_id;
        const mark = req.body.mark;
        const model = req.body.model;
        const tonnage = req.body.tonnage;
        pool.query("UPDATE car SET mark=$1, model=$2, tonnage=$3 WHERE id=$4", [mark, model, tonnage, car_id], function(err, data) {
            if(err) return console.log(err);
            res.redirect('/car');
        });
  })
app.get('/add_car', function (req, res) {
    res.render("car/add_car.hbs");
});  
app.post("/add_car",urlencodedParser, function (req, res) {
    if(!req.body) return res.sendStatus(400);
    const mark = req.body.mark;
    const model = req.body.model;
    const tonnage = req.body.tonnage;
    pool.query("INSERT INTO car (mark, model, tonnage) VALUES ($1,$2,$3)", [mark, model, tonnage], function(err, data) {
        if(err) return console.log(err);
        res.redirect('/car');
    });
});
app.post("/delete_car/:car_id",urlencodedParser, function(req, res){
    const car_id = req.params.car_id;
    pool.query("DELETE FROM car WHERE id=$1", [car_id], function(err, data) {
    if(err) return console.log(err);
        res.redirect("/car");
    });
});


//car_copy
app.get("/car_copy", function(req, res){
    pool.query("SELECT * FROM (car_copy INNER JOIN car ON car_copy.car_id = car.id)", function(err,data){
        if(err) return console.log(err);
        res.render("car_copy/car_copy.hbs",{
          car_copy: data.rows
        })
      })
  });   
app.get('/edit_car_copy/:car_copy_id', function (req, res) {
    const car_copy_id = req.params.car_copy_id;
    pool.query("SELECT state_num, car_id FROM car_copy WHERE car_copy.id = $1", [car_copy_id], function(err, data) {
        if(err) return console.log(err);
        const car_copy_data = data.rows[0];
        pool.query("SELECT mark, model FROM car", function(err, data) {
            console.log(data.rows);
            if(err) return console.log(err);
            res.render("car_copy/edit_car_copy.hbs", {
                car_copy: car_copy_data,
                car: data.rows
            })
        })
    });
});
app.post("/edit_car/:car_id",urlencodedParser, function (req, res) {
    if(!req.body) return res.sendStatus(400);
        const car_copy_id = req.params.car_id;
        const state_num = req.body.state_num;
        const car_id = req.body.state_num;
        pool.query("UPDATE car SET state_num=$1, car_id=$2 WHERE id=$4", [state_num, car_id, car_copy_id], function(err, data) {
            if(err) return console.log(err);
            res.redirect('/car_copy');
        });
  })
app.get('/add_car_copy', function (req, res) {
    pool.query("SELECT mark,model FROM car", function(err, data) {
        console.log(data.rows);
        if(err) return console.log(err);
        res.render("car/add_car.hbs", {
            car: data.rows
        });
    })
    res.render("car_copy/add_car_copy.hbs");
});  
app.post("/add_car",urlencodedParser, function (req, res) {
    if(!req.body) return res.sendStatus(400);
    const mark = req.body.mark;
    const model = req.body.model;
    const tonnage = req.body.tonnage;
    pool.query("INSERT INTO car (mark, model, tonnage) VALUES ($1,$2,$3)", [mark, model, tonnage], function(err, data) {
        if(err) return console.log(err);
        res.redirect('/car');
    });
});
app.post("/delete_car_copy/:car_copy_id",urlencodedParser, function(req, res){
    const car_copy_id = req.params.car_copy_id;
    pool.query("DELETE FROM car_copy WHERE id=$1", [car_copy_id], function(err, data) {
        if(err) return console.log(err);
        res.redirect("/car_copy");
    });
});

app.listen(3000, function(){
    console.log("Сервер ожидает подключения...");
  });