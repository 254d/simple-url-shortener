DROP TABLE IF EXISTS url_shortener_data;
CREATE TABLE url_shortener_data
(
   id VARCHAR (64),
   original_url VARCHAR (2048),
   expire VARCHAR (16),
   created_timestamp VARCHAR (32)
);