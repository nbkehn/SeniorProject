ALTER TABLE appointment_queue MODIFY COLUMN id INT auto_increment;
ALTER TABLE carrier MODIFY COLUMN id INT auto_increment;

INSERT IGNORE INTO carrier (name, email_domain) VALUES ('T-Mobile USA, Inc.', 'tmomail.net');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('Verizon Wireless', 'vzwpix.com');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('AT&T Wireless', 'mms.att.net');