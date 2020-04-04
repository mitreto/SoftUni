/* Football Scout Database */
-- Section 1: Data Definition Language (DDL) – 40 pts

# 1. Table Design

CREATE SCHEMA fsd;

CREATE TABLE countries(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL
);

CREATE TABLE towns(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`country_id` INT(11) NOT NULL,
CONSTRAINT fk_towns_countries
FOREIGN KEY (`country_id`)
REFERENCES countries (`id`)
);

CREATE TABLE stadiums(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`capacity` INT(11) NOT NULL,
`town_id` INT(11) NOT NULL,
CONSTRAINT fk_stadiums_towns
FOREIGN KEY (`town_id`)
REFERENCES towns(`id`)
);

CREATE TABLE teams(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
`established` DATE NOT NULL,
`fan_base` BIGINT(20) NOT NULL DEFAULT 0,
`stadium_id` INT(11) NOT NULL,
CONSTRAINT fk_teams_stadiums
FOREIGN KEY (`stadium_id`)
REFERENCES stadiums(`id`)
);

CREATE TABLE skills_data(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`dribbling` INT(11) DEFAULT 0,
 `pace` INT(11) DEFAULT 0,
 `passing` INT(11) DEFAULT 0,
 `shooting` INT(11) DEFAULT 0,
 `speed` INT(11) DEFAULT 0,
 `strength` INT(11) DEFAULT 0
);

CREATE TABLE coaches(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
`coach_level` INT(11) NOT NULL DEFAULT 0
);

CREATE TABLE players(
`id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(10) NOT NULL,
`last_name` VARCHAR(20) NOT NULL,
`age` INT(11) NOT NULL DEFAULT 0,
 `position` CHAR(1) NOT NULL,
 `salary` DECIMAL(10,2) NOT NULL DEFAULT 0,
 `hire_date` DATETIME,
 `skills_data_id` INT(11) NOT NULL,
 `team_id` INT(11),
 CONSTRAINT fk_players_skills_data
 FOREIGN KEY (`skills_data_id`)
 REFERENCES skills_data (`id`),
 CONSTRAINT fk_players_teams
 FOREIGN KEY (`team_id`)
 REFERENCES teams (`id`)
);

CREATE TABLE players_coaches(
`player_id` INT(11),
`coach_id` INT(11),
CONSTRAINT pk_players_coaches
PRIMARY KEY (`player_id`, `coach_id`),
CONSTRAINT fk_players_coaches_players
FOREIGN KEY (`player_id`)
REFERENCES players (`id`),
CONSTRAINT fk_players_coaches_coaches
FOREIGN KEY (`coach_id`)
REFERENCES coaches (`id`)
);


/* Section 2: Data Manipulation Language (DML) – 30 pts */

# 2. Insert

INSERT INTO coaches (first_name, last_name, salary, coach_level) 
SELECT 
	p.first_name, 
	p.last_name, 
    (p.salary * 2), 
    CHAR_LENGTH(p.first_name)
FROM players AS p 
WHERE p.age > 44;

# 3. UPDATE 

UPDATE coaches 
SET coach_level = (coach_level + 1)
WHERE first_name LIKE 'A%' AND id IN (1, 2, 4, 5, 6, 8, 10);


# 4. Delete

DELETE FROM players AS p
WHERE p.age > 44;

/* Section 3: Querying – 50 pts */

# 5. Players

SELECT 
	`first_name`, `age`, `salary`
FROM 
	players
ORDER BY `salary` DESC;

# 6. Young offense players without contract

SELECT 
	p.`id`,
    CONCAT(p.`first_name`, ' ', p.`last_name`) AS full_name,
    p.`age`,
    p.`position`,
    p.`hire_date`
FROM players AS p
JOIN skills_data AS sd ON sd.id = p.skills_data_id
WHERE 
	p.`age` < 23 
		AND 
	p.`position` = 'A' 
		AND 
	p.`hire_date` IS NULL 
		AND 
	sd.strength > 50
ORDER BY p.salary, p.age;

# 7. Detail info for all teams

SELECT 
	t.`name` AS team_name,
    t.`established`,
    t.`fan_base`,
    COUNT(p.`id`) AS players_count
FROM teams AS t
LEFT JOIN players AS p ON t.`id` = p.`team_id`
GROUP BY t.`id`
ORDER BY players_count DESC, t.fan_base DESC;

# 8. The fastest player by towns

SELECT
	MAX(sd.`speed`) AS max_speed,
    tow.`name` AS town_name
FROM skills_data AS sd
RIGHT JOIN players AS p ON sd.id= p.skills_data_id
RIGHT JOIN teams AS t ON t.id = p.team_id
RIGHT JOIN stadiums AS s ON s.id = t.stadium_id
RIGHT JOIN towns AS tow ON tow.id = s.town_id
WHERE t.`name` != 'Devify'
GROUP BY town_name
ORDER BY max_speed DESC, town_name;

# 9. Total salaries and players by country 

SELECT
	c.`name` AS `name`,
    COUNT(p.`id`) AS total_count_of_players,
    IF(SUM(p.`salary`) = 0, NULL, SUM(p.`salary`)) AS total_sum_of_salaries
FROM countries AS c
LEFT JOIN towns AS tow ON c.id = tow.country_id
LEFT JOIN stadiums AS s ON tow.id = s.town_id
LEFT JOIN teams AS t ON s.id = t.stadium_id
LEFT JOIN players AS p ON t.id = p.team_id
GROUP BY c.`name`
ORDER BY total_count_of_players DESC, `name`;

/* Section 4: Programmability – 30 pts */

# Find all players that play on stadium

DELIMITER $$
CREATE FUNCTION udf_stadium_players_count (stadium_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE count INT;
    SET count := (
				SELECT COUNT(p.id) as count
				FROM players AS p
				JOIN teams AS t ON t.id = p.team_id
				JOIN stadiums AS s ON s.id = t.stadium_id
				JOIN towns AS tow ON tow.id = s.town_id
				WHERE s.`name` = stadium_name
            );
				RETURN count;
END $$

SELECT udf_stadium_players_count('Jaxworks');

# 11. Find good playmaker by teams

DELIMITER $$
CREATE PROCEDURE udp_find_playmaker (min_dribble_points INT, team_name VARCHAR(45))
BEGIN 
	SELECT 
		CONCAT(p.first_name, ' ', p.last_name) AS full_name,
		p.`age`,
		p.`salary`,
		sd.`dribbling`,
		sd.`speed`,
		t.`name` AS team_name
	FROM
		players AS p
			JOIN
		skills_data AS sd ON sd.id = p.skills_data_id
			JOIN
		teams AS t ON t.id = p.team_id
	WHERE
		t.`name` = team_name
			AND sd.dribbling > min_dribble_points
	ORDER BY sd.speed DESC
	LIMIT 1;
END $$

CALL udp_find_playmaker (20, 'Skyble');





    
