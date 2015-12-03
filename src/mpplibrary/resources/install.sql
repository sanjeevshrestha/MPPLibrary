CREATE TABLE IF NOT EXISTS "users" 
("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"firstname" VARCHAR(50) NOT NULL ,
 "lastname" VARCHAR(50) NOT NULL , 
"email" VARCHAR(100), 
"username" VARCHAR(50) NOT NULL ,
"password" VARCHAR(100) NOT NULL ,
 "role" VARCHAR(20), "created" DATETIME,
 "createdby" INTEGER, "modified" DATETIME,
 "modifiedby" INTEGER, "active" BOOL, 
"lastlogin" DATETIME,
"email" VARCHAR(255), 
"phone" VARCHAR(50), 
"mobile" VARCHAR(50));

INSERT INTO "users" VALUES(1,'Admin','Admin','admin@mpplibrary.edu','admin','password','admin',NULL,NULL,NULL,NULL,'true',NULL);
INSERT INTO "users" VALUES(2,'Librarian','Librarian','libary@mpplibrary.edu','library','password','librarian',NULL,NULL,NULL,NULL,'true',NULL);



CREATE TABLE IF NOT EXISTS  "members" 
("id" INTEGER PRIMARY KEY  NOT NULL ,
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

CREATE TABLE IF NOT EXISTS  "authors" ("id" INTEGER PRIMARY KEY  NOT NULL ,
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


CREATE  TABLE  IF NOT EXISTS "addresses" 
("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
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


CREATE  TABLE  IF NOT EXISTS "books" 
("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"title" VARCHAR(255), 
"available" BOOL, 
"ISBN" VARCHAR(30), 
"created" DATETIME, 
"created_by" INTEGER, 
"modified" DATETIME, 
"modified_by" INTEGER, 
"description" TEXT, 
"note" TEXT);


CREATE TABLE IF NOT EXISTS "checkoutrecords" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`checkout_by`	INTEGER,
	`created`	DATETIME,
	`created_by`	INTEGER,
	`modified`	DATETIME,
	`modified_by`	INTEGER
);

CREATE TABLE IF NOT EXISTS "recordentry" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`checkout_record_id`	INTEGER,
	`lendable_id`	INTEGER,
	`note`	TEXT,
	`checkout_date`	INTEGER,
	`due_date`	INTEGER
);


CREATE  TABLE  IF NOT EXISTS "fines" 
("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"member_id" INTEGER, 
"fine_date" DATETIME, 
"paid_date" DATETIME, 
"created" DATETIME, 
"created_by" INTEGER, 
"modified" DATETIME, 
"modified_by" INTEGER);

CREATE  TABLE IF NOT EXISTS  "books_authors" 
("book_id" INTEGER NOT NULL , 
"author_id" INTEGER NOT NULL );

CREATE  TABLE "lendablecopies" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"isbn" VARCHAR(50), 
"uniqueid" VARCHAR(50), 
"available" BOOL);
