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

INSERT INTO notes (title, content, user_app_id, created_at, create_by, updated_at, updated_by) VALUES
  ('Note 1', 'Content for note number 1.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 2', 'This is the content of the second note.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 3', 'Random content for the third note.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 4', 'Some example content for note 4.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 5', 'Note 5 content goes here.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 6', 'Another note content example.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 7', 'Seventh note with some random text.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 8', 'Content of the eighth note.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 9', 'Randomly generated content for note 9.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 10', 'Tenth note content example.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 11', 'Eleventh note sample content.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 12', 'Content for the twelfth note.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 13', 'Thirteenth note with example content.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 14', 'Fourteenth note content goes here.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 15', 'Random text for note fifteen.', 1, NOW(), 'kgonzaga', NOW(), 'kgonzaga'),
  ('Note 16', 'Sixteenth note content sample.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 17', 'Content of note number seventeen.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 18', 'Eighteenth note random content.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 19', 'Note 19 has some example text.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 20', 'Twentieth note content goes here.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 21', 'Content for the twenty-first note.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 22', 'Twenty-second note with sample text.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 23', 'Random content for note twenty-three.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 24', 'Content of note number twenty-four.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 25', 'Twenty-fifth note example content.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 26', 'Sample text for note twenty-six.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 27', 'Content of the twenty-seventh note.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 28', 'Twenty-eighth note with random content.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 29', 'Note twenty-nine content goes here.', 2, NOW(), 'admin', NOW(), 'admin'),
  ('Note 30', 'Final note content for note thirty.', 2, NOW(), 'admin', NOW(), 'admin');
