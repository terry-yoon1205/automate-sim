// Generated from C:/Users/thebw/OneDrive/Documents/GitHub/Group15Project1/src/parser/AutomateSimLexer.g4 by ANTLR 4.13.1
package parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class AutomateSimLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TYPE=1, INHERITS=2, ENUM=3, STRING=4, NUMBER=5, ROOM=6, OF=7, ACTION=8, 
		ON=9, SET=10, TO=11, IF=12, IS=13, FOR=14, IN=15, COMMA=16, DOT=17, DECL_START=18, 
		DECL_END=19, LIST_START=20, LIST_END=21, ARG_START=22, ARG_END=23, WS=24, 
		NUM=25, STR=26, VAR=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"TYPE", "INHERITS", "ENUM", "STRING", "NUMBER", "ROOM", "OF", "ACTION", 
			"ON", "SET", "TO", "IF", "IS", "FOR", "IN", "COMMA", "DOT", "DECL_START", 
			"DECL_END", "LIST_START", "LIST_END", "ARG_START", "ARG_END", "WS", "NUM", 
			"STR", "VAR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'type'", "'inherits'", "'enum'", "'string'", "'number'", "'room'", 
			"'of'", "'action'", "'on'", "'set'", "'to'", "'if'", "'is'", "'for'", 
			"'in'", "','", "'.'", "'{'", "'}'", "'['", "']'", "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TYPE", "INHERITS", "ENUM", "STRING", "NUMBER", "ROOM", "OF", "ACTION", 
			"ON", "SET", "TO", "IF", "IS", "FOR", "IN", "COMMA", "DOT", "DECL_START", 
			"DECL_END", "LIST_START", "LIST_END", "ARG_START", "ARG_END", "WS", "NUM", 
			"STR", "VAR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public AutomateSimLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AutomateSimLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u001b\u00a5\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0018\u0004\u0018\u0094\b\u0018\u000b\u0018\f"+
		"\u0018\u0095\u0001\u0019\u0001\u0019\u0005\u0019\u009a\b\u0019\n\u0019"+
		"\f\u0019\u009d\t\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0004\u001a"+
		"\u00a2\b\u001a\u000b\u001a\f\u001a\u00a3\u0000\u0000\u001b\u0001\u0001"+
		"\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f"+
		"\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f"+
		"\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u0018"+
		"1\u00193\u001a5\u001b\u0001\u0000\u0004\u0003\u0000\t\n\r\r  \u0001\u0000"+
		"09\u0003\u0000\n\n\r\r\"\"\u000b\u0000\n\n\r\r  \"\"(),,..[[]]{{}}\u00a7"+
		"\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000"+
		"\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000"+
		"\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000+\u0001\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/"+
		"\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000"+
		"\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u00017\u0001\u0000\u0000\u0000"+
		"\u0003<\u0001\u0000\u0000\u0000\u0005E\u0001\u0000\u0000\u0000\u0007J"+
		"\u0001\u0000\u0000\u0000\tQ\u0001\u0000\u0000\u0000\u000bX\u0001\u0000"+
		"\u0000\u0000\r]\u0001\u0000\u0000\u0000\u000f`\u0001\u0000\u0000\u0000"+
		"\u0011g\u0001\u0000\u0000\u0000\u0013j\u0001\u0000\u0000\u0000\u0015n"+
		"\u0001\u0000\u0000\u0000\u0017q\u0001\u0000\u0000\u0000\u0019t\u0001\u0000"+
		"\u0000\u0000\u001bw\u0001\u0000\u0000\u0000\u001d{\u0001\u0000\u0000\u0000"+
		"\u001f~\u0001\u0000\u0000\u0000!\u0080\u0001\u0000\u0000\u0000#\u0082"+
		"\u0001\u0000\u0000\u0000%\u0084\u0001\u0000\u0000\u0000\'\u0086\u0001"+
		"\u0000\u0000\u0000)\u0088\u0001\u0000\u0000\u0000+\u008a\u0001\u0000\u0000"+
		"\u0000-\u008c\u0001\u0000\u0000\u0000/\u008e\u0001\u0000\u0000\u00001"+
		"\u0093\u0001\u0000\u0000\u00003\u0097\u0001\u0000\u0000\u00005\u00a1\u0001"+
		"\u0000\u0000\u000078\u0005t\u0000\u000089\u0005y\u0000\u00009:\u0005p"+
		"\u0000\u0000:;\u0005e\u0000\u0000;\u0002\u0001\u0000\u0000\u0000<=\u0005"+
		"i\u0000\u0000=>\u0005n\u0000\u0000>?\u0005h\u0000\u0000?@\u0005e\u0000"+
		"\u0000@A\u0005r\u0000\u0000AB\u0005i\u0000\u0000BC\u0005t\u0000\u0000"+
		"CD\u0005s\u0000\u0000D\u0004\u0001\u0000\u0000\u0000EF\u0005e\u0000\u0000"+
		"FG\u0005n\u0000\u0000GH\u0005u\u0000\u0000HI\u0005m\u0000\u0000I\u0006"+
		"\u0001\u0000\u0000\u0000JK\u0005s\u0000\u0000KL\u0005t\u0000\u0000LM\u0005"+
		"r\u0000\u0000MN\u0005i\u0000\u0000NO\u0005n\u0000\u0000OP\u0005g\u0000"+
		"\u0000P\b\u0001\u0000\u0000\u0000QR\u0005n\u0000\u0000RS\u0005u\u0000"+
		"\u0000ST\u0005m\u0000\u0000TU\u0005b\u0000\u0000UV\u0005e\u0000\u0000"+
		"VW\u0005r\u0000\u0000W\n\u0001\u0000\u0000\u0000XY\u0005r\u0000\u0000"+
		"YZ\u0005o\u0000\u0000Z[\u0005o\u0000\u0000[\\\u0005m\u0000\u0000\\\f\u0001"+
		"\u0000\u0000\u0000]^\u0005o\u0000\u0000^_\u0005f\u0000\u0000_\u000e\u0001"+
		"\u0000\u0000\u0000`a\u0005a\u0000\u0000ab\u0005c\u0000\u0000bc\u0005t"+
		"\u0000\u0000cd\u0005i\u0000\u0000de\u0005o\u0000\u0000ef\u0005n\u0000"+
		"\u0000f\u0010\u0001\u0000\u0000\u0000gh\u0005o\u0000\u0000hi\u0005n\u0000"+
		"\u0000i\u0012\u0001\u0000\u0000\u0000jk\u0005s\u0000\u0000kl\u0005e\u0000"+
		"\u0000lm\u0005t\u0000\u0000m\u0014\u0001\u0000\u0000\u0000no\u0005t\u0000"+
		"\u0000op\u0005o\u0000\u0000p\u0016\u0001\u0000\u0000\u0000qr\u0005i\u0000"+
		"\u0000rs\u0005f\u0000\u0000s\u0018\u0001\u0000\u0000\u0000tu\u0005i\u0000"+
		"\u0000uv\u0005s\u0000\u0000v\u001a\u0001\u0000\u0000\u0000wx\u0005f\u0000"+
		"\u0000xy\u0005o\u0000\u0000yz\u0005r\u0000\u0000z\u001c\u0001\u0000\u0000"+
		"\u0000{|\u0005i\u0000\u0000|}\u0005n\u0000\u0000}\u001e\u0001\u0000\u0000"+
		"\u0000~\u007f\u0005,\u0000\u0000\u007f \u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0005.\u0000\u0000\u0081\"\u0001\u0000\u0000\u0000\u0082\u0083"+
		"\u0005{\u0000\u0000\u0083$\u0001\u0000\u0000\u0000\u0084\u0085\u0005}"+
		"\u0000\u0000\u0085&\u0001\u0000\u0000\u0000\u0086\u0087\u0005[\u0000\u0000"+
		"\u0087(\u0001\u0000\u0000\u0000\u0088\u0089\u0005]\u0000\u0000\u0089*"+
		"\u0001\u0000\u0000\u0000\u008a\u008b\u0005(\u0000\u0000\u008b,\u0001\u0000"+
		"\u0000\u0000\u008c\u008d\u0005)\u0000\u0000\u008d.\u0001\u0000\u0000\u0000"+
		"\u008e\u008f\u0007\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000"+
		"\u0090\u0091\u0006\u0017\u0000\u0000\u00910\u0001\u0000\u0000\u0000\u0092"+
		"\u0094\u0007\u0001\u0000\u0000\u0093\u0092\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0095"+
		"\u0096\u0001\u0000\u0000\u0000\u00962\u0001\u0000\u0000\u0000\u0097\u009b"+
		"\u0005\"\u0000\u0000\u0098\u009a\b\u0002\u0000\u0000\u0099\u0098\u0001"+
		"\u0000\u0000\u0000\u009a\u009d\u0001\u0000\u0000\u0000\u009b\u0099\u0001"+
		"\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000\u0000\u009c\u009e\u0001"+
		"\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000\u0000\u009e\u009f\u0005"+
		"\"\u0000\u0000\u009f4\u0001\u0000\u0000\u0000\u00a0\u00a2\b\u0003\u0000"+
		"\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a1\u0001\u0000\u0000\u0000\u00a3\u00a4\u0001\u0000\u0000"+
		"\u0000\u00a46\u0001\u0000\u0000\u0000\u0004\u0000\u0095\u009b\u00a3\u0001"+
		"\u0000\u0001\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}