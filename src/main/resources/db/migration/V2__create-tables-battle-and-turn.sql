CREATE TABLE battle
(
    id              SERIAL PRIMARY KEY,
    hero_id         uuid NOT NULL,
    monster_id      uuid NOT NULL,
    player_initiative   INT,
    monster_initiative  INT,
    CONSTRAINT fk_hero FOREIGN KEY (hero_id) REFERENCES character(id),
    CONSTRAINT fk_monster FOREIGN KEY (monster_id) REFERENCES character(id)
);

CREATE TABLE turn
(
    id  SERIAL PRIMARY KEY,
    damage_dealt    INT,
    damage_received INT,
    battle_id   INT NOT NULL,
    CONSTRAINT fk_battle FOREIGN KEY (battle_id) REFERENCES battle(id)
);