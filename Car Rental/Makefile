PACKAGES = "rental_system" "system_statistics" "orders" "frames" "forms"
DIRS = "." $(PACKAGES)


all: Main.class

Main.class:
	javac -cp lib/*:. Main.java

run: Main.class
	java -cp lib/*:. Main

javadoc:
	javadoc -d ./docs/ $(PACKAGES)

clean:
	@for now in $(DIRS); do \
		rm -f $$now/*.class; \
	done
	@echo "*.class removed"
	rm -rf docs/*
