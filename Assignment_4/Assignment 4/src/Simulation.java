//-----------------------------------------------------------------------------
// Simulation.java
// Client for Job.java and Queue.java
// Author: Dillon Ney ID# 1705097
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation{

public static double totalTime = 0.0;
public static double avgTime = 0.0;
public static int longTime = 0; 

	
   public static Job getJob(Scanner in)
   {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }


   public static void main(String[] args) throws IOException{

      //check command line arguments
	   
	   if(args.length != 1)
	   {
		   throw new IOException(
				   "Usage: Simulation input output"
				   );
	   }

//      open files for reading and writing
	   File fIn = new File(args[0]);
	   Scanner sc = new Scanner(fIn);
	   PrintWriter trc = new PrintWriter(new FileWriter(args[0]+".trc"));
	   PrintWriter rpt = new PrintWriter(new FileWriter(args[0]+".rpt"));
	   

//      read in m jobs from input file

	   int m = Integer.parseInt(sc.nextLine());
	   
	   //writes headers to files
	   trc.println("Trace file: " + args[0] + ".trc");
	   rpt.println("Report file: "+ args[0] +".rpt");
	   if(m == 1)
	   {
		   rpt.println(m + " Job:");
		   trc.println(m + " Job:");
	   }
	   else
	   {
		   rpt.println(m + " Jobs:");
		   trc.println(m + " Jobs:");
	   }
		
	   
	   Job[] jobOrder = new Job[m];
	   
	   for(int i = 0; i < m; i++)
	   {
			jobOrder[i] = getJob(sc);
			rpt.print(jobOrder[i].toString());
			trc.print(jobOrder[i].toString());
			if(i < m-1)
			{
				rpt.print(" ");
				trc.print(" ");
			}
	   }
	   trc.println();
	   rpt.println("\n");
	   rpt.println("***********************************************************");
	   

//      run simulation with n processors for n=1 to n=m-1  {
	   		for(int n = 1; n < m ; n++)
	   		{
	   			
	   			
//          declare and initialize an array of n processor Queues and any 
//            necessary storage Queues
			   Queue[] proc = new Queue[n];
			   Queue store = new Queue();
			   for(int j = 0; j < n; j++)
			   {
				   proc[j] = new Queue();
				   
			   }
			   //resets finish time of jobs
			   for(int k = 0; k < m; k++)
			   {
				   jobOrder[k].resetFinishTime();
				   store.enqueue(jobOrder[k]);
				   
			   }
			   
			   
			   
			   
			   if(n == 1)
			   {
				   trc.println("\n*****************************\n" + 
					   		+ n +" processor:\n" + 
					   		"*****************************");
			   }
			   else
				   trc.println("*****************************\n" + 
					   		+ n +" processors:\n" + 
					   		"*****************************");
			   
//			   //declaring booleans needed for loop
			   boolean jobs_still_remain = true;
			   boolean jobs_have_arrived = true;
			   boolean jobs_in_proc = true;
			   boolean first_run = false;
			   boolean added;
			   boolean updated;
			   int timeNow = 0;
			   
			   //prints initial state before processing 
			   printFunction( trc, timeNow, store, proc, n);
//			   trc.println("time=" + timeNow);
//		    	 trc.println("0: " + store.toString());
//			    	for(int y = 0; y < n; y++)
//			    		trc.println((y+1) + ": " + proc[y].toString());
//			    	trc.println();
			    while (jobs_still_remain)
			   {
			    	
			    			    	
			    		//declaring needed variables
				    	Job workingJob;
				    	Job nextJob;
				    	Job[] frontJobs;
				    	Job[] newFrontJobs;
				    	added = false;
				    	jobs_in_proc = true;
				    	
				    	//initialization at timeNow = 0
				    	if(!store.isEmpty())
				    		 workingJob = (Job) store.peek();
				    	else
				    		workingJob = new Job(Job.UNDEF, Job.UNDEF);
				    	
				    	if(timeNow == 0)
					    	{
				    			timeNow = workingJob.getArrival();
					    		proc[0].enqueue(workingJob);
					    		store.dequeue();
					    		workingJob.computeFinishTime(timeNow);
					    		first_run = true;
					    		
					    		
					    	}
				    	
				    	if(!store.isEmpty())
				    		nextJob = (Job) store.peek();
				    	else
				    		nextJob = new Job(Job.UNDEF, Job.UNDEF);
				    	
				    	int lowestJobArr = 1000000000;
				    	for(int j = 0; j < store.length(); j++)
				    	{
				    		Job J = (Job) store.dequeue();

			       			if (J.getArrival() < lowestJobArr && J.getFinish() == Job.UNDEF)
			       				lowestJobArr= J.getArrival();
			       			
			       			store.enqueue(J);
			       		}
				    	frontJobs = new Job[n];

				    	//logs jobs at front of queue before new Jobs added
				    	for(int k = 0; k < frontJobs.length; k++)
			    		{
					    	if(!proc[k].isEmpty())
					    		frontJobs[k] = (Job) proc[k].peek();
					    	else
					    		frontJobs[k] = new Job(Job.UNDEF, Job.UNDEF);

			    		}
	
				    	updated = false;
				    	int[] lowestJTime = new int[n];
				    	int lowestJ = 10000;
				    	int lowestJindex = 0;
				    	for(int i = 0; i < n; i++)
				    	{
				    		//updates timeNow
					    	if(frontJobs[i].getArrival() == Job.UNDEF && lowestJobArr != 1000000000 && updated == false && !first_run||updated == false && lowestJobArr < frontJobs[i].getFinish() && lowestJobArr > timeNow && !first_run && lowestJobArr != 1000000000)
					    	{
					    		
					    		timeNow = lowestJobArr;
					    		updated = true;
					    		
					    	}  	
					    	
					    	else if (updated == false && frontJobs[i].getFinish() != Job.UNDEF && !first_run && frontJobs[i].getArrival() != Job.UNDEF)
					    	{
					    		for(int j = 0; j < lowestJTime.length; j++)
					    			if(frontJobs[j].getFinish() != Job.UNDEF)
					    				lowestJTime[j] =  frontJobs[j].getFinish();
					    			else
					    				lowestJTime[j] = 10000;
					    		for (int j = 0; j < lowestJTime.length; j++)
					       		{
					       			if (lowestJTime[j] < lowestJ && lowestJTime[j] != Job.UNDEF)
					       			{
					       				lowestJ = lowestJTime[j];
					       				lowestJindex = j;
					       				
					       			}
					       		}
					    		
					    		timeNow = frontJobs[lowestJindex].getFinish();
					    			updated = true;
					    	}	
					    	
					    
									    
					    
					    	//processes finish times for Jobs at front of queues before new jobs added

					    	processFinish(timeNow, frontJobs, proc, store, first_run);
					    	
					    	for(int k = 0; k < frontJobs.length; k++)
				    		{
						    	if(!proc[k].isEmpty())
						    		frontJobs[k] = (Job) proc[k].peek();
						    	else
						    		frontJobs[k] = new Job(Job.UNDEF, Job.UNDEF);

				    		}
					    	
					    	if(!store.isEmpty())
					    	{
						    	Queue JobsToAdd = new Queue();
						    	int sLenB = store.length();
						    	for(int u = 0; u < sLenB; u++)
						    	{
						    		Job J = (Job) store.dequeue();
						    		
						    		if(J.getArrival() == timeNow)
						    			JobsToAdd.enqueue(J);
						    		else
						    			store.enqueue(J);
						    	}
						    	
						    	//determines which Queue new Job should be placed in
						    						    	
						    	int jToAddB = JobsToAdd.length();
						    	for(int u = 0; u < jToAddB; u++)
						    	{
							    	workingJob = (Job) JobsToAdd.dequeue();
							    	if(timeNow == workingJob.getArrival())
							    	{
		
							    		int smallestQindex = 0;
							    		int smallestQ = 10000;
							    		
							    		int[] lenQ = new int[n];
							    		
							    		for(int l = 0; l < n; l++)
							    		{
									    			
							    			lenQ[l] = proc[l].length();
		
							    		}	
							   
							    		for (int j = 0; j < lenQ.length; j++)
							    		{
							    			if (lenQ[j] < smallestQ)
							    			{
							    				smallestQ = lenQ[j];
							    				smallestQindex = j;
							    				
							    			}
							    		}
							    				
							    			if( workingJob.getFinish() == Job.UNDEF)
							    			{
							    				proc[smallestQindex].enqueue(workingJob);
							    				added = true;
							    				
							    			}  	
							    			
							    		
							    	}
						    	}
						    	
					    	}
				    	}

				    	//logs Jobs in front of Queue after new Jobs added
				    	newFrontJobs = new Job[n];
				    	for(int k = 0; k < newFrontJobs.length; k++)
			    		{
					    	if(!proc[k].isEmpty())
					    		newFrontJobs[k] = (Job) proc[k].peek();
					    	else
					    		newFrontJobs[k] = new Job(Job.UNDEF, Job.UNDEF);
			    		}
				    	
				    	//processes finish times for Jobs at fronts of Queues after new Jobs added
				    	processFinish(timeNow, newFrontJobs, proc, store, first_run);
					      	
				    	
				    	//sets first_run flag to false so initialization isn't accidentally redone
				    	first_run = false;
				    	
				    	
				    	//print function
				    	printFunction( trc, timeNow, store, proc, n);
				    	
//				    	trc.println("time=" + timeNow);
//				    	 trc.println("0: " + store.toString());
//					    	for(int y = 0; y < n; y++)
//					    		trc.println((y+1) + ": " + proc[y].toString());
//					    trc.println();
					    
					    //checks to see if loop should be broken
					   					    
					    jobs_have_arrived = true;
					    if(!store.isEmpty())
				    	{
					    	int sLenB = store.length();
				    	
					    	for(int u = 0; u < sLenB; u++)
					    	{
					    		Job J = (Job) store.dequeue();
					    		
					    		if(J.getFinish() == Job.UNDEF)
					    			jobs_have_arrived = false;
					    		
					    			store.enqueue(J);
					    	}
					    	
				    	}

					    if(store.length() == m)
					    	jobs_in_proc = false;
					    
					    if(jobs_have_arrived == true && jobs_in_proc == false)
					    	jobs_still_remain = false;
					    				    	
			    }


			  avgTime = totalTime / m;
			  if(n > 1)
				  rpt.printf( "%d processors: totalWait=%.0f, maxWait=%d, averageWait=%.2f\n", n, totalTime, longTime, avgTime);
			  else
				  rpt.printf( "%d processor: totalWait=%.0f, maxWait=%d, averageWait=%.2f\n", n, totalTime, longTime, avgTime);
			  
			  totalTime = 0;
			  avgTime = 0;
			  longTime = 0;
			  
			   
			  
   }	 
	   		
//	       close input and output files
   		rpt.close();
   		trc.close();
}
   //processes finish times of jobs currently being processed
   static void processFinish(int timeNow, Job[] currentJobs, Queue[] proc, Queue store, boolean first_run)
   {
	   for(int i = 0; i < currentJobs.length; i++)
   		{
	   		if(currentJobs[i].getFinish() == Job.UNDEF && timeNow >= currentJobs[i].getArrival() && currentJobs[i].getArrival() != Job.UNDEF)
		    	{
		    		currentJobs[i].computeFinishTime(timeNow);
		    		totalTime += currentJobs[i].getWaitTime();
		    		if(currentJobs[i].getWaitTime() > longTime)
		    			longTime = currentJobs[i].getWaitTime();
		    	}
	   	//removes finished jobs from processor queues
	    	if(currentJobs[i].getFinish() <= timeNow && currentJobs[i].getFinish() != Job.UNDEF && !first_run)
	    	{
	    		proc[i].dequeue();
	    		store.enqueue(currentJobs[i]);
	    		
	    	}	
   		}
   }
   
   //prints store queue and processing queues
   static void printFunction(PrintWriter trc, int timeNow, Queue store, Queue[] proc, int n)
   {
	   trc.println("time=" + timeNow);
  	 trc.println("0: " + store.toString());
	    	for(int y = 0; y < n; y++)
	    		trc.println((y+1) + ": " + proc[y].toString());
	    	trc.println();
   }
   
}
   


