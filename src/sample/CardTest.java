package sample;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CardTest {
  // Need to run the tests on the JavaFX thread because init for Image requires a GUI.
  @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
  Card card;
  String question = RandomString.randomString();
  String answer = RandomString.randomString();
  String hint = RandomString.randomString();

  @Before
  public void setUp() {
    // Create new card with picture.
    File file = new File("Penguins_collage.png");
    String uri = file.toURI().toString();
    Image image = new Image(uri);
    card = new Card(question, answer, hint, image);
  }

  @Test
  public void getImageBytes() throws IOException {
    // Test getImageBytes, to assert that the function works as intended.
    // Test if the image's bytes are equal to the bytes from the func.
    assertEquals(Arrays.toString(ImageHelper.getImageBytes(card.getImage())), getImageByteStringFromPath("Penguins_collage.png"));
  }

  @Test
  public void toData() throws IOException {
    // The data of the card is equal to our question, answer, hint and image all glued together with commas. Look at the fancy regex!
    assertEquals(card.toData(), question + "," + answer + "," + hint + "," + getImageByteStringFromPath("Penguins_collage.png"));
  }

  // Our function to get the ByteString from the path.
  private String getImageByteStringFromPath(String path) throws IOException {
    // Get our file from the path param.
    File file = new File(path);
    // Get the StringURI.
    String uri = file.toURI().toString();
    // Make the image using the URI.
    Image image = new Image(uri);

    // Turn the FXImage object into a bufferedImage.
    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);

    // Make an outputStream.
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    // And then write to that stream, encoding as png.
    ImageIO.write(bufferedImage, "png", byteArrayOutputStream);

    // Return the String array.
    return Arrays.toString(byteArrayOutputStream.toByteArray());
  }
}