use master
go
drop database DuAn
go
create database DuAn
go
Use DuAn1
go

create table TaiKhoan(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	MaTK varchar(10) not null ,
	TenDangNhap varchar(20),
	MatKhau varchar(20),
	VaiTro nvarchar(20)
)
select * from TaiKhoan

insert into TaiKhoan(MaTK, TenDangNhap, MatKhau, VaiTro) values ('TK001','NV001','1',N'Nhân viên')
insert into TaiKhoan(MaTK, TenDangNhap, MatKhau, VaiTro) values ('TK002','CCH001','1',N'Chủ cửa hàng')
create table NhanVien(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	MaNV varchar(30) ,
	IDTaiKhoan VARCHAR(36),
	HoTen nvarchar(40),
	GioiTinh nvarchar(10),
	NgaySinh date,
	DiaChi nvarchar(60),
	SDT varchar(15),
	Email varchar(30),
	TrangThai BIT,
	foreign key(IDTaiKhoan) references TaiKhoan(ID)
)
drop table NhanVien
Select * from NhanVien
Insert into NhanVien(MaNV, IDTaiKhoan ,HoTen ,GioiTinh ,NgaySinh ,DiaChi ,SDT ,Email ,TrangThai)
values('NV001','1D0A2A64-7DB6-4E08-A717-BEA82DFA6506',N'Lương Tuấn Đạt',N'Nam','2004-11-14',
N'Huyện Thanh Sơn tỉnh Phú Thọ','0974567728','Datltph32151@gmail.com',0)
Insert into NhanVien(MaNV, IDTaiKhoan ,HoTen ,GioiTinh ,NgaySinh ,DiaChi ,SDT ,Email ,TrangThai)
values('NV002','CD2BC10F-C3A7-47B8-B475-63A118A983A0',N'Lương Tuấn Đạt',N'Nam','2004-11-14',
N'Huyện Thanh Sơn tỉnh Phú Thọ','0974567728','Datltph32151@gmail.com',0)


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
select * from GiamGia
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
select * from KhachHang;
drop table KhachHang;
create table ChatLieu(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	TenCL nvarchar(20)
)
insert into ChatLieu values ('CL001',N'Da'),('CL002',N'Vải'),('CL003',N'Cotton'),('CL004',N'Lông')

select * from ChatLieu
create table PhanLoai(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	TenPL nvarchar(20)
)
insert into PhanLoai values ('PL001',N'Túi đeo chéo'),('PL002',N'Túi đưa thư'),('PL003',N'Túi Nilon'),('PL004',N'Cặp da')

create table MauSac(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	TenMS nvarchar(20)
)
insert into MauSac values ('MS001',N'Đỏ'),('MS002',N'Đen'),('MS003',N'Xám'),('MS004',N'Nâu')

create table ThuongHieu(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	TenTH nvarchar(20)
)
insert into ThuongHieu values ('TH001',N'Gucci'),('TH002',N'LuonVuiTuoi'),('TH003',N'Adidas'),('TH004',N'Đôn chề')

create table XuatXu(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	TenXX nvarchar(20)
)
insert into XuatXu values ('XX001',N'Việt Nam'),('XX002',N'Trung Quốc'),('XX003',N'Mỹ'),('MS004',N'Pháp')

create table SanPham(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	MaSP varchar(15),
	TenSP nvarchar(30),
	TrangThai bit
)
insert into SanPham(MaSP,TenSP,TrangThai) values ('SP001',N'Túi đeo 2022',0),('SP002',N'Cặp da cá xấu xí',0),
						   ('SP003',N'Túi nilon 2021',0),('SP004',N'Túi đeo 2023',0)

select * from SanPham		   
create table SanPhamChiTiet(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	IDSP varchar(36),
	IDCL varchar(36),
	IDPL varchar(36),
	IDMS varchar(36),
	IDTH varchar(36),
	IDXX varchar(36),
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
select * from SanPhamChiTiet
insert into SanPhamChiTiet values('SPCT001','366B0A2A-CDD6-4851-9A47-5BE5B211C803','CL002','PL001','MS001','TH001','XX003','GG001',
'30x13x50',0.1,3,1000,N'Túi VIP pro Tây Hồ','',0)
insert into SanPhamChiTiet values('SPCT002','366B0A2A-CDD6-4851-9A47-5BE5B211C803','CL001','PL004','MS003','TH001','XX001',NULL,
'35x23x48',1.9,6,3980000,N'Túi xấu xí xù xì','',0)
insert into SanPhamChiTiet values('SPCT003','36F70C1A-2091-4BAE-8281-B15FC8ABF614','CL001','PL002','MS001','TH002','XX002','',
'10x10x10',0.03,50,400,N'Túi đựng rau,thịt,....','',0)
insert into SanPhamChiTiet values('SPCT004','69DFD5D6-42E7-44F1-A7AC-EBB7AD0DA6B7','CL003','PL002','MS004','TH003','XX001','GG001'
'45x25x50',1.2,9,139000,N'Túi siêu soft cho các boy','',0)
insert into SanPhamChiTiet values('SPCT005','69DFD5D6-42E7-44F1-A7AC-EBB7AD0DA6B7','CL001','PL002','MS004','TH003','XX001',
'45x25x50',1.2,9,139000,N'Túi siêu soft cho các boy','',0)
insert into SanPhamChiTiet values('SPCT006','69DFD5D6-42E7-44F1-A7AC-EBB7AD0DA6B7','CL001','PL002','MS003','TH003','XX001',
'45x25x50',1.2,9,139000,N'Túi siêu soft cho các boy','',0)
select * from SanPhamChiTiet	

Create table HoaDon(
IDHD varchar(10) primary key DEFAULT NEWID(),
MaNV varchar(30),
MaKH varchar(30),
MaHD varchar(30),
NgayTao date,
TrangThai bit
)

Create table HoaDonChiTiet(
ID varchar(10) primary key DEFAULT NEWID(),
IDHD varchar(10),
IDSPCT varchar(36),
SoLuong int,
DonGia float,
ThanhTien float,

CONSTRAINT FK1 FOREIGN KEY(IDHD) REFERENCES HoaDon(IDHD),
CONSTRAINT FK2 FOREIGN KEY(IDSPCT) REFERENCES SanPhamChiTiet(ID),

)
 create table NguoiDung(
	MaND varchar(10) primary key Default newid(),
	IDTK VARCHAR(36),
	HoTen nvarchar(40),
	NgaySinh date,
	GioiTinh nvarchar(10),
	DiaChi nvarchar(60),
	SDT varchar(15),
	Email varchar(30),
	TrangThai bit,
	foreign key (IDTK) references TaiKhoan(ID)

	)
create table DoiHang(
	ID varchar(10) primary key Default newid(),
	IDHD VARCHAR(10),
	MaKH varchar(30),
	MaND varchar(10),
	ThoiGianDoi date,
	TrangThai bit,
	foreign key (IDHD) references HoaDon(IDHD),
	foreign key (MaKH) references KhachHang(MaKH),
	foreign key (MaND) references NguoiDung(MaND)
	)
	drop table DoiHang

create table DoiHangChiTiet(
	ID VARCHAR(10)  primary key Default newid(),
	IDDH VARCHAR(10),
	IDHDCT VARCHAR(10),
	IDSPCT VARCHAR(36),
	SoLuongDoi int,
	GiaDoi date,
	foreign key (IDDH) references DoiHang(ID),
	foreign key (IDHDCT) references HoaDonChiTiet(ID),
	foreign key (IDSPCT) references SanPhamChiTiet(ID)
	)
	Drop table DoiHangChiTiet
CREATE TABLE PhieuDoi(
	ID  VARCHAR(10)  primary key Default newid(),
	IDDH VARCHAR(10),
	TenPhieuDoi nvarchar(40),
	ThoiGianDoi date,
	SoTien float,
	GhiChu nvarchar(60)
	foreign key (IDDH) references DoiHang(ID),

	)
