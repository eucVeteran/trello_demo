package com.example.kafka;

import com.example.dto.UserDto;
import com.example.kafka.messages.KafkaCommand;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.example.kafka.KafkaActions.*;

/**
 * @author Azizbek Toshpulatov
 */
@Service
public class KafkaConsumer {
    @Autowired
    private UserService userService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @KafkaListener(topics = "User", groupId = "trelloProxy")
    public void consumeCreateUser(KafkaCommand<User> command) {
        User user = command.data();

        if (command.action() == CREATE) {
            userService.save(user);
            kafkaProducer.sendUser(user, command.commandId());
        } else if (command.action() == UPDATE) {
            User userToUpdate = userService.findById(user.getId());
            User response;
            if (userToUpdate != null) {
                userToUpdate.setUsername(user.getUsername());
                userToUpdate.setEmail(user.getEmail());
                response = userService.save(userToUpdate);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            kafkaProducer.sendUser(response, command.commandId());
        }
    }

    @KafkaListener(topics = "Long", groupId = "trelloProxy")
    public void consumeGetUser(KafkaCommand<Long> command) {
        if (command.action() == GET) {
            User user = userService.findById(command.data());
            kafkaProducer.sendUser(user, command.commandId());
        } else if (command.action() == DELETE) {
            userService.deleteById(command.data());
            kafkaProducer.sendString("User deleted", command.commandId());
        }
    }

    @KafkaListener(topics = "String", groupId = "trelloProxy")
    public void consumeGetAllUsers(KafkaCommand<String> command) {
        var users = userService.findAll();
        kafkaProducer.sendUserList(users, command.commandId());
    }

    @KafkaListener(topics = "UserDto", groupId = "trelloProxy")
    public void consumeRegisterUser(KafkaCommand<UserDto> command) {
        UserDto userDto = command.data();
        String response;
        try {
            userService.registerUser(userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
            response = "User registered successfully";
        } catch (RuntimeException e) {
            response = e.getMessage();
        }
        kafkaProducer.sendString(response, command.commandId());
    }
}