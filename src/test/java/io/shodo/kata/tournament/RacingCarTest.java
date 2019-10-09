package io.shodo.kata.tournament;

import org.junit.Test;

public class RacingCarTest {

    /**
        the candidate can create other tests with baby steps before providing a full implementation

        track(200) means there is a track with 200km in distance
        withAcceleration(100).in(5) means the car can reach 100km/h in 5 seconds
        andMaxSpeed(320) means the car can't go beyond 320km/h in speed
     */

    @Test
    public void shouldWinTheRaceWhenHavingTheBiggestMaxSpeed() {

        RaceTournament tournament = new RaceTournament(track(200))
            .competing(car("Bugatti Chiron")
                .withAcceleration(100).in(5)
                .andMaxSpeed(320))
            .competing(car("Renault Clio")
                .withAcceleration(100).in(25)
                .andMaxSpeed(180));

        tournament.race();

        assertThat(tournament.winner()).isEqualTo("Bugatti Chiron");
    }

    @Test
    public void shouldWinTheRaceWhenHavingTheBiggestAcceleration() {

        RaceTournament tournament = new RaceTournament(track(200))
            .competing(car("Bugatti Chiron")
                .withAcceleration(100).in(5)
                .andMaxSpeed(320))
            .competing(car("Bugatti Veyron")
                .withAcceleration(100).in(3)
                .andMaxSpeed(320));

        tournament.race();

        assertThat(tournament.winner()).isEqualTo("Bugatti Veyron");
    }

    @Test
    public void shouldWinTheRaceWithMagnificentAccelerationOnSmallTrack() {

        RaceTournament tournament = new RaceTournament(track(3))
            .competing(car("Accelerator Beast")
                .withAcceleration(100).in(14)
                .andMaxSpeed(220))
            .competing(car("Speed Madness")
                .withAcceleration(100).in(20)
                .andMaxSpeed(320));

        tournament.race();

        assertThat(tournament.winner()).isEqualTo("Accelerator Beast");
    }

    @Test
    public void shouldWinTheRaceWithMagnificentMaxSpeedOnNormalTrack() {

        RaceTournament tournament = new RaceTournament(track(4))
            .competing(car("Accelerator Beast")
                .withAcceleration(100).in(14)
                .andMaxSpeed(220))
            .competing(car("Speed Madness")
                .withAcceleration(100).in(20)
                .andMaxSpeed(320));

        tournament.race();

        assertThat(tournament.winner()).isEqualTo("Speed Madness");
    }

    /**
     * a track with a turn of :
     * - 90 degrees over 0.4km forces the racers to decelerate their speed by 40%
     * track(5).havingTurn(90).at(3).over(0.4) means there is a turn of 90 degrees at the 3rd kilometer spanning over 0.4 kms
     */

    @Test
    public void earlyTurnMakesTheAcceleratorWinTheRace() {

        RaceTournament tournament = new RaceTournament(
            track(5)
                .havingTurn(90)
                .at(3)
                .over(0.4))
            .competing(car("Accelerator Beast")
                .withAcceleration(100).in(14)
                .andMaxSpeed(220))
            .competing(car("Speed Madness")
                .withAcceleration(100).in(20)
                .andMaxSpeed(250));

        tournament.race();

        assertThat(tournament.winner()).isEqualTo("Accelerator Beast");
    }

    @Test
    public void lateTurnMakesTheAcceleratorWinTheRace() {

        RaceTournament tournament = new RaceTournament(
            track(5)
                .havingTurn(90)
                .at(1.8)
                .over(0.4))
            .competing(car("Accelerator Beast")
                .withAcceleration(100).in(14)
                .andMaxSpeed(220))
            .competing(car("Speed Madness")
                .withAcceleration(100).in(20)
                .andMaxSpeed(260));

        tournament.race();

        assertThat(tournament.winner()).isEqualTo("Speed Madness");
    }
}
