package com.bitfactor.match;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetPlayTest {

	@Test
	public void allZero() {
		SetPlay setPlay = new SetPlay("a", "b");
		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("1 - 0", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("2 - 0", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("3 - 0", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("Set won by a with 4 to 0", setPlay.buildSetScore());

		Assert.assertTrue(setPlay.isCompleted());
		Assert.assertEquals("a", setPlay.getWinner());
	}

	@Test
	public void moreThan2Won() {
		SetPlay setPlay = new SetPlay("a", "b");
		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("1 - 0", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("2 - 0", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("2 - 1", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("2 - 2", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("3 - 2", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("3 - 3", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("3 - 4", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("Set won by b with 5 to 3", setPlay.buildSetScore());

		Assert.assertTrue(setPlay.isCompleted());
		Assert.assertEquals("b", setPlay.getWinner());
	}

	@Test(expected = InvalidGamePointException.class)
	public void kickBallAfterFinish() {
		SetPlay setPlay = new SetPlay("a", "b");
		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("1 - 0", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("2 - 0", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("2 - 1", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("2 - 2", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "a");
		Assert.assertEquals("3 - 2", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("3 - 3", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("3 - 4", setPlay.buildSetScore());

		this.winAllBalls(setPlay, "b");
		Assert.assertEquals("Set won by b with 5 to 3", setPlay.buildSetScore());

		setPlay.point("b");
	}

	@Test(expected = InvalidGamePointException.class)
	public void unknownPlayer() {
		SetPlay setPlay = new SetPlay("a", "b");
		setPlay.point("c");
	}


	private void winAllBalls(SetPlay setPlay, String player) {
		for (int k = 0; k < 4; k++) {
			setPlay.point(player);
		}
	}
}