import java.util.Comparator;
 // Compare MyProcess  burst times
  public  class BurstTimeComparor
     implements Comparator<MyProcess>
    {
        @Override
        public int compare(MyProcess  arg0,MyProcess  arg1) {
            if(arg0.arrivalTime==arg1.arrivalTime)
            {   if(arg0.priority==arg1.priority)
                {  
                if (arg0.burstTime < arg1.burstTime)
                    return -1;
                if(arg0.burstTime >arg1.burstTime)
                    return 1;
                }
            }
            return 0;
        }   
    }