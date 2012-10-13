

f = file("opcodes.asm", "w")


def DecToStr(nr):
    chars = "0123456789ABCDEF"
    s = ""
    s += chars[nr / 16]
    s += chars[nr % 16]
    return s

f.write("opcodes ")
for i in range(256):
    #f.write(";" + DecToStr(i) + '\n')
    f.write("db mUNKNOWN, argNone, argNone, tNorm ;" + DecToStr(i) + "\n")

f.close()
