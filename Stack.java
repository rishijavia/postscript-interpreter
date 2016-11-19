/**
 * Created by root on 11/18/16.
 */
public class Stack {
    private int top;
    private String[] data;

    public Stack(){
        data = new String[100];
        top = -1;
    }

    public void push(String element) {
        if (top == data.length){
            throw new java.util.NoSuchElementException("Cannot add element. Stack overflow.");
        }
        data[++top] = element;
    }

    public String pop(){
        if (top == -1){
            throw new java.util.NoSuchElementException("Stack is empty (from pop)");
        }
        return data[top--];
    }

    public String peek(int index){
        if (index > data.length || index > top){
            throw new java.util.NoSuchElementException("No element found at that index");
        }
        return data[top - index];
    }

    public void print(){
        if (top == -1){
            throw new java.util.NoSuchElementException("Stack is empty (from printing)");
        }
        for (int i = 0; i <= top; i++){
            System.out.print(data[i] + " ");
        }
        System.out.println("\n");
    }


}
