package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;

public class Main
{	
	static final String aliceFilePath = "D:\\Pictures\\Python\\alice.bmp"; 
	static BufferedImage aliceImage;
	
	public static void main(String[] args) throws IOException
	{
		aliceImage = ImageIO.read(new File(aliceFilePath)); 
		
		int maxPixels = aliceImage.getWidth()*aliceImage.getHeight();
		
		int[][] pixels = new int[maxPixels][3];
		int[] argb;
				
		int pixelCounter = 0;
		char[] lsbChars = new char[maxPixels];
		String binString = "";
		
		for (int y=0; y<aliceImage.getHeight(); y++)
		{
			for (int x=0; x<aliceImage.getWidth(); x++)
			{
				argb = getPixelArray(x, y);
				for (int i=0; i<argb.length; i++)
				{
					String bin = Integer.toBinaryString(argb[i]); 
					char[] chars = bin.toCharArray();
					char c = chars[chars.length-1];
					lsbChars[pixelCounter] = c;
					
					binString += c;				
					pixelCounter++;
					
					StringTokenizer str = new StringTokenizer(binString);
					
					if ((pixelCounter) % 8 == 0)
					{
						String textStr = "";
						while (str.hasMoreTokens())
						{
							int binVal = Integer.parseInt(str.nextToken(), 2);
							char textChar = (char) binVal;
							textStr += textChar;
						}
						System.out.println(textStr);
						binString = "";
					}
				}
			}
		}
	}
	
	private static int[] getPixelArray(int x, int y)
	{
		int rgb = aliceImage.getRGB(x, y);
		
		int[] argb = new int[]{
			(rgb >> 16) & 0xff, //red
			(rgb >> 8) & 0xff,  //green
			rgb & 0xff			//blue
		};
		
		return argb;
	}
}
