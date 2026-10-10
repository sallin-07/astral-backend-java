package com.astral.sound.backend.controller;

import com.astral.sound.backend.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/subir")
    public ResponseEntity<String> subirAudio(@RequestParam("archivo") MultipartFile archivo) {
        try {
            String url = cloudinaryService.subirArchivo(archivo);
            return ResponseEntity.ok(url);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error al subir el archivo: " + e.getMessage());
        }
    }
}
