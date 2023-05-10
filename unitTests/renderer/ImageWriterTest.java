package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {


    @Test
    void testWriteToImage() {
        //initialize the view plane resolustion for 800x500
        int nX=800;
        int nY=500;
        double height=10;
        double width= 16;
        Color red = new Color(255,0,0);
        Color blue = new Color (0,0,255);

        ImageWriter imageWriter= new ImageWriter("testRedBlueGrid",nX,nY);
        double margin= imageWriter.getNx()/width;
        for (int i=0;i<nX;i++)//scan the width
        {
            for(int j=0;j<nY;j++)
            {
                if (j % margin==0 || i % margin==0)
                {
                    imageWriter.writePixel(i,j,blue);
                }
                else
                {
                    imageWriter.writePixel(i,j,red);
                }
            }
        }
        imageWriter.writeToImage();

    }

}