DROP TABLE IF EXISTS "members";
CREATE TABLE "members" ("id" INTEGER PRIMARY KEY  NOT NULL ,"firstname" VARCHAR(50) NOT NULL ,"lastname" VARCHAR(50) NOT NULL ,"created_by" INTEGER,"modified" DATETIME,"modified_by" INTEGER,"active" BOOL, "amount_due" DOUBLE, "note" TEXT, "email" VARCHAR(255), "phone" VARCHAR(50), "mobile" VARCHAR(50), "address" VARCHAR(255), "city" VARCHAR(100), "state" VARCHAR(10), "zip" VARCHAR(20));
INSERT INTO "members" VALUES(2,'Ajit','Rajthala',NULL,NULL,NULL,'true',NULL,NULL,'aztirb@mum.edu','',NULL,NULL,NULL,NULL,NULL);
INSERT INTO "members" VALUES(3,'Sanjeev','Shrestha',NULL,NULL,NULL,'true',NULL,NULL,'sanjeevshrestha@mum.edu',NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO "members" VALUES(4,'Anish','Panthi',NULL,NULL,NULL,'true',NULL,NULL,'anish.panthi@gmail.com',NULL,NULL,NULL,NULL,NULL,NULL);
