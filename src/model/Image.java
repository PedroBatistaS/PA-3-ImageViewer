package model;


public interface Image {
    <T> T bitmap();
    Image next();
    Image previous();   
    int getWidth();
    int getHeight();
}
