
CREATE TABLE IF NOT EXISTS exercises (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS workouts (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id),
    completed_at TIMESTAMPTZ NOT NULL DEFAULT NOW(), -- timestamp with timezone info
    notes TEXT
);

CREATE TABLE IF NOT EXISTS workout_sets (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    workout_id INT NOT NULL REFERENCES workouts(id) ON DELETE CASCADE, -- when workout is deleted, delete all its sets
    exercise_id INT NOT NULL REFERENCES exercises(id),
    set_number INT NOT NULL,
    reps INT NOT NULL,
    weight_lbs NUMERIC(5,2) NOT NULL
)