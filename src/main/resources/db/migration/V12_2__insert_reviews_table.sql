INSERT INTO `reviews` (`id`, `stars`, `validated`, `created_at`, `show_id`, `updated_at`, `user_id`, `review`)
VALUES
    (1, 5, b'1', NOW(), 1, '2025-01-06 11:00:00', 1, 'Excellent show, highly recommended!'),
    (2, 4, b'1', NOW(), 2, '2025-01-06 12:00:00', 2, 'Good performance, but could improve the sound quality.'),
    (3, 3, b'0', NOW(), 3, '2025-01-06 13:00:00', 1, 'It was okay, but not as great as expected.'),
    (4, 5, b'1', NOW(), 4, '2025-01-06 14:00:00', 2, 'Amazing experience, would definitely go again!');
