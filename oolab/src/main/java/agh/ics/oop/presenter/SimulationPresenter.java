package agh.ics.oop.presenter;


import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.Boundary;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;


public class SimulationPresenter implements MapChangeListener {

    private WorldMap worldMap;

    @FXML
    private Label infoLabel;

    @FXML
    private TextField textField;

    @FXML
    private Label moveInfoLabel;

    @FXML
    private Canvas mapCanvas;

    private static final int CELL_WIDTH = 40;
    private static final int CELL_HEIGHT = 40;
    private static final int BORDER_WIDTH = 2;
    private static final int BORDER_OFFSET = BORDER_WIDTH/2;


    public void setWorldMap(WorldMap map) {
        this.worldMap = map;
    }

    public void drawMap(){
        clearGrid();

        Boundary boundary = worldMap.getCurrentBounds();

        int canvasWidth = boundary.upperRightMapCorner().getX() - boundary.lowerLeftMapCorner().getX() +1;
        int canvasHeight = boundary.upperRightMapCorner().getY() - boundary.lowerLeftMapCorner().getY() +1;

        mapCanvas.setWidth((canvasWidth+1) * CELL_WIDTH +BORDER_WIDTH);
        mapCanvas.setHeight((canvasHeight+1) * CELL_HEIGHT +BORDER_WIDTH);

        GraphicsContext graphics = mapCanvas.getGraphicsContext2D();

        graphics.setStroke(Color.BLACK);
        graphics.setLineWidth(BORDER_WIDTH);


        for (int x = 0; x < mapCanvas.getWidth() + 1; x += CELL_WIDTH) {
            graphics.strokeLine(x + BORDER_OFFSET , 0, x + BORDER_OFFSET  , mapCanvas.getHeight());
        }

        for (int y = 0; y < mapCanvas.getHeight() + 1; y += CELL_HEIGHT) {
            graphics.strokeLine(0,y + BORDER_OFFSET, mapCanvas.getWidth(),y + BORDER_OFFSET);
        }

        configureFont(graphics, 20, Color.BLACK);

        graphics.fillText("y/x", CELL_WIDTH / 2.0, CELL_HEIGHT / 2.0);
        for (int i = 0; i < canvasWidth; i++) {
            int xValue = boundary.lowerLeftMapCorner().getX() + i;
            double drawX = (i + 1) * CELL_WIDTH + (CELL_WIDTH / 2.0);
            double drawY = CELL_HEIGHT / 2.0;
            graphics.fillText(Integer.toString(xValue), drawX, drawY);
        }

        for (int i = 0; i < canvasHeight; i++) {
            int yValue = boundary.upperRightMapCorner().getY() - i;
            double drawX = CELL_WIDTH / 2.0;
            double drawY = (i + 1) * CELL_HEIGHT + (CELL_HEIGHT / 2.0);
            graphics.fillText(Integer.toString(yValue), drawX, drawY);
        }

        configureFont(graphics, 30, Color.RED);

        for (WorldElement element : worldMap.getElements()) {
            Vector2d position = element.getCurrentPosition();

            int currentX = position.getX() - boundary.lowerLeftMapCorner().getX();
            int currentY = boundary.upperRightMapCorner().getY() - position.getY();

            double drawX = (currentX+1) * CELL_WIDTH + ( CELL_WIDTH / 2.0);
            double drawY = (currentY+1) * CELL_HEIGHT + ( CELL_HEIGHT / 2.0);

            graphics.fillText(element.toString(), drawX, drawY);

        }

    }

    public void mapChanged(WorldMap worldMap, String message){
        Platform.runLater(() ->{
            drawMap();
            moveInfoLabel.setText(message);
        });
    }

    private void clearGrid() {
        GraphicsContext graphics = mapCanvas.getGraphicsContext2D();
        graphics.setFill(Color.WHITE);
        graphics.fillRect(0, 0, mapCanvas.getWidth(), mapCanvas.getHeight());
    }

    private void configureFont(GraphicsContext graphics, int size, Color black) {
        graphics.setTextAlign(TextAlignment.CENTER);
        graphics.setTextBaseline(VPos.CENTER);
        graphics.setFont(new Font("Arial", size));
        graphics.setFill(black);
    }

    public void onSimulationStartClicked(){
        String[] args = textField.getText().split(" ");
        List<MoveDirection> directions = OptionsParser.parse(
                textField.getText().replaceAll("\\s+", "").split(""));

        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        AbstractWorldMap map = new GrassField(5);

        setWorldMap(map);
        map.addObserver(this);

        Simulation simulation = new Simulation(positions, directions, map);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));
        engine.runAsyncInThreadPool();
    }
}
