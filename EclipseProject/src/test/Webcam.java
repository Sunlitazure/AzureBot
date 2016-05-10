package test;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import javax.swing.JPanel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.objdetect.CascadeClassifier;


public class Webcam {
	private static Webcam webcam;
	private BufferedImage bufIm = null;
	private VideoCapture cam;
	private Mat vid;
	
	private CascadeClassifier faceDetector, eyeDetector, sideDetector;
	private MatOfRect faceDetection;
	
	feed feed;
	
	public Webcam(){
	}
	
	public static void main(String[] args){
		//Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1);
		//System.out.println( "mat = " + mat.dump());
		
		//final Mat image = Highgui.imread(filename)
		
		webcam = new Webcam();
		webcam.init();
		
		for(;;){
			try{
				webcam.grabFrame();
			}catch(Exception e){
				System.out.println("Program probably closed.");
				System.exit(1);
			}
			webcam.feed.repaint();
		}
		
		
		
	}
	
	private void init(){
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		cam = new VideoCapture(0);
		vid = new Mat();
		
		faceDetector = new CascadeClassifier("data/cascades/lbpcascade_frontalface.xml");
		eyeDetector = new CascadeClassifier("data/cascades/haarcascade_eye.xml");
		sideDetector = new CascadeClassifier("data/cascades/lbpcascade_profileface.xml");
		faceDetection = new MatOfRect();
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, (int)cam.get(Highgui.CV_CAP_PROP_FRAME_WIDTH), (int)cam.get(Highgui.CV_CAP_PROP_FRAME_HEIGHT));
		frame.setVisible(true);
		
		frame.add(feed = new feed());
		
		System.out.println( cam.isOpened());
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				cam.release();
			}
		});
	}
	
	public void grabFrame(){
		cam.retrieve(vid);
		//System.out.println(vid.dump());
		
		//face detection
		faceDetector.detectMultiScale(vid, faceDetection);
		for(Rect rect : faceDetection.toArray()){
			//Core.rectangle(vid, new Point(rect.x, rect.y), new Point(rect.x+rect.width, rect.y+rect.height), new Scalar(232,140,28));
			//Core.putText(vid, "Y: " + rect.height, new Point(rect.x, rect.y - 12), 1, 1, new Scalar(255,255,255));
			//Core.putText(vid,"X: " + rect.width, new Point(rect.x, rect.y), 1, 1, new Scalar(255,255,255));
		}
		
		eyeDetector.detectMultiScale(vid, faceDetection);
		for(Rect rect : faceDetection.toArray()){
			//Core.rectangle(vid, new Point(rect.x, rect.y), new Point(rect.x+rect.width, rect.y+rect.height), new Scalar(232,140,28));
		}
		
		sideDetector.detectMultiScale(vid, faceDetection);
		for(Rect rect : faceDetection.toArray()){
			//Core.rectangle(vid, new Point(rect.x, rect.y), new Point(rect.x+rect.width, rect.y+rect.height), new Scalar(232,140,28));
		}
		//end
		
		
		MatOfByte matOfByte = new MatOfByte();
		Highgui.imencode(".jpg", vid, matOfByte);
		
		byte[] byteArray = matOfByte.toArray();
		
		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			bufIm = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Webcam getWebcam(){
		return webcam;
	}
	
	public BufferedImage getBufIm(){
		return bufIm;
	}
}

@SuppressWarnings("serial")
class feed extends JPanel{ // implements Runnable{
	
//	Thread camFrames = new Thread(this);
//	
//	public void start(){
//		//if( camFrames != null)
//			camFrames.start();
//	}
//	
//	public void run(){
//		for(;;){
//			Webcam.getWebcam().grabFrame();
//			repaint();
//			System.out.println("hi");
//		}
//	}
	
	private void doDrawing(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		
		g2d.drawImage(Webcam.getWebcam().getBufIm(), 0, 0, null);
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		doDrawing(g);
	}
	
}
