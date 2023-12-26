import java.util.Comparator;
import java.util.Random;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.Process;
import java.util.concurrent.TimeUnit;

public class MyProcess extends Process{
   
   
    public int Id;               // Unique MyProcess  ID
    public int arrivalTime;      // Arrival time  
    public int burstTime;        // Burst time  
    public int priority;         // Priority  
    public Process p ;          //ProcessBuilder için
    public ProcesStatus processStatus;  //Prosesin durumları için enum

    public int r;
    public int g;
    public int b;

    // multiple comparators kuyrukları dizmek için
    public static final Comparator<MyProcess> BY_ARRIVAL_TIME=new ArrivalTimeComparor();
    public static final Comparator<MyProcess> BY_PRIORITY=new PriorityComparor    ();
    public static final Comparator<MyProcess> BY_BURST_TIME=new BurstTimeComparor    ();

    public void startProcess  () throws IOException
    {
       
       /* List<String> list = new ArrayList<String>();  
        list.add("Gorevlendirici.jar");  
         ProcessBuilder builder = new ProcessBuilder();
       
        p = builder.start(); // process calişmaya başladı .
        this.processStatus = ProcesStatus.Basladi;
        System.out.println("Process başladı"+ this.Id);
    /*    while (this.p.isAlive())
        {
             System.out.println("Process yürütülüyor"+ this.Id);
        }*/

    }
    
    public void pauseProcess  (long timeout ) throws InterruptedException // saniye bazında 
    {       
        this.p.waitFor(timeout, TimeUnit.SECONDS);
        this.processStatus = ProcesStatus.Askida;
          System.out.println("Process askıda"+ this.Id);
    }
   
    public void endProcess ()
    {
        this.p.destroy();
        this.processStatus = ProcesStatus.Sonlandi;
        System.out.println("Process sonlandı"+ this.Id);
    }
    public enum  ProcesStatus{
        Basladi,
        Askida,
        Yurutuluyor,
        Sonlandi,
        ZamanAsimi
   }

    // Constructerımız 
    public MyProcess(int Id, int arrivalTime, int burstTime, int priority) {
        
        this.Id = Id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        changeColor(); //random renk ataması için

    }
   
    // Set and Get methodları
    public int getId() {
        return  Id;
    }

    public void setMyProcessId(int  Id) {
        this.Id =  Id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
   
    public void writeMyProcess (MyProcess process) //Prosesin bilgilerini yazan fonk.
   {
    

       System.out.print((char)27 +"[38;2;"+process.r+";"+process.g+";"+process.b+"m"+process.getId()+" id prosesi "+ process.getProcessStatus().toString());
   }


    public ProcesStatus getProcessStatus() {
        return this.processStatus;
    }

    public void setId(int id) {
        Id = id;
    }

    public Process getP() {
        return p;
    }

    public void setP(Process p) {
        this.p = p;
    }

    public void setProcessStatus(ProcesStatus processStatus) {
        this.processStatus = processStatus;
    }

    public static Comparator<MyProcess> getByArrivalTime() {
        return BY_ARRIVAL_TIME;
    }

    public static Comparator<MyProcess> getByPriority() {
        return BY_PRIORITY;
    }

    public static Comparator<MyProcess> getByBurstTime() {
        return BY_BURST_TIME;
    }

     @Override
    public OutputStream getOutputStream() {
        return null;
    }

    @Override
    public InputStream getInputStream() {
        return null;
    }

    @Override
    public InputStream getErrorStream() {
        return null;
    }

    @Override
    public int waitFor() throws InterruptedException {
        return 0;
    }

    @Override
    public int exitValue() {
        return 0;
    }

    @Override
    public void destroy() {

    }

    public void changeColor(){ //Renk atama fonksiyonu
        
        Random rndm=new Random();
        int r=rndm.nextInt(0,100);
        this.r=r;
        int g=rndm.nextInt(0,100);
        this.g=g;

        int b=rndm.nextInt(0,100);
        this.b=b;

     //   System.out.println((char)27 +"[38;2;"+r+";"+g+";"+b+"m" );
    }
}