;jmp to label at the end?(make last label?)

;82h opcodes - special case?

;gal perkelti paskutini 0 is ds i cs?

;direct - dont print label, if other segment overwritten

.model small
.stack 100h

.data
	include types.asm
	include op_names.asm
	
	include opcodes.asm

	info db 'This is disassembler, made by Donatas Kucinskas.', 13, 10, 'Program requires one argument - file to disassemble.', 13, 10, '$'
	
	outputHeader db ';This file was created with disassembler made by Donatas Kucinskas', 13, 10, '$'
	
	fileErrorM db 'File error$'
	wrongFileFormatM db 'Wrong file format!', 13, 10, '$'
	unexpectedFileEndM db 'Unexpected file end, bad file!', 13, 10, '$'
	
	digit db '0123456789ABCDEF'
	
	newLineM db 13, 10, '$'
	argSeperator db ', $'
	
	;hexPrefix db '0x$'
				   
	;buffer
	bufferL equ 3
	bufferLeft db 0
	buffer db bufferL dup (?), '$'
	curPointer dw 0;in buffer
	pointerPos dw 0;global
	
	;operation's names
	unknownByteM db 'Unknown byte found!', 13, 10, '$' 
	
	;registers
	reg80 db 'AL$'
	reg81 db 'CL$'
	reg82 db 'DL$'
	reg83 db 'BL$'
	reg84 db 'AH$'
	reg85 db 'CH$'
	reg86 db 'DH$'
	reg87 db 'BH$'
	
	reg160 db 'AX$'
	reg161 db 'CX$'
	reg162 db 'DX$'
	reg163 db 'BX$'
	reg164 db 'SP$'
	reg165 db 'BP$'
	reg166 db 'SI$'
	reg167 db 'DI$'
	
	sreg0 db 'ES$'
	sreg1 db 'CS$'
	sreg2 db 'SS$'
	sreg3 db 'DS$'
	
	
	reg8N dw reg80, reg81, reg82, reg83, reg84, reg85, reg86, reg87
	reg16N dw reg160, reg161, reg162, reg163, reg164, reg165, reg166, reg167
	sregN dw sreg0, sreg1, sreg2, sreg3

	
	fileName db 'task.exe', 0
	fileD dw ?;descriptor   
	
	outputFile db 'code.asm', 0
	fileOD dw ?
	
	char db ?
	
	header dw 14 dup(?)
	
	csStart dw ?
	
	
	dsSearch db 0
	dsPosition dw ?;where can I find dsStart
	dsStart dw 0
	dsFound db 0
	
	;opcodesStart dw ?
	
	;bufferL equ 5
   
	modelM db '.MODEL SMALL$'
	stackInitM db '.STACK $'
	codeSM db '.CODE$'
	dataSM db '.DATA$'
	;dbM db 'dataBytes db $'
	dbM db ' db $'
	
	progEndM db 'END$'
	
	;addressing format
	rm000 db 'BX + SI$'
	rm001 db 'BX + DI$'
	rm010 db 'BP + SI$'
	rm011 db 'BP + DI$'
	rm100 db 'SI$'
	rm101 db 'DI$'
	rm110 db 'BP$'
	rm111 db 'BX$'
	
	rmFormat dw rm000, rm001, rm010, rm011, rm100, rm101, rm110, rm111
	
	w db ?
	
	
	
	dsWord db 0;if currently read word is @data
	dsArgM db '@DATA$'
	;adressing byte
	;adrB db ?
	curInstrNr dw ?
	
	isAdrB db ?
	aMod db ?
	aReg db ?
	aRM db ?
	
	
	emptyLine db ?
	
	
	last0M db 'last0 db 0$';the last 0 in code segment before data segment, need to be described
	
	
	;segment register overWrite
	;isSRegO db ?
	sRegO db 0
	
	groupM db mADD, mOR, mADC, mSBC, mAND, mSUB, mXOR, mCMP,		mROL, mROR, mRCL, mRCR, mSHL, mSHR, mUNKNOWN, mSAR,			mUNKNOWN, mUNKNOWN, mNOT, mNEG, mMUL, mIMUL, mDIV, mIDIV,		mINC, mDEC, mCALL, mCALL, mJMP, mJMP, mPUSH, mUNKNOWN
	
	cByteM db 'byte ptr$'
	cWordM db 'word ptr$'
	
	sizeM dw cByteM, cWordM
	
.code
	;get psp
	mov ah, 62h
	int 21h
	mov ds, bx

	;add 0 at the end
	mov bx, 81h
	add bl, byte ptr [ds:80h]
	mov [ds:bx], byte ptr 0
	
	;open file
	mov ah, 3dh			
	mov al, 0h
	mov dx, 82h
	int 21h		 
	
	;get ds
	mov bx, @data
	mov ds, bx
	
	jc fileError
	jmp continueHeader
;-------------------------------
fileError:
	lea dx, fileErrorM
	call printStringScreen
	jmp exit
wrongFileFormat:
	lea dx, wrongFileFormatM
	call printStringScreen
	jmp exit
unexpectedFileEnd:
	lea dx, unexpectedFileEndM
	call printStringScreen
	jmp exit
;-------------------------------
continueHeader:	
	mov [fileD], ax
	
	;open output file
	mov ah, 3Ch
	lea dx, outputFile
	mov cx, 0
	int 21h
	mov [fileOD], ax
	
	;read header
	mov bh, 0
	mov cx, 14
	readHeaderPart:
		;offset
		mov ax, 14
		sub ax, cx
		
		mov bl, 2
		mul bl
		mov dx, ax
		
		call getWord
		cmp bl, 0
		jne unexpectedFileEnd
		
		mov bx, dx
		mov [header+bx], ax
	loop readHeaderPart
	
	cmp word ptr [header], 'ZM'
	jne wrongFileFormat
	
	mov ax, word ptr [header + 8h]
	mov cl, 4
	shl ax, cl
	mov [csStart], ax
	
	lea dx, [outputHeader]
	call printString
	
	lea dx, modelM
	call printString
	call printNewLine
	
	lea dx, stackInitM
	call printString
	
	mov ax, word ptr [header + 10h]
	call printWord
	call printNewLine
	
	lea dx, codeSM
	call printString
	call printNewLine
	
	mov cx, [header + 18h]
	call skipBytes
	
	call getWord
	cmp ax, 0
	jne insertDSPosition
	mov ax, 0FFFFh
	jmp addDSPosition
insertDSPosition:
	add ax, word ptr [csStart]
addDSPosition:
	mov [dsPosition], ax
	
	mov cx, [csStart]
	call skipBytes
	
	mov ax, dsStart
	
	mov [dsSearch], 1
	
	jmp iterate
;-------------------------------
skipBytes:
	cmp word ptr [pointerPos], cx
	jge endSkipping
	call getByte
	jmp skipBytes
endSkipping:
ret
;-------------------------------	
iterate:
	;get current instruction start byte
	call getStartByte
	mov [curInstrNr], ax

	;get first byte
	call checkIfEnd
	;call getByte
	cmp bl, 0
	jne iterateFinished
	
	;call printByte
	;call printSpace

	mov [isAdrB], 0
	;mov [isSRegO], 0
	
	;find opcode
	mov ah, 0
	mov bl, opcodeSize
	mul bl
	mov si, ax
	
	;opcodes+si - mnemonic
	mov al, [opcodes + si]
	cmp al, mUNKNOWN
	je unknownByte
	;jmp unknownByte
	call printTitle



	
	;first argument
	mov al, byte ptr [opcodes + si + 1]
	cmp al, argNone
	je iterateEnd
	call printSpace
	call printArg
	
	;second argument
	mov al, byte ptr [opcodes + si + 2]
	cmp al, argNone
	je iterateEnd
	lea dx, argSeperator
	call printString
	call printArg
iterateEnd:
	cmp [emptyLine], 0
	jne noNewLineNeeded
	call 	printNewLine
noNewLineNeeded:	
	jmp iterate 
	;-------------------------------
	unknownByte:
		lea dx, unknownByteM
		call printStringScreen
	jmp iterateEnd
	;-------------------------------
iterateFinished:

	lea dx, dataSM
	call printString
	call printNewLine

	mov cx, 0
	
printDataByte:
	call getByte
	cmp bl, 1
	je dataEnd
	
	;mov ax, 0
	call printDSTitle
	lea dx, [dbM]
	call printString
	
	call printByte
	call printNewLine
	
	inc cx
	jmp printDataByte
dataEnd:
	call printNewLine
	mov dx, 12
	
programEnd:
	lea dx, progEndM
	call printString
	call printNewLine
	mov al, 0
	jmp exit
printArg:;in al
	cmp al, argDS
	jle printConstReg
	
	cmp al, arg1
	je print1
	cmp al, arg3
	je print3
	
	cmp al, argImm8
	je printArgImm8
	cmp al, argImm16
	je printArgImm16
	
	cmp al, argReg8
	je printReg8
	cmp al, argReg16
	je printReg16

	cmp al, argRegMem8
	je printRegMem8
	cmp al, argRegMem16
	je printRegMem16
	
	cmp al, argMem8
	je printMem8
	cmp al, argMem16
	je printMem16
	
	cmp al, argMemDirect8
	je printMemDirect8
	cmp al, argMemDirect16
	je printMemDirect16
	

	cmp al, argSegReg
	je printSegReg
	
	cmp al, argShort
	je printShort
	
	cmp al, argNear
	je printNear
	
	cmp al, argFar
	je printFar
	
	
	;jmp printArgEnd
printArgEnd:
ret
;-------------------------------
printConstReg:
	sub al, argAL
	call printRegLabel
jmp printArgEnd
;-------------------------------
print1:
	mov dl, '1'
	call printChar
jmp printArgEnd

print3:
	mov dl, '3'
	call printChar
jmp printArgEnd
;-------------------------------
printArgImm8:
	call printInputByte
jmp printArgEnd

printArgImm16:
	call printArgImm16C
jmp printArgEnd
;-------------------------------
printReg8:
	mov [w], 0
	call printReg
jmp printArgEnd

printReg16:
	mov [w], 1
	call printReg
jmp printArgEnd
;-------------------------------
printRegMem8:
	mov [w], 0
	call printRegMem
jmp printArgEnd

printRegMem16:
	mov [w], 1
	call printRegMem
jmp printArgEnd
;-------------------------------
printMem8:
	jmp printRegMem8
jmp printArgEnd

printMem16:
	jmp printRegMem16

jmp printArgEnd
;-------------------------------
printMemDirect8:
	mov [w], 0
	call printMemDirect
jmp printArgEnd

printMemDirect16:
	mov [w], 1
	call printMemDirect
jmp printArgEnd
;-------------------------------
printSegReg:
	call getReg
	call printSegRegLabel
jmp printArgEnd
;-------------------------------
printShort:
	call getByte
	cbw
	call generateLabel
jmp printArgEnd

printNear:
	call getWord
	call generateLabel
	
jmp printArgEnd

printFar:
	;CHECK, NO EXAMPLE!!!
	call printInputWord
	call printInputWord

jmp printArgEnd
;-------------------------------
getMod:
	call getAdrB
	mov al, [aMod]
ret
getReg:
	call getAdrB
	mov al, [aReg]
ret
getRM:
	call getAdrB
	mov al, [aRM]
ret
;-------------------------------
calcAdrB:
	push bx
	push cx
	
	;r/m
	mov bl, al
	and bl, 111b
	mov [aRM], bl
	
	;reg
	mov bl, al
	mov cl, 3
	shr bl, cl
	and bl, 111b
	mov [aReg], bl
	
	;mod
	mov bl, al
	mov cl, 6
	shr bl, cl
	and bl, 111b
	mov [aMod], bl
	
	pop cx
	pop bx	
	
	mov [isAdrB], 1
ret
;-------------------------------
makeFakeAdrB:;in al
	call calcAdrB
ret
;-------------------------------
getAdrB:
	cmp [isAdrB], 0
	jne hasAdrB
	
	call getByte
	call calcAdrB
hasAdrB:
ret
;-------------------------------
printRegMem:
	call getMod
	
	cmp al, 11b
	je printMod11
	
	;jmp printModNot11
printModNot11:
	
	mov bl, [w]
	mov bh, 0
	shl bl, 1
	mov dx, [sizeM + bx]
	call printString
	call printSpace
	
	call checkSRegO
	
	;cmp [SRegO], 0
	;je printModNot11Continue
	;
	;;segment overWrite instruction
	;
	;mov bl, [sRegO]
	;mov bh, 0
	;dec bx
	;shl bx, 1
	;mov dx, [sregN + bx]
	;mov [sRegO], 0
	
;printModNot11Continue:	
	mov dl, '['
	call printChar
	
	xchg al, ah
	call getRM
	xchg al, ah
	
	cmp al, 00b
	jne printMemContinue
	cmp ah, 110b
	jne printMemContinue
	
	jmp printMemOffsetLabel
printMemContinue:
	shl ah, 1
	
	mov bl, ah
	mov bh, 0
	mov dx, [rmFormat + bx]
	call printString
	
	cmp al, 0
	je printMemEnd
	
	call printSpace
	mov dl, '+'
	call printChar
	call printSpace
	
jmp printMemOffset

printMod11:
	call getRM
	call addWidthBit
	call printRegLabel
ret
;-------------------------------
printReg:
	call getReg
	call addWidthBit
	
	
	call printRegLabel
ret
;-------------------------------
printMemOffsetLabel:
	call getWord
	mov cx, ax
	call printDSTitle
	;call printWord
jmp printMemEnd

printMemOffset:
	call getMod
	cmp al, 10b
	je off2
	;off1
	call printInputByte
jmp printMemEnd
off2:
	call printInputWord
jmp printMemEnd
;-------------------------------
printInputByte:
	call getByte
	call printByte
ret
printInputWord:
	call getWord
	call printWord
ret
;-------------------------------
printMemEnd:
	
	mov dl, ']'
	call printChar
ret



printDirectAdress:
	call getWord
	call printWord
	
	mov dl, ']'
	call printChar	
ret


printTitle:
	cmp al, g1
	jb checkTitle
	
	;group, find mnemonic
	mov bx, ax
	sub bl, g1
	mov cl, 3
	shl bl, cl	
	
	push bx
	call getReg
	pop bx
	add bl, al
	
	mov bh, 0
	mov al, byte ptr [groupM + bx]
checkTitle:	
	mov [emptyLine], 0
	
	cmp al, mSREGES
	jb outputTitle
	
	cmp al, mSREGDS
	ja outputTitle
	
	;segment overWrite instruction
	mov [emptyLine], 1
	
	
	sub al, mSREGES
	inc al
	mov sRegO, al
	
	
ret
outputTitle:
	;print label
	mov dx, 'L'
	call printChar
	push ax
	mov ax, [curInstrNr]
	
	cmp [sRegO], 0
	je noOverWrite
	dec ax;because 1 byte is overwrite prefix
noOverWrite:
	
	call printWordNumber
	pop ax
	mov dx, ':'
	call printChar
	mov dx, ' '
	call printChar
	
	
	sub al, 1
	mov ah, 0
	shl al, 1
	mov bx, ax
	mov dx, [mnemonics + bx]
	call printString
ret

;-------------------------------
printMemDirect:
	mov al, 00000110b
	call makeFakeAdrB
	call printRegMem
ret
;-------------------------------
addWidthBit:
	push bx
	push cx
	mov bl, [w]
	mov cl, 3
	shl bl, cl
	or al, bl
	pop cx
	pop bx
ret
;-------------------------------
generateLabel:;in ax
	mov dl, 'L'
	call printChar
	
	mov bx, ax
	call getStartByte
	add ax, bx
	call printWordNumber
ret
;-------------------------------
printRegLabel:
	mov bl, al
	shl bl, 1
	mov bh, 0
	mov dx, [reg8N + bx]
	call printString
ret
printSegRegLabel:
	mov bl, al
	shl bl, 1
	mov bh, 0
	mov dx, [sregN + bx]
	call printString
ret
;-------------------------------
getByte:
	cmp [bufferLeft], 0
	jg .returnByte;buffer not empty
	
	;read to buffer
	push cx
	push dx
	push ax
	mov ah, 3Fh
	mov bx, fileD 
	mov cx, bufferL
	lea dx, buffer
	int 21h
	
	mov cl, al
	pop ax
	mov al, cl
	pop dx
	pop cx
	;mov al, 0	
	
	jc .fileEnd;error
	
	cmp al, 0
	je .fileEnd
	mov [bufferLeft], al
	
	mov curPointer, 0
	;check		  
	
.returnByte:
	lea bx, buffer
	add bx, [curPointer]
	mov al, [bx]
	
	
	
	cmp [dsSearch], 0
	je skipDSTest
	;check if it is ds position
	push ax
	push bx
	
	mov bl, al
	
	mov ax, word ptr [dsPosition]
	cmp [pointerPos], ax
	je firstDSByte
	
	inc ax
	cmp [pointerPos], ax
	je secondDSByte
	jmp noneDSByte
secondDSByte:
	mov byte ptr [dsStart + 1], bl	
	mov [dsFound], 1
	mov [dsWord], 1
	jmp noneDSByte
firstDSByte:
	mov byte ptr [dsStart], bl
noneDSByte:	
	pop bx
	pop ax
skipDSTest:
	
	
	
	dec bufferLeft
	inc word ptr [curPointer]
	inc word ptr [pointerPos]
	
	
	mov bl, 0
ret
.fileEnd:
	mov bl, 1;end
	;jmp exit
ret
;-------------------------------
getWord:
	call getByte
	cmp bl, 0
	jne .getWordError
	mov ah, al
	call getByte
	cmp bl, 0
	jne .getWordError
	xchg ah, al
ret
.getWordError:
	mov bl, 1
ret
;-------------------------------
getStringLength:
	push bx
	mov bx, dx
	
	loopLength:
		cmp byte ptr [bx], '$'
		je finishedSearch
		
		inc bx
	jmp loopLength
	
finishedSearch:
	sub bx, dx
	mov cx, bx
	pop bx
ret
;-------------------------------
printStringScreen:
	push ax
	mov ah, 9
	int 21h
	pop ax
ret
;-------------------------------
printString:
	push ax
	push bx
	push cx
	
	mov ah, 40h
	mov bx, [fileOD]
	call getStringLength
	int 21h
	
	pop cx
	pop bx
	pop ax
ret
;-------------------------------
printSpace:
	push dx
	mov dl, ' '
	call printChar
	pop dx
ret
;-------------------------------
printNewLine:
	push dx
	lea dx, newLineM
	call printString
	pop dx
ret
;-------------------------------
printByteNumber:
	push ax
	push bx
	push cx
	push dx
	
	mov ah, 0
	mov bl, 16
	div bl
	
	mov bh, 0
	
	mov bl, al
	mov dl, [digit + bx]
	call printChar
	
	mov bl, ah
	mov dl, [digit + bx]
	call printChar
	
	pop dx
	pop cx
	pop bx
	pop ax
ret
;-------------------------------
printByte:
	mov dx, '0'
	call printChar
	call printByteNumber
	mov dx, 'h'
	call printChar
ret
;-------------------------------
printWordNumber:
	push ax
	push bx
	mov bl, al
	mov al, ah
	call PrintByteNumber
	mov al, bl
	call PrintByteNumber
	pop bx
	pop ax
ret
;-------------------------------
printWord:
	;MOV word ptr [00D0], FED4
	;MOV word ptr fileOD, word ptr -300

	mov dx, '0'
	call printChar
	call printWordNumber
	mov dx, 'h'
	call printChar
ret
;-------------------------------
printChar:;dl
	push ax
	push bx
	push cx
	push dx
	
	mov ah, 40h
	mov bx, [fileOD]
	mov cx, 1
	mov [char], dl
	lea dx, [char]
	int 21h
	
	pop dx
	pop cx
	pop bx
	pop ax
ret
;-------------------------------
checkSRegO:
	cmp sRegO, 0
	je checkSRegONone
	
	;print
	mov bl, [sRegO]
	dec bl
	shl bl, 1
	mov bh, 0
	mov [sRegO], 0
	
	mov dx, word ptr [sregN + bx]
	call printString
	
	mov dx, ':'
	call printChar
	
checkSRegONone:
ret
;-------------------------------
getStartByte:
	push bx
	push cx
	
	mov ax, [pointerPos]
	mov bx, [header + 8h]
	mov cl, 4
	shl bx, cl
	sub ax, bx
	
	pop cx
	pop bx
ret
;-------------------------------
checkIfBytesEnd:
	
	cmp byte ptr [dsFound], 0
	je bytesNotEnd
	;cmp word ptr [dsStart], 0
	;je bytesNotEnd
	
	push ax
	
	call getStartByte
	;mov ax, [dsStart]
	mov bx, [dsStart]
	mov cl, 4
	shl bx, cl
	cmp ax, bx
	
	
	pop ax
	jb bytesNotEnd
	
	;end
	mov bl, 1
	ret
bytesNotEnd:
	mov bl, 0
ret
;-------------------------------
printArgImm16C:
	call getWord
	cmp [dsWord], 1
	je printDataSeg
	call printWord
ret
	printDataSeg:
		lea dx, [dsArgM]
		call printString
		mov [dsWord], 0
	ret
;-------------------------------
checkIfEnd:
	call checkIfBytesEnd
	cmp bl, 1
	je endNow
	
	
	call getByte
	cmp bl, 1
	je endNow
	
	
	
	call checkIfBytesEnd
	cmp bl, 1
	jne fineEnd
	cmp al, 0
	jne fineEnd
	
	jmp endRead
	
fineEnd:
	mov bl, 0
ret
endRead:
	lea dx, [last0M]
	call printString
	call printNewLine
endNow:
	mov bl, 1
ret
	
notEnd:
	mov bl, 0
ret
;-------------------------------
printDSTitle:
	push dx
	mov dx, 'd'
	call printChar
	pop dx
	xchg cx, ax
	call printWordNumber
	xchg cx, ax
ret
;-------------------------------
exit:	
	mov ah, 4Ch
	int 21h
	
END