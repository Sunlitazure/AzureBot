package test;

import java.awt.EventQueue;

import javax.swing.JFrame;

//import processing.core.PApplet;
import java.awt.Toolkit;

public class ProcessingIntegrationTest{

//	private JFrame frame;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ProcessingIntegrationTest window = new ProcessingIntegrationTest();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public ProcessingIntegrationTest() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(ProcessingIntegrationTest.class.getResource("/Resources/logo.png")));
//		frame.setBounds(100, 100, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		stt sttFrame = new stt();
//		sttFrame.init();
//		frame.getContentPane().add(sttFrame);
//	}
//	
//	
//	
//	private class stt extends PApplet{
//		/**
//		 * 
//		 */
//		private static final long serialVersionUID = 3354343475853398333L;
//		String result;
//		int test;
//		int textLength;
//
//		float spin;
//
//
//
//
//
//
//		public void setup ()
//		{
//		      //size(600, 200, P2D);
//
//		      // Some text to display the result
//		      textFont(createFont("Arial", 24));
//		      result = "Yes?";
//		      
//		      String word = "moo";
//		      //link("http://www.google.com/#q=" + word);
//		      
//		      spin=0;
//		}
//
//
//
//
//
//
//		public void draw ()
//		{
//		      background(39,25,48);
//		      //text(result, mouseX, mouseY);
//		      
//		      
//		      
//		      textLength = result.length();
//		      fill(255,0,0,150);
//		      text(result, (width/2)-(textLength*10/2), height/2+1);
//		      fill(0,255,0,150);
//		      text(result, (width/2)-(textLength*10/2)+1, height/2-1);
//		      fill(0,0,255,150);
//		      text(result, (width/2)-(textLength*10/2)-1, height/2-1);
//		      fill(255);
//		      text(result, (width/2)-(textLength*10/2), height/2);
//		      
//		  
//		      pushMatrix();
//		      fill(55,185,250,90);
//		      noStroke();
//		      for(int j=10; j<=height; j= j+25){
//		        for(int i=0; i<=width; i= i+25){
//		          ellipse(i,j,2,2);
//		        }
//		      }
//		      popMatrix();
//		  
//		      pushMatrix();
//		      smooth();
//		      fill(255,50);
//		      noStroke();
//		      ellipse(width/2,0, (float) (width*1.5), height/2 - 5);
//		      popMatrix();
//		}
//
//
//
//
//
//
//		// Method is called if transcription was successful 
//		void transcribe (String utterance, float confidence) 
//		{   println(utterance + "---");
//		    
//		    if(utterance != ""){
//		         println("-" + utterance + "- " + confidence);
//		         result = utterance;
//		    }
//		    else{
//		      int rando = (int)(random(5));
//		      switch(rando) {
//		        case 0: result = "What'd you say?";
//		                break;
//		        case 1: result = "Sorry, didn't catch that.";
//		                break;
//		        case 2: result = "Come again?";
//		                break;
//		        case 3: result = "Huh?";
//		                break;
//		        default: result = "Didn't quite understand that.";
//		                break;
//		      }
//		    }
//		}
//	}

}
