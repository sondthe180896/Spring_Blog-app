INSERT INTO post (title, description, content, created_at, updated_at)
VALUES
    ('Title 1', 'Description 1', 'Content 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Title 2', 'Description 2', 'Content 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Title 3', 'Description 3', 'Content 3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Title 4', 'Description 4', 'Content 4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Title 5', 'Description 5', 'Content 5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO comment (id, name, email, body, status, created_at, updated_at, post_id)
VALUES
    (UUID(), 'John Doe', 'john@example.com', 'This is a great post!', 'O', NOW(), NOW(), '1'),
    (UUID(), 'Jane Smith', 'jane@example.com', 'Very informative, thanks!', 'R', NOW(), NOW(), '1'),
    (UUID(), 'Alice Brown', 'alice@example.com', 'I have a question about this topic.', 'C', NOW(), NOW(), '2'),
    (UUID(), 'Bob White', 'bob@example.com', 'Can you provide more examples?', 'O', NOW(), NOW(), '2'),
    (UUID(), 'Charlie Black', 'charlie@example.com', 'Thanks for sharing!', 'R', NOW(), NOW(), '3');

INSERT INTO role (type, created_at, updated_at)
VALUES
    ('ADMIN', NOW(), NOW()),
    ('EMPLOYEE', NOW(), NOW());
