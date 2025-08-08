
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    email TEXT NOT NULL UNIQUE,
    account_plan INTEGER DEFAULT 0,
    is_referred INTEGER DEFAULT 0,
    free_trial INTEGER DEFAULT (DATETIME('now', 'subsec')),
    created_at INTEGER DEFAULT (DATETIME('now', 'subsec'))
);

CREATE TABLE IF NOT EXISTS endpoints (
    id INTEGER PRIMARY KEY,
    endpoint TEXT UNIQUE NOT NULL,
    created_at INTEGER DEFAULT (DATETIME('now', 'subsec'))
);

CREATE TABLE IF NOT EXISTS forms (
    id INTEGER PRIMARY KEY,
    email TEXT NOT NULL,
    form_name TEXT NOT NULL,
    description TEXT,
    endpoint TEXT NOT NULL UNIQUE REFERENCES endpoints(endpoint),
    created_at INTEGER DEFAULT (DATETIME('now', 'subsec'))
);

CREATE TABLE IF NOT EXISTS submissions (
    id INTEGER PRIMARY KEY,
    data TEXT,
    source TEXT NOT NULL,
    ip_addr TEXT,
    endpoint TEXT NOT NULL REFERENCES endpoints(endpoint),
    created_at INTEGER DEFAULT (DATETIME('now', 'subsec'))
);

--INSERT INTO users(email,account_plan, is_referred, free_trial) VALUES ('kevinl33078@gmail.com', 0, 0, 0)