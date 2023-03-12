CREATE TABLE `organizations` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `description` varchar(4000) COLLATE utf8mb4_unicode_ci NOT NULL,
                                 `start_date` date NOT NULL,
                                 `end_date` date DEFAULT NULL,
                                 `layout_order` int NOT NULL,
                                 `institution_id` int NOT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
