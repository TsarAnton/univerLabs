INSERT INTO SPECIALITY (SName) VALUES
('POIT'),
('POKS'),
('PM'),
('PI')

INSERT INTO GROUP (Gname, Speciality_no) VALUES
('44', 1),
('34', 1),
('24', 1),
('22', 2),
('32', 2),
('35', 3),
('45', 3),
('38', 4)

INSERT INTO TEACHER (FName, SName, PName, Department, JobTitle) VALUES
("Ivan1", "Ivanov1", "Ivanovich1", "KPiSP1", "Docent1"),
("Ivan2", "Ivanov2", "Ivanovich2", "KPiSP2", "Docent2"),
("Ivan3", "Ivanov3", "Ivanovich3", "KPiSP3", "Docent3"),
("Ivan4", "Ivanov4", "Ivanovich4", "KPiSP4", "Docent4")

CREATE VIEW V1(Kafedra, Teacher_no, MCount) AS 
SELECT TEACHER.Department, TEACHER.Teacher_no, COUNT(*) FROM TEACHER INNER JOIN MARK ON(TEACHER.Teacher_no=Mark.Teacher_no) WHERE MARK.Mark < 5




INSERT INTO STUDENT (FName, SName, PName, Photography, Admission, Scholarship, Group_no) VALUES
("Semen1", "Semenov1", "Semenovich1", "Photo1", 1, 25.5, 1),
("Semen2", "Semenov2", "Semenovich2", "Photo2", 0, 25.6, 1),
("Semen3", "Semenov3", "Semenovich3", "Photo3", 1, 25.57, 2),
("Semen4", "Semenov4", "Semenovich4", "Photo4", 0, 25.8, 2),
("Semen5", "Semenov5", "Semenovich5", "Photo5", 1, 25.9, 2),
("Semen6", "Semenov6", "Semenovich6", "Photo6", 1, 27, 3),
("Semen7", "Semenov7", "Semenovich7", "Photo7", 1, 100, 4)

INSERT INTO DISCIPLINE (SName) VALUES
('BD'),
('PM'),
('OZI'),
('KSIS')

INSERT INTO SHEDULE (SDATE, Auditory, Discipline_no, Group_no, Teacher_no) VALUES
('20.04.2003', 101, 1, 1, 1)

INSERT INTO MARK (Mark, CType, EDate, Student_no, Group_no, Teacher_no) VALUES
(1, "exam1", '20.04.2003', 1, 1, 1)

INSERT INTO TEACHER_DISCIPLINE (Discipline_no, Teacher_no) VALUES
(1, 1)