# README

## Stars 1
**Known Bugs**

None.

**Design details**

For my stars 1 project, I made an extensible REPL which monitors input from the terminal and calls
appropriate Actions classes using a hashmap. To set this repl up, I call starsUniverse in the 
main method,
which is a wrapper class I made to make a new REPL and register the stars and mock modelling 
specific commands. Each of these repl commands is its own class which implements Action, and 
contains a run method which allows for the appropriate behavior. For commands that are used to 
parse, a CSVParser class is used along with a stars or mock specific parsing wrapper. For the stars
specific neighbor and radius naive commands, a distance calculator was used as well
as a NeighborDistances distance wrapper class to streamline the calculation of distances.

**Runtime/space optimizations**

None.

**How to build/run program**

Enter commands as follows:

mvn package

./run

Then start typing REPL commands to be executed.

**How to run tests**

For JUnit tests, type: mvn tests

For system tests, type: 

./cs32-test tests/src/stars/stars1/*.test 

or 

./cs32-test tests/src/stars/stars2/*.test


**Design Questions**

I believe my current implementation 
of my REPL allows for 10+ commands to be easily added and handled. This is because I
currently utilize a hashmap to map repl commands to a respective action interface, 
which means that I can simply just register 10 more command strings and their
respective action classes to the REPL hashmap to make my REPL check against these as well. 
The action interface guarantees that the class implementing it has a run method, which is intended 
to be the behavior generating method for each repl command.

**Checkstyle errors**

None.
