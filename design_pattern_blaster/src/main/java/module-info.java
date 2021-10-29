module design_pattern_blaster{
    requires transitive javafx.controls;
    requires javafx.fxml;



    requires javafx.graphics;

    requires jdk.compiler;

    requires javafx.base;
    requires java.xml;
    requires javafx.media;
    requires json.simple;
    //requires junit;

    opens design_pattern_blaster to javafx.fxml;
    exports design_pattern_blaster;

    exports design_pattern_blaster.view;
    opens design_pattern_blaster.view to javafx.fxml;

}