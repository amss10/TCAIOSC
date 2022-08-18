import generatedantlr.Swift3Lexer;
import generatedantlr.Swift3Parser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.print.attribute.standard.Destination;
import javax.swing.*;
import java.io.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;


public class Main {
    public static boolean terminated;
    public static  String projectDirectory = System.getProperty("user.dir");
    public static void main(String[] args) {
        terminated = false;
        /******************** source code reading **************************/
        //file class
        src.ReadFile read = new src.ReadFile();
        String swiftpath = "";
        if (true) {
            File directoryPath = new File(projectDirectory+"\\src\\"+"SwiftInput");

            for(File fileElement : directoryPath.listFiles()) {
                // Read file
                swiftpath = projectDirectory+"\\src\\"+"SwiftInput\\" +fileElement.getName();
                read.setSwiftname(fileElement.getName());
                read.Swiftname = fileElement.getName();
                System.out.println(swiftpath);
                String swiftCode = read.IosFileRead(swiftpath);

                // Convert to java
                String javaCode = convertToJava(swiftCode);
                System.out.println(javaCode);

                //create a file
                read.IosFileWrite(javaCode);
            }

            terminated = true;
        } else {

//        //choose a file
        swiftpath = read.chooseFile();
//        //read a file
        String swiftCode = read.IosFileRead(swiftpath);
//        String swiftCode = "struct ContentView: View {\n" +
//                "    @State private var name: String = \"\"\n" +
//                "@State private var age:String = \"\"\n" +
//                "    @State private var fullText: String = \"This is some editable text...\"\n" +
//                "\t@state private var new: String = \"BLABLABLA\"\n" +
//                "\n" +
//                "    var body: some let View {        \t\n" +
//                "\t   TextField(\"Enter your name\", text: name)\n" +
//                "\t   TextField(\"What is your age\", text: age)\n" +
//                "\t   TextEditor(text: fullText)\n" +
//                "\t   TextEditor(text: new)\n" +
//                "           Link(\"LearnSwiftUI\", destination: URL(string: \"https://www.hackingwithswift.com/quick-start/swiftui\")!)\n" +
//                "\tLink(\"google\",destination: URL(string: \"https://www.google.com\")!)\n" +
//                "\t   Image(\"foo\")\n" +
//                "\tImage(\"sky\")\n" +
//                "           Alert(title: Text(\"Title of the alert\"), message: Text(\"Alert Example\"), dismissButton: .default(Text(\"OK\")))\n" +
//                "Alert(title: Text(\"Title of the alert\"), message: Text(\"Alert 2\"), dismissButton: .default(Text(\"OK\")))\t\n" +
//                "\t\t     \t\t\t\t        \n" +
//                "    }\n" +
//                "}";
//
        /******************** Destination code writing **************************/

        String javaCode = convertToJava(swiftCode);
        System.out.println(javaCode);
//        //create a file
        read.IosFileWrite(javaCode);
        terminated = true;

        /******************** Components Counter **************************/

        ArrayList<String> javaComponents = new ArrayList<String>();
        javaComponents.add("TextView ");
        javaComponents.add("EditText ");
        javaComponents.add("Button ");
        javaComponents.add("AlertDialog ");
        javaComponents.add("ImageView ");
        javaComponents.add("ProgressView ");
        javaComponents.add("Switch ");
        javaComponents.add("Picker ");
        javaComponents.add("URL ");
        javaComponents.add("URLButton ");
        int DestinationComponentsCount = 0;
        int count =0;
        for(int i = 0; i<javaComponents.size();i++){
            count+= countMatches(javaCode, javaComponents.get(i));
        }
        System.out.println("Swift Components = "+SwiftToJavaVisitor.ComponentsCounter);
        System.out.println("Java Components  = "+count);
        System.out.println("XML Components   = "+XmlWriter.XMLComponentsCounter);
        System.out.println("Swift To Java & XML ratio  = "+SwiftToJavaVisitor.ComponentsCounter+" : "+(count+XmlWriter.XMLComponentsCounter));
        System.out.println("Swift To Java ratio        = "+SwiftToJavaVisitor.ComponentsCounter+" : "+count);
        System.out.println("Swift To XML ratio         = "+SwiftToJavaVisitor.ComponentsCounter+" : "+(XmlWriter.XMLComponentsCounter));

        /******************** Colors configurations testing **************************/

        int     R = 10,
                G = 132,
                B = 255;
        String hexColor = ColorsConfig.RGBToHex(R,G,B);
        String hexColor2 = ColorsConfig.RGBToHex(48,209,88);
        System.out.println(hexColor);
        System.out.println(hexColor2);
        }
    }


    public static void ReadDirectory(String path) throws IOException {
        File dir = new File(path);
        File[] files = dir.listFiles();
        for (File file : files) {
            String fullFileContent="";
            if(file.isFile()&&(file.getName().substring(file.getName().indexOf('.')+1).startsWith("swift")||file.getName().substring(file.getName().indexOf('.')+1).startsWith("txt")||file.getName().substring(file.getName().indexOf('.')+1).startsWith("storyboard"))) {
                BufferedReader inputStream = null;
                String line;
                System.out.println(file.getName());
                try {
                    inputStream = new BufferedReader(new FileReader(file));
                    while ((line = inputStream.readLine()) != null) {
                        fullFileContent+=line+"\n";
                    }
                }catch(IOException e) {
                    System.out.println(e);
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
            System.out.println(file);
            System.out.println(fullFileContent);

            String javaCode = convertToJava(fullFileContent);
            System.out.println(javaCode);
            //create a file
            src.ReadFile.IosFileWrite(javaCode);
            terminated = true;
        }


    }
    public static String ChooseDirectory(){
        String path = "";
        JButton open = new JButton();
        JFileChooser choose = new JFileChooser();
        choose.setCurrentDirectory((new java.io.File("/Users/amssh/Desktop")));
        choose.setDialogTitle("Choose a file");
        if (choose.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
            //
        }
        path =choose.getSelectedFile().getAbsolutePath();

        return path;
    }

    private static String convertToJava(String swiftCode) {
        SwiftToJavaVisitor swiftToJavaVisitor = new SwiftToJavaVisitor();
        Swift3Parser parser = new Swift3Parser(null);
        ANTLRInputStream input = new ANTLRInputStream(swiftCode);
        Swift3Lexer lexer = new Swift3Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser.setInputStream(tokens);
        // from here u need to change your code

        ParseTree parseTree = parser.top_level();
        String javaCode = swiftToJavaVisitor.visitTop_level((Swift3Parser.Top_levelContext) parseTree);
        return javaCode;
    }

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    /* Counts how many times the substring appears in the larger string. */
    public static int countMatches(String text, String str)
    {
        if (isEmpty(text) || isEmpty(str)) {
            return 0;
        }

        int index = 0, count = 0;
        while (true)
        {
            index = text.indexOf(str, index);
            if (index != -1)
            {
                count ++;
                index += str.length();
            }
            else {
                break;
            }
        }

        return count;
    }

}
