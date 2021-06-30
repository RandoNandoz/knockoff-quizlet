package sample;

public class DataHelper {
  // Use our regex to normalize the string into something CSV compliant. Removes the brackets, replaces commas with pipes, and spaces to reduce space taken.
  public static String normalizeString(String string) {
    return string.replaceAll("\\[", "").replaceAll("]", "").replaceAll(",", "|").replaceAll(" ", "");
  }
}
