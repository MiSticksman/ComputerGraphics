package com.example.demo;

import javafx.animation.*;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Random;

public class Main extends Application {
    private final Random RND = new Random();
    private  double  WIDTH = 900;
    private  double HEIGHT = 900;
    private final int TIMER_DELAY = 40;
    private final double treeW = WIDTH / 25;
    private final double treeH = HEIGHT / 10;
    private final Point[] treesArr =  {new Point((int) (WIDTH / 9), (int) (HEIGHT / 2.8)),
            new Point((int) ( WIDTH / 2.5), (int) (HEIGHT / 2.85)),
            new Point((int) (WIDTH / 4), (int) (HEIGHT / 2.2)),
            new Point((int) (WIDTH / 1.5), (int) (HEIGHT / 2.7)),
            new Point((int) (WIDTH / 1.1), (int) (HEIGHT / 2.2))};

    private  final Point[] arrayDrops = new Point[RND.nextInt(30) + 250];
    private int ticks = 0;
    private int angle = 0;
    private double[] marks = new double[] {WIDTH / 180, WIDTH / 7.2, WIDTH / 3.673, WIDTH /2.466, WIDTH / 1.86,
            WIDTH / 1.488, WIDTH / 1.241, WIDTH / 1.065};

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);
        //primaryStage.setResizable(false);
        for (int i = 0; i < arrayDrops.length; i++) {
            arrayDrops[i] = new Point(RND.nextInt((int) WIDTH),RND.nextInt((int) HEIGHT));
        }

        Timeline timeline = new Timeline (
                new KeyFrame(
                        Duration.millis(TIMER_DELAY),
                        ae -> {
                            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                            drawShapes(gc);
                            ticks +=2;
                            angle -= 10;
                            for (int i = 0; i < treesArr.length; i++) {
                                if (treesArr[i].x + 129 < 0) {
                                    treesArr[i].x = (int) (WIDTH +  10);
                                }
                                treesArr[i].x -= 5;
                            }
                            for (int i = 0; i < arrayDrops.length; i++) {
                                arrayDrops[i].x --;
                                if (arrayDrops[i].x == 1) {
                                    arrayDrops[i].x = (int) WIDTH;
                                }
                                arrayDrops[i].y += 10;
                                arrayDrops[i].y %= HEIGHT;
                                drop(gc, arrayDrops[i].x, arrayDrops[i].y);
                            }

                            for (int i = 0; i < marks.length; i++) {
                                marks[i] -= 10;
                                if (marks[i] + 80 < 0) {
                                    marks[i] = WIDTH - 10;
                                }
                            }
                        }
                )
        );

        primaryStage.setMaxWidth(1.15 * WIDTH);
        primaryStage.setMaxHeight(1.05 * HEIGHT);

        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
            WIDTH = (double) newVal;
            canvas.setWidth((Double) newVal);
            //primaryStage.minHeightProperty().bind(primaryStage.widthProperty().multiply(0.5));
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
            HEIGHT = (double) newVal;
            canvas.setHeight((Double) newVal);
        });

        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));

        primaryStage.show();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void drawShapes (GraphicsContext gc) {

        gc.setFill(Color.CADETBLUE);
        gc.fillRect(0, 0,  WIDTH, HEIGHT / 2.7);//небо
        gc.setFill(Color.SADDLEBROWN);
        gc.fillRect(0, HEIGHT / 2.7, WIDTH, HEIGHT / 1.5);//земля
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, HEIGHT / 1.7, WIDTH, HEIGHT / 3);//дорога

        markup(gc);//разметка


        tree(gc, treeW, treeH );//деревья

        gc.setFill(Color.BLACK);
        gc.fillRect(WIDTH / 9, HEIGHT / 1.5, WIDTH / 1.45, HEIGHT / 15);
        gc.fillRoundRect(WIDTH / 10, HEIGHT / 1.37, WIDTH / 1.4, HEIGHT / 19,30.0, 20.0);
        gc.fillRect(WIDTH / 2.5, HEIGHT / 1.75, WIDTH / 40, HEIGHT / 10.5);
        gc.setFill(Color.YELLOW);
        gc.fillRect(WIDTH / 1.33, HEIGHT / 1.45, WIDTH / 25, HEIGHT / 60);

        gc.setFill(Color.WHITE);
        gc.fillOval(WIDTH / 5.45, HEIGHT / 1.44, WIDTH / 6, WIDTH / 6.5); //обводка колеса
        gc.fillOval(WIDTH / 1.79, HEIGHT / 1.44, WIDTH / 6, WIDTH / 6.5);
        wheel(gc,WIDTH / 5.1, HEIGHT / 1.42, WIDTH / 7); //колесо
        wheel(gc,WIDTH / 1.75, HEIGHT / 1.42, WIDTH / 7);

        gc.setFill(Color.LIGHTBLUE);
        gc.fillPolygon(new double[]{WIDTH / 4.0, WIDTH / 2.5, WIDTH / 2.5},
                new double[]{HEIGHT / 1.599, HEIGHT / 1.599, HEIGHT / 1.75}, 3);
        gc.fillRect(WIDTH / 4.0, HEIGHT / 1.6, WIDTH / 6.65, HEIGHT / 24);
        gc.fillPolygon(new double[]{WIDTH / 5, WIDTH / 4, WIDTH / 4},
                new double[]{HEIGHT / 1.5, HEIGHT / 1.6, HEIGHT / 1.5}, 3);
        gc.setFill(Color.LIGHTBLUE);
        gc.fillPolygon(new double[]{WIDTH / 2.35, WIDTH / 2.35, WIDTH / 1.4},
                new double[]{HEIGHT / 1.75, HEIGHT / 1.5, HEIGHT / 1.5}, 3);
        gc.setFill(Color.DARKGRAY);
        gc.setStroke(Color.SADDLEBROWN);
        gc.fillPolygon(new double[]{WIDTH / 1.32, WIDTH / 1.235, WIDTH / 1.235},
                new double[]{HEIGHT / 1.51, HEIGHT / 1.51, HEIGHT / 1.365}, 3);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(WIDTH / 8.5, HEIGHT / 1.65, WIDTH / 8.5, HEIGHT / 1.5);
        gc.strokeLine(WIDTH / 8.1, HEIGHT / 1.65, WIDTH / 8.1, HEIGHT / 1.5);
        gc.setLineWidth(5);
        gc.strokeLine(WIDTH / 15, HEIGHT / 1.65, WIDTH / 5.6, HEIGHT / 1.65);
        gc.setLineWidth(3);

        int changeX = (ticks / 50) % 2 == 0 ? ticks % 50 : 50 - ticks % 50;
        int changeY =  changeX / 3;
        gc.strokeLine((changeX  + WIDTH / 2.1), ( -changeY +HEIGHT / 1.58), WIDTH / 1.7, HEIGHT / 1.5);
        gc.strokeLine((changeX + WIDTH / 2.2), (-changeY + HEIGHT / 1.56), WIDTH / 1.8, HEIGHT / 1.5);
    }

    private void wheel(GraphicsContext gc, double x, double y, double radius) {
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(12);
        gc.strokeOval(x, y, radius, radius);
        gc.fillOval(x, y, radius, radius);
        gc.setFill(Color.GRAY);
        int angle = this.angle;
        for (int i = 0; i < 4; i++) {
            gc.fillArc(x, y, radius, radius, angle, 60, ArcType.ROUND);

            angle = angle + 90;
        }
    }

    private void tree(GraphicsContext gc, double trWidth, double trHeight) {
        for (int i = 0; i < treesArr.length; i++ ) {
            gc.setFill(Color.SANDYBROWN);
            gc.fillRect(treesArr[i].x, treesArr[i].y,  trWidth, trHeight);
            gc.setFill(Color.FORESTGREEN);
            gc.fillOval(treesArr[i].x - 45, treesArr[i].y - 150, trWidth * 3.583, trHeight * 2);
        }
    }

    private void drop (GraphicsContext gc, double x, double y) {
        gc.setStroke(Color.ALICEBLUE);
        gc.setLineWidth(2);
        gc.strokeLine(x, y, x - WIDTH / 450, y + HEIGHT / 90);
    }



    private void markup (GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        double x = WIDTH / 180;
        for (int i = 0; i < marks.length; i++) {
            gc.fillRect(marks[i], HEIGHT / 1.365, WIDTH / 20, HEIGHT / 30);
            x += WIDTH / 7.5;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}