# Music streaming API
## Frameworks and language used:
-  Spring Boot
-  Java
-  SQL database
## Data Flow


There are Four Models class in this application.
    1. Users Model Class.
    2. Status Model Class.
    3. Role Model Class.
    4. Songs Model Class.


* **Controller** :

  There are Five Controller classse in this application.

    1. AdminController Class.
    2. UsersController Class.
    3. StatusController Class.
    4. SongsController class.
    5. RoleController class. 


* **Interfaces** :

  There are Four Interfaces in this application.
    1. IUsersInterface Interface.
    2. IStatusInterface Interface.
    3. IRoleInterface Interface.
    4. ISongInterface


* **Service** :

  There are Frou Service class in this application.

    1. UsersService Class.
    2. StatusService Class.
    3. RoleService Class.
    4. SongsService Class
  


* **Repository** :

  There are Four Repository Interfaces in this application.

    1. IUsersRepository Interface.
    2. IStatusRepository Interface.
    3. ISongsRepository Interface.
    4. IRoleRepository Interface


* **Util/Validation** :
    * In this application I'm using annotations based validation with manual validations for more safety and to keep continuty in data .



* **GlobalExceptionHandler class** :
  * In this class i have handled the exceptions that generates in project.



* **Database**

    * I have used SQL Database to store the data.
    * I have deployed this project on AWS EC2 machine.


## Project Summary

This project is an API for a music streaming service that supports Regular and Admin users. Normal users can only add songs to their playlists and execute CRUD operations on their playlists, whereas admin have the power to perform CRUD (Create, Read, Update, and Delete) activities on songs.

The MySQL database was used to build the API, which uses it to store user playlists and songs. To guarantee availability, the deployment link's IP address must be static. The API also employs annotation-based validation to make sure that all user inputs are correct before processing them.


A controller service and a repository are included in the project structure, which offers a distinct separation of concerns and makes the code more modular. Furthermore.

To guarantee data privacy and security.



  













  