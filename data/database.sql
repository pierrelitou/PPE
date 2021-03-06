USE findyourspot;


CREATE TABLE Users(mail VARCHAR(255) PRIMARY KEY,
pseudo VARCHAR(30),mdp VARCHAR(255),firsname VARCHAR(30),
lastname VARCHAR(255), dateofbirth DATE, sex CHAR(1),userloc BOOLEAN);

CREATE TABLE Activity(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 nameactivity VARCHAR(255),location VARCHAR(255),description TEXT,dateevent DATETIME,
 proposeby VARCHAR(255) REFERENCES Users(mail) ON DELETE CASCADE ON UPDATE CASCADE,
 localproposer BOOLEAN);

CREATE TABLE Preference(namep VARCHAR(30) PRIMARY KEY);

CREATE TABLE Rate(idactivity INT, mailuser VARCHAR(255),note FLOAT,
 FOREIGN KEY(idactivity) REFERENCES Activity(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(mailuser) REFERENCES Users(mail) ON DELETE CASCADE ON UPDATE CASCADE, 
PRIMARY KEY (idactivity, mailuser));

CREATE TABLE Userpreference(mailuser VARCHAR(255),idpref VARCHAR(30),importance FLOAT,
FOREIGN KEY(mailuser) REFERENCES Users(mail) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(idpref) REFERENCES Preference(namep) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY(mailuser, idpref));

CREATE TABLE Typeactivity(idactivity INT,idpref VARCHAR(30),
FOREIGN KEY(idactivity) REFERENCES Activity(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY(idpref) REFERENCES Preference(namep) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY(idactivity,idpref));

