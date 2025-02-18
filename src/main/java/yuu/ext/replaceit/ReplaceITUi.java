package yuu.ext.replaceit;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.core.ToolType;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReplaceITUi implements ContextMenuItemsProvider{
    private final MontoyaApi api;
    public ReplaceITUi(MontoyaApi api) {
        this.api = api;
    }

    @Override
    public List<Component> provideMenuItems(ContextMenuEvent event) {
        if (event.isFromTool(ToolType.REPEATER)){
            List <Component> menuItems = new ArrayList<>();

            JMenuItem replaceItem = new JMenuItem("ReplaceIt");
            menuItems.add(replaceItem);
            replaceItem.addActionListener(e -> {
                JFrame frame = new JFrame("ReplaceIt");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(800, 800);
                frame.setLocationRelativeTo(null);

                ReplaceBox replaceBox = new ReplaceBox(event);
                frame.add(replaceBox);

                frame.setVisible(true);

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
            return menuItems;
        }
        return null;
    }
}
