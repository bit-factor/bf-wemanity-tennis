package com.bitfactor;

import com.bitfactor.match.SetPlay;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class TennisMatchApplication
{
    public static void main( String[] args )
    {
        log.info("A tennis match is about to start between players `a` and `b`!");
        SetPlay setPlay = new SetPlay("a", "b");

        try (BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in))) {
            while (!setPlay.isCompleted()) {
                String player = buffer.readLine().trim();
                setPlay.point(player);
            }
        } catch (IOException iox) {
            log.error("A storm hit the game ... ", iox);
        }
    }
}
