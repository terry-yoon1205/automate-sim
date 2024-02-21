package parser;

import exceptions.ParserException;
import org.antlr.v4.runtime.*;

public class NoRecoverStrategy extends DefaultErrorStrategy {
    @Override
    public void reportError(Parser recognizer, RecognitionException e) {
        super.reportError(recognizer, e);
        throw new ParserException(e);
    }

    @Override
    protected void reportUnwantedToken(Parser recognizer) {
        super.reportUnwantedToken(recognizer);
        throw new ParserException();
    }
}
