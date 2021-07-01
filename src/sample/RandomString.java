package sample;

public class RandomString {
  // Hide public constructor.
  private RandomString() {
  }

  public static String randomString() {
    // Utility function to generate random string.
    return Long.toHexString(Double.doubleToLongBits(Math.random()));
  }
}
