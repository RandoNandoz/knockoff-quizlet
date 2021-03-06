import org.junit.Before;
import org.junit.Test;
import sample.DataHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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

    // This SHOULDN'T work as one has been normalized and the other hasn't.
    assertNotEquals(DataHelper.normalizeString(string), string);
  }
}