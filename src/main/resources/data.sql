INSERT INTO chat (chat_name)
VALUES ('General Chat'),
       ('Tech Talk'),
       ('Fun Lounge');

INSERT INTO client (login, name, online_status, password, phone_number, role, surname)
VALUES ('user1', 'John', false, 'password1', '1234567890', 'USER', 'Doe'),
       ('user2', 'Jane', false, 'password2', '9876543210', 'USER', 'Doe'),
       ('user3', 'Alice', false, 'password3', '5551234567', 'USER', 'Smith'),
       ('user4', 'Bob', false, 'password4', '9876543310', 'USER', 'Johnson'),
       ('user5', 'Eve', false, 'password5', '1112223333', 'USER', 'Brown');

INSERT INTO chat_to_client (client_id, chat_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 3),
       (1, 2),
       (5, 3);

INSERT INTO message (create_date, message, chat_id, client_id)
VALUES ('2024-03-11 12:00:00', 'Hello, everyone!', 1, 1),
       ('2024-03-11 12:05:00', 'Any tech news?', 2, 3),
       ('2024-03-11 12:10:00', 'Lets plan a meetup!', 2, 4),
       ('2024-03-11 12:15:00', 'Having a great time!', 3, 5);

INSERT INTO money_request (money_request, chat_id, client_id)
VALUES (100.0, 1, 1),
       (50.0, 2, 2),
       (75.0, 3, 3),
       (32.0, 1, 3);

INSERT INTO user_to_money_request (money_request_id, money_send, client_id)
VALUES (1, 50, 2),
       (1, 25, 3),
       (2, 25, 4),
       (3, 75, 5);
