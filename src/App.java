import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String input1 = scanner.nextLine();
        File file = new File(input1);
        if(file.exists()){
            System.out.println("File found.");
        } else {
            System.out.println("File not found.");
        }
        scanner.close();

        Scanner input = new Scanner(file); //Scanner is used to read the file

        ArrayList<String> wordsArray = new ArrayList<String>(); //Keeps track of different words from file
        ArrayList<Integer> wordsCountArray = new ArrayList<Integer>(); //Keeps track of how many times each word has appeared
        while(input.hasNext()){            
            boolean containsWord = false;
            int countIndex = 0; //Keeps track of index when looping through the arrays so the counters match with each word
            String word = input.next(); //Uses scanner to get the first word from the file
            String inputWord = word.toLowerCase(); //Changes word to lowercase
            
            for(int i=0; i<wordsArray.size(); i++){//Loops through the Array containing the words                
                if(wordsArray.get(i).equals(inputWord)){//Checks if the current word obtained from the file is equal to any of the words already in the list
                    containsWord = true;
                    countIndex = i;
                    break;
                }
            }
            
            if(containsWord == false){//If the word is not in the list, it is added to the end of the list
                wordsArray.add(inputWord);
                wordsCountArray.add(1);            
            } else { //If the word is already in the list, the counter increases by one
                int currentCount = wordsCountArray.get(countIndex);
                ++currentCount;
                wordsCountArray.set(countIndex, currentCount);
            }
        }

        FileWriter writer = new FileWriter("Words_Count.txt");
        BufferedWriter buffer = new BufferedWriter(writer);

        for(int i=0; i<wordsArray.size(); i++){ //Prints output to console and file
            System.out.print(wordsArray.get(i)+" ");
            System.out.print(wordsCountArray.get(i)+"\n");
            buffer.write("%s %d\n".formatted(wordsArray.get(i), wordsCountArray.get(i)));            
        } 

        input.close();
        buffer.close();
    }
}
