CREATE DATABASE DreamHome
ON PRIMARY (NAME=DreamHome,
FILENAME='D:\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DreamHome.mdf',
Size=3Mb,
Maxsize=30Mb,
FileGrouth=1Mb)
Log ON(Name=DreamHome,
FILENAME='D:\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DreamHome.ldf',
Size=3Mb,
Maxsize=10Mb,
FileGrouth=1Mb)