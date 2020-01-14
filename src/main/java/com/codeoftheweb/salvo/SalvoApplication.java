package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class,args);
	}
	@Autowired
	PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository,GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository, SalvoReposiory salvoReposiory, ScoreRepository scoreRepository) {
		return (args) -> {
			Player player0= new Player("GOtalora", "Griselda",  "griis.otalor@gmail.com", passwordEncoder.encode("1995"), true);
			Player player1= new Player("Jack95","Jack", "j.bauer@ctu.gov", passwordEncoder.encode("24"));
			Player player2= new Player("Chloe94","Chloe",  "c.obrian@ctu.gov", passwordEncoder.encode("42"));
			Player player3= new Player("Kim01","Kim",  "kim_bauer@gmail.com", passwordEncoder.encode("kb"));
			Player player4= new Player("Tony99", "Tony",  "t.almeida@ctu.gov", passwordEncoder.encode("mole"));


			Game game1= new Game();
			Game game2= new Game(LocalDateTime.now().plusHours(1));
			Game game3= new Game(LocalDateTime.now().plusHours(2));
			Game game4= new Game(LocalDateTime.now().plusHours(3));
			Game game5= new Game(LocalDateTime.now().plusHours(4));
			Game game6= new Game(LocalDateTime.now().plusHours(5));
			Game game7= new Game(LocalDateTime.now().plusHours(6));
			Game game8= new Game(LocalDateTime.now().plusHours(7));
			//Game game9= new Game(LocalDateTime.now().plusHours(8));

            Salvo salvo1 = new Salvo(1,Arrays.asList("B5","C5","F1"));
            Salvo salvo2 = new Salvo(1,Arrays.asList("B4","B5","B6"));
            Salvo salvo3 = new Salvo(2,Arrays.asList("F2","D5"));
            Salvo salvo4 = new Salvo(2,Arrays.asList("E1","H3","A2"));
            Salvo salvo5 = new Salvo(1,Arrays.asList("A2","A4","G6"));
            Salvo salvo6 = new Salvo(1,Arrays.asList("B5","D5","C7"));
            Salvo salvo7 = new Salvo(2,Arrays.asList("A3","H6"));
            Salvo salvo8 = new Salvo(2,Arrays.asList("C5","C6"));
            Salvo salvo9 = new Salvo(1,Arrays.asList("G6","H6","A4"));
            Salvo salvo10 = new Salvo(1,Arrays.asList("H1","H2","H3"));
            Salvo salvo11 = new Salvo(2,Arrays.asList("A2","A3","D8"));
            Salvo salvo12 = new Salvo(2,Arrays.asList("E1","F2","G3"));
            Salvo salvo13 = new Salvo(1,Arrays.asList("A3","A4","F7"));
            Salvo salvo14 = new Salvo(1,Arrays.asList("B5","C6","H1"));
            Salvo salvo15 = new Salvo(2,Arrays.asList("A2","G6","H6"));
            Salvo salvo16 = new Salvo(2,Arrays.asList("C5","C7","D5"));
            Salvo salvo17 = new Salvo(1,Arrays.asList("A1","A2","A3"));
            Salvo salvo18 = new Salvo(1,Arrays.asList("B5","B6","C7"));
            Salvo salvo19 = new Salvo(2,Arrays.asList("G6","G7","G8"));
            Salvo salvo20 = new Salvo(2,Arrays.asList("C6","D6","E6"));
            Salvo salvo21 = new Salvo(3,Arrays.asList("H1","H8"));

			GamePlayer gp1 = new GamePlayer(player1, game1, game1.getCreateDate());
			GamePlayer gp2 = new GamePlayer(player1, game2, game2.getCreateDate());
			GamePlayer gp3 = new GamePlayer(player1, game4);
			GamePlayer gp4 = new GamePlayer(player1, game5);
			GamePlayer gp5 = new GamePlayer(player2, game1);
			GamePlayer gp6 = new GamePlayer(player2, game2);
			GamePlayer gp7 = new GamePlayer(player2, game3, game3.getCreateDate());
			GamePlayer gp8 = new GamePlayer(player2, game4, game4.getCreateDate());
			GamePlayer gp9 = new GamePlayer(player3, game6, game6.getCreateDate());
			GamePlayer gp10 = new GamePlayer(player3, game8, game8.getCreateDate());
			GamePlayer gp11 = new GamePlayer(player4, game3);
			GamePlayer gp12 = new GamePlayer(player4, game5, game5.getCreateDate());
			GamePlayer gp13 = new GamePlayer(player4, game7, game7.getCreateDate());
			GamePlayer gp14 = new GamePlayer(player4, game8);
			//GamePlayer gp15 = new GamePlayer(player0, game9);


			Ship ship0 = new Ship();
			Ship ship1 = new Ship("destroyer", Arrays.asList("H2","H3","H4"));
			Ship ship2 = new Ship("submarine",gp1, Arrays.asList("E1","F1","G1"));
			Ship ship3 = new Ship("patrol_boat",gp1, Arrays.asList("B4", "B5"));
			Ship ship4 = new Ship("destroyer",gp5, Arrays.asList("B5", "C5", "D5"));
			Ship ship5 = new Ship("patrol_boat",gp5, Arrays.asList("F1", "F2"));
			Ship ship6 = new Ship("destroyer",gp2, Arrays.asList("B5", "C5","D5"));
			Ship ship7 = new Ship("patrol_boat",gp2, Arrays.asList("C6", "C7"));
			Ship ship8 = new Ship("submarine",gp6, Arrays.asList("A2", "A3","A4"));
			Ship ship9 = new Ship("patrol_boat",gp6, Arrays.asList("G6", "H6"));
			Ship ship10 = new Ship("destroyer",gp7, Arrays.asList("B5", "C5","D5"));
			Ship ship11 = new Ship("patrol_boat",gp7, Arrays.asList("C6", "C7"));
			Ship ship12 = new Ship("submarine",gp11, Arrays.asList("A2", "A3","A4"));
			Ship ship13 = new Ship("patrol_boat",gp11, Arrays.asList("G6","H6"));
			Ship ship14 = new Ship("destroyer",gp8, Arrays.asList("B5", "C5","D5"));
			Ship ship15 = new Ship("patrol_boat",gp8, Arrays.asList("C6", "C7"));
			Ship ship16 = new Ship("submarine",gp3, Arrays.asList("A2", "A3","A4"));
			Ship ship17 = new Ship("patrol_boat",gp3, Arrays.asList("G6", "H6"));
			Ship ship18 = new Ship("destroyer",gp12, Arrays.asList("B5", "C5","D5"));
			Ship ship19 = new Ship("patrol_boat",gp12, Arrays.asList("C6", "C7"));
			Ship ship20 = new Ship("submarine",gp4, Arrays.asList("A2", "A3","A4"));
			Ship ship21 = new Ship("patrol_boat",gp4, Arrays.asList("G6", "H6"));
			Ship ship22 = new Ship("destroyer",gp9, Arrays.asList("B5", "C5","D5"));
			Ship ship23 = new Ship("patrol_boat",gp9, Arrays.asList("C6", "C7"));
			Ship ship24 = new Ship("destroyer",gp10, Arrays.asList("B5", "C5","D5"));
			Ship ship25 = new Ship("patrol_boat",gp10, Arrays.asList("C6", "C7"));
			Ship ship26 = new Ship("submarine",gp14, Arrays.asList("A2", "A3","A4"));
			Ship ship27 = new Ship("patrol_boat",gp14, Arrays.asList("G6", "H6"));
			gp1.addShip(ship1);



			Score score1 = new Score(1,LocalDateTime.now().plusHours(1),game1,player1);
			Score score2 = new Score(0,LocalDateTime.now().plusHours(1),game1,player2);
			Score score3 = new Score(0.5,LocalDateTime.now().plusHours(2),game2,player1);
			Score score4 = new Score(0.5,LocalDateTime.now().plusHours(2),game2,player2);
			Score score5 = new Score(1,LocalDateTime.now().plusHours(3),game3,player2);
			Score score6 = new Score(0,LocalDateTime.now().plusHours(3),game3,player4);
			Score score7 = new Score(0.5,LocalDateTime.now().plusHours(4),game4,player2);
			Score score8 = new Score(0.5,LocalDateTime.now().plusHours(4),game4,player1);



			gp1.addSalvo(salvo1);
			gp1.addSalvo(salvo3);
			gp5.addSalvo(salvo2);
			gp5.addSalvo(salvo4);
			gp2.addSalvo(salvo5);
			gp2.addSalvo(salvo7);
			gp6.addSalvo(salvo6);
			gp6.addSalvo(salvo8);
			gp7.addSalvo(salvo9);
			gp7.addSalvo(salvo11);
			gp11.addSalvo(salvo10);
			gp11.addSalvo(salvo12);
			gp8.addSalvo(salvo13);
			gp8.addSalvo(salvo15);
			gp3.addSalvo(salvo14);
			gp3.addSalvo(salvo16);
			gp12.addSalvo(salvo17);
			gp12.addSalvo(salvo19);
			gp4.addSalvo(salvo18);
			gp4.addSalvo(salvo20);
			gp4.addSalvo(salvo21);

			playerRepository.save(player0);
			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);


			gameRepository.save(game1);
			gameRepository.save(game2);
			gameRepository.save(game3);
			gameRepository.save(game4);
			gameRepository.save(game5);
			gameRepository.save(game6);
			gameRepository.save(game7);
			gameRepository.save(game8);


			gamePlayerRepository.save(gp1);
			gamePlayerRepository.save(gp2);
			gamePlayerRepository.save(gp3);
			gamePlayerRepository.save(gp4);
			gamePlayerRepository.save(gp5);
			gamePlayerRepository.save(gp6);
			gamePlayerRepository.save(gp7);
			gamePlayerRepository.save(gp8);
			gamePlayerRepository.save(gp9);
			gamePlayerRepository.save(gp10);
			gamePlayerRepository.save(gp11);
			gamePlayerRepository.save(gp12);
			gamePlayerRepository.save(gp13);
			gamePlayerRepository.save(gp14);
			//gamePlayerRepository.save(gp15);


			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);
			shipRepository.save(ship6);
			shipRepository.save(ship7);
			shipRepository.save(ship8);
			shipRepository.save(ship9);
			shipRepository.save(ship10);
			shipRepository.save(ship11);
			shipRepository.save(ship12);
			shipRepository.save(ship13);
			shipRepository.save(ship14);
			shipRepository.save(ship15);
			shipRepository.save(ship16);
			shipRepository.save(ship17);
			shipRepository.save(ship18);
			shipRepository.save(ship19);
			shipRepository.save(ship20);
			shipRepository.save(ship21);
			shipRepository.save(ship22);
			shipRepository.save(ship23);
			shipRepository.save(ship24);
			shipRepository.save(ship25);
			shipRepository.save(ship26);
			shipRepository.save(ship27);

			salvoReposiory.save(salvo1);
			salvoReposiory.save(salvo2);
			salvoReposiory.save(salvo3);
			salvoReposiory.save(salvo4);
			salvoReposiory.save(salvo5);
			salvoReposiory.save(salvo6);
			salvoReposiory.save(salvo7);
			salvoReposiory.save(salvo8);
			salvoReposiory.save(salvo9);
			salvoReposiory.save(salvo10);
			salvoReposiory.save(salvo11);
			salvoReposiory.save(salvo12);
			salvoReposiory.save(salvo13);
			salvoReposiory.save(salvo14);
			salvoReposiory.save(salvo15);
			salvoReposiory.save(salvo16);
			salvoReposiory.save(salvo17);
			salvoReposiory.save(salvo18);
			salvoReposiory.save(salvo19);
			salvoReposiory.save(salvo20);
			salvoReposiory.save(salvo21);

			scoreRepository.save(score1);
			scoreRepository.save(score2);
			scoreRepository.save(score3);
			scoreRepository.save(score4);
			scoreRepository.save(score5);
			scoreRepository.save(score6);
			scoreRepository.save(score7);
			scoreRepository.save(score8);

		};
	}




}
