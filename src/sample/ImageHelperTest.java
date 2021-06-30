package sample;

import java.io.File;
import java.io.IOException;

import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ImageHelperTest {
  // Again, we need to test on the JavaFX thread so we need this rule.
  @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
  // Create two image vars, both should be different.
  Image image1;
  Image image2;

  @Before
  public void setUp() {
    // Set up our first Image.

    // Get the file from the path.
    File file = new File("Penguins_collage.png");

    // The get URI of the file.
    var stringURI = file.toURI().toString();

    // Create an image with the URI.
    image1 = new Image(stringURI);

    // Basically the same thing, but with another DIFFERENT image.
    File file2 = new File("Zürich_view_Quaibrücke_20200702.jpg");
    var stringURI2 = file2.toURI().toString();
    image2 = new Image(stringURI2);
  }

  @Test
  public void toImage() throws IOException {
    // Test the toImage function.

    // Assert that the Image, turned into bytes is equal to the toImage function, called on those bytes.
    assertTrue(ImageHelper.imageEqual(ImageHelper.toImage(ImageHelper.getImageBytes(image1)), image1));
  }

  @Test
  public void imageEqual() {
    // Test the imageEqual() function.

    // image1 and image1 are the same.
    assertTrue(ImageHelper.imageEqual(image1, image1));
    // This should be false as image1 and image2 are not the same.
    assertFalse(ImageHelper.imageEqual(image1, image2));
  }
}