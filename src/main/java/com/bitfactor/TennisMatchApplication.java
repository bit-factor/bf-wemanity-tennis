package com.bitfactor;

import com.bitfactor.match.SetPlay;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TennisMatchApplication
{
    public static void main( String[] args )
    {
        if (args.length == 0) {
            log.warn("Nothing to play!");
            return;
        }

        log.info("A tennis match is about to start between players `a` and `b`!");
        char[] pointsSequence = args[0].toCharArray();

        SetPlay setPlay = new SetPlay("a", "b");
        for (int k = 0; k < pointsSequence.length; k++) {
            setPlay.point(pointsSequence[k] + "");
        }

        if (!setPlay.isCompleted()) {
            log.warn("The set is not yet won! Keep working!");
        }
    }
}
