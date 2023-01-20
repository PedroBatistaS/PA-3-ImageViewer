package control;

import ui.ImageDisplay;


public class NextImageCommand implements Command{
    private ImageDisplay imageDisplay;
    
    
    public NextImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }   
    
    
    @Override
    public void execute() {
        imageDisplay.show(imageDisplay.image().next());
    }
}
