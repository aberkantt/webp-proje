import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FCFS extends Kuyruk  
{
    @Override
    public void Ekle(MyProcess p) {
        super.Ekle(p);

    }

    public void KuyrukUpdate(List<MyProcess> processList){
        Collections.sort(processList, MyProcess.BY_ARRIVAL_TIME);
        Collections.sort(processList, MyProcess.BY_BURST_TIME);
    }

    @Override
    public void Calistir(MyProcess p) throws IOException  {
        
        p.setProcessStatus(MyProcess.ProcesStatus.Yurutuluyor);
      //   p.writeMyProcess(p);

    }
}
