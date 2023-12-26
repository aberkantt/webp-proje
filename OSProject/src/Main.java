import java.util.List;
import java.io.*;
public class Main{
	public static void main(String[] args) {
	    if (args.length == 0) {
	        System.out.println("Lütfen dosya yolu argümanı giriniz.");
	        return; // Programı sonlandır
	    }

	    try {
	        Scheduler scheduler = new Scheduler();
	        List<MyProcess> processList = scheduler.readFile(args[0]);
	        Gorevlendirici gorevlendirici = new Gorevlendirici();

	        gorevlendirici.KuyrugaGonder(processList);
	        gorevlendirici.Calistir1();

	    } catch (FileNotFoundException e) {
	        System.out.println("Dosya bulunamadı. Doğru path giriniz.");
	    } catch (IOException e) {
	        System.out.println("Tekrar deneyin.");
	    }
	}
}