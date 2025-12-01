-- TreereeShop: thử nghiệm database cho cửa hàng cây cảnh (bonsai)
-- Tạo database và schema tối giản + dữ liệu seed

-- Tạo database (chạy nếu chưa có)
CREATE DATABASE IF NOT EXISTS treeree_shop
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE treeree_shop;

-- Bảng vai trò
CREATE TABLE IF NOT EXISTS roles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) UNIQUE NOT NULL
);

-- Bảng người dùng
CREATE TABLE IF NOT EXISTS users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  full_name VARCHAR(255),
  phone VARCHAR(30),
  enabled BOOLEAN DEFAULT TRUE,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Liên kết user-role
CREATE TABLE IF NOT EXISTS user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id)
);

-- Danh mục sản phẩm
CREATE TABLE IF NOT EXISTS categories (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  slug VARCHAR(255) UNIQUE,
  parent_id BIGINT NULL,
  CONSTRAINT fk_categories_parent FOREIGN KEY (parent_id) REFERENCES categories(id)
);

-- Thương hiệu/nhà vườn (tùy chọn)
CREATE TABLE IF NOT EXISTS brands (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL
);

-- Sản phẩm: cây cảnh
CREATE TABLE IF NOT EXISTS products (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  slug VARCHAR(255) UNIQUE,
  description TEXT,
  price DECIMAL(12,2) NOT NULL,
  sku VARCHAR(100),
  status VARCHAR(20) DEFAULT 'ACTIVE',
  category_id BIGINT,
  brand_id BIGINT,
  height_cm INT,
  pot_diameter_cm INT,
  care_level VARCHAR(20),
  light_need VARCHAR(20),
  CONSTRAINT fk_products_category FOREIGN KEY (category_id) REFERENCES categories(id),
  CONSTRAINT fk_products_brand FOREIGN KEY (brand_id) REFERENCES brands(id)
);

-- Ảnh sản phẩm
CREATE TABLE IF NOT EXISTS product_images (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  url VARCHAR(512) NOT NULL,
  is_primary BOOLEAN DEFAULT FALSE,
  CONSTRAINT fk_product_images_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Tồn kho
CREATE TABLE IF NOT EXISTS inventory (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  quantity INT NOT NULL DEFAULT 0,
  CONSTRAINT fk_inventory_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Địa chỉ giao hàng
CREATE TABLE IF NOT EXISTS addresses (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  full_name VARCHAR(255),
  phone VARCHAR(30),
  line1 VARCHAR(255) NOT NULL,
  line2 VARCHAR(255),
  city VARCHAR(100),
  district VARCHAR(100),
  ward VARCHAR(100),
  note VARCHAR(255),
  CONSTRAINT fk_addresses_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Đơn hàng
CREATE TABLE IF NOT EXISTS orders (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  status VARCHAR(20) NOT NULL,
  total DECIMAL(12,2) NOT NULL,
  payment_method VARCHAR(50),
  payment_status VARCHAR(20),
  shipping_address_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_orders_user FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT fk_orders_address FOREIGN KEY (shipping_address_id) REFERENCES addresses(id)
);

-- Sản phẩm trong đơn hàng
CREATE TABLE IF NOT EXISTS order_items (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  unit_price DECIMAL(12,2) NOT NULL,
  quantity INT NOT NULL,
  CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders(id),
  CONSTRAINT fk_order_items_product FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Thanh toán
CREATE TABLE IF NOT EXISTS payments (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  order_id BIGINT NOT NULL,
  provider VARCHAR(50),
  transaction_id VARCHAR(100),
  amount DECIMAL(12,2),
  status VARCHAR(20),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_payments_order FOREIGN KEY (order_id) REFERENCES orders(id)
);

-- Đánh giá
CREATE TABLE IF NOT EXISTS reviews (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  product_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  rating INT CHECK (rating BETWEEN 1 AND 5),
  comment TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_reviews_product FOREIGN KEY (product_id) REFERENCES products(id),
  CONSTRAINT fk_reviews_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Seed dữ liệu mẫu
INSERT INTO roles(name) VALUES ('ADMIN'), ('CUSTOMER')
  ON DUPLICATE KEY UPDATE name=VALUES(name);

INSERT INTO users(email, password_hash, full_name, phone)
VALUES ('admin@treeree.local', '$2a$10$9uQ4dq8x5YdQmJtGzWvJ3u8xQb7ZQw3eQH2C0eVj1b9qk1u2fP3Ky', 'Admin', '0900000000')
ON DUPLICATE KEY UPDATE full_name=VALUES(full_name);

INSERT INTO users(email, password_hash, full_name, phone)
VALUES ('customer@treeree.local', '$2a$10$9uQ4dq8x5YdQmJtGzWvJ3u8xQb7ZQw3eQH2C0eVj1b9qk1u2fP3Ky', 'Khách Hàng', '0911111111')
ON DUPLICATE KEY UPDATE full_name=VALUES(full_name);

INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON r.name='ADMIN' WHERE u.email='admin@treeree.local'
ON DUPLICATE KEY UPDATE user_id=user_id;

INSERT INTO user_roles(user_id, role_id)
SELECT u.id, r.id FROM users u JOIN roles r ON r.name='CUSTOMER' WHERE u.email='customer@treeree.local'
ON DUPLICATE KEY UPDATE user_id=user_id;

INSERT INTO categories(name, slug) VALUES
 ('Bonsai Mini', 'bonsai-mini'),
 ('Cây Trong Nhà', 'cay-trong-nha'),
 ('Cây Phong Thủy', 'cay-phong-thuy')
ON DUPLICATE KEY UPDATE name=VALUES(name);

INSERT INTO brands(name) VALUES ('Vườn Xanh'), ('GreenHouse'), ('Bonsai Art')
ON DUPLICATE KEY UPDATE name=VALUES(name);

INSERT INTO products(name, slug, description, price, sku, status, category_id, brand_id, height_cm, pot_diameter_cm, care_level, light_need)
VALUES
 ('Bonsai Tùng La Hán Mini', 'bonsai-tung-la-han-mini', 'Tùng La Hán dáng mini, phù hợp bàn làm việc.', 450000, 'BN-001', 'ACTIVE', 
   (SELECT id FROM categories WHERE slug='bonsai-mini'), (SELECT id FROM brands WHERE name='Bonsai Art'), 25, 12, 'MEDIUM', 'MEDIUM'),
 ('Cây Trầu Bà Leo', 'cay-trau-ba-leo', 'Cây dễ chăm, lọc không khí tốt.', 180000, 'CN-002', 'ACTIVE',
   (SELECT id FROM categories WHERE slug='cay-trong-nha'), (SELECT id FROM brands WHERE name='Vườn Xanh'), 40, 14, 'EASY', 'LOW'),
 ('Cây Kim Ngân', 'cay-kim-ngan', 'Phong thủy hút tài lộc.', 320000, 'PT-003', 'ACTIVE',
   (SELECT id FROM categories WHERE slug='cay-phong-thuy'), (SELECT id FROM brands WHERE name='GreenHouse'), 35, 15, 'EASY', 'MEDIUM')
ON DUPLICATE KEY UPDATE description=VALUES(description);

INSERT INTO product_images(product_id, url, is_primary)
SELECT id, CONCAT('https://example.com/images/', slug, '.jpg'), TRUE FROM products
ON DUPLICATE KEY UPDATE url=VALUES(url);

INSERT INTO inventory(product_id, quantity)
SELECT id, 20 FROM products
ON DUPLICATE KEY UPDATE quantity=VALUES(quantity);

INSERT INTO addresses(user_id, full_name, phone, line1, city, district, ward, note)
SELECT id, 'Khách Hàng', '0911111111', '123 Đường Hoa', 'TP HCM', 'Quận 1', 'Phường Bến Nghé', 'Giao giờ hành chính'
FROM users WHERE email='customer@treeree.local';

INSERT INTO orders(user_id, status, total, payment_method, payment_status, shipping_address_id)
VALUES ((SELECT id FROM users WHERE email='customer@treeree.local'), 'PENDING', 450000, 'COD', 'UNPAID',
        (SELECT id FROM addresses WHERE user_id=(SELECT id FROM users WHERE email='customer@treeree.local') LIMIT 1));

INSERT INTO order_items(order_id, product_id, unit_price, quantity)
VALUES (
  (SELECT id FROM orders ORDER BY id DESC LIMIT 1),
  (SELECT id FROM products WHERE slug='bonsai-tung-la-han-mini'),
  450000, 1
);

INSERT INTO reviews(product_id, user_id, rating, comment)
VALUES (
  (SELECT id FROM products WHERE slug='cay-trau-ba-leo'),
  (SELECT id FROM users WHERE email='customer@treeree.local'),
  5, 'Cây khỏe, lá xanh đẹp, giao nhanh!'
);