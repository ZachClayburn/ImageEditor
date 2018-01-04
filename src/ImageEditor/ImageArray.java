package ImageEditor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

class ImageArray {

    private int[][] red;
    private int[][] green;
    private int[][] blue;
    private int width = -1;
    private int height = -1;
    private int maxValue;

    ImageArray(String inFileName){
        Path inFilePath = Paths.get(inFileName);

        try {
            Scanner in = new Scanner(inFilePath,StandardCharsets.UTF_8.displayName());
            String next;
            next = getNext(in);
            if (!next.equals("P3")) {
                //Handle Error
                width = -2;
                height = -2;
            }
            next = getNext(in);
            width = Integer.parseInt(next);

            next = getNext(in);
            height = Integer.parseInt(next);

            red = new int[width][height];
            green = new int[width][height];
            blue = new int[width][height];

            next = getNext(in);
            maxValue = Integer.parseInt(next);

            fillArray(in);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageArray(int widthIn, int heightIn, int maxValueIn){
        width = widthIn;
        height = heightIn;
        maxValue = maxValueIn;
        red = new int[width][height];
        green = new int[width][height];
        blue = new int[width][height];
    }

    ImageArray invert(){
        ImageArray filtered = new ImageArray(width,height,maxValue);

        for(int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                filtered.red[col][row] = maxValue - red[col][row];
                filtered.green[col][row] = maxValue - green[col][row];
                filtered.blue[col][row] = maxValue - blue[col][row];
            }
        }
                return filtered;
    }

    ImageArray graycsale(){
        ImageArray filtered = new ImageArray(width,height,maxValue);

        for(int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int average = (red[col][row] + green[col][row] + blue[col][row]) / 3;
                filtered.red[col][row] = average;
                filtered.green[col][row] = average;
                filtered.blue[col][row] = average;
            }
        }
                return filtered;
    }

    ImageArray emboss(){
        ImageArray filtered = new ImageArray(width,height,maxValue);

        for(int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                filtered.red[col][row] = red[col][row];
                filtered.green[col][row] = green[col][row];
                filtered.blue[col][row] = blue[col][row];
            }
        }
                return filtered;
    }

    ImageArray motionBlur(int blurAmmount){
        ImageArray filtered = new ImageArray(width,height,maxValue);

        for(int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                filtered.red[col][row] = red[col][row];
                filtered.green[col][row] = green[col][row];
                filtered.blue[col][row] = blue[col][row];
            }
        }
                return filtered;
    }


    void writeOutput(String outFileName){
        Path outFilePath = Paths.get(outFileName);
        try {
            Files.createFile(outFilePath);//todo Fix FileAlreadyExistsException
            PrintWriter out = new PrintWriter(Files.newBufferedWriter(outFilePath, StandardCharsets.UTF_8));
            out.println("P3");
            out.println("#Created using ImageEditor by Zach Clayburn");
            Integer w = width;
            Integer h = height;
            out.println(w.toString() + " " + h.toString());
            out.println(maxValue);

            for(int col = 0; col < width; col++) {
                for (int row = 0; row < height; row++) {
                    out.println(red[col][row]);
                    out.println(green[col][row]);
                    out.println(blue[col][row]);
                }
            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getNext(Scanner in){
        String next = in.next();

        while (next.startsWith("#")){
            in.nextLine();
            next = in.next();
        }

        return next;
    }

    private void fillArray(Scanner in){
        String next;
        for(int col = 0; col < width; col++){
            for(int row = 0; row < height; row++){
                next = getNext(in);
                red[col][row]= Integer.parseInt(next);

                next = getNext(in);
                green[col][row]= Integer.parseInt(next);

                next = getNext(in);
                blue[col][row]= Integer.parseInt(next);
            }
        }

    }
}
