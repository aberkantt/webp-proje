import java.util.Comparator;

// Compare MyProcess  priorities
    public  class PriorityComparor  implements Comparator<MyProcess>
    {
        @Override
        public int compare(MyProcess arg0, MyProcess arg1) {
            if(arg0.arrivalTime==arg1.arrivalTime)
            {
            if (arg0.priority < arg1.priority)
                return -1;
            if(arg0.priority >arg1.priority)
                return 1;
            }
             else {
            if (arg0.priority < arg1.priority)
                return -1;
            if(arg0.priority >arg1.priority)
                return 1;
            }
            return 0;
        }
    }