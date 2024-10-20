import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TestingWriter {
    public static void main(String[] args) {
         File log = new File("log.txt");
    try{
    if(log.exists()==false){
            System.out.println("We had to make a new file.");
            log.createNewFile();
    }
    PrintWriter out = new PrintWriter(new FileWriter(log, true));
    out.append ( "Second Try");
    out.close();
    }catch(IOException e){
        System.out.println("COULD NOT LOG!!");
    }
    }
}
