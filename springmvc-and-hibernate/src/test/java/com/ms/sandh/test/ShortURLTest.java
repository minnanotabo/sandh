package com.ms.sandh.test;

import java.util.List;

import com.ms.h2hibernate.ShortURL;
import com.ms.h2hibernate.service.ShortURLService;

import junit.framework.TestCase;

public class ShortURLTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
		public static void main(String[] args) {
			System.out.println("Maven + Hibernate + Oracle");

			ShortURLService service = new ShortURLService();
			String shortURL = null;
			
			String[] ourls = { "long1", "long2", "long3", "long2", "long2"};
			for (String ourl : ourls) {
				shortURL = service.addShortURL(ourl, ourl + "abc@gmail.com");
				System.out.println("shortURL for " + ourl + " is: " + shortURL);
			}

			String myShort = service.getOriginalURL(shortURL);
			System.out.println("originalURL: " + myShort);
			
			ShortURL sURL = service.getShortURLObj(shortURL);
			System.out.println("shortURLObj: " + sURL);
			
			service.listShortURL();
			
			List<String> long2 = service.getShortURLByOriginalURL("long2");
			for (String s : long2) {
				System.out.println("shortURL for long2: " + s);
			}
			

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testOne() {
        System.out.println("test #1");
        assertEquals(0, 0);
    }
}
