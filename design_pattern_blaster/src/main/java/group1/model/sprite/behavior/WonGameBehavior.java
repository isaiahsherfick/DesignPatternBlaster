package group1.model.sprite.behavior;

import group1.App;
import group1.constants.Constants;
import group1.model.level.Level;
import group1.model.sprite.Sprite;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class WonGameBehavior implements Behavior {


    public WonGameBehavior() {

    }

    @Override
    public void performBehavior(Sprite sprite) {

        Stage mainStage = App.model.getMainStage();
        if (mainStage != null) {
            displayScoreScreen(mainStage);


        }


    }

    private void displayScoreScreen(Stage mainStage) {
        App.model.clearSprites();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(mainStage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.setPadding(new Insets(30,30,30,30));
        dialogVbox.setAlignment(Pos.CENTER);

        Label label = new Label("You beat the game!! These were your scores!");
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.getChildren().add(label);
        List<String> stageNames = List.of("Observer","Strategy","Command","Composite","Singleton","Factory");
        int stageNamesIndex = 0;
        for(Level l: App.model.getLevelManager().getCompletedLevelsList()){
            Label score = new Label(stageNames.get(stageNamesIndex) + ": " + l.getLevelScore());
            score.setAlignment(Pos.CENTER);
            score.setTextAlignment(TextAlignment.CENTER);
            dialogVbox.getChildren().add(score);
            stageNamesIndex++;

        }

        Label testKnowledge = new Label("Now take the quiz to proof your mastery!");
        testKnowledge.setAlignment(Pos.CENTER);
        testKnowledge.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.getChildren().add(testKnowledge);

        Button closeButton = new Button("Take Quiz");
        closeButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");

        closeButton.setOnAction(e->{
            dialog.close();
            displayQuiz(mainStage);
        });
        dialogVbox.getChildren().add(closeButton);
        Scene dialogScene = new Scene(dialogVbox);
        dialog.setScene(dialogScene);

        dialog.show();
    }

    private void displayQuiz(Stage mainStage) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(mainStage);

        ScrollPane quiz = new ScrollPane();
        VBox questions = new VBox();


        VBox question1 = newQuestion("In the Observer level, what was the observable?",
                List.of("eyeball enemy","moving platform","red button","player","blue button"),
                3);
        VBox question2 = newQuestion("Based on your experience in the strategy level, can an object generally contain two strategy objects of the same type?",
                List.of("Yes","No"),1);
        VBox question3 = newQuestion("What is the essence of the Command Pattern?",
                List.of("To establish a hierarchy between objects","Encapsulate requests as objects, allowing storage, parameterization, undo/redo, and prioritization",
                        "To create an enumeration of established commands in a system so as to prevent an explosion of classes by restricting the public interfaces of the main entry points",
                        "Encapsulate requests as objects so that you can reuse similar requests, thus saving memory"),1);
        VBox question4 = newQuestion("Which of the following is a real world analog to the Composite Pattern?",
                List.of("Sin Waves","Jigsaw Puzzle","Grocery bag inside of a grocery bag","A glass of water"),
                2);
        VBox question5 = newQuestion("On the factory level, why are you only able to spawn one observer platform?",
                List.of("Because part of the motivation for Factory Patterns is to only fulfill a construction request when necessary",
                        "Because the factory workers are attached to the first instance of the object and will not be freed until it is",
                        "Because Factories only ever spawn a single instance of a given class, with no exceptions",
                        "Because the construction request can only be allocated a set amount of memory"),
                0);
        VBox question6 = newQuestion("Given your task in the MVC Level, what is the Controller's job? (You were the controller)",
                List.of("Avoid interpreting the view's requests to lower stress on model",
                        "To control the view in accordance to the predictions of the model",
                        "Prevent the user from viewing data in the model",
                        "Serve as a bridge between the view and the model, filtering out invalid requests and performing business logic"),
                3);
        questions.getChildren().addAll(question1,question2,question3,question4, question5,question6);
        questions.setMaxWidth(500);
        quiz.setContent(questions);
        quiz.setPannable(false);
        Scene dialogScene = new Scene(quiz,500,500);
        dialog.setScene(dialogScene);
        dialog.setTitle("Quiz");
        dialog.show();

    }

    private VBox newQuestion(String question, List<String> answers, int correctAnswerIndex){

        Label labelfirst= new Label(question);
        labelfirst.setFont(new Font("Cambria", 24));
        labelfirst.setWrapText(true);


        Label labelresponse= new Label();
        labelresponse.setFont(new Font("Cambria", 18));

        VBox layout= new VBox(5);
        layout.setPadding(new Insets(20,20,20,20));

        Button checkAnswer= new Button("Check Answer");
        checkAnswer.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        checkAnswer.setFocusTraversable(false);
        checkAnswer.setFont(new Font("Cambria", 18));
        layout.getChildren().add(labelfirst);

        ToggleGroup tg = new ToggleGroup();


        checkAnswer.setDisable(true);
        for (int i =0; i<answers.size(); i++){
            RadioButton rb = new RadioButton(answers.get(i));

            rb.setOnAction(e->checkAnswer.setDisable(false));
            rb.setFont(new Font("Cambria", 18));
            layout.getChildren().add(rb);
            rb.setWrapText(true);
            rb.setToggleGroup(tg);

            if( i == correctAnswerIndex) {

                checkAnswer.setOnAction(e ->
                        {
                            if (rb.isSelected()) {
                                labelresponse.setText("Correct!");
                                labelresponse.setTextFill(Color.GREEN);
                                checkAnswer.setDisable(true);
                            } else {
                                labelresponse.setText("Incorrect. Try again!");
                                labelresponse.setTextFill(Color.RED);
                                checkAnswer.setDisable(true);
                            }
                        }
                );
            }
        }

        layout.getChildren().addAll(checkAnswer,labelresponse);
        layout.setSpacing(15);

        return layout;
    }
    private VBox newQuestion(String question, List<String> answers, int correctAnswerIndex, String imagePath)
    {
        Image image = new Image(Paths.get(imagePath).toUri().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        Label labelfirst= new Label(question, imageView);
        labelfirst.setFont(new Font("Cambria", 24));
        labelfirst.setWrapText(true);
        labelfirst.setGraphicTextGap(7);


        Label labelresponse= new Label();
        labelresponse.setFont(new Font("Cambria", 18));


        VBox layout= new VBox(5);
        layout.setPadding(new Insets(30,20,30,20));

        Button checkAnswer= new Button("Check Answer");
        checkAnswer.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
        checkAnswer.setFont(new Font("Cambria", 18));
        checkAnswer.setFocusTraversable(false);

        layout.getChildren().add(labelfirst);


        ToggleGroup toggleGroup = new ToggleGroup();

        checkAnswer.setDisable(true);

        for (int i =0; i<answers.size(); i++){
            RadioButton rb = new RadioButton(answers.get(i));
            rb.setToggleGroup(toggleGroup);


            rb.setOnAction(e->checkAnswer.setDisable(false));
            rb.setFont(new Font("Cambria", 18));
            rb.setWrapText(true);
            layout.getChildren().add(rb);

            if( i == correctAnswerIndex) {
                checkAnswer.setOnAction(e ->
                        {
                            if (rb.isSelected()) {
                                labelresponse.setText("Correct!");
                                labelresponse.setTextFill(Color.GREEN);
                                checkAnswer.setDisable(true);
                            } else {
                                labelresponse.setText("Incorrect. Try again!");
                                labelresponse.setTextFill(Color.RED);
                                checkAnswer.setDisable(true);
                            }
                        }
                );
            }
        }

        layout.getChildren().addAll(checkAnswer,labelresponse);
        layout.setSpacing(15);

        return layout;
    }

    public Behavior copy() {
        return new WonGameBehavior();
    }

}
