# CS542 - Internals Programming Option | Assignment 4 - Undo Redo Logging

Contributors:

1. Arun Vadivel

2. Neha Satish Mahajan


INSTRUCTIONS TO RUN THE PROGRAM:

Step 1. DemoApplication.java is the main file. This program is built on the previous assignments.

Step 2. When running the program for the first time, a prompt appears:

Select Project: Enter '1' for Logging. Enter '2' for Query Execution.
Press ‘1’ and ‘Enter’

Step 3. After pressing ‘1’, the next prompt asks for:

Which table do you want to work on?
Enter '1' for City Population
Enter '2' for Country Population

Enter ‘1’ or ‘2’ based on your choice.

Note: Make sure that City.db and Country.db is already present in the system.

Step 4. The next prompt asks you to select if you want to move the Log to a new system (or replace the updated population database with the unchanged old database) to update the old database, or to update the database with all the populations with an increase in 2%.

Which do you want to do in the City Table?

Enter '1' for Checking if the database is updated with a new Log File. Please make sure that the Log File is placed in the same location as the City.DB File

Enter '2' for increasing the population of all the cities by 2% will considered as '2' by default

Based on the option you enter, the respective processes are performed.
