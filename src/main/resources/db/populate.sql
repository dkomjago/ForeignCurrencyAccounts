INSERT INTO CUSTOMER (PESEL, NAME, SURNAME, BIRTHDATE)
VALUES
    ('99010112345', 'TESTER', 'TESTERSON', DATE '1999-01-01'),
    ('98020254321', 'TESTER', 'TESTERSTEIN', DATE '1998-02-02');

INSERT INTO ACCOUNT (CUSTOMER_ID)
VALUES 1, 2;

INSERT INTO SUB_ACCOUNT (CURRENCY, FUNDS, ACCOUNT_ID)
VALUES
    ('USD', 0, 1),
    ('PLN', 50, 1),
    ('USD', 100, 2),
    ('PLN', 1, 2);