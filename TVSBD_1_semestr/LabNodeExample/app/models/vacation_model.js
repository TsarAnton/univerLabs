const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "vacation"."id", "driver_id", "name", "surname", "beg_date", "end_date", "patronymic" FROM ("vacation" INNER JOIN "driver" ON "vacation"."driver_id"="driver"."id")', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "beg_date", "end_date", "driver_id" FROM "vacation" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.save = async (vacation) => {
    if(vacation.id !== undefined) {
      await this._operation(
        'UPDATE "vacation" SET "beg_date" = $1, "driver_id" = $2, "end_date" = $3 WHERE "id" = $4',
        [vacation.beg_date, vacation.driver_id, vacation.end_date, vacation.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "vacation" ("beg_date", "driver_id", "end_date") VALUES ($1, $2, $3)',
        [vacation.beg_date, vacation.driver_id, vacation.end_date]
      );
    }
  };

  this.delete = async (id) => {
    await this._operation('DELETE FROM "vacation" WHERE "id" = $1', [id]);
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