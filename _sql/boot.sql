/*
boot.sql file is there to make sure
all the necessary tables and data
are present in our DB on the app start-up
*/
CREATE TABLE IF NOT EXISTS t_role (
  f_id         SERIAL,
  f_name       VARCHAR(20)           NOT NULL,
  f_is_default BOOLEAN DEFAULT FALSE NOT NULL,
  PRIMARY KEY (f_id),
  UNIQUE (f_name)
);

CREATE TABLE IF NOT EXISTS t_permission (
  f_id   SERIAL,
  f_name VARCHAR(30) NOT NULL,
  PRIMARY KEY (f_id),
  UNIQUE (f_name)
);

CREATE TABLE IF NOT EXISTS t_authorization (
  f_role_id       BIGINT UNSIGNED NOT NULL,
  f_permission_id BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (f_role_id, f_permission_id),
  FOREIGN KEY (f_role_id) REFERENCES t_role (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_permission_id) REFERENCES t_permission (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_discount (
  f_id          SERIAL,
  f_name        VARCHAR(20)                                NOT NULL,
  f_percent     TINYINT UNSIGNED                           NOT NULL,
  f_finish_date DATETIME DEFAULT (NOW() + INTERVAL 14 DAY) NOT NULL,
  PRIMARY KEY (f_id)
);

CREATE TABLE IF NOT EXISTS t_user (
  f_id          SERIAL,
  f_first_name  VARCHAR(25)           NOT NULL,
  f_last_name   VARCHAR(25)           NOT NULL,
  f_email       VARCHAR(30)           NOT NULL,
  f_password    VARCHAR(30)           NOT NULL,
  f_is_disabled BOOLEAN DEFAULT FALSE NOT NULL,
  f_is_deleted  BOOLEAN DEFAULT FALSE NOT NULL,
  f_role_id     BIGINT UNSIGNED       NOT NULL,
  f_discount_id BIGINT UNSIGNED,
  PRIMARY KEY (f_id),
  UNIQUE (f_email),
  FOREIGN KEY (f_role_id) REFERENCES t_role (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_discount_id) REFERENCES t_discount (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  CHECK (f_email LIKE '%@%.%')
);

CREATE TABLE IF NOT EXISTS t_profile (
  f_user_id      BIGINT(19) UNSIGNED NOT NULL,
  f_address      VARCHAR(100),
  f_phone_number VARCHAR(15),
  PRIMARY KEY (f_user_id),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_item (
  f_id          SERIAL,
  f_name        VARCHAR(50)             NOT NULL,
  f_vendor_code CHAR(10)                NOT NULL,
  f_price       DECIMAL(12, 7) UNSIGNED NOT NULL,
  f_description VARCHAR(100),
  f_is_deleted  BOOLEAN DEFAULT FALSE   NOT NULL,
  PRIMARY KEY (f_id),
  UNIQUE (f_vendor_code),
  CHECK (f_price >= 199.99)
);

CREATE TABLE IF NOT EXISTS t_promotion (
  f_item_id     BIGINT(19) UNSIGNED NOT NULL,
  f_discount_id BIGINT UNSIGNED     NOT NULL,
  PRIMARY KEY (f_item_id, f_discount_id),
  FOREIGN KEY (f_item_id) REFERENCES t_item (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_discount_id) REFERENCES t_discount (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_order (
  f_user_id     BIGINT(19) UNSIGNED     NOT NULL,
  f_item_id     BIGINT(19) UNSIGNED     NOT NULL,
  f_order_code  CHAR(36)                NOT NULL,
  f_created     DATETIME DEFAULT NOW()  NOT NULL,
  f_quantity    INT UNSIGNED            NOT NULL,
  f_total_price DECIMAL(12, 7) UNSIGNED NOT NULL,
  f_status      VARCHAR(20)             NOT NULL,
  f_is_deleted  BOOLEAN DEFAULT FALSE   NOT NULL,
  PRIMARY KEY (f_user_id, f_item_id, f_order_code),
  UNIQUE (f_order_code),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_item_id) REFERENCES t_item (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_feedback (
  f_id      SERIAL,
  f_message VARCHAR(200)        NOT NULL,
  f_user_id BIGINT(19) UNSIGNED NOT NULL,
  PRIMARY KEY (f_id),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_news (
  f_id         SERIAL,
  f_title      VARCHAR(40)            NOT NULL,
  f_content    VARCHAR(500)           NOT NULL,
  f_created    DATETIME DEFAULT NOW() NOT NULL,
  f_is_deleted BOOLEAN DEFAULT FALSE  NOT NULL,
  f_user_id    BIGINT(19) UNSIGNED    NOT NULL,
  PRIMARY KEY (f_id),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_comment (
  f_id         SERIAL,
  f_content    VARCHAR(300)           NOT NULL,
  f_created    DATETIME DEFAULT NOW() NOT NULL,
  f_is_deleted BOOLEAN DEFAULT FALSE  NOT NULL,
  f_user_id    BIGINT(19) UNSIGNED    NOT NULL,
  f_news_id    BIGINT(19) UNSIGNED    NOT NULL,
  PRIMARY KEY (f_id),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_news_id) REFERENCES t_news (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_business_card (
  f_id           SERIAL,
  f_title        VARCHAR(20)         NOT NULL,
  f_name         VARCHAR(40)         NOT NULL,
  f_phone_number VARCHAR(20)         NOT NULL,
  f_user_id      BIGINT(19) UNSIGNED NOT NULL,
  PRIMARY KEY (f_id),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

INSERT INTO t_role (f_id, f_name, f_is_default)
VALUES (1, 'security_admin', FALSE),
       (2, 'api_admin', FALSE),
       (2, 'sales_admin', FALSE),
       (2, 'user', TRUE)
ON DUPLICATE KEY UPDATE f_id         = VALUES(f_id),
                        f_name       = VALUES(f_name),
                        f_is_default = VALUES(f_is_default);

INSERT INTO t_permission (f_id, f_name)
VALUES (1, 'SECURITY_ADMIN_BASIC_PERMISSION'),
       (2, 'API_ADMIN_BASIC_PERMISSION'),
       (3, 'SALES_ADMIN_BASIC_PERMISSION'),
       (4, 'USER_BASIC_PERMISSION')
ON DUPLICATE KEY UPDATE f_id   = VALUES(f_id),
                        f_name = VALUES(f_name);

INSERT INTO t_authorization (f_role_id, f_permission_id)
VALUES (1, 2),
       (2, 2),
       (3, 3),
       (4, 4)
ON DUPLICATE KEY UPDATE f_role_id       = VALUES(f_role_id),
                        f_permission_id = VALUES(f_permission_id);

INSERT INTO t_discount (f_id, f_name, f_percent, f_finish_date)
VALUES (1, '10%', 10, NOW() + INTERVAL 14 DAY),
       (2, '20%', 20, NOW() + INTERVAL 14 DAY),
       (3, '30%', 30, NOW() + INTERVAL 14 DAY),
       (4, '40%', 40, NOW() + INTERVAL 14 DAY)
ON DUPLICATE KEY UPDATE f_id          = VALUES(f_id),
                        f_name        = VALUES(f_name),
                        f_percent     = VALUES(f_percent),
                        f_finish_date = VALUES(f_finish_date);

INSERT INTO t_user (f_id, f_first_name, f_last_name, f_email, f_password, f_role_id, f_discount_id)
VALUES (1,
        'security',
        'admin',
        'security@pc.com',
        'rootroot',
        (SELECT f_id FROM t_role WHERE f_name = 'security_admin'),
        NULL),
       (2,
        'api',
        'admin',
        'api@pc.com',
        'rootroot',
        (SELECT f_id FROM t_role WHERE f_name = 'api_admin'),
        NULL),
       (3,
        'sales',
        'admin',
        'sales@pc.com',
        'rootroot',
        (SELECT f_id FROM t_role WHERE f_name = 'sales_admin'),
        NULL),
       (4,
        'normal',
        'user',
        'user@pc.com',
        'rootroot',
        (SELECT f_id FROM t_role WHERE f_name = 'user'),
        NULL)
ON DUPLICATE KEY UPDATE f_id          = VALUES(f_id),
                        f_first_name  = VALUES(f_first_name),
                        f_last_name   = VALUES(f_last_name),
                        f_email       = VALUES(f_email),
                        f_password    = VALUES(f_password),
                        f_role_id     = VALUES(f_role_id),
                        f_discount_id = VALUES(f_discount_id);

INSERT INTO t_profile (f_user_id, f_address, f_phone_number)
VALUES (1, 'sec_address', 'sec_phone'),
       (2, 'api_address', 'api_phone'),
       (3, 'sales_address', 'sales_phone'),
       (4, 'user_address', 'user_phone')
ON DUPLICATE KEY UPDATE f_user_id      = VALUES(f_user_id),
                        f_address      = VALUES(f_address),
                        f_phone_number = VALUES(f_phone_number);

INSERT INTO t_item (f_id, f_name, f_vendor_code, f_price, f_description)
VALUES (1, 'comp1', 'v_code0001', 333.00, 'desc1'),
       (2, 'comp2', 'v_code0002', 555.00, 'desc2'),
       (3, 'comp3', 'v_code0003', 777.00, 'desc3'),
       (4, 'comp4', 'v_code0004', 999.00, 'desc4'),
       (5, 'comp5', 'v_code0005', 1337.00, 'desc5')
ON DUPLICATE KEY UPDATE f_id          = VALUES(f_id),
                        f_name        = VALUES(f_name),
                        f_vendor_code = VALUES(f_vendor_code),
                        f_price       = VALUES(f_price),
                        f_description = VALUES(f_description);

INSERT INTO t_feedback (f_id, f_message, f_user_id)
VALUES (1, 'nice shop', 4),
       (1, 'too expensive', 4)
ON DUPLICATE KEY UPDATE f_id      = VALUES(f_id),
                        f_message = VALUES(f_message),
                        f_user_id = VALUES(f_user_id);

INSERT INTO t_news (f_id, f_title, f_content, f_user_id)
VALUES (1, 'Test article', 'Test article profound content', 3)
ON DUPLICATE KEY UPDATE f_id      = VALUES(f_id),
                        f_title   = VALUES(f_title),
                        f_content = VALUES(f_content),
                        f_user_id = VALUES(f_user_id);

INSERT INTO t_comment (f_id, f_content, f_user_id, f_news_id)
VALUES (1, 'Cheeky test comment', 4, 1)
ON DUPLICATE KEY UPDATE f_id      = VALUES(f_id),
                        f_content = VALUES(f_content),
                        f_user_id = VALUES(f_user_id),
                        f_news_id = VALUES(f_news_id);
