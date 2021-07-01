package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageHelper {
  // Hides Java's implicit
  private ImageHelper() {
  }

  public static boolean imageEqual(Image image1, Image image2) {
    // If neither the first ima ge nor the second are null, we can proceed with our check.
    // If either are null, we throw an error and tell the user.
    if (image1 != null && image2 != null) {
      // Get the width and height of the two images, and compare them, if they aren't equal, then they obviously are not the same image.
      // But if they are the same dimensions, we can proceed with checking the alpha and rgb values of the images.
      if (image1.getWidth() == image2.getWidth() && image1.getHeight() == image2.getHeight()) {
        // First loop through the width (x axis) of the image.
        for (int i = 0; i < image1.getWidth(); i++) {
          // Then, for each column of the image, we loop through the row.
          for (int j = 0; j < image1.getHeight(); j++) {
            // We then get the alpha, and rgb values of the pixels at each coordinate, and compare them.
            var image1Value = image1.getPixelReader().getArgb(i, j);
            var image2Value = image2.getPixelReader().getArgb(i, j);

            // If they're equal in value, then we're fine.
            if (image1Value != image2Value) {
              return false;
            }
          }
        }
        return true;
      }
    } else {
      throw new NullPointerException("Image is null!");
    }
    return false;
  }

  // This function turns a byte array of PNG into an FXImage.
  public static Image toImage(byte[] bytes) {
    // In the Card.getImageBytes function, we used a ByteArrayOutputStream.
    // We now need the reverse, a ByteArrayInputStream.
    ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

    // Throw that inputStream into our Image construction and voila, here is our image!
    return new Image(inputStream);
  }

  public static byte[] getImageBytes(Image image) throws IOException {
    // Function to get the bytes of the image.

    // So java has many different images classes, the Swing image, buffered image, and JavaFX.
    // We need to convert the FXImage to a buffered image, and then turn it into a byte array.

    // Luckily, SwingFXUtils allows us to do so. Calling fromFXImage, I can turn a FXImage to a BufferedImage.
    BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
    // Now, let's create a byte array output stream.
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    // Using ImageIO.write(), it allows us to to encode the bufferedImage into a PNG byteArrayOutputStream.
    ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
    // We get call toByteArray(), to turn the image into a byte array!
    return byteArrayOutputStream.toByteArray();

    // Magic!
  }
}
