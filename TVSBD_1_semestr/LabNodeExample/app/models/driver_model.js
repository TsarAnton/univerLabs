const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "id", "name", "surname", "patronymic", "category", "license_num", "license_date" FROM "driver"', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "name", "surname", "patronymic", "category", "license_num", "license_date" FROM "driver" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.save = async (driver) => {
    if(driver.id !== undefined) {
      await this._operation(
        'UPDATE "driver" SET "name" = $1, "surname" = $2, "patronymic" = $3, "category" = $4, "license_num" = $5, "license_date" = $6 WHERE "id" = $7',
        [driver.name, driver.surname, driver.patronymic, driver.category, driver.license_num, driver.license_date, driver.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "driver" ("name", "surname", "patronymic", "category", "license_num", "license_date") VALUES ($1, $2, $3, $4, $5, $6)',
        [driver.name, driver.surname, driver.patronymic, driver.category, driver.license_num, driver.license_date]
      );
    }
  };

  this.delete = async (id) => {
    await this._operation('DELETE FROM "driver" WHERE "id" = $1', [id]);
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
