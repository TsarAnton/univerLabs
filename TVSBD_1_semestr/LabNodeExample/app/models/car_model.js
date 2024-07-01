const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "id", "mark", "model", "tonnage" FROM "car"', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "mark", "model", "tonnage" FROM "car" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.save = async (car) => {
    if(car.id !== undefined) {
      await this._operation(
        'UPDATE "car" SET "mark" = $1, "model" = $2, "tonnage" = $3 WHERE "id" = $4',
        [car.mark, car.model, car.tonnage, car.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "car" ("mark", "model", "tonnage") VALUES ($1, $2, $3)',
        [car.mark, car.model, car.tonnage]
      );
    }
  };

  this.delete = async (id) => {
    await this._operation('DELETE FROM "car" WHERE "id" = $1', [id]);
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
