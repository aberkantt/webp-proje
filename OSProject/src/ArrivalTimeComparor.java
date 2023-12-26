  // Compare MyProcess  arrival times
  import java.util.Comparator;
  
  public class ArrivalTimeComparor implements Comparator<MyProcess>
    {
        @Override
        public int compare(MyProcess arg0, MyProcess arg1) {
           
            if (arg0.arrivalTime < arg1.arrivalTime)
                return -1;
            if(arg0.arrivalTime >arg1.arrivalTime)
                return 1;
            
            return 0;
        }
    }