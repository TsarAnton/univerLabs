--задание 1
CREATE VIEW VIEW1 AS 
SELECT B.Branch_no, AVG(S.Salary) AS Average_salary
FROM BRANCH B INNER JOIN STAFF S ON B.Branch_no = S.Branch_no
GROUP BY B.Branch_no;

SELECT * FROM VIEW1;
SELECT Branch_no FROM VIEW1 WHERE Average_salary = (SELECT MAX(Average_salary) FROM VIEW1);
SELECT Branch_no FROM VIEW1 WHERE Average_salary = (SELECT MIN(Average_salary) FROM VIEW1);

CREATE VIEW VIEW2 AS
SELECT Кафедра, Код_преподавателя, 
Количество_оценок = (SELECT COUNT(*) FROM Оценки WHERE Оценки.Код_преподавателя = Преподаватели.Код_преподавателя AND Оценка < 5) 
FROM Преподаватели;

SELECT Кафедра, Код_преподавателя, Фамилия_преподавателя FROM Преподаватели WHERE GROUP BY Кафедра;
SELECT Код_преподавателя FROM VIEW2 WHERE Количество_оценок = 0;
SELECT Код_преподавателя FROM VIEW2 WHERE Количество_оценок = (SELECT COUNT(*) FROM Оценки WHERE Оценки.Код_преподавателя = Преподаватели.Код_преподавателя);

--задание 2
CREATE VIEW VIEW2 AS
SELECT S.Staff_no, P.Branch_no COUNT(*) AS Prop_count
FROM STAFF S INNER JOIN PROPERTY P ON S.Staff_no = P.Staff_no
GROUP BY S.Staff_no;

SELECT * FROM VIEW2;
SELECT LName FROM STAFF WHERE Staff_no IN (SELECT Staff_no FROM VIEW2 WHERE Prop_count = 3);

--задание 3
CREATE VIEW VIEW3 AS
SELECT Property_no, The_area, Kitchen_area FROM PROPERTY;

SELECT * FROM VIEW3;
SELECT City, Street, House FROM PROPERTY WHERE Property_no IN (SELECT Property_no FROM VIEW3 WHERE Kitchen_area > 12);

--задание 4
CREATE VIEW VIEW4 AS
SELECT * FROM PROPERTY WHERE Property_no IN
(SELECT Property_no FROM VIEWING GROUP BY Property_no HAVING COUNT(*) > 2)
AND Property_no IN 
(SELECT Property_no FROM VIEWING WHERE Comments = 'требует ремонт');

SELECT * FROM VIEW4;
SELECT LNAME, Otel_no FROM OWNER WHERE Owner_no IN (SELECT Owner_no FROM VIEW4);

DROP VIEW VIEW1;
DROP VIEW VIEW2;
DROP VIEW VIEW3;
DROP VIEW VIEW4;