# count-min-sketch
ip application Java implementation

Main file at `src/com/ipa/CountMin.java`

Implements a count-min-sketch in Java. this is static/ no user way to change width/depth, could be added

I want delta to be 0.001 (99.9% not error prob) so use depth = ceiling(ln 1/delta) to get depth of 7. 
could let user specify in future.
Using int so want error to be small enough for no overlap, I chose ~0.3 so width = ceiling(2.7/0.3) so width of 10.

In terminal debug/viewer, some issues with the initial filling if enabled.
