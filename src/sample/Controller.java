package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
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

  public void deleteSelectedCardInListView(ActionEvent actionEvent) {
    // Get the index of the selected item.
    int index = listQuestions.getSelectionModel().getSelectedIndex();
    // Delete the index position.
    listQuestions.getItems().remove(index, index + 1);
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
      image = null;
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
    // Create the FileChooser object.
    FileChooser fileChooser = new FileChooser();
    // Set the title of the FileChooser to "Select Image"
    fileChooser.setTitle("Select Image");
    // Since JavaFX only works with bitmap images, gifs, jpegs, and pngs, we allow the user to pick those files only.
    // Also allow all files, just in case if the operating system we're running on doesn't use extensions to dictate the file type.
    // A file with no extension could also be a JPG, or a PNG.
    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.gif", "*.jpeg", "*.jpg", "*.png"),
        new FileChooser.ExtensionFilter("All Files", "*.*"));
    // Get the URI of the image.
    String imagePath = fileChooser.showOpenDialog(new Stage()).toURI().toString();
    // Set the global image to the path of the image.
    image = new Image(imagePath);
    // Set the preview to the image URI.
    createImagePreview.setImage(image);
    // And make the button invisible (but still clickable!)
    addImageButton.setOpacity(0);
  }

  public void clearListQuestions(MouseEvent mouseEvent) {
    // Clear the list.
    listQuestions.getItems().clear();
  }

  public void loadCardFromSelection(MouseEvent mouseEvent) {
    // Get the selected card.
    Card card = listQuestions.getSelectionModel().getSelectedItem();
    // If it isn't selected, then tell the user to select one.
    if (card == null) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Please select a card.");
      alert.setContentText("Select a card from the list of cards that you want to display.");
      alert.showAndWait();
    } else {
      // If it is selected, set the fields to the fields from the Card object above.
      createQuestionField.setText(card.getQuestion());
      createAnswerField.setText(card.getAnswer());
      createTextAreaHint.setText(card.getHint());
      createImagePreview.setImage(card.getImage());
    }
  }

  public void clearFields(ActionEvent actionEvent) {
  }
}
