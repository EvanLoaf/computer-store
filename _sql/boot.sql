/*
boot.sql file is there to make sure
all the necessary tables and data
are present in our DB on the app start-up
*/
CREATE TABLE IF NOT EXISTS t_role (
  f_id   SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL,
  f_name VARCHAR(20)                      NOT NULL,
  PRIMARY KEY (f_id),
  UNIQUE (f_name)
);

CREATE TABLE IF NOT EXISTS t_permission (
  f_id   SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL,
  f_name VARCHAR(30)                      NOT NULL,
  PRIMARY KEY (f_id),
  UNIQUE (f_name)
);

CREATE TABLE IF NOT EXISTS t_authorization (
  f_role_id       SMALLINT UNSIGNED NOT NULL,
  f_permission_id SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (f_role_id, f_permission_id),
  FOREIGN KEY (f_role_id) REFERENCES t_role (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_permission_id) REFERENCES t_permission (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_discount (
  f_id          SMALLINT UNSIGNED AUTO_INCREMENT           NOT NULL,
  f_name        VARCHAR(20)                                NOT NULL,
  f_percent     TINYINT UNSIGNED                           NOT NULL,
  f_finish_date DATETIME DEFAULT (now() + INTERVAL 14 DAY) NOT NULL,
  PRIMARY KEY (f_id)
);

CREATE TABLE IF NOT EXISTS t_user (
  f_id          SERIAL,
  f_first_name  VARCHAR(25)       NOT NULL,
  f_last_name   VARCHAR(25)       NOT NULL,
  f_email       VARCHAR(30)       NOT NULL,
  f_password    VARCHAR(30)       NOT NULL,
  f_role_id     SMALLINT UNSIGNED NOT NULL,
  f_discount_id SMALLINT UNSIGNED,
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
  PRIMARY KEY (f_id),
  UNIQUE (f_vendor_code),
  CHECK (f_price >= 199.99)
);

CREATE TABLE IF NOT EXISTS t_promotion (
  f_item_id     BIGINT(19) UNSIGNED NOT NULL,
  f_discount_id SMALLINT UNSIGNED   NOT NULL,
  PRIMARY KEY (f_item_id, f_discount_id),
  FOREIGN KEY (f_item_id) REFERENCES t_item (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_discount_id) REFERENCES t_discount (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_order_status (
  f_id   TINYINT UNSIGNED AUTO_INCREMENT NOT NULL,
  f_name VARCHAR(20)                     NOT NULL,
  PRIMARY KEY (f_id),
  UNIQUE (f_name)
);

CREATE TABLE IF NOT EXISTS t_order (
  f_user_id     BIGINT(19) UNSIGNED     NOT NULL,
  f_item_id     BIGINT(19) UNSIGNED     NOT NULL,
  f_order_code  CHAR(36)                NOT NULL,
  f_created     DATETIME DEFAULT NOW()  NOT NULL,
  f_quantity    SMALLINT UNSIGNED       NOT NULL,
  f_total_price DECIMAL(12, 7) UNSIGNED NOT NULL,
  f_status_id   TINYINT UNSIGNED        NOT NULL,
  PRIMARY KEY (f_user_id, f_item_id, f_order_code),
  UNIQUE (f_order_code),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_item_id) REFERENCES t_item (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_status_id) REFERENCES t_order_status (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_feedback (
  f_id      SERIAL,
  f_message VARCHAR(200),
  f_user_id BIGINT(19) UNSIGNED NOT NULL,
  PRIMARY KEY (f_id),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_news (
  f_id      SERIAL,
  f_title   VARCHAR(40)                  NOT NULL,
  f_content VARCHAR(500)                 NOT NULL,
  f_created DATETIME DEFAULT now()       NOT NULL,
  f_user_id BIGINT(19) UNSIGNED          NOT NULL,
  PRIMARY KEY (f_id),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS t_comment (
  f_id      SERIAL,
  f_message VARCHAR(300)           NOT NULL,
  f_created DATETIME DEFAULT now() NOT NULL,
  f_user_id BIGINT(19) UNSIGNED    NOT NULL,
  f_news_id BIGINT(19) UNSIGNED    NOT NULL,
  PRIMARY KEY (f_id),
  FOREIGN KEY (f_user_id) REFERENCES t_user (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT,
  FOREIGN KEY (f_news_id) REFERENCES t_news (f_id)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);

INSERT INTO t_role (f_id, f_name)
VALUES (1, 'admin'),
       (2, 'user')
ON DUPLICATE KEY UPDATE f_id   = VALUES(f_id),
                        f_name = VALUES(f_name);

INSERT INTO t_user (f_id, f_first_name, f_last_name, f_email, f_password, f_role_id)
VALUES (1,
        'admin_name',
        'admin_surname',
        'root@admin.com',
        'root',
        (SELECT f_id FROM t_role WHERE f_name = 'admin')),
       (2,
        'user_name',
        'user_surname',
        'root@user.com',
        'root',
        (SELECT f_id FROM t_role WHERE f_name = 'user'))
ON DUPLICATE KEY UPDATE f_id              = VALUES(f_id),
                        f_first_name      = VALUES(f_first_name),
                        f_last_name       = VALUES(f_last_name),
                        f_email           = VALUES(f_email),
                        f_password        = VALUES(f_password),
                        f_role_id         = VALUES(f_role_id);