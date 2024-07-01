const pool = require("../db_config");

module.exports = new function() {
  this.request2_1 = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "number" FROM "telephone" WHERE "driver_id" = $1', [id]);
      return res ? res : null;
    } else {
      return null;
    }
  };

  this.request2_2 = async (id) => {
    if(id) {
      let res = await this._operation('SELECT * FROM "vacation" WHERE "driver_id" = $1 ORDER BY "beg_date"', [id]);
      return res ? res : null;
    } else {
      return null;
    }
  };

  this.request2_3 = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "car_copy_id","beg_date","end_date","break_notes","repair_notes" FROM ("repair" INNER JOIN "repair_vs_break" ON "repair"."id" = "repair_vs_break"."repair_id") INNER JOIN "break" ON "repair_vs_break"."break_id" = "break"."id" WHERE "car_copy_id" = $1 ORDER BY "beg_date"', [id]);
      return res ? res : null;
    } else {
      return null;
    }
  };
  
  this.request2_4 = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "driver_id", "car_copy_id", "beg_point", "end_point", "beg_date", "end_date", "weight" FROM ("trip" INNER JOIN "route" ON "trip"."route_id" = "route"."id") WHERE "driver_id" = $1 ORDER BY "beg_point","end_point", "weight"', [id]);
      return res ? res : null;
    } else {
      return null;
    }
  };

  this.request2_5 = async () => {
    let res = await this._operation('SELECT "beg_point", "end_point", "beg_date", "end_date", "weight" FROM ("trip" INNER JOIN "route" ON "trip"."route_id" = "route"."id") WHERE "end_date" IS NULL', null);
    return res ? res : null;
  };

  this.request3_1 = async () => {
      let res = await this._operation('SELECT "name", "surname", "patronymic", "number" FROM "driver" INNER JOIN "telephone" ON "driver"."id" = "telephone"."driver_id"', null);
      return res ? res : null;
  };

  this.request3_2 = async () => {
    let res = await this._operation('SELECT * FROM "user" LEFT JOIN "driver" ON "user"."id" = "driver"."id"', null);
    return res ? res : null;
  };

  this.request3_3 = async () => {
    let res = await this._operation('SELECT * FROM "driver" RIGHT JOIN "user" ON "driver"."id" = "user"."id"', null);
    return res ? res : null;
  };

  this.request4_1 = async () => {
    let res = await this._operation('SELECT "state_num", COUNT("beg_date") AS "num_repair" FROM ("repair" INNER JOIN "car_copy" ON "repair"."car_copy_id" = "car_copy"."id") GROUP BY "state_num"', null);
    return res ? res : null;
  };

  this.request4_2 = async () => {
    let res = await this._operation('SELECT "driver_id", MIN("beg_date") AS "first_vacation" FROM "vacation" GROUP BY "driver_id"', null);
    return res ? res : null;
  };

  this.request4_3 = async () => {
    let res = await this._operation('SELECT "state_num", SUM("weight") AS "overall_weight" FROM ("trip" INNER JOIN "car_copy" ON "trip"."car_copy_id" = "car_copy"."id") GROUP BY "state_num"', null);
    return res ? res : null;
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