CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE character
(
    id              uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name            VARCHAR(255),
    life_points     INT,
    strength        INT,
    defence         INT,
    agility         INT,
    dice_number     INT,
    dice_type       VARCHAR(30),
    character_class VARCHAR(30)
);