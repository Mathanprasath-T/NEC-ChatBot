package com.example.chatbot;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ChatRepository extends JpaRepository<ChatData, Long> {
    // Database-la keyword search panna logic
    List<ChatData> findAll();
}