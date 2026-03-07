
INSERT INTO exercises(name)
VALUES ('Bench Press'),
       ('Front Squat'),
       ('Overhead Press'),
       ('Trapbar Deadlift'),
       ('Romanian Deadlift')
ON CONFLICT(name) DO NOTHING