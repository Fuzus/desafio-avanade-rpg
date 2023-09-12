CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE warrior
(
    id          uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR(255),
    life_points INT,
    strength    INT,
    defence     INT,
    agility     INT,
    dice_number INT,
    dice_type   VARCHAR(30)
);

CREATE TABLE barbarian
(
    id          uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR(255),
    life_points INT,
    strength    INT,
    defence     INT,
    agility     INT,
    dice_number INT,
    dice_type   VARCHAR(30)
);

CREATE TABLE knight
(
    id          uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR(255),
    life_points INT,
    strength    INT,
    defence     INT,
    agility     INT,
    dice_number INT,
    dice_type   VARCHAR(30)
);

CREATE TABLE orc
(
    id          uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR(255),
    life_points INT,
    strength    INT,
    defence     INT,
    agility     INT,
    dice_number INT,
    dice_type   VARCHAR(30)
);

CREATE TABLE giant
(
    id          uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR(255),
    life_points INT,
    strength    INT,
    defence     INT,
    agility     INT,
    dice_number INT,
    dice_type   VARCHAR(30)
);

CREATE TABLE werewolf
(
    id          uuid PRIMARY KEY DEFAULT uuid_generate_v4(),
    name        VARCHAR(255),
    life_points INT,
    strength    INT,
    defence     INT,
    agility     INT,
    dice_number INT,
    dice_type   VARCHAR(30)
);