CREATE TABLE IF NOT EXISTS reviews (
                                       id BIGINT NOT NULL AUTO_INCREMENT,
                                       stars TINYINT,
                                       validated BOOLEAN,
                                       created_at TIMESTAMP NULL DEFAULT NULL,
                                       updated_at TIMESTAMP NULL DEFAULT NULL,
                                       review TEXT,
                                       show_id BIGINT NOT NULL,
                                       user_id BIGINT NOT NULL,
                                       PRIMARY KEY (id),
                                       FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
                                       FOREIGN KEY (show_id) REFERENCES shows (id) ON UPDATE CASCADE ON DELETE CASCADE
);
