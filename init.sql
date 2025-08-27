-- Enable UUID generation extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Domain table for person qualification
CREATE TABLE person_qualification (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Insert the possible roles
INSERT INTO person_qualification (name) VALUES ('student'), ('teacher');

-- Person table
CREATE TABLE person (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    qualification_id BIGINT NOT NULL REFERENCES person_qualification(id)
);

-- Courses table
CREATE TABLE courses (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    description TEXT
);

-- A course can have exactly one teacher (qualification must be 'teacher')
ALTER TABLE courses
ADD COLUMN teacher_id UUID,
ADD CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES person(id);

-- Many-to-many: courses <-> students
CREATE TABLE course_students (
    course_id UUID NOT NULL REFERENCES courses(id) ON DELETE CASCADE,
    student_id UUID NOT NULL REFERENCES person(id) ON DELETE CASCADE,
    PRIMARY KEY (course_id, student_id)
);

-- Ensure teacher_id really belongs to a teacher
CREATE OR REPLACE FUNCTION check_teacher_is_valid()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM person p
        JOIN person_qualification q ON p.qualification_id = q.id
        WHERE p.id = NEW.teacher_id AND q.name = 'teacher'
    ) THEN
        RAISE EXCEPTION 'Assigned teacher must have qualification = teacher';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_check_teacher
BEFORE INSERT OR UPDATE ON courses
FOR EACH ROW EXECUTE FUNCTION check_teacher_is_valid();

-- Ensure students in relation are really "student"
CREATE OR REPLACE FUNCTION check_student_is_valid()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM person p
        JOIN person_qualification q ON p.qualification_id = q.id
        WHERE p.id = NEW.student_id AND q.name = 'student'
    ) THEN
        RAISE EXCEPTION 'Assigned student must have qualification = student';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_check_student
BEFORE INSERT OR UPDATE ON course_students
FOR EACH ROW EXECUTE FUNCTION check_student_is_valid();
