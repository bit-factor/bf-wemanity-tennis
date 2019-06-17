package com.bitfactor.match;

import org.junit.Assert;
import org.junit.Test;


public class GamePlayTest {                 // NOSONAR - there are a lot of generated strings in an expected format

	@Test
	public void winToZeroTest() {
		GamePlay gamePlay = new GamePlay("a", "b");
		Assert.assertEquals("Love ALL", gamePlay.buildGameScore());

		gamePlay.point("a");        // 15-0
		Assert.assertEquals("Fifteen - Love", gamePlay.buildGameScore());

		gamePlay.point("a");        // 30-0
		Assert.assertEquals("Thirty - Love", gamePlay.buildGameScore());

		gamePlay.point("a");        // 40-0
		Assert.assertEquals("Forty - Love", gamePlay.buildGameScore());

		gamePlay.point("a");        // Win
		Assert.assertEquals("Game won by a", gamePlay.buildGameScore());

		Assert.assertTrue(gamePlay.isCompleted());
		Assert.assertEquals("a", gamePlay.getWinner());
	}

	@Test(expected = InvalidGamePointException.class)
	public void forceOneBallOverEndOfGame() {
		GamePlay gamePlay = new GamePlay("a", "b");
		Assert.assertEquals("Love ALL", gamePlay.buildGameScore());

		gamePlay.point("a");        // 15-0
		Assert.assertEquals("Fifteen - Love", gamePlay.buildGameScore());

		gamePlay.point("a");        // 30-0
		Assert.assertEquals("Thirty - Love", gamePlay.buildGameScore());

		gamePlay.point("a");        // 40-0
		Assert.assertEquals("Forty - Love", gamePlay.buildGameScore());

		gamePlay.point("a");        // Win
		Assert.assertEquals("Game won by a", gamePlay.buildGameScore());

		Assert.assertTrue(gamePlay.isCompleted());
		Assert.assertEquals("a", gamePlay.getWinner());

		gamePlay.point("a");        // Win
	}

	@Test(expected = InvalidGamePointException.class)
	public void testInvalidPlayer() {
		GamePlay gamePlay = new GamePlay("a", "b");
		Assert.assertEquals("Love ALL", gamePlay.buildGameScore());

		gamePlay.point("a");        // 15-0
		Assert.assertEquals("Fifteen - Love", gamePlay.buildGameScore());

		gamePlay.point("c");        // someone from the court hit the ball
	}



	@Test
	public void deuceAbrupt() {
		GamePlay gamePlay = new GamePlay("a", "b");
		Assert.assertEquals("Love ALL", gamePlay.buildGameScore());

		gamePlay.point("a");        // 15-0
		Assert.assertEquals("Fifteen - Love", gamePlay.buildGameScore());
		gamePlay.point("a");        // 30-0
		Assert.assertEquals("Thirty - Love", gamePlay.buildGameScore());
		gamePlay.point("a");        // 40-0
		Assert.assertEquals("Forty - Love", gamePlay.buildGameScore());

		gamePlay.point("b");        // 40-15
		Assert.assertEquals("Forty - Fifteen", gamePlay.buildGameScore());
		gamePlay.point("b");        // 40-30
		Assert.assertEquals("Forty - Thirty", gamePlay.buildGameScore());
		gamePlay.point("b");        // 40-40 -- deuce
		Assert.assertEquals("Deuce", gamePlay.buildGameScore());

		// start with advantage
		gamePlay.point("a");
		Assert.assertEquals("Advantage player a", gamePlay.buildGameScore());

		gamePlay.point("a");
		Assert.assertEquals("Game won by a", gamePlay.buildGameScore());

		Assert.assertTrue(gamePlay.isCompleted());
		Assert.assertEquals("a", gamePlay.getWinner());
	}

	@Test
	public void deuceTestProgressive() {
		GamePlay gamePlay = new GamePlay("a", "b");
		Assert.assertEquals("Love ALL", gamePlay.buildGameScore());

		gamePlay.point("a");        // 15-0
		Assert.assertEquals("Fifteen - Love", gamePlay.buildGameScore());

		gamePlay.point("b");        // 15-15
		Assert.assertEquals("Fifteen ALL", gamePlay.buildGameScore());

		gamePlay.point("a");        // 30-15
		Assert.assertEquals("Thirty - Fifteen", gamePlay.buildGameScore());

		gamePlay.point("b");        // 40-30
		Assert.assertEquals("Thirty ALL", gamePlay.buildGameScore());

		gamePlay.point("a");        // 40-30
		Assert.assertEquals("Forty - Thirty", gamePlay.buildGameScore());

		gamePlay.point("b");        // 40-40 -- deuce
		Assert.assertEquals("Deuce", gamePlay.buildGameScore());

		gamePlay.point("a");        // advantage a
		Assert.assertEquals("Advantage player a", gamePlay.buildGameScore());

		gamePlay.point("b");        // deuce
		Assert.assertEquals("Deuce", gamePlay.buildGameScore());

		gamePlay.point("b");        // advantage b
		Assert.assertEquals("Advantage player b", gamePlay.buildGameScore());
		gamePlay.point("a");        // deuce
		Assert.assertEquals("Deuce", gamePlay.buildGameScore());

		gamePlay.point("b");        // advantage b
		Assert.assertEquals("Advantage player b", gamePlay.buildGameScore());

		gamePlay.point("b");        // win b
		Assert.assertEquals("Game won by b", gamePlay.buildGameScore());

		Assert.assertTrue(gamePlay.isCompleted());
		Assert.assertEquals("b", gamePlay.getWinner());
	}


}