package com.ejercicio2.Veterinaria.controller;

import com.ejercicio2.Veterinaria.model.Mascota;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private List<Mascota> listaMascotas = new ArrayList<>();

    // Constructor para simular algunas mascotas de ejemplo
    public MascotaController() {
        listaMascotas.add(new Mascota(1L, "Rex", "Perro", LocalDate.of(2020, 5, 15), "Carlos"));
        listaMascotas.add(new Mascota(2L, "Miau", "Gato", LocalDate.of(2021, 3, 10), "Ana"));
        listaMascotas.add(new Mascota(3L, "Luna", "Conejo", LocalDate.of(2022, 8, 30), "David"));
    }

    // 1. Obtener todas las mascotas
    @GetMapping
    public ResponseEntity<List<Mascota>> obtenerMascotas() {
        return new ResponseEntity<>(listaMascotas, HttpStatus.OK);
    }

    // 2. Obtener una mascota por id
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascota(@PathVariable Long id) {
        return listaMascotas.stream()
                .filter(mascota -> mascota.getId().equals(id))
                .findFirst()
                .map(mascota -> new ResponseEntity<>(mascota, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 3. Obtener mascotas menores a cierta edad
    @GetMapping("/menores")
    public ResponseEntity<List<Mascota>> obtenerMascotasMenores(@RequestParam int edad) {
        List<Mascota> mascotasMenores = listaMascotas.stream()
                .filter(mascota -> calcularEdad(mascota.getFechaNacimiento()) < edad)
                .collect(Collectors.toList());

        // Si la lista está vacía, devolver 200 OK con un array vacío
        if (mascotasMenores.isEmpty()) {
            return new ResponseEntity<>(mascotasMenores, HttpStatus.OK);
        }

        return new ResponseEntity<>(mascotasMenores, HttpStatus.OK);
    }

    // Método para calcular la edad exacta de una mascota
    private int calcularEdad(LocalDate fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - fechaNacimiento.getYear();

        // Si no ha cumplido años este año, restamos 1
        if (fechaNacimiento.getMonthValue() > hoy.getMonthValue() ||
                (fechaNacimiento.getMonthValue() == hoy.getMonthValue() && fechaNacimiento.getDayOfMonth() > hoy.getDayOfMonth())) {
            edad--;
        }

        return edad;
    }

    // 4. Registrar una nueva mascota
    @PostMapping
    public ResponseEntity<Mascota> registrarMascota(@RequestBody Mascota nuevaMascota) {
        nuevaMascota.setId((long) (listaMascotas.size() + 1)); // Generar un ID secuencial simple
        listaMascotas.add(nuevaMascota);
        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
    }
}
