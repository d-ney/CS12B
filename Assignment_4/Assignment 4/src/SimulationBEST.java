//-----------------------------------------------------------------------------
// Simulation.java
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation{

public static double totalTime = 0.0;
public static double avgTime = 0.0;
public static int longTime = 0; 

	
//-----------------------------------------------------------------------------
//
// The following function may be of use in assembling the initial backup and/or
// storage queues.  You may use it as is, alter it as you see fit, or delete it
// altogether.
//
//-----------------------------------------------------------------------------

   public static Job getJob(Scanner in)
   {
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a, d);
   }

//-----------------------------------------------------------------------------
//
// The following stub for function main contains a possible algorithm for this
// project.  Follow it if you like.  Note that there are no instructions below
// which mention writing to either of the output files.  You must intersperse
// those commands as necessary.
//
//-----------------------------------------------------------------------------

   public static void main(String[] args) throws IOException{

//    1.  check command line arguments
	   
	   if(args.length != 1)
	   {
		   throw new IOException(
				   "Usage: Simulation input output"
				   );
	   }
//
//    2.  open files for reading and writing
	   File fIn = new File(args[0]);
	   Scanner sc = new Scanner(fIn);
	   PrintWriter trc = new PrintWriter(new FileWriter(args[0]+".trc"));
	   PrintWriter rpt = new PrintWriter(new FileWriter(args[0]+".rpt"));
	   
//
//    3.  read in m jobs from input file

	   int m = Integer.parseInt(sc.nextLine());
	   
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
	   
//
//    4.  run simulation with n processors for n=1 to n=m-1  {
	   		for(int n = 1; n < m ; n++)
	   		{
	   			
	   			
//    5.      declare and initialize an array of n processor Queues and any 
//            necessary storage Queues
			   Queue[] proc = new Queue[n];
			   Queue store = new Queue();
			   for(int j = 0; j < n; j++)
			   {
				   proc[j] = new Queue();
				   
			   }
			   
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
			   
			   trc.println("time=" + timeNow);
		    	 trc.println("0: " + store.toString());
			    	for(int y = 0; y < n; y++)
			    		trc.println((y+1) + ": " + proc[y].toString());
			    	trc.println();
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

				    	//trc.println("LowestJobArr: " + lowestJobArr);
				    	
				    	//logs jobs at front of queue before new Jobs added
				    	for(int k = 0; k < frontJobs.length; k++)
			    		{
					    	if(!proc[k].isEmpty())
					    		frontJobs[k] = (Job) proc[k].peek();
					    	else
					    		frontJobs[k] = new Job(Job.UNDEF, Job.UNDEF);

			    		}
				    	
				    	//trc.println("Lowest Job Arrival Time:: " + lowestJobArr);
	
				    	updated = false;
				    	int[] lowestJTime = new int[n];
				    	int lowestJ = 10000;
				    	int lowestJindex = 0;
				    	for(int i = 0; i < n; i++)
				    	{
				    		//trc.println("Front Jobs arrival:" + frontJobs[i].getArrival());
				    		//updates timeNow
					    	if(frontJobs[i].getArrival() == Job.UNDEF && lowestJobArr != 1000000000 && updated == false && !first_run||updated == false && lowestJobArr < frontJobs[i].getFinish() && lowestJobArr > timeNow && !first_run && lowestJobArr != 1000000000)
					    	{
					    		
					    		timeNow = lowestJobArr;
					    		updated = true;
					    		//trc.println("time: " + timeNow);
					    		
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
					    		//trc.println("time: " + timeNow);
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
					    	
					    	//trc.println("time: " + timeNow);
					    	
					    	if(!store.isEmpty())
					    	{
						    	Queue JobsToAdd = new Queue();
						    	//trc.println("store len: " + store.length());
						    	int sLenB = store.length();
						    	for(int u = 0; u < sLenB; u++)
						    	{
						    		Job J = (Job) store.dequeue();
						    		//trc.println("Current J: " + J.toString());
						    		
						    		if(J.getArrival() == timeNow)
						    			JobsToAdd.enqueue(J);
						    		else
						    			store.enqueue(J);
						    	}
						    	
						    	
						    	//trc.println("JobsToAdd: " + JobsToAdd.toString());
						    	
//						    	//determines which Queue new Job should be placed in
						    	
						    	
						    	int jToAddB = JobsToAdd.length();
						    	//trc.println("jToAddB: " + jToAddB);
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
//							    				if(smallestQindex == 0 &&  i == n-1)
//							    					proc[smallestQindex].enqueue(workingJob);
//							    				else if(smallestQindex != 0)
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
				    	trc.println("time=" + timeNow);
				    	 trc.println("0: " + store.toString());
					    	for(int y = 0; y < n; y++)
					    		trc.println((y+1) + ": " + proc[y].toString());
					    trc.println();
					    
					    //checks to see if loop should be broken
					    
					    Job storeJob;
					    
					    jobs_have_arrived = true;
					    if(!store.isEmpty())
				    	{
					    	int sLenB = store.length();
				    	
					    	for(int u = 0; u < sLenB; u++)
					    	{
					    		Job J = (Job) store.dequeue();
					    		//trc.println("Current J: " + J.toString());
					    		
					    		if(J.getFinish() == Job.UNDEF)
					    			jobs_have_arrived = false;
					    		
					    			store.enqueue(J);
					    	}
					    	
				    	}
//					    trc.println("Store: " + store.toString());
//					    trc.println("Store len: " + store.length());
//					    trc.println("m: " + m);
//					    
					    if(store.length() == m)
					    	jobs_in_proc = false;
					    
					    if(jobs_have_arrived == true && jobs_in_proc == false)
					    	jobs_still_remain = false;
					    				    	
//					    trc.println("loop still going: " + jobs_still_remain);
//					    
//					    if(timeNow == 22)
//					    	return;
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
	   		//
//	      13. close input and output files
   		rpt.close();
   		trc.close();
}

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
   
   static void queueUp(int timeNow, Job workingJob, boolean first_run, int n, Queue[] proc, Queue store)
   {
	   if(timeNow == workingJob.getArrival() && !first_run)
   	{
		boolean added = false;
   		int largestQindex = 0;
   		int largestQ = 10000;
   		
   		int[] lenQ = new int[n];
   		
   		for(int l = 0; l < n; l++)
   		{
		    			
   			lenQ[l] = proc[l].length();

   		}	
  
   		for (int j = 0; j < lenQ.length; j++)
   		{
   			if (lenQ[j] < largestQ)
   			{
   				largestQ = lenQ[j];
   				largestQindex = j;
   				
   			}
   		}
   				
   			if(!added && workingJob.getFinish() == Job.UNDEF)
   			{
   				store.dequeue();
   				proc[largestQindex].enqueue(workingJob);
   				added = true;
   				
   			}  	
   			
   		
   	}
	  
   }
   

   
   
}
   


