package ar.com.bamba.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class TwittearTest {

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(locations = { "classpath:mvc-servlet.xml" })
	public class TwitearTest {
		
		/*
	   @Autowired
	   private TwitterService twitterService;
	   
	   @Autowired
	   private TwitterTemplateCreator twitterCreator;
	 */
	   
		/*
		@Test
	   public void whenTweeting_thenNoExceptions() {
	      Twitter twitterTemplate = twitterCreator.getTwitterTemplate("bambaSpring");
	      twitterService.tweet(twitterTemplate, "Twitteando por la vida");
	   }
	   */
	}
	
	
}
