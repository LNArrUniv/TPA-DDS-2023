package ar.edu.utn.frba.dds.seguridad.filtros;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ControlPasswordDebil implements FiltroInterface {
  private static String commonPasswordsPath;

  public ControlPasswordDebil() {
    commonPasswordsPath =
        "src/main/java/ar/edu/utn/frba/dds/Seguridad/Filtros/common-passwords.txt";
  }

  @Override
  public Boolean validar(String password) {
    return !esComun(password);
  }

  public boolean esComun(String password) {
    try (InputStream inputStream = this.getClass()
        .getClassLoader().getResourceAsStream("public/files/common-passwords.txt");
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader buff = new BufferedReader(isr)
        ) {
      String s;
      while ((s = buff.readLine()) != null) {
        if (s.trim().contains(password)) {
          return true;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
