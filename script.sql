USE [MoonCakeShop]
GO
ALTER TABLE [dbo].[ProductOrder] DROP CONSTRAINT [FK__ProductOr__UserI__4BAC3F29]
GO
ALTER TABLE [dbo].[OrderDetail] DROP CONSTRAINT [FK__OrderDeta__Order__4E88ABD4]
GO
ALTER TABLE [dbo].[OrderDetail] DROP CONSTRAINT [FK__OrderDeta__IDCak__4F7CD00D]
GO
ALTER TABLE [dbo].[MoonCake] DROP CONSTRAINT [FK__MoonCake__Catego__45F365D3]
GO
ALTER TABLE [dbo].[Account] DROP CONSTRAINT [FK__Account__RoleID__1273C1CD]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 10/14/2020 10:27:11 PM ******/
DROP TABLE [dbo].[Role]
GO
/****** Object:  Table [dbo].[ProductOrder]    Script Date: 10/14/2020 10:27:11 PM ******/
DROP TABLE [dbo].[ProductOrder]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 10/14/2020 10:27:11 PM ******/
DROP TABLE [dbo].[OrderDetail]
GO
/****** Object:  Table [dbo].[MoonCake]    Script Date: 10/14/2020 10:27:11 PM ******/
DROP TABLE [dbo].[MoonCake]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/14/2020 10:27:11 PM ******/
DROP TABLE [dbo].[Category]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 10/14/2020 10:27:11 PM ******/
DROP TABLE [dbo].[Account]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 10/14/2020 10:27:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[UserID] [varchar](25) NOT NULL,
	[Password] [varchar](25) NOT NULL,
	[Phone] [varchar](11) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[RoleID] [varchar](5) NOT NULL,
	[Address] [nvarchar](200) NOT NULL DEFAULT ('H? Chí Minh'),
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Category]    Script Date: 10/14/2020 10:27:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Category](
	[CategoryID] [varchar](5) NOT NULL,
	[CategoryName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MoonCake]    Script Date: 10/14/2020 10:27:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MoonCake](
	[IDCake] [varchar](5) NOT NULL,
	[CakeName] [nvarchar](50) NOT NULL,
	[Image] [varchar](1000) NOT NULL,
	[Description] [nvarchar](200) NOT NULL,
	[Price] [int] NOT NULL,
	[CreateDate] [date] NOT NULL,
	[ExpireDate] [date] NOT NULL,
	[Status] [nvarchar](15) NOT NULL,
	[CategoryID] [varchar](5) NOT NULL,
	[Quantity] [int] NOT NULL DEFAULT ('100'),
	[userUpdate] [nvarchar](50) NOT NULL,
	[lastUpdate] [date] NOT NULL DEFAULT ('9/10/2020'),
PRIMARY KEY CLUSTERED 
(
	[IDCake] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 10/14/2020 10:27:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[DetailID] [int] IDENTITY(1,1) NOT NULL,
	[OrderId] [int] NOT NULL,
	[IDCake] [varchar](5) NOT NULL,
	[Price] [int] NOT NULL,
	[Quantity] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[DetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ProductOrder]    Script Date: 10/14/2020 10:27:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ProductOrder](
	[OrderId] [int] IDENTITY(1,1) NOT NULL,
	[OrderName] [nvarchar](30) NOT NULL,
	[UserID] [varchar](25) NULL,
	[dateOrder] [date] NOT NULL,
	[Address] [nvarchar](200) NOT NULL,
	[Phone] [varchar](11) NOT NULL,
	[Total] [int] NOT NULL,
	[Payment] [nvarchar](20) NOT NULL DEFAULT ('COD'),
	[PaymentStatus] [nvarchar](25) NOT NULL DEFAULT ('Waiting for Pay'),
PRIMARY KEY CLUSTERED 
(
	[OrderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Role]    Script Date: 10/14/2020 10:27:11 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Role](
	[RoleID] [varchar](5) NOT NULL,
	[RoleName] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Account] ([UserID], [Password], [Phone], [Fullname], [RoleID], [Address]) VALUES (N'guest', N'123456789', N'0312312356', N'Guest', N'1', N'Hồ Chí Minh')
INSERT [dbo].[Account] ([UserID], [Password], [Phone], [Fullname], [RoleID], [Address]) VALUES (N'huavinhkhang', N'123456789', N'0936528417', N'Hứa Vĩnh Khang', N'2', N'H? Chí Minh')
INSERT [dbo].[Account] ([UserID], [Password], [Phone], [Fullname], [RoleID], [Address]) VALUES (N'lamhauhuong', N'123456789', N'0123421374', N'Lâm Hậu Huống', N'1', N'Hồ Chí Minh')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (N'1', N'Bánh Nhân Ngọt Trứng Muối')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (N'2', N'Bánh Chay')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (N'3', N'Bánh Mặn')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (N'4', N'Bánh Mặn Không Trứng Muối')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (N'5', N'Bánh Dẻo Nhân Ngọt Trứng Muối')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (N'6', N'Bánh Dẻo Chay')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (N'7', N'Bánh Dẻo Thập Cẩm')
INSERT [dbo].[Category] ([CategoryID], [CategoryName]) VALUES (N'8', N'Khác')
INSERT [dbo].[MoonCake] ([IDCake], [CakeName], [Image], [Description], [Price], [CreateDate], [ExpireDate], [Status], [CategoryID], [Quantity], [userUpdate], [lastUpdate]) VALUES (N'1', N'Bánh Trung Thu Thập Cẩm 1 Trứng', N'https://agiadinh.net/wp-content/uploads/2018/07/banh-trung-thu-nhan-thap-cam-trung-muoi-4-600x402.jpg', N'Bánh Thập Cẩm ngon đến lẩm cẩm', 65000, CAST(N'2020-09-24' AS Date), CAST(N'2020-09-28' AS Date), N'Available', N'3', 150, N'Hứa Vĩnh Khang', CAST(N'2020-10-14' AS Date))
INSERT [dbo].[MoonCake] ([IDCake], [CakeName], [Image], [Description], [Price], [CreateDate], [ExpireDate], [Status], [CategoryID], [Quantity], [userUpdate], [lastUpdate]) VALUES (N'2', N'Bánh Dẻo Nhân Thập Cẩm', N'https://img.websosanh.vn/v10/users/review/images/26uokdgbi9yfc/banh-deo-nhan-thap-cam-2019.jpg?compress=85', N'Bánh Dẻo Ngon', 45000, CAST(N'2020-09-18' AS Date), CAST(N'2020-09-30' AS Date), N'Available', N'7', 100, N'Hứa Vĩnh Khang', CAST(N'2020-10-11' AS Date))
INSERT [dbo].[MoonCake] ([IDCake], [CakeName], [Image], [Description], [Price], [CreateDate], [ExpireDate], [Status], [CategoryID], [Quantity], [userUpdate], [lastUpdate]) VALUES (N'3', N'Bánh Trung Thu Đậu Xanh 2 Trứng', N'https://1.bp.blogspot.com/-UZGTB9jagZI/V7T-FKydfoI/AAAAAAAAFug/OyHDf3NtPwQNrdG5c-ZvzdaM_t9cktV8QCLcB/s320/banh%2Btrung%2Bthu%2Bnhan%2Bdau%2Bxanh.jpeg', N'Ngon lắm nha', 40000, CAST(N'2020-09-10' AS Date), CAST(N'2020-09-18' AS Date), N'Available', N'1', 100, N'Hứa Vĩnh Khang', CAST(N'2020-10-14' AS Date))
INSERT [dbo].[MoonCake] ([IDCake], [CakeName], [Image], [Description], [Price], [CreateDate], [ExpireDate], [Status], [CategoryID], [Quantity], [userUpdate], [lastUpdate]) VALUES (N'4', N'Bánh Trung Thu Mè Đen Không Trứng', N'https://vn-test-11.slatic.net/p/5b19bb48154a2ce5860dee70ea89c1f0.png', N'Mè Đen ngon', 35000, CAST(N'2020-10-09' AS Date), CAST(N'2020-10-13' AS Date), N'Available', N'2', 98, N'Hứa Vĩnh Khang', CAST(N'2020-10-11' AS Date))
INSERT [dbo].[MoonCake] ([IDCake], [CakeName], [Image], [Description], [Price], [CreateDate], [ExpireDate], [Status], [CategoryID], [Quantity], [userUpdate], [lastUpdate]) VALUES (N'5', N'Bánh Trung Thu Trân Châu', N'https://scontent.fhan5-2.fna.fbcdn.net/v/t1.0-9/117386093_754438558679243_7371815515231809588_n.jpg?_nc_cat=102&_nc_sid=730e14&_nc_ohc=N5fFgb67Fj4AX_sH5dY&_nc_ht=scontent.fhan5-2.fna&oh=f35f1b121766434fe644cf670b4a9981&oe=5FAA4D85', N'Ngon lắm nha', 40000, CAST(N'2020-10-05' AS Date), CAST(N'2020-10-11' AS Date), N'Available', N'1', 100, N'Hứa Vĩnh Khang', CAST(N'2020-10-11' AS Date))
INSERT [dbo].[MoonCake] ([IDCake], [CakeName], [Image], [Description], [Price], [CreateDate], [ExpireDate], [Status], [CategoryID], [Quantity], [userUpdate], [lastUpdate]) VALUES (N'6', N'Bánh Trung Thu Trà Xanh Không Trứng', N'https://anh.24h.com.vn//upload/3-2016/images/2016-09-05/1473050683-thumbnail.jpg', N'Ngon lắm nha', 42000, CAST(N'2020-09-03' AS Date), CAST(N'2020-09-30' AS Date), N'Available', N'1', 83, N'Hứa Vĩnh Khang', CAST(N'2020-10-12' AS Date))
INSERT [dbo].[MoonCake] ([IDCake], [CakeName], [Image], [Description], [Price], [CreateDate], [ExpireDate], [Status], [CategoryID], [Quantity], [userUpdate], [lastUpdate]) VALUES (N'7', N'Bánh Trung Thu Hạt Sen', N'https://khachhang2.web3b.com/banhtrungthu3/wp-content/uploads/2019/07/Product-1533007146-5937-pure_lotus-600x600.png', N'Ngon lắm nha', 35000, CAST(N'2020-10-01' AS Date), CAST(N'2020-10-02' AS Date), N'active', N'1', 100, N'Hứa Vĩnh Khang', CAST(N'2020-10-14' AS Date))
INSERT [dbo].[MoonCake] ([IDCake], [CakeName], [Image], [Description], [Price], [CreateDate], [ExpireDate], [Status], [CategoryID], [Quantity], [userUpdate], [lastUpdate]) VALUES (N'8', N'Bánh Trung Thu Vi Cá', N'https://image-us.eva.vn/upload/3-2019/images/2019-09-02/10-loai-banh-trung-thu-doc-la-cua-trung-quoc-1-1567427101-337-width650height650.jpg', N'Ngon lắm nha', 78000, CAST(N'2020-10-01' AS Date), CAST(N'2020-10-21' AS Date), N'active', N'3', 145, N'Hứa Vĩnh Khang', CAST(N'2020-10-14' AS Date))
INSERT [dbo].[MoonCake] ([IDCake], [CakeName], [Image], [Description], [Price], [CreateDate], [ExpireDate], [Status], [CategoryID], [Quantity], [userUpdate], [lastUpdate]) VALUES (N'9', N'Bánh Trung Thu Hải Sản Chiên Cơm', N'https://image-us.eva.vn/upload/3-2019/images/2019-09-02/10-loai-banh-trung-thu-doc-la-cua-trung-quoc-1-1567427101-337-width650height650.jpg', N'Khôi bảo "Nghe Hấp Dẫn"', 60000, CAST(N'2020-09-02' AS Date), CAST(N'2020-09-24' AS Date), N'Available', N'1', 198, N'Hứa Vĩnh Khang', CAST(N'2020-10-14' AS Date))
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (12, 13, N'5', 40000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (13, 13, N'2', 45000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (14, 13, N'1', 45000, 4)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (15, 14, N'4', 35000, 2)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (16, 14, N'2', 45000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (17, 14, N'3', 40000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (18, 15, N'6', 42000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (19, 15, N'1', 45000, 3)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (20, 15, N'2', 45000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (21, 16, N'2', 45000, 2)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (22, 16, N'1', 45000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (23, 17, N'1', 45000, 4)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (24, 17, N'5', 40000, 3)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (25, 18, N'2', 45000, 5)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (26, 18, N'1', 45000, 3)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (27, 19, N'1', 45000, 4)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (28, 19, N'2', 45000, 2)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (29, 20, N'1', 45000, 2)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (30, 21, N'6', 42000, 99)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (31, 22, N'6', 42000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (32, 22, N'4', 35000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (33, 22, N'1', 45000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (34, 22, N'2', 45000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (35, 23, N'6', 42000, 99)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (36, 24, N'6', 42000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (37, 25, N'6', 42000, 99)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (38, 26, N'6', 42000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (39, 27, N'6', 42000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (40, 28, N'1', 45000, 89)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (41, 29, N'6', 42000, 3)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (42, 30, N'6', 42000, 3)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (43, 30, N'2', 45000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (44, 31, N'6', 42000, 1)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (45, 32, N'6', 42000, 6)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (46, 33, N'6', 42000, 4)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (47, 33, N'8', 78000, 5)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (48, 34, N'9', 60000, 2)
INSERT [dbo].[OrderDetail] ([DetailID], [OrderId], [IDCake], [Price], [Quantity]) VALUES (49, 34, N'4', 35000, 1)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
SET IDENTITY_INSERT [dbo].[ProductOrder] ON 

INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (13, N'Cart of Khang', N'Guest', CAST(N'2020-10-13' AS Date), N'123 QUận 2', N'0961101399', 265000, N'COD', N'Waiting for Pay')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (14, N'Cart of Lâm Hậu Huống', N'lamhauhuong', CAST(N'2020-10-13' AS Date), N'123 QUận 2', N'0123421374', 155000, N'COD', N'Waiting for Pay')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (15, N'Cart of Lâm Hậu Huống', N'lamhauhuong', CAST(N'2020-10-13' AS Date), N'123 QUận 2', N'0123421374', 222000, N'COD', N'Waiting for Pay')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (16, N'Cart of Lâm Hậu Huống', N'lamhauhuong', CAST(N'2020-10-13' AS Date), N'123 QUận 2', N'0123421374', 135000, N'COD', N'Waiting for Pay')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (17, N'Cart of Lâm Hậu Huống', N'lamhauhuong', CAST(N'2020-10-13' AS Date), N'123 QUận 2', N'0123421374', 300000, N'COD', N'Waiting for Pay')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (18, N'Cart of Lâm Hậu Huống', N'lamhauhuong', CAST(N'2020-10-13' AS Date), N'123 QUận 2', N'0123421374', 360000, N'COD', N'Waiting for Pay')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (19, N'Cart of Khang', N'Guest', CAST(N'2020-10-14' AS Date), N'123 QUận 2', N'0315264878', 270000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (20, N'Cart of Khang', N'Guest', CAST(N'2020-10-14' AS Date), N'123 QUận 2', N'0961101399', 90000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (21, N'Cart of Lê Đạt Anh Khôi', N'Guest', CAST(N'2020-10-14' AS Date), N'Nguyễn Chí Thanh', N'0123426132', 4158000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (22, N'Cart of Bùi Vĩnh Khôi', N'Guest', CAST(N'2020-10-14' AS Date), N'Quận 8', N'0312345612', 167000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (23, N'Cart of KonoDioDa', N'Guest', CAST(N'2020-10-14' AS Date), N'Petshop', N'0123426132', 4158000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (24, N'Cart of Bùi Vĩnh Khôi', N'Guest', CAST(N'2020-10-14' AS Date), N'Quận 8', N'0315264878', 42000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (25, N'Cart of Bùi Vĩnh Khôi', N'Guest', CAST(N'2020-10-14' AS Date), N'Quận 8', N'0315264878', 4158000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (26, N'Cart of KonoDioDa', N'Guest', CAST(N'2020-10-14' AS Date), N'Petshop', N'0123426132', 42000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (27, N'Cart of Khang', N'Guest', CAST(N'2020-10-14' AS Date), N'123 QUận 2', N'0961101399', 42000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (28, N'Cart of KonoDioDa', N'Guest', CAST(N'2020-10-14' AS Date), N'Petshop', N'0123426132', 4005000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (29, N'Cart of Khang', N'Guest', CAST(N'2020-10-14' AS Date), N'123 QUận 2', N'0315264878', 126000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (30, N'Cart of Khang', N'Guest', CAST(N'2020-10-14' AS Date), N'123 QUận 2', N'0315264878', 171000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (31, N'Cart of Khang', N'Guest', CAST(N'2020-10-14' AS Date), N'Quận 8', N'0315264878', 42000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (32, N'Cart of Khang', N'Guest', CAST(N'2020-10-14' AS Date), N'123 QUận 2', N'0315264878', 252000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (33, N'Cart of Lâm Hậu Huống', N'lamhauhuong', CAST(N'2020-10-14' AS Date), N'Quận 8', N'0123421374', 558000, N'COD', N'Waiting for Paying')
INSERT [dbo].[ProductOrder] ([OrderId], [OrderName], [UserID], [dateOrder], [Address], [Phone], [Total], [Payment], [PaymentStatus]) VALUES (34, N'Cart of Lâm Hậu Huống', N'lamhauhuong', CAST(N'2020-10-14' AS Date), N'Quận 8', N'0123421374', 155000, N'COD', N'Waiting for Paying')
SET IDENTITY_INSERT [dbo].[ProductOrder] OFF
INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (N'1', N'Member')
INSERT [dbo].[Role] ([RoleID], [RoleName]) VALUES (N'2', N'Admin')
ALTER TABLE [dbo].[Account]  WITH CHECK ADD FOREIGN KEY([RoleID])
REFERENCES [dbo].[Role] ([RoleID])
GO
ALTER TABLE [dbo].[MoonCake]  WITH CHECK ADD FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Category] ([CategoryID])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([IDCake])
REFERENCES [dbo].[MoonCake] ([IDCake])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([OrderId])
REFERENCES [dbo].[ProductOrder] ([OrderId])
GO
ALTER TABLE [dbo].[ProductOrder]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[Account] ([UserID])
GO
