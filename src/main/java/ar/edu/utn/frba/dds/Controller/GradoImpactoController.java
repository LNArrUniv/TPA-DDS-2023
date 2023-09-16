package ar.edu.utn.frba.dds.Controller;

import ar.edu.utn.frba.dds.Modelos.Rankings.GradoImpactoService;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class GradoImpactoController {
    private final GradoImpactoService gradoImpactoService;

    @Autowired
    public GradoImpactoController(GradoImpactoService gradoImpactoService) {
        this.gradoImpactoService = gradoImpactoService;
    }

    @GetMapping("/calcularImpacto")
    public ResponseEntity<Long> calculateImpactGet(@RequestBody List<Incidente> incidents) {
        Long resultado = gradoImpactoService.calcularImpacto(incidents);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/calcularImpacto")
    public ResponseEntity<Long> calculateImpactPost(@RequestBody List<Incidente> incidents) {
        Long resultado = gradoImpactoService.calcularImpacto(incidents);
        return ResponseEntity.ok(resultado);
    }
}
