package ar.edu.utn.frba.dds.Modelos.Rankings;

import ar.edu.utn.frba.dds.Modelos.Incidente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradoImpactoService {

    private final GradoImpacto gradoImpacto;

    public GradoImpactoService(GradoImpacto gradoImpacto) {
        this.gradoImpacto = gradoImpacto;
    }

    public Long calcularImpacto(List<Incidente> incidents) {
        return gradoImpacto.calculoImpacto(incidents);
    }
}
