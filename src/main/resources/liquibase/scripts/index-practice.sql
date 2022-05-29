-- liquibase formatted sql

-- changeset pavelign:1
CREATE INDEX index_student_name ON student (name);

-- changeset pavelign:2
CREATE INDEX index_faculty_name_color ON faculty(name, color);