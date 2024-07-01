const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "id", "role_name" FROM "role"', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "role_name" FROM "role" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.readByUser = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "role"."id", "role_name" FROM ("role" INNER JOIN "user_vs_role" ON "role"."id" = "user_vs_role"."role_id") WHERE "user_id" = $1', [id]);
      return res;
    } else {
      return null;
    }
  }

  this.readWithoutUser = async (id) => {
    if(id) {
      let res = await this._operation('SELECT DISTINCT "role"."id", "role_name" FROM ("user_vs_role" INNER JOIN "role" ON "user_vs_role"."role_id" = "role"."id") WHERE "role_id" NOT IN (SELECT "role_id" FROM "user_vs_role" WHERE "user_id" = $1 )', [id]);
      return res;
    } else {
      return null;
    }
  }

  this.save = async (role) => {
    if(role.id !== undefined) {
      await this._operation(
        'UPDATE "role" SET "role_name" = $1 WHERE "id" = $2',
        [role.role_name, role.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "role" ("role_name") VALUES ($1)',
        [role.role_name]
      );
    }
  };

  this.saveRoleUser = async (role, user) => {
    await this._operation(
      'INSERT INTO "user_vs_role" ("role_id", "user_id") VALUES ($1, $2)',
      [role.id, user.id]
    );
  }

  this.deleteRoleUser = async(roleId, userId) => {
    await this._operation(
      'DELETE FROM "user_vs_role" WHERE "user_id" = $1 AND "role_id" = $2',
      [userId, roleId]
    );
  }

  this.delete = async (id) => {
    await this._operation('DELETE FROM "role" WHERE "id" = $1', [id]);
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