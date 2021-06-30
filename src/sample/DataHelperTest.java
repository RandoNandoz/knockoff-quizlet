package sample;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataHelperTest {
  String string;
  @Before
  // Create mock array of stuff
  public void setUp() {
    string = "[10, -20, 60, -40]";
  }

  @Test
  public void normalizeString() {
    // Test if the regex works.
    assertEquals(DataHelper.normalizeString(string), string.replaceAll("\\[", "").replaceAll("]", "").replaceAll(",", "|").replaceAll(" ", ""));

    // This SHOULDNT work as one has been normalized and the other hasnt.
    assertNotEquals(DataHelper.normalizeString(string), string);
  }
}