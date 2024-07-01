const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "trip"."id", "car_copy_id", "state_num", "mark", "model", "driver_id", "name", "surname", "patronymic", "route_id", "beg_point", "end_point", "beg_date", "end_date", "weight" FROM (((("trip" INNER JOIN "driver" ON "trip"."driver_id" = "driver"."id") INNER JOIN "route" ON "trip"."route_id" = "route"."id") INNER JOIN "car_copy" ON "trip"."car_copy_id" = "car_copy"."id") INNER JOIN "car" ON "car_copy"."car_id" = "car"."id")', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "driver_id", "route_id", "car_copy_id", "beg_date", "end_date", "weight" FROM "trip" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.save = async (trip) => {
    if(trip.id !== undefined) {
      await this._operation(
        'UPDATE "trip" SET "car_copy_id" = $1, "driver_id" = $2, "route_id" = $3, "beg_date" = $4, "end_date" = $5, "weight" = $6 WHERE "id" = $7',
        [trip.car_copy_id, trip.driver_id, trip.route_id, trip.beg_date, trip.end_date, trip.weight, trip.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "trip" ("car_copy_id", "driver_id", "route_id", "beg_date", "end_date", "weight") VALUES ($1, $2, $3, $4, $5, $6)',
        [trip.car_copy_id, trip.driver_id, trip.route_id, trip.beg_date, trip.end_date, trip.weight]
      );
    }
  };

  this.delete = async (id) => {
    await this._operation('DELETE FROM "trip" WHERE "id" = $1', [id]);
  };

  this._operation = async (sql, data) => {
    let client = await pool.connect();
    try {
      let res = await client.query(sql, data);
      return res ? res.rows : undefined;
    } catch(err) {
      throw err;
    } finally {
      client.release();
    }
  };
};