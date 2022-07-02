﻿---tạo DB 
CREATE DATABASE QLBDIENTHOAI
---TẠO BẢNG 

----BẢNG QUẢN LÝ NHÂN SỰ 
IF OBJECT_ID('QLNVBH') IS NOT NULL
	DROP TABLE QLNVBH
GO
CREATE TABLE QLNVBH
(
	MANV VARCHAR(7) NOT NULL,
	SCMND NCHAR(12) NOT NULL,
	HOTENNV NVARCHAR(30) NOT NULL,
	NGAYSINH DATE NOT NULL,
	SDTNV NCHAR(11) NOT NULL,
	EMAILNV NVARCHAR(30) NOT NULL,
	CHUCVU NVARCHAR(2) NOT NULL,
	PASSWORD NCHAR(10) NOT NULL,
	CONSTRAINT PK_QLNVBH PRIMARY KEY(MANV)

)


----BẢNG QUẢN LÝ KHÁCH HÀNG
IF OBJECT_ID('KHACHHANG') IS NOT NULL
	DROP TABLE KHACHHANG
GO
CREATE TABLE KHACHHANG
(
	MAKH	INT IDENTITY(1,1) NOT NULL,
	HOVATEN	NVARCHAR(50) NOT NULL,
	DIACHI	NVARCHAR(50) NOT NULL,
	EMAIL VARCHAR(35) NOT NULL,		
	DIENTHOAI	VARCHAR(15) NOT NULL,
	MANV VARCHAR(7) NOT NULL,
	CONSTRAINT PK_KHACHHANG PRIMARY KEY(MAKH),
	CONSTRAINT FK_QLNVBH_KHACHHANG FOREIGN KEY(MANV) REFERENCES QLNVBH
)
----BẢNG SẢN PHẨM 
IF OBJECT_ID('SANPHAM') IS NOT NULL
	DROP TABLE SANPHAM
GO
CREATE TABLE SANPHAM
(
	MASP INT IDENTITY(1,1) NOT NULL,
	TENSP NVARCHAR(30) NOT NULL,
	SLTON INT NOT NULL,
	DONGIA MONEY NOT NULL,
	MOTA NVARCHAR(MAX) NOT NULL,
	MANV VARCHAR(7) NOT NULL,
	CONSTRAINT PK_SANPHAM PRIMARY KEY(MASP),
	CONSTRAINT FK_QLNVBH_SANPHAM FOREIGN KEY(MANV) REFERENCES QLNVBH
)
IF OBJECT_ID('HOADON') IS NOT NULL
	DROP TABLE HOADON
GO
CREATE TABLE HOADON
(
	MAHD NVARCHAR(10) NOT NULL,
	NGAYMUA DATE NOT NULL,
	MAKH INT IDENTITY(1,1) NOT NULL FOREIGN KEY REFERENCES KHACHHANG,
	TRANGTHAI NVARCHAR(50) NOT NULL,
	CONSTRAINT PK_HOADON PRIMARY KEY(MAHD)
)

----BẢNG HÓA ĐƠN CHI TIẾT 
IF OBJECT_ID('CTHOADON') IS NOT NULL
	DROP TABLE CTHOADON
GO
CREATE TABLE CTHOADON
(
	MAHD NVARCHAR(10) NOT NULL,
	MASP INT IDENTITY(1,1) NOT NULL,
	SLBAN INT NOT NULL,
	CONSTRAINT FK_HOADON_CTHOADON FOREIGN KEY(MAHD) REFERENCES HOADON,
	CONSTRAINT FK_SANPHAM_CTHOADON FOREIGN KEY(MASP) REFERENCES SANPHAM,
	CONSTRAINT PK_CTHOADON PRIMARY KEY(MAHD, MASP)
)


----CHÈN dữ liệu 
----BẢNG QUẢN LÝ NHÂN VIÊN BÁN HÀNG

INSERT INTO QLNVBH(MANV,SCMND,HOTENNV,NGAYSINH,SDTNV,EMAILNV,CHUCVU,PASSWORD) VALUES('NV001','026207001730','Cao John Cetter','02/01/2002','0354748218','john@gmail.com','ql','nv1234')
INSERT INTO QLNVBH(MANV,SCMND,HOTENNV,NGAYSINH,SDTNV,EMAILNV,CHUCVU,PASSWORD) VALUES('NV002','026207001740','Julia Tempest','02/10/2002','0324748218','julia@gmail.com','nv','nv1234')
INSERT INTO QLNVBH(MANV,SCMND,HOTENNV,NGAYSINH,SDTNV,EMAILNV,CHUCVU,PASSWORD) VALUES('NV003','026207001750','Eris Grayrat','02/01/2003','0354348218','eris@gmail.com','nv','nv1234')
INSERT INTO QLNVBH(MANV,SCMND,HOTENNV,NGAYSINH,SDTNV,EMAILNV,CHUCVU,PASSWORD) VALUES('NV004','026207001760','Hello World','02/05/2002','0354728218','hello@gmail.com','nv','nv1234')

----BẢNG KHÁCH HÀNG 
SET IDENTITY_INSERT KHACHHANG ON
GO
INSERT INTO KHACHHANG(MAKH,HOVATEN,DIACHI,EMAIL,DIENTHOAI,MANV) VALUES(1,N'CAO GIANG HÀO',N'HÀ NỘI','haocgph16856@fpt.edu.vn','0354749217','NV002'),
							(2,N'NGUYỄN VĂN TÙNG',N'HÀ NAM ','tungnvph16856@fpt.edu.vn','0354749217','NV002'),
							(3,N'ĐỖ TUẤN ANH',N'ĐÀ NẴNG','anhdtph16856@fpt.edu.vn','0354749217','NV002'),
							(4,N'ĐÀO NHẬT ÁNH ',N'HÀ NỘI ','anhdnph16856@fpt.edu.vn','0354749217','NV004'),
							(5,N'NGUYỄN VĂN TÙNG',N'HÀ NAM ','tungnvph16856@fpt.edu.vn','0354749217','NV002'),
							(6,N'ĐỖ TUẤN ANH',N'ĐÀ NẴNG','anhdtph16856@fpt.edu.vn','0354749217','NV002'),
							(7,N'ĐÀO NHẬT ÁNH ',N'HÀ NỘI ','anhdnph16856@fpt.edu.vn','0354749217','NV004'),
							(8,N'ĐỖ TUẤN ANH',N'ĐÀ NẴNG','anhdtph16856@fpt.edu.vn','0354749217','NV002'),
							(9,N'ĐÀO NHẬT ÁNH ',N'HÀ NỘI ','anhdnph16856@fpt.edu.vn','0354749217','NV004'),
							(10,N'ĐỖ TUẤN ANH',N'ĐÀ NẴNG','anhdtph16856@fpt.edu.vn','0354749217','NV002'),
							(11,N'ĐÀO NHẬT ÁNH ',N'HÀ NỘI ','anhdnph16856@fpt.edu.vn','0354749217','NV004'),
							(12,N'ĐỖ TUẤN ANH',N'ĐÀ NẴNG','anhdtph16856@fpt.edu.vn','0354749217','NV002'),
							(13,N'ĐỖ TUẤN ANH',N'ĐÀ NẴNG','anhdtph16856@fpt.edu.vn','0354749217','NV002'),
							(14,N'ĐÀO NHẬT ÁNH ',N'HÀ NỘI ','anhdnph16856@fpt.edu.vn','0354749217','NV004'),
							(15,N'ĐÀO NHẬT ÁNH ',N'HÀ NỘI ','anhdnph16856@fpt.edu.vn','0354749217','NV004')
							
SET IDENTITY_INSERT KHACHHANG OFF
GO
/*---bảng sản phẩm ---*/

SET IDENTITY_INSERT SANPHAM ON
GO
INSERT INTO SANPHAM(MASP,TENSP,SLTON,DONGIA,MOTA,MANV) VALUES(1,N'IPHONE7',12345,4000000,N'HÀNG MỚI','NV002'),
						  (2,N'IPHONE8',123,4500000,N'HÀNG MỚI','NV003'),
						  (3,N'VERTU',13,544000000,N'HÀNG MỚI','NV002'),
						  (4,N'IPHONE11',1245,20000000,N'HÀNG MỚI','NV004'),
						  (5,N'IPHONE12',2345,24000000,N'HÀNG MỚI','NV002'),
						  (6,N'IPHONE8',123,4500000,N'HÀNG MỚI','NV003'),
						  (7,N'IPHONE8',123,4500000,N'HÀNG MỚI','NV003'),
						  (8,N'IPHONE8',123,4500000,N'HÀNG MỚI','NV003'),
						  (9,N'IPHONE8',123,4500000,N'HÀNG MỚI','NV003'),
						  (10,N'IPHONE8',123,4500000,N'HÀNG MỚI','NV003')

SET IDENTITY_INSERT SANPHAM OFF
GO
/*--- HÓA ĐƠN---*/
SET IDENTITY_INSERT HOADON ON
GO
INSERT INTO HOADON(MAHD,NGAYMUA,MAKH,TRANGTHAI) VALUES('HD01','01/01/2021',2,N'ĐÃ THANH TOÁN'),
						 ('HD02','02/01/2021',1,N'ĐÃ THANH TOÁN'),
						 ('HD03','03/06/2021',2,N'ĐÃ THANH TOÁN'),
						 ('HD04','04/01/2021',8,N'ĐÃ THANH TOÁN'),
						 ('HD05','05/08/2021',5,N'CHƯA THANH TOÁN'),
						 ('HD06','05/08/2021',5,N'CHƯA THANH TOÁN'),
						 ('HD07','05/08/2021',5,N'CHƯA THANH TOÁN'),
						 ('HD08','05/08/2021',5,N'CHƯA THANH TOÁN'),
						 ('HD09','05/08/2021',5,N'CHƯA THANH TOÁN')
SET IDENTITY_INSERT HOADON OFF
GO
/*----CTHOADON---*/
SET IDENTITY_INSERT CTHOADON ON
GO
INSERT INTO CTHOADON(MAHD,MASP,SLBAN) VALUES('HD01',3,3),
						   ('HD02',2,5),
						   ('HD05',1,15),
						   ('HD03',2,5),
						   ('HD04',7,5)
SET IDENTITY_INSERT CTHOADON OFF
GO


/*----TRUY VẤN ---*/

SELECT *FROM CTHOADON
SELECT *FROM SANPHAM
SELECT *FROM HOADON
SELECT *FROM KHACHHANG
SELECT *FROM QLNVBH

/* delete */
DELETE FROM QLNVBH
DELETE FROM KHACHHANG
DELETE FROM SANPHAM
DELETE FROM HOADON
DELETE FROM CTHOADON