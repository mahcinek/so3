import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int pam = 3; //wielkość pamięci służąca do wywoływania metod

        /*
        *
        * Dużo nieporzebnych powtórzeń, ale main działa, co prawda jest troche śmieci
        * */

        int [] aktywnaPamiec = new int[5];
        int [] dysk = new int [1200];
        ArrayList<double []> lisaZapytan = new ArrayList<double[]>();
        Random k = new Random();
        int [] zapyt = new int[10];

        for (int i = 0; i<10;i++)
        {
            zapyt[i]= k.nextInt(20);
            lisaZapytan.add(generuj(zapyt[i]));
        }
        int [] wartosci = new int[1200];
        int a = 0;

        for (int i = 0 ;i<lisaZapytan.size();i++)
        {
            for (int u =0;u<lisaZapytan.get(i).length;u++){
                a++;
            }
        }
        int w=0;
        int [] wartos = new int[a];
        for (int i = 0 ;i<lisaZapytan.size();i++)
        {
            for (int u =0;u<lisaZapytan.get(i).length;u++){
                wartos [w] =(int) lisaZapytan.get(i)[u];
                w++;
            }
        }
        ArrayList<Integer> dowysl = new ArrayList<>();
        for (int wart :wartos)
        {
            dowysl.add(wart);
        }
        Alg alg = new Alg();
        int rand=alg.random(wartos,pam);
        int liczfifo= alg.fifo(wartos,pam);
        System.out.println("Random:"+rand);
        System.out.println("FIFO:"+liczfifo);
        Zapyt [] zapytan =new Zapyt[wartos.length];
        ArrayList<Zapyt> dowyslZapyt = new ArrayList<Zapyt>();
        for (int kl:wartos)
        {
            dowyslZapyt.add(new Zapyt(kl));
        }
        //for (Zapyt za:dowyslZapyt)
        //{System.out.println(za.wartosc + " "+ za.opt+ " " + za.ost);}

        int opt = alg.opt(dowyslZapyt,pam);
        int ost =alg.lru(dowyslZapyt,pam);
        System.out.println("optym:" + opt);
        System.out.println("LRU"+ ost);
        ArrayList<ZapytLRU>ar = new ArrayList<ZapytLRU>();
        ArrayList<ZapytLRU>ar2 = new ArrayList<ZapytLRU>();
        for (int y:wartos)
        {
            ar.add(new ZapytLRU(y));
            ar2.add(new ZapytLRU(y,"0",1));
        }
        int alru= alg.aLru(ar,pam);
        ArrayList<ZapytLRU> pam2 = new ArrayList<ZapytLRU>();
        System.out.println("Dodatkowe bity odwołań" + alru);
        for (ZapytLRU r:ar)
        {
            pam2.add(r);
        }
        int alru2 = alg.drugaSzansa(pam2,pam);
        System.out.println("Druga szansa"+ alru2);

//        int opt= alg.opt(zapytan,5);
  //      System.out.println(opt);
//        int ost = alg.lru(zapytan,5);
  //      System.out.println(ost);
    }
    public static double [] generuj(double przsuniecie) {
        Random rand = new Random();
        double[] tab = new double[350];
        for (int i = 0; i < 350; i++) {
            double g = rand.nextGaussian();
            g = g * 2;
            g = Math.min(g, 3);
            g = Math.max(g, -5);
            g = g + przsuniecie;
            g= Math.max(g,0);
            tab[i] = g;
        }
        return tab;
    }


}


