CREATE TABLE `skills` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
                          `layout_order` int NOT NULL,
                          `project_id` int NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
