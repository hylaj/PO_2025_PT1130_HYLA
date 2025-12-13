package agh.ics.oop.presenter;


import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;


public class SimulationPresenter implements MapChangeListener {

    private WorldMap worldMap;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField textField;

    @FXML
    private Label moveInfoLabel;

    public void setWorldMap(WorldMap map) {
        this.worldMap = map;
    }

    public void drawMap(){
        infoLabel.setText(worldMap.toString());

    }

    public void mapChanged(WorldMap worldMap, String message){
        Platform.runLater(() ->{
            drawMap();
            moveInfoLabel.setText(message);
        });
    }

    public void onSimulationStartClicked(){
        String[] args = textField.getText().split(" ");
        List<MoveDirection> directions = OptionsParser.parse(
                textField.getText().replaceAll("\\s+", "").split(""));

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap map = new RectangularMap(5,10);

        setWorldMap(map);
        map.addObserver(this);

        Simulation simulation = new Simulation(positions, directions, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        engine.runAsyncInThreadPool();
    }
}
