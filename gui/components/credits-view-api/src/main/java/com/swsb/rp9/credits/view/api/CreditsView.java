package com.swsb.rp9.credits.view.api;

import javafx.scene.Parent;

public interface CreditsView {
    String getStyleSheetLocation();

    Parent createGuiRootNode();

    String getTitle();

    void redraw();

    int awesomeness();
}
