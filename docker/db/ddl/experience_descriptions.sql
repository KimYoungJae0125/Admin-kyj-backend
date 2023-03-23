CREATE TABLE `experience_descriptions` (
    `id` int NOT NULL AUTO_INCREMENT,
    `content` varchar(4000) COLLATE utf8mb4_unicode_ci NOT NULL,
    `experience_id` int,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;