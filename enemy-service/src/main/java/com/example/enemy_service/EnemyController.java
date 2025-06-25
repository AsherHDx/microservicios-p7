package com.example.enemy_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enemies")
public class EnemyController {
    private final EnemyService enemyService;

    public EnemyController(EnemyService enemyService){
        this.enemyService = enemyService;
    }

    @RequestMapping("/isInEnemy")
    public boolean isInEnemy(@RequestParam int x,@RequestParam int y){
        return enemyService.isInEnemy(x,y);
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int x, @RequestParam int y){
        return enemyService.delete(x,y);
    }

}
