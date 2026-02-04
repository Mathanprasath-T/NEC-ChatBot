package com.example.chatbot;

// Thanithaniyaa import panradhu professional-aa irukkum
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class ChatData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;
    
    // 1000 characters mela answer irundha Column length mukkiyam
    @Column(length = 1000)
    private String answer;
    
    private String imagePath;

    // Default Constructor (JPA-vukku idhu kandippa venum)
    public ChatData() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getKeyword() { return keyword; }
    public void setKeyword(String keyword) { this.keyword = keyword; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}