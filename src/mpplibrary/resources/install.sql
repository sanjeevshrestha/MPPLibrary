CREATE  TABLE  IF NOT EXISTS "users" 
("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"firstname" VARCHAR(50) NOT NULL ,
 "lastname" VARCHAR(50) NOT NULL , 
"email" VARCHAR(100), 
"username" VARCHAR(50) NOT NULL ,
"password" VARCHAR(100) NOT NULL ,
 "role" VARCHAR(20), "created" DATETIME,
 "createdby" INTEGER, "modified" DATETIME,
 "modifiedby" INTEGER, "active" BOOL, 
"lastlogin" DATETIME);

CREATE  TABLE  IF NOT EXISTS "users2" 
("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , 
"firstname" VARCHAR(50) NOT NULL ,
 "lastname" VARCHAR(50) NOT NULL , 
"email" VARCHAR(100), 
"username" VARCHAR(50) NOT NULL ,
"password" VARCHAR(100) NOT NULL ,
 "role" VARCHAR(20), "created" DATETIME,
 "createdby" INTEGER, "modified" DATETIME,
 "modifiedby" INTEGER, "active" BOOL, 
"lastlogin" DATETIME);

