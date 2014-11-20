package ruleTrie;
import java.util.BitSet;

public class IpConvertUtils {


    private final String ipAddress;
    private final String mask;
    public IpConvertUtils(String ipAddress, String mask){
        this.ipAddress = ipAddress;
        this.mask = mask;

    }

    public static BitSet[] ipToTernary(String ipAddress, String mask){
        BitSet[] arrBitset = new BitSet[32];
        long Lipaddr =  ipToLong(ipAddress);
        long Lmask =  ipToLong(mask);
        BitSet Bipaddr = longToBitset(Lipaddr);
        BitSet Bmask = longToBitset(Lmask);

        int startFalsePoint = 32-Bmask.nextSetBit(0);

        for (int i = 0; i<=31; i++){
            if (i>=startFalsePoint){
                arrBitset[i] = bitToTernary();
            }
            else{

                arrBitset[i] = bitToTernary(Bipaddr.get(31-i));
            }
        }
        return arrBitset;

    }

    public static long ipToLong(String ipAddress) {

        String[] ipAddressInArray = ipAddress.split("\\.");

        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {

            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);

        }

        return result;
      }
      public static BitSet longToBitset(long value) {
        BitSet bits = new BitSet();
        int index = 0;
        while (value != 0L) {
          if (value % 2L != 0) {
            bits.set(index);
          }
          ++index;
          value = value >>> 1;
        }
        return bits;
      }

      public static long BitsetToLong(BitSet bits) {
        long value = 0L;
        for (int i = 0; i < bits.length(); ++i) {
          value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
      }

      public static BitSet bitToTernary(boolean bit){

          BitSet value = new BitSet(2);
          if (bit){
              //bit is 1, transfer to 11
              value.set(0,true);
              value.set(1,true);
          }
          else{
              //bit is 0, transfer to 00
              value.set(0,false);
              value.set(1,false);
          }
          return value;
      }

      public static BitSet bitToTernary(){
          //If bit is *, transfer to 01
          BitSet value = new BitSet(2);
          value.set(0,false);
          value.set(1,true);
          return value;
      }


}
