CREATE TABLE OWNER
(Owner_no smallint IDENTITY(1,1)  NOT NULL PRIMARY KEY,
FName varchar(20) NOT NULL,
LName varchar(20) NOT NULL,
City varchar(20) NOT NULL,
Street varchar(20) NOT NULL,
House nchar(6) NOT NULL,
Flat smallint NULL,
Otel_no varchar(30) NULL)

CREATE TABLE BRANCH
(Branch_no smallint IDENTITY(1,1) NOT NULL PRIMARY KEY,
Postcode varchar(20) NULL,
City varchar(20) NOT NULL,
Street varchar(20) NOT NULL,
House nchar(10) NOT NULL,
Btel_no varchar(30) NOT NULL,
Fax_no varchar(30) NULL)

CREATE TABLE BUYER
(Buyer_no smallint NOT NULL PRIMARY KEY,
FName varchar(20) NOT NULL,
LName varchar(20) NOT NULL,
City varchar(20) NOT NULL,
Street varchar(20) NOT NULL,
House nchar(6) NOT NULL,
Flat smallint NULL,
Htel_no varchar(30) NULL,
Wtel_no varchar(30) NULL,
Prof_Rooms tinyint NOT NULL,
Max_Price money NOT NULL,
Branch_no smallint NOT NULL,
CONSTRAINT FK_BUYER_BRANCH FOREIGN KEY(Branch_no) REFERENCES BRANCH ON UPDATE CASCADE,
CHECK (Htel_no IS NOT NULL OR Wtel_no IS NOT NULL) 
)

CREATE TABLE STAFF
(Staff_no smallint NOT NULL PRIMARY KEY,
FName varchar(20) NOT NULL,
LName varchar(20) NOT NULL,
DOB smalldatetime NOT NULL,
Sex varchar(20) NOT NULL,
City varchar(20) NOT NULL,
Street varchar(20) NOT NULL,
House nchar(6) NOT NULL,
Flat smallint NOT NULL,
Stel_no varchar(30) NOT NULL,
Date_Joined smalldatetime NOT NULL,
Position varchar(50) NOT NULL,
Salary money NOT NULL,
Branch_no smallint NULL,
CONSTRAINT FK_STAFF_BRANCH FOREIGN KEY(Branch_no) REFERENCES BRANCH ON UPDATE CASCADE
)

CREATE TABLE PROPERTY
(Propery_no smallint NOT NULL PRIMARY KEY,
Date_registration smalldatetime NOT NULL,
Postcode varchar(20) NULL,
City varchar(20) NOT NULL,
Street varchar(20) NOT NULL,
House nchar(6) NOT NULL,
Flat smallint NOT NULL,
Floor_Type varchar(20) NOT NULL,
Floor smallint NOT NULL,
Rooms smallint NOT NULL,
The_area smallint NOT NULL,
Kitchen_area smallint NOT NULL,
Balcony smallint NOT NULL,
Ptel varchar(30) NULL,
Selling_Price money NOT NULL,
Branch_no smallint NOT NULL,
CONSTRAINT FK_PROPERTY_BRANCH FOREIGN KEY(Branch_no) REFERENCES BRANCH ON UPDATE CASCADE,
Staff_no smallint NULL,
CONSTRAINT FK_PROPERTY_STAFF FOREIGN KEY(Staff_no) REFERENCES STAFF ON UPDATE NO ACTION,
Owner_no smallint NOT NULL,
CONSTRAINT FK_PROPERTY_OWNER FOREIGN KEY(Owner_no) REFERENCES OWNER ON UPDATE CASCADE
)

CREATE TABLE CONTRACT
(Sale_no smallint NOT NULL PRIMARY KEY,
Notary_Office varchar(20) NOT NULL,
Date_Contract smalldatetime NOT NULL,
Service_Cost money NOT NULL,
Property_no smallint NOT NULL,
CONSTRAINT FK_CONTRACT_PROPERTY FOREIGN KEY(Property_no) REFERENCES PROPERTY ON UPDATE CASCADE,
Buyer_no smallint NOT NULL,
CONSTRAINT FK_CONTRACT_BUYER FOREIGN KEY(Buyer_no) REFERENCES BUYER ON UPDATE NO ACTION,
Owner_no smallint NOT NULL,
CONSTRAINT FK_CONTRACT_OWNER FOREIGN KEY(Owner_no) REFERENCES OWNER ON UPDATE NO ACTION
)

CREATE TABLE VIEWING
(Viewing_no smallint NOT NULL PRIMARY KEY,
Date_View smalldatetime NOT NULL,
Comments varchar(20) NULL,
Property_no smallint NOT NULL,
CONSTRAINT FK_VIEWING_PROPERTY FOREIGN KEY(Property_no) REFERENCES PROPERTY ON UPDATE CASCADE,
Buyer_no smallint NOT NULL,
CONSTRAINT FK_VIEWING_BUYER FOREIGN KEY(Buyer_no) REFERENCES BUYER ON UPDATE NO ACTION
)