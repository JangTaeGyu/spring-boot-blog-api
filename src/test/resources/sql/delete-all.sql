
-- categories
DELETE FROM categories;
ALTER TABLE categories ALTER COLUMN id RESTART WITH 1;

-- posts
DELETE FROM posts;
ALTER TABLE posts ALTER COLUMN id RESTART WITH 1;