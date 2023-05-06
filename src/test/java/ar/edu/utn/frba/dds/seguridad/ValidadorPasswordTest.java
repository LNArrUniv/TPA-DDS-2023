package ar.edu.utn.frba.dds.seguridad;

import ar.edu.utn.frba.dds.seguridad.filtros.ControlPasswordDebil;
import ar.edu.utn.frba.dds.seguridad.filtros.PoliticaNist800;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ValidadorPasswordTest {
  private String passwordDebil = "1234567890";
  private String passwordNoCumpleLaPoliticaNIST = "fgALP";
  private String passwordValida = "b5ryDTt8s";
  private ValidadorPassword validador;

  @BeforeEach
  public void init(){
    validador = new ValidadorPassword();
    ControlPasswordDebil filtro1 = new ControlPasswordDebil();
    PoliticaNist800 filtro2 = new PoliticaNist800();
    validador.addFiltro(filtro1);
    validador.addFiltro(filtro2);
  }

  @Test
  public void contraseniaEntreLasTopNoEsValida(){
    Assertions.assertFalse(validador.validarPassword(passwordDebil));
  }

  @Test
  public void contraseniaNoCumpleLaLongitud(){
    Assertions.assertFalse(validador.validarPassword(passwordNoCumpleLaPoliticaNIST));
  }

  @Test
  public void contraseniaEsValida(){
    Assertions.assertTrue(validador.validarPassword(passwordValida));
  }
}
