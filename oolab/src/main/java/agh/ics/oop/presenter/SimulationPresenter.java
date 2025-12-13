package agh.ics.oop.presenter;


import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.WorldMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class SimulationPresenter implements MapChangeListener {

    private WorldMap worldMap;

    @FXML
    private Label infoLabel;

    public void setWorldMap(WorldMap map) {
        this.worldMap = map;
    }

    public void drawMap(){
        infoLabel.setText(worldMap.toString());

    }

    public void mapChanged(WorldMap worldMap, String message){
        drawMap();
    }
}
