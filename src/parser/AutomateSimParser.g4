parser grammar AutomateSimParser;
options { tokenVocab=AutomateSimLexer; }

program: decl+ EOF;
decl: type | room | action;

type: TYPE VAR (INHERITS VAR)? DECL_START property+ DECL_END;
property: enum | string | number;

enum: ENUM VAR LIST_START VAR (',' VAR)* LIST_END;
string: STRING VAR;
number: NUMBER VAR LIST_START NUM ',' NUM LIST_END;

arg: VAR | NUM | STR;

room: ROOM VAR DECL_START device+ DECL_END;
device: VAR OF VAR ARG_START arg (',' arg)* ARG_END;

device_prop: VAR DOT VAR;
statement: if | for | set;
action: ACTION VAR ON (device_prop | LIST_START device_prop (',' device_prop)* LIST_END)
        DECL_START statement* DECL_END;
if: IF device_prop IS arg DECL_START statement* DECL_END;
for: FOR VAR OF VAR IN VAR DECL_START statement* DECL_END;
set: SET device_prop TO arg;