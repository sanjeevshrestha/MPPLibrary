CREATE  TABLE  IF NOT EXISTS "users" 
("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"firstname" VARCHAR(50) NOT NULL ,
 "lastname" VARCHAR(50) NOT NULL , 
"email" VARCHAR(100), 
"username" VARCHAR(50) NOT NULL ,
"password" VARCHAR(100) NOT NULL ,
 "role" VARCHAR(20), 
"created" DATETIME,
 "created_by" INTEGER, 
"modified" DATETIME,
 "modified_by" INTEGER, 
"active" BOOL, 
"lastlogin" DATETIME,
"email" VARCHAR(255), 
"phone" VARCHAR(50), 
"mobile" VARCHAR(50));


CREATE TABLE "members" ("id" INTEGER PRIMARY KEY  NOT NULL ,
"firstname" VARCHAR(50) NOT NULL ,
"lastname" VARCHAR(50) NOT NULL ,
"created_by" INTEGER,
"modified" DATETIME,
"modified_by" INTEGER,
"active" BOOL, 
"amount_due" DOUBLE, 
"note" TEXT, 
"email" VARCHAR(255), 
"phone" VARCHAR(50), 
"mobile" VARCHAR(50));

CREATE TABLE "authors" ("id" INTEGER PRIMARY KEY  NOT NULL ,
"firstname" VARCHAR(50) NOT NULL ,
"lastname" VARCHAR(50) NOT NULL ,
"created_by" INTEGER,
"modified" DATETIME,
"modified_by" INTEGER,
"active" BOOL, 
"credentials" TEXT,
"bio" TEXT,
"note" TEXT,
"email" VARCHAR(255), 
"phone" VARCHAR(50), 
"mobile" VARCHAR(50));


CREATE  TABLE  IF NOT EXISTS "addresses" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"address" VARCHAR(255), 
"city" VARCHAR(255), 
"state" VARCHAR(20), 
"zip" VARCHAR(10), 
"country" VARCHAR(50), 
"created_by" INTEGER, 
"created" DATETIME, 
"modified" DATETIME, 
"modified_by" INTEGER, 
"status" BOOL);


CREATE  TABLE  IF NOT EXISTS "books" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"title" VARCHAR(255), 
"available" BOOL, 
"ISBN" VARCHAR(30), 
"created" DATETIME, 
"created_by" INTEGER, 
"modified" DATETIME, 
"modified_by" INTEGER, 
"description" TEXT, 
"note" TEXT);


CREATE TABLE "checkoutrecords" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"checkout_date" DATETIME, 
"due_date" DATETIME, 
"checkout_by" INTEGER, 
"created" DATETIME, 
"created_by" INTEGER, 
"modified" DATETIME, 
"modified_by" INTEGER);

CREATE  TABLE "recordentry" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"checkout_record_id" INTEGER, 
"book_id" INTEGER, 
"note" TEXT);

