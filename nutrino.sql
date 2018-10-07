CREATE SCHEMA IF NOT EXISTS Nutrino;
USE Nutrino;

DROP TABLE IF EXISTS RecipeToIngredients;
DROP TABLE IF EXISTS RecipeToHealthLabels;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS HealthLabels;
DROP TABLE IF EXISTS PlannerToRecipe;
DROP TABLE IF EXISTS LoggedInUser;
DROP TABLE IF EXISTS Administrator;
DROP TABLE IF EXISTS Planner;
DROP TABLE IF EXISTS Search;
DROP TABLE IF EXISTS Favorites;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS Users;

CREATE TABLE Users(
	username VARCHAR(255),
    password VARCHAR(255),
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    email VARCHAR(255),
    privileges VARCHAR(255),
    CONSTRAINT pk_Users_username
		PRIMARY KEY(username)
);

CREATE TABLE LoggedInUser(
	username VARCHAR(255),
    height FLOAT,
    weight FLOAT,
    diet VARCHAR(255),
    CONSTRAINT pk_LoggedInUser_username
		PRIMARY KEY(username),
	CONSTRAINT fk_LoggedInUser_username FOREIGN KEY(username)
		REFERENCES Users(username)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Administrator(
	username VARCHAR(255),
    lastLogin TIMESTAMP,
    CONSTRAINT pk_Admin_username PRIMARY KEY(username),
    CONSTRAINT fk_Admin_username FOREIGN KEY(username)
		REFERENCES Users(username)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Planner(
	planID int AUTO_INCREMENT,
    username VARCHAR(255),
    day ENUM('Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'),
    time ENUM('Breakfast', 'Lunch', 'Dinner'),
    CONSTRAINT pk_Planner_planID PRIMARY KEY(planID),
    CONSTRAINT fk_Planner_username FOREIGN KEY(username)
		REFERENCES Users(username)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE recipe(
	id INT NOT NULL AUTO_INCREMENT,
	uri VARCHAR(255),
    label VARCHAR(255),
    image VARCHAR(255),
    url VARCHAR(255),
    ingredient_lines LONGTEXT,
    calories VARCHAR(255),
    total_weight float,
    health_labels VARCHAR(255),
    CONSTRAINT pk_Recipes_id PRIMARY KEY(id),
    CONSTRAINT uk_Recipes_uri UNIQUE KEY(uri),
    CONSTRAINT uk_Recipes_label UNIQUE KEY(label)
);

CREATE TABLE Search(
	searchID INT AUTO_INCREMENT,
    username VARCHAR(255),
    keyword VARCHAR(255),
    CONSTRAINT pk_Search_searchID PRIMARY KEY(searchID),
    CONSTRAINT fk_Search_username FOREIGN KEY(username)
		REFERENCES Users(username)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Favorites(
	favoriteID INT AUTO_INCREMENT,
    username VARCHAR(255),
    recipeID INT,
    CONSTRAINT pk_Favorites_favoriteID PRIMARY KEY(favoriteID),
    CONSTRAINT fk_Favorites_username FOREIGN KEY(username)
		REFERENCES Users(username)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_Favorites_recipeID FOREIGN KEY(recipeID)
		REFERENCES Recipe(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE PlannerToRecipe(
	pToRID INT AUTO_INCREMENT,
    recipeID INT,
    planID INT,
    CONSTRAINT pk_PlannerToRecipe_pToRID PRIMARY KEY(pToRID),
    CONSTRAINT fk_PlannerToRecipe_recicpeID FOREIGN KEY(recipeID)
		REFERENCES Recipe(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_PlannerToRecipe_planID FOREIGN KEY(planID)
		REFERENCES Planner(planID)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE HealthLabels(
    labelName VARCHAR(255),
    CONSTRAINT pk_HealthLabels_label_name PRIMARY KEY(labelName)
);

CREATE TABLE recipe_to_health_labels(
	hid INT AUTO_INCREMENT,
    label_name VARCHAR(255),
    recipe_name VARCHAR(255),
    CONSTRAINT pk_RecipeToHealthLabels_hid PRIMARY KEY(hid),
    CONSTRAINT fk_RecipeToHealthLabels_label_name FOREIGN KEY(label_name)
		REFERENCES HealthLabels(labelName)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_recipe_to_health_labels_recipe_name FOREIGN KEY(recipe_name)
		REFERENCES Recipe(label)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	UNIQUE KEY (label_name, recipe_name)
);