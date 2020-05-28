USE [master]
GO
/****** Object:  Database [Football]    Script Date: 28/05/2020 18:52:14 ******/
CREATE DATABASE [Football]
 CONTAINMENT = NONE
 --ON  PRIMARY 
--( NAME = N'Football', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS_2012\MSSQL\DATA\Football.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 --LOG ON 
--( NAME = N'Football_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS_2012\MSSQL\DATA\Football_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [Football] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Football].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Football] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Football] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Football] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Football] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Football] SET ARITHABORT OFF 
GO
ALTER DATABASE [Football] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Football] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Football] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Football] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Football] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Football] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Football] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Football] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Football] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Football] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Football] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Football] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Football] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Football] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Football] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Football] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Football] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Football] SET RECOVERY FULL 
GO
ALTER DATABASE [Football] SET  MULTI_USER 
GO
ALTER DATABASE [Football] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Football] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Football] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Football] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [Football] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Football', N'ON'
GO
ALTER DATABASE [Football] SET QUERY_STORE = OFF
GO
USE [Football]
GO
/****** Object:  Table [dbo].[AssociationMember]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AssociationMember](
	[MemberID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BudgetReports]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BudgetReports](
	[TeamName] [nvarchar](50) NOT NULL,
	[ReportDate] [datetime] NOT NULL,
	[TransferAmount] [real] NULL,
	[TransferCause] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[TeamName] ASC,
	[ReportDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Coach]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Coach](
	[MemberID] [int] NOT NULL,
	[CertID] [int] NULL,
	[TeamName] [nvarchar](50) NULL,
	[JoinInTeam] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CoachTweets]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CoachTweets](
	[CoachID] [int] NOT NULL,
	[Tweet] [nvarchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[CoachID] ASC,
	[Tweet] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FGEventLog]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FGEventLog](
	[EventType] [int] NOT NULL,
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[EventDescription] [nvarchar](150) NULL,
PRIMARY KEY CLUSTERED 
(
	[EventType] ASC,
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FGObservers]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FGObservers](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[ObserverID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC,
	[ObserverID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FootballGame]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FootballGame](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[MainRefereeID] [int] NULL,
	[LeftRefereeID] [int] NULL,
	[RightRefereeID] [int] NULL,
	[VarRefereeID] [int] NULL,
	[HomeGoals] [int] NULL,
	[AwayGoals] [int] NULL,
	[SeasonYear] [int] NULL,
	[LeagueName] [nvarchar](50) NULL,
	[StadiumName] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameDelayedEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameDelayedEvent](
	[GameDate] [datetime] NOT NULL,
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDelayedTime] [datetime] NULL,
	[GameOriginalTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[GameDate] ASC,
	[HomeTeamName] ASC,
	[AwayTeamName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameEndEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameEndEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameFoulEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameFoulEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[GameTime] [time](7) NULL,
	[TeamName] [nvarchar](50) NULL,
	[PlayerID] [int] NULL,
	[FouledPlayerID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameGoalEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameGoalEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[GameTime] [time](7) NULL,
	[TeamName] [nvarchar](50) NULL,
	[PlayerID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameInjuryEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameInjuryEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[GameTime] [time](7) NULL,
	[TeamName] [nvarchar](50) NULL,
	[PlayerID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameOffsideEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameOffsideEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[GameTime] [time](7) NULL,
	[TeamName] [nvarchar](50) NULL,
	[PlayerID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameRedCardEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameRedCardEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[GameTime] [time](7) NULL,
	[TeamName] [nvarchar](50) NULL,
	[PlayerID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameRelocationEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameRelocationEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[NewLocation] [nvarchar](50) NULL,
	[OriginalLocation] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameStartEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameStartEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameSubtitutionEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameSubtitutionEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[GameTime] [time](7) NULL,
	[TeamName] [nvarchar](50) NULL,
	[OutgoingPlayerID] [int] NULL,
	[IngoingPlayerID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GameYellowCardEvent]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GameYellowCardEvent](
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
	[GameTime] [time](7) NULL,
	[TeamName] [nvarchar](50) NULL,
	[PlayerID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[League]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[League](
	[LeagueName] [nvarchar](50) NOT NULL,
	[NumOfTwoTeamsGames] [int] NULL,
	[PointsPerWin] [real] NULL,
	[PointsPerDraw] [real] NULL,
	[PointsPerLoss] [real] NULL,
PRIMARY KEY CLUSTERED 
(
	[LeagueName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LeagueLReferee]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LeagueLReferee](
	[LeagueName] [nvarchar](50) NOT NULL,
	[LineRefereeID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[LeagueName] ASC,
	[LineRefereeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LeagueMReferee]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LeagueMReferee](
	[LeagueName] [nvarchar](50) NOT NULL,
	[MainRefereeID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[LeagueName] ASC,
	[MainRefereeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LeaguePosition]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LeaguePosition](
	[SeasonYear] [int] NOT NULL,
	[LeagueName] [nvarchar](50) NOT NULL,
	[TeamName] [nvarchar](50) NOT NULL,
	[GamesWin] [int] NULL,
	[GamesLoss] [int] NULL,
	[GamesDraw] [int] NULL,
	[GoalsScored] [int] NULL,
	[GoalsReceived] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[SeasonYear] ASC,
	[LeagueName] ASC,
	[TeamName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LeagueVReferee]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LeagueVReferee](
	[LeagueName] [nvarchar](50) NOT NULL,
	[VarRefereeID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[LeagueName] ASC,
	[VarRefereeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Member]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Member](
	[ID] [int] NOT NULL,
	[UserName] [nvarchar](50) NULL,
	[UserPassword] [nvarchar](50) NULL,
	[FullName] [nvarchar](50) NULL,
	[UserOnline] [bit] NULL,
	[Blocked] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[UserName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MemberCoachFollowed]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MemberCoachFollowed](
	[MemberID] [int] NOT NULL,
	[CoachID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC,
	[CoachID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MemberEvents]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MemberEvents](
	[MemberID] [int] NOT NULL,
	[EventDescription] [nvarchar](150) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC,
	[EventDescription] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MemberFGFollowed]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MemberFGFollowed](
	[MemberID] [int] NOT NULL,
	[HomeTeamName] [nvarchar](50) NOT NULL,
	[AwayTeamName] [nvarchar](50) NOT NULL,
	[GameDate] [datetime] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC,
	[HomeTeamName] ASC,
	[AwayTeamName] ASC,
	[GameDate] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MemberPlayerFollowed]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MemberPlayerFollowed](
	[MemberID] [int] NOT NULL,
	[PlayerID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC,
	[PlayerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MemberSearch]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MemberSearch](
	[MemberID] [int] NOT NULL,
	[Search] [nvarchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MemberTeamFollowed]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MemberTeamFollowed](
	[MemberID] [int] NOT NULL,
	[TeamName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC,
	[TeamName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MemberTickets]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MemberTickets](
	[MemberID] [int] NOT NULL,
	[TicketID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC,
	[TicketID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Player]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Player](
	[MemberID] [int] NOT NULL,
	[PosID] [int] NULL,
	[TeamName] [nvarchar](50) NULL,
	[BirthDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PlayerObservers]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlayerObservers](
	[PlayerID] [int] NOT NULL,
	[ObserverID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PlayerID] ASC,
	[ObserverID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PlayerTweets]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlayerTweets](
	[PlayerID] [int] NOT NULL,
	[Tweet] [nvarchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[PlayerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Referee]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Referee](
	[MemberID] [int] NOT NULL,
	[RType] [int] NOT NULL,
	[Active] [bit] NULL,
 CONSTRAINT [PK__Referee__0CF04B38E1F48764] PRIMARY KEY CLUSTERED 
(
	[MemberID] ASC,
	[RType] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Season]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Season](
	[SYear] [int] NOT NULL,
	[LeagueName] [nvarchar](50) NOT NULL,
	[NumOfTwoTeamsGames] [int] NULL,
	[PointsPerWin] [real] NULL,
	[PointsPerDraw] [real] NULL,
	[PointsPerLoss] [real] NULL,
PRIMARY KEY CLUSTERED 
(
	[SYear] ASC,
	[LeagueName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Stadium]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Stadium](
	[SName] [nvarchar](50) NOT NULL,
	[City] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[SName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Team]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Team](
	[TeamName] [nvarchar](50) NOT NULL,
	[StatusID] [int] NULL,
	[StadiumName] [nvarchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TeamName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamCoaches]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamCoaches](
	[TeamName] [nvarchar](50) NOT NULL,
	[CoachID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TeamName] ASC,
	[CoachID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamManager]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamManager](
	[TMID] [int] NOT NULL,
	[JobName] [nvarchar](50) NULL,
	[TeamName] [nvarchar](50) NULL,
 CONSTRAINT [PK__TeamMana__A13C67450E167AAC] PRIMARY KEY CLUSTERED 
(
	[TMID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamObservers]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamObservers](
	[TeamName] [nvarchar](50) NOT NULL,
	[ObserverID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TeamName] ASC,
	[ObserverID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamOwner]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamOwner](
	[TOID] [int] NOT NULL,
	[TeamName] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[TOID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamPlayers]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamPlayers](
	[TeamName] [nvarchar](50) NOT NULL,
	[PlayerID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TeamName] ASC,
	[PlayerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamTM]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamTM](
	[TeamName] [nvarchar](50) NOT NULL,
	[TMID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TeamName] ASC,
	[TMID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamTO]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamTO](
	[TeamName] [nvarchar](50) NOT NULL,
	[TOID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TeamName] ASC,
	[TOID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TeamTweets]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TeamTweets](
	[TeamName] [nvarchar](50) NOT NULL,
	[Tweet] [nvarchar](200) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TeamName] ASC,
	[Tweet] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ticket]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ticket](
	[ID] [int] NOT NULL,
	[Complaint] [nvarchar](50) NULL,
	[Answer] [nvarchar](50) NULL,
	[Answered] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TMPermissions]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TMPermissions](
	[TMID] [int] NOT NULL,
	[PermissionID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TMID] ASC,
	[PermissionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TOAppointments]    Script Date: 28/05/2020 18:52:14 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TOAppointments](
	[TOID] [int] NOT NULL,
	[AID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[TOID] ASC,
	[AID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[AssociationMember]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[BudgetReports]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[Coach]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[Coach]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[CoachTweets]  WITH CHECK ADD FOREIGN KEY([CoachID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[FGEventLog]  WITH CHECK ADD FOREIGN KEY([HomeTeamName], [AwayTeamName], [GameDate])
REFERENCES [dbo].[FootballGame] ([HomeTeamName], [AwayTeamName], [GameDate])
GO
ALTER TABLE [dbo].[FGObservers]  WITH CHECK ADD FOREIGN KEY([ObserverID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[FGObservers]  WITH CHECK ADD FOREIGN KEY([HomeTeamName], [AwayTeamName], [GameDate])
REFERENCES [dbo].[FootballGame] ([HomeTeamName], [AwayTeamName], [GameDate])
GO
ALTER TABLE [dbo].[FootballGame]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[FootballGame]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[FootballGame]  WITH CHECK ADD FOREIGN KEY([LeagueName])
REFERENCES [dbo].[League] ([LeagueName])
GO
ALTER TABLE [dbo].[FootballGame]  WITH CHECK ADD FOREIGN KEY([LeftRefereeID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[FootballGame]  WITH CHECK ADD FOREIGN KEY([MainRefereeID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[FootballGame]  WITH CHECK ADD FOREIGN KEY([RightRefereeID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[FootballGame]  WITH CHECK ADD FOREIGN KEY([StadiumName])
REFERENCES [dbo].[Stadium] ([SName])
GO
ALTER TABLE [dbo].[FootballGame]  WITH CHECK ADD FOREIGN KEY([VarRefereeID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[FootballGame]  WITH CHECK ADD FOREIGN KEY([SeasonYear], [LeagueName])
REFERENCES [dbo].[Season] ([SYear], [LeagueName])
GO
ALTER TABLE [dbo].[GameDelayedEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameDelayedEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameEndEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameEndEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameFoulEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameFoulEvent]  WITH CHECK ADD FOREIGN KEY([FouledPlayerID])
REFERENCES [dbo].[Player] ([MemberID])
GO
ALTER TABLE [dbo].[GameFoulEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameFoulEvent]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Player] ([MemberID])
GO
ALTER TABLE [dbo].[GameFoulEvent]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameGoalEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameGoalEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameGoalEvent]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Player] ([MemberID])
GO
ALTER TABLE [dbo].[GameGoalEvent]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameInjuryEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameInjuryEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameInjuryEvent]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Player] ([MemberID])
GO
ALTER TABLE [dbo].[GameInjuryEvent]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameOffsideEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameOffsideEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameOffsideEvent]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Player] ([MemberID])
GO
ALTER TABLE [dbo].[GameOffsideEvent]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameRedCardEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameRedCardEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameRedCardEvent]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Player] ([MemberID])
GO
ALTER TABLE [dbo].[GameRedCardEvent]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameRelocationEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameRelocationEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameRelocationEvent]  WITH CHECK ADD FOREIGN KEY([NewLocation])
REFERENCES [dbo].[Stadium] ([SName])
GO
ALTER TABLE [dbo].[GameRelocationEvent]  WITH CHECK ADD FOREIGN KEY([OriginalLocation])
REFERENCES [dbo].[Stadium] ([SName])
GO
ALTER TABLE [dbo].[GameStartEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameStartEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameSubtitutionEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameSubtitutionEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameSubtitutionEvent]  WITH CHECK ADD FOREIGN KEY([IngoingPlayerID])
REFERENCES [dbo].[Player] ([MemberID])
GO
ALTER TABLE [dbo].[GameSubtitutionEvent]  WITH CHECK ADD FOREIGN KEY([OutgoingPlayerID])
REFERENCES [dbo].[Player] ([MemberID])
GO
ALTER TABLE [dbo].[GameSubtitutionEvent]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameYellowCardEvent]  WITH CHECK ADD FOREIGN KEY([AwayTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameYellowCardEvent]  WITH CHECK ADD FOREIGN KEY([HomeTeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[GameYellowCardEvent]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Player] ([MemberID])
GO
ALTER TABLE [dbo].[GameYellowCardEvent]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[LeagueLReferee]  WITH CHECK ADD FOREIGN KEY([LeagueName])
REFERENCES [dbo].[League] ([LeagueName])
GO
ALTER TABLE [dbo].[LeagueLReferee]  WITH CHECK ADD FOREIGN KEY([LineRefereeID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[LeagueMReferee]  WITH CHECK ADD FOREIGN KEY([LeagueName])
REFERENCES [dbo].[League] ([LeagueName])
GO
ALTER TABLE [dbo].[LeagueMReferee]  WITH CHECK ADD FOREIGN KEY([MainRefereeID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[LeaguePosition]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[LeaguePosition]  WITH CHECK ADD FOREIGN KEY([SeasonYear], [LeagueName])
REFERENCES [dbo].[Season] ([SYear], [LeagueName])
GO
ALTER TABLE [dbo].[LeagueVReferee]  WITH CHECK ADD FOREIGN KEY([LeagueName])
REFERENCES [dbo].[League] ([LeagueName])
GO
ALTER TABLE [dbo].[LeagueVReferee]  WITH CHECK ADD FOREIGN KEY([VarRefereeID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberCoachFollowed]  WITH CHECK ADD FOREIGN KEY([CoachID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberCoachFollowed]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberEvents]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberFGFollowed]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberFGFollowed]  WITH CHECK ADD FOREIGN KEY([HomeTeamName], [AwayTeamName], [GameDate])
REFERENCES [dbo].[FootballGame] ([HomeTeamName], [AwayTeamName], [GameDate])
GO
ALTER TABLE [dbo].[MemberPlayerFollowed]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberPlayerFollowed]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberSearch]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberTeamFollowed]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberTeamFollowed]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[MemberTickets]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[MemberTickets]  WITH CHECK ADD FOREIGN KEY([TicketID])
REFERENCES [dbo].[Ticket] ([ID])
GO
ALTER TABLE [dbo].[Player]  WITH CHECK ADD FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[Player]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[PlayerObservers]  WITH CHECK ADD FOREIGN KEY([ObserverID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[PlayerObservers]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[PlayerTweets]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[Referee]  WITH CHECK ADD  CONSTRAINT [FK__Referee__MemberI__22751F6C] FOREIGN KEY([MemberID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[Referee] CHECK CONSTRAINT [FK__Referee__MemberI__22751F6C]
GO
ALTER TABLE [dbo].[Season]  WITH CHECK ADD FOREIGN KEY([LeagueName])
REFERENCES [dbo].[League] ([LeagueName])
GO
ALTER TABLE [dbo].[Team]  WITH CHECK ADD FOREIGN KEY([StadiumName])
REFERENCES [dbo].[Stadium] ([SName])
GO
ALTER TABLE [dbo].[TeamCoaches]  WITH CHECK ADD FOREIGN KEY([CoachID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[TeamCoaches]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[TeamManager]  WITH CHECK ADD  CONSTRAINT [FK__TeamManag__TeamN__2B0A656D] FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[TeamManager] CHECK CONSTRAINT [FK__TeamManag__TeamN__2B0A656D]
GO
ALTER TABLE [dbo].[TeamManager]  WITH CHECK ADD  CONSTRAINT [FK__TeamManage__TMID__2A164134] FOREIGN KEY([TMID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[TeamManager] CHECK CONSTRAINT [FK__TeamManage__TMID__2A164134]
GO
ALTER TABLE [dbo].[TeamObservers]  WITH CHECK ADD FOREIGN KEY([ObserverID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[TeamObservers]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[TeamOwner]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[TeamOwner]  WITH CHECK ADD FOREIGN KEY([TOID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[TeamPlayers]  WITH CHECK ADD FOREIGN KEY([PlayerID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[TeamPlayers]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[TeamTM]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[TeamTM]  WITH CHECK ADD FOREIGN KEY([TMID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[TeamTO]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[TeamTO]  WITH CHECK ADD FOREIGN KEY([TOID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[TeamTweets]  WITH CHECK ADD FOREIGN KEY([TeamName])
REFERENCES [dbo].[Team] ([TeamName])
GO
ALTER TABLE [dbo].[TMPermissions]  WITH CHECK ADD FOREIGN KEY([TMID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[TOAppointments]  WITH CHECK ADD FOREIGN KEY([TOID])
REFERENCES [dbo].[Member] ([ID])
GO
ALTER TABLE [dbo].[TOAppointments]  WITH CHECK ADD FOREIGN KEY([AID])
REFERENCES [dbo].[Member] ([ID])
GO
USE [master]
GO
ALTER DATABASE [Football] SET  READ_WRITE 
GO
