INSERT INTO "user"
("id", "login"  , "password") VALUES
(  1 , 'root'   , 'root'),
(  2 , 'user1'  , 'user12'),
(  3 , 'user2'  , 'user34'),
(  4 , 'user3'  , 'user56'),
(  5 , 'user4'  , 'user67');
SELECT setval('user_id_seq', 5);

INSERT INTO "enrollee" 
("id", "name", "surname", "patronymic", "certificateScore", "firstExamScore", "secondExamScore", "thirdExamScore") VALUES
(1, 'Sidor', 'Sidorov', 'Sidorovich', 8.2, 8, 7, 9),
(2, 'Aleksei', 'Alekseev', 'Alekseevich', 5.0, 6, 2, 7),
(3, 'Kisel', 'Kiselev', 'Kiselelovich', 7.0, 9, 7, 8),
(4, 'Ivan', 'Ivanov', 'Ivanovich', 7.0, 9, 8, 7),
(5, 'Denis', 'Denisov', 'Denisovich', 8.2, 2, 4, 4),
(6, 'Vladimir', 'Vladimirov', 'Vladimirovich', 8.2, 9, 8, 7),
(7, 'Anton', 'Antonov', 'Antonovich', 5.0, 8, 7, 6),
(8, 'Nikolai', 'Nikolaev', 'Nikolaevich', 9.0, 10, 9, 10),
(9, 'Artur', 'Arturov', 'Arturovich', 6.0, 8, 7, 5);
SELECT setval('enrollee_id_seq', 5);

INSERT INTO "role"
("id", "role_name") VALUES
(  1 , 'Admin'),
(  2 , 'role1'),
(  3 , 'role2'),
(  4 , 'role3');
SELECT setval('role_id_seq', 4);

INSERT INTO "user_vs_role"
("id", "user_id" , "role_id") VALUES
(  1,  1,          1),
(  2,  2,          3),
(  3,  2,          2),
(  4,  3,          2),
(  5,  4,          3),
(  6,  5,          4);
SELECT setval('user_vs_role_id_seq', 6);

INSERT INTO "driver"
("id", "name",  "surname",  "patronymic",   "category", "license_num", "license_date") VALUES
(  2 , 'Антон', 'Емельянов', 'Дмитриевич',  'B',        'АА123456',    '2000-02-06'),
(  3 , 'Егор',  'Семенов',   'Сергеевич',   'D',        'AA654321',    '2001-06-08'),
(  4 , 'Никита','Литвинков', 'Владимирович','F',        'AA651243',    '2015-09-01'),
(  5 , 'Михаил','Пучков',    'Семенович',   'I',        'AA786512',    '2007-10-22');

INSERT INTO "telephone"
("id", "number"             , "driver_id") VALUES
(1,    '+375 (29) 123-45-67',      2     ),
(2,    '+375 (33) 890-12-34',      3     ),
(3,    '+375 (25) 567-89-01',      4     ),
(4,    '+375 (29) 234-56-78',      5     ),
(5,    '+375 (29) 235-57-79',      3     );
SELECT setval('telephone_id_seq', 5);

INSERT INTO "vacation"
("id", "driver_id", "beg_date",   "end_date") VALUES
(  1 , 2,           '2005-09-03', '2005-10-05'),
(  3 , 5,           '2010-12-01', '2011-01-02'),
(  2 , 5,           '2006-10-04', '2006-11-05'),
(  4 , 4,           '2007-02-07', '2007-03-06'),
(  5 , 2,           '2018-01-06', '2018-02-07'),
(  6 , 4,           '2021-03-07', '2021-04-07'),
(  7 , 3,           '2007-11-20', '2007-12-21' );
SELECT setval('vacation_id_seq', 7);

INSERT INTO "car"
("id", "mark",    "model", "tonnage") VALUES
( 1,   'BMW',     'X5',    100),
( 2,   'Audi',    'X6',    70),
( 3,   'Bentley', 'X6-2',  80),
( 4,   'Acura',   'X2',    120);
SELECT setval('car_id_seq', 4);

INSERT INTO "car_copy"
("id", "state_num", "car_id") VALUES
( 1,   456790,       1),
( 2,   126782,       1),
( 3,   782351,       2),
( 4,   982376,       3),
( 5,   235782,       4),
( 6,   761365,       2);
SELECT setval('car_copy_id_seq', 6);

INSERT INTO "repair"
("id", "car_copy_id", "beg_date",   "plan_end_date", "end_date") VALUES
( 1,   1,             '2010-06-07', '2010-06-28',    '2010-06-29'),
( 2,   5,             '2022-03-01', '2022-03-19',    NULL),
( 3,   1,             '2021-06-04', '2021-08-20',    NULL),
( 4,   5,             '2020-06-04', '2020-08-20',    '2020-07-04'),
( 5,   3,             '2021-08-06', '2021-09-07',    '2021-09-06');
SELECT setval('repair_id_seq', 5);

INSERT INTO "break"
("id", "break_notes",              "repair_notes") VALUES
( 1,   'двигатель вышел из строя', 'ремонт двигателя'),
( 2,   'перегрев двигателя',       'замена двигателя'),
( 3,   'поломка коробки передач',  'замена коробки передач');
SELECT setval('break_id_seq', 3);

INSERT INTO "repair_vs_break"
("id", "repair_id", "break_id") VALUES
( 1,   1,           2),
( 2,   2,           3),
( 3,   2,           1),
( 4,   3,           1),
( 5,   4,           2),
( 6,   5,           3),
( 7,   4,           1),
( 8,   3,           2);
SELECT setval('repair_vs_break_id_seq', 8);

INSERT INTO "route"
("id", "beg_point", "end_point", "time") VALUES
( 1,   'Витебск',   'Полоцк',     '02:30'),
( 2,   'Орша',      'Гомель',     '04:00'),
( 3,   'Лепель',    'Витебск',    '03:20'),
( 4,   'Могилев',   'Брест',      '06:40'),
( 5,   'Могилев',   'Гомель',      '06:40'),
( 6,   'Минск',     'Бобруйск',   '05:10');
SELECT setval('route_id_seq', 6);

INSERT INTO "trip"
("id", "car_copy_id", "driver_id", "route_id", "beg_date",   "end_date",   "weight") VALUES
( 1,   2,             2,           3,          '2015-05-02', '2015-05-03', 20),
( 2,   1,             3,           1,          '2015-06-03', '2015-06-04', 30),
( 3,   6,             2,           4,          '2018-05-21', '2018-05-21', 50), 
( 4,   3,             4,           4,          '2019-02-16', '2019-02-16', 25),
( 5,   4,             5,           2,          '2003-06-08', '2003-06-09', 80),
( 6,   5,             3,           5,          '2022-03-18', NULL,         21),
( 7,   6,             4,           3,          '2022-03-18', NULL,         55),
( 8,   4,             2,           5,          '2001-10-21', '2001-10-21', 25),
( 9,   6,             2,           4,          '2007-05-21', '2007-05-21', 15);
SELECT setval('trip_id_seq', 9); 