CREATE TABLE POST (
    ID NUMBER PRIMARY KEY,
    MESSAGE NVARCHAR2(200),
    DATE_POSTED DATE,
    USER_POSTED NUMBER REFERENCES USERS(ID),
    USER_PAGE_POSTED NUMBER REFERENCES USERS(ID),
    LOCATION NVARCHAR2(100)
);