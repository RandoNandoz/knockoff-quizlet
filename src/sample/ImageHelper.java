package sample;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageHelper {
  public static boolean imageEqual(Image image1, Image image2) {
    boolean returnValue = false;
    // If either value is null then they're not equal
    if (image1 != null || image2 != null) {
      if (image1.getWidth() == image2.getWidth() && image1.getHeight() == image2.getHeight()) {
        for (int i = 0; i < image1.getWidth(); i++) {
          for (int j = 0; j < image1.getHeight(); j++) {
            var image1Value = image1.getPixelReader().getArgb(i, j);
            var image2Value = image2.getPixelReader().getArgb(i, j);

            if (image1Value == image2Value) {
              returnValue = true;
            } else {
              returnValue = false;
              break;
            }
          }
        }
      } else {
        returnValue = false;
      }
    } else {
      throw new NullPointerException("Image is null!");
    }
    return returnValue;
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
    // Using ImageIO.write(), it allows us to to encode the bufferedImage into a PNG bytestream.
    ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
    // We get call toByteArray(), to turn the image into a byte array!
    return byteArrayOutputStream.toByteArray();

    // Magic!
  }
}
