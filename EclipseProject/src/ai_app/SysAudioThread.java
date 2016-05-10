package ai_app;

import java.util.concurrent.BlockingQueue;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

public class SysAudioThread extends Thread {
	protected BlockingQueue<byte[]> queue;
	
	private Mixer.Info[] mxInfo = null;
	private int mixIndex = 0;
	private Mixer mixer = null;
	private Line.Info[] info = null;
	private TargetDataLine line = null;
	private byte[] data = null;
	
	private boolean end = false;
	
	SysAudioThread(BlockingQueue<byte[]> queue){
		this.queue = queue;
	}
	
	public void run(){
		mxInfo = AudioSystem.getMixerInfo();
		mixIndex = 6; //5 for input, 6 for mixer
		mixer = AudioSystem.getMixer(mxInfo[mixIndex]);
		System.out.println(mxInfo[mixIndex]);
		
		info = mixer.getTargetLineInfo();
		System.out.println(info[0]);
		
		try {
			line = (TargetDataLine) mixer.getLine(info[0]);
			line.open(line.getFormat());
			System.out.println(line.getFormat().toString() + ", " + line.getBufferSize());
		} catch (LineUnavailableException e){
			e.printStackTrace();
		}
		
		data = new byte[line.getBufferSize() / 1]; //5
		
		line.start();
		
		while(!end){
			line.read(data, 0, data.length); 
			try {
				queue.put(data);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Audio thread closed");
		this.interrupt();
	}
	
	public void setEnd(){
		end = true;
	}
	
}
