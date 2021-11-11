package util;

import java.util.Scanner;

public class ConsoleInputReader {
    private Scanner scanner = new Scanner(System.in);
    
    public String next(String inputName) {
        printInputName(inputName);
        return scanner.next();
    }

    public String nextLine(String inputName) {
        printInputName(inputName);
        return scanner.nextLine();
    }

    public boolean hasNextLine(String inputName) {
        printInputName(inputName);
        return scanner.hasNextLine();
    }

    public boolean hasNext(String inputName) {
        printInputName(inputName);
        return scanner.hasNext();
    }
    
    private void printInputName(String inputName) {
        if(inputName != null && !inputName.isEmpty())
            System.out.print(String.format("%s: ", inputName));
    }

    public void close() {
        scanner.close();
    }
}
