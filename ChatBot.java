import java.util.*;

public class ChatBot {

    // Some stop words to ignore
    private static final Set<String> STOPWORDS = new HashSet<>(Arrays.asList(
        "is","am","are","the","a","an","to","for","in","on","at","of","and","or","but","what","who","how"
    )); 

    // Function: preprocess text → lowercase, remove punctuation, split, remove stopwords
    private static List<String> tokenize(String text) {
        text = text.toLowerCase().replaceAll("[^a-z ]", " ");
        String[] words = text.split("\\s+");
        List<String> tokens = new ArrayList<>();
        for (String w : words) {
            if (!w.isEmpty() && !STOPWORDS.contains(w)) {
                tokens.add(w);
            }
        }
        return tokens;
    }

    // Simple rule-based responses using NLP keywords
    private static String getResponse(String input) {
        List<String> tokens = tokenize(input);

        if (tokens.contains("hi") || tokens.contains("hello")) {
            return "Hello! How can I assist you?";
        }
        if (tokens.contains("name")) {
            return "I am a simple NLP chatbot.";
        }
        if (tokens.contains("time")) {
            return "Sorry, I cannot tell the time yet.";
        }
        if (tokens.contains("thanks") || tokens.contains("thank")) {
            return "You're welcome!";
        }
        if (tokens.contains("bye")) {
            return "Goodbye! Have a nice day!";
        }

        return "Hmm, I didn't understand. Can you rephrase?";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I am an NLP-based chatbot. Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String userInput = sc.nextLine();

            String response = getResponse(userInput);
            System.out.println("Bot: " + response);

            if (response.contains("Goodbye")) {
                break;
            }
        }

        sc.close();
    }
}
