package ai_app;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RobotApp {

	BlockingQueue<byte[]> queueout;
	BlockingQueue<String> queuein;
	BlockingQueue<String> voicequeue;
	TCPthread tcp;
	SysAudioThread audio;
	VoiceSynth voice;
	Scanner scan;
	String input;
	
	Long answer = null;
	
	public static void main(String[] args) {

		
		RobotApp app = new RobotApp();
		app.init();
		try {
			app.loop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void init(){

		
		queueout = new LinkedBlockingQueue<byte[]>(); //audio out
		queuein = new LinkedBlockingQueue<String>(); //sensor data in
		voicequeue = new LinkedBlockingQueue<String>();//voice text
		
		tcp = new TCPthread(10, queueout, queuein);
		tcp.start();
		while(!tcp.isConnected()){  //stalls program until client connected
			System.out.print("");
		}
		audio = new SysAudioThread(queueout);
		audio.start();
		
		voice = new VoiceSynth(voicequeue);
		voice.start();
		
		// TODO find better solution
		try {
			Thread.sleep(1000); //stalls program so audio can build up samples, necessary.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		tcp.setStart(); // start pulling audio off the queue

		
		scan = new Scanner(System.in); //sets up input from the console
		
	}
	
	private void loop() throws InterruptedException{  //everything that needs to keep looping after init()
		while(true){
			System.out.print(": ");
			input = scan.nextLine();
			//System.out.println(input);
			if(input.equals("exit")){
				System.exit(0);
			}
			else{
				voicequeue.put(input);
			}
		}
	}

}

// ./client 192.168.1.115 |aplay -r44100 -f S16_BE
