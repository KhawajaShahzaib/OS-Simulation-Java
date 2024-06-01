# OS-Simulation-Java
*Description*
This is my project I made to demonstrate my knowledge in Operationg System Course, Showing how a single-core processor does Process Scheduling, Memory management. Built using Client-Server architecture (Networking, u need 2 system for this, or u can create two instance as well) where client can create multiple processes in Server machine. I have used Java Swing and awt for the GUI.
I have used various OOP concepts and static classes for this.

*About the Project*
I have used GridLayout for processes and memory buttons and all are packed in a BorderLayout for ease of access. 
You can create as many processes as you want and enter the memory size, based on that, pages will be created. 
You can see the Ready Queue as soon as u create a process.

*Scheduling*
There are two types of scheduling algorithms I used, the default one is FCFS (First come-first-Serve) and the second one is Round-Robin (remember to set the quantum time for RR). When u run a process, then based on scheduling selected, u will see how it affects the ready queue and gantChart. There are other options like Suspend, Block where process goes into the blocked Queue.

*Memory Management*
I have implemented LRU only in this, you can either pass on your pages of processes, created in scheduling to the memory or you can just use the Create_page and set the page Size. 
Overall it was a fun project, I got to practice alot of OOP / DSA concepts and learned GUI alongside.
