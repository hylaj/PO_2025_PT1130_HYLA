package agh.ics.oop;

import agh.ics.oop.model.*;

import agh.ics.oop.presenter.SimulationPresenter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        String[] args = new String[] { "f", "b", "r", "l", "f", "f", "f", "f", "r", "f", "f" };
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap map = new RectangularMap(5,10);
        Simulation simulation = new Simulation(positions, directions, map);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();

        presenter.setWorldMap(map);
        map.addObserver(presenter);
        simulation.run();

        configureStage(primaryStage, viewRoot);
        primaryStage.show();

    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        // stworzenie sceny (panelu do wyświetlania wraz zawartością z FXML)
        var scene = new Scene(viewRoot);

        // ustawienie sceny w oknie
        primaryStage.setScene(scene);

        // konfiguracja okna
        primaryStage.setTitle("Simulation app");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
