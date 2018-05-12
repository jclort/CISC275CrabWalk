import java.util.Random;
import java.swing.*;
import java.util.ArrayList;

Class Quiz{
    private int question;
    private ArrayList<ImageIcon> answers;
    private static ImageIcon Trash1 = new ImageIcon("images\trash1.png");
    private static ImageIcon Trash2 = new ImageIcon("images\trash2.png");
    private static ImageIcon Trash3 = new ImageIcon("images\trash3.png");
    private static ImageIcon Trash4 = new ImageIcon("images\trash4.png");
    private static ImageIcon Trash5 = new ImageIcon("images\trash5.png");
    private static ImageIcon Crab = new ImageIcon("images\crab.png");
    private static ImageIcon Creature1 = new ImageIcon("images\creature1");
    private static ImageIcon Creature2 = new ImageIcon("images\creature2");
    private static String[] questions = {"Which of these belongs in an estuary"};
    privat static Random rand = new Random();
    

    public Quiz(){
        question = rand.nextInt()%questions.length;
        answers = new ArrayList<ImageIcon>();
        switch(question){
            default: answers.add(Crab);
        }

    }

    public boolean correct(ImageIcon response){
        return answers.contains(response);
    }

}

    