// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)


@R0
D=M

@a
M=D

@R1
D=M

@b
M=D

@R2
M=0

@mult
M=0

@i
M=1

(LOOP)
@i
D=M
@b
D=D-M
@STOP
D;JGT

@mult
D=M
@a
D=D+M
@mult
M=D

@i
M=M+1
@LOOP
0;JMP

(STOP)
@mult
D=M
@R2
M=D

(END)
@END
0;JMP