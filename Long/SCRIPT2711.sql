USE [master]
GO
/****** Object:  Database [DuAn]    Script Date: 27/11/2023 1:09:09 SA ******/
CREATE DATABASE [DuAn]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DuAn', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\DuAn.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DuAn_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\DuAn_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [DuAn] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DuAn].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DuAn] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DuAn] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DuAn] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DuAn] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DuAn] SET ARITHABORT OFF 
GO
ALTER DATABASE [DuAn] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [DuAn] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DuAn] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DuAn] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DuAn] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DuAn] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DuAn] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DuAn] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DuAn] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DuAn] SET  ENABLE_BROKER 
GO
ALTER DATABASE [DuAn] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DuAn] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DuAn] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DuAn] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DuAn] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DuAn] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DuAn] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DuAn] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [DuAn] SET  MULTI_USER 
GO
ALTER DATABASE [DuAn] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DuAn] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DuAn] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DuAn] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DuAn] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [DuAn] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [DuAn] SET QUERY_STORE = OFF
GO
USE [DuAn]
GO
/****** Object:  Table [dbo].[ChatLieu]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChatLieu](
	[ID] [varchar](36) NOT NULL,
	[TenCL] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GiamGia]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GiamGia](
	[MaGG] [varchar](15) NOT NULL,
	[Ten] [nvarchar](40) NULL,
	[GiaTri] [float] NULL,
	[NgayBatDau] [date] NULL,
	[NgayHetHan] [date] NULL,
	[GhiChu] [nvarchar](60) NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaGG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[IDHD] [varchar](50) NOT NULL,
	[MaNV] [varchar](30) NULL,
	[MaKH] [varchar](30) NULL,
	[MaHD] [varchar](30) NULL,
	[NgayTao] [date] NULL,
	[TrangThai] [int] NULL,
	[TongTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[IDHD] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDonChiTiet]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDonChiTiet](
	[ID] [varchar](50) NOT NULL,
	[IDHD] [varchar](50) NULL,
	[IDSPCT] [varchar](36) NULL,
	[SoLuong] [int] NULL,
	[DonGia] [float] NULL,
	[ThanhTien] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MauSac]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MauSac](
	[ID] [varchar](36) NOT NULL,
	[TenMS] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [varchar](30) NOT NULL,
	[MaTK] [varchar](10) NULL,
	[HoTen] [nvarchar](40) NULL,
	[GioiTinh] [nvarchar](10) NULL,
	[NgaySinh] [date] NULL,
	[DiaChi] [nvarchar](60) NULL,
	[SDT] [varchar](15) NULL,
	[Email] [varchar](30) NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhanLoai]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhanLoai](
	[ID] [varchar](36) NOT NULL,
	[TenPL] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPham]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPham](
	[ID] [varchar](36) NOT NULL,
	[MaSP] [varchar](15) NULL,
	[TenSP] [nvarchar](30) NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SanPhamChiTiet]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SanPhamChiTiet](
	[ID] [varchar](36) NOT NULL,
	[IDSP] [varchar](36) NULL,
	[IDCL] [varchar](36) NULL,
	[IDPL] [varchar](36) NULL,
	[IDMS] [varchar](36) NULL,
	[IDTH] [varchar](36) NULL,
	[IDXX] [varchar](36) NULL,
	[MaGG] [varchar](15) NULL,
	[KichThuoc] [varchar](15) NULL,
	[KhoiLuong] [float] NULL,
	[SoLuong] [int] NULL,
	[Gia] [float] NULL,
	[MoTa] [nvarchar](60) NULL,
	[Anh] [varchar](300) NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[MaTK] [varchar](10) NOT NULL,
	[TenDangNhap] [varchar](20) NULL,
	[MatKhau] [varchar](20) NULL,
	[VaiTro] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ThuongHieu]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ThuongHieu](
	[ID] [varchar](36) NOT NULL,
	[TenTH] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[XuatXu]    Script Date: 27/11/2023 1:09:10 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[XuatXu](
	[ID] [varchar](36) NOT NULL,
	[TenXX] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[ChatLieu] ([ID], [TenCL]) VALUES (N'CL001', N'Da')
INSERT [dbo].[ChatLieu] ([ID], [TenCL]) VALUES (N'CL002', N'Vải')
INSERT [dbo].[ChatLieu] ([ID], [TenCL]) VALUES (N'CL003', N'Cotton')
INSERT [dbo].[ChatLieu] ([ID], [TenCL]) VALUES (N'CL004', N'Lông')
GO
INSERT [dbo].[GiamGia] ([MaGG], [Ten], [GiaTri], [NgayBatDau], [NgayHetHan], [GhiChu], [TrangThai]) VALUES (N'GG001', N'Black Feiday', 30, CAST(N'2023-11-25' AS Date), CAST(N'2023-11-27' AS Date), N'Gi?m 50%', 0)
GO
INSERT [dbo].[HoaDon] ([IDHD], [MaNV], [MaKH], [MaHD], [NgayTao], [TrangThai], [TongTien]) VALUES (N'3569BC56-E6D6-4899-B177-5BBF23CB7F2C', NULL, N'123', N'3SD0A', CAST(N'2023-11-27' AS Date), 1, NULL)
INSERT [dbo].[HoaDon] ([IDHD], [MaNV], [MaKH], [MaHD], [NgayTao], [TrangThai], [TongTien]) VALUES (N'CE463C2D-5F62-4ABF-BAFA-10B1446DA273', NULL, N'', N'3WA99', CAST(N'2023-11-27' AS Date), 1, NULL)
INSERT [dbo].[HoaDon] ([IDHD], [MaNV], [MaKH], [MaHD], [NgayTao], [TrangThai], [TongTien]) VALUES (N'D88999B4-887A-4FC9-86CB-480A4A3F8224', NULL, N'123', N'9ASNX', CAST(N'2023-11-27' AS Date), 1, NULL)
INSERT [dbo].[HoaDon] ([IDHD], [MaNV], [MaKH], [MaHD], [NgayTao], [TrangThai], [TongTien]) VALUES (N'E9CAF03B-BE74-4617-A0DB-67FF14890AF7', NULL, N'Long', N'OLNL1', CAST(N'2023-11-26' AS Date), 1, 1254610)
INSERT [dbo].[HoaDon] ([IDHD], [MaNV], [MaKH], [MaHD], [NgayTao], [TrangThai], [TongTien]) VALUES (N'FDD0E62F-1A47-4AD1-B536-83C89AF38D23', NULL, N'dat', N'KE490', CAST(N'2023-11-26' AS Date), 1, 970)
GO
INSERT [dbo].[HoaDonChiTiet] ([ID], [IDHD], [IDSPCT], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'2457C0E9-AA23-434E-A957-485F0CB284B4', N'E9CAF03B-BE74-4617-A0DB-67FF14890AF7', N'SPCT001', 4, 1000, 3880)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IDHD], [IDSPCT], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'2969CF90-1FA3-437D-AD4F-C7544C471758', N'FDD0E62F-1A47-4AD1-B536-83C89AF38D23', N'SPCT001', 1, 1000, 970)
INSERT [dbo].[HoaDonChiTiet] ([ID], [IDHD], [IDSPCT], [SoLuong], [DonGia], [ThanhTien]) VALUES (N'CA30CEFD-0F76-4737-8A2C-BCA5A3BD050C', N'E9CAF03B-BE74-4617-A0DB-67FF14890AF7', N'SPCT006', 9, 139000, 1250730)
GO
INSERT [dbo].[MauSac] ([ID], [TenMS]) VALUES (N'MS001', N'Đỏ')
INSERT [dbo].[MauSac] ([ID], [TenMS]) VALUES (N'MS002', N'Đen')
INSERT [dbo].[MauSac] ([ID], [TenMS]) VALUES (N'MS003', N'Xám')
INSERT [dbo].[MauSac] ([ID], [TenMS]) VALUES (N'MS004', N'Nâu')
GO
INSERT [dbo].[NhanVien] ([MaNV], [MaTK], [HoTen], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [Email], [TrangThai]) VALUES (N'NV001', N'TK001', N'Lương Tuấn Đạt', N'Nam', CAST(N'2004-11-14' AS Date), N'Huyện Thanh Sơn tỉnh Phú Thọ', N'0974567728', N'Datltph32151@gmail.com', 0)
INSERT [dbo].[NhanVien] ([MaNV], [MaTK], [HoTen], [GioiTinh], [NgaySinh], [DiaChi], [SDT], [Email], [TrangThai]) VALUES (N'NV002', N'TK002', N'Lương Tuấn Đạt', N'Nam', CAST(N'2004-11-14' AS Date), N'Huyện Thanh Sơn tỉnh Phú Thọ', N'0974567728', N'Datltph32151@gmail.com', 0)
GO
INSERT [dbo].[PhanLoai] ([ID], [TenPL]) VALUES (N'PL001', N'Túi đeo chéo')
INSERT [dbo].[PhanLoai] ([ID], [TenPL]) VALUES (N'PL002', N'Túi đưa thư')
INSERT [dbo].[PhanLoai] ([ID], [TenPL]) VALUES (N'PL003', N'Túi Nilon')
INSERT [dbo].[PhanLoai] ([ID], [TenPL]) VALUES (N'PL004', N'Cặp da')
GO
INSERT [dbo].[SanPham] ([ID], [MaSP], [TenSP], [TrangThai]) VALUES (N'17C84F4F-7268-446A-85D1-3450E3ED9236', N'SP001', N'Túi đeo 2022', 0)
INSERT [dbo].[SanPham] ([ID], [MaSP], [TenSP], [TrangThai]) VALUES (N'1EBCF615-3E5D-492B-9EA9-CD6184AABC5E', N'SP002', N'Cặp da cá xấu xí', 0)
INSERT [dbo].[SanPham] ([ID], [MaSP], [TenSP], [TrangThai]) VALUES (N'94EDA0CF-5D77-4647-BF9C-2509085DECFC', N'SP003', N'Túi nilon 2021', 0)
INSERT [dbo].[SanPham] ([ID], [MaSP], [TenSP], [TrangThai]) VALUES (N'9565A53D-BFEA-4433-823F-ADC7B6BA52CC', N'SP004', N'Túi đeo 2023', 0)
GO
INSERT [dbo].[SanPhamChiTiet] ([ID], [IDSP], [IDCL], [IDPL], [IDMS], [IDTH], [IDXX], [MaGG], [KichThuoc], [KhoiLuong], [SoLuong], [Gia], [MoTa], [Anh], [TrangThai]) VALUES (N'SPCT001', N'17C84F4F-7268-446A-85D1-3450E3ED9236', N'CL002', N'PL001', N'MS001', N'TH001', N'XX003', N'GG001', N'30x13x50', 0.1, 9, 1000, N'Túi VIP pro Tây Hồ', N'', 0)
INSERT [dbo].[SanPhamChiTiet] ([ID], [IDSP], [IDCL], [IDPL], [IDMS], [IDTH], [IDXX], [MaGG], [KichThuoc], [KhoiLuong], [SoLuong], [Gia], [MoTa], [Anh], [TrangThai]) VALUES (N'SPCT002', N'17C84F4F-7268-446A-85D1-3450E3ED9236', N'CL001', N'PL004', N'MS003', N'TH001', N'XX001', NULL, N'35x23x48', 1.9, 6, 3980000, N'Túi xấu xí xù xì', N'', 0)
INSERT [dbo].[SanPhamChiTiet] ([ID], [IDSP], [IDCL], [IDPL], [IDMS], [IDTH], [IDXX], [MaGG], [KichThuoc], [KhoiLuong], [SoLuong], [Gia], [MoTa], [Anh], [TrangThai]) VALUES (N'SPCT003', N'1EBCF615-3E5D-492B-9EA9-CD6184AABC5E', N'CL001', N'PL002', N'MS001', N'TH002', N'XX002', NULL, N'10x10x10', 0.03, 50, 400, N'Túi đựng rau,thịt,....', N'', 0)
INSERT [dbo].[SanPhamChiTiet] ([ID], [IDSP], [IDCL], [IDPL], [IDMS], [IDTH], [IDXX], [MaGG], [KichThuoc], [KhoiLuong], [SoLuong], [Gia], [MoTa], [Anh], [TrangThai]) VALUES (N'SPCT004', N'1EBCF615-3E5D-492B-9EA9-CD6184AABC5E', N'CL003', N'PL002', N'MS004', N'TH003', N'XX001', N'GG001', N'45x25x50', 1.2, 9, 139000, N'Túi siêu soft cho các boy', N'', 0)
INSERT [dbo].[SanPhamChiTiet] ([ID], [IDSP], [IDCL], [IDPL], [IDMS], [IDTH], [IDXX], [MaGG], [KichThuoc], [KhoiLuong], [SoLuong], [Gia], [MoTa], [Anh], [TrangThai]) VALUES (N'SPCT005', N'94EDA0CF-5D77-4647-BF9C-2509085DECFC', N'CL001', N'PL002', N'MS004', N'TH003', N'XX001', NULL, N'45x25x50', 1.2, 9, 139000, N'Túi siêu soft cho các boy', N'', 0)
INSERT [dbo].[SanPhamChiTiet] ([ID], [IDSP], [IDCL], [IDPL], [IDMS], [IDTH], [IDXX], [MaGG], [KichThuoc], [KhoiLuong], [SoLuong], [Gia], [MoTa], [Anh], [TrangThai]) VALUES (N'SPCT006', N'9565A53D-BFEA-4433-823F-ADC7B6BA52CC', N'CL001', N'PL002', N'MS003', N'TH003', N'XX001', N'GG001', N'45x25x50', 1.2, 0, 139000, N'Túi siêu soft cho các boy', N'', 0)
GO
INSERT [dbo].[TaiKhoan] ([MaTK], [TenDangNhap], [MatKhau], [VaiTro]) VALUES (N'TK001', N'NV001', N'1', N'Nhân viên')
INSERT [dbo].[TaiKhoan] ([MaTK], [TenDangNhap], [MatKhau], [VaiTro]) VALUES (N'TK002', N'CCH001', N'1', N'Chủ cửa hàng')
GO
INSERT [dbo].[ThuongHieu] ([ID], [TenTH]) VALUES (N'TH001', N'Gucci')
INSERT [dbo].[ThuongHieu] ([ID], [TenTH]) VALUES (N'TH002', N'LuonVuiTuoi')
INSERT [dbo].[ThuongHieu] ([ID], [TenTH]) VALUES (N'TH003', N'Adidas')
INSERT [dbo].[ThuongHieu] ([ID], [TenTH]) VALUES (N'TH004', N'Đôn chề')
GO
INSERT [dbo].[XuatXu] ([ID], [TenXX]) VALUES (N'MS004', N'Pháp')
INSERT [dbo].[XuatXu] ([ID], [TenXX]) VALUES (N'XX001', N'Việt Nam')
INSERT [dbo].[XuatXu] ([ID], [TenXX]) VALUES (N'XX002', N'Trung Quốc')
INSERT [dbo].[XuatXu] ([ID], [TenXX]) VALUES (N'XX003', N'Mỹ')
GO
ALTER TABLE [dbo].[ChatLieu] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[HoaDon] ADD  DEFAULT (newid()) FOR [IDHD]
GO
ALTER TABLE [dbo].[HoaDonChiTiet] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[MauSac] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[PhanLoai] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[SanPham] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[SanPhamChiTiet] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[ThuongHieu] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[XuatXu] ADD  DEFAULT (newid()) FOR [ID]
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD  CONSTRAINT [FK1] FOREIGN KEY([IDHD])
REFERENCES [dbo].[HoaDon] ([IDHD])
GO
ALTER TABLE [dbo].[HoaDonChiTiet] CHECK CONSTRAINT [FK1]
GO
ALTER TABLE [dbo].[HoaDonChiTiet]  WITH CHECK ADD  CONSTRAINT [FK2] FOREIGN KEY([IDSPCT])
REFERENCES [dbo].[SanPhamChiTiet] ([ID])
GO
ALTER TABLE [dbo].[HoaDonChiTiet] CHECK CONSTRAINT [FK2]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD FOREIGN KEY([MaTK])
REFERENCES [dbo].[TaiKhoan] ([MaTK])
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD FOREIGN KEY([IDCL])
REFERENCES [dbo].[ChatLieu] ([ID])
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD FOREIGN KEY([IDMS])
REFERENCES [dbo].[MauSac] ([ID])
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD FOREIGN KEY([IDPL])
REFERENCES [dbo].[PhanLoai] ([ID])
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD FOREIGN KEY([IDSP])
REFERENCES [dbo].[SanPham] ([ID])
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD FOREIGN KEY([IDTH])
REFERENCES [dbo].[ThuongHieu] ([ID])
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD FOREIGN KEY([IDXX])
REFERENCES [dbo].[XuatXu] ([ID])
GO
ALTER TABLE [dbo].[SanPhamChiTiet]  WITH CHECK ADD FOREIGN KEY([MaGG])
REFERENCES [dbo].[GiamGia] ([MaGG])
GO
USE [master]
GO
ALTER DATABASE [DuAn] SET  READ_WRITE 
GO
