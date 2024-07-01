CREATE DATABASE Kontra
ON PRIMARY (NAME=Kontra,
FILENAME='D:\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Kontra.mdf',
Size=3Mb,
Maxsize=30Mb,
FileGrouth=1Mb)
Log ON(Name=Kontra,
FILENAME='D:\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\Kontra.ldf',
Size=3Mb,
Maxsize=10Mb,
FileGrouth=1Mb)