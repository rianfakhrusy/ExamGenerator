package examgenerator;

import java.io.IOException;

public class Test {
    
    public static void main(String[] args) throws IOException {
        String filename = "input.txt";
        Exam e = new Exam();
        e.load(filename);
        
        int iScore = 0;
        double iDiff = 0f;
        double iDist = 0f;
        int iTime = 0;
        
        int[] all = new int[]{164, 66, 456, 449, 386, 522, 494, 203, 103, 545};
        
        for (int i=0;i<all.length;i++){
            System.out.println(Exam.quest[all[i]-1].toString());
            iScore += Exam.quest[all[i]-1].getScore();
            iDiff += Exam.quest[all[i]-1].getDifficulty();
            iDist += Exam.quest[all[i]-1].getDistinguishingDegree();
            iTime += Exam.quest[all[i]-1].getSolutionTime();
        }
        iDiff /= all.length;
        iDist /= all.length;
        
        System.out.println(iScore);
        System.out.println(iDiff);
        System.out.println(iDist);
        System.out.println(iTime);
        
    }
}
