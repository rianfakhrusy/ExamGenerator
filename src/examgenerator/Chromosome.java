package examgenerator;

/*
* The MIT License
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is used to define a chromosome for the gentic algorithm 
 * simulation.  
 * 
 * This class is essentially nothing more than a container for the details 
 * of the chromosome, namely the gene (the string that represents our target 
 * string) and the fitness (how close the gene is to the target string).
 *
 * Note that this class is immutable.  Calling <code>mate(Chromsome)</code>
 * or <code>mutate</code> will result in a new <code>Chromosome</code>
 * instance being created.
 * 
 * @author Rian Fakhrusy
 */
public class Chromosome implements Comparable<Chromosome> {
    private final int[] gene;
    private final double fitness;
    //private int id;

    /** Convenience randomizer. */
    private static final Random rand = new Random(System.currentTimeMillis());

    /**
     * Default constructor.
     *
     * @param gene The gene representing this <code>Chromosome</code>.
     * @param num The id for the gene in population.
     */
    public Chromosome(int[] gene){//, int num) {
        this.gene    = gene;
        this.fitness = calculateFitness(gene);
        //this.id = num; 
    }

    /**
     * Default constructor that generates random gene for this
     * <code>Chromosome</code>.
     */

    /**
     * Method to retrieve the gene for this <code>Chromosome</code>.
     *
     * @return The gene for this <code>Chromosome</code>.
     */
    public String getGene() {
        List<Integer> intList = new ArrayList<Integer>();
        for (int index = 0; index < gene.length; index++)
        {
            intList.add(gene[index]);
        }
        return intList.toString();
    }

    /**
     * Method to retrieve the id for this <code>Chromosome</code>.
     *
     * @return The id for this <code>Chromosome</code>.
     */
    /*
    public int getId(){
       return id;
    }*/

    /**
     * Method to retrieve the fitness of this <code>Chromosome</code>.  Note
     * that a lower fitness indicates a better <code>Chromosome</code> for the
     * solution.
     *
     * @return The fitness of this <code>Chromosome</code>.
     */
    public double getFitness() {
        return fitness;
    }

    /**
     * Helper method used to calculate the fitness for a given gene.
     * 
     * @param gene The gene to calculate the fitness for.
     * 
     * @return The calculated fitness of the given gene.
     */
    private static double calculateFitness(int[] gene) {
        
        // Constraint
        int sumScore = Exam.sumScore; int iScore = 0;
        double avgDiff = Exam.avgDiff; double iDiff = 0f;
        int[] chapter = Exam.chapter;
        double avgDist = Exam.avgDist; double iDist = 0f;
        int sumTime = Exam.sumTime; int iTime = 0;
        
        for (int i=0;i<gene.length;i++){
            iScore += Exam.quest[gene[i]-1].getScore();
            iDiff += Exam.quest[gene[i]-1].getDifficulty();
            iDist += Exam.quest[gene[i]-1].getDistinguishingDegree();
            iTime += Exam.quest[gene[i]-1].getSolutionTime();
        }
        iDiff /= gene.length;
        iDist /= gene.length;
        
        double NRE = 0f;
        NRE += Math.abs((double)sumScore - (double)iScore)/(double)sumScore;
        //System.out.println(NRE);
        NRE += Math.abs((double)avgDiff - (double)iDiff)/(double)avgDiff;
        //System.out.println(NRE);
        NRE += Math.abs((double)avgDist - (double)iDist)/(double)avgDist;
        //System.out.println(NRE);
        NRE += Math.abs((double)sumTime - (double)iTime)/(double)sumTime;
        //System.out.println(NRE);
        
        double fitness = 1f/(1f+NRE);
        
        
        
        /*

        HashMap<Character, Integer> stock = new HashMap<> ();
        HashMap<Character, Integer> inventory = new HashMap<> ();
        HashMap<Character, Integer> maxVisit = new HashMap<> ();
        boolean[] metAtLeastOnce = new boolean[Nanto.nCandidate];
        char[] geneArr = gene.toCharArray();
        char[] itemCode = Nanto.itemCodes.toCharArray();
        char[] candidCode = Nanto.candidCodes.toCharArray();

        // Set "met at least once" table
        for(int i = 0; i < Nanto.nCandidate; i++) {
            metAtLeastOnce[i] = false;
        }

        // Set inventory
        for(int i = 0; i < Nanto.nItem; i++) {
            inventory.put(itemCode[i], 0);
        }

        for(int i = 0; i < 7 * Nanto.time; i++) {
            // Restock items for sale
            for(int x = 0; x < Nanto.nItem; x++) {
                Item temp = Nanto.items.get(itemCode[x]);
                stock.put(temp.getCode(), temp.getRestock());
            }

            // Restore max visit per candidate
            for(int x = 0; x < Nanto.nCandidate; x++) {
                Candidate temp = Nanto.candid.get(candidCode[x]);
                maxVisit.put(candidCode[x], temp.getMax());
            }

            // Recharge energy
            curEnergy = energyPerDay;

            for(int j = 0; j < 12; j++) {
                int idx = j + i * 12;
                if(geneArr[idx] == '0') {
                    // Do nothing
                } else if(Character.isUpperCase(geneArr[idx])) {
                    // Check if it's item code
                    for(int x = 0; x < Nanto.nItem; x++) {
                        if(geneArr[idx] == itemCode[x]) {
                            // Check if money is enough and there is stock
                            Item toBuy = Nanto.items.get(itemCode[x]);
                            int price = toBuy.getPrice();
                            int curStock = stock.get(itemCode[x]);
                            if(money >= price && stock.get(itemCode[x]) != 0) {
                                money -= price;
                                stock.put(itemCode[x], curStock - 1);
                                int curInventory = inventory.get(itemCode[x]);
                                inventory.put(itemCode[x], curInventory + 1);
                            } else return -1;
                            break;
                        }
                    }
                } else if (Character.isDigit(geneArr[idx])) {
                    // Check if it's candidate code
                    for(int x = 0; x < Nanto.nCandidate; x++) {
                        if(geneArr[idx] == candidCode[x]) {
                            // Check if all prerequisite is enough
                            Candidate toVisit = Nanto.candid.get(candidCode[x]);
                            char[] prereq = toVisit.getPrerequisite();
                            boolean haveItems = true;
                            if(prereq[0] != '-') {
                                for(int k = 0; k < prereq.length; k++) {
                                    if(inventory.get(prereq[k]) == 0) {
                                        haveItems = false;
                                        break;
                                    }
                                }
                            }
                            int visitRemaining = maxVisit.get(candidCode[x]);
                            if(haveItems &&
                                    Nanto.jCandid[x][idx % 84] == 1 &&
                                    visitRemaining != 0 &&
                                    brain >= toVisit.getBrain() &&
                                    charm >= toVisit.getCharm() &&
                                    strength >= toVisit.getStrength() &&
                                    curEnergy >= toVisit.getEnergy()) {
                                fitness += toVisit.getEnlightenment();
                                curEnergy -= toVisit.getEnergy();
                                visitRemaining--;
                                maxVisit.put(candidCode[x], visitRemaining);
                                if(visitRemaining == 0 || idx == Nanto.time * 84 - 1 || 
                                        geneArr[idx] != geneArr[idx+1]) {
                                    for(int k = 0; k < prereq.length; k++) {
                                        if(prereq[k] != '-') {											
                                            int amount = inventory.get(prereq[k]);
                                            inventory.put(prereq[k], amount - 1);
                                        }
                                    }
                                }
                                metAtLeastOnce[x] = true;
                            } else return -1;
                            break;
                        }
                    }
                } else if(geneArr[idx] == 'm') {
                    if(Nanto.jPlace[0][idx % 84] ==	 1 && curEnergy >= 8) {
                        money += 10000;
                        curEnergy -= 8;
                    } else return -1;
                } else if(geneArr[idx] == 'g') {
                    if(Nanto.jPlace[1][idx % 84] == 1 && curEnergy >= 12) {
                        strength += 2;
                        curEnergy -= 12;
                    } else return -1;
                } else if(geneArr[idx] == 'c') {
                    if(Nanto.jPlace[2][idx % 84] == 1 &&	curEnergy >= 6) {
                        charm += 2;
                        curEnergy -= 6;
                    } else return -1;
                } else if(geneArr[idx] == 'u') {
                    if(Nanto.jPlace[3][idx % 84] == 1 &&	curEnergy >= 15) {
                        brain += 3;
                        curEnergy -= 15;
                    } else return -1;
                } else {
                    return -1;
                }
            }
        }

        for(int i = 0; i < Nanto.nCandidate; i++) {
            if(metAtLeastOnce[i] == false) return -1;
        }*/

        return fitness;
    }
    
    /**
     * Helper method used to optimize a given gene.
     * 
     * @param gene The gene to be optimized.
     * 
     * @return The optimized gene.
     */
    /*
    private static Chromosome optimize(String gene, int id) {
        // Game states
        int energyPerDay = Nanto.energy;
        int money = Nanto.money;
        int strength = Nanto.strength;
        int charm = Nanto.charm;
        int brain = Nanto.brain;
        int curEnergy;

        HashMap<Character, Integer> stock = new HashMap<> ();
        HashMap<Character, Integer> inventory = new HashMap<> ();
        HashMap<Character, Integer> maxVisit = new HashMap<> ();
        boolean[] metAtLeastOnce = new boolean[Nanto.nCandidate];
        char[] geneArr = gene.toCharArray();
        char[] itemCode = Nanto.itemCodes.toCharArray();
        char[] candidCode = Nanto.candidCodes.toCharArray();

        // Set "met at least once" table
        for(int i = 0; i < Nanto.nCandidate; i++) {
            metAtLeastOnce[i] = false;
        }

        // Set inventory
        for(int i = 0; i < Nanto.nItem; i++) {
            inventory.put(itemCode[i], 0);
        }

        char[] possibleChars = ("0mgcu" + Nanto.itemCodes + Nanto.candidCodes)
                .toCharArray();
        int length = possibleChars.length;

        int day = 0;
        int hour = 0;
        boolean validAction = true;

        while(day < 7 * Nanto.time) {
            hour = 0;
            // Restock items for sale
            for(int x = 0; x < Nanto.nItem; x++) {
                Item temp = Nanto.items.get(itemCode[x]);
                stock.put(temp.getCode(), temp.getRestock());
            }

            // Restore max visit per candidate
            for(int x = 0; x < Nanto.nCandidate; x++) {
                Candidate temp = Nanto.candid.get(candidCode[x]);
                maxVisit.put(candidCode[x], temp.getMax());
            }

            // Recharge energy
            curEnergy = energyPerDay;

            while(hour < 12) {
                int idx = hour + day * 12;
                if(geneArr[idx] == '0') {
                    validAction = true;
                } else if(Character.isUpperCase(geneArr[idx])) {
                    // Check if it's item code
                    for(int x = 0; x < Nanto.nItem; x++) {
                        if(geneArr[idx] == itemCode[x]) {
                            // Check if money is enough and there is stock
                            Item toBuy = Nanto.items.get(itemCode[x]);
                            int price = toBuy.getPrice();
                            int curStock = stock.get(itemCode[x]);
                            if(money >= price && stock.get(itemCode[x]) != 0) {
                                money -= price;
                                stock.put(itemCode[x], curStock - 1);
                                int curInventory = inventory.get(itemCode[x]);
                                inventory.put(itemCode[x], curInventory + 1);
                                validAction = true;
                            } else {
                                validAction = false;
                            }
                            break;
                        }
                    }
                } else if (Character.isDigit(geneArr[idx])) {
                    // Check if it's candidate code
                    for(int x = 0; x < Nanto.nCandidate; x++) {
                        if(geneArr[idx] == candidCode[x]) {
                            // Check if all prerequisite is enough
                            Candidate toVisit = Nanto.candid.get(candidCode[x]);
                            char[] prereq = toVisit.getPrerequisite();
                            boolean haveItems = true;
                            if(prereq[0] != '-') {
                                for(int k = 0; k < prereq.length; k++) {
                                    if(inventory.get(prereq[k]) == 0) {
                                        haveItems = false;
                                        break;
                                    }
                                }
                            }
                            int visitRemaining = maxVisit.get(candidCode[x]);
                            if(haveItems &&
                                    Nanto.jCandid[x][idx % 84] == 1 &&
                                    visitRemaining != 0 &&
                                    brain >= toVisit.getBrain() &&
                                    charm >= toVisit.getCharm() &&
                                    strength >= toVisit.getStrength() &&
                                    curEnergy >= toVisit.getEnergy()) {
                                curEnergy -= toVisit.getEnergy();
                                visitRemaining--;
                                maxVisit.put(candidCode[x], visitRemaining);
                                if(visitRemaining == 0 || idx == Nanto.time * 84 - 1 || 
                                        geneArr[idx] != geneArr[idx+1]) {
                                    for(int k = 0; k < prereq.length; k++) {
                                        if(prereq[k] != '-') {											
                                            int amount = inventory.get(prereq[k]);
                                            inventory.put(prereq[k], amount - 1);
                                        }
                                    }
                                }
                                metAtLeastOnce[x] = true;
                                validAction = true;
                            } else {
                                validAction = false;
                            }
                            break;
                        }
                    }
                } else if(geneArr[idx] == 'm') {
                    if(Nanto.jPlace[0][idx % 84] ==	 1 && curEnergy >= 8) {
                        money += 10000;
                        curEnergy -= 8;
                        validAction = true;
                    } else {
                        validAction = false;
                    }
                } else if(geneArr[idx] == 'g') {
                    if(Nanto.jPlace[1][idx % 84] == 1 && curEnergy >= 12) {
                        strength += 2;
                        curEnergy -= 12;
                        validAction = true;
                    } else {
                        validAction = false;
                    }
                } else if(geneArr[idx] == 'c') {
                    if(Nanto.jPlace[2][idx % 84] == 1 && curEnergy >= 6) {
                        charm += 2;
                        curEnergy -= 6;
                        validAction = true;
                    } else {
                        validAction = false;
                    }
                } else if(geneArr[idx] == 'u') {
                    if(Nanto.jPlace[3][idx % 84] == 1 &&	curEnergy >= 15) {
                        brain += 3;
                        curEnergy -= 15;
                        validAction = true;
                    } else {
                        validAction = false;
                    }
                }
                if(validAction == true) hour++;
                else {
                    // Optimize by visiting all candidates first
                    boolean madeAction = false;
                    boolean metAllCandids = true;
                    for(int i = 0; i < Nanto.nCandidate; i++) {
                        if(metAtLeastOnce[i] == false) {
                            metAllCandids = false;
                            Candidate toVisit = Nanto.candid.get(candidCode[i]);

                            // Is candidate available?
                            if(Nanto.jCandid[i][idx % 84] == 0) continue;
                            if(maxVisit.get(candidCode[i]) == 0) continue;

                            // Check if all prereq parameters are enough
                            if(charm < toVisit.getCharm()) {
                                if(curEnergy >= 6 && Nanto.jPlace[1][idx % 84] == 1){
                                    geneArr[idx] = 'c';	
                                    madeAction = true;
                                    break;
                                } else continue;
                            }
                            if(strength < toVisit.getStrength()) {
                                if(curEnergy >= 12 && Nanto.jPlace[0][idx % 84] == 1) {
                                    geneArr[idx] = 'g';
                                    madeAction = true;
                                    break;
                                } else continue;
                            }
                            if(brain < toVisit.getBrain()) {
                                if(curEnergy >= 15 && Nanto.jPlace[2][idx % 84] == 1) {
                                    geneArr[idx] = 'u';
                                    madeAction = true;
                                    break;
                                } else continue;
                            }

                                // Check inventory
                            char[] prereq = toVisit.getPrerequisite();
                            boolean haveItems = true;
                            if(prereq[0] != '-') {
                                for(int j = 0; j < prereq.length; j++) {
                                    if(inventory.get(prereq[j]) == 0) {
                                        haveItems = false;
                                        // Check if money is enough to buy item
                                        Item toBuy = Nanto.items.get(prereq[j]);
                                        int price = toBuy.getPrice();
                                        int curStock = stock.get(prereq[j]);
                                        if(money >= price && curStock != 0) {
                                            // Buy item on next action
                                            geneArr[idx] = prereq[j];
                                            madeAction = true;
                                            break;
                                        } else {
                                            // Try going to mall on next action
                                            if(curEnergy >= 8) {
                                                geneArr[idx] = 'm';
                                                madeAction = true;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                            }

                            if(haveItems == true && curEnergy >= toVisit.getEnergy()) {
                                geneArr[idx] = candidCode[i];
                            } else geneArr[idx] = '0';
                            break;
                        }
                    }
                    if(metAllCandids == true) {
                        geneArr[idx] = possibleChars[rand.nextInt(length)];
                    } else if(madeAction == false) {
                        geneArr[idx] = '0';
                    }
                //System.out.println(String.valueOf(geneArr));
                }
            }
            day++;
        }
        return new Chromosome(String.valueOf(geneArr), id);
    }*/

    /**
     * Method to generate a new <code>Chromosome</code> that is a random
     * mutation of this <code>Chromosome</code>.  This method randomly
     * selects one character in the <code>Chromosome</code>s gene, then
     * replaces it with another random (but valid) character.  Note that
     * this method returns a new <code>Chromosome</code>, it does not
     * modify the existing <code>Chromosome</code>.
     * 
     * @return A mutated version of this <code>Chromosome</code>.
     */
    public Chromosome mutate() { 
        int[] arr  = gene;
        int idx     = rand.nextInt(arr.length);
        int newQuestion = rand.nextInt(Exam.nQuestion)+1;
        
        arr[idx] = newQuestion;       
        

        return new Chromosome(arr);
    }

    /**
     * Method used to mate this <code>Chromosome</code> with another.  The
     * resulting child <code>Chromosome</code>s are returned.
     * 
     * @param mate The <code>Chromosome</code> to mate with.
     * 
     * @return The resulting <code>Chromosome</code> children.
     */
    public Chromosome[] mate(Chromosome mate) {
        // Convert the genes to arrays to make thing easier.
        int[] arr1  = gene;
        int[] arr2  = mate.gene;

        // Select a random pivot point for the mating
        int pivot    = rand.nextInt(arr1.length);

        // Provide a container for the child gene data
        int[] child1 = new int[gene.length];
        int[] child2 = new int[gene.length];

        // Copy the data from each gene to the first child.
        System.arraycopy(arr1, 0, child1, 0, pivot);
        System.arraycopy(arr2, pivot, child1, pivot, (child1.length - pivot));

        // Repeat for the second child, but in reverse order.
        System.arraycopy(arr2, 0, child2, 0, pivot);
        System.arraycopy(arr1, pivot, child2, pivot, (child2.length - pivot));

        return new Chromosome[] { new Chromosome(child1),
                new Chromosome(child2)};
    }

    /**
     * A convenience method to generate a random <code>Chromosome</code>.
     * 
     * @return A randomly generated <code>Chromosome</code>.
     */
    static Chromosome generateRandom() {
        //char[] possibleChars = ("0mgcu" + Nanto.itemCodes + Nanto.candidCodes)
        //        .toCharArray();
        //int length = 84 * Nanto.time;

        int[] newGene=new int[Exam.nQuestion];

        for (int i=0; i<Exam.nQuestion;i++){
            newGene[i]=rand.nextInt(Exam.quest.length)+1;
            //System.out.println(newGene[i]);
        }

//		System.out.println(String.valueOf(newGene));
        return new Chromosome(newGene);
    }

    /**
     * Method to allow for comparing <code>Chromosome</code> objects with
     * one another based on fitness.  <code>Chromosome</code> ordering is 
     * based on the natural ordering of the fitnesses of the
     * <code>Chromosome</code>s.  
     * @param c Chromosome to be compared
     * @return -1 if this chromosome's fitness is less than c's fitness, 1 if
     * bigger, 0 otherwise
     */
    @Override
    public int compareTo(Chromosome c) {
        if (fitness < c.fitness) {
            return 1;
        } else if (fitness > c.fitness) {
            return -1;
        }

        return 0;
    }

    /**
     * @param o Object to be compared
     * @return true when o is exactly the same as this object.
     * @see Object#equals(Object)
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Chromosome)) {
            return false;
        }

        Chromosome c = (Chromosome) o;
        return (gene.equals(c.gene) && fitness == c.fitness);
    }

    /**
     * @return Hash code of the gene and fitness.
     * @see Object#hashCode()
     */
    @Override
    public int hashCode() {		
        return new StringBuilder().append(gene).append(fitness)
                .toString().hashCode();
    }

    @Override
    public String toString() {
        return gene + " " + fitness + "\n";
    }
    /*
    public static void main(String[] args) {
        File f1 = new File("info.txt");
        File f2 = new File("kandidat.txt");
        File f3 = new File("tempat.txt");
        Nanto n = new Nanto();
        n.load(f1);
        Genetic.load(f2,f3);
        Chromosome c1 = new Chromosome("30mDA0CguC0AuA3034AgAcm03m3cDgCCgCgADu444mCAg03330CguAg0u42cA2gDDB1mg3B4u3mcgmD304g01c0cA0DgucAB1C0D0Dumm3DAgB34c2mu1D3AmD1cDcB2AcAm10mg3mc131Ccg4gCu3ADD1m4ggD2m0020CA3", 1);
        System.out.println(c1);
        Chromosome c2 = optimize(c1.getGene(), 2);
        System.out.println(c2);
    }*/
}