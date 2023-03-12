CREATE TABLE `project_descriptions` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `content` varchar(4000) COLLATE utf8mb4_unicode_ci NOT NULL,
                                        `layout_order` int NOT NULL,
                                        `project_id` int NOT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;