package examgenerator;

import java.util.Date;

/**
 *
 * @author Rian Fakhrusy
 */
public class Question {
    private int id;
    private String text;
    private int score; //a1
    private String type;
    private double difficulty; //a2
    private String knowledgePoint; //a4
    private int exposure;
    private double distinguishingDegree; //a5
    private Date date;
    private String author;
    private int solutionTime; //a7
    private int abilityLevel; //a3

    public Question(int id, int score, String type, double difficulty, String knowledgePoint, double distinguishingDegree, int solutionTIme) {
        this.id = id;
        this.score = score;
        this.type = type;
        this.difficulty = difficulty;
        this.knowledgePoint = knowledgePoint;
        this.distinguishingDegree = distinguishingDegree;
        this.solutionTime = solutionTIme;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the knowledgePoint
     */
    public String getKnowledgePoint() {
        return knowledgePoint;
    }

    /**
     * @param knowledgePoint the knowledgePoint to set
     */
    public void setKnowledgePoint(String knowledgePoint) {
        this.knowledgePoint = knowledgePoint;
    }

    /**
     * @return the difficulty
     */
    public Double getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the exposure
     */
    public int getExposure() {
        return exposure;
    }

    /**
     * @param exposure the exposure to set
     */
    public void setExposure(int exposure) {
        this.exposure = exposure;
    }

    /**
     * @return the distinguishingDegree
     */
    public Double getDistinguishingDegree() {
        return distinguishingDegree;
    }

    /**
     * @param distinguishingDegree the distinguishingDegree to set
     */
    public void setDistinguishingDegree(int distinguishingDegree) {
        this.distinguishingDegree = distinguishingDegree;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the solutionTIme
     */
    public int getSolutionTime() {
        return solutionTime;
    }

    /**
     * @param solutionTime the solutionTime to set
     */
    public void setSolutionTime(int solutionTime) {
        this.solutionTime = solutionTime;
    }

    /**
     * @return the abilityLevel
     */
    public int getAbilityLevel() {
        return abilityLevel;
    }

    /**
     * @param abilityLevel the abilityLevel to set
     */
    public void setAbilityLevel(int abilityLevel) {
        this.abilityLevel = abilityLevel;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", score=" + score + ", difficulty=" + difficulty + ", knowledgePoint=" + knowledgePoint + ", distinguishingDegree=" + distinguishingDegree + ", solutionTime=" + solutionTime + '}';
    }
    
}

