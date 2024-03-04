INSERT INTO client(name, cpf, phone, email)
VALUES ('Gabriel Chireia', '065.569.847-96', '(65)94654-5619', 'gchireia@gmail.com');
INSERT INTO client(name, cpf, phone, email)
VALUES ('John Doe', '123.456.789-00', '(11)98765-4321', 'johndoe@example.com');
INSERT INTO client(name, cpf, phone, email)
VALUES ('Jane Smith', '987.654.321-00', '(21)98765-4321', 'janesmith@example.com');
INSERT INTO client(name, cpf, phone, email)
VALUES ('Bob Johnson', '789.123.456-00', '(31)98765-4321', 'bobjohnson@example.com');

INSERT INTO product(description, quantity, price)
VALUES ('Arroz', 50, 10.00);
INSERT INTO product(description, quantity, price)
VALUES ('Feijão', 30, 8.50);
INSERT INTO product(description, quantity, price)
VALUES ('Óleo de soja', 20, 6.00);
INSERT INTO product(description, quantity, price)
VALUES ('Café', 40, 12.00);
INSERT INTO product(description, quantity, price)
VALUES ('Açúcar', 25, 5.00);

INSERT INTO orders(client_name, description, issued_date, total_price)
VALUES ('Gabriel Chireia', 'Compra da semana', '2023-05-01', 200);

INSERT INTO order_product(quantity, order_id, product_id)
VALUES (20, 1, 1);