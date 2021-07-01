package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Controller {
    private static final String ALL_FILES = "All Files";
    private static final String SELECT_CARD = "Please select a card.";
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
    public Button commitButton;
    public TextArea reviewAnswerField;
    public Button checkAnswersButton;
    public Button displaySelectedButton;
    public Button startButton;
    private Image image;
    private Card selectedCard;

    public void deleteSelectedCardInListView() {
        // Get the index of the selected item.
        int index = listQuestions.getSelectionModel().getSelectedIndex();
        // Delete the index position.
        try {
            listQuestions.getItems().remove(index, index + 1);
        } catch (Exception e) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No card selected.");
            alert.setContentText("You have not selected a card to delete.");
            alert.showAndWait();
        }
    }

    public void createCardCommitChange() {
        // If the field for the question and the answer are less than zero in length (aka., empty), we tell their user that it's an invalid entry.
        if (createQuestionField.getText().length() <= 0 && createAnswerField.getText().length() <= 0) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty field for question and/or answer.");
            alert.setContentText("There is an empty field for either question or answer, please fill them and try again.");
            alert.showAndWait();
        } else {
            Card card;
            String question = createQuestionField.getText();
            String answer = createAnswerField.getText();
            String hint = createTextAreaHint.getText();
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
            addImageButton.setOpacity(100);
        }
    }

    public void saveCardsToDisk() throws IOException {
        // Save the cards to the disk.

        // Let the user chooser the file they want to save to.
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Save your flash cards");
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Comma Separated Values", "*.csv"), new FileChooser.ExtensionFilter(ALL_FILES, "*.*"));
        var file = fileChooser.showSaveDialog(new Stage());

        // Make a new instance of our IOHelper.
        var ioHelper = new IOHelper();

        // If the user cancels the file selection operation, file is null, so we check if its null before proceeding.
        if (file != null) {
            // Write that collection to file.
            ioHelper.writeCardCollectionToFile(listQuestions.getItems(), file);
        }
    }

    public void loadCardsFromDisk() throws IOException {
        // Get the user to pick their flash card to open.
        var fileChooser = new FileChooser();
        fileChooser.setTitle("Open your flash cards");
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Comma Separated Values", "*.csv"), new FileChooser.ExtensionFilter(ALL_FILES, "*.*"));
        var file = fileChooser.showOpenDialog(new Stage());

        // New IOHelper.
        var ioHelper = new IOHelper();
        List<Card> cards;

        // Since FileChooser can return null if the user cancels the operation, we check if null.
        if (file != null) {
            // This time, read from the selected file.
            cards = ioHelper.readCardCollectionFromFile(file);
            // Clear the list, and the iterate through the cards to populate the list with all the cards in the returned arraylist.
            listQuestions.getItems().clear();
            // For each card in cards, we add the card to listQuestions.
            cards.forEach(card -> listQuestions.getItems().add(card));
        }
    }

    public void reviewShuffleReviewCards() {
        // Shuffle cards.
        Collections.shuffle(listQuestionsReview.getItems());
    }

    public void reviewDisplaySelectedCard() {
        // Get the selected card from the list.
        selectedCard = listQuestionsReview.getSelectionModel().getSelectedItem();
        if (selectedCard != null) {
            if (!selectedCard.getDone()) {
                // Enable both the buttons.
                checkAnswersButton.setDisable(false);
                displaySelectedButton.setDisable(false);
                // Set question, and image fields.
                String selectedQuestion = selectedCard.getQuestion();
                var selectedImage = selectedCard.getImage();
                reviewQuestionText.setText("Question: " + selectedQuestion);
                reviewImageView.setImage(selectedImage);
            } else {
                // If it is done, then we must disable the check answers button, and show the questions.
                // Set question, and image fields.
                String selectedQuestion = selectedCard.getQuestion();
                var selectedImage = selectedCard.getImage();
                reviewQuestionText.setText("Question: " + selectedQuestion);
                reviewImageView.setImage(selectedImage);
                checkAnswersButton.setDisable(true);
            }
        } else {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(SELECT_CARD);
            alert.setContentText("Select a card from the list of cards that you want to display.");
            alert.showAndWait();
        }
    }

    public void reviewNextCard() {
        // If the list isn't empty:
        if (!listQuestionsReview.getItems().isEmpty()) {
            // If the selected card isn't the last card in the list of cards, then we move the selected card forward one.
            int selectedIndex = listQuestionsReview.getSelectionModel().getSelectedIndex();
            if (selectedIndex < listQuestionsReview.getItems().size() - 1) {
                // Set the selected index forward by one.
                listQuestionsReview.getSelectionModel().select(selectedIndex + 1);
                // Focus (highlight) it as well.
                listQuestionsReview.getFocusModel().focus(selectedIndex + 1);
                // If the list is long, scroll to the item selected.
                listQuestionsReview.scrollTo(selectedIndex + 1);
            }
            reviewDisplaySelectedCard();
        }
    }

    public void reviewPreviousCard() {
        // Basically, the same as reviewNextCard(), except we check whether it's last or not.
        if (!listQuestionsReview.getItems().isEmpty()) {
            int selectedIndex = listQuestionsReview.getSelectionModel().getSelectedIndex();
            if (selectedIndex > 0) {
                // Roll the selected index back by one.
                listQuestionsReview.getSelectionModel().select(selectedIndex - 1);
                // Focus that subtracted index.
                listQuestionsReview.getFocusModel().focus(selectedIndex - 1);
                // And scroll to it.
                listQuestionsReview.scrollTo(selectedIndex - 1);
            }
            reviewDisplaySelectedCard();
        }
    }

    public void selectImageFromPath() {
        // Create the FileChooser object.
        var fileChooser = new FileChooser();
        // Set the title of the FileChooser to "Select Image"
        fileChooser.setTitle("Select Image");
        // Since JavaFX only works with bitmap images, gifs, jpegs, and pngs, we allow the user to pick those files only.
        // Also allow all files, just in case if the operating system we're running on doesn't use extensions to dictate the file type.
        // A file with no extension could also be a JPG, or a PNG.
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.gif", "*.jpeg", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter(ALL_FILES, "*.*"));
        // Get the URI of the image.
        var imageFile = fileChooser.showOpenDialog(new Stage());
        if (imageFile != null) {
            var imagePath = imageFile.toURI().toString();
            // Set the global image to the path of the image.
            image = new Image(imagePath);
            // Set the preview to the image URI.
            createImagePreview.setImage(image);
            // And make the button invisible (but still clickable!)
            addImageButton.setOpacity(0);
        }
    }

    public void clearListQuestions() {
        // Clear the list.
        listQuestions.getItems().clear();
    }

    public void loadCardFromSelection() {
        // Get the selected card.
        var card = listQuestions.getSelectionModel().getSelectedItem();
        // If it isn't selected, then tell the user to select one.
        if (card == null) {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(SELECT_CARD);
            alert.setContentText("Select a card from the list of cards that you want to display.");
            alert.showAndWait();
        } else {
            // If it is selected, set the fields to the fields from the Card object above.
            createQuestionField.setText(card.getQuestion());
            createAnswerField.setText(card.getAnswer());
            createTextAreaHint.setText(card.getHint());
            createImagePreview.setImage(card.getImage());
            // Make button invisible so we can see the underlying Image.
            addImageButton.setOpacity(0);
            // Disable commit change button.
            commitButton.setDisable(true);
            // Also disable the answer field.
            createAnswerField.setDisable(true);
        }
    }

    public void syncReviewCreateSides() {
        // Sync the left and right side of the app.

        // Clear the right side list.
        listQuestionsReview.getItems().clear();
        // Iterate through the list of questions on the left side, add them to the list on the right.
        listQuestions.getItems().forEach(card -> listQuestionsReview.getItems().add(card));
        // Also, set the scoreboard's out-of-value.
        textScoreFraction.setText("0/" + listQuestionsReview.getItems().size());
        // Set the scoreboard percent as well.
        textScorePercent.setText("0%");
        // Restart the review.
        startRecordScore();
    }

    public void makeNewEmptyCard() {
        // Clear fields.
        createQuestionField.clear();
        createAnswerField.clear();
        createTextAreaHint.clear();
        createImagePreview.setImage(null);
        image = null;
        addImageButton.setOpacity(100);

        // Re-enable the button.
        commitButton.setDisable(false);
    }

    public void reviewCheckAnswers() {
        if (selectedCard != null) {
            // Get the user's answer.
            // Use regex to remove punctuation.
            String userAnswer = reviewAnswerField.getText().replaceAll("\\W", "").toLowerCase();

            // Get the actual answer from the card.
            // Use regex to remove punctuation.
            String actualAnswer = selectedCard.getAnswer().replaceAll("\\W", "").toLowerCase();

            // Update the scoreboard if it equals.
            if (userAnswer.equals(actualAnswer)) {
                // Split our fraction in half.
                String[] splitFraction = textScoreFraction.getText().split("/");

                // Get our numerator from our split fraction.
                var numerator = Integer.parseInt(splitFraction[0]);
                var denominator = Integer.parseInt(splitFraction[1]);

                // Set the fraction, increment by 1.
                textScoreFraction.setText((numerator + 1) + "/" + denominator);

                // Calculate the percent value by rounding the numerator + 1, divided by denominator,
                // then multiplied by 100. Then append a % symbol to the end.
                var scorePercent = Math.round((((float) numerator + 1) / (float) denominator) * 100);
                textScorePercent.setText(scorePercent + "%");
                // Review the next card.
            }
            // Make the card done.
            selectedCard.setDone(true);
            // Also clear the answer field.
            reviewAnswerField.setText("");

            reviewNextCard();
        }
        // Tell the user to select a card if they didn't
        else {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(SELECT_CARD);
            alert.setContentText("Select a card from the list of cards that you want to check against.");
            alert.showAndWait();
        }
    }

    public void showHintReview() {
        // Show hint.

        // If the selected card isn't null,
        if (selectedCard != null) {
            // Set the text to the selected card's hint.
            reviewTextHint.setText("Hint: " + selectedCard.getHint());
        }
        // If it is, tell the user to select a damn card!
        else {
            var alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(SELECT_CARD);
            alert.setContentText("Select a card.");
            alert.showAndWait();
        }
    }

    public void startRecordScore() {
        // Mark all cards undone.
        listQuestions.getItems().forEach(question -> question.setDone(false));
        // Go to the first card, and display it.
        selectedCard = listQuestionsReview.getItems().get(0);
        // Roll the selected index back by one.
        listQuestionsReview.getSelectionModel().select(0);
        // Focus that subtracted index.
        listQuestionsReview.getFocusModel().focus(0);
        // And scroll to it.
        listQuestionsReview.scrollTo(0);
        reviewDisplaySelectedCard();

        // Reset the score as well.
        textScoreFraction.setText("0/" + listQuestionsReview.getItems().size());
        textScorePercent.setText("0%");
    }
}
