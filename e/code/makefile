#small makefile script for dinamicEQ project
#
#
#	TODO:
#	NEED TO USE CONFIGURE SCRIPTS and AUTOCONF
#
#
#	written by: Rytis Karpuška
#

CXXC=g++
CC=gcc

CXXFLAGS= -Wall -O3

CXXLIBS=

INCDIRS=-I./ 

OBJFILES=main.o suffixTree.o suffixTreeTest.o

EXECUTABLE=main


CXXFLAGS+=$(CXXLIBS)
CXXFLAGS+=$(INCDIRS)

CFLAGS=$(CXXFLAGS)

all:main


.SUFFIXES: .cpp .c

.cpp.o:
	$(CXXC) $(CXXFLAGS) -c $<

.c.o:
	$(CC) $(CFLAGS) -c $<




main: $(OBJFILES)
	$(CXXC) $(CFLAGS) $(OBJFILES) -o $(EXECUTABLE)

clean:
	rm $(EXECUTABLE)	
	rm $(OBJFILES)
