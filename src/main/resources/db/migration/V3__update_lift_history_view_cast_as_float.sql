DROP VIEW IF EXISTS user_lift_history;


CREATE VIEW user_lift_history AS
SELECT users.id       AS user_id,
       users.name     AS lifter,
       exercises.name AS exercise,
       lift_sets.reps ,
       lift_sets.weight::float -- kotlin doesn't like BigDecimal
FROM users
         JOIN lift_sessions ON users.id = lift_sessions.user_id
         JOIN lifts ON lift_sessions.id = lifts.lift_session_id
         JOIN exercises ON lifts.exercise_id = exercises.id
         JOIN lift_sets ON lifts.id = lift_sets.lift_id;