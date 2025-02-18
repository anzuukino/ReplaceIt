package yuu.ext.replaceit;

import javax.swing.*;

public class ReplaceBoxTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("ReplaceBox Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);

//            ReplaceBox replaceBox = new ReplaceBox();
//            frame.add(replaceBox);
//
//            frame.setVisible(true);
            //                replaceBox.ReplaceButton().addActionListener(e1 -> {
//                    HttpRequestResponse requestResponse = event.messageEditorRequestResponse().isPresent() ? event.messageEditorRequestResponse().get().requestResponse() : event.selectedRequestResponses().get(0);
//                    String search = replaceBox.FindText();
//                    String replace = replaceBox.ReplaceText();
//                    String body = requestResponse.request().toString();
//                    List<Integer> indexes = new ArrayList<>();
//                    WordFinder wordFinder = new WordFinder();
//                    indexes = wordFinder.findWord(body, search);
//                    List<String> words = wordFinder.WordList(body, indexes, search);
//                    if (!indexes.isEmpty()) {
//
//                    } else {
//                        JOptionPane.showMessageDialog(frame, "No occurrences of " + search + " found");
//                        replaceBox.setResultText( "No occurrences of " + search + " found");
//                    }
//
//                });
//
//                replaceBox.SearchButton().addActionListener(e1 -> {
//                    HttpRequestResponse requestResponse = event.messageEditorRequestResponse().isPresent() ? event.messageEditorRequestResponse().get().requestResponse() : event.selectedRequestResponses().get(0);
//                    String search = replaceBox.FindText();
//                    String body = requestResponse.request().toString();
//                    List<Integer> indexes = new ArrayList<>();
//                    WordFinder wordFinder = new WordFinder();
//                    indexes = wordFinder.findWord(body, search);
//                    List<String> words = wordFinder.WordList(body, indexes, search);
//                    if (!indexes.isEmpty()) {
//                        JOptionPane.showMessageDialog(frame, "Found " + indexes.size() + " occurrences of " + search);
//                        StringBuilder sb = new StringBuilder();
//                        sb.append("Found " + indexes.size() + " occurrences of " + search + "\n");
//                        for (int index : indexes) {
//                            sb.append("Index: " + index + ":").append(words.get(indexes.indexOf(index))).append("\n");
//                        }
//                        replaceBox.setResultText(sb.toString());
//                    } else {
//                        JOptionPane.showMessageDialog(frame, "Found " + indexes.size() + " occurrences of " + search);
//                        replaceBox.setResultText( "No occurrences of " + search + " found");
//                    }
//
//                });
        });
    }
}
