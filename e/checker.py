
din = open('check').readlines()[0]
dok = open('correct').readlines()[0]

print len(din), len(dok)

for i in range(min(len(din), len(dok))):
	if din[i] != dok[i]:
		print 'Wrong digit at', i
		
