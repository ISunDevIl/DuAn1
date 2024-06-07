
create database testda1
go
use testda1
go
Create table SanPham(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	MaSP varchar(15),
	TenSP nvarchar(30),
	TrangThai BIT
)
-------ThuocTinhSanPham-------
Create table ThuongHieu(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenTH nvarchar(30)
)
insert into ThuongHieu(TenTH) values('Gucci')
insert into ThuongHieu(TenTH) values('LuonVuiTuoi')
insert into ThuongHieu(TenTH) values('Laptop360')
insert into ThuongHieu(TenTH) values('FPTU')
insert into ThuongHieu(TenTH) values('A')
select * from ThuongHieu
Create table XuatXu(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenXX nvarchar(30)
)
insert into XuatXu(TenXX) values (N'Việt Nam')
insert into XuatXu(TenXX) values (N'Trung Quốc')
insert into XuatXu(TenXX) values (N'Thái Lan')
insert into XuatXu(TenXX) values (N'USA')
insert into XuatXu(TenXX) values (N'Pháp')
select * from XuatXu
Create table MauSac(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenMS nvarchar(30)
)
Insert into MauSac(TenMS) values(N'Đỏ')
Insert into MauSac(TenMS) values(N'Vàng')
Insert into MauSac(TenMS) values(N'Đen')
Insert into MauSac(TenMS) values(N'Xanh')
Insert into MauSac(TenMS) values(N'Xám')
select * from MauSac
Create table ChatLieu(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenCL nvarchar(30)
)
insert into ChatLieu(TenCL) values(N'Vải')
insert into ChatLieu(TenCL) values(N'Da')
insert into ChatLieu(TenCL) values(N'Cotton')
insert into ChatLieu(TenCL) values(N'Lụa')
insert into ChatLieu(TenCL) values(N'Lông')
select * from ChatLieu
Create table PhanLoai(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	TenPL nvarchar(30)
)
insert into PhanLoai(TenPL) values(N'Túi đeo chéo')
insert into PhanLoai(TenPL) values(N'Túi xách tay')
insert into PhanLoai(TenPL) values(N'Balo')
select * from PhanLoai

--------SanPhamChiTiet---------
Create table SanPhamChiTiet(
	ID VARCHAR(50) PRIMARY KEY DEFAULT NEWID(),
	IDCL VARCHAR(50),
	IDPL VARCHAR(50),
	IDTH VARCHAR(50),
	IDXX VARCHAR(50),
	IDMS VARCHAR(50),
	KhoiLuong float,
	Anh varchar(300),
	SoLuong int,
	MoTa nvarchar(100),
	KichThuoc varchar(15),
	Gia float,
	FOREIGN KEY(IDCL) REFERENCES ChatLieu(ID),
	FOREIGN KEY(IDPL) REFERENCES PhanLoai(ID),
	FOREIGN KEY(IDTH) REFERENCES ThuongHieu(ID),
	FOREIGN KEY(IDXX) REFERENCES XuatXu(ID),
	FOREIGN KEY(IDMS) REFERENCES MauSac(ID),
)
--------SanPham_SanPhamChiTiet-----------
Create table SanPham_SanPhamChiTiet(
	IDSanPham VARCHAR(50) not null,
	IDSanPhamChiTiet VARCHAR(50) not null
)
select * from SanPham
insert into SanPham(MaSP,TenSP,TrangThai) values ('SP001',N'Balo Gucci',0)
insert into SanPham(ID,MaSP,TenSP,TrangThai) values ('ID001','SP001',N'Balo Gucci',0)
select * from SanPhamChiTiet
insert into SanPhamChiTiet(IDCL,IDMS,IDPL,IDTH,IDXX,KhoiLuong,Anh,SoLuong,MoTa,KichThuoc,Gia)
values('B73A9933-1539-4C89-854E-58A7BF2775B3','750F52D4-2B67-4D38-8D7B-9ED50975E09C','C5447B33-122B-4094-AE11-533624F8979B',
'A74D49E5-A797-4705-BC96-F5BFBADE2B7C','D136862D-2395-4434-805F-EBDF0C373789',1.2,'','','','','')
insert into SanPhamChiTiet(IDCL,IDMS,IDPL,IDTH,IDXX,KhoiLuong,Anh,SoLuong,MoTa,KichThuoc,Gia)
values('B73A9933-1539-4C89-854E-58A7BF2775B3','E1C0AD17-9541-4F4B-8837-0DDFA43C6C8B','C5447B33-122B-4094-AE11-533624F8979B',
'A74D49E5-A797-4705-BC96-F5BFBADE2B7C','D136862D-2395-4434-805F-EBDF0C373789',1.2,'','','','','')
select * from SanPham
select * from SanPhamChiTiet
select * from SanPham_SanPhamChiTiet

insert into SanPham_SanPhamChiTiet values('AB02E2F9-A49C-4619-AFB1-2964C7530104','A428C4F4-CFF2-4825-98F1-E03DD7D1712B')
insert into SanPham_SanPhamChiTiet values('AB02E2F9-A49C-4619-AFB1-2964C7530104','D592F432-2034-4A4D-BB85-8AF446059D98')
select * from SanPhamChiTiet where ID = (Select IDSanPhamChiTiet from SanPham_SanPhamChiTiet where IDSanPham = 'AB02E2F9-A49C-4619-AFB1-2964C7530104')
select * from MauSac
select * from ChatLieu
select * from PhanLoai
select * from ThuongHieu
select * from XuatXu
