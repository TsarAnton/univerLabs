const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "car_copy"."id", "car_id", "mark", "model", "state_num" FROM ("car_copy" INNER JOIN "car" ON "car_copy"."car_id"="car"."id")', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "state_num", "car_id" FROM "car_copy" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.save = async (car_copy) => {
    if(car_copy.id !== undefined) {
      await this._operation(
        'UPDATE "car_copy" SET "state_num" = $1, "car_id" = $2 WHERE "id" = $3',
        [car_copy.state_num, car_copy.car_id, car_copy.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "car_copy" ("state_num", "car_id") VALUES ($1, $2)',
        [car_copy.state_num, car_copy.car_id]
      );
    }
  };

  this.delete = async (id) => {
    await this._operation('DELETE FROM "car_copy" WHERE "id" = $1', [id]);
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