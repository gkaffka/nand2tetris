// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input. 
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel. When no key is pressed, the
// program clears the screen, i.e. writes "white" in every pixel.


(MAINLOOP)
@SCREEN
D=A;
@i
M=D

@KBD
D=M
@BLACK
D;JNE

@WHITE
0;JMP

@MAINLOOP
0;JMP

(BLACK)
@i
A=M
M=-1
D=A+1
@i
M=D

@KBD
D=D-A
@BLACK
D;JLT

@MAINLOOP
0;JMP

(WHITE)
 @i
 A=M
 M=0
 D=A+1
 @i
 M=D

 @KBD
 D=D-A
 @WHITE
 D;JLT
 
 @MAINLOOP
 0;JMP