# marsRoverKata
Program to simulate rovers movement around the surface of Mars
it is possible to simulate rovers movement running the Main class, some test cases are already available,using number 1-5  you can see in the console output where they move.
1-moves 2 rovers, no errors
2-contains invalid commands
3-cointains commands that send the rover out of the plateau
4-shows the behaviour of the rover when it meets an obstacle on his way
5-shows the behaviour of the program when the stairting point coordinates are invalid
6-*custom file* digit or copy and paste the fileName.txt having the following format:
1st line 2 integer numbers separated by a space - they set the max coordinates of the plateau, the grid where rovers are allowed to move
2nd line the coordinates of a rover: 2 integer numbers smaller or equals than plateau max values
3d line a sequence of character RLM R stands for RIGHT and dictate the rover to rotate right. L does the same but LEFT. M dictate the rover to move 1 grid space.
it's possible to move several rovers in sequence, you can add them repeating 2nd and 3rd lines format for each rover. 
save your file in the main directory and choose option 6 when running App/Main.java 

