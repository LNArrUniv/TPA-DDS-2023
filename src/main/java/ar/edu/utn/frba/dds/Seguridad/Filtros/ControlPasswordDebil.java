package ar.edu.utn.frba.dds.Seguridad.Filtros;

import java.io.BufferedReader;
import java.io.FileReader;

public class ControlPasswordDebil implements FiltroInterface {
  private static String commonPasswordsPath;

  public ControlPasswordDebil() {
    commonPasswordsPath = "src/main/java/ar/edu/utn/frba/dds/Seguridad/Filtros/common-passwords.txt";
  }

  @Override
  public Boolean validar(String password) {
    return !EsComun(password);
  }

  public static boolean EsComun(String password){
    try{
      BufferedReader buff = new BufferedReader(new FileReader(commonPasswordsPath));
      String s;
      while((s=buff.readLine())!=null){
        if(s.trim().contains(password)){
          return true;
        }
      }
      buff.close();
    }catch(Exception e){e.printStackTrace();}
    return false;
  }
}
