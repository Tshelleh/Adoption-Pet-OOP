module petapp.group.adoptionpet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens petapp.group.adoptionpet to javafx.fxml;
    opens petapp.group.adoptionpet.petapp.adoptionprocessing to javafx.base;
    exports petapp.group.adoptionpet;
}