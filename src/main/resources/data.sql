INSERT INTO LAB_WORK VALUES (35, 1);
INSERT INTO LAB_WORK VALUES (36, 2);
INSERT INTO LAB_WORK VALUES (37, 3);
INSERT INTO LAB_WORK VALUES (38, 4);

INSERT INTO STUDENT VALUES (10, 'Карпов Н.И.');
INSERT INTO STUDENT VALUES (11, 'Биглер П.П.');
INSERT INTO STUDENT VALUES (12, 'Куконен Е.И.');

INSERT INTO MARK VALUES ( 1, 10, 7 );
INSERT INTO MARK VALUES ( 1, 11, 9 );
INSERT INTO MARK VALUES ( 1, 12, 8 );
INSERT INTO MARK VALUES ( 2, 10, 0 );
INSERT INTO MARK VALUES ( 2, 11, 0 );
INSERT INTO MARK VALUES ( 2, 12, 0 );
INSERT INTO MARK VALUES ( 3, 10, 0 );
INSERT INTO MARK VALUES ( 3, 11, 0 );
INSERT INTO MARK VALUES ( 3, 12, 0 );
INSERT INTO MARK VALUES ( 4, 10, 0 );
INSERT INTO MARK VALUES ( 4, 11, 0 );
INSERT INTO MARK VALUES ( 4, 12, 0 );

INSERT INTO USR(USER_ID, USERNAME, PASS_HASH, ACTIVE, ROLES) VALUES (0, 'admin1', 'secret', true, 'ROLE_ADMIN');
INSERT INTO USR(USER_ID, USERNAME, PASS_HASH, ACTIVE, ROLES) VALUES (1, 'admin2', 'password', false, 'ROLE_ADMIN');
INSERT INTO USR(USER_ID, USERNAME, PASS_HASH, ACTIVE, ROLES) VALUES (3, 'user1', 'user', true, 'ROLE_USER');