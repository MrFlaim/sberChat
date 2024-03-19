INSERT INTO t_chat (chat_name) VALUES ('General Chat');
INSERT INTO t_chat (chat_name) VALUES ('Work Chat');
INSERT INTO t_chat (chat_name) VALUES ('Fun Lounge');

INSERT INTO t_role (role) VALUES ('USER');
INSERT INTO t_role (role) VALUES ('FAMILY_HEAD');


--password: password
INSERT INTO t_user (login, password, name, surname, phone_number, online_status)VALUES ('user1', '$2a$10$Gb5pKELZlRS2OFFOQ3cJiOw7n/vacj/sVr4DGgyf.vzYR2X8.QyfW', 'John', 'Doe', '123456789', false);
INSERT INTO t_user (login, password, name, surname, phone_number, online_status)VALUES ('user2', '$2a$10$Gb5pKELZlRS2OFFOQ3cJiOw7n/vacj/sVr4DGgyf.vzYR2X8.QyfW', 'Jane', 'Smith', '987654321', false);
INSERT INTO t_user (login, password, name, surname, phone_number, online_status)VALUES ('user3', '$2a$10$Gb5pKELZlRS2OFFOQ3cJiOw7n/vacj/sVr4DGgyf.vzYR2X8.QyfW', 'Alice', 'Smith', '5551234567', false);
INSERT INTO t_user (login, password, name, surname, phone_number, online_status)VALUES ('user4', '$2a$10$Gb5pKELZlRS2OFFOQ3cJiOw7n/vacj/sVr4DGgyf.vzYR2X8.QyfW', 'Bob', 'Johnson', '9876543310', false);
INSERT INTO t_user (login, password, name, surname, phone_number, online_status)VALUES ('admin', '$2a$10$Gb5pKELZlRS2OFFOQ3cJiOw7n/vacj/sVr4DGgyf.vzYR2X8.QyfW', 'Admin', 'Admin', '555555555', false);


INSERT INTO t_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO t_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO t_user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO t_user_role (user_id, role_id) VALUES (4, 1);
INSERT INTO t_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO t_user_role (user_id, role_id) VALUES (5, 2);


INSERT INTO t_chat_to_user (user_id, chat_id) VALUES (1, 1);
INSERT INTO t_chat_to_user (user_id, chat_id) VALUES (2, 1);
INSERT INTO t_chat_to_user (user_id, chat_id) VALUES (2, 2);
INSERT INTO t_chat_to_user (user_id, chat_id) VALUES (3, 2);
INSERT INTO t_chat_to_user (user_id, chat_id) VALUES (4, 3);
INSERT INTO t_chat_to_user (user_id, chat_id) VALUES (1, 2);
INSERT INTO t_chat_to_user (user_id, chat_id) VALUES (5, 3);

INSERT INTO t_message (create_date, message, chat_id, user_id) VALUES ('2024-03-11 12:00:00', 'Hello, everyone!', 1, 1);
INSERT INTO t_message (create_date, message, chat_id, user_id) VALUES ('2024-03-11 12:05:00', 'Any tech news?', 2, 3);
INSERT INTO t_message (create_date, message, chat_id, user_id) VALUES ('2024-03-11 12:10:00', 'Lets plan a meetup!', 2, 4);
INSERT INTO t_message (create_date, message, chat_id, user_id) VALUES ('2024-03-11 12:15:00', 'Having a great time!', 3, 5);

INSERT INTO t_money_request (money_request, chat_id, user_id, currency) VALUES (100.0, 1, 1, 'USD');
INSERT INTO t_money_request (money_request, chat_id, user_id, currency) VALUES (50.0, 2, 2, 'RUB');
INSERT INTO t_money_request (money_request, chat_id, user_id, currency) VALUES (75.0, 3, 3, 'RUB');
INSERT INTO t_money_request (money_request, chat_id, user_id, currency) VALUES (32.0, 1, 3, 'RUB');

INSERT INTO t_user_to_money_request (money_request_id, money_send, user_id) VALUES (1, 50, 2);
INSERT INTO t_user_to_money_request (money_request_id, money_send, user_id) VALUES (1, 25, 3);
INSERT INTO t_user_to_money_request (money_request_id, money_send, user_id) VALUES (2, 25, 4);
INSERT INTO t_user_to_money_request (money_request_id, money_send, user_id) VALUES (3, 75, 5);