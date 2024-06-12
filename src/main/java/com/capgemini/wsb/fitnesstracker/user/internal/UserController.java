package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.training.internal.TrainingServiceImpl;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Slf4j
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    private final TrainingServiceImpl trainingService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @GetMapping("/simple")
    public List<UserA> getAllUserA(){
        return userService.findAllUsers().stream().map(UserMapper::aToDto).toList();
    }

    @GetMapping("/userId")
    public Optional<User> getUserById(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserDto userDto){
        return userService.createUser(userMapper.toEntity(userDto));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId){
        trainingService.deleteTrainingByUserId(userId);
        userService.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        return userService.updateUser(userId, userDto);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<User>> getUserByEmail(@RequestParam String email){
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isPresent()){
            List<User> userList = new ArrayList<>();
            userList.add(optionalUser.get());
            return ResponseEntity.ok(userList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/older/{date}")
    public ResponseEntity<List<User>> getAllUsersOlderThan(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    List<User> users = userService.getOlderUsers(date);
    return ResponseEntity.ok(users);
    }
}