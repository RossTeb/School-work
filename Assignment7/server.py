from polynomials import evaluate, bisection
import socket


port = 12321
check = socket.socket()
check.bind(("localhost", port))
check.listen(0)

while True:
    message = ""
    reply = "Something Is Wrong"
    connection = check.accept()
    socket = connection[0]

    received = socket.recv(2048)
    while len(received) > 0:
        message += received.decode()
        received = socket.recv(2048)
    print("Message received: " + message)

    if len(message) == 0:
        reply = "XInvalid request syntax: There are no arguments"
    elif message[0] == 'S':
        tokens = message[1:].split()
        if len(tokens) < 3:
            reply = "XInvalid request syntax: wrong number of fields in request: " + str(len(tokens))
        else:
            try:
                a = float(tokens[0])
                b = float(tokens[1])
                tol = float(tokens[len(tokens) - 1])
                poly = []
                for i in range(2, len(tokens) - 1):
                    poly.append(float(tokens[i]))
                reply = "S" + str(bisection(a, b, poly, tol))
            except ValueError:
                reply = "Xinvalid format numeric data"

    elif message[0] == "E":
        tokens = message[1:].split()
        if len(tokens) < 1:
            reply = "Wrong number of fields in request"
        else:
            try:
                x = float(tokens[0])
                poly = []
                for i in range(1, len(tokens)):
                    poly.append(float(tokens[i]))
                reply = "E" + str(evaluate(x, poly))
            except ValueError:
                reply = "Xinvalid format numeric data"

    else:
        reply = "Invalid Operator:" + str(message[0])

    print("Sending result: " + reply)
    received = reply.encode()
    socket.sendall(received)
    socket.shutdown(1)
    socket.close()
check.close()