SELECT * FROM "user" WHERE "id" = 1;
SELECT * FROM "role" WHERE "id" = 1;
SELECT * FROM "user_vs_role" WHERE "id" = 1;
SELECT * FROM "driver" WHERE "id" = 2;
SELECT * FROM "telephone" WHERE "id" = 1;
SELECT * FROM "vacation" WHERE "id" = 1;
SELECT * FROM "car" WHERE "id" = 1;
SELECT * FROM "repair" WHERE "id" = 1;
SELECT * FROM "break" WHERE "id" = 1;
SELECT * FROM "repair_vs_break" WHERE "id" = 1;
SELECT * FROM "route" WHERE "id" = 1;
SELECT * FROM "trip" WHERE "id" = 1;

SELECT "number" FROM "telephone" WHERE "driver_id" = 3;

SELECT * FROM "vacation" WHERE "driver_id" = 5 ORDER BY "beg_date";

SELECT "car_copy_id","beg_date","end_date","break_notes","repair_notes"
FROM ("repair" INNER JOIN "repair_vs_break" ON "repair"."id" = "repair_vs_break"."repair_id")
INNER JOIN "break" ON "repair_vs_break"."break_id" = "break"."id"
WHERE "car_copy_id" = 5 ORDER BY "beg_date";

SELECT "driver_id", "car_copy_id", "beg_point", "end_point", "beg_date", "end_date", "weight"
FROM ("trip" INNER JOIN "route" ON "trip"."route_id" = "route"."id")
WHERE "driver_id" = 2 ORDER BY "beg_point","end_point", "weight";

SELECT "beg_point", "end_point", "beg_date", "end_date", "weight"
FROM ("trip" INNER JOIN "route" ON "trip"."route_id" = "route"."id")
WHERE "end_date" IS NULL;

SELECT "name", "surname", "patronymic", "number" FROM "driver" INNER JOIN "telephone" ON "driver"."id" = "telephone"."driver_id";
SELECT * FROM "user" LEFT JOIN "driver" ON "user"."id" = "driver"."id";
SELECT * FROM "driver" RIGHT JOIN "user" ON "driver"."id" = "user"."id";

SELECT "car_copy_id", COUNT("beg_date") AS "num_repair" FROM "repair" GROUP BY "car_copy_id";
SELECT "driver_id", MIN("beg_date") AS "first_vacation" FROM "vacation" GROUP BY "driver_id";
SELECT "car_copy_id", SUM("weight") AS "overall_weight" FROM "trip" GROUP BY "car_copy_id";
