package examgenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ExamGenerator {
    static String outname = "input.txt"; 
    static int t = 600;
    static int questionMaxScore = 30;
    
    public static void generateQuestions(){
        Random r = new Random();
        try{
            //Scanner in = new Scanner(System.in);
            BufferedWriter out = new BufferedWriter(new FileWriter(outname));
            out.write(t + "\n");
            for (int cas = 1; cas <= t; cas++){
                out.write( cas + " "); //id
                out.write( getLinnearRandomNumber(questionMaxScore) + " "); //score
                out.write( r.nextInt(100000)/100000f + " "); //difficulty
                out.write( r.nextInt(10)+1 + " "); //knowledge
                out.write( r.nextInt(100000)/100000f + " "); //distinguishing degree
                out.write( r.nextInt(20)+1 + "\n"); //time
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int getLinnearRandomNumber(int maxSize){
        //Get a linearly multiplied random number
        int randomMultiplier = maxSize * (maxSize + 1) / 2;
        Random r=new Random();
        int randomInt = r.nextInt(randomMultiplier);

        //Linearly iterate through the possible values to find the correct one
        int linearRandomNumber = 0;
        for(int i=maxSize; randomInt >= 0; i--){
            randomInt -= i;
            linearRandomNumber++;
        }

        return linearRandomNumber;
    }
    
    public static void main(String[] args) {
        //generateQuestions();
        
    }
}
