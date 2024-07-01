const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "telephone"."id", "driver_id", "name", "surname", "number", "patronymic" FROM ("telephone" INNER JOIN "driver" ON "telephone"."driver_id"="driver"."id")', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "number", "driver_id" FROM "telephone" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.save = async (telephone) => {
    if(telephone.id !== undefined) {
      await this._operation(
        'UPDATE "telephone" SET "number" = $1, "driver_id" = $2 WHERE "id" = $3',
        [telephone.number, telephone.driver_id, telephone.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "telephone" ("number", "driver_id") VALUES ($1, $2)',
        [telephone.number, telephone.driver_id]
      );
    }
  };

  this.delete = async (id) => {
    await this._operation('DELETE FROM "telephone" WHERE "id" = $1', [id]);
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