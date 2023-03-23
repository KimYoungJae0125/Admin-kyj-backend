CREATE TABLE `profiles` (
    `id` int NOT NULL AUTO_INCREMENT,
    `korean_name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
    `english_name` varchar(20) NOT NULL,
    `job_name` varchar(30) COLLATE utf8mb4_unicode_ci,
    `site_address` varchar(100) COLLATE utf8mb4_unicode_ci,
    `email_address` varchar(100) COLLATE utf8mb4_unicode_ci,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;