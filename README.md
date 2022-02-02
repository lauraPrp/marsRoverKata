## _Mars Rover Kata_
Program to simulate rovers movement around the surface of Mars

![](assets/walle.gif) Thanks to https://github.com/ianmartinez/AsciiStudio for this image 


To start the simulation run the App/Main.java class running the Main class, some test cases are already available,
using number 1-5  you can see in the console output where they move.
✨
```sh
1- Simulate 2 rovers moving, no errors
2- contains invalid commands
3- cointains invalid commands that send the rover out of the plateau
4- shows the behaviour of the rover when it meets an obstacle on its way
5- shows the behaviour of the program when the starting point is not valid
6- you can use your *custom file*!  digit or copy and paste the fileName.txt having the following format:
```
✨
```sh
1st line: 2 integer numbers separated by a space
 they set the max x y coordinates of the plateau, where rovers are allowed to move
2nd line: 2 integer numbers and a letter [N-S-E-W] separated by a space
 the coordinates of a rover on the plateau and the direction the rover is facing
3d line: sequence of letters [RLM] without spaces between them 
 R rotates the rover RIGHT 
 L rotates the rover LEFT
 M moves the rover 1 grid space 
```
✨
it's possible to move several rovers in sequence, you can add them repeating 2nd and 3rd lines format for each rover.
save your file in the main directory and choose option 6 when running App/Main.java


