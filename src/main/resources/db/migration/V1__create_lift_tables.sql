CREATE TABLE users
(
    id   INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT NOT NULL
);

CREATE TABLE exercises
(
    id   INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE lift_sessions
(
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id    INT         NOT NULL REFERENCES users (id),
    date       TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    notes      TEXT
);

CREATE TABLE lifts
(
    id              INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    exercise_id     INT NOT NULL REFERENCES exercises (id),
    lift_session_id INT NOT NULL REFERENCES lift_sessions (id)
);

CREATE TABLE lift_sets
(
    id      INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    lift_id INT           NOT NULL REFERENCES lifts (id),
    reps    INT           NOT NULL CHECK (reps > 0),
    weight  NUMERIC(5, 2) NOT NULL CHECK ( weight > 0 )
);
