package org.ics.flying_stars.ui;

import javafx.scene.Parent;

// Interface for UI classes
public interface UI {
    // Get root node that has all the children nodes. This node should be used to show the UI
    Parent getRoot();
}
