package com.astral.sound.backend.controller;

import com.astral.sound.backend.model.Cancion;
import com.astral.sound.backend.repository.CancionRepository;
import com.astral.sound.backend.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private CancionRepository cancionRepository;

    @GetMapping
    public List<Cancion> listarCanciones() {
        return cancionRepository.findAll();
    }

    @PostMapping("/subir")
    public ResponseEntity<?> subirCancion(
            @RequestParam("titulo") String titulo,
            @RequestParam("artista") String artista,
            @RequestParam("album") String album,
            @RequestParam("duracionSegundos") Integer duracionSegundos,
            @RequestParam("archivo") MultipartFile archivo
    ) {
        try {
            // 1. Sube el archivo MP3 a Cloudinary
            String urlAudio = cloudinaryService.subirArchivo(archivo);

            // 2. Crea la entidad y guarda en MySQL
            Cancion nuevaCancion = new Cancion();
            nuevaCancion.setTitulo(titulo);
            nuevaCancion.setArtista(artista);
            nuevaCancion.setAlbum(album);
            nuevaCancion.setDuracionSegundos(duracionSegundos);
            nuevaCancion.setArchivoAudioUrl(urlAudio);

            Cancion guardada = cancionRepository.save(nuevaCancion);
            return ResponseEntity.ok(guardada);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error al procesar el archivo: " + e.getMessage());
        }
    }
}
