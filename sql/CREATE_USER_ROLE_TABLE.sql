CREATE TABLE USER_ROLE (
    ROLE_ID NUMBER REFERENCES ROLE(ID),
    USER_ID NUMBER REFERENCES USERS(ID),
    CONSTRAINT PK PRIMARY KEY (ROLE_ID, USER_ID)
);