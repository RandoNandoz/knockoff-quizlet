package sample;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;

import javafx.scene.image.Image;

public class Card {
  private String question;
  private String answer;
  private String hint;
  private Image image;

  // Constructors: One for with image and without.
  public Card(String question, String answer, String hint) {
    // This is the one without the image.
    this.question = question;
    this.answer = answer;
    this.hint = hint;
  }

  public Card(String question, String answer, String hint, Image image) {
    // With the image.
    this.question = question;
    this.answer = answer;
    this.hint = hint;
    this.image = image;
  }


  // To the data format that we're going to export as a CSV (yes, i know, storing images as csv...)
  public String toData() throws IOException {
    // This function returns a CSV line for our class.

    // The return value defined.
    String returnValue;
    // If there's no image, just return the fields of question, answer & hint, and a blank for the image.
    if (image == null) {
      returnValue = String.format("%s,%s,%s,", getQuestion(), getAnswer(), getHint());
    } else {
      // If there is,, do the same as above, but the String representation of the bytes.
      returnValue = String.format("%s,%s,%s,%s", getQuestion(), getAnswer(), getHint(), DataHelper.normalizeString(Arrays.toString(ImageHelper.getImageBytes(image))));
    }
    // Return
    return returnValue;
  }

  public static Card toCard(String data) {
    // Turns a line of csv into a Card.

    // Our columns are found by splitting the row into columns.
    var columns = data.split(",");

    // Our fields, since we know 1st column is question, 2nd answer, 3rd hint.
    String question = columns[0];
    String answer = columns[1];
    String hint = columns[2];

    // If our columns are more than three, then there must be an image field.
    if (columns.length > 3) {
      // Get the image column.
      String imageRaw = columns[3];
      // Decode the string to bytes, then into an image.
      Image image = ImageHelper.toImage(ByteHelper.toBytesFromString(imageRaw));
      // Return the card with the image.
      return new Card(question, answer, hint, image);
    } else {
      // Since the column length is less than three, therefore there mustn't be an image.
      return new Card(question, answer, hint);
    }
  }

  @Override
  public String toString() {
    // Return fstring with question, answer, and hint.
    return String.format("Question: %s, Answer: %s, Hint: %s", getQuestion(), getAnswer(), getHint());
  }


  // Default getters & setters.
  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public String getHint() {
    return hint;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
