import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.*;

public class Scheduler { //Dosya Okuma işlemi için oluşturulan sınıf
    
    
    public ArrayList<MyProcess> readFile(String url) throws FileNotFoundException, IOException
    {

        int processId=0;  //unique id için her gelen proseste id artırılıp atanır.

        String fileName = url; //dosya uzantısı

        ArrayList<MyProcess> processes; //process listesi
        MyProcess process = null;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line = null;
        String ilkveri = null;
        String ikinciveri = null;
        String ucuncuveri = null;
       
       try{
        processes = new ArrayList<MyProcess>();
        while ((line = bufferedReader.readLine()) != null) {
            
            String[] arrOfStr = line.split("[, ?.@]+");
            int i = 0;
            while (i < 3) {
                ilkveri = arrOfStr[i];
                int arrivalTime = Integer.parseInt(ilkveri);
                i++;
                ikinciveri = arrOfStr[i];
                int priority = Integer.parseInt(ikinciveri);
                i++;
                ucuncuveri = arrOfStr[i];
                int burstTime = Integer.parseInt(ucuncuveri);
                
                i++;
                process = new MyProcess(   //Atama işlemleri yapılır.
                processId,  // ProcessID
                arrivalTime,  // ArrivalTime             
                burstTime,  // BurstTime
                priority); // Priority

                processes.add(process);
                processId++;
            }
            }
            return processes;
            
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } 
       
    }

}      
    


