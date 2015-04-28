Paul Beckman Capstone
---
Microtonal Synthesizer
---

This project is a basic synthesizer that I can use to pass in vectors that represent 
the distance in semitones between two pitches, do mathematical operations on these 
vectors, and play the result. It allows me to do scalar multiplication, additive 
transposition, and matrix operations like reduced row echelon form (rref) and a 
modified version of rref that swaps columns to produce a more random contour that 
doesn't result in the standard rref pattern in the output vectors.

This project synthesizes (pun intended) a huge variety of topics studied in this course,
ranging from 2D Arrays and ArrayLists to GUI creation to abstract class construction and
use. In terms of additional topics/research, I've always wanted to work with sound and
in this project I devled into playing compound wave forms made by adding sine waves.

Frequency Calculations:
A4 = 440 Hz
n = number of semitones away from A
Since raising a pitch by an octave double its frequency, and since there are twelve
semitones per octave, we can derive
440*2^(n/12) = frequency

rref:
Here I use code by Martijn Courteaux to reduce a matrix of vectors to its basis,
which is the simplest set of vectors of which scalar multiples can be added together 
to attain any vector within the original set. 
For example, rref of the matrix
v1 = 1, 2, 3, 4
v2 = 5, 6, 7, 8
reduces to
w1 = 1, 0, -1, -2
w2 = 0, 1,  2,  3
because one 1*w1 + 2*w2 = v1
        and 5*w1 + 6*w2 = v2
so v1 and v2 can be formed by linear combinations of w1 and w2

No Stasis rref:
because the standard form for the rref of a matrix (a 3 x 5 for example) is
1, 0, 0, a, b
0, 1, 0, c, d
0, 0, 1, e, f
When these vectors represent half steps, the first three intervals in this 
form are very static predictable, as the pitches each rise by one semitone until they
are in unison one semitone higher. To disrupt this form, I wrote a simple algorithm to
swap columns in order to spead out the first (1,0,0),(0,1,0),(0,0,1) columns, perform 
the rref, then reverse the process to get back to the original vectors. That would result
in a matrix like:
1, a, 0, b, 0
0, c, 1, d, 0
0, e, 0, f, 1
so you dont get the predictable rising semitone sound in the first three intervals

Run the main method of the MicrotonalViewer class
Enter a list of numbers seperated by commas under the 'Interval Vector' heading
    - 'Mary had a little lamb' for example is -2,-2,2,2,0,0
    - The beginning of 'when the saints go marching in' is 4,1,2
Multiply all intervals by a scalar by entering that value under the 'Scalar k' heading
Bypass playing a certain vector by entering any text under the 'Bypass' heading
Set a note duration in seconds by entering it under 'Note Duration'
Play an individual vector with its 'Play' button, or 'Play All' to play simultaneously

You can also calculate the frequency of a given note by entering that note in the
'Pitch' box, then press calculate to find its frequency i.e. C# = 554.37
Press 'Up' or 'Down' to change the octave of the caluclated frequency

