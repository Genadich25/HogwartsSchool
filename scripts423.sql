SELECT student.name, student.id, student.faculty_id
FROM student
         INNER JOIN faculty f on student.faculty_id = f.id;

SELECT avatar.id, student_id
FROM avatar
         LEFT JOIN student s on avatar.student_id = s.id;
