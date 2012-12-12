DIRS = "." "rental_system" "system_statistics" "orders" "frames" "forms"


all: Main.class

Main.class:
	javac -cp lib/*:. Main.java

run: Main.class
	java -cp lib/*:. Main


clean:
	@for now in $(DIRS); do \
		rm -f $$now/*.class; \
	done
