const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "id", "beg_point", "end_point", "time" FROM "route"', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "beg_point", "end_point", "time" FROM "route" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.save = async (route) => {
    if(route.id !== undefined) {
      await this._operation(
        'UPDATE "route" SET "beg_point" = $1, "end_point" = $2, "time" = $3 WHERE "id" = $4',
        [route.beg_point, route.end_point, route.time, route.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "route" ("beg_point", "end_point", "time") VALUES ($1, $2, $3)',
        [route.beg_point, route.end_point, route.time]
      );
    }
  };

  this.delete = async (id) => {
    await this._operation('DELETE FROM "route" WHERE "id" = $1', [id]);
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