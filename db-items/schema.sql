CREATE TABLE IF NOT EXISTS Player (
    username VARCHAR(25) NOT NULL PRIMARY KEY,
    email TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    password_salt TEXT NOT NULL,
    total_winnings BIGINT DEFAULT 0,
    confirmation_code VARCHAR(5),
    confirmation_date DATE
);

-- CREATE TABLE IF NOT EXISTS Room (
--     session_id VARCHAR(30) NOT NULL,
--     open_date TIMESTAMP NOT NULL DEFAULT NOW(),
--     close_date TIMESTAMP,
--     room_id VARCHAR(5) NOT NULL,
--     host_username VARCHAR(25) NOT NULL,
--     player_2 VARCHAR(25),
--     player_3 VARCHAR(25),
--     player_4 VARCHAR(25),
--     player_5 VARCHAR(25),
--     player_6 VARCHAR(25),
--     player_7 VARCHAR(25),
--     PRIMARY KEY (session_id)
-- );

-- CREATE TABLE IF NOT EXISTS Game (
--     game_id VARCHAR(30) NOT NULL,
--     session_id VARCHAR(30) NOT NULL,
--     start_date TIMESTAMP NOT NULL DEFAULT NOW(),
--     end_date TIMESTAMP,
--     game_won BOOLEAN DEFAULT FALSE,
--     winner_username VARCHAR(25),
--     game_state JSON,
--     game_type VARCHAR(25),
--     PRIMARY KEY (game_id),
--     FOREIGN KEY (session_id) REFERENCES Room(session_id)
-- );