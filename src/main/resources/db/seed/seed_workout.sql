

INSERT INTO workouts(user_id, notes)
VALUES
(1, 'test notes'),
(2, 'more test notes');

INSERT INTO workout_sets(workout_id, exercise_id, set_number, reps, weight_lbs)
VALUES
(1, 1, 1, 3, 100),
(1, 1, 2, 3, 100),
(1, 1, 3, 3, 100);
