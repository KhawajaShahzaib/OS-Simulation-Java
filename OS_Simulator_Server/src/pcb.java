import java.util.Random;
public class pcb {
String state;
static int totalProcessID=0;
int processID;
String process_priority="High";
static int Parent=535;
int memoryRequired=64;
int allocatedMemory;
int processorid=1;
boolean ioState;
Random random;
int btime;

pcb(){
	state="Ready";
	processID=totalProcessID;
	totalProcessID+=1;
	allocatedMemory=64;
	ioState=false;
	random = new Random();
	btime=random.nextInt(7) + 1;
}

pcb(int m){
	state="Ready";
	processID=totalProcessID;
	totalProcessID+=1;
	allocatedMemory=m;
	ioState=false;
	random = new Random();
	btime=random.nextInt(7) + 1;
}
}
