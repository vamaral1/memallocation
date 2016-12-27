## Memory Allocation

This project simulates how memory allocation is performed using three different data structures to allow for comparisons of performance. Namely, we try out a balanced binary search tree, a heap, and a hashtable.

Java 7 and J-unit 4.0

Run `Driver.java` with an input file as the first and only program argument. Examples of input files are in the data folder. The format is as follows:

  1. The first line should be the number of units of memory available
  2. Every line following has the format: letter, space, number
    - The letter corresponds to a request to allocate (A) or de-allocate (D) a certain amount of memory
    - The number is corresponds to the amount of memory to perform the requested operation on

Two output files are generated:
  - `Analysis.txt` shows performance statistics for each data structure
  - `Translog.txt` displays the history of requests with the amount of memory available and whether the allocation/deallocation request was satisfied