package com.example.enemy_service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemyService {
    private final EnemyRepository enemyRepository;

    public EnemyService(EnemyRepository enemyRepository){
        this.enemyRepository = enemyRepository;
    }

    public boolean isInEnemy(int x, int y){
        List<EnemyEntity> lst = enemyRepository.findAll();
        for(EnemyEntity enemy: lst){
            if(enemy.getPosX()==x && enemy.getPosY()==y){
                return true;
            }
        }
        return false;
    }

    public String delete(int x, int y){
        List<EnemyEntity> lst = enemyRepository.findAll();
        for(EnemyEntity enemy: lst){
            if(enemy.getPosX()==x && enemy.getPosY()==y){
                enemyRepository.delete(enemy);
                return "Ataque realizado con éxito. Haz eliminado al enemigo";
            }
        }
        return "No se han realizado cambios (no se eliminó al enemigo)";
    }
}
