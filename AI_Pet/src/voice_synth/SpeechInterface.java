package voice_synth;

/// A set of static methods that give users access to the main speech 
/// interaction components.  These methods are the only ones exposed to 
/// other programming languages through the Java Native Interface.
public class SpeechInterface
{
	private static SpeechSynthesizer mSynthesizer = null;

	/// Initializes Voce.  The 'vocePath' String specifies the path where 
	/// Voce classes and config file can be found.  'initSynthesis' 
	/// and 'initRecognition' enable these capabilities; if you don't 
	/// need one or the other, not initializing it will save load time 
	/// and memory (though the feature will be disabled, of course).  
	/// 'grammarPath' is a relative or absolute path to one or more 
	/// grammar files (all .gram files in 'grammarPath' will automatically 
	/// be searched).  'grammarName' is the name of a specific grammar 
	/// within a .gram file in the 'grammarPath'.  If the 'grammarName' 
	/// is empty, a simple default grammar will be used.
	public static void init( String acoustPath, String dictPath, String grammarPath, String grammarName)
	{
		Utils.setPrintDebug(false);
		Utils.log("debug", "Beginning initialization");


			// Create a speech synthesizer and give it a name.
		Utils.log("", "Initializing synthesizer");
		mSynthesizer = new SpeechSynthesizer("kevin16");


		Utils.log("", "Initialization complete");
	}

	/// Destroys Voce.	
	public static void destroy()
	{
		Utils.log("debug", "Shutting down...");
		
		if (null != mSynthesizer)
		{
			mSynthesizer.destroy();
		}


		Utils.log("", "Shutdown complete");
	}

	/// Requests that the given string be synthesized as soon as possible.
	public static void synthesize(String message)
	{
		if (null == mSynthesizer)
		{
			Utils.log("warning", "synthesize called before " 
				+ "synthesizer was initialized.  Request will be ignored.");
			return;
		}

		//Utils.log("debug", "SpeechInterface.speak: Adding message to speech queue: " + message);
		
		mSynthesizer.synthesize(message);
	}

	/// Tells the speech synthesizer to stop synthesizing.  This cancels all 
	/// pending messages.
	public static void stopSynthesizing()
	{
		if (null == mSynthesizer)
		{
			Utils.log("warning", "stopSynthesizing called before " 
				+ "synthesizer was initialized.  Request will be ignored.");
			return;
		}

		mSynthesizer.stopSynthesizing();
	}

}
