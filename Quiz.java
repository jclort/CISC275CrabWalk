import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.Serializable;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;

public class Quiz implements Serializable{


	//private BufferedImage q1 = createImage("images/quiz1.png");
	//private int q1Answer;
	private BufferedImage q1 = createImage("images/quiz2.png");
	private int q1Answer;
	private BufferedImage q2 = createImage("images/quiz3.png");
	private int q2Answer;
	private BufferedImage q3 = createImage("images/quiz4.png");
	private int q3Answer;
	private BufferedImage q4 = createImage("images/quiz5.png");
	private int q4Answer;
	
	private int quiz = 1;
	//private BufferedImage check = createImage();
	
	//public BufferedImage getCheck() {
	//	return check;
	//}

	public BufferedImage getPic(int num) {
	       	switch(num) {
			case 1: return q1;
		        case 2: return q2;
		        case 3: return q3;
		        case 4: return q4;
		default: return q1;
	       }
	}

	public int getAnswer(int num) {
		switch(num) {
			case 1: return q1Answer;
			case 2: return q2Answer;
			case 3: return q3Answer;
			case 4: return q4Answer;
		default: return q1Answer;
		}
	}		

	public int getQuiz() {
		if ((quiz+1) % 4 == 0) {
			quiz = 1;
		}
		return (quiz++%4);
	}

	private BufferedImage createImage(String fname) {
		BufferedImage bufferedImage;

		try {
			bufferedImage = ImageIO.read(new File(fname));
			return bufferedImage;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}	


