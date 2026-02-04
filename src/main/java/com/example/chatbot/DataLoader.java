package com.example.chatbot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ChatRepository repository;

    public DataLoader(ChatRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        Path path = Paths.get("document.txt");
        if (Files.exists(path)) {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            for (String line : lines) {
                if (line.contains(":")) {
                    String[] parts = line.split(":", 3);
                    ChatData data = new ChatData();
                    data.setKeyword(parts[0].trim());
                    data.setAnswer(parts[1].trim());
                    if (parts.length > 2) data.setImagePath(parts[2].trim());
                    repository.save(data);
                }
            }
            System.out.println(">>> 1000+ Data loaded into H2 Database! <<<");
        }
    }
}