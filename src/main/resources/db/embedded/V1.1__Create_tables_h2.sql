-- ROLE TABLE --
DROP TABLE IF EXISTS ROLES;
CREATE TABLE ROLES (ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255));

-- ROLE TABLE --
DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
  ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  USERNAME VARCHAR(255),
  PASSWORD VARCHAR(255),
  IS_ACCOUNT_NON_EXPIRED BOOLEAN,
  IS_ACCOUNT_NON_LOCKED BOOLEAN,
  IS_CREDENTIAL_NON_EXPIRED BOOLEAN,
  IS_ENABLED BOOLEAN
  );

-- CATEGORY TABLE --
DROP TABLE IF EXISTS CATEGORY;
CREATE TABLE CATEGORY (ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(255));


-- ROLES_TO_USERS TABLE --
DROP TABLE IF EXISTS ROLES_TO_USERS;

CREATE TABLE ROLES_TO_USERS (
  USER_ID BIGINT NOT NULL,
  ROLE_ID BIGINT NOT NULL
);


-- APPLICATION TABLE --
DROP TABLE IF EXISTS APPLICATION;
CREATE TABLE APPLICATION (
  ID BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  CATEGORY_ID BIGINT,
  APPLICATION_NAME VARCHAR(255),
  DESCRIPTION VARCHAR(255),
  PACKAGE_NAME VARCHAR(255),
  PICTURE_128  VARCHAR(255),
  PICTURE_512 VARCHAR(255),
  DOWNLOADS_QUANTITY BIGINT,
  UPLOAD_DATE DATETIME,
  IMAGE_128 BINARY,
  IMAGE_512 BINARY
);