package sample;

public class RandomString {
  public static String randomString() {
    // Utility function to generate random string.
    return Long.toHexString(Double.doubleToLongBits(Math.random()));
  }
}
