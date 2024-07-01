const pool = require("../db_config");

module.exports = new function() {
  this.readAll = async () => {
    return await this._operation('SELECT "repair"."id", "car_copy_id", "beg_date", "plan_end_date", "end_date", "state_num" FROM ("repair" INNER JOIN "car_copy" ON "repair"."car_copy_id"="car_copy"."id")', null);
  };

  this.read = async (id) => {
    if(id) {
      let res = await this._operation('SELECT "id", "car_copy_id", "beg_date", "plan_end_date", "end_date" FROM "repair" WHERE "id" = $1', [id]);
      return res ? res[0] : null;
    } else {
      return null;
    }
  };

  this.save = async (repair) => {
    if(repair.id !== undefined) {
      await this._operation(
        'UPDATE "repair" SET "car_copy_id" = $1, "beg_date" = $2, "plan_end_date" = $3, "end_date" = $4 WHERE "id" = $5',
        [repair.car_copy_id, repair.beg_date, repair.plan_end_date, repair.end_date, repair.id]
      );
    } else {
      await this._operation(
        'INSERT INTO "repair" ("car_copy_id", "beg_date", "plan_end_date", "end_date") VALUES ($1, $2, $3, $4)',
        [repair.car_copy_id, repair.beg_date, repair.plan_end_date, repair.end_date]
      );
    }
  };

  this.saveRepairBreak = async (repair, break1) => {
    await this._operation(
      'INSERT INTO "repair_vs_break" ("repair_id", "break_id") VALUES ($1, $2)',
      [repair.id, break1.id]
    );
  }

  this.deleteRepairBreak = async(repairId, breakId) => {
    await this._operation(
      'DELETE FROM "repair_vs_break" WHERE "break_id" = $1 AND "repair_id" = $2',
      [breakId, repairId]
    );
  }

  this.delete = async (id) => {
    await this._operation('DELETE FROM "repair" WHERE "id" = $1', [id]);
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