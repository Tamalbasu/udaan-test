

CREATE TABLE IF NOT EXISTS COVIDUSER (
id INT AUTO_INCREMENT  PRIMARY KEY,
name VARCHAR(50) ,
phone VARCHAR(50) ,
pincode VARCHAR(50) ,
admin VARCHAR(1),
CONSTRAINT ck_admin CHECK(admin in (1,0))
);
CREATE TABLE IF NOT EXISTS COVIDRESULT (
id INT AUTO_INCREMENT  PRIMARY KEY,
userid VARCHAR(50) ,
adminid VARCHAR(50) ,
result VARCHAR(50)
);