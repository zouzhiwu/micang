package com.pixel.test;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class PixelTest {
	public static void main(String[] args) {  
		InputStream in = PixelTest.class.getClassLoader().getResourceAsStream("template/map.bmp");
		BufferedImage img = null;
		try {
			img = ImageIO.read(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int w = img.getWidth();
		int h = img.getHeight();
		int startX = 0;
		int startY = 0;
		// rgb的数组
		int[] rgbArray = new int[w * h];
		img.getRGB(startX, startY, img.getWidth(), img.getHeight(), rgbArray, 0, w);
		for (int i = 0; i < rgbArray.length; i++) {
			int rgb = rgbArray[i];
			System.out.println(rgb);
		}
		
    }
}
