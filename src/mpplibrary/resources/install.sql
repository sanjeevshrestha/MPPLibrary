CREATE TABLE IF NOT EXISTS "addresses" 
("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"address" VARCHAR(255), 
"city" VARCHAR(255), 
"state" VARCHAR(20),
 "zip" VARCHAR(10), 
"country" VARCHAR(50),
 "createdby" INTEGER, 
"created" DATETIME,
 "modified" DATETIME,
 "modifiedby" INTEGER, 
"status" BOOL);


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
"address" VARCHAR(255), 
"city" VARCHAR(50), 
"state" VARCHAR(10), 
"zip" VARCHAR(10), 
"email" VARCHAR(255));


CREATE TABLE IF NOT EXISTS  "books" 
("id" INTEGER PRIMARY KEY  NOT NULL ,
"title" VARCHAR(255),
"available" BOOL,
"ISBN" VARCHAR(30),
"created" DATETIME,
"created_by" INTEGER DEFAULT (null) ,
"modified" DATETIME,
"modified_by" INTEGER DEFAULT (null) ,
"description" TEXT,
"note" TEXT,
"type" VARCHAR(20));

CREATE TABLE IF NOT EXISTS  "books_authors" ("book_id" INTEGER NOT NULL , "author_id" INTEGER NOT NULL );


CREATE TABLE IF NOT EXISTS  "checkoutrecords" (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	`checkout_by`	INTEGER,
	`created`	DATETIME,
	`created_by`	INTEGER,
	`modified`	DATETIME,
	`modified_by`	INTEGER
, "checkout_date" DATETIME);

CREATE TABLE IF NOT EXISTS  "lendablecopies" ("id" INTEGER PRIMARY KEY  NOT NULL ,
"uniqueid" INTEGER,
"book_id" INTEGER DEFAULT (null) , 
"available" BOOL);


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
"mobile" VARCHAR(50), 
"address" VARCHAR(255),
 "city" VARCHAR(100), 
"state" VARCHAR(10), 
"zip" VARCHAR(20));


CREATE TABLE  IF NOT EXISTS  "recordentries" 
("id" INTEGER PRIMARY KEY  NOT NULL ,
"checkout_record_id" INTEGER,
"lendable_id" INTEGER,
"note" TEXT,
"checkout_date" INTEGER,
"due_date" INTEGER,
"checked_in" BOOL DEFAULT (null) );


CREATE TABLE IF NOT EXISTS  "users" 
("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"firstname" VARCHAR(50) NOT NULL ,
 "lastname" VARCHAR(50) NOT NULL , 
"email" VARCHAR(100), 
"username" VARCHAR(50) NOT NULL ,
"password" VARCHAR(100) NOT NULL ,
 "role" VARCHAR(20), "created" DATETIME,
 "createdby" INTEGER, "modified" DATETIME,
 "modifiedby" INTEGER, "active" BOOL, 
"lastlogin" DATETIME, "address" VARCHAR(255), "city" VARCHAR(255), "state" VARCHAR(20), "zip" VARCHAR(20), "phone" VARCHAR(20))


