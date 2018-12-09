## **## GUI-Database Program**

This is a clients database management software for M&S protech autorepair. This program allows the user to login with 2 types of credential, admin with full control and User with only able to view information but not make changes.
At the Admin GUI the user will be able to perform the next activities: • see all clients by clicking “Load all clients” • Add clients by filling the form on the right side, click add when you are done with the info. To add a new client click clear form before adding new information. • Search clients by Id to make modifications, after modify email you click in Update
##                                                                                                              
### NOTE : 
* The credentials for login with Admin are: username = milko and password= hello
* The credentials for login with user are : username = grecia and password = hello
* This program was designed with sqlite Studio for mac. Please refer to references to find installation steps and instruction about how to use it. 
* Check this video for an explanation on how this program works:
https://www.youtube.com/watch?v=M4g3ncXMiVw&t=3s
* Refer to the folder DOC to find java documentation.
##                                                                                                              
### HOW THE PROGRAM WORKS:

![](https://github.com/delcastGitHub/GUI-Database-Final/blob/master/how%20it%20works%20gif.gif)

##                                                                                                              
### ABOUT GUI DESIGN:

This program has 3 GUI: login, admin, and User. All GUIs were design with SceneBuilder app. SceneBuilder is a visual layout tool that lets users quickly design JavaFX application without coding. 

**Login interface:**

The Login GUI displays the database status, recollect the login credentials: username, password and type (admin or user). It compares with the table login to check if the credentials are correct. After the check, the program displays the next interface depending of the type of login: if is admin, it shows the admin interface; if is user, it displays the user interface.

Admin GUI:

This user interface (Admin GUI) has buttons and text fields to manage information on the table. 
•	On the left side of the screen there is a form to add information about a new client : ID, name, surname, email, and phone number. After fill the text fields click on the button “Add Client” to perform the adding client to the table. Use “Clear Form” button to clean the form and add another client’s records.
•	On the top right side there are 3 field text and 4 buttons:
o	The “client ID” text field : recollect the ID number to perform a search into the table and displays it into the table. 
o	The “Search” button executes the action described above.
o	The “New Phone Number” text field sets the phone number of the client with the ID we searched. The user is able to edit this information and click on “Update” to update the client’s phone number.
o	The “Update” button executes the described above.
o	The “New Email” text field sets the email of the client with the ID we searched. The user is able to edit this information and click on “Update” to update the client’s phone number.
o	“Load all Clients” button displays all client’s records into the table.

User GUI :

This interface allows the user to only view the client’s records, and to perform searches by ID.

##                                                                                                              
### UML DIAGRAMS:

**Admin Package UML Diagram:**
![admin package uml diagram](https://user-images.githubusercontent.com/42677141/49692428-bba1b200-fb28-11e8-94c0-416a274b9bb8.png)

**Database Package UML Diagram:**
![database package uml diagram](https://user-images.githubusercontent.com/42677141/49692430-da07ad80-fb28-11e8-9a67-f6818e274e65.png)

**Login Package UML Diagram:**
![login package uml diagram](https://user-images.githubusercontent.com/42677141/49692435-e4c24280-fb28-11e8-86f4-d10f44dfe3c9.png)

**User Package UML Diagram:**
![users package - uml diagram](https://user-images.githubusercontent.com/42677141/49692437-ee4baa80-fb28-11e8-9d28-31e5f2e8aaec.png)
##                                                                                                              
### ABOUT THE EVALUATION RUBRIC FOR THIS GUI DATABASE 2:

https://docs.google.com/spreadsheets/d/1jENM7TZuNQ6QU18a7-_6MhX7dkCNMOk96_2df9_rvi4/edit?usp=sharing

##                                                                                                              
### REFERENCES:

•	To install JavaFX Scene Builder :
https://www.oracle.com/technetwork/java/javase/downloads/javafxscenebuilder-info-2157684.html

Overview of JDK installation : 
https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A


•	To install and use SQLite Studio in JavaFX follow the next step:
o	Download the sqlite jdbc driver from this link:
https://bitbucket.org/xerial/sqlite-jdbc/downloads/

o	Download SQLite Studio from his link:
https://sqlitestudio.pl/index.rvt?act=download
