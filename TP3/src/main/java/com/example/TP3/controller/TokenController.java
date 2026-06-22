package com.example.TP3.controller;

import java.time.Duration;
import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
public class TokenController {

    private final RedisTemplate<String, String> redisTemplate;

    public TokenController(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/generate/{alunoId}")
    public ResponseEntity<String> generate(@PathVariable Long alunoId) {
        String token = UUID.randomUUID().toString();
        String key = "token:" + token;
        redisTemplate.opsForValue().set(key, String.valueOf(alunoId), Duration.ofMinutes(5));
        return ResponseEntity.ok(token);
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<Boolean> validate(@PathVariable String token) {
        String key = "token:" + token;
        String val = redisTemplate.opsForValue().get(key);
        return ResponseEntity.ok(val != null);
    }
}
