/*
boot.sql file is there to make sure
all the necessary tables and data
are present in our DB on the app start-up
*/
CREATE TABLE IF NOT EXISTS role (
  id   SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL,
  name VARCHAR(15)                      NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user (
  id              SERIAL,
  first_name      VARCHAR(25)  NOT NULL,
  last_name       VARCHAR(25)  NOT NULL,
  email           VARCHAR(30)  NOT NULL,
  password        VARCHAR(30)  NOT NULL,
  additional_info VARCHAR(100) DEFAULT NULL,
  phone_number    VARCHAR(15)  DEFAULT NULL,
  role_id         SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (email),
  FOREIGN KEY (role_id) REFERENCES role (id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CHECK (email LIKE '%@%')
);

CREATE TABLE IF NOT EXISTS item (
  id          SERIAL,
  name        VARCHAR(50)            NOT NULL,
  vendor_code CHAR(10)               NOT NULL,
  price       DECIMAL(9, 4) UNSIGNED NOT NULL,
  description VARCHAR(100),
  PRIMARY KEY (id),
  UNIQUE (vendor_code),
  CHECK (price >= 199.99)
);

CREATE TABLE IF NOT EXISTS `order` (
  id         SERIAL,
  order_code VARCHAR(36)       NOT NULL,
  user_id    BIGINT UNSIGNED   NOT NULL,
  created    DATETIME DEFAULT NOW(),
  item_id    BIGINT UNSIGNED   NOT NULL,
  quantity   SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (order_code),
  FOREIGN KEY (user_id) REFERENCES user (id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  FOREIGN KEY (item_id) REFERENCES item (id)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);

INSERT INTO role (id, name)
VALUES (1, 'admin'),
       (2, 'user')
ON DUPLICATE KEY UPDATE id   = VALUES(id),
                        name = VALUES(name);

INSERT INTO user (id, first_name, last_name, email, password, additional_info, phone_number, role_id)
VALUES (1,
        'admin_name',
        'admin_surname',
        'root@admin',
        'root',
        'admin_info',
        '+8624-admin',
        (SELECT id FROM role WHERE name = 'admin')),
       (2,
        'user_name',
        'user_surname',
        'root@user',
        'root',
        'user_info',
        '+8624-user',
        (SELECT id FROM role WHERE name = 'user'))
ON DUPLICATE KEY UPDATE id              = VALUES(id),
                        first_name      = VALUES(first_name),
                        last_name       = VALUES(last_name),
                        email           = VALUES(email),
                        password        = VALUES(password),
                        additional_info = VALUES(additional_info),
                        phone_number    = VALUES(phone_number);