package sample;

public class DataHelper {
  public static String normalizeString(String string) {
    return string.replaceAll("\\[", "").replaceAll("]", "").replaceAll(",", "|").replaceAll(" ", "");
  }
}
