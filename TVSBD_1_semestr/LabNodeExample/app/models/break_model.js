const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "id", "repair_notes", "break_notes" FROM "break"', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "repair_notes", "break_notes" FROM "break" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.readWithoutRepair = async (id) => {
    if(id) {
      let res = await this._operation('SELECT DISTINCT "break_id", "break_notes", "repair_notes" FROM ("repair_vs_break" INNER JOIN "break" ON "repair_vs_break"."break_id" = "break"."id") WHERE "break_id" NOT IN (SELECT "break_id" FROM "repair_vs_break" WHERE "break_id" = $1 )', [id]);
      return res;
    } else {
      return null;
    }
  }

  this.readByRepair = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "break"."id", "break_notes", "repair_notes" FROM ("break" INNER JOIN "repair_vs_break" ON "break"."id" = "repair_vs_break"."break_id") WHERE "repair_id" = $1', [id]);
      return res;
    } else {
      return null;
    }
  }

  this.save = async (break1) => {
    if(break1.id !== undefined) {
      await this._operation(
        'UPDATE "break" SET "break_notes" = $1, "repair_notes" = $2 WHERE "id" = $3',
        [break1.break_notes, break1.repair_notes, break1.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "break" ("break_notes", "repair_notes") VALUES ($1, $2)',
        [break1.break_notes, break1.repair_notes]
      );
    }
  };

  this.delete = async (id) => {
    await this._operation('DELETE FROM "break" WHERE "id" = $1', [id]);
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
