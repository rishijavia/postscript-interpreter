import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Created by root on 11/18/16.
 * Author: Rishi Javia
 */
public class Core extends Stack{

    private Stack myStack;

    public Core(String filename){
        myStack = new Stack();
        try {
            Scanner input = new Scanner(new File(filename));
            while(input.hasNext()){
                Scanner line = new Scanner(input.nextLine());
                while(line.hasNext()){
                    String word = line.next();
                    switch (word){
                        case "pop": myStack.pop(); break;
                        case "exch": exch(); break;
                        case "dup": dup(); break;
                        case "pstack": myStack.print(); break;
                        case "index": index(); break;
                        case "copy": copy(); break;
                        case "roll": roll(); break;
                        case "add": add(); break;
                        case "sub": sub(); break;
                        case "mul": mul(); break;
                        case "div": div(); break;
                        case "idiv": idiv(); break;
                        case "mod": mod(); break;
                        case "sqrt": sqrt(); break;
                        case "sin": sin(); break;
                        case "cos": cos(); break;
                        default: myStack.push(word); break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void add() {
        double value1 = Double.parseDouble(myStack.pop());
        double value2 = Double.parseDouble(myStack.pop());
        myStack.push(String.valueOf(Math.round((value2 + value1)*10000.0)/10000.0));
    }

    private void sub() {
        double value1 = Double.parseDouble(myStack.pop());
        double value2 = Double.parseDouble(myStack.pop());
        myStack.push(String.valueOf(Math.round((value2 - value1)*10000.0)/10000.0));
    }

    private void mul() {
        double value1 = Double.parseDouble(myStack.pop());
        double value2 = Double.parseDouble(myStack.pop());
        myStack.push(String.valueOf(Math.round((value2 * value1)*10000.0)/10000.0));
    }

    private void div() {
        double value1 = Double.parseDouble(myStack.pop());
        double value2 = Double.parseDouble(myStack.pop());
        myStack.push(String.valueOf(Math.round((value2/value1)*10000.0)/10000.0));
    }

    private void idiv() {
        int value1 = Integer.parseInt(myStack.pop());
        int value2 = Integer.parseInt(myStack.pop());
        myStack.push(String.valueOf(value2/value1));
    }

    private void mod() {
        int value1 = Integer.parseInt(myStack.pop());
        int value2 = Integer.parseInt(myStack.pop());
        myStack.push(String.valueOf(value2%value1));
    }

    private void sqrt() {
        double value1 = Double.parseDouble(myStack.pop());
        myStack.push(String.valueOf(Math.sqrt(value1)));
    }

    private void sin() {
        double value1 = Double.parseDouble(myStack.pop());
        myStack.push(String.valueOf(Math.sin(Math.toRadians(value1))));
    }

    private void cos() {
        double value1 = Double.parseDouble(myStack.pop());
        myStack.push(String.valueOf(Math.cos(Math.toRadians(value1))));
    }

    private void roll() {
        int places = Integer.parseInt(myStack.pop());
        int elements = Integer.parseInt(myStack.pop());

        if (places == 0 || elements == 0){
            return;
        }

        String[] arr = new String[elements];
        int counter = 0;
        for(int i= elements - 1; i >= 0; i--){
            arr[counter++] = myStack.peek(i);
        }

        for(int j= 0; j < places; j++){
            String lastElement = arr[elements - 1];
            for(int i= elements - 2; i >= 0; i--){
                arr[i+1] = arr[i];
            }
            arr[0] = lastElement;
        }

        for(int i= 0; i<elements; i++){
            myStack.pop();
        }

        for(int i=0; i < elements; i++){
            myStack.push(arr[i]);
        }

    }

    private void copy() {
        int size = Integer.parseInt(myStack.pop());
        String[] arr = new String[size];
        int counter = 0;
        for(int i= size - 1; i >= 0; i--){
            arr[counter++] = myStack.peek(i);
        }
        for(int i=0; i < size; i++){
            myStack.push(arr[i]);
        }
    }

    private void index() {
        myStack.push(myStack.peek(Integer.parseInt(myStack.pop())));
    }

    private void dup() {
        myStack.push(myStack.peek(0));
    }


    private void exch() {
        String value1 = myStack.pop();
        String value2 = myStack.pop();
        myStack.push(value1);
        myStack.push(value2);
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter name of a file: ");
        String filename = reader.next();
        Core intpt = new Core (filename);
    }
}
