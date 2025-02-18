package yuu.ext.replaceit;
import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.Http;


public class ReplaceIT implements BurpExtension {
    public boolean HTTP_LOG = false;
    @Override
    public void initialize(MontoyaApi api)
    {
        api.extension().setName("ReplaceIt");

        api.logging().logToOutput("ReplaceIt extension loaded (From Yuu with love)");

        if (HTTP_LOG) {
            api.logging().logToOutput("HTTP logging enabled");
            Http http = api.http();
            http.registerHttpHandler(new RequestHandler(api));
        }

        api.userInterface().registerContextMenuItemsProvider(new ReplaceITUi(api));

    }
}