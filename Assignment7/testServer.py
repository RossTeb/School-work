import unittest
import socket

# I tried to write unit tests for testing the server, but for some reason, the tests won't instantiate and run.
# Researching this issue, it looks like unittest doesn't work well with servers and socket based communication.
# Some people have suggested instantiating a "fake" server IN the unittest class (here) and implementing threads.
# I wanted to try out writing unit tests because I really liked it in the
# REST server assignment (05), but it will have to wait
# because it sounds like a lot a hassle for something I believe works, anyways.

# Define port
port = 12321


class MyTestCase(unittest.TestCase):
    def test_error_empty_request(self):
        message = ''
        message = send_message(message)
        self.assertEqual('XInvalid request syntax: There are no arguments', message)

    def test_error_invalid_code(self):
        message = 'F'
        message = send_message(message)
        self.assertEqual('XInvalid operation code |' + message + '|', message)

    def test_evaluate(self):
        message = 'E1.0 -945 1689 -950 230 -25 1'
        message = send_message(message)
        self.assertEqual('E0.0', message)

    def test_not_enough_arguments_eval(self):
        message = 'E'
        message = send_message(message)
        self.assertEqual('XInvalid request syntax: wrong number of fields in request', message)

    def test_error_invalid_format_evaluate(self):
        message = 'E1.0 -945xx 1689 -950 230 -25 1'
        message = send_message(message)
        self.assertEqual('Xinvalid format numeric data', message)

    def test_bisection(self):
        message = 'S0 2 -945 1689 -950 230 -25 1 1e-15'
        message = send_message(message)
        self.assertEqual('S1.0000000000000004', message)

    def test_error_invalid_format_bisection(self):
        message = 'S0 2 -94x5 1689 -95x0 230 -25 1 1e-15'
        message = send_message(message)
        self.assertEqual('Xinvalid format numeric data', message)

    def test_not_enough_arguments_bis(self):
        message = 'S'
        message = send_message(message)
        self.assertEqual('XInvalid request syntax: wrong number of fields in request', message)


def send_message(message):
        # Define socket
        sck = socket.socket()
        # Connect to indicated address and port
        sck.connect(("localhost", port))

        # Send the encoded Bisection request string to server
        for c in message:
            encoded_message = c.encode()
            sck.sendall(encoded_message)

        # Initialize message to be received (Results of request)
        message = ''

        # Define buffer and decode message
        encoded_message = sck.recv(2048)
        while len(encoded_message) > 0:
            message += encoded_message.decode()
            # possibility that decode error from split encoding
            encoded_message = sck.recv(2048)

        # Close the socket
        sck.close()

        # return the received message (results of request)
        return message

if __name__ == '__main__':
    unittest.main()
