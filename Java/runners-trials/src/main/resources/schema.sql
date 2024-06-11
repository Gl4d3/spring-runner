CREATE TABLE IF NOT EXISTS // THIS COMMAND WILL CREATE A TABLE IF IT DOESN'T EXIST
    Run(
        id INT AUTO_INCREMENT PRIMARY KEY,
        title VARCHAR(255) NOT NULL,
        distance FLOAT NOT NULL,
        time FLOAT NOT NULL,
        pace FLOAT NOT NULL,
        date DATE NOT NULL,
        location VARCHAR(255) NOT NULL,

        PRIMARY KEY (id)// THIS COMMAND WILL CREATE A PRIMARY KEY FOR THE TABLE Run. The primary key is the id column.
    );