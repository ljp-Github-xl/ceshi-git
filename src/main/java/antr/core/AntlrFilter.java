package antr.core;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;


public class AntlrFilter {
    private static final ANTLRErrorListener antlrErrorListener = new AntlrErrorListener();

    public void checkAndGetTableNames(String sql) {
        ClickHouseLexer lexer = new ClickHouseLexer(CharStreams.fromString(sql));
        ClickHouseParser parser = new ClickHouseParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(antlrErrorListener);
        ClickHouseParser.QueryStmtContext queryStmtContext = null;
        try {
            queryStmtContext = parser.queryStmt();
        } catch (RecognitionException ignored) {
        }

        if (null == queryStmtContext || null == queryStmtContext.query() || null == queryStmtContext.query().selectUnionStmt()) {
            System.out.println("没有找到SELECT语句");
        }
        if (null != queryStmtContext.INTO() || null != queryStmtContext.OUTFILE()) {
            System.out.println("禁止使用 INTO OUTFILE 语句");
        }
    }

    private HashSet<String> treeTraversal(ParseTree root) {
        HashSet<String> tableNames = new HashSet<>();
        if (root == null) {
            return null;
        }
        Deque<ParseTree> childs = new LinkedList<>();
        childs.add(root);

        while (!childs.isEmpty()) {
            ParseTree node = childs.pop();
            int i = node.getChildCount();
            int k = 0;
            while (k < i) {
                ParseTree child = node.getChild(k);
                if (child instanceof ClickHouseParser.TableExprIdentifierContext) {
                    // find the node which contains table names
                    String tableName = child.getText();
                    tableName = tableName.replace("\"", "");
                    tableNames.add(tableName);
                }
                childs.push(child);
                k++;
            }
        }
        return tableNames;
    }
}
