package org.platform.initalize;

public interface TableStatements {
    String USER =  "CREATE TABLE IF NOT EXISTS users (\n"
            + "	id SERIAL PRIMARY KEY,\n"
            + "	username text NOT NULL,\n"
            + "	email text NOT NULL,\n"
            + "	password text NOT NULL,\n"
            + "	role text NOT NULL,\n"
            + "	created timestamp with time zone DEFAULT CURRENT_TIMESTAMP\n"
            + ");";
}
