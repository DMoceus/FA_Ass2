CS442 Design Patterns
Sprint 2015
PROJECT <1> README FILE

Due Date: <Sunday, March 15, 2015>
Submission Date: <Sunday, March 15, 2015>
Grace Period Used This Project: <0> Days
Grace Period Remaining: <N/A> Days
Author(s): <David Morris>
e-mail(s): <dmorris4@binghamton.edu>


PURPOSE:

[
	Basic One -> Many, Server -> Client Program
	Checks Prime Numbers
]

PERCENT COMPLETE:

[
	100%
]

PARTS THAT ARE NOT COMPLETE:

[
]

BUGS:

[
	Due to method used to work into antiquated "ANT" compiler, Number of input arguments are not checked.
]

FILES:

[
	README.txt
	build.xml
	ClientDriver.java
	PrimeDriver.java
	AllPrimeQueries.java
	ServerDriver.java
	ServerMenu.java
	ServerThreadSpawner.java
	PrimeServerSocket.java
	PrimeServerWorker.java
	CheckPrime.java
	Debug.java
]

SAMPLE OUTPUT:

[

run:
     [java] Constructor Called
     [java] Constructor Called
     [java] Constructor Called
     [java] Constructor Called
     [java] ServerMenu Setup Correctly
     [java] Please Choose an Option:
     [java] [1] Client Name / History
     [java] [2] All Client Queries
     [java] [3] Quit
2
     [java]
     [java] Please Choose an Option:
     [java] [1] Client Name / History
     [java] [2] All Client Queries
     [java] [3] Quit
3
     [java] Bye!
	
]

TO COMPILE:

[
	In the Directory of this README.txt:
		ant -buildfile src/build.xml all
]

TO RUN:

[
	Server:
		ant -buildfile src/build.xml -Darg0="server" -Darg1="<portNumber>" -Darg2="<DebugValue>" run
	Client:
		ant -buildfile src/build.xml -Darg0="client" -Darg1="<hostName>" -Darg2="<portNumber>" -Darg3="<DebugValue>" run
]

EXTRA CREDIT:

[
]


BIBLIOGRAPHY:

 This serves as evidence that we are in no way intending Academic Dishonesty.
<NAMES OF GROUP MEMBERS>

[
	* Boilerplate code was borrowed heavily (As Instructed) from http://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
]

ACKNOWLEDGEMENT:

[
	During this project, Joseph Howard's help with configuring ANT, in addition to general stress relief, was greatly appreciated.
]
