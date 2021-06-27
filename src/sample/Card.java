package sample;

import java.util.Formatter;
import java.util.Locale;

import javafx.scene.image.Image;

public class Card {
  private String question;
  private String answer;
  private String hint;
  private Image image;

  public Card(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }

  public Card(String question, String answer, String hint) {
    this.question = question;
    this.answer = answer;
    this.hint = hint;
  }

  public Card(String question, String answer, String hint, Image image) {
    this.question = question;
    this.answer = answer;
    this.hint = hint;
    this.image = image;
  }

  @Override
  public String toString() {
    return String.format("Question: %s, Answer: %s, Hint: %s, Image: %s", getQuestion(), getAnswer(), getHint(), getImage());
  }

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
