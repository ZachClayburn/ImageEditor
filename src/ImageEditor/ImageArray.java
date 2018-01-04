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
    private Scanner in;

    ImageArray(String inFileName){
        Path inFilePath = Paths.get(inFileName);

        try {
            in = new Scanner(inFilePath,StandardCharsets.UTF_8.displayName());
            String next;
            next = getNext();
            if (!next.equals("P3")) {
                //Handle Error
                width = -2;
                height = -2;
            }
            next = getNext();
            width = Integer.parseInt(next);

            next = getNext();
            height = Integer.parseInt(next);

            red = new int[width][height];
            green = new int[width][height];
            blue = new int[width][height];

            next = getNext();
            maxValue = Integer.parseInt(next);

            fillArray();

        }catch (IOException e) {
            e.printStackTrace();
        }
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

    private String getNext(){
        String next = in.next();

        while (next.startsWith("#")){
            in.nextLine();
            next = in.next();
        }

        return next;
    }

    private void fillArray(){
        String next;
        for(int col = 0; col < width; col++){
            for(int row = 0; row < height; row++){
                next = getNext();
                red[col][row]= Integer.parseInt(next);

                next = getNext();
                green[col][row]= Integer.parseInt(next);

                next = getNext();
                blue[col][row]= Integer.parseInt(next);
            }
        }

    }
}
