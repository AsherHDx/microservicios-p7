package com.example.player_service;

import com.netflix.discovery.provider.Serializer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final EnemyServiceFeignClient enemyServiceFeignClient;

    public PlayerService (PlayerRepository playerRepository, EnemyServiceFeignClient enemyServiceFeignClient){
        this.playerRepository = playerRepository;
        this.enemyServiceFeignClient = enemyServiceFeignClient;
    }

    /*
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }*/


    public PlayerEntity getPlayerByName(String username) {
        Optional<PlayerEntity> playerOpt = playerRepository.findByName(username);
        return playerOpt.orElse(null);
    }

    @Transactional
    public String addPlayer(PlayerEntity player) {
        if(player.getName()==null || player.getName().isEmpty()){
            return "Es posible que el nombre no se esté recibiendo correctamente";
        }
        System.out.println("Se supone que sí hay nombre");
        playerRepository.save(player);
        return "Jugador añadido exitosamente";
    }

    public String movePlayer(String username, int posX, int posY){
        if(username==null || username.isEmpty()){
            return "Es posible que el nombre no se esté recibiendo correctamente";
        }
        Optional<PlayerEntity> existingPlayerOpt = playerRepository.findByName(username);
        if(existingPlayerOpt.isEmpty()){
            return "Jugador no encoentrado";
        }
        PlayerEntity existingPlayer = existingPlayerOpt.get();
        existingPlayer.setPosX(posX);
        existingPlayer.setPosY(posY);
        playerRepository.save(existingPlayer);

        if(enemyServiceFeignClient.isInEnemy(posX,posY)){
            return username+" fue movido a ("+posX+","+posY+") de manera exitosa. Está parado sobre un enemigo";
        }
        return username+" fue movido a ("+posX+","+posY+") de manera exitosa";
    }

    public String statusPlayer(String username){
        Optional<PlayerEntity> playerOpt = playerRepository.findByName(username);
        if(playerOpt.isEmpty()) return "No se encontró el jugador";
        PlayerEntity player = playerOpt.get();
        return "El jugador "+player.getName()+" está en la posición ("+player.getPosX()+","+player.getPosY()+")";
    }

    public String attack(String username){
        if(username==null || username.isEmpty()){
            return "Es posible que el nombre no se esté recibiendo correctamente";
        }
        Optional<PlayerEntity> existingPlayerOpt = playerRepository.findByName(username);
        if(existingPlayerOpt.isEmpty()){
            return "Jugador no encoentrado";
        }
        PlayerEntity existingPlayer = existingPlayerOpt.get();
        return enemyServiceFeignClient.delete(existingPlayer.getPosX(),existingPlayer.getPosY());
    }

    @Transactional
    public String deletePlayer(String username) {
        System.out.print("Ya en el service, antes del if");
        if(playerRepository.deleteByName(username)>0) return "Jugador "+username+" eliminado correctamente";
        return "La base de datos no ha sufrido cambios";
    }
}
