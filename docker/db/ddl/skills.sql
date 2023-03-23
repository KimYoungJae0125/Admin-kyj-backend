CREATE TABLE `skills` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50),
    `layout_order` int,
    `experience_id` int,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;