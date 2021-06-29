package sample;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataHelperTest {
  String string;
  @Before
  public void setUp() {
    string = "[10, -20, 60, -40]";
  }

  @Test
  public void normalizeString() {
    assertEquals(DataHelper.normalizeString(string), string.replaceAll("\\[", "").replaceAll("]", "").replaceAll(",", "|").replaceAll(" ", ""));
    assertNotEquals(DataHelper.normalizeString(string), string);
  }
}