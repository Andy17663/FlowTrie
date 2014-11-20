package ruleTrie;
import java.util.BitSet;

public class Ternary {
    private final  BitSet value = new BitSet(2);
    public Ternary(BitSet bit){
        if (bit.size() == 1 && bit.get(0) ){
            //bit is 1, transfer to 11
            value.set(0,1);
            value.set(1,1);
        }
        else if (bit.size() == 1 && !(bit.get(0)) ){
            //bit is 0, transfer to 00
            value.set(0,0);
            value.set(1,0);
        }
        else{
           System.out.print("error");
        }



    }
    public Ternary(){
        //bit is star, transfer to 01
        value.set(0,0);
        value.set(1,1);


    }
    private BitSet getValue(){
        return value;
    }

}
