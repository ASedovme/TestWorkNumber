package me.asedov.TestWorkNumber.controller;

import me.asedov.TestWorkNumber.service.NumberFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/number")
public class MinNumController {

    private final NumberFinderService numberFinderService;

    @Autowired
    public MinNumController(NumberFinderService numberFinderService) {
        this.numberFinderService = numberFinderService;
    }

    @GetMapping("/min-number")
    public ResponseEntity<?> getNthMinimal(@RequestParam String filePath, @RequestParam int N) {
        try {
            int result = numberFinderService.findNthMinimalNumber(filePath,N);
            return ResponseEntity.ok(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
