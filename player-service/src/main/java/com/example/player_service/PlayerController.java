package com.example.player_service;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/saludo")
    public String welcome() {
        return "Saludos";
    }

    @GetMapping("/checkUser/{username}")
    public boolean existsUser(@PathVariable String username) {
        return playerService.getPlayerByName(username) != null;
    }

    @PostMapping("/addPlayer/{username}")
    public String addPlayer(@PathVariable String username) {
        PlayerEntity newPlayer = new PlayerEntity(username);
        System.out.println("newPlayer: " + newPlayer.getName());
        return playerService.addPlayer(newPlayer);
    }

    @PutMapping("/move/{username}")
    public String movePlayer(@PathVariable String username, @RequestBody Map<String,Integer> pos) {
        return playerService.movePlayer(username, pos.get("posX"), pos.get("posY"));
    }

    @GetMapping("/status/{username}")
    public String statusPlayer(@PathVariable String username){
        return playerService.statusPlayer(username);
    }


    @PutMapping("/attack/{username}")
    public String attackEnemy(@PathVariable String username) {
        return playerService.attack(username);
    }

    @PutMapping("/delete/{username}")
    public String deletePlayer(@PathVariable String username) {
        return playerService.deletePlayer(username);
    }
}
