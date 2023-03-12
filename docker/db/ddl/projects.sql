CREATE TABLE `projects` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                            `start_date` date NOT NULL,
                            `end_date` date DEFAULT NULL,
                            `layout_order` int NOT NULL,
                            `team_id` int NOT NULL,
                            `organization_id` int NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
