package utils;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class TileRenderer {
    private static final String filePath = ClassLoader.getSystemResource("sprite/tiles.png").toString();
    private static final Image tileSet = new Image(filePath);
    public static int TILE_SIZE = 16;

    public static Image getTile(char i) {

        if (i=='0') return new WritableImage(tileSet.getPixelReader(), 0, 16, TILE_SIZE, TILE_SIZE); // wall 1
        if (i=='1') return new WritableImage(tileSet.getPixelReader(), 0, 32, TILE_SIZE, TILE_SIZE); // floor 1

        if (i=='2') return new WritableImage(tileSet.getPixelReader(), 32, 48, TILE_SIZE, TILE_SIZE); // floor 2
        if (i=='3') return new WritableImage(tileSet.getPixelReader(), 48, 16, TILE_SIZE, TILE_SIZE); // wall 2

        if (i=='4') return new WritableImage(tileSet.getPixelReader(), 48, 32, TILE_SIZE, TILE_SIZE); // floor 3

        if (i=='5') return new WritableImage(tileSet.getPixelReader(), 6*16, 64, TILE_SIZE, TILE_SIZE); // wall 3
        if (i=='6') return new WritableImage(tileSet.getPixelReader(), 6*16, 64+16, TILE_SIZE, TILE_SIZE); // floor 4

        if (i=='7') return new WritableImage(tileSet.getPixelReader(), 0, 16*6, TILE_SIZE, TILE_SIZE); // floor 5
        if (i=='8') return new WritableImage(tileSet.getPixelReader(), 0, 16*7, TILE_SIZE, TILE_SIZE); // floor 6
        if (i=='9') return new WritableImage(tileSet.getPixelReader(), 0, 16*8, TILE_SIZE, TILE_SIZE); // floor 7
        if (i=='a') return new WritableImage(tileSet.getPixelReader(), 16, 16*6, TILE_SIZE, TILE_SIZE); // floor 8
        if (i=='b') return new WritableImage(tileSet.getPixelReader(), 16, 16*7, TILE_SIZE, TILE_SIZE); // floor 9
        if (i=='c') return new WritableImage(tileSet.getPixelReader(), 16, 16*8, TILE_SIZE, TILE_SIZE); // floor 10
        if (i=='d') return new WritableImage(tileSet.getPixelReader(), 32, 16*6, TILE_SIZE, TILE_SIZE); // floor 8
        if (i=='e') return new WritableImage(tileSet.getPixelReader(), 32, 16*7, TILE_SIZE, TILE_SIZE); // floor 9
        if (i=='f') return new WritableImage(tileSet.getPixelReader(), 32, 16*8, TILE_SIZE, TILE_SIZE); // floor 10
        if (i=='g') return new WritableImage(tileSet.getPixelReader(), 48, 16*6, TILE_SIZE, TILE_SIZE); // floor 8
        if (i=='h') return new WritableImage(tileSet.getPixelReader(), 48, 16*7, TILE_SIZE, TILE_SIZE); // floor 9
        if (i=='i') return new WritableImage(tileSet.getPixelReader(), 48, 16*8, TILE_SIZE, TILE_SIZE); // floor 10

        return null;
    }
}
