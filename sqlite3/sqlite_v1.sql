
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY,
    stripe_id TEXT NOT NULL,
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
    email TEXT NOT NULL REFERENCES users(email) ON UPDATE CASCADE,
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
    endpoint TEXT NOT NULL REFERENCES endpoints(endpoint) ON DELETE CASCADE,
    created_at INTEGER DEFAULT (DATETIME('now', 'subsec'))
);

--INSERT INTO users(email, stripe_id, account_plan, is_referred, free_trial) VALUES ('kevinl33078@gmail.com', 'stripe-test', 0, 0, 0);
--INSERT INTO endpoints(endpoint) VALUES ('endpoint-test');
--INSERT INTO forms(email, form_name, endpoint) VALUES ('kevinl33078@gmail.com', "test", "endpoint-test");