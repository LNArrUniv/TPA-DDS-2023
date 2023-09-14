import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GradoImpactoController {

    @PostMapping("/calcular-impacto")
    public Integer calculateImpact(@RequestBody List<Incidente> incidents) {
        GradoImpacto gradoImpacto = new GradoImpacto();
        return gradoImpacto.calculoImpacto(incidents);
    }
}
