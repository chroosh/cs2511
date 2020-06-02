package example;

import java.util.Scanner;

public class Splitter {

    public static void main(String[] args){
        System.out.println("Enter a sentence specified by spaces only: ");
				Scanner scan = new Scanner(System.in);
				String sentence = scan.nextLine();

				String[] sentence_array = sentence.split(" "); 
				for (String s : sentence_array) {
					System.out.println(s);
				}
				
        // Close your scanner
    }
}
