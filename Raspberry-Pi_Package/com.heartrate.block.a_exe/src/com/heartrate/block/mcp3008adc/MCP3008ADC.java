package com.heartrate.block.mcp3008adc;

//HeartBeat Code

import no.ntnu.item.arctis.runtime.Block;

import java.util.Arrays;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class MCP3008ADC extends Block 
{
	public MCP3008ADC.Parameters p;
	private final static boolean DISPLAY_DIGIT = "true".equals(System.getProperty("display.digit", "false"));
	  // Note: "Mismatch" 23-24. The wiring says DOUT->#23, DIN->#24
	  // 23: DOUT on the ADC is IN on the GPIO. ADC:Slave, GPIO:Master
	  // 24: DIN on the ADC, OUT on the GPIO. Same reason as above.
	  // SPI: Serial Peripheral Interface
	  private static Pin spiClk  = RaspiPin.GPIO_14; // Pin #18, clock
	  private static Pin spiMiso = RaspiPin.GPIO_13; // Pin #23, data in.  MISO: Master In Slave Out
	  private static Pin spiMosi = RaspiPin.GPIO_12; // Pin #24, data out. MOSI: Master Out Slave In
	  private static Pin spiCs   = RaspiPin.GPIO_10; // Pin #25, Chip Select


	  public static enum MCP3008_input_channels
	  {
	    CH0(0),
	    CH1(1),
	    CH2(2),
	    CH3(3),
	    CH4(4),
	    CH5(5),
	    CH6(6),
	    CH7(7);
	    
	    private int ch;
	    
	    MCP3008_input_channels(int chNum)
	    {
	      this.ch = chNum;
	    }
	    
	    public int ch() 
	    { 
	    	return ch; 
	    }
	  }


	  
	  private static GpioController gpio;
	  
	  private static GpioPinDigitalInput  misoInput        = null;
	  private static GpioPinDigitalOutput mosiOutput       = null;
	  private static GpioPinDigitalOutput clockOutput      = null;
	  private static GpioPinDigitalOutput chipSelectOutput = null;


	public static class Parameters 
	{
		//public RaspberryPiCSPin chipSelectPin = RaspberryPiCSPin.CE0;
		//public MCP3008ADCChannel channel = MCP3008ADCChannel.CH0;
		public int samplingIntervalInMs = 1000;
		public int numOfSampleForAverage = 10;
	}

	public String init() 
	{
		try 
		{
			final GpioController Gpio = GpioFactory.getInstance();
			//gpio = GpioFactory.getInstance();
		    mosiOutput       = Gpio.provisionDigitalOutputPin(spiMosi, "MOSI", PinState.LOW);
		    clockOutput      = Gpio.provisionDigitalOutputPin(spiClk,  "CLK",  PinState.LOW);
		    chipSelectOutput = Gpio.provisionDigitalOutputPin(spiCs,   "CS",   PinState.LOW);
		     misoInput        = Gpio.provisionDigitalInputPin(spiMiso, "MISO"); 
			
		}
		catch (Throwable t) 
		{
			return "Error initializing SPI, are you on the right machine? " + t.toString();
		}
		//channel = (byte) p.channel.ch;
		logger.debug("MCDP3008ADC initialized, ch=" );
		return null;
	}


	public String read() 
	{
		//int sum = 0;
		int[] channel0 = new int[10]; //HeartRate
		int[] channel1 = new int[10]; //Temperature
		
		
		
		for (int i = 0; i < 10; i++) 
		{
			channel0 [i]= readOnce(0);
		}

		for (int i = 0; i < 10; i++) 
		{
			channel1 [i]= readOnce(1);
		}

		logger.info("Inside MCP3008 Block" );
		for (int i = 0; i < 10; i++) 
		{
			logger.info("channel0 value "+channel0 [i] );
		}
		for (int i = 0; i < 10; i++) 
		{
			logger.info("channel1 value "+channel1 [i] );
		}
		
		String chanel0 = Arrays.toString(channel0);
		logger.info("Channel0 string Value "+chanel0);
		String chanel1 = Arrays.toString(channel1);
		logger.info("Channel1 string Value "+chanel1);
		
		//String s = "Strings are immutable";
	    chanel0 = chanel0.concat(chanel1);
	    logger.info("Concatenated Value "+chanel0);
	    
		return chanel0;
	}
	
	
	 public static void shutdownMCP3008()
	  {
	    gpio.shutdown();
	  }
	 
	private int readOnce(int channel)
	  {
	    chipSelectOutput.high();
	    
	    clockOutput.low();
	    chipSelectOutput.low();
	  
	    int adccommand = channel;
	    if (DISPLAY_DIGIT)
	      System.out.println("1 -       ADCCOMMAND: 0x" + lpad(Integer.toString(adccommand, 16).toUpperCase(), "0",  4) + 
	                                       ", 0&" + lpad(Integer.toString(adccommand,  2).toUpperCase(), "0", 16));
	    adccommand |= 0x18; // 0x18: 00011000
	    if (DISPLAY_DIGIT)
	      System.out.println("2 -       ADCCOMMAND: 0x" + lpad(Integer.toString(adccommand, 16).toUpperCase(), "0",  4) + 
	                                       ", 0&" + lpad(Integer.toString(adccommand,  2).toUpperCase(), "0", 16));
	    adccommand <<= 3;
	    if (DISPLAY_DIGIT)
	      System.out.println("3 -       ADCCOMMAND: 0x" + lpad(Integer.toString(adccommand, 16).toUpperCase(), "0",  4) + 
	                                       ", 0&" + lpad(Integer.toString(adccommand,  2).toUpperCase(), "0", 16));
	    // Send 5 bits: 8 - 3. 8 input channels on the MCP3008.
	    for (int i=0; i<5; i++) //
	    {
	      if (DISPLAY_DIGIT)
	        System.out.println("4 - (i=" + i + ") ADCCOMMAND: 0x" + lpad(Integer.toString(adccommand, 16).toUpperCase(), "0",  4) + 
	                                                       ", 0&" + lpad(Integer.toString(adccommand,  2).toUpperCase(), "0", 16));
	      if ((adccommand & 0x80) != 0x0) // 0x80 = 0&10000000
	        mosiOutput.high();
	      else
	        mosiOutput.low();
	      adccommand <<= 1;      
	      // Clock high and low
	      tickOnPin(clockOutput);      
	    }

	    int adcOut = 0;
	    for (int i=0; i<12; i++) // Read in one empty bit, one null bit and 10 ADC bits
	    {
	      tickOnPin(clockOutput);      
	      adcOut <<= 1;

	      if (misoInput.isHigh())
	      {
//	      System.out.println("    " + misoInput.getName() + " is high (i:" + i + ")");
	        // Shift one bit on the adcOut
	        adcOut |= 0x1;
	      }
	      if (DISPLAY_DIGIT)
	        System.out.println("ADCOUT: 0x" + lpad(Integer.toString(adcOut, 16).toUpperCase(), "0",  4) + 
	                                 ", 0&" + lpad(Integer.toString(adcOut,  2).toUpperCase(), "0", 16));
	    }
	    chipSelectOutput.high();

	    adcOut >>= 1; // Drop first bit
	    return adcOut;
	  }
	  


	  private static void tickOnPin(GpioPinDigitalOutput pin)
	  {
	    pin.high();
	    pin.low();
	  }
	  
	  private static String lpad(String str, String with, int len)
	  {
	    String s = str;
	    while (s.length() < len)
	      s = with + s;
	    return s;
	  }
}
