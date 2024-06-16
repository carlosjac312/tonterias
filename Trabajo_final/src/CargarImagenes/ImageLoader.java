package CargarImagenes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Esta Clase toma la imagen de resources para generarla en la interfaz
 */
public class ImageLoader {
    /**
     * La funcion toma el path de la imagen para asi generar una BufferedImage
     * @param path es la direccion de la imagen
     * @return
     */
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
