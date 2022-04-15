# Final Project - Group 32
## Members: 
* Chris Lammers -100790204
* Russell Ngo - 100791124
* Islam Nuryyev - 100778667
* Dane Rosedo - 100703194

## Project information: 
The project features a linear game of Battleship where two players each assign a position, from 0 to 9, for their ships. The positions are represented by circles and each player tries to guess the location of their opponent's ship. A wrong guess turns the circle red and a correct one turns it green. The game concludes with a player winning and the title of the window displaying a message specifying the winner. 

## How to run:

Unfortunately, we faced difficulties connecting Server and the UI because the data in State.java was not preserving and could not be accessed from TestBS.java. We will show two ways to run the program instead. One can be played through the console and one with UI.

## Console:

![image](https://user-images.githubusercontent.com/71353833/163502624-f2227262-126e-46ad-81b4-01906ee01c7d.png)

1. Clone the repository from github and open the project.
2. Apply appropriate configurations to make sure the project runs smoothly. In our case, we used Project SDK version 16 on Intellij. We also modified the run configurations for the Client.java class to allow multiple instances.
3. Start running the Server class first and enter a name for the game log file as prompted. The progress of the game will be printed onto a text file.
4. Run the Client class twice. Each Client run instance represents a player and each one will be prompted to select a location from 0-9 to store their ship. The players will then take turns entering and confirming their guesses until someone wins. 
5. Keep checking the server console after each player confirms their guess to see if they win. Once a player wins, the Server console displays the message declaring the winner.
6. After a player wins, enter "done" into the both Clients' console to terminate their connections.
7. A text file containing the progress of the game will be generated.

## UI:

![image](https://user-images.githubusercontent.com/71353833/163502715-8e1ea73d-5411-4260-a548-277f7cb4f5cd.png)

1. Run Battleship and a main menu window will pop up showing options, Start and Exit.
2. Click Start to start game. Each player will be prompted to select a location by entering an integer from 0-9 to store their ship. Players will be asked to enter an integer again if the initial inputs aren't within bounds. 
3. Each player takes turns clicking onto the gray circle which represents the options for the ship location. The top represents Player 2's board and the bottom represents Player 1's board.
4. Red represents the incorrect guess and green represents the correct guess. After a player wins, the game can be shut down by clicking exit.       

## Other resources:
This page was used as extra reference for the project: https://www.geeksforgeeks.org/java-multithreading-tutorial/

## Video Demonstration:



