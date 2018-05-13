import java.util.Random;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.*;

class Quiz extends JFrame{
    private int question;
    private ArrayList<ImageIcon> answers;
    private static ImageIcon Trash1 = new ImageIcon(View.trashPic);
    private static ImageIcon Trash2 = new ImageIcon(View.trashPic1);
    private static ImageIcon Trash3 = new ImageIcon(View.trashPic2);
    private static ImageIcon Trash4 = new ImageIcon(View.trashPic3);
    private static ImageIcon Trash5 = new ImageIcon(View.trashPic4);
    private static ImageIcon Crab = new ImageIcon(View.pics[0][0]);
    private static ImageIcon Creature1 = new ImageIcon(View.invaPic1[0]);
    private static ImageIcon Creature2 = new ImageIcon(View.invaPic2[0]);
    private static String[] questions = {"Which of these belongs in an estuary"};
    private static Random rand = new Random();
    

    public Quiz(){
        question = rand.nextInt()%questions.length;
        answers = new ArrayList<ImageIcon>();
        switch(question){
            default: answers.add(Crab);
        }
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 4, 5, 5));
        add(new JButton(answers.get(0)));
        add(new JButton(Trash1));
        setVisible(true);
        requestFocusInWindow();
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }

    public boolean correct(ImageIcon response){
        return answers.contains(response);
    }


}

    