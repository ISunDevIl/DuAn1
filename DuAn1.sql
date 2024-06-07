use master
go
drop database DuAn1
go
create database DuAn1
go
Use DuAn1
go

create table TaiKhoan(
	MaTK varchar(10) not null primary key,
	TenDangNhap varchar(20),
	MatKhau varchar(20),
	VaiTro nvarchar(20)
)
select * from TaiKhoan
insert into TaiKhoan values ('TK001','NV001','1',N'Nhân viên')
insert into TaiKhoan values ('TK002','CCH001','1',N'Chủ cửa hàng')
create table NhanVien(
	MaNV varchar(30) PRIMARY KEY,
	MaTK varchar(10),
	HoTen nvarchar(40),
	GioiTinh nvarchar(10),
	NgaySinh date,
	DiaChi nvarchar(60),
	SDT varchar(15),
	Email varchar(30),
	TrangThai BIT,
	foreign key(MaTK) references TaiKhoan(MaTK)
)
Insert into NhanVien
values('NV001','TK001',N'Lương Tuấn Đạt',N'Nam','2004-11-14',
N'Huyện Thanh Sơn tỉnh Phú Thọ','0974567728','Datltph32151@gmail.com',0)
Insert into NhanVien
values('NV002','TK002',N'Lương Tuấn Đạt',N'Nam','2004-11-14',
N'Huyện Thanh Sơn tỉnh Phú Thọ','0974567728','Datltph32151@gmail.com',0)
create table LoaiGG(
	MaLoaiGG varchar(10) primary key,
	TenLoai nvarchar(40)
)
Insert into LoaiGG values('LGG001',N'Giảm giá theo số tiền'),('LGG002',N'Giảm giá theo %')
create table GiamGia(
	MaGG varchar(15) primary key,
	MaLoaiGG VARCHAR(10),
	Ten nvarchar(40),
	GiaTri float,
	NgayBatDau date,
	NgayHetHan date,
	GhiChu nvarchar(60),
	TrangThai BIT,
	FOREIGN KEY(MaLoaiGG) REFERENCES LoaiGG(MaLoaiGG)
)

Create table KhachHang(
	MaKH varchar(30) primary key,
	HoTen nvarchar(40),
	NgaySinh date,
	GioiTinh nvarchar(10),
	DiaChi nvarchar(60),
	SDT varchar(15),
	Email varchar(30),
	TrangThai Bit
)
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
				Select * from SanPham where TrangThai =0 1
create table SanPhamChiTiet(
	ID VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
	IDSP varchar(36),
	IDCL varchar(36),
	IDPL varchar(36),
	IDMS varchar(36),
	IDTH varchar(36),
	IDXX varchar(36),
	KichThuoc varchar(15),
	KhoiLuong float,
	SoLuong int,
	Gia float,
	MoTa nvarchar(60),
	Anh varchar(300),
	TrangThai bit,
	foreign key (IDSP) references SanPham(ID),
	foreign key (IDCL) references ChatLieu(ID),
	foreign key (IDPL) references PhanLoai(ID),
	foreign key (IDMS) references MauSac(ID),
	foreign key (IDTH) references ThuongHieu(ID),
	foreign key (IDXX) references XuatXu(ID)
)
insert into SanPhamChiTiet values('SPCT001','3CB6DA47-B586-4F90-94FF-6EB3FF7683D6','CL002','PL001','MS001','TH001','XX003',
'30x13x50',0.1,3,1000,N'Túi VIP pro Tây Hồ','',0)
insert into SanPhamChiTiet values('SPCT002','3CB6DA47-B586-4F90-94FF-6EB3FF7683D6','CL001','PL004','MS003','TH001','XX001',
'35x23x48',1.9,6,3980000,N'Túi xấu xí xù xì','',0)
insert into SanPhamChiTiet values('SPCT003','3EAB5968-944B-42BA-B98D-E9F9A9A4C492','CL001','PL002','MS001','TH002','XX002',
'10x10x10',0.03,50,400,N'Túi đựng rau,thịt,....','',0)
insert into SanPhamChiTiet values('SPCT004','5ECEE5C4-E6F3-4471-B1CC-3E88DB346E63','CL003','PL002','MS004','TH003','XX001',
'45x25x50',1.2,9,139000,N'Túi siêu soft cho các boy','',0)
insert into SanPhamChiTiet values('SPCT005','DB5D2B46-E92A-479B-B07D-7092AB945DA0','CL001','PL002','MS004','TH003','XX001',
'45x25x50',1.2,9,139000,N'Túi siêu soft cho các boy','',0)
insert into SanPhamChiTiet values('SPCT006','DB5D2B46-E92A-479B-B07D-7092AB945DA0','CL001','PL002','MS003','TH003','XX001',
'45x25x50',1.2,9,139000,N'Túi siêu soft cho các boy','',0)
select * from SanPhamChiTiet