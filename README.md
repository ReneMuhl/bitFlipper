BitFlipper
==========

A program that reads a matrix from a file and flips a certain amount of bits. The result will be written to a another file.

# Usage

```
java -jar bitFlipper.jar -i mat_10x10_r0c0_a9b9 -x1 3 -x2 5 -y1 3 -y2 6
```

This call reads the matrix from input file "mat_10x10_r0c0_a9b". All bits in the rectangle in intervals
3 <= x <= 5 and 3 <= y <= 6 will be flipped. Finally a file "mat_10x10_r0c0_a9b9_flipped" with the new flipped matrix will be written.

#Explanation

meaning of the parameters:
-i: input file name
x1, x2, y1, y2: are indices in the matrix (start with 0)


the following graphic should explain the position of x1, x2, y1, y2:


           x1    x2
           |     |
     ___________________
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 0 0 0 1 1 1 1 -- y1 
    |1 1 1 0 1 0 1 1 1 1
    |1 1 1 0 1 0 1 1 1 1
    |1 1 1 0 0 0 1 1 1 1 -- y2 
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 1 1 1 1 1 1 1 
            input


           x1    x2
           |     |
     ___________________
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 1 1 1 1 1 1 1 -- y1 
    |1 1 1 1 0 1 1 1 1 1
    |1 1 1 1 0 1 1 1 1 1
    |1 1 1 1 1 1 1 1 1 1 -- y2 
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 1 1 1 1 1 1 1 
    |1 1 1 1 1 1 1 1 1 1 
            output
