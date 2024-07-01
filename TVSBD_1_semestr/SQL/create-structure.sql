CREATE TABLE "user" (
    "id"                  SERIAL   PRIMARY KEY,
    "login"               TEXT     NOT NULL UNIQUE,
    "password"            TEXT     NOT NULL
);

CREATE TABLE "role" (
    "id"                  SERIAL   PRIMARY KEY,
    "role_name"           TEXT     NOT NULL
);

CREATE TABLE "user_vs_role" (
    "id"                  SERIAL   PRIMARY KEY,
    "user_id"             INTEGER  NOT NULL REFERENCES "user" ("id")      ON UPDATE RESTRICT ON DELETE RESTRICT,  
    "role_id"             INTEGER  NOT NULL REFERENCES "role" ("id")      ON UPDATE RESTRICT ON DELETE RESTRICT,
    UNIQUE("user_id", "role_id")
);

CREATE TABLE "driver" (
    "id"                  INTEGER  PRIMARY KEY REFERENCES "user" ("id")   ON UPDATE RESTRICT ON DELETE RESTRICT, 
    "name"                TEXT     NOT NULL,
    "surname"             TEXT     NOT NULL,
    "patronymic"          TEXT     NOT NULL,
    "category"            TEXT     NOT NULL,
    "license_num"         TEXT     NOT NULL UNIQUE,
    "license_date"        DATE     NOT NULL  
);

CREATE TABLE "telephone" (
    "id"                  SERIAL   PRIMARY KEY,
    "number"              TEXT     NOT NULL UNIQUE,
    "driver_id"           INTEGER  NOT NULL REFERENCES "driver" ("id")   ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE "vacation" (
    "id"                  SERIAL   PRIMARY KEY,
    "driver_id"           INTEGER  NOT NULL  REFERENCES "driver" ("id")  ON UPDATE RESTRICT ON DELETE RESTRICT,
    "beg_date"            DATE     NOT NULL,
    "end_date"            DATE     NOT NULL 
);

CREATE TABLE "car" (
    "id"                  SERIAL   PRIMARY KEY,
    "mark"                TEXT     NOT NULL,
    "model"               TEXT     NOT NULL,
    "tonnage"             INTEGER     NOT NULL
);

CREATE TABLE "car_copy" (
    "id"                  SERIAL   PRIMARY KEY,
    "state_num"           TEXT  NOT NULL UNIQUE,
    "car_id"              INTEGER  NOT NULL  REFERENCES "car" ("id")    ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE "repair" (
    "id"                  SERIAL   PRIMARY KEY,
    "car_copy_id"         INTEGER  NOT NULL  REFERENCES "car_copy" ("id")    ON UPDATE RESTRICT ON DELETE RESTRICT,
    "beg_date"            DATE     NOT NULL,
    "plan_end_date"       DATE     NOT NULL,
    "end_date"            DATE
);

CREATE TABLE "break" (
    "id"                  SERIAL   PRIMARY KEY,
    "break_notes"         TEXT     NOT NULL,
    "repair_notes"        TEXT     NOT NULL
);

CREATE TABLE "repair_vs_break" (
    "id"                  SERIAL   PRIMARY KEY,
    "repair_id"           INTEGER  NOT NULL REFERENCES "repair" ("id")   ON UPDATE RESTRICT ON DELETE RESTRICT,
    "break_id"            INTEGER  NOT NULL REFERENCES "break" ("id")    ON UPDATE RESTRICT ON DELETE RESTRICT,
    UNIQUE("repair_id", "break_id")
);

CREATE TABLE "route" (
    "id"                  SERIAL  PRIMARY KEY,
    "beg_point"           TEXT    NOT NULL,
    "end_point"           TEXT    NOT NULL,
    "time"                TIME    NOT NULL 
);

CREATE TABLE "trip" (
    "id"                  SERIAL   PRIMARY KEY,
    "car_copy_id"         INTEGER  NOT NULL REFERENCES "car_copy" ("id")  ON UPDATE RESTRICT ON DELETE RESTRICT,
    "driver_id"           INTEGER  NOT NULL REFERENCES "driver" ("id")    ON UPDATE RESTRICT ON DELETE RESTRICT,
    "route_id"            INTEGER  NOT NULL REFERENCES "route" ("id")     ON UPDATE RESTRICT ON DELETE RESTRICT,
    "beg_date"            DATE     NOT NULL,
    "end_date"            DATE,
    "weight"              INTEGER  NOT NULL
);
