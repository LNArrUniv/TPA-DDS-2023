package ar.edu.utn.frba.dds.Controller;

import java.util.List;
import ar.edu.utn.frba.dds.Modelos.Rankings.GradoImpacto;
import ar.edu.utn.frba.dds.Modelos.Incidente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "GradoImpactoController", description = "Operations related to GradoImpacto")
public class GradoImpactoController {

    @ApiOperation(value = "Calcular impacto", response = Integer.class)
    public Integer calculateImpact(List<Incidente> incidents) {
        GradoImpacto gradoImpacto = new GradoImpacto();
        return gradoImpacto.calculoImpacto(incidents);
    }
}