package com.example.enemy_service;

import jakarta.persistence.*;

@Entity
@Table(name = "Enemigo")
public class EnemyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private int posX;
    @Column(nullable = false)
    private int posY;

    public EnemyEntity(){}

    public Long getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
