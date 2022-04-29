module project.plataforma.plataformaeducativa {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens project.plataforma.plataformaeducativa to javafx.fxml;
    exports project.plataforma.plataformaeducativa;
}