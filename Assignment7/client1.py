import socket


sox = socket.socket()
port = 12321
sox.connect(("localhost", port))

coeff = [-945, 1689, -950, 230, -25, 1]
#coeff = [-945xx, 1689, -950, 230, -25, 1]
x = 1.0
a = 0
#a = "x"
b = 2
Tolerance = 1e-0

message = "S" + str(a) + " " + str(b)
for i in range(len(coeff)):
    message += " " + str(coeff[i])
message += " " + str(Tolerance)

for i in message:
    received = i.encode()
    sox.sendall(received)

sox.shutdown(1)
message = ""

received = sox.recv(2048)
while len(received) > 0:
    message += received.decode()
    received = sox.recv(2048)

print(message)
sox.close()

go = True

try:
    x = float(message[1:])
    message = "E" + str(x)
    for i in range(len(coeff)):
        message += " " + str(coeff[i])
except ValueError:
    print("Reply was not Valid")
    go = False

if go:
    sox = socket.socket()
    sox.connect(("localhost", port))
    for i in message:
        received = i.encode()
        sox.sendall(received)

    sox.shutdown(1)

    message = ""
    received = sox.recv(2048)
    while len(received) > 0:
        message += received.decode()
        received = sox.recv(2048)

    print(message)

sox.close()
