INSERT INTO notes (title, content, created_at, updated_at) VALUES
  ('Note 1', 'Content for note number 1.', NOW(), NOW()),
  ('Note 2', 'This is the content of the second note.', NOW(), NOW()),
  ('Note 3', 'Random content for the third note.', NOW(), NOW()),
  ('Note 4', 'Some example content for note 4.', NOW(), NOW()),
  ('Note 5', 'Note 5 content goes here.', NOW(), NOW()),
  ('Note 6', 'Another note content example.', NOW(), NOW()),
  ('Note 7', 'Seventh note with some random text.', NOW(), NOW()),
  ('Note 8', 'Content of the eighth note.', NOW(), NOW()),
  ('Note 9', 'Randomly generated content for note 9.', NOW(), NOW()),
  ('Note 10', 'Tenth note content example.', NOW(), NOW()),
  ('Note 11', 'Eleventh note sample content.', NOW(), NOW()),
  ('Note 12', 'Content for the twelfth note.', NOW(), NOW()),
  ('Note 13', 'Thirteenth note with example content.', NOW(), NOW()),
  ('Note 14', 'Fourteenth note content goes here.', NOW(), NOW()),
  ('Note 15', 'Random text for note fifteen.', NOW(), NOW()),
  ('Note 16', 'Sixteenth note content sample.', NOW(), NOW()),
  ('Note 17', 'Content of note number seventeen.', NOW(), NOW()),
  ('Note 18', 'Eighteenth note random content.', NOW(), NOW()),
  ('Note 19', 'Note 19 has some example text.', NOW(), NOW()),
  ('Note 20', 'Twentieth note content goes here.', NOW(), NOW()),
  ('Note 21', 'Content for the twenty-first note.', NOW(), NOW()),
  ('Note 22', 'Twenty-second note with sample text.', NOW(), NOW()),
  ('Note 23', 'Random content for note twenty-three.', NOW(), NOW()),
  ('Note 24', 'Content of note number twenty-four.', NOW(), NOW()),
  ('Note 25', 'Twenty-fifth note example content.', NOW(), NOW()),
  ('Note 26', 'Sample text for note twenty-six.', NOW(), NOW()),
  ('Note 27', 'Content of the twenty-seventh note.', NOW(), NOW()),
  ('Note 28', 'Twenty-eighth note with random content.', NOW(), NOW()),
  ('Note 29', 'Note twenty-nine content goes here.', NOW(), NOW()),
  ('Note 30', 'Final note content for note thirty.', NOW(), NOW());

INSERT INTO role_app (name, description, created_at, updated_at) VALUES
  ('USER', 'Rol básico de usuario con permisos limitados.',NOW(),NOW()),
  ('ADMIN', 'Rol con acceso completo a todas las funciones administrativas.',NOW(),NOW());

INSERT INTO user_app (full_name, dni, username, password, enabled, created_at, updated_at) VALUES
  ('Kevin Gonzaga', '12345678', 'kgonzaga',
    '$2a$10$jKevTtqxObz3jtgWNQSuoOxGGnfG3L56I0e3RCgxT0bWNP5rMlJfO', -- clave123
    TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Admin User', '87654321', 'admin',
    '$2a$10$jKevTtqxObz3jtgWNQSuoOxGGnfG3L56I0e3RCgxT0bWNP5rMlJfO', -- clave123
    TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_roles (user_id, role_id) VALUES
  (1, 1), -- Kevin Gonzaga -> USER
  (2, 1), -- Admin User -> USER
  (2, 2); -- Admin User -> ADMIN