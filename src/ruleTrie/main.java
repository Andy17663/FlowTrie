package ruleTrie;
import java.util.BitSet;


public class main {
    public static void main(String[] args) {


        String src_ip="192.168.28.1";
        String mask = "255.255.224.0";
        String dst_ip;
        String flowkey = "18654";



        String src_ip2="192.168.28.90";
        String mask2 = "255.255.255.255";

        String flowkey2 = "17726";




        BitSet[] arrBitset = new BitSet[32];
        arrBitset = IpConvertUtils.ipToTernary(src_ip, mask);




        for (int i=0;i<=31;i++){
            System.out.print(arrBitset[i].get(0));
            System.out.print(".");
            System.out.print(arrBitset[i].get(1));
            System.out.print(" , ");
        }






        Node rootNode = new Node();
        FlowTrie flowTrie = new FlowTrie(rootNode);


        flowTrie.addFlow(arrBitset, flowkey);

        flowTrie.addFlow(IpConvertUtils.ipToTernary(src_ip2, mask2), flowkey2);




        System.out.println("");
        System.out.println("****************");
        System.out.print(flowTrie.queryIncludeFlow(arrBitset ));


        System.out.println("");
        System.out.println("****************");
        System.out.print(flowTrie.queryIncludeFlow(IpConvertUtils.ipToTernary(src_ip2, mask2)));
        //System.out.println("");
       // System.out.println("****************");
        //System.out.print(flowTrie.queryIncludeFlow(arrBitset ));
        //System.out.print(flowTrie.queryIncludeFlow( IpConvertUtils.ipToTernary(src_ip2, mask2)  ));




        //System.out.println(convertUtils.fromByteArray(  convertUtils.ipToByte(mask)).get(7));

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
      public static BitSet convert(long value) {
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

      public static long convert(BitSet bits) {
        long value = 0L;
        for (int i = 0; i < bits.length(); ++i) {
          value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
      }
}