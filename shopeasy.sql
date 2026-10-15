CREATE DATABASE  IF NOT EXISTS `shopeasy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `shopeasy`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shopeasy
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `accountID` int NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `paymentinfo` varchar(16) DEFAULT NULL,
  `shippingaddress` varchar(255) DEFAULT NULL,
  `employeestatus` tinyint(1) DEFAULT NULL,
  `employeeID` int DEFAULT NULL,
  `notification_preference` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`accountID`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `employeeID` (`employeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'maria.gonzalez@store.com','Maria','Gonzalez','4539123487651234','101 Market St, Miami, FL',1,1001,1),(2,'james.hart@store.com','James','Hart','4920113478123490','88 Coral Way, Miami, FL',1,1002,1),(3,'sophia.wright@store.com','Sophia','Wright','4023987654321123','220 Palm Ave, Kendall, FL',1,1003,0),(4,'david.cho@store.com','David','Cho','4485123498762345','55 Sunset Dr, Miami, FL',1,1004,1),(5,'emily.stone@store.com','Emily','Stone','4712349087123456','300 Ocean Blvd, Miami Beach, FL',1,1005,0),(6,'ethan.brooks@email.com','Ethan','Brooks','4920113478123491','123 Oak Street, Orlando, FL',0,NULL,1),(7,'sophia.ramirez@email.com','Sophia','Ramirez','4539123487651235','758 Pine Avenue, Tampa, FL',0,NULL,1),(8,'liam.carter@email.com','Liam','Carter','4023987654321124','321 Maple Drive, Jacksonville, FL',0,NULL,0),(9,'ava.bennett@email.com','Ava','Bennett','4485123498762346','556 Cedar Lane, Miami, FL',0,NULL,1),(10,'noah.jenkins@email.com','Noah','Jenkins','4712349087123457','44 Riverbend Rd, Orlando, FL',0,NULL,1),(11,'olivia.martin@email.com','Olivia','Martin','4539123487651236','900 Lakeside Dr, Tampa, FL',0,NULL,0),(12,'william.scott@email.com','William','Scott','4920113478123492','12 Cypress Ct, Miami, FL',0,NULL,1),(13,'isabella.hughes@email.com','Isabella','Hughes','4023987654321125','77 Palm Grove Rd, Orlando, FL',0,NULL,1),(14,'jacob.miller@email.com','Jacob','Miller','4485123498762347','210 Sunset Blvd, Miami Beach, FL',0,NULL,0),(15,'mia.foster@email.com','Mia','Foster','4712349087123458','65 Ocean Breeze Ave, Tampa, FL',0,NULL,1),(16,'logan.price@email.com','Logan','Price','4539123487651237','500 Pine Ridge Rd, Jacksonville, FL',0,NULL,1),(17,'amelia.turner@email.com','Amelia','Turner','4920113478123493','88 Harbor Point Dr, Miami, FL',0,NULL,0),(18,'lucas.henderson@email.com','Lucas','Henderson','4023987654321126','33 Magnolia St, Orlando, FL',0,NULL,1),(19,'charlotte.king@email.com','Charlotte','King','4485123498762348','120 Bayfront Dr, Tampa, FL',0,NULL,1),(20,'henry.watson@email.com','Henry','Watson','4712349087123459','19 Coral Reef Rd, Miami, FL',0,NULL,0),(21,'ella.murphy@email.com','Ella','Murphy','4539123487651238','700 Willow Creek Rd, Orlando, FL',0,NULL,1),(22,'sebastian.ward@email.com','Sebastian','Ward','4920113478123494','45 Riverwalk Ln, Tampa, FL',0,NULL,1),(23,'grace.hamilton@email.com','Grace','Hamilton','4023987654321127','88 Sunrise Blvd, Miami Beach, FL',0,NULL,0),(24,'jackson.bryant@email.com','Jackson','Bryant','4485123498762349','210 Pine Terrace, Jacksonville, FL',0,NULL,1),(25,'harper.davis@email.com','Harper','Davis','4712349087123460','33 Ocean Crest Dr, Miami, FL',0,NULL,1),(26,'daniel.reed@email.com','Daniel','Reed','4539123487651239','500 Seaside Ave, Tampa, FL',0,NULL,0),(27,'victoria.clark@email.com','Victoria','Clark','4920113478123495','77 Palm Ridge Ln, Orlando, FL',0,NULL,1),(28,'mason.holt@email.com','Mason','Holt','4023987654321128','12 Coral Springs Rd, Miami, FL',0,NULL,1),(29,'natalie.owens@email.com','Natalie','Owens','4485123498762350','99 Harborview Dr, Jacksonville, FL',0,NULL,0),(30,'wyatt.barnes@email.com','Wyatt','Barnes','4712349087123461','66 Sunset Cove Rd, Tampa, FL',0,NULL,1);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_items`
--

DROP TABLE IF EXISTS `cart_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_items` (
  `cartItemID` int NOT NULL AUTO_INCREMENT,
  `cartID` int NOT NULL,
  `productID` int NOT NULL,
  `quantity` int DEFAULT '1',
  PRIMARY KEY (`cartItemID`),
  KEY `cartID` (`cartID`),
  KEY `productID` (`productID`),
  CONSTRAINT `cart_items_ibfk_1` FOREIGN KEY (`cartID`) REFERENCES `shopping_cart` (`cartID`),
  CONSTRAINT `cart_items_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_items`
--

LOCK TABLES `cart_items` WRITE;
/*!40000 ALTER TABLE `cart_items` DISABLE KEYS */;
INSERT INTO `cart_items` VALUES (61,26,5,1),(62,26,12,2),(63,27,8,1),(64,27,33,1),(65,28,14,1),(66,28,27,3),(67,29,2,1),(68,29,45,1),(69,30,19,2),(70,30,41,1),(71,1,7,1),(72,1,22,2),(73,2,11,1),(74,2,38,1),(75,3,3,1),(76,3,29,2),(77,4,17,1),(78,4,44,1),(79,5,9,1),(80,5,36,2),(81,6,6,1),(82,6,25,1),(83,7,13,2),(84,7,48,1),(85,8,4,1),(86,8,32,1),(87,9,10,1),(88,9,21,2),(89,10,18,1),(90,10,40,1),(91,11,1,1),(92,11,30,2),(93,12,15,1),(94,12,47,1),(95,13,20,1),(96,13,35,1),(97,14,23,2),(98,14,50,1),(99,15,28,1),(100,15,52,1),(101,16,16,1),(102,16,39,2),(103,17,24,1),(104,17,53,1),(105,18,26,1),(106,18,42,1),(107,19,31,1),(108,19,55,1),(109,20,34,1),(110,20,49,2),(111,21,37,1),(112,21,43,1),(113,22,46,1),(114,22,51,1),(115,23,54,1),(117,23,51,2),(118,24,21,1),(119,25,23,2);
/*!40000 ALTER TABLE `cart_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount_codes`
--

DROP TABLE IF EXISTS `discount_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount_codes` (
  `codeID` int NOT NULL AUTO_INCREMENT,
  `codename` varchar(20) DEFAULT NULL,
  `discount` decimal(2,2) NOT NULL DEFAULT '0.00',
  `expiration_date` date DEFAULT NULL,
  PRIMARY KEY (`codeID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_codes`
--

LOCK TABLES `discount_codes` WRITE;
/*!40000 ALTER TABLE `discount_codes` DISABLE KEYS */;
INSERT INTO `discount_codes` VALUES (1,'STYLEWITHAVA',0.15,'2027-01-15'),(2,'JORDANFIT20',0.20,'2027-02-01'),(3,'MIAFASHION10',0.10,'2027-03-10'),(4,'TRENDINGTOM',0.15,'2027-04-05'),(5,'EMILYSTYLE25',0.25,'2027-05-01'),(6,'SHOPWITHLILY',0.12,'2027-01-30'),(7,'FITBYNOAH',0.18,'2027-02-20'),(8,'GRACELOOKS15',0.15,'2027-03-25'),(9,'LOGANLIFESTYLE',0.20,'2027-04-15'),(10,'HARPERWEAR10',0.10,'2027-05-20'),(11,'FASHIONBYELLA',0.22,'2027-06-01'),(12,'DRESSWITHJACK',0.15,'2027-06-15'),(13,'CHICCHARLOTTE',0.20,'2027-07-01'),(14,'SEBSTYLES12',0.12,'2027-07-20'),(15,'NATALIETRENDS',0.18,'2027-08-05'),(16,'WYATTFITNESS',0.15,'2027-08-25'),(17,'VICTORIAWEAR',0.10,'2027-09-10'),(18,'MASONLOOKS20',0.20,'2027-09-30'),(19,'AMELIAFASHION',0.25,'2027-10-10'),(20,'HENRYSTYLE15',0.15,'2027-10-31');
/*!40000 ALTER TABLE `discount_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `productname` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` decimal(8,2) NOT NULL DEFAULT '0.00',
  `availability` tinyint(1) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Cotton Sweatshirt','Blue sweatshirt made of 100% cotton. Available in sizes XS-XXL',32.99,1,80,'Men\'s Sweaters'),(2,'Dark-wash Jeans Boot-Cut','Boot-cut, dark wash jeans. Available in waist sizes 24-46',29.99,1,120,'Men\'s Bottoms'),(3,'Slim-Fit Polo Shirt','Soft cotton polo shirt available in sizes S-XXL',24.99,1,100,'Men\'s Tops'),(4,'Classic White Tee','Crew-neck white t-shirt made from breathable cotton',12.99,1,200,'Men\'s Tops'),(5,'Flannel Plaid Shirt','Long-sleeve flannel shirt in red/black plaid',34.99,1,75,'Men\'s Tops'),(6,'Henley Long Sleeve','Three-button henley shirt made from soft knit fabric',22.99,1,120,'Men\'s Tops'),(7,'Oxford Dress Shirt','Button-down oxford shirt ideal for business casual wear',39.99,1,90,'Men\'s Tops'),(8,'Graphic Tee – Mountain Print','Cotton tee featuring a minimalist mountain graphic',17.99,1,140,'Men\'s Tops'),(9,'Khaki Chino Pants','Slim-fit khaki chinos available in waist sizes 28-42',39.99,1,90,'Men\'s Bottoms'),(10,'Athletic Joggers','Lightweight joggers with tapered legs and zip pockets',29.99,1,110,'Men\'s Bottoms'),(11,'Straight-Leg Jeans','Classic straight-leg denim jeans in medium wash',44.99,1,80,'Men\'s Bottoms'),(12,'Cargo Pants','Utility cargo pants with multiple pockets',49.99,1,70,'Men\'s Bottoms'),(13,'Slim-Fit Dress Pants','Formal dress pants suitable for office or events',59.99,1,60,'Men\'s Bottoms'),(14,'Athletic Shorts','Moisture-wicking shorts ideal for training',19.99,1,150,'Men\'s Bottoms'),(15,'Denim Jacket','Classic blue denim jacket with button closure',59.99,1,60,'Men\'s Outerwear'),(16,'Black Bomber Jacket','Nylon bomber jacket with ribbed cuffs and hem',69.99,1,50,'Men\'s Outerwear'),(17,'Wool Overcoat','Formal wool overcoat for cold weather',129.99,1,40,'Men\'s Outerwear'),(18,'Hooded Windbreaker','Lightweight windbreaker with adjustable hood',49.99,1,85,'Men\'s Outerwear'),(19,'Puffer Vest','Insulated puffer vest for layering',54.99,1,70,'Men\'s Outerwear'),(20,'Fleece Zip-Up Jacket','Warm fleece jacket with full zipper',39.99,1,100,'Men\'s Outerwear'),(21,'Ribbed Crop Top','Stretchy ribbed crop top available in sizes XS-L',14.99,1,150,'Women’s Tops'),(22,'Oversized Knit Sweater','Soft knit sweater with relaxed fit',39.99,1,80,'Women’s Tops'),(23,'Silk Blouse','Lightweight silk blouse with button front',49.99,1,60,'Women’s Tops'),(24,'V-Neck Tee','Lightweight v-neck t-shirt available in multiple colors',12.99,1,180,'Women’s Tops'),(25,'Button-Up Cardigan','Classic button-up cardigan made from soft knit',29.99,1,100,'Women’s Tops'),(26,'Lace Trim Camisole','Elegant camisole with lace trim details',19.99,1,130,'Women’s Tops'),(27,'High-Waisted Leggings','Black leggings with high-rise waistband',22.99,1,180,'Women’s Bottoms'),(28,'A-Line Skirt','Flowy A-line skirt available in multiple colors',29.99,1,90,'Women’s Bottoms'),(29,'Distressed Skinny Jeans','Skinny-fit jeans with distressed detailing',44.99,1,70,'Women’s Bottoms'),(30,'Wide-Leg Trousers','High-waisted wide-leg trousers for a modern look',49.99,1,65,'Women’s Bottoms'),(31,'Athletic Yoga Pants','Stretchy yoga pants with breathable fabric',27.99,1,160,'Women’s Bottoms'),(32,'Midi Pencil Skirt','Form-fitting pencil skirt ideal for office wear',34.99,1,75,'Women’s Bottoms'),(33,'Faux Leather Jacket','Black faux leather moto jacket',69.99,1,55,'Women’s Outerwear'),(34,'Long Cardigan','Open-front cardigan made from soft knit fabric',34.99,1,95,'Women’s Outerwear'),(35,'Trench Coat','Classic beige trench coat with belt',89.99,1,50,'Women’s Outerwear'),(36,'Puffer Jacket','Insulated puffer jacket with hood',99.99,1,45,'Women’s Outerwear'),(37,'Sherpa-Lined Hoodie','Warm hoodie with sherpa lining',49.99,1,85,'Women’s Outerwear'),(38,'Denim Overshirt','Relaxed-fit denim overshirt for layering',44.99,1,70,'Women’s Outerwear'),(39,'Running Sneakers','Lightweight running shoes with breathable mesh',59.99,1,120,'Shoes'),(40,'Canvas Slip-On Shoes','Casual slip-on shoes with rubber sole',29.99,1,140,'Shoes'),(41,'Leather Dress Shoes','Formal leather shoes with cushioned insole',79.99,1,60,'Shoes'),(42,'Women’s Ballet Flats','Comfortable flats with cushioned footbed',34.99,1,110,'Shoes'),(43,'Women’s Ankle Boots','Stylish ankle boots with low heel',69.99,1,75,'Shoes'),(44,'High-Top Sneakers','Retro-style high-top sneakers',54.99,1,95,'Shoes'),(45,'Baseball Cap','Adjustable cotton baseball cap',14.99,1,200,'Accessories'),(46,'Leather Belt','Genuine leather belt with metal buckle',24.99,1,150,'Accessories'),(47,'Aviator Sunglasses','Metal frame aviator sunglasses with UV protection',19.99,1,130,'Accessories'),(48,'Beanie Hat','Soft knit beanie available in multiple colors',12.99,1,180,'Accessories'),(49,'Crossbody Bag','Compact crossbody bag with adjustable strap',29.99,1,90,'Accessories'),(50,'Wool Scarf','Soft wool scarf available in multiple colors',17.99,1,160,'Accessories'),(51,'Compression Shirt','Moisture-wicking compression top',27.99,1,100,'Activewear'),(52,'Training Shorts','Lightweight shorts with elastic waistband',21.99,1,140,'Activewear'),(53,'Sports Bra','Medium-support sports bra with breathable fabric',24.99,1,160,'Activewear'),(54,'Performance Hoodie','Athletic hoodie designed for workouts',39.99,1,110,'Activewear'),(55,'Running Leggings','High-stretch leggings for running and training',29.99,1,150,'Activewear'),(56,'Mesh Tank Top','Breathable mesh tank ideal for gym sessions',16.99,1,170,'Activewear');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `cartID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cartID`),
  UNIQUE KEY `accountID` (`accountID`),
  CONSTRAINT `shopping_cart_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
INSERT INTO `shopping_cart` VALUES (1,6,'2026-10-15 23:24:54'),(2,7,'2026-10-15 23:24:54'),(3,8,'2026-10-15 23:24:54'),(4,9,'2026-10-15 23:24:54'),(5,10,'2026-10-15 23:24:54'),(6,11,'2026-10-15 23:24:54'),(7,12,'2026-10-15 23:24:54'),(8,13,'2026-10-15 23:24:54'),(9,14,'2026-10-15 23:24:54'),(10,15,'2026-10-15 23:24:54'),(11,16,'2026-10-15 23:24:54'),(12,17,'2026-10-15 23:24:54'),(13,18,'2026-10-15 23:24:54'),(14,19,'2026-10-15 23:24:54'),(15,20,'2026-10-15 23:24:54'),(16,21,'2026-10-15 23:24:54'),(17,22,'2026-10-15 23:24:54'),(18,23,'2026-10-15 23:24:54'),(19,24,'2026-10-15 23:24:54'),(20,25,'2026-10-15 23:24:54'),(21,26,'2026-10-15 23:24:54'),(22,27,'2026-10-15 23:24:54'),(23,28,'2026-10-15 23:24:54'),(24,29,'2026-10-15 23:24:54'),(25,30,'2026-10-15 23:24:54'),(26,1,'2026-10-15 23:24:54'),(27,2,'2026-10-15 23:24:54'),(28,3,'2026-10-15 23:24:54'),(29,4,'2026-10-15 23:24:54'),(30,5,'2026-10-15 23:24:54');
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `wishlistID` int NOT NULL AUTO_INCREMENT,
  `accountID` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`wishlistID`),
  UNIQUE KEY `accountID` (`accountID`),
  CONSTRAINT `wishlist_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `accounts` (`accountID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,6,'2026-10-15 23:25:10'),(2,7,'2026-10-15 23:25:10'),(3,8,'2026-10-15 23:25:10'),(4,9,'2026-10-15 23:25:10'),(5,10,'2026-10-15 23:25:10'),(6,11,'2026-10-15 23:25:10'),(7,12,'2026-10-15 23:25:10'),(8,13,'2026-10-15 23:25:10'),(9,14,'2026-10-15 23:25:10'),(10,15,'2026-10-15 23:25:10'),(11,16,'2026-10-15 23:25:10'),(12,17,'2026-10-15 23:25:10'),(13,18,'2026-10-15 23:25:10'),(14,19,'2026-10-15 23:25:10'),(15,20,'2026-10-15 23:25:10'),(16,21,'2026-10-15 23:25:10'),(17,22,'2026-10-15 23:25:10'),(18,23,'2026-10-15 23:25:10'),(19,24,'2026-10-15 23:25:10'),(20,25,'2026-10-15 23:25:10'),(21,26,'2026-10-15 23:25:10'),(22,27,'2026-10-15 23:25:10'),(23,28,'2026-10-15 23:25:10'),(24,29,'2026-10-15 23:25:10'),(25,30,'2026-10-15 23:25:10'),(26,1,'2026-10-15 23:25:10'),(27,2,'2026-10-15 23:25:10'),(28,3,'2026-10-15 23:25:10'),(29,4,'2026-10-15 23:25:10'),(30,5,'2026-10-15 23:25:10');
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist_items`
--

DROP TABLE IF EXISTS `wishlist_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist_items` (
  `wishlistItemID` int NOT NULL AUTO_INCREMENT,
  `wishlistID` int NOT NULL,
  `productID` int NOT NULL,
  PRIMARY KEY (`wishlistItemID`),
  KEY `wishlistID` (`wishlistID`),
  KEY `productID` (`productID`),
  CONSTRAINT `wishlist_items_ibfk_1` FOREIGN KEY (`wishlistID`) REFERENCES `wishlist` (`wishlistID`),
  CONSTRAINT `wishlist_items_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist_items`
--

LOCK TABLES `wishlist_items` WRITE;
/*!40000 ALTER TABLE `wishlist_items` DISABLE KEYS */;
INSERT INTO `wishlist_items` VALUES (1,26,12),(2,26,33),(3,26,45),(4,27,8),(5,27,19),(6,27,41),(7,28,27),(8,28,14),(9,28,5),(10,29,2),(11,29,36),(12,29,48),(13,30,7),(14,30,22),(15,30,29),(16,1,11),(17,1,38),(18,1,44),(19,2,3),(20,2,17),(21,2,40),(22,3,9),(23,3,25),(24,3,47),(25,4,4),(26,4,21),(27,4,35),(28,5,18),(29,5,30),(30,5,50),(31,6,15),(32,6,39),(33,6,53),(34,7,24),(35,7,42),(36,7,55),(37,8,26),(38,8,49),(39,8,43),(40,9,31),(41,9,51),(42,9,56),(43,10,34),(44,10,46),(45,10,52),(46,11,37),(47,11,45),(48,11,28),(49,12,54),(50,12,32),(51,12,10),(52,13,23),(53,13,41),(54,13,12),(55,14,33),(56,14,7),(57,14,19),(58,15,48),(59,15,14),(60,15,27),(61,16,45),(62,16,29),(63,16,38),(64,17,44),(65,17,17),(66,17,3),(67,18,40),(68,18,36),(69,18,25),(70,19,9),(71,19,47),(72,19,21),(73,20,4),(74,20,35),(75,20,30),(76,21,18),(77,21,50),(78,21,39),(79,22,15),(80,22,53),(81,22,42),(82,23,24),(83,23,55),(84,23,49),(85,24,26),(86,24,34),(87,24,46),(88,25,52),(89,25,37),(90,25,28);
/*!40000 ALTER TABLE `wishlist_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-15 19:33:56
