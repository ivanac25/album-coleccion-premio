-- Eliminamos datos anteriores
DELETE FROM user_rewards;
DELETE FROM user_stickers;
DELETE FROM rewards;
DELETE FROM stickers;
DELETE FROM albums;
DELETE FROM users;

-- Usuario
INSERT INTO users (id, username, email, nombre, apellido)
VALUES (1, 'ivana', 'ivana@mail.com', 'Ivana', 'Cervera');

-- Álbum
INSERT INTO albums (id, titulo, descripcion, categoria, total_figuritas)
VALUES (1, 'Mundial 2026', 'Álbum de ejemplo', 'Fútbol', 6);

-- Figuritas
INSERT INTO stickers (id, album_id, nombre, numero, rareza) VALUES
 (1, 1, 'Messi', 1, 'EPICA'),
 (2, 1, 'Di María', 2, 'RARA'),
 (3, 1, 'Dibu', 3, 'RARA'),
 (4, 1, 'Otamendi', 4, 'COMUN'),
 (5, 1, 'De Paul', 5, 'COMUN'),
 (6, 1, 'Enzo', 6, 'COMUN');

-- Premio
INSERT INTO rewards (id, album_id, tipo, payload_json)
VALUES (1, 1, 'MEDALLA', '{"color":"oro","texto":"Campeón"}');

-- Figuritas del usuario (3 de 6)
INSERT INTO user_stickers (id, usuario_id, sticker_id, idx, estado)
VALUES
 (1, 1, 1, 0, 'EN_COLECCION'),
 (2, 1, 2, 0, 'EN_COLECCION'),
 (3, 1, 3, 0, 'EN_COLECCION');

-- Premio del usuario
INSERT INTO user_rewards (id, usuario_id, album_id, reward_id, status)
VALUES (1, 1, 1, 1, 'DISPONIBLE');
