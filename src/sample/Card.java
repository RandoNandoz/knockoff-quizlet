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
    // Avec le image.
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

  @Override
  public String toString() {
    // toString override.
    // Define a return value.
    String returnValue;
    if (image == null) {
      // If there isn't an image, return just the question, answer, and hint field.
      returnValue = String.format("Question: %s, Answer: %s, Hint: %s", getQuestion(), getAnswer(), getHint());
    } else {
      // If there **is** one, return the image's URL.
      returnValue = String.format("Question: %s, Answer: %s, Hint: %s, Image: %s", getQuestion(), getAnswer(), getHint(), getImage().getUrl());
    }
    return returnValue;
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
