import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Stack;

public class HtmlAnalyzer {

    public static void main(String[] args) {
        if (args.length == 0) return;
        String urlString = args[0];

        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            Stack<String> stack = new Stack<>();
            String deepestText = null;
            int maxDepth = -1;

            String line;
            while ((line = reader.readLine()) != null){
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("</") && line.endsWith(">")) {
                    //Validate HTML structure and closing tags
                    String tagName = line.substring(2, line.length() - 1);

                    if (stack.isEmpty() || !stack.pop().equals(tagName)){
                        System.out.println("malformed HTML");
                        return;
                    }

                }else if (line.startsWith("<") && line.endsWith(">")){
                    //Track nesting level by pushing opening tags
                    String tagName = line.substring(1, line.length() -1);
                    stack.push(tagName);

                }else {
                    //Identify the deepest text segment
                    int currentDepth = stack.size();

                    if (currentDepth > maxDepth){
                        maxDepth = currentDepth;
                        deepestText = line;
                    }
                }
            }
            reader.close();

            //Final validation of unclosed tags
            if (!stack.isEmpty()){
                System.out.println("malformed HTML");
            }else if (deepestText != null){
                System.out.println(deepestText);
            }
        }catch (Exception e){
            System.out.println("URL connection error"); //Required error output
        }
    }
}
