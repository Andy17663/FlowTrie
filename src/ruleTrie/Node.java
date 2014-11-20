package ruleTrie;
import java.util.Vector;

public class Node {


    private  Node right;
    private  Node left;
    private  Node middle;
    Vector<String> flowKeys=new Vector<String>();

    public Node(Node right,Node left,Node middle){
        this.right=right;
        this.left=left;
        this.middle=middle;


    }
    public Node(){
        this.right=null;
        this.left=null;
        this.middle=null;


    }

    public Node getRight(){
        return right;
    }
    public Node getLeft(){
        return left;
    }
    public Node getMiddle(){
        return middle;
    }



    public void setRight(Node node){
        this.right = node;
    }
    public void setLeft(Node node){
        this.left = node;
    }
    public void setMiddle(Node node){
        this.middle = node;
    }
    public Vector<String> getFlowKeys(){
        return this.flowKeys;
    }
    public void addFlowKey(String flowKey){
        flowKeys.add(flowKey);
    }

}
