import java.util.ArrayList;

/**
 * Created by qwerty on 2016-05-09.
 */
public class Zapyt {
    int wartosc;
    int opt;
    int ost;

    public int setOpt(int op) {
        int w = this.opt;
        this.opt = op;
        return w;
    }

    public Zapyt(int w, int op, int os) {
        wartosc = w;
        opt = op;
        ost = os;
    }

    public Zapyt(int w) {
        wartosc = w;
        opt = 0;
        ost = 0;
    }

    public boolean zawiera (ArrayList<Zapyt> tab, Zapyt zap)
    {
        for (int i=0;i<tab.size();i++)
        {
            if (tab.get(i).wartosc==zap.wartosc)
                return true;
        }
        return false;
    }
}