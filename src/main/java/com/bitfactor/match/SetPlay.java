package com.bitfactor.match;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SetPlay implements Score {
	private final String player1;
	private final String player2;
	private final Map<String, Integer> setScore = new HashMap<>();
	private GamePlay currentGamePlay;
	private String winner;

	public SetPlay(String p1, String p2) {
		this.player1 = p1;
		this.player2 = p2;
		this.setScore.put(this.player1, 0);
		this.setScore.put(this.player2, 0);
		log.info("SET: {} vs {}", this.player1, this.player2);

		this.currentGamePlay = new GamePlay(this.player1, this.player2);
	}

	@Override
	public void point(String winningPlayer) {
		this.currentGamePlay.point(winningPlayer);          // player is already verified during the game play

		if (this.winner != null) {
			throw new InvalidGamePointException("Set was already finished");
		}

		if (this.currentGamePlay.isCompleted()) {
			// set the score
			this.computeScore(winningPlayer);
			log.info(this.buildSetScore());

			if (this.winner == null) {
				this.currentGamePlay = new GamePlay(this.player1, this.player2);
			}
		}
	}

	private void computeScore(String winningPlayer) {
		this.setScore.put(winningPlayer, this.setScore.get(winningPlayer) + 1);

		// verify the set-score rules
		int scorePlayer1 = this.setScore.get(this.player1);
		int scorePlayer2 = this.setScore.get(this.player2);

		if (scorePlayer1 >= 4 && scorePlayer1 - scorePlayer2 >= 2) {
			this.winner = this.player1;
		} else if (scorePlayer2 >= 4 && scorePlayer2 - scorePlayer1 >= 2) {
			this.winner = this.player2;
		}
	}

	public boolean isCompleted() {
		return this.winner != null;
	}

	public String getWinner() {
		return winner;
	}

	public String buildSetScore() {
		int scorePlayer1 = this.setScore.get(this.player1);
		int scorePlayer2 = this.setScore.get(this.player2);

		if (this.winner != null) {
			String looser = this.winner.equals(this.player1) ? this.player2 : this.player1;
			return "Set won by " + this.winner + " with " +
					this.setScore.get(this.winner) + " to " +
					this.setScore.get(looser);
		}

		return scorePlayer1 + " - " + scorePlayer2;
	}
}
