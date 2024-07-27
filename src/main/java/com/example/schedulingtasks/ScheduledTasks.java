package com.example.schedulingtasks;

import com.example.model.Board;
import com.example.model.Card;
import com.example.model.Column;
import com.example.model.User;
import com.example.service.BoardService;
import com.example.service.CardService;
import com.example.service.ColumnService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Azizbek Toshpulatov
 */
@Component
public class ScheduledTasks {
    @Autowired
    private BoardService boardService;

    @Autowired
    private CardService cardService;

    @Autowired
    private ColumnService columnService;

    @Autowired
    private UserService userService;

    private final Random random = new Random();
    private long usernameInt = 0;
    private long emailInt = 0;
    private long passwordInt = 0;


    @Value("${schedule.delay}")
    private long scheduleDelay;

    @Scheduled(fixedRateString = "${schedule.delay}")
    public void insertRandomEntity() {
        System.out.println("Default value is: " + scheduleDelay);
        double probability = random.nextDouble();

        if (probability < 0.8) {
            Card card = new Card();
            cardService.save(card);
            System.out.println("Saved a new Card entity.");
        } else if (probability < 0.9) {
            Column column = new Column();
            columnService.save(column);
            System.out.println("Saved a new Column entity.");
        } else if (probability < 0.95) {
            Board board = new Board();
            boardService.save(board);
            System.out.println("Saved a new Board entity.");
        } else {
            User user = new User();

            user.setUsername(String.valueOf(usernameInt));
            user.setEmail(String.valueOf(emailInt));
            user.setPassword(String.valueOf(passwordInt));

            usernameInt++;
            emailInt++;
            passwordInt++;

            userService.save(user);
            System.out.println("Saved a new User entity.");
        }
    }
}
