floggit-postit MySql


CREATE TABLE 'whiteboards' (
  'id' mediumint(8) unsigned NOT NULL auto_increment,
  'name' varchar(255) NOT NULL,
  PRIMARY KEY ('id')
) AUTO_INCREMENT=1;

CREATE TABLE categories_in_whiteboard (
  'category_id' mediumint(8) NOT NULL,
  'whiteboard_id' mediumint(8) NOT NULL
)

CREATE TABLE categories (
  'id' mediumint(8) unsigned NOT NULL auto_increment,
  'name' varchar(36) NOT NULL,
  PRIMARY KEY (id)
) AUTO_INCREMENT=1;

CREATE TABLE postits (
	'id' mediumint(8) unsigned NOT NULL auto_increment,
	'name' varchar(36) NOT NULL,
	'content' TEXT default NULL,¨
	PRIMARY KEY (id)
) AUTO_INCREMENT=1;

INSERT INTO 'whiteboards' (id,name) VALUES (id,"Sales"),(id,"Economy"),(id,"Development"),(id,"Management"),(id,"Maintanance");

