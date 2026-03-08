
# kFit Data Plan

A `Workout` is either a lift session or a run session and is associated with one `User`.

```typescript
type Workout = {
    id: number,
    user: User,
    workoutType: LiftSession | RunSession
} 

type User = {
    id: number,
    name: string
}
```

## Lift Sessions

- `LiftSessions` are made up up multiple lifts.
- A `Lift` has a name and a list of sets.
- A `LiftSet` has number of reps and weight.

 ```typescript
 type LiftSession = {
    id: number,
    date: string // iso timestamp
    lifts: Lift[],
    notes?: string // optional notes
}

type Lift = {
    id: number,
    name: string,
    sets: LiftSet[]
}

type LiftSet = {
    id: number,
    reps: number,
    weight: number // lbs
}
 ```

## Run Sessions

- A `RunSession` is flat and contains relevant run data.

```typescript
type RunSession = {
    id: number,
    date: string,
    distance: number, // miles
    duration: number, // minutes
    notes?: string // optional notes
}
```