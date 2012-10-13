#-------------------------------------------------------------------------------
# Name:        module1
# Purpose:
#
# Author:      Donatas
#
# Created:     03.12.2011
# Copyright:   (c) Donatas 2011
# Licence:     <your licence>
#-------------------------------------------------------------------------------
#!/usr/bin/env python

def main():
    pass

if __name__ == '__main__':
    main()

f = open('command_list.txt')
c = f.readlines()
f.close()

for i in range(len(c)):
    c[i] = c[i][:len(c[i])-1]

f = open('op_names.asm', 'w')
for i in c:
    f.write('mt' + i + ' db \'' + i + '$\'\n')
f.write('\n')


f.write('mnemonics dw ')
f.write('mt' + c[0])
for i in c[1:]:
    f.write(', mt' + i)

f.write('\n')
f.write('\n')


f.write('mUNKNOWN equ 0\n')

number = 0
for i in c:
    number += 1
    f.write('m' + i + ' equ ' + str(number) + '\n')

f.close()
