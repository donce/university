run: disasm.exe test.exe
	disasm.exe test.exe
disasm.exe: disasm.asm types.asm op_names.asm opcodes.asm
	@tasm disasm.asm
	@tlink disasm.obj
	@rm disasm.obj
	@rm disasm.map
test.exe: test.asm
	@tasm test.asm
	@tlink test.obj	
	@rm test.obj
	@rm test.map
code.exe: code.asm
	@tasm code.asm
	@tlink code.obj
	@rm code.obj
	@rm code.map
