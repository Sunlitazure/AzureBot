package test;

import java.awt.image.BufferedImage;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.VideoCapture;

public class FaceRecognition {

	private static Webcam webcam;
	private VideoCapture cam;
	private Mat vid;
	private BufferedImage bufIm = null;
	
	public void init(){
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME);
		cam = new VideoCapture(0);
		vid = new Mat();
	}
	
}
