module org.ics.flying_stars {
    requires javafx.controls;
    requires javafx.base;
    requires java.desktop;

    opens org.ics.flying_stars.tests;
    opens org.ics.flying_stars;
}