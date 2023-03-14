create table `certifcates` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(50) Not NULL,
                             `description` varchar(4000) DEFAULT NULL,
                             issuing_authority varchar(100) NOT NULL,
                             `certification_date`  date NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;