----Senate Bus Problem---
Index numbers :140338F and 140623B

The folder contains all the source files (java files ) , executable jar and screenshots corresponding the simulation

In order to execute the jar file : java -jar SenateBus.jar 
command is used.

Please Note: that 
	1. Program is implemented by considering mean arrival time between riders as 30 secs and mean arrival time between buses as 20 mins.
	   Hence it will take quite time run the program.
	2. Moreover inter-arrival time is calculated following the exponential distribution.Hence inter-arrival time will not be always same. 
	3. For each arriving new Rider, a new thread will be created. 
	4. for each arriving bus, a thread is created.
	5. There is slight a probability that a rider which prints to the starndard output as "waiting til bus arrives" not being able to board 	   to the arrived bus, in a case bus acquires the mutex before the passenger.(This scenario was not implememented as it addes more 		   complexity to the program). The passenger will be boarded to the next bus.
	

