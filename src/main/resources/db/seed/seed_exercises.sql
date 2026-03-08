
INSERT INTO exercises(name)
VALUES ('Bench Press'),
       ('Front Squat'),
       ('Overhead Press'),
       ('Trapbar Deadlift'),
       ('Romanian Deadlift'),
       ('Pullup')
ON CONFLICT(name) DO NOTHING