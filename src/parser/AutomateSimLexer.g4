lexer grammar AutomateSimLexer;

DECLARATION_TYPES: 'type' | 'device' | 'room' | 'action';
ACTION_START:   'enable' | 'disable';
ADD_REMOVE: 'add' | 'remove';
INSIDE_TYPES: 'Enum' | 'String';

FUNC_START: '{';
FUNC_END: '}';

// TODO: DECLARATION unfinished
DECLARATION: DECLARATION_TYPES TEXT 'on' TEXT FUNC_START -> mode(IN_FUNC_MODE);

ADD_OR_REMOVE: ADD_REMOVE WS TEXT WS 'of'|'from' WS TEXT WS;
ACTION: ACTIONSTART WS TEXT WS;

WS :[ \t\r\n]* -> channel(HIDDEN);

// IDENTIFIER: [a-zA-Z] [a-zA-Z_0-9]*
TEXT: ~[{}\r\n ];

mode IN_FUNC_MODE;
CODE: ~[{}] -> mode(DEFAULT_MODE);
IF_CLAUSE: 'if' TEXT 'is' IN_FUNC_START CODE IN_FUNC_END;

IN_FUNC_START: '{';
IN_FUNC_END: '}';


// unsure about the whitespace whether to incluude \r\n or separate another NAME