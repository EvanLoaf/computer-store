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
  f_name VARCHAR(40) NOT NULL,
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
  f_percent     INT UNSIGNED                               NOT NULL,
  f_finish_date DATETIME DEFAULT (NOW() + INTERVAL 14 DAY) NOT NULL,
  PRIMARY KEY (f_id)
);

CREATE TABLE IF NOT EXISTS t_user (
  f_id          SERIAL,
  f_first_name  VARCHAR(25)           NOT NULL,
  f_last_name   VARCHAR(25)           NOT NULL,
  f_email       VARCHAR(30)           NOT NULL,
  f_password    VARCHAR(60)           NOT NULL,
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
       (3, 'sales_admin', FALSE),
       (4, 'user', TRUE)
ON DUPLICATE KEY UPDATE f_id         = VALUES(f_id),
                        f_name       = VALUES(f_name),
                        f_is_default = VALUES(f_is_default);

INSERT INTO t_permission (f_id, f_name)
VALUES (1, 'SECURITY_ADMIN_BASIC_PERMISSION'),
       (2, 'API_ADMIN_BASIC_PERMISSION'),
       (3, 'SALES_ADMIN_BASIC_PERMISSION'),
       (4, 'USER_BASIC_PERMISSION'),
       (11, 'VIEW_ITEMS'),
       (12, 'CREATE_ITEM'),
       (13, 'DELETE_ITEM'),
       (14, 'UPLOAD_ITEMS'),
       (15, 'VIEW_ORDERS_SELF'),
       (16, 'CREATE_ORDER'),
       (17, 'DELETE_ORDER_SELF'),
       (18, 'VIEW_ORDERS_ALL'),
       (19, 'UPDATE_ORDER_STATUS'),
       (20, 'VIEW_USER_SELF'),
       (21, 'UPDATE_USER_SELF'),
       (22, 'VIEW_USERS_ALL'),
       (23, 'UPDATE_USERS_ALL'),
       (24, 'DISABLE_USER'),
       (25, 'DELETE_USER'),
       (26, 'CREATE_USER'),
       (27, 'VIEW_NEWS'),
       (28, 'CREATE_NEWS'),
       (29, 'UPDATE_NEWS'),
       (30, 'DELETE_NEWS'),
       (31, 'CREATE_COMMENT'),
       (32, 'DELETE_COMMENTS'),
       (33, 'UPDATE_DISCOUNT_ITEM'),
       (34, 'UPDATE_DISCOUNT_USERS'),
       (35, 'CREATE_FEEDBACK'),
       (36, 'DELETE_FEEDBACK'),
       (37, 'VIEW_FEEDBACK'),
       (38, 'VIEW_ITEMS_API'),
       (39, 'CREATE_ITEM_API'),
       (40, 'UPDATE_ITEM_API'),
       (41, 'DELETE_ITEM_API'),
       (42, 'MANAGE_BUSINESS_CARD'),
       (43, 'MANAGE_BUSINESS_CARD_API')
ON DUPLICATE KEY UPDATE f_id   = VALUES(f_id),
                        f_name = VALUES(f_name);

INSERT INTO t_authorization (f_role_id, f_permission_id)
VALUES (1, 1),
       (1, 22),
       (1, 23),
       (1, 24),
       (1, 25),
       (1, 26),
       (2, 2),
       (2, 38),
       (2, 39),
       (2, 40),
       (2, 41),
       (2, 43),
       (3, 3),
       (3, 11),
       (3, 27),
       (3, 36),
       (3, 18),
       (3, 19),
       (3, 28),
       (3, 29),
       (3, 30),
       (3, 32),
       (3, 12),
       (3, 13),
       (3, 14),
       (3, 33),
       (3, 34),
       (3, 37),
       (4, 4),
       (4, 11),
       (4, 15),
       (4, 16),
       (4, 17),
       (4, 20),
       (4, 21),
       (4, 35),
       (4, 27),
       (4, 31),
       (4, 42)
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
       (2, 'api', 'admin', 'api@pc.com', 'rootroot', (SELECT f_id FROM t_role WHERE f_name = 'api_admin'), NULL),
       (3, 'sales', 'admin', 'sales@pc.com', 'rootroot', (SELECT f_id FROM t_role WHERE f_name = 'sales_admin'), NULL),
       (4, 'normal', 'user', 'user@pc.com', 'rootroot', (SELECT f_id FROM t_role WHERE f_name = 'user'), NULL)
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
