package Resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by AlexVR on 1/24/2020.
 */
public class Images {


    public static BufferedImage titleScreenBackground;
    public static BufferedImage pauseBackground;
    public static BufferedImage selectionBackground;
    public static BufferedImage[] games;
    public static BufferedImage galagaCopyright;
    public static BufferedImage galagaSelect;
    public static BufferedImage muteIcon;
    public static BufferedImage galagaPlayerLaser;
    public static BufferedImage galagamyEnemyLaser;
    public static BufferedImage[] startGameButton;
    public static BufferedImage[] pauseResumeButton;
    public static BufferedImage[] pauseToTitleButton;
    public static BufferedImage[] pauseOptionsButton;
    public static BufferedImage[] galagaLogo;
    public static BufferedImage[] galagaPlayer;
    public static BufferedImage[] galagaPlayerDeath;
    public static BufferedImage[] galagaEnemyDeath;
    public static BufferedImage[] galagaEnemyBee;
    public static BufferedImage[] galagamyEnemy;
    public static BufferedImage galagaImageSheet;
    public SpriteSheet galagaSpriteSheet;
    
    
    
    public Images() {

        startGameButton = new BufferedImage[3];
        pauseResumeButton = new BufferedImage[2];
        pauseToTitleButton = new BufferedImage[2];
        pauseOptionsButton = new BufferedImage[2];
        games = new BufferedImage[7];
        
        galagaLogo = new BufferedImage[3];
        galagaPlayer = new BufferedImage[8];//not full yet, only has second to last image on sprite sheet
        galagaPlayerDeath = new BufferedImage[8];
        galagaEnemyDeath = new BufferedImage[5];
        galagaEnemyBee = new BufferedImage[8];
        galagamyEnemy = new BufferedImage[7];
        
        try {
        	startGameButton[0]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/NormalStartButton.png"));
            startGameButton[1]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/HoverStartButton.png"));
            startGameButton[2]= ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Start/ClickedStartButton.png"));

            titleScreenBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Title.png"));

            pauseBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Pause.png"));

            selectionBackground = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Selection.png"));

            galagaCopyright = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/Copyright.png"));

            galagaSelect = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_select.png"));

            muteIcon = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/mute.png"));
            
            galagaLogo[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Misc/galaga_logo.png"));
            galagaLogo[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/hover_galaga_logo.png"));
            galagaLogo[2] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Selection/Galaga/pressed_galaga_logo.png"));

            pauseResumeButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/NormalHoverResume.png"));
            pauseResumeButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/Resume/PressedResume.png"));

            pauseToTitleButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/NormalHoverToTitleButton.png"));
            pauseToTitleButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToTitle/PressedToTitleButton.png"));

            pauseOptionsButton[0] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/NormalHoverToOptionsButton.png"));
            pauseOptionsButton[1] = ImageIO.read(getClass().getResourceAsStream("/UI/Buttons/Pause/ToOptions/PressedToOptionsButton.png"));
            
            
            games[0] = ImageIO.read(getClass().getResourceAsStream("/RacingGameImages/LogoGameRacing.png"));
            games[1] = ImageIO.read(getClass().getResourceAsStream("/PixelGunGameImages/LogoPixelGun.png"));
            games[2] = ImageIO.read(getClass().getResourceAsStream("/MarioGameImages/LogoMarioGame.png"));
            games[3] = ImageIO.read(getClass().getResourceAsStream("/KirbyGameImages/LogoKirbyGame.png"));
            games[4] = ImageIO.read(getClass().getResourceAsStream("/PacManGameImages/LogoPacManGame.png"));
            games[5] = ImageIO.read(getClass().getResourceAsStream("/GalagaGameImages/LogoGalagaGame.png"));
            games[6] = ImageIO.read(getClass().getResourceAsStream("/ZeldaGameImages/LogoZeldaGame.png"));
           
            
            galagaImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Galaga/Galaga.png"));
            galagaSpriteSheet = new SpriteSheet(galagaImageSheet);

            galagaPlayer[0] = galagaSpriteSheet.crop(162,249,11,12);

            galagaPlayerDeath[0] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[1] = galagaSpriteSheet.crop(209,48,32,32);
            galagaPlayerDeath[2] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[3] = galagaSpriteSheet.crop(247,48,32,32);
            galagaPlayerDeath[4] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[5] = galagaSpriteSheet.crop(288,47,32,32);
            galagaPlayerDeath[6] = galagaSpriteSheet.crop(327,47,32,32);
            galagaPlayerDeath[7] = galagaSpriteSheet.crop(327,47,32,32);

            galagaEnemyDeath[0] = galagaSpriteSheet.crop(201,191,32,32);
            galagaEnemyDeath[1] = galagaSpriteSheet.crop(223,191,32,32);
            galagaEnemyDeath[2] = galagaSpriteSheet.crop(247,191,32,32);
            galagaEnemyDeath[3] = galagaSpriteSheet.crop(280,191,32,32);
            galagaEnemyDeath[4] = galagaSpriteSheet.crop(320,191,32,32);

            galagaEnemyBee[0] = galagaSpriteSheet.crop(188,178,9,10);
            galagaEnemyBee[1] = galagaSpriteSheet.crop(162,178,13,10);
            galagaEnemyBee[2] = galagaSpriteSheet.crop(139,177,11,12);
            galagaEnemyBee[3] = galagaSpriteSheet.crop(113,176,14,13);
            galagaEnemyBee[4] = galagaSpriteSheet.crop(90,177,13,13);
            galagaEnemyBee[5] = galagaSpriteSheet.crop(65,176,13,14);
            galagaEnemyBee[6] = galagaSpriteSheet.crop(42,178,12,11);
            galagaEnemyBee[7] = galagaSpriteSheet.crop(19,177,10,13);
            
            galagamyEnemy[0] = galagaSpriteSheet.crop(162, 203, 12, 10);
            galagamyEnemy[1] = galagaSpriteSheet.crop(139, 203, 12, 10);
            galagamyEnemy[2] = galagaSpriteSheet.crop(113, 203, 12, 10);
            galagamyEnemy[3] = galagaSpriteSheet.crop(90, 203, 12, 10);
            galagamyEnemy[4] = galagaSpriteSheet.crop(65, 203, 12, 10);
            galagamyEnemy[5] = galagaSpriteSheet.crop(42, 203, 12, 10);
            galagamyEnemy[6] = galagaSpriteSheet.crop(19, 203, 12, 10);


            galagaPlayerLaser = galagaSpriteSheet.crop(373 ,50, 4, 8);
            galagamyEnemyLaser = galagaSpriteSheet.crop(224, 103, 16, 10);
            
        }catch (IOException e) {
        e.printStackTrace();
    }


    }


    public BufferedImage invertImage(BufferedImage bufferedImage, String name) {
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
        String path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
        File imagess = new File(path2.replaceAll("%20"," "));
        if (imagess.exists()){
            try {
                return ImageIO.read(imagess.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        for (int x = 0; x < bufferedImage.getWidth(); x++) {
            for (int y = 0; y < bufferedImage.getHeight(); y++) {
                int rgba = bufferedImage.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                        255 - col.getGreen(),
                        255 - col.getBlue());
                bufferedImage.setRGB(x, y, col.getRGB());
            }
        }
        File f = null;

        try
        {
            path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
            path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
            f = new File(path2.replaceAll("%20"," "));
            System.out.println("File saved in: "+path2);
            ImageIO.write(bufferedImage, "png", f);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        return bufferedImage;
    }

    public static Color transparant = new Color(255, 255, 255, 0);
    public static Color brown = new Color(200,76,12);

    public BufferedImage createImageTransparent(BufferedImage image, String name, int RGBToReplace){


        return createImage(image,name,RGBToReplace,transparant.getRGB());
    }

    public BufferedImage createImage(BufferedImage image, String name, int RGBToReplace,int RGBReplaicing){

        int width = image.getWidth();
        int height = image.getHeight();
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
        String path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
        File imagess = new File(path2.replaceAll("%20"," "));
        if (imagess.exists()){
            try {
                return ImageIO.read(imagess.getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        // Create buffered image object
        BufferedImage img = null;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // file object
        File f = null;

        // create random values pixel by pixel
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if (image.getRGB(x, y) == RGBToReplace) {
                    img.setRGB(x, y, RGBReplaicing);
                } else {
                    img.setRGB(x, y, image.getRGB(x, y));
                }


            }
        }

        // write image, AKA save it to pc
        try
        {
            path = Objects.requireNonNull(getClass().getClassLoader().getResource(".")).getPath();
            path2 = path.substring(0,path.indexOf("/bin/"))+"/res/Edited/"+name+".png";
            f = new File(path2.replaceAll("%20"," "));
            System.out.println("File saved in: "+path2);
            ImageIO.write(img, "png", f);
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e);
        }
        return img;
    }


    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

        // Copy image
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }

    public static BufferedImage flipHorizontal(BufferedImage image){
        // Flip the image horizontally
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }

}
