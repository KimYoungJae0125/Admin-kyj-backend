CREATE TABLE `experiences` (
    `id` int NOT NULL AUTO_INCREMENT,
    `institution_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
    `start_date` date NOT NULL,
    `end_date` date,
    `type` varchar(30) COLLATE utf8mb4_unicode_ci,
    `team_id` int,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;