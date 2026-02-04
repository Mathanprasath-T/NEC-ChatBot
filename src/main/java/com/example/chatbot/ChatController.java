package com.example.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @GetMapping("/chat")
    public Map<String, String> getResponse(@RequestParam String query) {
        Map<String, String> response = new HashMap<>();
        String userQuery = query.toLowerCase().trim();

        // Default Badhilgal
        String defaultEnglish = "Sorry, I don't have information about that.";
        String defaultTamil = "மன்னிக்கவும், அதைப் பற்றிய தகவல் என்னிடம் இல்லை.";

        // Input Tamil-aa nu check pannurom
        boolean isUserAskingInTamil = query.matches(".*[\\u0B80-\\u0BFF]+.*");
        response.put("text", isUserAskingInTamil ? defaultTamil : defaultEnglish);
        response.put("image", "none");

        try {
            // Database-la irukkira ella data-vaiyum edukkurom
            List<ChatData> allData = chatRepository.findAll();

            for (ChatData data : allData) {
                String keyword = data.getKeyword().toLowerCase().trim();
                
                // Flexible Matching: User query-la keyword irukka nu paarkurom
                if (userQuery.contains(keyword) || keyword.contains(userQuery)) {
                    
                    boolean isDataTamil = keyword.matches(".*[\\u0B80-\\u0BFF]+.*");

                    // Language matching condition (Tamil-na Tamil badhil varanum)
                    if (isUserAskingInTamil == isDataTamil) {
                        response.put("text", data.getAnswer());
                        
                        // Image path database-la irundha adhai anuppurom
                        if (data.getImagePath() != null && !data.getImagePath().trim().equalsIgnoreCase("none")) {
                            response.put("image", data.getImagePath().trim());
                        } else {
                            response.put("image", "none");
                        }
                        return response;
                    }
                }
            }
        } catch (Exception e) {
            response.put("text", "Database Error: " + e.getMessage());
        }

        return response;
    }
}