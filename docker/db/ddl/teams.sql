CREATE TABLE `teams` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(30) COLLATE utf8mb4_unicode_ci,
    `rank` varchar(30) COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;