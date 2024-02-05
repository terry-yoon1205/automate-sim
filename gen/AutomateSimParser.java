// Generated from /Users/Admin/Desktop/courses/2023w2/cpsc410/project1/src/parser/AutomateSimParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class AutomateSimParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TYPE=1, INHERITS=2, ENUM=3, STRING=4, NUMBER=5, ROOM=6, OF=7, ACTION=8, 
		ON=9, SET=10, TO=11, IF=12, IS=13, FOR=14, IN=15, COMMA=16, DOT=17, DECL_START=18, 
		DECL_END=19, LIST_START=20, LIST_END=21, ARG_START=22, ARG_END=23, WS=24, 
		NUM=25, STR=26, VAR=27;
	public static final int
		RULE_program = 0, RULE_decl = 1, RULE_type = 2, RULE_property = 3, RULE_enum = 4, 
		RULE_string = 5, RULE_number = 6, RULE_arg = 7, RULE_room = 8, RULE_device = 9, 
		RULE_device_prop = 10, RULE_statement = 11, RULE_action = 12, RULE_if = 13, 
		RULE_for = 14, RULE_set = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "decl", "type", "property", "enum", "string", "number", "arg", 
			"room", "device", "device_prop", "statement", "action", "if", "for", 
			"set"
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

	@Override
	public String getGrammarFileName() { return "AutomateSimParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AutomateSimParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(AutomateSimParser.EOF, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				decl();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 322L) != 0) );
			setState(37);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public RoomContext room() {
			return getRuleContext(RoomContext.class,0);
		}
		public ActionContext action() {
			return getRuleContext(ActionContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(39);
				type();
				}
				break;
			case ROOM:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				room();
				}
				break;
			case ACTION:
				enterOuterAlt(_localctx, 3);
				{
				setState(41);
				action();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(AutomateSimParser.TYPE, 0); }
		public List<TerminalNode> VAR() { return getTokens(AutomateSimParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(AutomateSimParser.VAR, i);
		}
		public TerminalNode DECL_START() { return getToken(AutomateSimParser.DECL_START, 0); }
		public TerminalNode DECL_END() { return getToken(AutomateSimParser.DECL_END, 0); }
		public TerminalNode INHERITS() { return getToken(AutomateSimParser.INHERITS, 0); }
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(TYPE);
			setState(45);
			match(VAR);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(46);
				match(INHERITS);
				setState(47);
				match(VAR);
				}
			}

			setState(50);
			match(DECL_START);
			setState(52); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(51);
				property();
				}
				}
				setState(54); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 56L) != 0) );
			setState(56);
			match(DECL_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropertyContext extends ParserRuleContext {
		public EnumContext enum_() {
			return getRuleContext(EnumContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_property);
		try {
			setState(61);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ENUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				enum_();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				string();
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(60);
				number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EnumContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(AutomateSimParser.ENUM, 0); }
		public List<TerminalNode> VAR() { return getTokens(AutomateSimParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(AutomateSimParser.VAR, i);
		}
		public TerminalNode LIST_START() { return getToken(AutomateSimParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(AutomateSimParser.LIST_END, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AutomateSimParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AutomateSimParser.COMMA, i);
		}
		public EnumContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterEnum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitEnum(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitEnum(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EnumContext enum_() throws RecognitionException {
		EnumContext _localctx = new EnumContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_enum);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(ENUM);
			setState(64);
			match(VAR);
			setState(65);
			match(LIST_START);
			setState(66);
			match(VAR);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(67);
				match(COMMA);
				setState(68);
				match(VAR);
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(74);
			match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(AutomateSimParser.STRING, 0); }
		public TerminalNode VAR() { return getToken(AutomateSimParser.VAR, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(STRING);
			setState(77);
			match(VAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(AutomateSimParser.NUMBER, 0); }
		public TerminalNode VAR() { return getToken(AutomateSimParser.VAR, 0); }
		public TerminalNode LIST_START() { return getToken(AutomateSimParser.LIST_START, 0); }
		public List<TerminalNode> NUM() { return getTokens(AutomateSimParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(AutomateSimParser.NUM, i);
		}
		public TerminalNode COMMA() { return getToken(AutomateSimParser.COMMA, 0); }
		public TerminalNode LIST_END() { return getToken(AutomateSimParser.LIST_END, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(NUMBER);
			setState(80);
			match(VAR);
			setState(81);
			match(LIST_START);
			setState(82);
			match(NUM);
			setState(83);
			match(COMMA);
			setState(84);
			match(NUM);
			setState(85);
			match(LIST_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(AutomateSimParser.VAR, 0); }
		public TerminalNode NUM() { return getToken(AutomateSimParser.NUM, 0); }
		public TerminalNode STR() { return getToken(AutomateSimParser.STR, 0); }
		public ArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgContext arg() throws RecognitionException {
		ArgContext _localctx = new ArgContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_arg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 234881024L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoomContext extends ParserRuleContext {
		public TerminalNode ROOM() { return getToken(AutomateSimParser.ROOM, 0); }
		public TerminalNode VAR() { return getToken(AutomateSimParser.VAR, 0); }
		public TerminalNode DECL_START() { return getToken(AutomateSimParser.DECL_START, 0); }
		public TerminalNode DECL_END() { return getToken(AutomateSimParser.DECL_END, 0); }
		public List<DeviceContext> device() {
			return getRuleContexts(DeviceContext.class);
		}
		public DeviceContext device(int i) {
			return getRuleContext(DeviceContext.class,i);
		}
		public RoomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_room; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterRoom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitRoom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitRoom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoomContext room() throws RecognitionException {
		RoomContext _localctx = new RoomContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_room);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(ROOM);
			setState(90);
			match(VAR);
			setState(91);
			match(DECL_START);
			setState(93); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(92);
				device();
				}
				}
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VAR );
			setState(97);
			match(DECL_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeviceContext extends ParserRuleContext {
		public List<TerminalNode> VAR() { return getTokens(AutomateSimParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(AutomateSimParser.VAR, i);
		}
		public TerminalNode OF() { return getToken(AutomateSimParser.OF, 0); }
		public TerminalNode ARG_START() { return getToken(AutomateSimParser.ARG_START, 0); }
		public List<ArgContext> arg() {
			return getRuleContexts(ArgContext.class);
		}
		public ArgContext arg(int i) {
			return getRuleContext(ArgContext.class,i);
		}
		public TerminalNode ARG_END() { return getToken(AutomateSimParser.ARG_END, 0); }
		public List<TerminalNode> COMMA() { return getTokens(AutomateSimParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AutomateSimParser.COMMA, i);
		}
		public DeviceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_device; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterDevice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitDevice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitDevice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeviceContext device() throws RecognitionException {
		DeviceContext _localctx = new DeviceContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_device);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(VAR);
			setState(100);
			match(OF);
			setState(101);
			match(VAR);
			setState(102);
			match(ARG_START);
			setState(103);
			arg();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(104);
				match(COMMA);
				setState(105);
				arg();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
			match(ARG_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Device_propContext extends ParserRuleContext {
		public List<TerminalNode> VAR() { return getTokens(AutomateSimParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(AutomateSimParser.VAR, i);
		}
		public TerminalNode DOT() { return getToken(AutomateSimParser.DOT, 0); }
		public Device_propContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_device_prop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterDevice_prop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitDevice_prop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitDevice_prop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Device_propContext device_prop() throws RecognitionException {
		Device_propContext _localctx = new Device_propContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_device_prop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(VAR);
			setState(114);
			match(DOT);
			setState(115);
			match(VAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public ForContext for_() {
			return getRuleContext(ForContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_statement);
		try {
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				enterOuterAlt(_localctx, 1);
				{
				setState(117);
				if_();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(118);
				for_();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 3);
				{
				setState(119);
				set();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActionContext extends ParserRuleContext {
		public TerminalNode ACTION() { return getToken(AutomateSimParser.ACTION, 0); }
		public TerminalNode VAR() { return getToken(AutomateSimParser.VAR, 0); }
		public TerminalNode ON() { return getToken(AutomateSimParser.ON, 0); }
		public TerminalNode DECL_START() { return getToken(AutomateSimParser.DECL_START, 0); }
		public TerminalNode DECL_END() { return getToken(AutomateSimParser.DECL_END, 0); }
		public List<Device_propContext> device_prop() {
			return getRuleContexts(Device_propContext.class);
		}
		public Device_propContext device_prop(int i) {
			return getRuleContext(Device_propContext.class,i);
		}
		public TerminalNode LIST_START() { return getToken(AutomateSimParser.LIST_START, 0); }
		public TerminalNode LIST_END() { return getToken(AutomateSimParser.LIST_END, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(AutomateSimParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(AutomateSimParser.COMMA, i);
		}
		public ActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_action; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionContext action() throws RecognitionException {
		ActionContext _localctx = new ActionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_action);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(ACTION);
			setState(123);
			match(VAR);
			setState(124);
			match(ON);
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				{
				setState(125);
				device_prop();
				}
				break;
			case LIST_START:
				{
				setState(126);
				match(LIST_START);
				setState(127);
				device_prop();
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(128);
					match(COMMA);
					setState(129);
					device_prop();
					}
					}
					setState(134);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(135);
				match(LIST_END);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(139);
			match(DECL_START);
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 21504L) != 0)) {
				{
				{
				setState(140);
				statement();
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
			match(DECL_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(AutomateSimParser.IF, 0); }
		public Device_propContext device_prop() {
			return getRuleContext(Device_propContext.class,0);
		}
		public TerminalNode IS() { return getToken(AutomateSimParser.IS, 0); }
		public ArgContext arg() {
			return getRuleContext(ArgContext.class,0);
		}
		public TerminalNode DECL_START() { return getToken(AutomateSimParser.DECL_START, 0); }
		public TerminalNode DECL_END() { return getToken(AutomateSimParser.DECL_END, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfContext if_() throws RecognitionException {
		IfContext _localctx = new IfContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_if);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(IF);
			setState(149);
			device_prop();
			setState(150);
			match(IS);
			setState(151);
			arg();
			setState(152);
			match(DECL_START);
			setState(156);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 21504L) != 0)) {
				{
				{
				setState(153);
				statement();
				}
				}
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(159);
			match(DECL_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(AutomateSimParser.FOR, 0); }
		public List<TerminalNode> VAR() { return getTokens(AutomateSimParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(AutomateSimParser.VAR, i);
		}
		public TerminalNode OF() { return getToken(AutomateSimParser.OF, 0); }
		public TerminalNode IN() { return getToken(AutomateSimParser.IN, 0); }
		public TerminalNode DECL_START() { return getToken(AutomateSimParser.DECL_START, 0); }
		public TerminalNode DECL_END() { return getToken(AutomateSimParser.DECL_END, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ForContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitFor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForContext for_() throws RecognitionException {
		ForContext _localctx = new ForContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_for);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(FOR);
			setState(162);
			match(VAR);
			setState(163);
			match(OF);
			setState(164);
			match(VAR);
			setState(165);
			match(IN);
			setState(166);
			match(VAR);
			setState(167);
			match(DECL_START);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 21504L) != 0)) {
				{
				{
				setState(168);
				statement();
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(174);
			match(DECL_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(AutomateSimParser.SET, 0); }
		public Device_propContext device_prop() {
			return getRuleContext(Device_propContext.class,0);
		}
		public TerminalNode TO() { return getToken(AutomateSimParser.TO, 0); }
		public List<TerminalNode> VAR() { return getTokens(AutomateSimParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(AutomateSimParser.VAR, i);
		}
		public TerminalNode NUM() { return getToken(AutomateSimParser.NUM, 0); }
		public TerminalNode STR() { return getToken(AutomateSimParser.STR, 0); }
		public TerminalNode DOT() { return getToken(AutomateSimParser.DOT, 0); }
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).enterSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomateSimParserListener ) ((AutomateSimParserListener)listener).exitSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomateSimParserVisitor ) return ((AutomateSimParserVisitor<? extends T>)visitor).visitSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(SET);
			setState(177);
			device_prop();
			setState(178);
			match(TO);
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR:
				{
				setState(179);
				match(VAR);
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(180);
					match(DOT);
					setState(181);
					match(VAR);
					}
				}

				}
				break;
			case NUM:
				{
				setState(184);
				match(NUM);
				}
				break;
			case STR:
				{
				setState(185);
				match(STR);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001b\u00bd\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0001\u0000\u0004\u0000\"\b\u0000\u000b\u0000\f\u0000#\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001+\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00021\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0004\u00025\b\u0002\u000b\u0002\f\u00026\u0001"+
		"\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003>\b"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0005\u0004F\b\u0004\n\u0004\f\u0004I\t\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0004\b^\b\b\u000b"+
		"\b\f\b_\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0005\tk\b\t\n\t\f\tn\t\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000by\b\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u0083"+
		"\b\f\n\f\f\f\u0086\t\f\u0001\f\u0001\f\u0003\f\u008a\b\f\u0001\f\u0001"+
		"\f\u0005\f\u008e\b\f\n\f\f\f\u0091\t\f\u0001\f\u0001\f\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u009b\b\r\n\r\f\r\u009e\t\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00aa\b\u000e\n\u000e"+
		"\f\u000e\u00ad\t\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u00b7\b\u000f"+
		"\u0001\u000f\u0001\u000f\u0003\u000f\u00bb\b\u000f\u0001\u000f\u0000\u0000"+
		"\u0010\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e\u0000\u0001\u0001\u0000\u0019\u001b\u00c0\u0000!\u0001"+
		"\u0000\u0000\u0000\u0002*\u0001\u0000\u0000\u0000\u0004,\u0001\u0000\u0000"+
		"\u0000\u0006=\u0001\u0000\u0000\u0000\b?\u0001\u0000\u0000\u0000\nL\u0001"+
		"\u0000\u0000\u0000\fO\u0001\u0000\u0000\u0000\u000eW\u0001\u0000\u0000"+
		"\u0000\u0010Y\u0001\u0000\u0000\u0000\u0012c\u0001\u0000\u0000\u0000\u0014"+
		"q\u0001\u0000\u0000\u0000\u0016x\u0001\u0000\u0000\u0000\u0018z\u0001"+
		"\u0000\u0000\u0000\u001a\u0094\u0001\u0000\u0000\u0000\u001c\u00a1\u0001"+
		"\u0000\u0000\u0000\u001e\u00b0\u0001\u0000\u0000\u0000 \"\u0003\u0002"+
		"\u0001\u0000! \u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#!\u0001"+
		"\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000"+
		"%&\u0005\u0000\u0000\u0001&\u0001\u0001\u0000\u0000\u0000\'+\u0003\u0004"+
		"\u0002\u0000(+\u0003\u0010\b\u0000)+\u0003\u0018\f\u0000*\'\u0001\u0000"+
		"\u0000\u0000*(\u0001\u0000\u0000\u0000*)\u0001\u0000\u0000\u0000+\u0003"+
		"\u0001\u0000\u0000\u0000,-\u0005\u0001\u0000\u0000-0\u0005\u001b\u0000"+
		"\u0000./\u0005\u0002\u0000\u0000/1\u0005\u001b\u0000\u00000.\u0001\u0000"+
		"\u0000\u000001\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000024\u0005"+
		"\u0012\u0000\u000035\u0003\u0006\u0003\u000043\u0001\u0000\u0000\u0000"+
		"56\u0001\u0000\u0000\u000064\u0001\u0000\u0000\u000067\u0001\u0000\u0000"+
		"\u000078\u0001\u0000\u0000\u000089\u0005\u0013\u0000\u00009\u0005\u0001"+
		"\u0000\u0000\u0000:>\u0003\b\u0004\u0000;>\u0003\n\u0005\u0000<>\u0003"+
		"\f\u0006\u0000=:\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000=<\u0001"+
		"\u0000\u0000\u0000>\u0007\u0001\u0000\u0000\u0000?@\u0005\u0003\u0000"+
		"\u0000@A\u0005\u001b\u0000\u0000AB\u0005\u0014\u0000\u0000BG\u0005\u001b"+
		"\u0000\u0000CD\u0005\u0010\u0000\u0000DF\u0005\u001b\u0000\u0000EC\u0001"+
		"\u0000\u0000\u0000FI\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000"+
		"GH\u0001\u0000\u0000\u0000HJ\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000"+
		"\u0000JK\u0005\u0015\u0000\u0000K\t\u0001\u0000\u0000\u0000LM\u0005\u0004"+
		"\u0000\u0000MN\u0005\u001b\u0000\u0000N\u000b\u0001\u0000\u0000\u0000"+
		"OP\u0005\u0005\u0000\u0000PQ\u0005\u001b\u0000\u0000QR\u0005\u0014\u0000"+
		"\u0000RS\u0005\u0019\u0000\u0000ST\u0005\u0010\u0000\u0000TU\u0005\u0019"+
		"\u0000\u0000UV\u0005\u0015\u0000\u0000V\r\u0001\u0000\u0000\u0000WX\u0007"+
		"\u0000\u0000\u0000X\u000f\u0001\u0000\u0000\u0000YZ\u0005\u0006\u0000"+
		"\u0000Z[\u0005\u001b\u0000\u0000[]\u0005\u0012\u0000\u0000\\^\u0003\u0012"+
		"\t\u0000]\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_]\u0001"+
		"\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000"+
		"ab\u0005\u0013\u0000\u0000b\u0011\u0001\u0000\u0000\u0000cd\u0005\u001b"+
		"\u0000\u0000de\u0005\u0007\u0000\u0000ef\u0005\u001b\u0000\u0000fg\u0005"+
		"\u0016\u0000\u0000gl\u0003\u000e\u0007\u0000hi\u0005\u0010\u0000\u0000"+
		"ik\u0003\u000e\u0007\u0000jh\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000"+
		"\u0000lj\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000"+
		"\u0000\u0000nl\u0001\u0000\u0000\u0000op\u0005\u0017\u0000\u0000p\u0013"+
		"\u0001\u0000\u0000\u0000qr\u0005\u001b\u0000\u0000rs\u0005\u0011\u0000"+
		"\u0000st\u0005\u001b\u0000\u0000t\u0015\u0001\u0000\u0000\u0000uy\u0003"+
		"\u001a\r\u0000vy\u0003\u001c\u000e\u0000wy\u0003\u001e\u000f\u0000xu\u0001"+
		"\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000xw\u0001\u0000\u0000\u0000"+
		"y\u0017\u0001\u0000\u0000\u0000z{\u0005\b\u0000\u0000{|\u0005\u001b\u0000"+
		"\u0000|\u0089\u0005\t\u0000\u0000}\u008a\u0003\u0014\n\u0000~\u007f\u0005"+
		"\u0014\u0000\u0000\u007f\u0084\u0003\u0014\n\u0000\u0080\u0081\u0005\u0010"+
		"\u0000\u0000\u0081\u0083\u0003\u0014\n\u0000\u0082\u0080\u0001\u0000\u0000"+
		"\u0000\u0083\u0086\u0001\u0000\u0000\u0000\u0084\u0082\u0001\u0000\u0000"+
		"\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0087\u0001\u0000\u0000"+
		"\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0087\u0088\u0005\u0015\u0000"+
		"\u0000\u0088\u008a\u0001\u0000\u0000\u0000\u0089}\u0001\u0000\u0000\u0000"+
		"\u0089~\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000\u0000\u0000\u008b"+
		"\u008f\u0005\u0012\u0000\u0000\u008c\u008e\u0003\u0016\u000b\u0000\u008d"+
		"\u008c\u0001\u0000\u0000\u0000\u008e\u0091\u0001\u0000\u0000\u0000\u008f"+
		"\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090"+
		"\u0092\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0092"+
		"\u0093\u0005\u0013\u0000\u0000\u0093\u0019\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0005\f\u0000\u0000\u0095\u0096\u0003\u0014\n\u0000\u0096\u0097"+
		"\u0005\r\u0000\u0000\u0097\u0098\u0003\u000e\u0007\u0000\u0098\u009c\u0005"+
		"\u0012\u0000\u0000\u0099\u009b\u0003\u0016\u000b\u0000\u009a\u0099\u0001"+
		"\u0000\u0000\u0000\u009b\u009e\u0001\u0000\u0000\u0000\u009c\u009a\u0001"+
		"\u0000\u0000\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009f\u0001"+
		"\u0000\u0000\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009f\u00a0\u0005"+
		"\u0013\u0000\u0000\u00a0\u001b\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005"+
		"\u000e\u0000\u0000\u00a2\u00a3\u0005\u001b\u0000\u0000\u00a3\u00a4\u0005"+
		"\u0007\u0000\u0000\u00a4\u00a5\u0005\u001b\u0000\u0000\u00a5\u00a6\u0005"+
		"\u000f\u0000\u0000\u00a6\u00a7\u0005\u001b\u0000\u0000\u00a7\u00ab\u0005"+
		"\u0012\u0000\u0000\u00a8\u00aa\u0003\u0016\u000b\u0000\u00a9\u00a8\u0001"+
		"\u0000\u0000\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001"+
		"\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ae\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u00af\u0005"+
		"\u0013\u0000\u0000\u00af\u001d\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005"+
		"\n\u0000\u0000\u00b1\u00b2\u0003\u0014\n\u0000\u00b2\u00ba\u0005\u000b"+
		"\u0000\u0000\u00b3\u00b6\u0005\u001b\u0000\u0000\u00b4\u00b5\u0005\u0011"+
		"\u0000\u0000\u00b5\u00b7\u0005\u001b\u0000\u0000\u00b6\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00bb\u0001\u0000"+
		"\u0000\u0000\u00b8\u00bb\u0005\u0019\u0000\u0000\u00b9\u00bb\u0005\u001a"+
		"\u0000\u0000\u00ba\u00b3\u0001\u0000\u0000\u0000\u00ba\u00b8\u0001\u0000"+
		"\u0000\u0000\u00ba\u00b9\u0001\u0000\u0000\u0000\u00bb\u001f\u0001\u0000"+
		"\u0000\u0000\u0010#*06=G_lx\u0084\u0089\u008f\u009c\u00ab\u00b6\u00ba";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}