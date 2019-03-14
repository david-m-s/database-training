ALTER TABLE users ADD email varchar(50);

UPDATE users u set u.email=CONCAT(u.username, '@domain.com');
