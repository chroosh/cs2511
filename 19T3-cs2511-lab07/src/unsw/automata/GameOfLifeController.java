package unsw.automata;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;


/**
 * A JavaFX controller for the Conway's Game of Live Application.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLifeController {

  @FXML
  private GridPane board;


  @FXML
  private Button playButton;
  
  private GameOfLife gol;
  private Timeline timeline;
  // private KeyFrame keyframe;
  
  public GameOfLifeController() {
    gol = new GameOfLife();
    timeline = new Timeline();
  }
  
  EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent m) {
      gol.tick();
    }
  };
  

  @FXML
  void handlePlay(ActionEvent event) {
    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        playButton.setText("Stop");
        gol.tick();
        playButton.setOnAction(m -> timeline.stop());
      }
    }));
    timeline.play();
  }

  @FXML
  void handleTick(ActionEvent event) {
    gol.tick();
  }

  
  @FXML
  public void initialize() {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        CheckBox newCheckBox = new CheckBox();
        board.add(newCheckBox, i, j);
        newCheckBox.selectedProperty().bindBidirectional(gol.cellProperty(i, j));
      }
    }
  }
  
  
}
