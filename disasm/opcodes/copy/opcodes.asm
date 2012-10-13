;mnemonics
mPOPF db 'POPF$'
mJZ db 'JZ$'
mSCASW db 'SCASW$'
mJS db 'JS$'
mJL db 'JL$'
mJO db 'JO$'
mJG db 'JG$'
mJA db 'JA$'
mJB db 'JB$'
mSAHF db 'SAHF$'
mRETF db 'RETF$'
mRETN db 'RETN$'
mREPNE db 'REPNE$'
mLAHF db 'LAHF$'
mJAE db 'JAE$'
mJNS db 'JNS$'
mJNZ db 'JNZ$'
mCMPSB db 'CMPSB$'
mJNO db 'JNO$'
mOR db 'OR$'
mLOOP db 'LOOP$'
mCMC db 'CMC$'
mSEGCS db 'SEGCS$'
mDEC db 'DEC$'
mCMP db 'CMP$'
mJMP db 'JMP$'
mSCASB db 'SCASB$'
mLDS db 'LDS$'
mLES db 'LES$'
mXLAT db 'XLAT$'
mXCHG db 'XCHG$'
mLOOPNZ db 'LOOPNZ$'
mSEGSS db 'SEGSS$'
mMOVSB db 'MOVSB$'
mJGE db 'JGE$'
mMOVSW db 'MOVSW$'
mAND db 'AND$'
mINT db 'INT$'
mHLT db 'HLT$'
mMOV db 'MOV$'
mJLE db 'JLE$'
mCBW db 'CBW$'
mPUSHF db 'PUSHF$'
mNOP db 'NOP$'
mINC db 'INC$'
mSEGES db 'SEGES$'
mSUB db 'SUB$'
mCMPSW db 'CMPSW$'
mREP db 'REP$'
mJBE db 'JBE$'
mPUSH db 'PUSH$'
mOUT db 'OUT$'
mCLI db 'CLI$'
mCLD db 'CLD$'
mLOCK db 'LOCK$'
mIRET db 'IRET$'
mCLC db 'CLC$'
mADD db 'ADD$'
mADC db 'ADC$'
mWAIT db 'WAIT$'
mLEA db 'LEA$'
mSBB db 'SBB$'
mJCXZ db 'JCXZ$'
mAAD db 'AAD$'
mAAA db 'AAA$'
mLODSW db 'LODSW$'
mAAM db 'AAM$'
mLODSB db 'LODSB$'
mAAS db 'AAS$'
mDAA db 'DAA$'
mSTOSB db 'STOSB$'
mPOP db 'POP$'
mLOOPZ db 'LOOPZ$'
mSTOSW db 'STOSW$'
mDAS db 'DAS$'
mSTD db 'STD$'
mSEGDS db 'SEGDS$'
mXOR db 'XOR$'
mSTC db 'STC$'
mINTO db 'INTO$'
mSTI db 'STI$'
mIN db 'IN$'
mCWD db 'CWD$'
mJPO db 'JPO$'
mJPE db 'JPE$'
mCALL db 'CALL$'
mTEST db 'TEST$'

;opcodes
opcodes dw mADD
db argRegMem8,argReg8,kNormal
dw mADD
db argRegMem16,argReg16,kNormal
dw mADD
db argReg8,argRegMem8,kNormal
dw mADD
db argReg16,argRegMem16,kNormal
dw mADD
db argRegAL,argImm8,kNormal
dw mADD
db argRegAX,argImm16,kNormal
dw mPUSH
db argRegES,argNone,kNormal
dw mPOP
db argRegES,argNone,kNormal
dw mOR
db argRegMem8,argReg8,kNormal
dw mOR
db argRegMem16,argReg16,kNormal
dw mOR
db argReg8,argRegMem8,kNormal
dw mOR
db argReg16,argRegMem16,kNormal
dw mOR
db argRegAL,argImm8,kNormal
dw mOR
db argRegAX,argImm16,kNormal
dw mPUSH
db argRegCS,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw mADC
db argRegMem8,argReg8,kNormal
dw mADC
db argRegMem16,argReg16,kNormal
dw mADC
db argReg8,argRegMem8,kNormal
dw mADC
db argReg16,argRegMem16,kNormal
dw mADC
db argRegAL,argImm8,kNormal
dw mADC
db argRegAX,argImm16,kNormal
dw mPUSH
db argRegSS,argNone,kNormal
dw mPOP
db argRegSS,argNone,kNormal
dw mSBB
db argRegMem8,argReg8,kNormal
dw mSBB
db argRegMem16,argReg16,kNormal
dw mSBB
db argReg8,argRegMem8,kNormal
dw mSBB
db argReg16,argRegMem16,kNormal
dw mSBB
db argRegAL,argImm8,kNormal
dw mSBB
db argRegAX,argImm16,kNormal
dw mPUSH
db argRegDS,argNone,kNormal
dw mPOP
db argRegDS,argNone,kNormal
dw mAND
db argRegMem8,argReg8,kNormal
dw mAND
db argRegMem16,argReg16,kNormal
dw mAND
db argReg8,argRegMem8,kNormal
dw mAND
db argReg16,argRegMem16,kNormal
dw mAND
db argRegAL,argImm8,kNormal
dw mAND
db argRegAX,argImm16,kNormal
dw mSEGES
db argNone,argNone,kPrefix
dw mDAA
db argNone,argNone,kNormal
dw mSUB
db argRegMem8,argReg8,kNormal
dw mSUB
db argRegMem16,argReg16,kNormal
dw mSUB
db argReg8,argRegMem8,kNormal
dw mSUB
db argReg16,argRegMem16,kNormal
dw mSUB
db argRegAL,argImm8,kNormal
dw mSUB
db argRegAX,argImm16,kNormal
dw mSEGCS
db argNone,argNone,kPrefix
dw mDAS
db argNone,argNone,kNormal
dw mXOR
db argRegMem8,argReg8,kNormal
dw mXOR
db argRegMem16,argReg16,kNormal
dw mXOR
db argReg8,argRegMem8,kNormal
dw mXOR
db argReg16,argRegMem16,kNormal
dw mXOR
db argRegAL,argImm8,kNormal
dw mXOR
db argRegAX,argImm16,kNormal
dw mSEGSS
db argNone,argNone,kPrefix
dw mAAA
db argNone,argNone,kNormal
dw mCMP
db argRegMem8,argReg8,kNormal
dw mCMP
db argRegMem16,argReg16,kNormal
dw mCMP
db argReg8,argRegMem8,kNormal
dw mCMP
db argReg16,argRegMem16,kNormal
dw mCMP
db argRegAL,argImm8,kNormal
dw mCMP
db argRegAX,argImm16,kNormal
dw mSEGDS
db argNone,argNone,kPrefix
dw mAAS
db argNone,argNone,kNormal
dw mINC
db argRegAX,argNone,kNormal
dw mINC
db argRegCX,argNone,kNormal
dw mINC
db argRegDX,argNone,kNormal
dw mINC
db argRegBX,argNone,kNormal
dw mINC
db argRegSP,argNone,kNormal
dw mINC
db argRegBP,argNone,kNormal
dw mINC
db argRegSI,argNone,kNormal
dw mINC
db argRegDI,argNone,kNormal
dw mDEC
db argRegAX,argNone,kNormal
dw mDEC
db argRegCX,argNone,kNormal
dw mDEC
db argRegDX,argNone,kNormal
dw mDEC
db argRegBX,argNone,kNormal
dw mDEC
db argRegSP,argNone,kNormal
dw mDEC
db argRegBP,argNone,kNormal
dw mDEC
db argRegSI,argNone,kNormal
dw mDEC
db argRegDI,argNone,kNormal
dw mPUSH
db argRegAX,argNone,kNormal
dw mPUSH
db argRegCX,argNone,kNormal
dw mPUSH
db argRegDX,argNone,kNormal
dw mPUSH
db argRegBX,argNone,kNormal
dw mPUSH
db argRegSP,argNone,kNormal
dw mPUSH
db argRegBP,argNone,kNormal
dw mPUSH
db argRegSI,argNone,kNormal
dw mPUSH
db argRegDI,argNone,kNormal
dw mPOP
db argRegAX,argNone,kNormal
dw mPOP
db argRegCX,argNone,kNormal
dw mPOP
db argRegDX,argNone,kNormal
dw mPOP
db argRegBX,argNone,kNormal
dw mPOP
db argRegSP,argNone,kNormal
dw mPOP
db argRegBP,argNone,kNormal
dw mPOP
db argRegSI,argNone,kNormal
dw mPOP
db argRegDI,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw mJO
db argShort,argNone,kNormal
dw mJNO
db argShort,argNone,kNormal
dw mJB
db argShort,argNone,kNormal
dw mJAE
db argShort,argNone,kNormal
dw mJZ
db argShort,argNone,kNormal
dw mJNZ
db argShort,argNone,kNormal
dw mJBE
db argShort,argNone,kNormal
dw mJA
db argShort,argNone,kNormal
dw mJS
db argShort,argNone,kNormal
dw mJNS
db argShort,argNone,kNormal
dw mJPE
db argShort,argNone,kNormal
dw mJPO
db argShort,argNone,kNormal
dw mJL
db argShort,argNone,kNormal
dw mJGE
db argShort,argNone,kNormal
dw mJLE
db argShort,argNone,kNormal
dw mJG
db argShort,argNone,kNormal
dw 0
db argRegMem8,argImm8,kArOp
dw 0
db argRegMem16,argImm16,kArOp
dw 0
db argNone,argNone,kNormal
dw 0
db argRegMem16,argSImm8,kArOp
dw mTEST
db argRegMem8,argReg8,kNormal
dw mTEST
db argRegMem16,argReg16,kNormal
dw mXCHG
db argReg8,argRegMem8,kNormal
dw mXCHG
db argReg16,argRegMem16,kNormal
dw mMOV
db argRegMem8,argReg8,kNormal
dw mMOV
db argRegMem16,argReg16,kNormal
dw mMOV
db argReg8,argRegMem8,kNormal
dw mMOV
db argReg16,argRegMem16,kNormal
dw mMOV
db argRegMem16,argSegReg,kNormal
dw mLEA
db argReg16,argMem8,kNormal
dw mMOV
db argSegReg,argRegMem16,kNormal
dw mPOP
db argRegMem16,argNone,kNormal
dw mNOP
db argNone,argNone,kNormal
dw mXCHG
db argRegAX,argRegCX,kNormal
dw mXCHG
db argRegAX,argRegDX,kNormal
dw mXCHG
db argRegAX,argRegBX,kNormal
dw mXCHG
db argRegAX,argRegSP,kNormal
dw mXCHG
db argRegAX,argRegBP,kNormal
dw mXCHG
db argRegAX,argRegSI,kNormal
dw mXCHG
db argRegAX,argRegDI,kNormal
dw mCBW
db argNone,argNone,kNormal
dw mCWD
db argNone,argNone,kNormal
dw mCALL
db argFar,argNone,kNormal
dw mWAIT
db argNone,argNone,kNormal
dw mPUSHF
db argNone,argNone,kNormal
dw mPOPF
db argNone,argNone,kNormal
dw mSAHF
db argNone,argNone,kNormal
dw mLAHF
db argNone,argNone,kNormal
dw mMOV
db argRegAL,argMemOffs8,kNormal
dw mMOV
db argRegAX,argMemOffs16,kNormal
dw mMOV
db argMemOffs8,argRegAL,kNormal
dw mMOV
db argMemOffs16,argRegAX,kNormal
dw mMOVSB
db argNone,argNone,kNormal
dw mMOVSW
db argNone,argNone,kNormal
dw mCMPSB
db argNone,argNone,kNormal
dw mCMPSW
db argNone,argNone,kNormal
dw mTEST
db argRegAL,argImm8,kNormal
dw mTEST
db argRegAX,argImm16,kNormal
dw mSTOSB
db argNone,argNone,kNormal
dw mSTOSW
db argNone,argNone,kNormal
dw mLODSB
db argNone,argNone,kNormal
dw mLODSW
db argNone,argNone,kNormal
dw mSCASB
db argNone,argNone,kNormal
dw mSCASW
db argNone,argNone,kNormal
dw mMOV
db argRegAL,argImm8,kNormal
dw mMOV
db argRegCL,argImm8,kNormal
dw mMOV
db argRegDL,argImm8,kNormal
dw mMOV
db argRegBL,argImm8,kNormal
dw mMOV
db argRegAH,argImm8,kNormal
dw mMOV
db argRegCH,argImm8,kNormal
dw mMOV
db argRegDH,argImm8,kNormal
dw mMOV
db argRegBH,argImm8,kNormal
dw mMOV
db argRegAX,argImm16,kNormal
dw mMOV
db argRegCX,argImm16,kNormal
dw mMOV
db argRegDX,argImm16,kNormal
dw mMOV
db argRegBX,argImm16,kNormal
dw mMOV
db argRegSP,argImm16,kNormal
dw mMOV
db argRegBP,argImm16,kNormal
dw mMOV
db argRegSI,argImm16,kNormal
dw mMOV
db argRegDI,argImm16,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw mRETN
db argImm16,argNone,kNormal
dw mRETN
db argNone,argNone,kNormal
dw mLES
db argReg16,argMem32,kNormal
dw mLDS
db argReg16,argMem32,kNormal
dw mMOV
db argMem8,argImm8,kNormal
dw mMOV
db argMem16,argImm16,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw mRETF
db argImm16,argNone,kNormal
dw mRETF
db argNone,argNone,kNormal
dw mINT
db argConst3,argNone,kNormal
dw mINT
db argImm8,argNone,kNormal
dw mINTO
db argNone,argNone,kNormal
dw mIRET
db argNone,argNone,kNormal
dw 0
db argRegMem8,argConst1,kShfOp
dw 0
db argRegMem16,argConst1,kShfOp
dw 0
db argRegMem8,argRegCL,kShfOp
dw 0
db argRegMem16,argRegCL,kShfOp
dw mAAM
db argImm8,argNone,kNormal
dw mAAD
db argImm8,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw mXLAT
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw 0
db argNone,argNone,kNormal
dw mLOOPNZ
db argShort,argNone,kNormal
dw mLOOPZ
db argShort,argNone,kNormal
dw mLOOP
db argShort,argNone,kNormal
dw mJCXZ
db argShort,argNone,kNormal
dw mIN
db argRegAL,argImm8,kNormal
dw mIN
db argRegAX,argImm8,kNormal
dw mOUT
db argImm8,argRegAL,kNormal
dw mOUT
db argImm8,argRegAX,kNormal
dw mCALL
db argNear,argNone,kNormal
dw mJMP
db argNear,argNone,kNormal
dw mJMP
db argFar,argNone,kNormal
dw mJMP
db argShort,argNone,kNormal
dw mIN
db argRegAL,argRegDX,kNormal
dw mIN
db argRegAX,argRegDX,kNormal
dw mOUT
db argRegDX,argRegAL,kNormal
dw mOUT
db argRegDX,argRegAX,kNormal
dw mLOCK
db argNone,argNone,kPrefix
dw 0
db argNone,argNone,kNormal
dw mREPNE
db argNone,argNone,kPrefix
dw mREP
db argNone,argNone,kPrefix
dw mHLT
db argNone,argNone,kNormal
dw mCMC
db argNone,argNone,kNormal
dw 0
db argRegMem8,argNone,kGrp1
dw 0
db argRegMem16,argNone,kGrp1
dw mCLC
db argNone,argNone,kNormal
dw mSTC
db argNone,argNone,kNormal
dw mCLI
db argNone,argNone,kNormal
dw mSTI
db argNone,argNone,kNormal
dw mCLD
db argNone,argNone,kNormal
dw mSTD
db argNone,argNone,kNormal
dw 0
db argRegMem8,argNone,kGrp2
dw 0
db argRegMem16,argNone,kGrp3
