INSERT INTO role_app (name, description, created_at, updated_at) VALUES
  ('USER', 'Rol básico de usuario con permisos limitados.', NOW(), NOW()),
  ('ADMIN', 'Rol con acceso completo a todas las funciones administrativas.', NOW(), NOW()),
  ('MODERATOR', 'Rol con permisos para moderar contenido y usuarios.', NOW(), NOW()),
  ('GUEST', 'Rol para usuarios no autenticados con acceso restringido.', NOW(), NOW()),
  ('SUPPORT', 'Rol encargado de soporte técnico a los usuarios.', NOW(), NOW()),
  ('DEVELOPER', 'Rol con acceso a herramientas de desarrollo y debugging.', NOW(), NOW()),
  ('TESTER', 'Rol enfocado en pruebas del sistema y reporte de errores.', NOW(), NOW()),
  ('MANAGER', 'Rol con permisos de gestión intermedia.', NOW(), NOW()),
  ('EDITOR', 'Rol con permisos para editar contenido.', NOW(), NOW()),
  ('VIEWER', 'Rol con permisos solo de lectura.', NOW(), NOW()),
  ('ANALYST', 'Rol con acceso a reportes y datos analíticos.', NOW(), NOW()),
  ('AUDITOR', 'Rol para auditar cambios y accesos en el sistema.', NOW(), NOW()),
  ('CONTENT_CREATOR', 'Rol que permite crear y publicar contenido.', NOW(), NOW()),
  ('HR', 'Rol del departamento de recursos humanos.', NOW(), NOW()),
  ('FINANCE', 'Rol con acceso a módulos financieros.', NOW(), NOW()),
  ('SALES', 'Rol para gestionar clientes y ventas.', NOW(), NOW()),
  ('MARKETING', 'Rol para gestionar campañas y materiales de marketing.', NOW(), NOW()),
  ('INTERN', 'Rol con permisos limitados para prácticas.', NOW(), NOW()),
  ('TRAINER', 'Rol para usuarios encargados de capacitación.', NOW(), NOW()),
  ('CLIENT', 'Rol asignado a usuarios externos o clientes.', NOW(), NOW()),
  ('PARTNER', 'Rol para socios comerciales o aliados.', NOW(), NOW()),
  ('REVIEWER', 'Rol con permisos para revisar contenido antes de su publicación.', NOW(), NOW()),
  ('TRANSLATOR', 'Rol para usuarios encargados de traducción de contenido.', NOW(), NOW()),
  ('SECURITY', 'Rol con enfoque en supervisión de seguridad del sistema.', NOW(), NOW()),
  ('SYSTEM_ADMIN', 'Rol para administración de servidores y sistemas.', NOW(), NOW()),
  ('PRODUCT_OWNER', 'Rol de dueño de producto con visibilidad total del proyecto.', NOW(), NOW()),
  ('PROJECT_MANAGER', 'Rol encargado de la gestión de proyectos.', NOW(), NOW()),
  ('SCRUM_MASTER', 'Rol de facilitador en equipos ágiles.', NOW(), NOW()),
  ('DATA_SCIENTIST', 'Rol con acceso avanzado a datos y modelos analíticos.', NOW(), NOW());

INSERT INTO user_app (full_name, dni, username, password, enabled, created_at, updated_at) VALUES
  ('Kevin Gonzaga', '12345678', 'kgonzaga',
    '$2a$10$jKevTtqxObz3jtgWNQSuoOxGGnfG3L56I0e3RCgxT0bWNP5rMlJfO', -- clave123
    TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Admin User', '87654321', 'admin',
    '$2a$10$jKevTtqxObz3jtgWNQSuoOxGGnfG3L56I0e3RCgxT0bWNP5rMlJfO', -- clave123
    TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Ana Torres', '45678901', 'atorres',
    '$2a$10$jKevTtqxObz3jtgWNQSuoOxGGnfG3L56I0e3RCgxT0bWNP5rMlJfO', -- clave123
    TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Jorge Mendoza', '56789012', 'jmendoza',
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
