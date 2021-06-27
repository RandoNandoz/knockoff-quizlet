package sample;

import java.io.File;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controller {

  // Yes I know this is a superclass with a ton of methods
  // that really should be in other classes but dont @ me.
  public ListView<Card> listQuestions;
  public TextArea createQuestionField;
  public TextArea createAnswerField;
  public TextArea createTextAreaHint;
  public Text reviewQuestionText;
  public Text reviewTextHint;
  public ImageView reviewImageView;
  public Text textScoreFraction;
  public Text textScorePercent;
  public ListView<Card> listQuestionsReview;
  public ImageView createImagePreview;
  public Button addImageButton;
  private Image image;

  public void createAddImageToQuestion(ActionEvent actionEvent) {
  }

  public void deleteSelectedCardInListView(ActionEvent actionEvent) {
  }

  public void createEditSelectedCard(ActionEvent actionEvent) {
  }

  public void createDeleteCurrentCard(ActionEvent actionEvent) {
  }

  public void createCardCommitChange(ActionEvent actionEvent) {
    // If the field for the question and the answer are less than zero in length (aka., empty), we tell their user that it's an invalid entry.
    if (createQuestionField.getText().length() <= 0 && createAnswerField.getText().length() <= 0) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Empty field for question and/or answer.");
      alert.setContentText("There is an empty field for either question or answer, please fill them and try again.");
      alert.showAndWait();
    } else {
      Card card;
      var question = createQuestionField.getText();
      var answer = createAnswerField.getText();
      var hint = createTextAreaHint.getText();
      // Now that we know that there are at least the two core fields, question and answer, let us proceed with creating our Card.

      // So, if there is no image selected, then it must be null. Create a card without an image.
      if (image == null) {
        card = new Card(question, answer, hint);
      }
      // If image isn't null, we assume it has *something*; so create a card with an image
      else {
          card = new Card(question, answer, hint, image);
      }

      // Finally, add the card to the list of questions.
      listQuestions.getItems().add(card);

      // Also clear the fields.
      createQuestionField.clear();
      createAnswerField.clear();
      createTextAreaHint.clear();
      createImagePreview.setImage(null);
    }
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

  public void selectImageFromPath(MouseEvent mouseEvent) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Select Image");
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.gif", "*.jpeg", "*.jpg", "*.png"),
        new FileChooser.ExtensionFilter("All Files", "*.*"));
    String imagePath = fileChooser.showOpenDialog(new Stage()).toURI().toString();
    image = new Image(imagePath);
    createImagePreview.setImage(image);
    addImageButton.setVisible(false);
  }
}
