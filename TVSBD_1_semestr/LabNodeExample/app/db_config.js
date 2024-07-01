const {Pool} = require('pg');

module.exports = new function(){
    const pool = new Pool({
        user: 'postgres',
        password: '2003',
        host: 'localhost',
        port: '5432',
        database: 'trip_db',
        max: 10
    });
    return pool;
}