ALTER TABLE appointment_queue MODIFY COLUMN id INT auto_increment;
ALTER TABLE carrier MODIFY COLUMN id INT auto_increment;

INSERT IGNORE INTO carrier (name, email_domain) VALUES ('T-Mobile USA, Inc.', 'tmomail.net');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('Verizon Wireless', 'vzwpix.com');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('AT&T Wireless', 'mms.att.net');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('Virgin Mobile', 'vmpix.com');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('Metro PCS', 'mymetropcs.com');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('Boost Mobile', 'myboostmobile.com');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('Cricket', 'mms.cricketwireless.net');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('U.S. Cellular', 'mms.uscc.net');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('C-Spire', 'cspire1.com');
INSERT IGNORE INTO carrier (name, email_domain) VALUES ('Sprint', 'pm.sprint.com');