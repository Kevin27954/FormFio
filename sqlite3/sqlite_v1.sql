
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    email TEXT NOT NULL,
    account_plan INTEGER,
    is_referred INTEGER,
    free_trail INTEGER
);

CREATE TABLE IF NOT EXISTS endpoints (
    id INTEGER PRIMARY KEY,
    endpoint TEXT UNIQUE NOT NULL,
    created_date INTEGER DEFAULT (DATETIME('now', 'subsec'))
);

CREATE TABLE IF NOT EXISTS forms (
    id INTEGER PRIMARY KEY,
    user TEXT NOT NULL,
    form_name TEXT NOT NULL,
    description TEXT,
    endpoint TEXT NOT NULL REFERENCES endpoints(endpoint),
    created_date INTEGER DEFAULT (DATETIME('now', 'subsec'))
);

CREATE TABLE IF NOT EXISTS submissions (
    id INTEGER PRIMARY KEY,
    data TEXT,
    source TEXT NOT NULL,
    ip_addr TEXT,
    endpoint TEXT NOT NULL REFERENCES endpoints(endpoint),
    created_date INTEGER DEFAULT (DATETIME('now', 'subsec'))
);