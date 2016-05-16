import java.util.ArrayList;

/**
 * Created by qwerty on 2016-05-09.
 */
public class  Alg {

    public int fifo(int[] tab, int wlk)
    {

        ArrayList<Integer> arr = new ArrayList<>();
        int licznik = 0;
        for (int i=0;i<tab.length;i++)
        {
            for (int h:arr)
            {System.out.println(h);}
            System.out.println("Do wpisania: " +tab[i]);
            System.out.println("Licznik: " +licznik);
            if (arr.size()<wlk&&!arr.contains(tab[i]))
            {
                arr.add(tab[i]);
            }
            else if (!arr.contains(tab[i])) {

                arr.remove(0);
                licznik++;
                arr.add(tab[i]);
                System.out.println("Licznik: " +licznik);
        }
        }
        return licznik;
    }

    public int opt (ArrayList<Zapyt> tab, int wlk)
    {
        ArrayList<Zapyt> arr = new ArrayList<Zapyt>();

        int licznik = 0;
        for (int i=0;i<tab.size();i++)
        {   Zapyt pom = new Zapyt(1);

           /* System.out.println("Do wpisania" + tab.get(i).wartosc);
            for (Zapyt za:arr)
                {System.out.println(za.wartosc + " "+ za.opt+ " " + za.ost);}
            System.out.println();
            System.out.println("===========");
            System.out.println();*/
            if (arr.size()<wlk&&!(pom.zawiera(arr,tab.get(i))))
            {
                arr.add(tab.get(i));
            }
            if (!(pom.zawiera(arr,tab.get(i)))) {
            {
                for (int up=0;up<arr.size();up++)
                {
                    int war = arr.get(up).wartosc;
                        for (int k =i;k<tab.size();k++)
                         {
                           if (war==tab.get(k).wartosc)
                            {
                                arr.get(up).setOpt(k-i);
                                break;
                            }

                         }
                     }
                }
                /*for (Zapyt za:arr)
                {System.out.println("wyliczenie odleglosci" );
                    System.out.println(za.wartosc + " "+ za.opt+ " " + za.ost);}
                System.out.println();
                System.out.println("===========");
                System.out.println();*/
                int max =arr.get(0).opt;
                int maxIndex=0;
                for (int up=0;up<arr.size();up++)
                {
                    if (arr.get(up).opt>max)
                    max=arr.get(up).opt;
                }

                for (int up=0;up<arr.size();up++) {
                    if (arr.get(up).opt == max) {
                        arr.remove(up);
                        arr.add(tab.get(i));
                        licznik++;
                        break;
                    }
                }
            }
         //   System.out.println("Licznik"+ licznik);
        }
        return licznik;
    }

    int lru (ArrayList<Zapyt> tab, int wlk)
    {   int licznik =0;
        ArrayList<Zapyt> arr = new ArrayList<Zapyt>();
        Zapyt pom = new Zapyt(1);

        for (int i=0;i<tab.size();i++)
        {
            if (arr.size()<wlk&&!(pom.zawiera(arr,tab.get(i))))
            {
                arr.add(tab.get(i));
            }
            else  if (!(pom.zawiera(arr,tab.get(i)))) {
              /*  System.out.println("Licznik"+ licznik);*/
                int min = arr.get(0).wartosc;
                int minIndex = 0;
                for (int k=0;k<arr.size();k++ )
                {
                    if (arr.get(k).ost<k)
                    {min=arr.get(k).ost;
                    minIndex=k;}
                }
                for (int k=0;k<arr.size();k++ )
                {
                    if (arr.get(k).ost==min)
                    {   arr.remove(minIndex);
                        arr.add(tab.get(i));}
                }
                for (int k=0;k<arr.size();k++ )
                {
                   arr.get(k).ost++;
                }

                licznik++;

            }

        }
        return licznik;
    }

    public int aLru (ArrayList<ZapytLRU> tab, int wlk){
        int licznik =0;
        int index=0;
        ArrayList<ZapytLRU> pom  = new ArrayList<ZapytLRU>();
        for (int i=0;i<tab.size();i++)
        {
            if (zaw(pom,tab.get(i)))
            {
              ZapytLRU w= pom.get(indexOfZaw(pom,tab.get(i)));
                w.move();
                w.reference="1"+w.reference;
                pom.set(indexOfZaw(pom,tab.get(i)),w);
                continue;
            }
            if (!zaw(pom,tab.get(i))&&pom.size()<wlk)
            {pom.add(tab.get(i));

            }
            else
            {

                  licznik++;
                pom.remove(indexOfBest(pom));
                for (ZapytLRU q:pom)
                {
                 q.move();
                   q.reference="0"+q.reference;
                }



        }
        }

        return licznik;
}

    public int drugaSzansa (ArrayList<ZapytLRU> tab, int wlk)
    {
        int licznik = 0;
        ArrayList<ZapytLRU> pom  = new ArrayList<ZapytLRU>();
        for (int i=0;i<tab.size();i++)
        {
            if (zaw(pom,tab.get(i)))
            {
                ZapytLRU w= pom.get(indexOfZaw(pom,tab.get(i)));
                w.reference="1";
                pom.set(indexOfZaw(pom,tab.get(i)),w);
                continue;
            }
            if (!zaw(pom,tab.get(i))&&pom.size()<wlk)
            {pom.add(tab.get(i));

            }
            else
            {
                for (ZapytLRU a:pom)
                {
                    if (a.reference.equals(1))
                    {pom.remove(a);
                        a.reference="0";
                        pom.add(a);}
                }
                licznik++;

                for (ZapytLRU q:pom)
                {
                    if (q.reference.equals(0)) {pom.remove(q);
                    break;}
                }



            }
        }

        return licznik;
    }
    public int indexOfBest (ArrayList<ZapytLRU> tab)
    {
        ArrayList <Integer> tablicz= new ArrayList<Integer>();
        for (ZapytLRU a:tab)
        {
            tablicz.add(Integer.parseInt(a.reference));
        }
        Integer max =tablicz.get(0); // naprawde minimum
        for (int i=0;i<tablicz.size();i++)
        {
            if (tablicz.get(i)<max) max=tablicz.get(i);
        }

        return tablicz.indexOf(max);
    }

    public boolean zaw (ArrayList <ZapytLRU> tab, ZapytLRU zap)
    {
        for (ZapytLRU a:tab)
        {
            if (a.warosc==zap.warosc) return true;
        }
        return false;
    }

    public int indexOfZaw (ArrayList <ZapytLRU> tab, ZapytLRU zap)
    {
        int index=0;
        for (int i =0;i<tab.size();i++)
        {
            if (tab.get(i).warosc==zap.warosc) return i;
        }
        return -1;
    }
    }







