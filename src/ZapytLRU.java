/**
 * Created by qwerty on 2016-05-13.
 */
public class ZapytLRU {
    int warosc;
    String reference;
    int referenceSize;

    public ZapytLRU (int warosc, String reference, int referenceSize)
    {
        this.warosc=warosc;
        this.reference=reference;
        this.referenceSize=referenceSize;
    }
    public  ZapytLRU (int warosc)
    {
        this.warosc = warosc;
        this.reference="0000";
        this.referenceSize=4;
    }

    public String move ()
    {
        this.reference=this.reference.substring(0,this.reference.length()-1);
        return this.reference;
    }

    public void addRight (int a)
    {
        if (a==0) reference=reference+"0";
        if (a==1) reference=reference+"1";

    }


}
