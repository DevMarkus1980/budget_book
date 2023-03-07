package de.struma.budget_book.Service;

import java.io.IOException;

public class RuntimeService {
    String _url , _os;
    Runtime _rt ;

    public RuntimeService(){}
    public RuntimeService(String url){
        _url = url;
        _os = System.getProperty("os.name").toLowerCase();
        _rt = Runtime.getRuntime();
    }

    public void runBrowser() throws IOException {

        if (_os.contains("win")) {
            _rt.exec("rundll32 url.dll,FileProtocolHandler " + _url);
        } else if (_os.contains("mac")) {
            _rt.exec("open " + _url);
        } else if (_os.contains("nix") || _os.contains("nux")) {
            _rt.exec("xdg-open " + _url);
        }
    }
}
