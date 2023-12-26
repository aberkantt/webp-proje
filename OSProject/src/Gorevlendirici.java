import java.io.*;
import java.util.*;
public class Gorevlendirici {

    public int zaman=0;
    public int count; // List size
    FCFS fcfs=new FCFS(); //kuyruk sınıfını kullanarak FCFS kuyruğu oluşturma
    BirinciOncelikliKuyruk birinciOncelik=new BirinciOncelikliKuyruk(); //kuyruk sınıfını kullanarak Öncelikli kuyruk oluşturma
    IkinciOncelikliKuyruk ikinciOncelik=new IkinciOncelikliKuyruk(); //kuyruk sınıfını kullanarak Öncelikli kuyruk oluşturma
    RoundRobin roundRobin =new RoundRobin(); //kuyruk sınıfını kullanarak Round Robin kuyruğu oluşturma
    MyProcess calisanProses; // O anda çalışan proses tutulur.
    int c; //proses listesini uzunluğu

    public void KuyrugaGonder(List<MyProcess> prosesler) throws IOException { //Kuyruklara dağıtım işlemi
     
         count = prosesler.size();
         c=count;       

        for(int i=0;i<prosesler.size();i++)
        {

            if(prosesler.get(i).getPriority()==0) //önceliği 0 olan FCFS ye eklenir.
            {
                
                fcfs.Ekle(prosesler.get(i));
                fcfs.KuyrukUpdate(prosesler);   //Her eklemede kuyruklar geliş ve patlama zamanlarına göre sıralanır.
                fcfs.Calistir(prosesler.get(i));
                
            }  
            else if(prosesler.get(i).getPriority()==1) //önceliği 1 olan birinci öncelikli kuyruğa eklenir.
            {
               
                birinciOncelik.Ekle(prosesler.get(i));
                birinciOncelik.KuyrukUpdate(prosesler);
                birinciOncelik.Calistir(prosesler.get(i));
              

            }

            else if(prosesler.get(i).getPriority()==2) //önceliği 2 olan ikinci öncelikli kuyruğa eklenir.
            {   
                ikinciOncelik.Ekle(prosesler.get(i));
                ikinciOncelik.KuyrukUpdate(prosesler);
                ikinciOncelik.Calistir(prosesler.get(i));
            }
            else if(prosesler.get(i).getPriority()==3) //önceliği 3 olan RoundRobin e eklenir.
            {
               
                roundRobin.Ekle(prosesler.get(i));
                roundRobin.KuyrukUpdate(prosesler);
                roundRobin.Calistir(prosesler.get(i));

            }

            
        }        
        
    }
   
    public void Calistir1() throws IOException
    {
                
        for(int i=0;i<c;i++) //proses sayısı kadar döner.
        {
            
            if(fcfs.processes.size()>0&&calisanProses==null) //eğer çalışan proses yoksa ve fcfs kuyruğu boş değilse
            {
                if(fcfs.processes.peek().arrivalTime<=zaman) //eğer ilk eklenen prosesin geliş zamanı gerçek zamandan küçük veya eşitse
                {
                    MyProcess p=fcfs.processes.poll(); //kuyruktan silinir.
                    
                    calisanProses=p;//Çalışan prosese atanır.
                   
                }
                
            }
            if(birinciOncelik.processes.size()>0 &&calisanProses==null) //eğer çalışan proses yoksa ve birinciOncelik kuyruğu boş değilse
            {

                 if( birinciOncelik.processes.peek().arrivalTime<=zaman)//eğer ilk eklenen prosesin geliş zamanı gerçek zamandan küçük veya eşitse
                {
                    MyProcess p1=birinciOncelik.processes.poll(); //kuyruktan silinir.
      
                    calisanProses=p1;
                    p1.setBurstTime(p1.getBurstTime()-1);//patlama zamanı 1 azaltılır.
                    
                    if(p1.getBurstTime()>0)
                    {
                        ikinciOncelik.Ekle(p1); //Patlama zamanı 0dan küçükse sonlanır.
                    }
                  
                    
                }
                 
            }
        
            if(ikinciOncelik.processes.size()>0 &&calisanProses==null)
            {

    
                 if(ikinciOncelik.processes.peek().arrivalTime<=zaman)
                {
                    MyProcess p2=ikinciOncelik.processes.poll();
                   
                    p2.setBurstTime(p2.getBurstTime()-1);
                    calisanProses=p2;
                   
                    if(p2.getBurstTime()>0)
                    {
                     roundRobin.Ekle(p2);

                    }
                    
                }
                 
            }
            if(calisanProses!=null) //Çalışan prosesin ekrana yazdırılması
            {
                 if(calisanProses.getPriority()==0)
                    {
                        
                        for(int z=calisanProses.burstTime;z>0;z--)
                        { 
                            calisanProses.writeMyProcess(calisanProses);
                            System.out.print(" "+zaman+ ". sn ");
                            System.out.println(" (kalan sure: "+z+" oncelik: "+calisanProses.priority+")  ");
                            zaman++;
                        }
                    
                            calisanProses.setProcessStatus(MyProcess.ProcesStatus.Sonlandi);
                            calisanProses.writeMyProcess(calisanProses);
                            System.out.print(" "+zaman+ ". sn ");
                            System.out.println(" (kalan sure: 0 "+" oncelik: "+calisanProses.priority+")  ");
                            
                    }
                    else
                    {
                        
                        calisanProses.writeMyProcess(calisanProses);
                        System.out.print(zaman+ ". sn ");
                        System.out.print(" (kalan sure: "+calisanProses.getBurstTime()+" oncelik: "+calisanProses.priority+")  ");
                        System.out.println("\n");
                        zaman++;
                        if(calisanProses.getBurstTime()>0)
                        {
                            calisanProses.setProcessStatus(MyProcess.ProcesStatus.Askida);
                            calisanProses.writeMyProcess(calisanProses);
                            System.out.print(" "+zaman+ ". sn ");
                            System.out.print(" (kalan sure: "+calisanProses.getBurstTime()+" oncelik: "+calisanProses.priority+")  ");
                            System.out.println("\n");
                        }
                        else if(calisanProses.getBurstTime()==0)
                        {
                             calisanProses.setProcessStatus(MyProcess.ProcesStatus.Sonlandi);
                            calisanProses.writeMyProcess(calisanProses);
                            System.out.print(" "+zaman+ ". sn ");
                            System.out.print(" (kalan sure: "+calisanProses.getBurstTime()+" oncelik: "+calisanProses.priority+")  ");
                            System.out.println("\n");
                        }
                        
                    }
                    calisanProses=null;
            }

            
            //Zaman aşımına uğrama işlemi her kuyruk için ayrı ayrı tarama işlemi yapılıp bekleme zamanına göre işlem yapılır. 
            
            BirinciOncelikliKuyruk kontrolKuyruguBirinci=new BirinciOncelikliKuyruk(); //Geçici kuyruk oluşturulur.
            
            MyProcess pb1;
            while((pb1 = birinciOncelik.processes.poll()) != null) //kuyruktan sile sile ilerlenir.
            {
                int fark=zaman-pb1.getArrivalTime();
                if(fark>=20)
                {
                    
                    System.out.println((char)27 +"[38;2;"+pb1.r+";"+pb1.g+";"+pb1.b+"m"+pb1.getId()+" prosesi zaman aşımına uğradı");
                   
                }
                else
                {
                    kontrolKuyruguBirinci.Ekle(pb1);
                }
                
            }
            birinciOncelik=kontrolKuyruguBirinci; //geçici kuyruk asıl kuyruğa eşitlenir.
            MyProcess pb2;
            IkinciOncelikliKuyruk kontrolKuyruguIkinci=new IkinciOncelikliKuyruk();

            while((pb2 = ikinciOncelik.processes.poll()) != null)
            {                             

                 int fark=zaman-pb2.getArrivalTime();
                if(fark>=20)
                {
                    System.out.println((char)27 +"[38;2;"+pb2.r+";"+pb2.g+";"+pb2.b+"m"+pb2.getId()+" prosesi zaman aşımına uğradı");
                   
                }
                else
                {
                     kontrolKuyruguIkinci.Ekle(pb2);
                }

            }
                ikinciOncelik=kontrolKuyruguIkinci;


            MyProcess p3;
            RoundRobin kontrolKuyruguRR=new RoundRobin();


            while((p3 = roundRobin.processes.poll()) != null)
            {
                int fark=zaman-p3.getArrivalTime();
        
                
                if(fark>=20)
                {
                    System.out.println((char)27 +"[38;2;"+p3.r+";"+p3.g+";"+p3.b+"m"+p3.getId()+" prosesi zaman aşımına uğradı");
                    
                }
                else
                {
                        kontrolKuyruguRR.Ekle(p3);
                }

            

            }
            roundRobin=kontrolKuyruguRR;
              
        }
        
        MyProcess p4;
        while((p4 = roundRobin.processes.peek()) != null) 
        {
                roundRobin.processes.poll();
                calisanProses=p4;
                p4.writeMyProcess(p4);
                p4.setBurstTime(p4.getBurstTime()-1);
                 calisanProses.writeMyProcess(calisanProses);
                        System.out.print(zaman+ ". sn ");
                        System.out.print(" (kalan sure: "+calisanProses.getBurstTime()+" oncelik: "+calisanProses.priority+")  ");
                        System.out.println("\n");
                        
                zaman++;
                calisanProses.setProcessStatus(MyProcess.ProcesStatus.Askida);
                  calisanProses.writeMyProcess(calisanProses);
                        System.out.print(zaman+ ". sn ");
                        System.out.print(" (kalan sure: "+calisanProses.getBurstTime()+" oncelik: "+calisanProses.priority+")  ");
                        System.out.println("\n");
                int fark=zaman-p4.getArrivalTime();

                if(fark>=20)
            {
                    System.out.println((char)27 +"[38;2;"+p4.r+";"+p4.g+";"+p4.b+"m"+p4.getId()+" prosesi zaman asimina ugradi");
                
            }
                else if(p4.burstTime>0)
                {
                    roundRobin.Ekle(p4);
                }
                    

        }

    }

}
