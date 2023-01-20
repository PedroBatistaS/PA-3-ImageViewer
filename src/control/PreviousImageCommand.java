package control;

import ui.ImageDisplay;


public class PreviousImageCommand implements Command{
    private ImageDisplay imageDisplay;
    
    
    public PreviousImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }    

    @Override
    public void execute() {
        imageDisplay.show(imageDisplay.image().previous());
    }

}
