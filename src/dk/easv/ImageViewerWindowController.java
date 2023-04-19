package dk.easv;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import java.io.File;
import java.util.*;

public class ImageViewerWindowController
{
    private final List<Image> images = Collections.synchronizedList(new ArrayList<>());
    private int currentImageIndex = 0;

    private SlideshowThread slideshowThread;

    @FXML
    Parent root;

    @FXML
    private ImageView imageView;
    @FXML
    public Label label;

    @FXML
    private void handleBtnLoadAction()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image files");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Images",
                "*.png", "*.jpg", "*.gif", "*.tif", "*.bmp"));
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        if (!files.isEmpty())
        {
            files.forEach((File f) ->
            {
                images.add(new Image(f.toURI().toString()));
            });
            displayImage();
        }
    }

    @FXML
    private void handleBtnPreviousAction()
    {
        if (!images.isEmpty())
        {
            currentImageIndex =
                    (currentImageIndex - 1 + images.size()) % images.size();
            displayImage();
        }
    }

    @FXML
    public void handleBtnNextAction()
    {
            currentImageIndex = (currentImageIndex + 1) % images.size();
            displayImage();
    }

    public void displayImage()
    {
        if (!images.isEmpty())
        {
            imageView.setImage(images.get(currentImageIndex));
            String filename = new File(images.get(currentImageIndex).getUrl()).getName();
            label.setText("Filename:" + " " + filename);
        }
    }
    @FXML
    public void handleBtnStart(){
            slideshowThread = new SlideshowThread();
            slideshowThread.setImgController(this);
            slideshowThread.setSlideshowOn(true);
            slideshowThread.start();
    }

    public void handleBtnStop(){
        slideshowThread.setSlideshowOn(false);
    }
}