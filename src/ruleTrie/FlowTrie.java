package ruleTrie;
import java.util.BitSet;
import java.util.Vector;

public class FlowTrie {

    private final Node rootNode;

    public FlowTrie(Node rootNode){
        this.rootNode = rootNode;

    }
    public void addFlow(BitSet[] flowTernay, String flowKey){
        System.out.println("flowTernay.length: "+flowTernay.length);

        addFlow(flowTernay, rootNode, flowKey,0);

    }

    private void addFlow(BitSet[] flowTernay, Node node, String flowKey, int index){

        bitType type = ternaryToType (flowTernay[index]);
        /*System.out.print(type+",");

        if (index ==  flowTernay.length - 1 ){
            System.out.print("last!~");
        }
        else{
            addFlow(flowTernay,null,null,index + 1);
        }*/


        switch (type){
            case ONE:
                System.out.print("ONE,"+index+",");

                if (index ==  flowTernay.length - 1 ){
                    //reach leaf
                    System.out.print("reach leaf ,right |");
                    if (node.getRight() != null){

                        node.getRight().addFlowKey(flowKey);
                    }
                    else{
                        System.out.print("create right newLeafNode.   ");
                        Node newLeafNode = new Node();
                        newLeafNode.addFlowKey(flowKey);
                        node.setRight(newLeafNode);
                        System.out.println("reach leaf(right),key:"+newLeafNode.getFlowKeys());
                    }

                }
                else{
                    if (node.getRight() == null){
                        System.out.print("create right newInterNode.  ");
                        Node newInterNode = new Node();

                        node.setRight(newInterNode);
                    }
                    addFlow(flowTernay, node.getRight(), flowKey, index + 1);
                }
                break;
            case ZERO:
                System.out.print("ZERO,"+index+",");
                if (index ==  flowTernay.length - 1 ){
                    //reach leaf
                    System.out.print("reach leaf ,left");
                    if (node.getLeft() != null){
                        node.getLeft().addFlowKey(flowKey);

                    }
                    else{
                        System.out.print("create left newLeafNode.  ");
                        Node newLeafNode = new Node();
                        newLeafNode.addFlowKey(flowKey);
                        node.setLeft(newLeafNode);

                        System.out.println("reach leaf(left),key:"+newLeafNode.getFlowKeys());
                    }


                }
                else{
                    if (node.getLeft() == null){
                        System.out.print("create left newInterNode.  ");
                        Node newInterNode = new Node();
                        node.setLeft(newInterNode);
                    }
                    addFlow(flowTernay, node.getLeft(), flowKey, index + 1);
                }
                break;
            case STAR:
                System.out.print("STAR,"+index+",");
                if (index ==  flowTernay.length - 1 ){
                    //reach leaf

                    if (node.getMiddle() != null){
                        node.getMiddle().addFlowKey(flowKey);
                    }
                    else{
                        System.out.print("create middle newLeafNode.  ");
                        Node newLeafNode = new Node();
                        newLeafNode.addFlowKey(flowKey);
                        node.setMiddle(newLeafNode);
                        System.out.println("reach leaf(middle),key:"+newLeafNode.getFlowKeys());
                    }


                }
                else{
                    if (node.getMiddle() == null){
                        System.out.print("create middle newInterNode.  ");
                        Node newInterNode = new Node();
                        node.setMiddle(newInterNode);
                    }
                    addFlow(flowTernay, node.getMiddle(), flowKey, index + 1);
                }
                break;
        }

    }

    public Vector<String> queryIncludeFlow(BitSet[] flowTernay){
        return queryIncludeFlow(flowTernay, rootNode, 0);
    }

    private Vector<String> queryIncludeFlow(BitSet[] flowTernay, Node node, int index){
        // Query flow which header space is covered by this flow
        bitType type = ternaryToType (flowTernay[index]);
        switch (type){
            case ONE:
                System.out.print("ONE,"+index+",   ");
                if (index == flowTernay.length -1 ){
                    System.out.print(" reach one leaf");
                    Vector<String> flowKeys = node.getRight().getFlowKeys();
                    return  node.getRight() != null ? flowKeys : null;
                }
                else
                    return node.getRight() != null ?  queryIncludeFlow(flowTernay,node.getRight(),index+1) :  null ;

            case ZERO:
                System.out.print("ZERO,"+index+",   ");
                if (index == flowTernay.length -1 ){
                    System.out.print(" reach zero leaf");
                    Vector<String> flowKeys = node.getLeft().getFlowKeys();
                    return  node.getLeft() != null ? flowKeys : null;
                }
                else
                    return node.getLeft() != null ?  queryIncludeFlow(flowTernay,node.getLeft(),index+1) :  null ;


            case STAR:
                System.out.print("STAR,"+index+",   ");
                if (index == flowTernay.length -1 ){

                    Vector<String> flows = new Vector<String>();

                    if (node.getRight() != null){
                        System.out.print(" reach right leaf: "+node.getRight().getFlowKeys());
                        appendFlowKey(flows,node.getRight().getFlowKeys());
                    }
                    if ( node.getLeft() != null){
                        System.out.print(" reach left leaf: "+node.getLeft().getFlowKeys());
                        appendFlowKey(flows,node.getLeft().getFlowKeys());

                    }
                    if (node.getMiddle() != null) {
                        System.out.print(" reach middle leaf: "+node.getMiddle().getFlowKeys());
                        appendFlowKey(flows,node.getMiddle().getFlowKeys());

                    }

                    return flows;


                }
                else{
                    Vector<String> rightFlowKeys = new Vector<String>();
                    Vector<String> leftFlowKeys = new Vector<String>();
                    Vector<String> middleFlowKeys = new Vector<String>();

                    rightFlowKeys = node.getRight() != null ? queryIncludeFlow(flowTernay,node.getRight(),index+1) : null;

                    leftFlowKeys = node.getLeft() != null ? queryIncludeFlow(flowTernay,node.getLeft(),index+1) : null;

                    middleFlowKeys = node.getMiddle() != null ? queryIncludeFlow(flowTernay,node.getMiddle(),index+1) : null;


                    return  appendFlowKey( appendFlowKey(rightFlowKeys,leftFlowKeys), middleFlowKeys );
                }
            default:
                System.out.println("tenary error");
                return null;
        }
    }
    public Vector<String> queryCoverFlow(BitSet[] flowTernay){
        return queryCoverFlow(flowTernay,rootNode,0);
    }


    private Vector<String> queryCoverFlow(BitSet[] flowTernay, Node node, int index){
        // Query flow which header space covers this flow
        bitType type = ternaryToType (flowTernay[index]);

        switch (type){
            case ONE:
                if (index == flowTernay.length )
                    return node.getFlowKeys();

                else
                    return appendFlowKey(node.getRight() != null ? queryIncludeFlow(flowTernay,node.getRight(),index+1) : null,
                                         node.getMiddle() != null ? queryIncludeFlow(flowTernay,node.getMiddle(),index+1) : null);


            case ZERO:

                if (index == flowTernay.length )
                    return node.getFlowKeys();

                else
                    return appendFlowKey(node.getLeft() != null ? queryIncludeFlow(flowTernay,node.getLeft(),index+1) : null,
                                         node.getMiddle() != null ? queryIncludeFlow(flowTernay,node.getMiddle(),index+1) : null);


            case STAR:
                if (index == flowTernay.length )
                    return node.getFlowKeys();

                else
                    return node.getMiddle() != null ? queryIncludeFlow(flowTernay,node.getMiddle(),index+1) : null;

            default:
                System.out.println("tenary error");
                return null;
        }
    }






    private Vector<String> appendFlowKey(Vector<String> flows1, Vector<String> flows2){
        if (flows1 == null){
            if (flows2 == null)
                return null;
            else
                return flows2;
        }
        else{
            if (flows2 == null)
                return flows1;
            else{
                flows1.addAll(flows2);
                return flows1;
            }

        }

    }

    /*public void removeFlow(BitSet[] flowTernay){

    }


    private void removeFlow(BitSet[] flowTernay){

    }*/



    private bitType ternaryToType(BitSet bs){

        if (bs.get(0) && bs.get(1)){
            return bitType.ONE;
        }
        else if (!bs.get(0) && !bs.get(1)){
            return bitType.ZERO;
        }
        else if (!bs.get(0) && bs.get(1)){
            return bitType.STAR;
        }
        else{
            System.out.print("bit type error");
            return null;
        }


    }


    enum bitType{
        ONE,ZERO,STAR
    }
}
