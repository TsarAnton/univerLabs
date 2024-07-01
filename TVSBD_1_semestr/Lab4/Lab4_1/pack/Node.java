package pack;

public class Node<Type> {
    public Type data; 
    public Node<Type> next; 
   
   public Node(Type data) {
       this.data = data;
   }

   public Type getData() {
       return data;
   }
}
