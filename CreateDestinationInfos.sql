CREATE DATABASE `ScheduleTour` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `tblDestination` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblScheduledDestination` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ScheduledDestinationID` int NOT NULL,
  `TourID` int NOT NULL,
  `StartDate` varchar(45) NOT NULL,
  `EndDate` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `TourID_idx` (`TourID`),
  KEY `ScheduledDestinationID_idx` (`ScheduledDestinationID`),
  CONSTRAINT `ScheduledDestinationID` FOREIGN KEY (`ScheduledDestinationID`) REFERENCES `tblDestination` (`ID`),
  CONSTRAINT `TourID` FOREIGN KEY (`TourID`) REFERENCES `tblTour` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblScheduledService` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ScheduledDesID` int NOT NULL,
  `ServicePriceID` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ScheduledDesID_idx` (`ScheduledDesID`),
  KEY `ServicePriceID_idx` (`ServicePriceID`),
  CONSTRAINT `ScheduledDesID` FOREIGN KEY (`ScheduledDesID`) REFERENCES `tblScheduledDestination` (`ID`),
  CONSTRAINT `ServicePriceID` FOREIGN KEY (`ServicePriceID`) REFERENCES `tblServicePrice` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblService` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblServiceOfDestination` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDService` int DEFAULT NULL,
  `IDDestination` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ServiceID_idx` (`IDService`),
  KEY `DestinationID_idx` (`IDDestination`),
  CONSTRAINT `IDDestination` FOREIGN KEY (`IDDestination`) REFERENCES `tblDestination` (`ID`),
  CONSTRAINT `IDService` FOREIGN KEY (`IDService`) REFERENCES `tblService` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblServicePrice` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ServiceProviderID` int NOT NULL,
  `ServiceID` int NOT NULL,
  `ServicePrice` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ServiceProviderID_idx` (`ServiceProviderID`),
  KEY `ServiceID_idx` (`ServiceID`),
  CONSTRAINT `ServiceID` FOREIGN KEY (`ServiceID`) REFERENCES `tblService` (`ID`),
  CONSTRAINT `ServiceProviderID` FOREIGN KEY (`ServiceProviderID`) REFERENCES `tblServiceProvider` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblServiceProvider` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblTour` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(1000) DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Name_UNIQUE` (`Name`),
  KEY `UserID_idx` (`UserID`),
  CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `tblUser` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tblUser` (
  `ID` int NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  `fullname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into tblDestination (name,location)
values ("DaNang","Vietnam"),("HaNoi","Vietnam"),("NhaTrang","Vietnam"),("HaiPhong","Vietnam"),("StPetersbourg","Russia");

insert into tblService (name,type,destinationID)
values ("TourBusDN","Bus",1),("Flight HN-DN","Flight",1),("Hotel DaNang","Hotel",1),("BaNaHills","TouristAttraction",1),
		("TourBusHN","Bus",2),("Flight HCM-HN","Flight",2),("Hotel Hanoi","Hotel",2),("VanMieu","TouristAttraction",2),
		("Flight HN-NT","Flight",3),("HotelNhaTrang","Hotel",3),("VinWonders","TouristAttraction",3),
		("TourBusHP","Bus",4),("HotelHP","Hotel",4),("CatBaForest","TouristAttraction",4),
        ("Flight HN-MO","Flight",5),("Hotel SPTB","Hotel",5),("KremlinPalace","TouristAttraction",5)
;


INSERT INTO tblServiceProvider (name, email, telephone)
VALUES
    ('Vingroup', 'vingroup@gmail.com', '0988555666'),
    ('Sungroup', 'sungroup@gmail.com', '0999888777'),
    ('Jetstar', 'jetstar@gmail.com', '0999555333'),
    ('BambooAirways', 'bamboo@gmail.com', '0999333777'),
    ('VietnamAirlines','vairlines@gmail.com','0993564385'),
    ('HoaHongHotel', 'hhhotel@gmail.com', '0947362784'),
    ('PearlHotel', 'pearlhotel@gmail.com', '0967887568'),
    ('HelenCatBaHotel','hcbh@gmail.com','0987687655'),
    ('VanMieuQTG','vmqtg@gmail.com','0456567567'),
    ('HanoiOldTimes','hnoldtimes@gmail.com','0876876876'),
    ('VoyageTravels','voyagestravels@gmail.com','04945687446'),
    ('HanoiTravels', 'hntravles@gmail.com', '0969999999'),
    ('HaiPhongTravels','hptravels@gmail.com','0333444555'),
    ('DaNangBus','danangbus@gmail.com','0777666555')
;

insert into tblServicePrice (ServiceProviderID,ServiceID,ServicePrice)
values 
(14,1,800000),
(3,2,1200000),
(6,3,800000),
(7,3,700000),
(2,4,500000),
(12,5,200000),
(3,6,1500000),
(4,6,1400000),
(5,6,1300000),
(10,7,600000),
(9,8,50000),
(3,9,800000),
(4,9,900000),
(5,9,700000),
(6,10,900000),
(7,10,1000000),
(1,11,800000),
(13,12,90000),
(8,13,300000),
(13,14,400000),
(3,15,3000000),
(7,16,2000000),
(11,17,500000)
;
