package ar.edu.utn.frba.dds.seguridad.filtros;

public class ControlPasswordDebil implements FiltroInterface {
  private static final String[] password_list = {
    "password", "123456", "123456789", "guest", "qwerty",
    "12345678", "111111", "12345", "col123456", "123123",
    "1234567", "1234", "1234567890", "000000", "555555",
    "666666", "123321", "1q2w3e4r", "qwertyuiop", "asdfghjkl",
    "zxcvbnm", "qwerty123", "admin", "letmein", "welcome",
    "monkey", "baseball", "superman", "iloveyou", "babygirl",
    "qazwsx", "sunshine", "master", "password1", "princess",
    "starwars", "michael", "football", "hello", "qwertyuiop123",
    "password123", "p@ssw0rd", "shadow", "iloveyou1", "admin123",
    "welcome123", "adminadmin", "password12", "admin@123", "adminadmin123",
    "changeme", "test123", "123456a", "123456789a", "password1234",
    "letmein123", "abcd1234", "qwe123", "abcdefg", "12345678910",
    "qwertyuiop1234", "admin1", "password12345", "admin1234", "welcome1",
    "password12345", "password@123", "password!", "qweasdzxc", "asdfghjkl123",
    "adminadmin1", "iloveyou123", "123456789qwerty", "admin12", "password123456",
    "mypassword", "abcdef123", "password123456789", "qweqweqwe", "letmein1",
    "qweasdzxc123", "password2", "admin@1234", "qwerty12345", "password12!",
    "password123!", "password!123", "password@1", "1234abcd", "iloveyou!",
    "iloveyou1234", "qwerty123456", "password1234!", "changeme123", "1234qwer",
    "testtest", "password1234!", "qwertyuiop12345", "password1234567", "adminadminadmin",
    "password!1234", "password!12345", "adminpassword", "1234567890a", "qwerty123456789",
    "qwertyuiop123456", "letmein1234", "admin@12345", "password12!34", "iloveyou12345",
    "password1234567890", "123456789a!", "password!123456", "mypassword123"
      };

  @Override
  public Boolean validar(String password) {
    for (String pswList : password_list) {
      if (pswList.equals(password)) {
        return false;
      }
    }
    return true;
  }
}
