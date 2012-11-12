def gcd(a, b):
	while b != 0:
		t = a
		a = b
		b = t % b
	return a

def fix(frac):
	d = gcd(frac[0], frac[1])
	return frac[0]/d, frac[1]/d

def join(a, b):
	return (a[0]*b[1] + b[0], a[1] * b[1])

def calcInterval(start, end):
	f = 1
	add = 0
	for i in xrange(start, end):
		f *= i
		add = i*add+1
	return (add, f)

from multiprocessing import Process, Value, Queue, Lock

results = Queue()

currentJob = Value('L', 0)
jobLock = Lock()

intervalSize = 10

def getJob():
	jobLock.acquire()
	nr = currentJob.value
	currentJob.value += 1
	jobLock.release()
	interval = nr * intervalSize + 1, (nr+1) * intervalSize + 1
	return (nr, interval)

def calculate():
	try:
		while True:
			job = getJob()
			result = calcInterval(*job[1])
			results.put([job[0], result])
	except KeyboardInterrupt:
		pass

def bitSize(size):
	size = float(size) / 8
	letter = ['', 'K', 'M', 'G']
	for l in letter:
		if size < 1024:
			return str(round(size, 2)) + l + 'B'
		size /= 1024
	return str(round(1024*size, 2)) + letter[len(letter)-1] + 'B'

from sys import stdout
def printFrac(frac, stream=stdout, length=8):
	print "GCD..."
	frac = fix(frac)
	print "Printing..."
	stream.write(str(frac[0] / frac[1]) + '.')
	bottom = frac[1]
	top = frac[0] % frac[1]
	count = 0
	for i in xrange(length):
		top *= 10
		stream.write(str(top / bottom))
		top %= bottom
		count += 1
		if count % 1000 == 0:
			print count
	stream.write('\n')


def calcE(threadsNumber, _intervalSize):
	global intervalSize
	intervalSize = _intervalSize
	threads = []
	for i in range(threadsNumber):
		Process(target=calculate).start()
	
	e = (1, 1)
	eDict = {}
	eNow = 0
	
	finished = False
	try:
		while True:
			result = results.get()
			eDict[result[0]] = result[1]
			while eNow in eDict:
				e = join(e, eDict[eNow])
				del eDict[eNow]
				eNow += 1
	except KeyboardInterrupt:
		print "Calculation closing."
	print "Iterations done:", eNow * intervalSize
	print bitSize(e[0].bit_length() + e[1].bit_length())
	
	from math import log10
	digits = int(log10(e[1]))
	print digits
	f = open('output', 'w')
	printFrac(e, f, digits)
	f.close()

	return e

calcE(8, 10000)
print "Done!"
