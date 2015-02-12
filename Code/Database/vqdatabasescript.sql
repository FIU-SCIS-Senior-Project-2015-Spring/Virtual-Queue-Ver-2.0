CREATE DATABASE `VQdatabase`;
USE 'VQdatabase';

CREATE TABLE 'venue'(
venue_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY.
name VARCHAR(35) NOT NULL,
max_queue_per_guest INT DEFAULT 3,
restrictionsackrequired ENUM ('Y', 'N') ,
disclaimer_ack_required ENUM ('Y', 'N'),
waiver_ack_required ENUM ('Y', 'N'),
max_wait_per_acivity INT NOT NULL,
metrics ENUM ('Y', 'N') );

CREATE TABLE 'patron'(
patron_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
venue_id INT FOREIGN KEY PREFERENCES venue(venue_id),
name_first VARCHARCHAR(20) NOT NULL,
name_last VARCHAR(20) NOT NULL,
marketing_contact_info_tbd VARCHAR(30) NULL);

CREATE TABlE 'visitor'(
patron_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
contact_phone INT NOT NULL,
contact_email VARCHAR(45) NOT NULL,
contact_other VARCHAR(45) NULL,
contact_method ENUM ('E', 'P'), #"E" FOR EMAIL, "P" FOR PHONE
time_in TIMESTAMP,
time_out TIMESTAMP,
group_leader VARCHAR(20) NOT NULL,
visitors_in_party INT NULL
);

CREATE TABLE 'activity'(
activity_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL,
about BLOB NULL,
latitude INT NOT NULL,
longitude NOT NULL,
location_in_venue VARCHAR(30),
time_per_event INT NOT NULL,
entry_time INT NOT NULL,
exit_time INT NOT NULL,
max_guest_per_event INT NOT NULL,
)