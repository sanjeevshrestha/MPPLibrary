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
"lastlogin" DATETIME, "address" VARCHAR(255), "city" VARCHAR(255), "state" VARCHAR(20), "zip" VARCHAR(20), "phone" VARCHAR(20));

INSERT INTO "users" VALUES(1,'Admin','Admin','admin@mpplibrary.edu','admin','password','admin',NULL,NULL,NULL,NULL,'true',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "users" VALUES(2,'Library ','One','library@libsys.com','library1','password','librarian','Sun Dec 06 21:42:07 CST 2015',1,'Sun Dec 06 21:42:07 CST 2015',1,'true',NULL,'1000 North Fourth Street','Fairfield','IA','52557','123-456-7890');
INSERT INTO "users" VALUES(3,'Library','Two','lib2@libsys.com','library2','password','librarian','Sun Dec 06 21:46:51 CST 2015',1,'Sun Dec 06 21:46:51 CST 2015',1,'true',NULL,'1000 Fourth North Street','Fairfield','IA','52557','123-456-7890');
INSERT INTO "users" VALUES(4,'Library ','Three','lib3@libsys.com','library3','password','librarian','Sun Dec 06 21:42:07 CST 2015',1,'Sun Dec 06 21:42:07 CST 2015',1,'true',NULL,'1000 North Fourth Street','Fairfield','IA','52557','123-456-7890');
INSERT INTO "users" VALUES(5,'Library','Four','lib4@libsys.com','library4','password','librarian','Sun Dec 06 21:46:51 CST 2015',1,'Sun Dec 06 21:46:51 CST 2015',1,'true',NULL,'1000 Fourth North Street','Fairfield','IA','52557','123-456-7890');


INSERT INTO "authors" VALUES(1,'K N','King',1,NULL,NULL,'true','The first edition of C Programming: A Modern Approach was popular with students and faculty alike because of its clarity and comprehensiveness as well as its trademark Q&A sections.','The first edition of C Programming: A Modern Approach was popular with students and faculty alike because of its clarity and comprehensiveness as well as its trademark Q&A sections.','','','','','',NULL);
INSERT INTO "authors" VALUES(2,'Robert','Sedgewick',1,NULL,NULL,'true','robert','sedgewick','','','','','',NULL);
INSERT INTO "authors" VALUES(3,'Kevin','Wayne',1,NULL,NULL,'true','Kevin','Wayne the great','','','','','',NULL);
INSERT INTO "authors" VALUES(4,'Martin','Fowler',1,NULL,NULL,'true','','','','','','','',NULL);

INSERT INTO "books" VALUES(1,'Programming in C','false','1111111111',NULL,1,NULL,NULL,'The first edition of C Programming: A Modern Approach was popular with students and faculty alike because of its clarity and comprehensiveness as well as its trademark Q&A sections.',NULL,'Book');
INSERT INTO "books" VALUES(2,'Programming in Java','false','222222222',NULL,1,NULL,NULL,'',NULL,'Book');
INSERT INTO "books" VALUES(3,'Algorithms','false','33333',NULL,1,NULL,NULL,'This book is Part II of the fourth edition of Robert Sedgewick and Kevin Wayne’s Algorithms , the leading textbook on algorithms today, widely used in colleges and universities worldwide. Part II contains Chapters 4 through 6 of the book. The fourth edition of Algorithms surveys the most important computer algorithms currently in use and provides a full treatment of data structures and algorithms for sorting, searching, graph processing, and string processing -- including fifty algorithms every programmer should know. In this edition, new Java implementations are written in an accessible modular programming style, where all of the code is exposed to the reader and ready to use.',NULL,'Book');
INSERT INTO "books" VALUES(4,'UML Distilled','false','444444444',NULL,1,NULL,NULL,'UML Distilled book for quick reference',NULL,'Book');
INSERT INTO "books" VALUES(5,'Times','false','',NULL,1,NULL,NULL,'Times periodicals',NULL,'Periodicals');
INSERT INTO "books" VALUES(6,'Tribune','false','',NULL,1,NULL,NULL,'Tribune',NULL,'Periodicals');
INSERT INTO "books" VALUES(7,'Iowa Chronicles','false','',NULL,1,NULL,NULL,'Iowa Chronicles',NULL,'Periodicals');

INSERT INTO "books_authors" VALUES(1,1);
INSERT INTO "books_authors" VALUES(0,2);
INSERT INTO "books_authors" VALUES(0,3);
INSERT INTO "books_authors" VALUES(0,4);
INSERT INTO "books_authors" VALUES(0,5);
INSERT INTO "books_authors" VALUES(0,6);
INSERT INTO "books_authors" VALUES(0,2);
INSERT INTO "books_authors" VALUES(2,2);
INSERT INTO "books_authors" VALUES(2,3);
INSERT INTO "books_authors" VALUES(0,2);
INSERT INTO "books_authors" VALUES(0,3);
INSERT INTO "books_authors" VALUES(3,2);
INSERT INTO "books_authors" VALUES(3,3);
INSERT INTO "books_authors" VALUES(4,4);



INSERT INTO "members" VALUES(1,'Sanjeev','Shrestha',1,NULL,NULL,'true',0,'','sanjeevshrestha@mum.edu','','123-456-7890','1000 North Fourth Street','Fairfield','IA','52557');
INSERT INTO "members" VALUES(2,'Ajit','Rajthala',1,NULL,NULL,'true',0,'','arajthala@mum.edu','','123-456-789','1000 North Fourth Street','Fairfield','IA','52557');
INSERT INTO "members" VALUES(3,'Anish','Panthi',1,NULL,NULL,'true',0,'','anishpanthi@mum.edu','','123-456-7890','1000 North Fourth Street','Fairfield','IA','52557');
INSERT INTO "members" VALUES(4,'Member','One',1,NULL,NULL,'true',0,'','member1@mum.edu','','123-456-7890','1000 North Fourth Street','Fairfield','IA','52557');


INSERT INTO "lendablecopies" VALUES(1,11110001,1,0);
INSERT INTO "lendablecopies" VALUES(2,11110002,1,1);
INSERT INTO "lendablecopies" VALUES(3,11110003,1,1);
INSERT INTO "lendablecopies" VALUES(4,11110004,1,1);
INSERT INTO "lendablecopies" VALUES(5,11110005,1,1);
INSERT INTO "lendablecopies" VALUES(21,22220001,2,0);
INSERT INTO "lendablecopies" VALUES(22,22220002,2,1);
INSERT INTO "lendablecopies" VALUES(23,22220003,2,1);
INSERT INTO "lendablecopies" VALUES(24,22220004,2,1);
INSERT INTO "lendablecopies" VALUES(25,33330001,3,1);
INSERT INTO "lendablecopies" VALUES(27,44440001,4,1);
INSERT INTO "lendablecopies" VALUES(28,55550001,5,0);
INSERT INTO "lendablecopies" VALUES(29,55550002,5,0);
INSERT INTO "lendablecopies" VALUES(30,66660001,6,1);
INSERT INTO "lendablecopies" VALUES(31,666660002,6,1);
INSERT INTO "lendablecopies" VALUES(32,77770001,7,1);
INSERT INTO "lendablecopies" VALUES(33,77770002,7,1);


INSERT INTO "checkoutrecords" VALUES(14,1,'2015-12-06',2,NULL,NULL,'2015-12-10');
INSERT INTO "checkoutrecords" VALUES(15,2,'2015-12-06',2,NULL,NULL,'2015-12-06');
INSERT INTO "checkoutrecords" VALUES(16,3,'2015-12-06',2,NULL,NULL,'2015-10-01');
INSERT INTO "checkoutrecords" VALUES(17,4,'2015-12-06',2,NULL,NULL,'2015-08-01');


INSERT INTO "recordentries" VALUES(1,14,11110001,NULL,'2015-12-10','2015-12-20',0);
INSERT INTO "recordentries" VALUES(2,14,55550001,NULL,'2015-12-10','2015-12-25',0);
INSERT INTO "recordentries" VALUES(3,15,22220001,NULL,'2015-12-06','2015-12-16',0);
INSERT INTO "recordentries" VALUES(4,15,55550002,NULL,'2015-12-06','2015-12-21',0);
INSERT INTO "recordentries" VALUES(5,16,11110004,NULL,'2015-10-01','2015-10-11',0);
INSERT INTO "recordentries" VALUES(6,16,66660001,NULL,'2015-10-01','2015-10-11',0);
INSERT INTO "recordentries" VALUES(7,17,44440001,NULL,'2015-08-01','2015-08-11',0);
INSERT INTO "recordentries" VALUES(8,17,77770001,NULL,'2015-08-01','2015-08-11',0);
