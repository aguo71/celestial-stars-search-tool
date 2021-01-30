# README

## Stars 1
**Known Bugs**

None.

**Design details**



**Runtime/space optimizations**

None.

**How to run tests**



**How to build/run program**

Enter commands as follows:

mvn package

./run

Then start typing REPL commands to be executed.

**Design Questions**

I believe my current implementation 
of my REPL allows for 10+ commands to be easily added and handled. This is because I
currently utilize a hashmap to map repl commands to a respective action interface, 
which means that I can simply just register 10 more command strings and their
respective action classes to the REPL hashmap to make my REPL check against these as well. The action
interface guarantees that the class implementing it has a run method, which is intended to 
be the behavior generating method for each repl command.

**Checkstyle errors**

None


