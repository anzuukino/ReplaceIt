package yuu.ext.replaceit;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.ToolType;
import burp.api.montoya.ui.contextmenu.MessageEditorHttpRequestResponse;

public class ReplaceBox extends JPanel {
    private final JTextField searchField;
    private final JTextField replaceField;
    private final JButton searchButton;
    private final JButton replaceButton;
    private final JButton replaceAllButton;
    private final JButton nextButton;
    private final JButton prevButton;
    private final JButton confirm;
    private final JTextArea resultArea;
    private List<Integer> searchIndexes = new ArrayList<>();
    private int currentIndex = -1;
    private String searchString = "";
    private ContextMenuEvent event;
    public ReplaceBox(ContextMenuEvent  event) {
        this.event = event;
        setLayout(new BorderLayout(10, 10));

        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel searchLabel = new JLabel("Find:");
        searchField = new JTextField(20);

        JLabel replaceLabel = new JLabel("Replace with:");
        replaceField = new JTextField(20);

        inputPanel.add(searchLabel);
        inputPanel.add(searchField);
        inputPanel.add(replaceLabel);
        inputPanel.add(replaceField);



        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        searchButton = new JButton("Search");
        replaceButton = new JButton("Replace");
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        replaceAllButton = new JButton("Replace All");
        confirm = new JButton("Confirm");

        buttonPanel.add(searchButton);
        buttonPanel.add(replaceButton);
        buttonPanel.add(replaceAllButton);
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(confirm);

        // Result Panel
        JPanel resultPanel = new JPanel(new BorderLayout());
        JLabel resultLabel = new JLabel("Result:");
        resultArea = new JTextArea(3, 30);
        resultArea.setEditable(true);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        resultPanel.add(resultLabel, BorderLayout.NORTH);
        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        // Add panels to main layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, buttonPanel, resultPanel);
        splitPane.setResizeWeight(0.1); // Keeps buttons small, gives more space to result panel
        splitPane.setContinuousLayout(true); // Smooth resizing
        splitPane.setOneTouchExpandable(true); // Adds a button to collapse/expand

        // Add to main layout
        add(inputPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        searchButton.addActionListener(e -> search(true,""));
        nextButton.addActionListener(e -> nextSearch());
        prevButton.addActionListener(e -> prevSearch());
        replaceButton.addActionListener(e -> replaceOnce());
        replaceAllButton.addActionListener(e -> replaceAll());
        confirm.addActionListener(e -> confirmData());


    }

    private void confirmData(){
        String AreaText = resultArea.getText();
        if (event.messageEditorRequestResponse().isPresent()){
            MessageEditorHttpRequestResponse messageEditorHttpRequestResponse = event.messageEditorRequestResponse().get();

            int caretPosition = messageEditorHttpRequestResponse.caretPosition();

            HttpRequest originalRequest = messageEditorHttpRequestResponse.requestResponse().request();

            ByteArray newByteArray = ByteArray.byteArray(AreaText.getBytes());

            messageEditorHttpRequestResponse.setRequest(HttpRequest.httpRequest(originalRequest.httpService(), newByteArray));
            JOptionPane.showMessageDialog(this, "Request has been updated");
        }else{
            JOptionPane.showMessageDialog(this, "No request found");
        }
    }

    private void replaceAll(){
        String AreaText = resultArea.getText();
        String replaceText = replaceField.getText();
        String searchText = searchField.getText();
        String newAreaText = AreaText.replaceAll(searchText, replaceText);
        resultArea.setText(newAreaText);
        search(false, newAreaText);
        Highlighter highlighter = resultArea.getHighlighter();
        highlighter.removeAllHighlights();
    }

    private void replaceOnce(){
        String AreaText = resultArea.getText();
        String replaceText = replaceField.getText();
        String searchText = searchField.getText();
        String newAreaText = replaceNthOccurrence(AreaText, searchText, replaceText, currentIndex);
        resultArea.setText(newAreaText);
        search(false, newAreaText);
        Highlighter highlighter = resultArea.getHighlighter();
        highlighter.removeAllHighlights();
    }

    private String replaceNthOccurrence(String original, String find, String replace, int n) {
        int index = original.indexOf(find);
        if (index >= 0) {
            return original.substring(0, index) + replace + original.substring(index + find.length());
        }
        JOptionPane.showMessageDialog(this, "No occurrences of \"" + find + "\" found");
        return original;
    }

    private void nextSearch(){
        if (currentIndex >= 0 && currentIndex < searchIndexes.size() - 1) {
            currentIndex++;
            highLight();
        }else{
            currentIndex = 0;
            highLight();
        }
    }

    private void prevSearch(){
        if (currentIndex >= 1 && currentIndex < searchIndexes.size()) {
            currentIndex--;
            highLight();
        }else{
            currentIndex = searchIndexes.size() - 1;
            highLight();
        }
    }

    private void search(boolean isDirectlyCall, String textToBeSearch) {
        Highlighter highlighter = resultArea.getHighlighter();
        highlighter.removeAllHighlights();
        searchIndexes.clear();
        currentIndex = -1;

        searchString = searchField.getText();

        if (searchString.isEmpty()) {
            return;
        }
        String tosearchString = searchString.toLowerCase();
        String originalRequest = "";
        if (isDirectlyCall) {
            HttpRequestResponse requestResponse = event.messageEditorRequestResponse().isPresent() ? event.messageEditorRequestResponse().get().requestResponse() : event.selectedRequestResponses().get(0);
            originalRequest = requestResponse.request().toString();
        }else{
            originalRequest = textToBeSearch;
        }
        String text = originalRequest.toLowerCase();
        int index = text.indexOf(tosearchString);
        while (index >= 0) {
            searchIndexes.add(index);
            index = text.indexOf(tosearchString, index + 1);
        }

        if (!searchIndexes.isEmpty()) {
            currentIndex = 0;
            setResultText(originalRequest);
            highLight();
            if (isDirectlyCall) {
                JOptionPane.showMessageDialog(this, "Found " + searchIndexes.size() + " occurrences of " + "\"" + searchString + "\"");
            }

        }else{
            if (isDirectlyCall) {
                JOptionPane.showMessageDialog(this, "No occurrences of \"" + searchString + "\" found");
            }
        }

    }

    private void highLight(){
        if (currentIndex >= 0 && currentIndex < searchIndexes.size()) {
            Highlighter highlighter = resultArea.getHighlighter();
            highlighter.removeAllHighlights();
            try{
                int start = searchIndexes.get(currentIndex);
                int end = start + searchString.length();
                highlighter.addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void setResultText(String text) {
        resultArea.setText(text);
    }

    public String FindText() {
        return searchField.getText();
    }

    public String ReplaceText() {
        return replaceField.getText();
    }

    public JButton SearchButton() {
        return searchButton;
    }

    public JButton NextButton() {
        return nextButton;
    }

    public JButton PrevButton() {
        return prevButton;
    }

    public JButton ReplaceButton() {
        return replaceButton;
    }
}
