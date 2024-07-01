--задание 1
CREATE FUNCTION FUNC1
(@STAFF_NO smallint)
RETURNS VARCHAR(50)
AS
BEGIN
RETURN (SELECT LName FROM STAFF WHERE Staff_no = @STAFF_NO) + ' ' +
+ SUBSTRING((SELECT FName FROM STAFF WHERE Staff_no = @STAFF_NO),1,1) + '.';
END

SELECT Staff_no, SName = (SELECT dbo.FUNC1(Staff_no)), Stel_no FROM STAFF;

CREATE FUNCTION MAX_SCORE
(@Код_группы smallint)
RETURNS Real
AS
BEGIN
DECLARE @RESULT Real;
SELECT @RESULT = SELECT MAX(Avg_Score) FROM 
(SELECT Avg_Score = AVG(Оценка) FROM Оценки WHERE Код_студента IN (SELECT Код_студента FROM Студенты WHERE Код_группы = @Код группы) GROUP ) A;
RETURN @RESULT;
END

SELECT Код_студента, Фамилия_студента, Код_группы, Avg_Score = (SELECT AVG(Оценка) FROM Оценки WHERE Оценки.Код_студента = Студенты.Код_студента) WHERE Avg_Score = dbo.MAX_SCORE(Код_группы);
SELECT Код_группы, Max_Score = dbo.MAX_SCORE(Код_группы) FROM Группы ORDER BY Max_Score DESC;


--задание 2
CREATE FUNCTION FUNC2
(@ROOMS smallint,
@CITY varchar(20))
RETURNS varchar(60)
AS
BEGIN
IF EXISTS (SELECT Property_no FROM PROPERTY WHERE Rooms = @ROOMS AND City = @CITY AND Selling_Price = (SELECT MIN(Selling_Price) FROM PROPERTY WHERE Rooms = @ROOMS AND City = @CITY))
BEGIN
RETURN 'Город: ' + (SELECT City FROM PROPERTY WHERE Rooms = @ROOMS AND City = @CITY AND Selling_Price = (SELECT MIN(Selling_Price) FROM PROPERTY WHERE Rooms = @ROOMS AND City = @CITY)) + ' Улица:' +
(SELECT Street FROM PROPERTY WHERE Rooms = @ROOMS AND City = @CITY AND Selling_Price = (SELECT MIN(Selling_Price) FROM PROPERTY WHERE Rooms = @ROOMS AND City = @CITY)) + ' Дом:' +
(SELECT House FROM PROPERTY WHERE Rooms = @ROOMS AND City = @CITY AND Selling_Price = (SELECT MIN(Selling_Price) FROM PROPERTY WHERE Rooms = @ROOMS AND City = @CITY));
END
RETURN 'Подходящей недвижимости не найдено';
END

SELECT dbo.FUNC2(1,'Витебск');

--задание 3
CREATE FUNCTION FUNC3
(@BRANCH_NO smallint,
@START_DATE smalldatetime,
@END_DATE smalldatetime)
RETURNS smallint
AS
BEGIN
DECLARE @NUM smallint;
SELECT @NUM = COUNT(*) FROM CONTRACT WHERE Property_no IN (SELECT Property_no FROM PROPERTY WHERE Branch_no = @BRANCH_NO)
AND Date_Contract > @START_DATE AND Date_Contract < @END_DATE;
RETURN @NUM;
END

SELECT dbo.FUNC3(1,'08.04.2023','12.04.2023');
SELECT Branch_no, Sell_num = (SELECT dbo.FUNC3(Branch_no,'08.04.2023','12.04.2023')) FROM BRANCH;
SELECT Branch_no, Sell_num = (SELECT dbo.FUNC3(Branch_no,'08.04.2023','12.04.2023')) FROM BRANCH ORDER BY Sell_num DESC;

--задание 4
CREATE FUNCTION FUNC4()
RETURNS TABLE
AS
RETURN (SELECT Branch_no, Avg_DOB = (SELECT AVG(DATEDIFF(yy,DOB,GETDATE())) FROM STAFF WHERE STAFF.Branch_no = BRANCH.Branch_no) FROM BRANCH);

SELECT * FROM dbo.FUNC4();
SELECT * FROM dbo.FUNC4() WHERE Avg_DOB > 50;
SELECT COUNT(*) FROM dbo.FUNC4() WHERE Avg_DOB > 50;
SELECT Branch_no FROM dbo.FUNC4() WHERE Avg_DOB = (SELECT MIN(Avg_DOB) FROM dbo.FUNC4());