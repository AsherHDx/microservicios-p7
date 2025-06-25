package com.example.player_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="enemy-service")
public interface EnemyServiceFeignClient {
    @GetMapping("/enemies/isInEnemy")
    boolean isInEnemy(@RequestParam("x") int x, @RequestParam("y") int y);

    @GetMapping("/enemies/delete")
    String delete(@RequestParam("x") int x, @RequestParam("y") int y);
}
