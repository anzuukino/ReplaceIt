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
            });
            return menuItems;
        }
        return null;
    }
}
