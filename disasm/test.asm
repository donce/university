.model small
.stack 100h

.data
	;message db 'Hello, world!!!$'
.code
	es:movsb
	;mov ax, @data
	;mov ds, ax
	;mov word ptr cs:[bx + 10], es
	;
	;;mov ah, 9
	;;lea dx, [message]
	;;int 21h
	;
	;mov ah, 4ch
	;int 21h
END