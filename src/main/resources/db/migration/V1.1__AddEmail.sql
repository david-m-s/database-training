ALTER TABLE users ADD email varchar(50);

UPDATE users u set u.email=u.username||'@domain.com';