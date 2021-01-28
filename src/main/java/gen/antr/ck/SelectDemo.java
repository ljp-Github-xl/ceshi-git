package gen.antr.ck;


import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Created by chengc on 2018/9/30.
 */
public class SelectDemo
{
    public static void main(String[] args)
    {
        // first, form input charstream from string

        CharStream input = CharStreams.fromString("select name from studetnt;");
        // 1. Lexer-Lexical analysis
        // Create a lexer that feeds off of input CharStream.
        SelectExample1Lexer lexer = new SelectExample1Lexer(input);

        // 2. Create a buffer of tokens pulled from the lexer.
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // 3. Parser-Syntax analysis
        // Create a parser that feeds off the tokens buffer.
        SelectExample1Parser parser = new SelectExample1Parser(tokens);

//        System.out.println(parser.tables());
        // 4. Begin parsing at "select" rule.
        ParseTree tree = parser.sql();
        String text = tree.getText();
        System.out.println(text);
        System.out.println(tree.toStringTree(parser));




    }
}
