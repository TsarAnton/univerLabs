package pack;

public class Queue<Type> {
	private Node<Type> first;
	private Node<Type> last;
	
	public Queue() {
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		return (first==null);
	}
	
	public void push(Type data) {
		Node<Type> newNode = new Node<Type>(data);
		if (isEmpty()) {
			newNode.next = null;
			first = newNode;
		} else {
			last.next = newNode;
			newNode.next = null;
		}
		last = newNode;
	}
	
	public Type pop() {
		if(isEmpty()) {
			return null;
        }
		Node<Type> temp = first;
		first = first.next;
		return temp.getData();
	}

	public Type safePop() {
		if(isEmpty()) {
			return null;
		}
		return first.getData();
	}
}
