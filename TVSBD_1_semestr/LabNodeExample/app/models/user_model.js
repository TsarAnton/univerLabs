const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "id", "login", "password" FROM "user"', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "login", "password" FROM "user" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.readByRole = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "user"."id", "login", "password" FROM ("user" INNER JOIN "user_vs_role" ON "user"."id" = "user_vs_role"."user_id") WHERE "role_id" = $1', [id]);
      return res;
    } else {
      return null;
    }
  }

  this.readWithoutRole = async (id) => {
    if(id) {
      let res = await this._operation('SELECT DISTINCT "user"."id", "login", "password" FROM ("user_vs_role" INNER JOIN "user" ON "user_vs_role"."user_id" = "user"."id") WHERE "user_id" NOT IN (SELECT "user_id" FROM "user_vs_role" WHERE "role_id" = $1)', [id]);
      return res;
    } else {
      return null;
    }
  }

  this.save = async (user1) => {
    if(user1.id !== undefined) {
      await this._operation(
        'UPDATE "user" SET "password" = $1, "login" = $2 WHERE "id" = $3',
        [user1.password, user1.login, user1.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "user" ("password", "login") VALUES ($1, $2)',
        [user1.password, user1.login]
      );
    }
  };

  this.delete = async (id) => {
    await this._operation('DELETE FROM "user" WHERE "id" = $1', [id]);
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
