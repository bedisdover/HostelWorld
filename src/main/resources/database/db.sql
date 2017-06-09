CREATE TABLE user (
  id       INTEGER      AUTO_INCREMENT,
  cardNum  VARCHAR(7)   NOT NULL,
  password VARCHAR(64) NOT NULL,
  bankCard VARCHAR(30) NOT NULL,
  balance  DOUBLE      NOT NULL DEFAULT 0.0,
  sum      DOUBLE      NOT NULL DEFAULT 0.0,
  rate     ENUM ('白金卡', '金卡', '银卡', '象牙卡') NOT NULL DEFAULT '象牙卡',
  discount DOUBLE NOT NULL DEFAULT 1.0,
  name     VARCHAR(50)                     NOT NULL,
  phoneNum VARCHAR(20)                     NOT NULL,
  active   BOOLEAN                         NOT NULL DEFAULT FALSE,
  validity DATE                            NOT NULL,
  stopDate DATE,
  PRIMARY KEY (id)
)
  DEFAULT CHARACTER SET 'UTF8';

CREATE TABLE manager (
  id INTEGER AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL ,
  password VARCHAR(64) NOT NULL ,
  PRIMARY KEY (id)
)
  DEFAULT CHARACTER SET utf8;

CREATE TABLE hostel (
  id        INTEGER AUTO_INCREMENT,
  hostelID  VARCHAR(7)  NOT NULL,
  name      VARCHAR(50) NOT NULL,
  address   VARCHAR(100) NOT NULL,
  password  VARCHAR(64) NOT NULL,
  active    BOOLEAN NOT NULL DEFAULT FALSE ,
  PRIMARY KEY (id)
)
  DEFAULT CHARACTER SET 'UTF8';

CREATE TABLE application_open (
  id        INTEGER AUTO_INCREMENT,
  hostelID  VARCHAR(7) NOT NULL ,
  pass      ENUM('pass', 'reject', 'waiting') NOT NULL DEFAULT 'waiting',
  notes     VARCHAR(100) ,
  PRIMARY KEY (id)
)
  DEFAULT CHARACTER SET 'UTF8';

CREATE TABLE application_modify (
  id        INTEGER AUTO_INCREMENT,
  hostelID  VARCHAR(7) NOT NULL ,
  name_before VARCHAR(50) NOT NULL ,
  address_before VARCHAR(100) NOT NULL ,
  name_after  VARCHAR(50) NOT NULL ,
  address_after  VARCHAR(100) NOT NULL ,
  pass      ENUM('pass', 'reject', 'waiting') NOT NULL DEFAULT 'waiting',
  notes     VARCHAR(100),
  PRIMARY KEY (id)
)
  DEFAULT CHARACTER SET 'UTF8';

CREATE TABLE plan (
  id        INTEGER AUTO_INCREMENT,
  hostelID  VARCHAR(7) NOT NULL ,
  startDate DATE    NOT NULL ,
  endDate   DATE    NOT NULL ,
  single    INTEGER NOT NULL ,
  normal    INTEGER NOT NULL ,
  singlePrice DOUBLE NOT NULL ,
  normalPrice DOUBLE NOT NULL ,
  PRIMARY KEY (id)
) DEFAULT CHARACTER SET 'UTF8';

CREATE TABLE room_order (
  id INTEGER AUTO_INCREMENT,
  cardNum VARCHAR(7) NOT NULL ,
  hostelID VARCHAR(7) NOT NULL ,
  startDate DATE NOT NULL ,
  endDate DATE NOT NULL ,
  type ENUM('single', 'normal'),
  amount DOUBLE NOT NULL ,
  finish BOOLEAN NOT NULL DEFAULT FALSE ,
  PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;

CREATE TABLE check_in(
  id INTEGER AUTO_INCREMENT,
  cardNum VARCHAR(7),
  hostelID VARCHAR(7) NOT NULL ,
  name VARCHAR(50) ,
  roomID VARCHAR(7) NOT NULL ,
  startDate DATE NOT NULL ,
  endDate DATE,
  type ENUM('single', 'normal'),
  orderID INTEGER,
  payType ENUM('card', 'crash'),
  price DOUBLE NOT NULL ,
  discount DOUBLE ,
  money DOUBLE,
  finish BOOLEAN DEFAULT FALSE ,
  PRIMARY KEY (id)
)
  DEFAULT CHARACTER SET utf8;

CREATE TABLE payment (
  id INTEGER AUTO_INCREMENT,
  hostelID VARCHAR(7) NOT NULL ,
  date DATE NOT NULL ,
  amount DOUBLE NOT NULL DEFAULT 0.0,
  PRIMARY KEY (id)
) DEFAULT CHARACTER SET utf8;
