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

	public static BufferedImage skeletorKillsAgain;
	public static BufferedImage[] skeletorDefault;
	public static BufferedImage[] skeletorAttacked;
	public static BufferedImage tPose;
	public static BufferedImage celebration;
	public static BufferedImage gameOver;
	public static BufferedImage pakuMan;
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
    
    public static BufferedImage map1;
    public static BufferedImage map2;
    public static BufferedImage map3;
    public static BufferedImage OriginalMap;
    public static BufferedImage pacmanMapImage;
    public static BufferedImage[] ghostEdible;
    public static BufferedImage[] ghostLeft1;
    public static BufferedImage[] ghostLeft2;
    public static BufferedImage[] ghostRight1;
    public static BufferedImage[] ghostRight2;
    public static BufferedImage[] ghostUp1;
    public static BufferedImage[] ghostUp2;
    public static BufferedImage[] ghostDown1;
    public static BufferedImage[] ghostDown2;
    public static BufferedImage[] pacmanDots;
    public static BufferedImage[] pacmanRight;
    public static BufferedImage[] pacmanLeft;
    public static BufferedImage[] pacmanUp;
    public static BufferedImage[] pacmanDown;
    public static BufferedImage[] pacManDeath;
    public static BufferedImage[] pacmanScore;
    public static BufferedImage[] pacmanFruits;
    public static BufferedImage[] pacmanGameSelection;
    public static BufferedImage PacManLogo;
    public static BufferedImage[] bound;
    public static BufferedImage intro;
    public static BufferedImage start;
    
    public static BufferedImage galagaImageSheet;
    public SpriteSheet galagaSpriteSheet;
    
    public static BufferedImage pacmanImageSheet;
    public SpriteSheet pacmanSpriteSheet;
    
    public Images() {
    	skeletorDefault = new BufferedImage[12];
    	skeletorAttacked = new BufferedImage[14];

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
        
        pacmanDots = new BufferedImage[3];
        pacmanRight = new BufferedImage[2];
        pacmanLeft = new BufferedImage[2];
        pacmanUp = new BufferedImage[2];
        pacmanDown = new BufferedImage[2];
        bound = new BufferedImage[16];
        ghostEdible = new BufferedImage[4];
        ghostLeft1 = new BufferedImage[4];
        ghostLeft2 = new BufferedImage[4];
        ghostRight1 = new BufferedImage[4];
        ghostRight2 = new BufferedImage[4];
        ghostUp1 = new BufferedImage[4];
        ghostUp2 = new BufferedImage[4];
        ghostDown1 = new BufferedImage[4];
        ghostDown2 = new BufferedImage[4];
        pacManDeath = new BufferedImage[11];
        pacmanScore = new BufferedImage[5];
        pacmanFruits = new BufferedImage[5];
        pacmanGameSelection = new BufferedImage[2];
        
        try {
        	skeletorKillsAgain = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/skeletorKillsAgain.png"));
        	skeletorAttacked[0] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee00.png"));
        	skeletorAttacked[1] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee01.png"));
        	skeletorAttacked[2] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee02.png"));
        	skeletorAttacked[3] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee03.png"));
        	skeletorAttacked[4] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee04.png"));
        	skeletorAttacked[5] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee05.png"));
        	skeletorAttacked[6] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee06.png"));
        	skeletorAttacked[7] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee07.png"));
        	skeletorAttacked[8] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee08.png"));
        	skeletorAttacked[9] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee09.png"));
        	skeletorAttacked[10] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee10.png"));
        	skeletorAttacked[11] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee11.png"));
        	skeletorAttacked[12] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee12.png"));
        	skeletorAttacked[13] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/SkeletorFlee13.png"));
        		
        	skeletorDefault[0] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor00.png"));
        	skeletorDefault[1] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor01.png"));
        	skeletorDefault[2] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor02.png"));
        	skeletorDefault[3] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor03.png"));
        	skeletorDefault[4] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor04.png"));
        	skeletorDefault[5] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor05.png"));
        	skeletorDefault[6] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor06.png"));
        	skeletorDefault[7] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor07.png"));
        	skeletorDefault[8] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor08.png"));
        	skeletorDefault[9] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor09.png"));
        	skeletorDefault[10] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor10.png"));
        	skeletorDefault[11] = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/Skeletor/Skeletor11.png"));		
        	
        	
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
            
            pacmanImageSheet = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/Background.png"));
            PacManLogo = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/PacManLogo.png"));
            pacmanSpriteSheet = new SpriteSheet(pacmanImageSheet);
            pacmanMapImage = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/pacmanImage.png"));
            map1 = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map1.png"));
            map2 = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map2.png"));
            map3 = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/map3.png"));
            OriginalMap = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/PacManMaps/OGMap.png"));           
            
            ghostEdible[0] = pacmanSpriteSheet.crop(584,64,16,16);
            ghostEdible[1] = pacmanSpriteSheet.crop(600,64,16,16);
            ghostEdible[2] = pacmanSpriteSheet.crop(616,64,16,16);
            ghostEdible[3] = pacmanSpriteSheet.crop(632,64,16,16);
            
            
            ghostRight1[0] = pacmanSpriteSheet.crop(456,64,16,16);//Red
            ghostRight1[1] = pacmanSpriteSheet.crop(456,80,16,16);//Pink
            ghostRight1[2] = pacmanSpriteSheet.crop(456,96,16,16);//Blue            
            ghostRight1[3] = pacmanSpriteSheet.crop(456,112,16,16);//Brown
            
            ghostRight2[0] = pacmanSpriteSheet.crop(472,64,16,16);//Red
            ghostRight2[1] = pacmanSpriteSheet.crop(472,80,16,16);//Pink
            ghostRight2[2] = pacmanSpriteSheet.crop(472,96,16,16);//Blue
            ghostRight2[3] = pacmanSpriteSheet.crop(472,112,16,16);//Brown
    
            
            ghostLeft1[0] = pacmanSpriteSheet.crop(488,64,16,16);//Red
            ghostLeft1[1] = pacmanSpriteSheet.crop(488,80,16,16);//Pink
            ghostLeft1[2] = pacmanSpriteSheet.crop(488,96,16,16);//Blue
            ghostLeft1[3] = pacmanSpriteSheet.crop(488,112,16,16);//Brown
            
            ghostLeft2[0] = pacmanSpriteSheet.crop(504,64,16,16);//Red
            ghostLeft2[1] = pacmanSpriteSheet.crop(504,80,16,16);//Pink
            ghostLeft2[2] = pacmanSpriteSheet.crop(504,96,16,16);//Blue
            ghostLeft2[3] = pacmanSpriteSheet.crop(504,112,16,16);//Brown     
            
            
            ghostUp1[0] = pacmanSpriteSheet.crop(520,64,16,16);//Red
            ghostUp1[1] = pacmanSpriteSheet.crop(520,80,16,16);//Pink
            ghostUp1[2] = pacmanSpriteSheet.crop(520,96,16,16);//Blue
            ghostUp1[3] = pacmanSpriteSheet.crop(520,112,16,16);//Brown
            
            ghostUp2[0] = pacmanSpriteSheet.crop(536,64,16,16);//Red
            ghostUp2[1] = pacmanSpriteSheet.crop(536,80,16,16);//Pink
            ghostUp2[2] = pacmanSpriteSheet.crop(536,96,16,16);//Blue      
            ghostUp2[3] = pacmanSpriteSheet.crop(536,112,16,16);//Brown
            
            
            ghostDown1[0] = pacmanSpriteSheet.crop(552,64,16,16);//Red
            ghostDown1[1] = pacmanSpriteSheet.crop(552,80,16,16);//Pink  
            ghostDown1[2] = pacmanSpriteSheet.crop(552,96,16,16);//Blue 
            ghostDown1[3] = pacmanSpriteSheet.crop(552,112,16,16);//Brown
            
            ghostDown2[0] = pacmanSpriteSheet.crop(568,64,16,16);//Red
            ghostDown2[1] = pacmanSpriteSheet.crop(568,80,16,16);//Pink 
            ghostDown2[2] = pacmanSpriteSheet.crop(568,96,16,16);//Blue    
            ghostDown2[3] = pacmanSpriteSheet.crop(568,112,16,16);//Brown
            
            
            pacmanDots[0] = pacmanSpriteSheet.crop(643,18,16,16);
            pacmanDots[1] = pacmanSpriteSheet.crop(623,18,16,16);

            bound[0] = pacmanSpriteSheet.crop(603,18,16,16);//single
            bound[1] = pacmanSpriteSheet.crop(615,37,16,16);//right open
            bound[2] = pacmanSpriteSheet.crop(635,37,16,16);//down open
            bound[3] = pacmanSpriteSheet.crop(655,37,16,16);//left open
            bound[4] = pacmanSpriteSheet.crop(655,57,16,16);//up open
            bound[5] = pacmanSpriteSheet.crop(655,75,16,16);//up/down
            bound[6] = pacmanSpriteSheet.crop(656,116,16,16);//left/Right
            bound[7] = pacmanSpriteSheet.crop(656,136,16,16);//up/Right
            bound[8] = pacmanSpriteSheet.crop(655,174,16,16);//up/left
            bound[9] = pacmanSpriteSheet.crop(655,155,16,16);//down/Right
            bound[10] = pacmanSpriteSheet.crop(655,192,16,16);//down/left
            bound[11] = pacmanSpriteSheet.crop(664,232,16,16);//all
            bound[12] = pacmanSpriteSheet.crop(479,191,16,16);//left
            bound[13] = pacmanSpriteSheet.crop(494,191,16,16);//right
            bound[14] = pacmanSpriteSheet.crop(479,208,16,16);//top
            bound[15] = pacmanSpriteSheet.crop(479,223,16,16);//bottom

            pacmanRight[0] = pacmanSpriteSheet.crop(473,1,12,13);
            pacmanRight[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanLeft[0] = pacmanSpriteSheet.crop(474,17,12,13);
            pacmanLeft[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanUp[0] = pacmanSpriteSheet.crop(473,34,13,12);
            pacmanUp[1] = pacmanSpriteSheet.crop(489,1,13,13);

            pacmanDown[0] = pacmanSpriteSheet.crop(473,48,13,12);
            pacmanDown[1] = pacmanSpriteSheet.crop(489,1,13,13);
            
            pacManDeath[0] = pacmanSpriteSheet.crop(504, 2, 15, 11);
            pacManDeath[1] = pacmanSpriteSheet.crop(519, 3, 17, 10);
            pacManDeath[2] = pacmanSpriteSheet.crop(535, 5, 17, 8);
            pacManDeath[3] = pacmanSpriteSheet.crop(551, 6, 17, 7);
            pacManDeath[4] = pacmanSpriteSheet.crop(567, 7, 17, 7);
            pacManDeath[5] = pacmanSpriteSheet.crop(583, 7, 17, 8);
            pacManDeath[6] = pacmanSpriteSheet.crop(600, 7, 15, 9);
            pacManDeath[7] = pacmanSpriteSheet.crop(616, 7, 15, 9);
            pacManDeath[8] = pacmanSpriteSheet.crop(632, 7, 15, 9);
            pacManDeath[9] = pacmanSpriteSheet.crop(648, 7, 15, 8);
            pacManDeath[10] = pacmanSpriteSheet.crop(665, 5, 13, 12);
            
            pacmanScore[0] = pacmanSpriteSheet.crop(487, 147, 17, 9);
            
            pacmanFruits[0] = pacmanSpriteSheet.crop(489, 49, 14, 14);
            pacmanFruits[1] = pacmanSpriteSheet.crop(505, 49, 13, 14);
            pacmanFruits[2] = pacmanSpriteSheet.crop(521, 49, 14, 14);
            pacmanFruits[3] = pacmanSpriteSheet.crop(537, 49, 14, 14);
            pacmanFruits[4] = pacmanSpriteSheet.crop(554, 48, 13, 16);
            
            pacmanGameSelection[0] = pacmanMapImage;
            pacmanGameSelection[1] = pacmanSpriteSheet.crop(0,0,225,248);//original pacman Map
            
            tPose = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Tpose.png"));
            celebration = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Celebration.png"));
            pakuMan = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/Pac-man.png"));
            gameOver = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/GameOver2.png"));
            intro = ImageIO.read(getClass().getResourceAsStream("/UI/SpriteSheets/PacMan/intro.png"));
            start = ImageIO.read(getClass().getResourceAsStream("/UI/Backgrounds/startScreen.png"));

            
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
