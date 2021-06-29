package sample;

import java.util.stream.IntStream;

public class ByteHelper {
  public static byte[] toBytesFromString(String string) {
    // We split the string param into an array of strings.
    // The input string is a bunch of bytes, separated by pipes.
    String[] bytesString = string.split("\\|");

    // Make a byte array which we return.
    byte[] bytes = new byte[bytesString.length];

    // Now, for each item in the input string, which we previously split,
    // we iterate across that string array, and for each item in that array,
    // we parse it as a byte.
    IntStream.range(0, bytes.length).forEach(i -> bytes[i] = Byte.parseByte(bytesString[i]));

    // Voila, here are our bytes!
    return bytes;
  }
}
