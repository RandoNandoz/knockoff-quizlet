package sample;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Controller {

  public ListView<Card> listQuestions;
  public TextArea createQuestionField;
  public TextArea createAnswerField;
  public TextArea createTextAreaHint;
  public ImageView createImagePreview;
  public Text reviewQuestionText;
  public Text reviewTextHint;
  public ImageView reviewImageView;
  public Text textScoreFraction;
  public Text textScorePercent;

  public void createAddImageToQuestion(ActionEvent actionEvent) {
  }

  public void deleteSelectedCardInListView(ActionEvent actionEvent) {
  }

  public void createEditSelectedCard(ActionEvent actionEvent) {
  }

  public void createDeleteCurrentCard(ActionEvent actionEvent) {
  }

  public void createCardCommitChange(ActionEvent actionEvent) {
    String question = createQuestionField.getText();
    String answer = createAnswerField.getText();
    System.out.println("Question: " + question);
    System.out.println("Answer:" + answer);
    System.out.println("");
  }

  public void saveCardsToDisk(ActionEvent actionEvent) {
  }

  public void loadCardsFromDisk(ActionEvent actionEvent) {
  }

  public void reviewShuffleReviewCards(ActionEvent actionEvent) {
  }

  public void reviewDisplaySelectedCard(ActionEvent actionEvent) {
  }

  public void reviewPreviousCard(ActionEvent actionEvent) {
  }

  public void reviewNextCard(ActionEvent actionEvent) {
  }
}
