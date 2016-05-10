package test;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

/**
 * https://github.com/bytedeco/javacv look in java files as documentation
 * 
 * 
 * reference: http://docs.opencv.org/2.4/modules/contrib/doc/facerec/facerec_tutorial.html
 * 
 * 
 * I couldn't find any tutorial on how to perform face recognition using OpenCV and Java,
 * so I decided to share a viable solution here. The solution is very inefficient in its
 * current form as the training model is built at each run, however it shows what's needed
 * to make it work.
 *
 * The class below takes two arguments: The path to the directory containing the training
 * faces and the path to the image you want to classify. Note that all images has to be of
 * the same size and that the faces already has to be cropped out of their original images
 * (Take a look here http://fivedots.coe.psu.ac.th/~ad/jg/nui07/index.html if you haven't
 * done the face detection yet).
 *
 * For the simplicity of this post, the class also requires that the training images have
 * filename format: <label>-rest_of_filename.png. For example:
 *
 * 1-jon_doe_1.png
 * 1-jon_doe_2.png
 * 2-jane_doe_1.png
 * 2-jane_doe_2.png
 * ...and so on.
 *
 * Source: http://pcbje.com/2012/12/doing-face-recognition-with-javacv/
 *
 * @author Petter Christian Bjelland
 */
public class FaceRecognizerFromImage {
    public static void main(String[] args) {
        String trainingDir = "data/face_recog/face_database"; //args[0];
        Mat testImage = imread("data/face_recog/tests/test12.jpg", CV_LOAD_IMAGE_GRAYSCALE); //imread(args[1], CV_LOAD_IMAGE_GRAYSCALE);
//New folder/6-s3 (1).jpg
//trevor_hatch_source/test3.jpg
        File root = new File(trainingDir);

        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
            }
        };

        File[] imageFiles = root.listFiles(imgFilter);

        MatVector images = new MatVector(imageFiles.length);

        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.getIntBuffer();

        int counter = 0;

        for (File image : imageFiles) {
            Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);

            int label = Integer.parseInt(image.getName().split("\\-")[0]);

            images.put(counter, img);

            labelsBuf.put(counter, label);

            counter++;
        }

         //FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
         //FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
         //FaceRecognizer faceRecognizer = createLBPHFaceRecognizer(1, 8, 8, 8, 80);
         FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();

        faceRecognizer.train(images, labels);

        int[] labeld = new int[1];
        double[] confidence = new double[1];
        //int predictedLabel = faceRecognizer.predict(testImage);
        faceRecognizer.predict(testImage, labeld, confidence);
        System.out.println("label: " + labeld[0] + " conf: " + confidence[0]);
        //System.out.println("Predicted label: " + predictedLabel);
    }
}