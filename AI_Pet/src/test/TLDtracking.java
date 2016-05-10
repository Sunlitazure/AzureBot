package test;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import boofcv.abst.tracker.TrackerObjectQuad;
import boofcv.factory.tracker.FactoryTrackerObjectQuad;
import boofcv.gui.image.ImagePanel;
import boofcv.gui.image.ShowImages;
import boofcv.gui.tracker.TrackerObjectQuadPanel;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.webcamcapture.UtilWebcamCapture;
import boofcv.misc.BoofMiscOps;
import boofcv.struct.image.ImageBase;
import boofcv.struct.image.ImageUInt8;
import georegression.struct.shapes.Quadrilateral_F64;

//import javax.swing.JFrame;
//
//import com.github.sarxos.webcam.Webcam;
//import com.github.sarxos.webcam.WebcamPanel;
//import boofcv.io.webcamcapture.*;
//
//public class TLDtracking {
//
//public static void main(String[]args){
//	Webcam webcam = UtilWebcamCapture.openDefault(640, 480);
////	Webcam webcam = Webcam.getDefault();
////	webcam.setViewSize(WebcamResolution.VGA.getSize());
//	WebcamPanel panel = new WebcamPanel(webcam);
//	
//	JFrame window = new JFrame("TLD tracking");
//	window.add(panel);
//	window.setResizable(true);
//	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	window.pack();
//	window.setVisible(true);
//}
//
//}


public class TLDtracking {
	 
	public static void main(String[] args) {

 
		// Create the tracker.  Comment/Uncomment to change the tracker.
		TrackerObjectQuad tracker =
//				FactoryTrackerObjectQuad.circulant(null, ImageUInt8.class);
//				FactoryTrackerObjectQuad.sparseFlow(null,ImageUInt8.class,null);
				FactoryTrackerObjectQuad.tld(null,ImageUInt8.class);
//				FactoryTrackerObjectQuad.meanShiftComaniciu2003(new ConfigComaniciu2003(), ImageType.ms(3, ImageUInt8.class));
//				FactoryTrackerObjectQuad.meanShiftComaniciu2003(new ConfigComaniciu2003(true),ImageType.ms(3,ImageUInt8.class));
 
				// Mean-shift likelihood will fail in this video, but is excellent at tracking objects with
				// a single unique color.  See ExampleTrackerMeanShiftLikelihood
//				FactoryTrackerObjectQuad.meanShiftLikelihood(30,5,255, MeanShiftLikelihoodType.HISTOGRAM,ImageType.ms(3,ImageUInt8.class));
		
		// Open a webcam at a resolution close to 640x480
		com.github.sarxos.webcam.Webcam webcam = UtilWebcamCapture.openDefault(640,480);
		
		
		BufferedImage image = webcam.getImage();
		ImageBase frame = ConvertBufferedImage.convertFrom(image,(ImageUInt8)null);
 
		// specify the target's initial location and initialize with the first frame
		Quadrilateral_F64 location = new Quadrilateral_F64(211.0,162.0,326.0,153.0,335.0,258.0,215.0,249.0);
		
		tracker.initialize(frame,location);
 
		// For displaying the results
		TrackerObjectQuadPanel gui = new TrackerObjectQuadPanel(null);
		gui.setPreferredSize(webcam.getViewSize());
		gui.setBackGround((BufferedImage)webcam.getImage());
		gui.setTarget(location,true);
		ShowImages.showWindow(gui,"Tracking Results", true);
 
		// Track the object across each video frame and display the results
		long previous = 0;
		while( true ) {
			image = webcam.getImage();
			frame = ConvertBufferedImage.convertFrom(image,(ImageUInt8)null);
 
			boolean visible = tracker.process(frame,location);
			
			gui.setBackGround((BufferedImage) webcam.getImage());
			gui.setTarget(location, visible);
			gui.repaint();
 
			// shoot for a specific frame rate
			long time = System.currentTimeMillis();
			BoofMiscOps.pause(Math.max(0,80-(time-previous)));
			previous = time;
		}
	}
}