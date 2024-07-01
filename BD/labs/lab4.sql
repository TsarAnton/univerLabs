--задание 1
CREATE TRIGGER TRIGGER1 ON CONTRACT
FOR INSERT
AS
DECLARE @PROPERTY_NO smallint;
SELECT @PROPERTY_NO = Property_no FROM INSERTED;
DECLARE @STAFF_NO smallint;
SELECT @STAFF_NO = Staff_no FROM PROPERTY WHERE Property_no = @PROPERTY_NO;
UPDATE CONTRACT SET Property_no = NULL WHERE Property_no = @PROPERTY_NO;
DELETE FROM VIEWING WHERE Property_no = @PROPERTY_NO;
DELETE FROM PROPERTY WHERE Property_no = @PROPERTY_NO;
UPDATE STAFF SET Salary = Salary * 1.05 WHERE Staff_no = @STAFF_NO;
PRINT 'Зарплата повышена. Сотрудник:: ' + CONVERT(CHAR, @STAFF_NO)

INSERT INTO CONTRACT (Sale_no, Notary_Office, Date_Contract, Service_Cost, Property_no, Buyer_no, Owner_no) VALUES
(12, 'office1', '05.03.2003', 100, 15, 1, 2);
DROP TRIGGER TRIGGER1

CREATE TRIGGER TRIGGER1 ON Оценки
FOR INSERT
AS
--Получаем поставленную оценку
DECLARE @Оценка smallint;
SELECT @Оценка = Оценка FROM INSERTED;
--Если она 4
IF @Оценка < 4
BEGIN
    --Получаем все данные о преподавателе
    DECLARE @Код_преподавателя smallint;
    SELECT @Код_преподавателя = Код_преподавателя FROM INSERTED;
    DECLARE @[Фамилия преподавателя] varchar(50);
    SELECT @[Фамилия преподавателя] = [Фамилия преподавателя] FROM Преподаватели WHERE Код_преподаваетля = @Код_преподавателя; 
    DECLARE @[Имя преподавателя] varchar(50);
    SELECT @[Имя преподавателя] = [Имя преподавателя] FROM Преподаватели WHERE Код_преподаваетля = @Код_преподавателя;
    DECLARE @[Отчество преподавателя] varchar(50);
    SELECT @[Отчество преподавателя] = [Отчество преподавателя] FROM Преподаватели WHERE Код_преподаваетля = @Код_преподавателя;
    DECLARE @Кафедра varchar(50);
    SELECT @Кафедра = Кафедра FROM Преподаватели WHERE Код_преподаваетля = @Код_преподавателя;
    DECLARE @Должность = Должность FROM Преподаватели WHERE Код_преподаваетля = @Код_преподавателя;
    --Заносим данные в таблицу Пересдачи
    INSERT INTO Пересдачи (Код_преподавателя, [Фамилия преподавателя], [Имя преподавателя], [Отчество преподавателя], Кафедра, Должность) VALUES
    (@Код_преподавателя, @[Фамилия преподавателя], @[Имя преподавателя], @[Отчество преподавателя], @Кафедра, @Должность);
END

--задание 2
CREATE TRIGGER TRIGGER2 ON PROPERTY
FOR INSERT
AS
DECLARE @STAFF_NO smallint;
SELECT @STAFF_NO = Staff_no FROM INSERTED;
DECLARE @STAFF_COUNT smallint;
SELECT @STAFF_COUNT = COUNT(*) FROM PROPERTY WHERE Staff_no = @STAFF_NO;
IF @STAFF_COUNT >= 3
BEGIN
ROLLBACK TRAN
RAISERROR('Количество закрепленных сотрудников больше 3',16,10);
END

INSERT INTO PROPERTY(Property_no, Date_registration, Postcode, City, Street, House, Flat, Floor_Type, Floor, Rooms, The_area, Kitchen_area, Balcony, Ptel, Selling_Price, Branch_no, Staff_no, Owner_no) VALUES
(17, '01.01.2001', '11111', 'city1', 'street1', '1', 1, 'type1', 1, 1, 1, 1, 1, '+375111111111', 100, 1, 1, 1);
DROP TRIGGER TRIGGER2

--задание 3
CREATE TRIGGER TRIGGER3 ON VIEWING 
FOR INSERT
AS
DECLARE @PROPERTY_NO smallint;
SELECT @PROPERTY_NO = Property_no FROM INSERTED;
DECLARE @SEE_COUNT smallint;
SELECT @SEE_COUNT = COUNT(*) FROM VIEWING WHERE Property_no = @PROPERTY_NO;
DECLARE @COMMENT varchar(20);
SELECT @COMMENT = Comments FROM INSERTED;
DECLARE @STAFF_NO smallint;
SELECT @STAFF_NO = Staff_no FROM PROPERTY WHERE Property_no = @PROPERTY_NO;
DECLARE @SALARY money;
SELECT @SALARY = Salary FROM STAFF WHERE Staff_no = @STAFF_NO;
DECLARE @BRANCH_NO smallint;
SELECT @BRANCH_NO = Branch_no FROM STAFF WHERE Staff_no = @STAFF_NO;
IF @SEE_COUNT >= 2 AND @COMMENT = 'не согласен' AND @SALARY > (SELECT AVG(Salary) FROM STAFF WHERE Branch_no = @BRANCH_NO)
BEGIN
UPDATE STAFF SET Salary = Salary * 0.95 WHERE Staff_no = @STAFF_NO;
PRINT 'Зарплата понижена';
END

INSERT INTO VIEWING (Viewing_no, Date_View, Comments, Property_no, Buyer_no) VALUES
(17, '01.01.2001', 'не согласен', 14, 1);
DROP TRIGGER TRIGGER3

--задание 4
CREATE TRIGGER TRIGGER4 ON CONTRACT
FOR INSERT
AS
DECLARE @OWNER_NO smallint;
SELECT @OWNER_NO = Owner_no FROM INSERTED;
IF EXISTS (SELECT Sale_no FROM CONTRACT WHERE Owner_no = @OWNER_NO)
BEGIN
DECLARE @LAST_DATE smalldatetime;
SELECT @LAST_DATE = Date_Contract FROM CONTRACT WHERE Owner_no = @OWNER_NO AND Date_Contract = (SELECT MAX(Date_Contract) FROM CONTRACT WHERE Owner_no = @OWNER_NO);
PRINT 'Время последней продажи: ' + CONVERT(CHAR, @LAST_DATE)
END

INSERT INTO CONTRACT (Sale_no, Notary_Office, Date_Contract, Service_Cost, Property_no, Buyer_no, Owner_no) VALUES
(13, 'office1', '05.03.2003', 100, 1, 1, 2);
DROP TRIGGER TRIGGER4

--задание 5
CREATE TRIGGER TRIGGER5 ON CONTRACT
FOR INSERT
AS
DECLARE @OWNER_NO smallint;
SELECT @OWNER_NO = Owner_no FROM INSERTED;
DECLARE @PROPERTY_NO smallint;
SELECT @PROPERTY_NO = Property_no FROM INSERTED;
IF NOT EXISTS (SELECT Property_no FROM PROPERTY WHERE Owner_no = @OWNER_NO AND Property_no != @PROPERTY_NO)
BEGIN
UPDATE PROPERTY SET Owner_no = NULL WHERE Property_no = @PROPERTY_NO;
UPDATE CONTRACT SET Owner_no = NULL WHERE Owner_no = @OWNER_NO;
DELETE FROM OWNER WHERE Owner_no = @OWNER_NO;
END

TO CONTRACT (Sale_no, Notary_Office, Date_Contract, Service_Cost, Property_no, Buyer_no, Owner_no) VALUES
(14, 'office1', '05.03.2003', 100, 16, 1, 10);
DROP TRIGGER TRIGGER5