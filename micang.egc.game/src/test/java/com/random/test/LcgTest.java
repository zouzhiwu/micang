package com.random.test;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LcgTest {
	private static final Logger logger = LoggerFactory.getLogger(LcgTest.class);
	
	public static void main(String[] args) {
		LcgTest lcgTest = new LcgTest();
		lcgTest.test();
	}
	
	public int sed;
	public int count = 0;
	
	public void test() {
		Random random = new Random();
		sed = random.nextInt();
		logger.info("begin");
		for (int i = 0; i < 1000000; i++) {
			sed = lcg(sed);
		}
		logger.info("end");
	}
	
	private int lcg(int seed) {
		sed = lcgRandom(seed);
		count++;
		return sed;
	}
	
	private int lcgRandom(int seed) {
		int a = 2083146228;
		int c = 828425687;
		int modulus = 7379091;
		int random = (a * seed + c) % modulus;
		return random;
	}
	
	public long setAttribute(long value) {
		sed = lcg(sed);
		return value;
	}
	
	public int setAttribute(int value) {
		sed = lcg(sed);
		return value;
	}
	
	public short setAttribute(short value) {
		sed = lcg(sed);
		return value;
	}
	
	public byte setAttribute(byte value) {
		sed = lcg(sed);
		return value;
	}
	
	public float setAttribute(float value) {
		sed = lcg(sed);
		return value;
	}
	
	public String setAttribute(String value) {
		sed = lcg(sed);
		return value;
	}
}
