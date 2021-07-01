package sample;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class IOHelper {
    // Write card to csv.
    public void writeCardCollectionToFile(List<Card> cardsList, File file) throws IOException {
        // Set up a writer.
        FileWriter fileWriter = new FileWriter(file);

        // Iterate through the list of cards and write the cards as csv lines.
        try (BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
            for (var card : cardsList) {
                bufferWriter.write(card.toData());
            }
        }

        // Close the writer.
    }

    public List<Card> readCardCollectionFromFile(File file) throws IOException {
        // List of cards we return.
        List<Card> cards = new ArrayList<>();

        // Writer
        FileReader fileReader = new FileReader(file);

        // The stream of strings are the lines in the bufferReader.
        try (BufferedReader bufferReader = new BufferedReader(fileReader)) {
            Stream<String> lines = bufferReader.lines();
            // Iterate through the lines and for each line in the lines, we call Card.toCard() to convert the line to a Card object.
            // Then we append to the card ArrayList.
            lines.forEach(line -> cards.add(Card.toCard(line)));
        }

        // And then we return that list of cards.
        return cards;
    }
}
