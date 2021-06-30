import org.junit.Before;
import org.junit.Test;
import sample.ByteHelper;
import sample.DataHelper;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ByteHelperTest {
  // Initialize a set of random bytes.
  final byte[] randomBytes = new byte[256];

  @Before
  public void setUp() {
    // Fill said set of random bytes with pseudorandom data.
    Random random = new Random();
    random.nextBytes(randomBytes);
  }

  @Test
  public void toBytesFromString() {
    // Test toBytesFromString function.

    // Assert that the random bytes of the array is equal to the random bytes called from the toBytesFromString on the randomBytes, as a string, using regex to strip.
    assertEquals(Arrays.hashCode(randomBytes), Arrays.hashCode(ByteHelper.toBytesFromString(DataHelper.normalizeString(Arrays.toString(randomBytes)))));
  }
}