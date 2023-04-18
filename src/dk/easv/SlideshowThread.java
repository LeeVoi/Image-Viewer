package dk.easv;

import javafx.scene.image.Image;

import java.util.List;

public class SlideshowThread extends Thread{

    ImageViewerWindowController imgController;
    Boolean slideshowOn;

    @Override
    public void run() {
        while(slideshowOn){
            imgController.handleBtnNextAction();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setImgController(ImageViewerWindowController newImgController){
        imgController = newImgController;
    }

    public void setSlideshowOn(Boolean isSlideshowOn){
        slideshowOn = isSlideshowOn;
    }
}
