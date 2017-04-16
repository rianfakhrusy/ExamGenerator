package examgenerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Inject {
    static final String TF = "truefalsegnrquiz";
    static final String SHORT = "shortgnrquiz";
    static final String MULTI = "choicegnrquiz";
    static final String MATCH = "matchgnrquiz";
    static final String ESSAY = "essaygnrquiz";
    
    static String[] types = {TF,SHORT,MULTI,MATCH,ESSAY};
    
    static String filename = "mathpuzzle";
    //static String filename = "Alarge";
    //static String filename = "tc";
    static String inname = filename + ".xml";    
    static String outname = filename + "-out.xml"; 
    public static void main(String[] args){
        Random r = new Random();
        try{
            Scanner in = new Scanner(new BufferedReader(new FileReader(inname)));
            //Scanner in = new Scanner(System.in);
            BufferedWriter out = new BufferedWriter(new FileWriter(outname));
            String typenow = "";
            while (in.hasNextLine()) {
                String temp = in.nextLine();
                if (temp.contains("matching"))
                    temp = temp.replace("matching", MATCH);
                else if (temp.contains("multichoice"))
                    temp = temp.replace("multichoice", MULTI);
                else if (temp.contains("shortanswer"))
                    temp = temp.replace("shortanswer", SHORT);
                else if (temp.contains("truefalse"))
                    temp = temp.replace("truefalse", TF);
                else if (temp.contains("truefalse"))
                    temp = temp.replace("truefalse", TF);
                
                for (int i=0;i<types.length;i++){
                    if (temp.contains(types[i])){
                        typenow = types[i];
                    }
                }
                
                if ((typenow.equals(ESSAY))&&(temp.contains("</responsetemplate>"))){
                    out.write("<time>");
                    out.write((r.nextInt(15)+6)*60+""); //time
                    out.write("</time>\n");
                    out.write("<difficulty>");
                    out.write(r.nextInt(100)/100f + ""); //difficulty
                    out.write("</difficulty>\n");
                    out.write("<distinguishingdegree>");
                    out.write(r.nextInt(100)/100f + ""); //distinguishing degree
                    out.write("</distinguishingdegree>\n");
                } else if ((typenow.equals(MATCH))&&(temp.contains("<hidden>"))){
                    out.write("<time>");
                    out.write((r.nextInt(20)+11)*60+""); //time
                    out.write("</time>\n");
                    out.write("<difficulty>");
                    out.write(r.nextInt(100)/100f + ""); //difficulty
                    out.write("</difficulty>\n");
                    out.write("<distinguishingdegree>");
                    out.write(r.nextInt(100)/100f + ""); //distinguishing degree
                    out.write("</distinguishingdegree>\n");
                } else if ((typenow.equals(MULTI))&&(temp.contains("<answernumbering>"))){
                    out.write("<time>");
                    out.write((r.nextInt(10)+1)*60+""); //time
                    out.write("</time>\n");
                    out.write("<difficulty>");
                    out.write(r.nextInt(100)/100f + ""); //difficulty
                    out.write("</difficulty>\n");
                    out.write("<distinguishingdegree>");
                    out.write(r.nextInt(100)/100f + ""); //distinguishing degree
                    out.write("</distinguishingdegree>\n");
                } else if ((typenow.equals(SHORT))&&(temp.contains("<usecase>"))){
                    out.write("<time>");
                    out.write((r.nextInt(10)+1)*60+""); //time
                    out.write("</time>\n");
                    out.write("<difficulty>");
                    out.write(r.nextInt(100)/100f + ""); //difficulty
                    out.write("</difficulty>\n");
                    out.write("<distinguishingdegree>");
                    out.write(r.nextInt(100)/100f + ""); //distinguishing degree
                    out.write("</distinguishingdegree>\n");
                } else if ((typenow.equals(TF))&&(temp.contains("<hidden>"))){
                    out.write("<time>");
                    out.write((r.nextInt(5)+1)*60+""); //time
                    out.write("</time>\n");
                    out.write("<difficulty>");
                    out.write(r.nextInt(100)/100f + ""); //difficulty
                    out.write("</difficulty>\n");
                    out.write("<distinguishingdegree>");
                    out.write(r.nextInt(100)/100f + ""); //distinguishing degree
                    out.write("</distinguishingdegree>\n");
                }
                out.write(temp+"\n");
            }

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
