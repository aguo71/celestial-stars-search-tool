# README

## Stars 1, 2, 3
**Known Bugs**

None.

**Design details**

For stars 1, I made an extensible REPL which monitors input from the terminal and calls
appropriate Actions classes using a hashmap. To set this repl up, I call starsUniverse in the 
main method, which is a wrapper class I made to make a new REPL and register the stars and 
mock modelling specific commands. In this starsUniverse wrapper, I instantiate a List<Star>
containing a single star with a name of null as a placeholder to represent that the stars command
hasn't been called yet. Similarly, I make a KDTree who's values are all null and has a depth of -1
to represent the fact that stars hasn't been called yet. After a call to stars, even empty CSV
files with the proper heading automatically clears these lists, so we know stars has been called.
Next, each of repl commands mapped to and called by the REPL is its own class which implements 
Action, and contains a run method which allows for the appropriate behavior. For commands that 
parse an external csv, a CSVParser class is used along with a stars or mock specific parsing wrapper. 
For the stars specific neighbor and radius naive commands, a distance calculator was used as well
as a NeighborDistances distance wrapper class to streamline the calculation of distances.


For stars 2, I added upon the above functionality with a class representing an extensible KDTree data
structure, and made a class capable of constructing a KDTree given an input list. To make this 
process extensible, I made a HasCoordinates interface which Star extends, which is referenced
by my CoordinateComparator to be used during sorting before KDTree construction. With the KDTree
constructed, I added command classes representing the non-naive implementations of neighbors and
radius which are stars specific but call the extensible algorithm holding classes NeighborsInRadius
or GetKNeighbors which do the bulk of the KDTree traversing and searching. 

For stars 3, I added the main.ftl, query.ftl, and welcome.ftl pages to be the main browser GUI
displaying pages. These in combination created a visual medium including a welcome, instructions, 
and forms for each of the star searching algorithms, and displays results in the bottom of 
the GUI. To style these in an intuitive way, I used main.css to make sure each important
functional part of the GUI was compartmentalized and understandable. Finally, I created
handler classes NaiveNeighborsHandler, NaiveRadiusHandler, NeighborsHandler, and RadiusHandler
to retrieve the form inputs when a user clicks submit on the GUI and runs the appropriate algorithm
in the backend before returning these results to be displayed by the GUI. 

All my extensible classes are contained in package tools, while my stars specific classes
are contained in package edu.brown.cs.student.stars, and my mock classes are contained in 
package mockaroo. 


**Runtime/space optimizations**

None.

**How to build/run program**

Enter commands as follows:

1. mvn package

Then, depending on if you want only enter command through the REPL or also through a GUI, call:

2. ./run 

or

2. ./run --gui

Then start typing REPL commands/GUI inputs to be executed.

**How to run tests**

For JUnit tests, type: mvn tests

To run the system tests I personally designed, run: 

./cs32-test tests/src/stars/stars1/*.test 

and

./cs32-test tests/src/stars/stars2/*.test


**Design Questions**

Stars 1: I believe my current implementation 
of my REPL allows for 10+ commands to be easily added and handled. This is because I
currently utilize a hashmap in my REPL class to map repl commands (strings) to a respective action interface, 
which means that I can simply just register 10 more command strings and their
respective action classes to the REPL hashmap to make my REPL check against these as well. 
The action interface guarantees that the class implementing it has a run method, which is intended 
to be the behavior generating method of each repl command class.

Stars 2a: My k-d tree relies on direct Euclidean distance between 2 points in space in its algorithms. However,
for points close to the surface of the earth, we don't want the Euclidean distance but the distance
along a curve (the earth's natural surface), or an arclength calculation. This means our k-d tree
algorithms would always result in closer distances than the reality would be for someone traveling 
along/close to Earth's surface.

Stars 2b: To implement the Collection interface, I would need to add more methods to my KDTree class such as
remove, removeAll, iterator, size, equals, and hashcode. Most of these methods would simply require 
a simple definition or local variable. However, remove would be a bit difficult to implement
because we would have to retain ordering which introduces the complexity of resorting after each
removal. To do that, we would switch a leaf node with the recently removed node and then propagate
that previous "leaf node" down the kd-tree by comparisons and swappings
until it reaches its proper sorted position.


**Checkstyle errors**

None.


**Other Misc**

Model Based testing did not work very well because my program prints/returns stars who tie
for a certain distance away in a random order each time the algorithm is ran, so checking for ==
equality will fail if the order of tied stars printed changes even though it should still
technically be correct since all the stars are the same distance away from the target.
Thus, I implemented property based testing instead to specifically test distance and star validity rather than
just simple == equality, which does accurately pass tests in the case of ties being part of the results.

**Brief Tests Overview and Information**


In my system test suite for stars 1 and 2 , I test basic functionality but mainly 
focused on edge case arguments for the REPL commands. I created new ill-formatted CSV files to
input as stars arguments, tried various types of command argument mistakes like too many/too few
arguments, incorrect format of arguments like strings instead of ints, and various edge cases
like empty string as star name. Thus, my system tests mainly error check the ERROR messages
that my commands print out.

My JUnit test suite, on the other hand, mainly focuses on algorithmic levels of edge cases. Here,
I mainly test if my various classes are processing and updating data structures correctly,
such as if CSV parser is updating the correct data structure, if KDTree is constructing valid
trees, and if my nearest neighbors is returning the proper neighbors. I have JUnit tested
all public methods in every class except StarsUniverse because my system tests cover the functionality
of that class since it's just a wrapper to set up the REPL, and any other lines I am missing coverage 
on in Jacoco are basically lines that check for edge case argument formatting or REPL inputs 
which I cover using system tests, or lines relating to my GUI handler code which I tested manually
by using my GUI.

To test my GUI, I started my webserver and went on the browser GUI. Then, I tested it by refreshing
to make sure nothing happens as expected, then randomly clicking submit buttons in random combinations
to see if the GUI can properly handle just being clicked on without information. Then, I tried to plug
in random inputs to the forms and submitting to see what the output was, trying both ill-formatted inputs
as well as legitimate arguments to the algorithms. I also tried filling out multiple forms but only pressing
one submit button, as well as just filling out half a form and submitting it. All these actions
were intended to catch cases of functionality that are not generally expected but are still possible
user inputs/behavior. Fortunately, all of these cases were handled properly by my code, and I do not
get any null pointer or other exceptions from any of these actions.