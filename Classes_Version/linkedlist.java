public class linkedlist {       //https://www.youtube.com/watch?v=SMIq13-FZSE
    
    Node head;

    public void put(patient thing){
        Node node = new Node();
        node.data = thing;
        node.next =null;

        if(head==null){
            head = node;
        }
        else{
            Node n = head;                     //the previous value (the head value)
            while(n.next!=null){               //travelling through the link list
                n = n.next;
            }
            n.next = node;
        }

    }




}
