DIRS = "." "rental_system" "system_statistics"


Main.class: Main.java
	javac Main.java

run: Main.class
	java Main


clean:
	@for now in $(DIRS); do \
		rm -f $$now/*.class; \
	done
