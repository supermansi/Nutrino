CREATE SCHEMA IF NOT EXISTS Nutrino;
USE Nutrino;

DROP TABLE IF EXISTS RecipeToIngredients;
DROP TABLE IF EXISTS RecipeToHealthLabels;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS HealthLabels;
DROP TABLE IF EXISTS PlannerToRecipe;
DROP TABLE IF EXISTS LoggedInUser;
DROP TABLE IF EXISTS Administrators;
DROP TABLE IF EXISTS Planner;
DROP TABLE IF EXISTS Search;
DROP TABLE IF EXISTS Favorites;
DROP TABLE IF EXISTS Recipes;
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

CREATE TABLE Recipes(
	uri VARCHAR(255),
    label VARCHAR(255),
    image VARCHAR(255),
    url VARCHAR(255),
    diet ENUM('balanced', 'high-protien', 'high-fiber', 'low-fat', 'low-carb', 'low-sodium'),
    calories VARCHAR(255),
    totalWeight float,
    totalNutrients float,
    CONSTRAINT pk_Recipes_uri PRIMARY KEY(uri)
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
    uri VARCHAR(255),
    CONSTRAINT pk_Favorites_favoriteID PRIMARY KEY(favoriteID),
    CONSTRAINT fk_Favorites_username FOREIGN KEY(username)
		REFERENCES Users(username)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_Favorites_uri FOREIGN KEY(uri)
		REFERENCES Recipes(uri)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE PlannerToRecipe(
	pToRID INT AUTO_INCREMENT,
    uri VARCHAR(255),
    planID INT,
    CONSTRAINT pk_PlannerToRecipe_pToRID PRIMARY KEY(pToRID),
    CONSTRAINT fk_PlannerToRecipe_uri FOREIGN KEY(uri)
		REFERENCES Recipes(uri)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_PlannerToRecipe_planID FOREIGN KEY(planID)
		REFERENCES Planner(planID)
        ON UPDATE CASCADE
        ON DELETE SET NULL
);

CREATE TABLE HealthLabels(
	hID INT AUTO_INCREMENT,
    labelName VARCHAR(255),
    CONSTRAINT pk_HealthLabels_hID PRIMARY KEY(hID)
);

CREATE TABLE RecipeToHealthLabels(
	rToHID INT AUTO_INCREMENT,
    hID INT,
    uri VARCHAR(255),
    CONSTRAINT pk_RecipeToHealthLabels_rToHID PRIMARY KEY(rToHID),
    CONSTRAINT fk_RecipeToHealthLabels_hID FOREIGN KEY(hID)
		REFERENCES HealthLabels(hID)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_RecipeToHealthLabels_uri FOREIGN KEY(uri)
		REFERENCES Recipes(uri)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Ingredients(
	ingredientID INT AUTO_INCREMENT,
    name VARCHAR(255),
    CONSTRAINT pk_Ingredients_ingredientID PRIMARY KEY(ingredientID)
);

CREATE TABLE RecipeToIngredients(
	rToIID INT AUTO_INCREMENT,
    ingredientID INT,
    uri VARCHAR(255),
    CONSTRAINT pk_RecipeToIngredients_rToIID PRIMARY KEY(rToIID),
    CONSTRAINT fk_RecipeToIngredients_ingredientID FOREIGN KEY(ingredientID)
		REFERENCES Ingredients(ingredientID)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
	CONSTRAINT fk_RecipeToIngredients_uri FOREIGN KEY(uri)
		REFERENCES Recipes(uri)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
