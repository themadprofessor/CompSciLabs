grammar Calc; 

// Programs

prog
	:	com* EOF
	;

// Commands

com
	:	PUT expr EOL         # put
	|	SET var ASSN expr EOL # set
	;

// Expressions

expr
	:	prim (operator+=(PLUS | MINUS | TIMES | DIV) prim)* # op
	;

prim		        
	:	NUM                  # num
	|	ID                   # id
	|	LPAR expr RPAR       # parens
	;
	
var
	:	ID
	;

// Lexicon

PUT	:	'put' ;
SET	:	'set' ;

ASSN	:	'=' ;
PLUS	:	'+' ;
MINUS	:	'-' ;
TIMES	:	'*' ;
DIV     :   '/' ;
LPAR	:	'(' ;
RPAR	:	')' ;

ID	:	'a'..'z'+ ;
NUM	:	'0'..'9'+ ;

EOL	:	'\r'? '\n' ;

SPACE	:	(' ' | '\t')+  -> skip ;
COMMENT :   '//' ~('\n')* '\n' -> skip ;
