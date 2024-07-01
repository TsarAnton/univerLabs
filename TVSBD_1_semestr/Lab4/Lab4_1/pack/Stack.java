package pack;

public class Stack<Type> {

    private int size;
    private Type[] array;
    private int top;
 
    public Stack(int size) {
        this.size = size;
        array = (Type[]) new Object[size];
        top = -1;
    }

    public void push(Type element) {
        if(top == size - 1) {
            return;
        } else {
            array[++top] = element;
        }
    }

    public Type pop() {
        return array[top--];
    }

    public Type safePop() {
        return array[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == size - 1);
    }
}
