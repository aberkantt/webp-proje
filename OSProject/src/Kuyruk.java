import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Kuyruk {

    Queue<MyProcess> processes = new LinkedList<MyProcess>(); //proses kuyrukları

    public void Ekle(MyProcess p)
    {
        processes.add(p);
    }
    public void Sil(MyProcess p)
    {
        processes.remove(p);
    }
    public void Calistir(MyProcess p) throws IOException{

    }  

}