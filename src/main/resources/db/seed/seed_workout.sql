


INSERT INTO lift_sessions(user_id, notes)
VALUES (1, 'pspspspsps');

INSERT INTO lifts(exercise_id, lift_session_id)
VALUES (1, 1);

INSERT INTO lift_sets(lift_id, reps, weight)
VALUES (1, 3, 155) ,
       (1, 3, 165),
       (1, 3, 175);

INSERT INTO lift_sessions(user_id, notes)
VALUES (2, 'another test note');

INSERT INTO lifts(exercise_id, lift_session_id)
VALUES (2, 2);

INSERT INTO lift_sets(lift_id, reps, weight)
VALUES (2, 3, 105) ,
       (2, 3, 110),
       (2, 3, 115);