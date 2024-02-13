lexer grammar AutomateSimLexer;

TYPE: 'type';
INHERITS: 'inherits';
ENUM: 'enum';
STRING: 'string';
NUMBER: 'number';

ROOM: 'room';
OF: 'of';

ACTION: 'action';
ON: 'on';

SET: 'set';
TO: 'to';

IF: 'if';
IS: 'is';
FOR: 'for';
IN: 'in';

COMMA: ',';
DOT: '.';

DECL_START: '{';
DECL_END: '}';

LIST_START: '[';
LIST_END: ']';

ARG_START: '(';
ARG_END: ')';

WS: [ \t\r\n] -> channel(HIDDEN);
NUM: [0-9]+;
STR: '"' ~[\r\n"]* '"';
VAR: ~[()[\]{},.\r\n" ]+;