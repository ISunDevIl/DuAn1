use master
go
drop database DuAn
go
create database DuAn2
go
Use DuAn2
go






create table GiamGia(
	MaGG varchar(15) primary key,
	Ten nvarchar(40),
	GiaTri float,
	NgayBatDau date,
	NgayHetHan date,
	GhiChu nvarchar(60),
	TrangThai BIT,
)
insert into GiamGia values
			('GG001', 'Black Feiday', 30, '2023-11-25', '2023-11-27', 'Giảm 50%', 0)
Create table KhachHang(
	MaKH varchar(30) primary key,
	HoTen nvarchar(40),
	NgaySinh date,
	GioiTinh nvarchar(10),
	DiaChi nvarchar(60),
	SDT varchar(15),
	Email varchar(30),
	TrangThai bit 
)
insert into KhachHang values 
			('KH001', 'Nguyễn Hồng Nhung', '2004-08-01',N'Nữ','Vĩnh Phúc', '0862443023', 'nhung@gmail',1),
			('KH002', 'Bùi Hoàng Long', '2004-08-01',N'Nam','Nam Định', '0862443023', 'long@gmail',1)

create table ChatLieu(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenCL nvarchar(20)
)
insert into ChatLieu values ('CL001',N'Da'),('CL002',N'Vải'),('CL003',N'Cotton'),('CL004',N'Lông')


create table PhanLoai(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenPL nvarchar(20)
)
insert into PhanLoai values ('PL001',N'Túi đeo chéo'),('PL002',N'Túi đưa thư'),('PL003',N'Túi Nilon'),('PL004',N'Cặp da')

create table MauSac(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenMS nvarchar(20)
)
insert into MauSac values ('MS001',N'Đỏ'),('MS002',N'Đen'),('MS003',N'Xám'),('MS004',N'Nâu')

create table ThuongHieu(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenTH nvarchar(20)
)
insert into ThuongHieu values ('TH001',N'Gucci'),('TH002',N'LuonVuiTuoi'),('TH003',N'Adidas'),('TH004',N'Đôn chề')

create table XuatXu(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenXX nvarchar(20)
)
insert into XuatXu values ('XX001',N'Việt Nam'),('XX002',N'Trung Quốc'),('XX003',N'Mỹ'),('MS004',N'Pháp')

create table SanPham(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	MaSP varchar(15),
	TenSP nvarchar(30),
	TrangThai bit
)
insert into SanPham(ID,MaSP,TenSP,TrangThai) values ('366B0A2A-CDD6-4851-9A47-5BE5B211C803','SP001',N'Túi đeo 2022',0),('36F70C1A-2091-4BAE-8281-B15FC8ABF614','SP002',N'Cặp da cá xấu xí',0),
						   ('69DFD5D6-42E7-44F1-A7AC-EBB7AD0DA6B7','SP003',N'Túi nilon 2021',0),('AF9FD5D6-42E7-44F1-A7AC-EBB7AD0DA6B7','SP004',N'Túi đeo 2023',0)

	   
create table SanPhamChiTiet(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	IDSP varchar(50),
	IDCL varchar(50),
	IDPL varchar(50),
	IDMS varchar(50),
	IDTH varchar(50),
	IDXX varchar(50),
	MaGG varchar(15),
	KichThuoc varchar(15),
	KhoiLuong float,
	SoLuong int,
	Gia float,
	MoTa nvarchar(60),
	Anh varchar(300),
	TrangThai bit,
	foreign key (MaGG) references GiamGia(MaGG),
	foreign key (IDSP) references SanPham(ID),
	foreign key (IDCL) references ChatLieu(ID),
	foreign key (IDPL) references PhanLoai(ID),
	foreign key (IDMS) references MauSac(ID),
	foreign key (IDTH) references ThuongHieu(ID),
	foreign key (IDXX) references XuatXu(ID)
)

insert into SanPhamChiTiet values('SPCT001','366B0A2A-CDD6-4851-9A47-5BE5B211C803','CL002','PL001','MS001','TH001','XX003','GG001',
'30x13x50',0.1,3,1000,N'Túi VIP pro Tây Hồ','',0)
insert into SanPhamChiTiet values('SPCT002','366B0A2A-CDD6-4851-9A47-5BE5B211C803','CL001','PL004','MS003','TH001','XX001',NULL,
'35x23x48',1.9,6,3980000,N'Túi xấu xí xù xì','',0)
insert into SanPhamChiTiet values('SPCT003','36F70C1A-2091-4BAE-8281-B15FC8ABF614','CL001','PL002','MS001','TH002','XX002',NULL,
'10x10x10',0.03,50,400,N'Túi đựng rau,thịt,....','',0)
insert into SanPhamChiTiet values('SPCT004','69DFD5D6-42E7-44F1-A7AC-EBB7AD0DA6B7','CL003','PL002','MS004','TH003','XX001','GG001',
'45x25x50',1.2,9,139000,N'Túi siêu soft cho các boy','',0)

	
select * from SanPhamChiTiet

Create table HoaDon(
IDHD varchar(50) primary key DEFAULT NEWID(),
MaND varchar(50),
MaKH varchar(30),
MaHD varchar(30),
NgayTao date,
TrangThai bit,
TongTien float,
foreign key (MaND) references NguoiDung(ID),
foreign key (MaKH) references KhachHang(MaKH),
)

Create table HoaDonChiTiet(
ID varchar(50) primary key DEFAULT NEWID(),
IDHD varchar(50),
IDSPCT varchar(50),
SoLuong int,
DonGia float,
ThanhTien float,

CONSTRAINT FK1 FOREIGN KEY(IDHD) REFERENCES HoaDon(IDHD),
CONSTRAINT FK2 FOREIGN KEY(IDSPCT) REFERENCES SanPhamChiTiet(ID),

)
 create table NguoiDung(
	ID varchar(50) primary key Default newid(),
	TenDangNhap varchar(20),
	MatKhau varchar(20),
	HoTen nvarchar(40),
	GioiTinh nvarchar(10),
	NgaySinh date,
	SDT varchar(15),
	Email varchar(30),
	VaiTro bit,
	DiaChi nvarchar(60),
	TrangThai bit
)
use DuAn2
	select * from SanPhamChiTiet
	update SanPhamChiTiet set TrangThai = 1 where ID = 'SPCT001'
	select * from SanPham
	select * from SanPham where (MaSP like '%SP%' and TrangThai = 0)
	select * from NhanVien
	select * from TaiKhoan
	
Insert into NhanVien(MaNV, IDTaiKhoan ,HoTen ,GioiTinh ,NgaySinh ,DiaChi ,SDT ,Email ,TrangThai)
values('NV001','1D0A2A64-7DB6-4E08-A717-BEA82DFA6506',N'Lương Tuấn Đạt',N'Nam','2004-11-14',
N'Huyện Thanh Sơn tỉnh Phú Thọ','0974567728','Datltph32151@gmail.com',0)
Insert into NhanVien(MaNV, IDTaiKhoan ,HoTen ,GioiTinh ,NgaySinh ,DiaChi ,SDT ,Email ,TrangThai)
values('NV002','CD2BC10F-C3A7-47B8-B475-63A118A983A0',N'Lương Tuấn Đạt',N'Nam','2004-11-14',
N'Huyện Thanh Sơn tỉnh Phú Thọ','0974567728','Datltph32151@gmail.com',0)
	select * from NguoiDung
	insert into NguoiDung(TenDangNhap,MatKhau,HoTen,GioiTinh,NgaySinh,SDT,Email,VaiTro,DiaChi,TrangThai)
	values('ND01','1',N'Lương Tuấn Đạt',N'Nam','2004/11/14','0974567728','dat@gmail.com',1,N'Việt Nam',0)
	select * from HoaDon
	select * from HoaDonChiTiet
	--Doanh thu ngày hiện tại
SELECT SUM(SoLuong * DonGia) AS DoanhThuHomNay
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE())
	AND DAY(HD.NgayTao) = DAY(GETDATE()) AND HD.TrangThai = 1

--Doanh thu tháng hiện tại
SELECT SUM(SoLuong * DonGia) AS DoanhThuThangNay
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) AND HD.TrangThai = 1

--Doanh thu năm hiện tại
SELECT SUM(SoLuong * DonGia) AS DoanhThuNamNay
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND HD.TrangThai = 1

-- Số lượng tổng hóa đơn ngày hiện tại
SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
    AND DAY(HD.NgayTao) = DAY(GETDATE())

-- Số lượng tổng hóa đơn ngày hiện tại thành công
SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
    AND DAY(HD.NgayTao) = DAY(GETDATE()) AND HD.TrangThai = 1

-- Số lượng tổng hóa đơn ngày hiện tại bị hủy
SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
    AND DAY(HD.NgayTao) = DAY(GETDATE()) AND HD.TrangThai = 2

-- Số lượng tổng hóa đơn tháng hiện tại thành công
SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
     AND HD.TrangThai = 1

-- Số lượng tổng hóa đơn tháng hiện tại bị hủy
SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND MONTH(HD.NgayTao) = MONTH(GETDATE()) 
     AND HD.TrangThai = 2

-- Số lượng tổng hóa đơn năm hiện tại thành công
SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND HD.TrangThai = 1

-- Số lượng tổng hóa đơn năm hiện tại bị hủy
SELECT  COUNT(DISTINCT HD.IDHD) AS SoLuongDonHang
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(HD.NgayTao) = YEAR(GETDATE()) AND HD.TrangThai = 2

-- Lấy năm
SELECT DISTINCT YEAR(NgayTao)
FROM HoaDon
ORDER BY YEAR(NgayTao) DESC

-- Số lượng sản phẩm bán được trong tháng
SELECT SUM(SoLuong) AS TongSoLuong
FROM HoaDonChiTiet HDCT JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE  YEAR(HD.NgayTao) = 2023 AND MONTH(HD.NgayTao) = 12
      AND HD.TrangThai = 1

--Doanh thu tháng
SELECT SUM(SoLuong * DonGia) AS DoanhThuThang
FROM HoaDonChiTiet HDCT JOIN HOADON HD ON HDCT.IDHD = HD.IDHD 
WHERE YEAR(hd.NGAYTAO) = ? AND MONTH(hd.NGAYTAO) = ?
     AND hd.TrangThai = 1

-- Tổng giá giảm
SELECT SUM(GG.GiaTri * SPCT.Gia / 100) TongSoTienGiam
FROM GiamGia GG JOIN SanPhamChiTiet SPCT ON GG.MaGG = SPCT.MaGG
				JOIN HoaDonChiTiet HDCT ON SPCT.ID = HDCT.IDSPCT
				JOIN HoaDon HD ON HDCT.IDHD = HD.IDHD
WHERE YEAR(hd.NGAYTAO) = ? AND MONTH(hd.NGAYTAO) = ?
     AND hd.TrangThai = 1


	 select * from SanPhamChiTiet
	 select * from GiamGia
	 delete GiamGia
	 select * from SanPham where MaSP like '%%' and TrangThai = 0 or TenSP like '%%' and TrangThai = 0