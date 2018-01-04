public class ImageEditor {

    public static void main (String[] args) {
        String inFileName;
        String outFileName;
        String filterName;
        int blurLength = -1;
        int maxLength = 3;
        try{
            inFileName = args[0];
            outFileName = args[1];
            filterName = args[2];

            if(filterName.equals("motionblur")){
                try {
                    blurLength = Integer.parseInt(args[3]);
                } catch(NumberFormatException e) {
                    printUsage();
                    return;
                }
                maxLength++;
            }
        }catch(IndexOutOfBoundsException ex){
            printUsage();
            return;
        }
        if(args.length > maxLength){
            printUsage();
            return;
        }

        new ImageEditor(inFileName, outFileName, filterName, blurLength);
    }

    public ImageEditor(String inFileName, String outFileName, String filterType, int blurLength){

    }

    private static void printUsage(){
        System.out.println("USAGE: java ImageEditor in-file out-file (grayscale|invert|emboss|motionblur motion-blur-length)");
    }
}