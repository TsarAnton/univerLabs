import numpy as np

def sigmoid(x):
    return 1 / (1+np.exp(-x))

def tanh(x):
    return 2 / (1 + np.exp(-2 * x)) - 1

class Neuron:
    def __init__(self, weights, bias):
        self.weights = weights
        self.bias = bias

    def feedforward1(self, inputs):
        total = np.dot(self.weights, inputs) + self.bias
        return sigmoid(total)
    
    def feedforward2(self, inputs):
        total = np.dot(self.weights, inputs) + self.bias
        return tanh(total)
    
class OurNeuralNetwork1:
    def __init__(self):
        weights = np.array([0,1])
        bias = 0
        self.h1 = Neuron(weights,bias)
        self.h2 = Neuron(weights,bias)
        self.o1 = Neuron(weights,bias)
    def feedforward(self,x):
        out_h1 = self.h1.feedforward1(x)
        out_h2 = self.h2.feedforward1(x)
        out_o1 = self.o1.feedforward1(np.array([out_h1,out_h2]))
        return out_o1
    
class OurNeuralNetwork2:
    def __init__(self):
        weights = np.array([0.4, 0.4, 0.2])
        bias = 0
        self.h1 = Neuron(weights,bias)
        self.h2 = Neuron(weights,bias)
        self.h3 = Neuron(weights, bias)
        self.o1 = Neuron(weights,bias)
    def feedforward(self,x):
        out_h1 = self.h1.feedforward2(x)
        out_h2 = self.h2.feedforward2(x)
        out_h3 = self.h3.feedforward2(x)
        out_o1 = self.o1.feedforward2(np.array([out_h1,out_h2, out_h3]))
        return out_o1
network1 = OurNeuralNetwork1()
x1 = np.array([2,3])
print(network1.feedforward(x1))

network2 = OurNeuralNetwork2()
x2 = np.array([2,3,3])
print(network2.feedforward(x2))
