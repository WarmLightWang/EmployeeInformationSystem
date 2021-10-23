--Creating the database accesses and interacts with the Employee Information System

CREATE TABLE roles(role_id serial PRIMARY KEY,
				   role_title TEXT,
				   role_salary int);
				   
CREATE TABLE employees(employee_id serial PRIMARY KEY,
					   firstName TEXT,
					   lastName TEXT,
					   hireDate date,
					   phoneNumber varchar(15),
					   emailAddress TEXT,
					   role_id_fk int REFERENCES roles(role_id) );
					   
INSERT INTO roles(role_title, role_salary)
             values('Chief Leader', 200000),
                   ('Team Leader', 150000),
                   ('Software Engineer', 100000),
                   ('Administrator', 90000),
                   ('Marketing Specilist', 80000);
                  
INSERT INTO employees(firstName, lastName, hireDate, phoneNumber, emailAddress, role_id_fk)
               values('James', 'Smith', '2019-10-12', '(608)1239876', 'jamesS@example.com', 1),
                     ('Michael', 'Smith', '2020-11-22', '(608)3548574', 'michaelS@example.com', 3),
                     ('Maria', 'Garcia', '2021-03-14', '(608)6543984', 'mariaG@example.com', 4),
                     ('David', 'Rodriguez', '2018-08-25', '(608)8652358', 'davidR@example.com', 2),
                     ('Mary', 'Hernandez', '2017-11-15', '(608)3923479', 'maryH@example.com', 5),
                     ('Johnson', 'Curry', '2015-12-19', '(608)3752358', 'johnsonC@example.com', 3);
                    
SELECT * FROM roles;
SELECT * FROM employees;
SELECT * FROM employees JOIN roles ON employees.role_id_fk = roles.role_id;  
DROP TABLE roles, employees;