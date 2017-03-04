package examgenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Exam {
    public static int nQuestion;
    public static int sumScore;
    public static HashMap<String, Integer> types;
    public static double avgDiff;
    //public static String[] chapter;
    public static HashMap<String, Integer> chapter;
    public static double avgDist;
    public static int sumTime;
    //public static int[] idQuestions;
    public static Question[] quest;
    
    public void load(String inname){
        quest = null;
        try{
            Scanner in = new Scanner(new BufferedReader(new FileReader(inname)));
            in.useLocale(Locale.US);
            //Scanner in = new Scanner(System.in);
            //BufferedWriter out = new BufferedWriter(new FileWriter(outname));
            int t = in.nextInt();
            in.nextLine();
            quest = new Question[t];
            for (int cas = 0; cas < t; cas++){
                quest[cas] = new Question(
                        in.nextInt(), //id
                        in.nextInt(), //score
                        in.next(), //type
                        in.nextDouble(), //difficulty
                        in.next(), //knowledge
                        in.nextDouble(), //distinguishing degree
                        in.nextInt() //time
                );
                //System.out.println(quest[cas].toString());
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
