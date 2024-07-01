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
neuron1 = Neuron(np.array([0, 1]), 4)
neuron2 = Neuron(np.array([0.3, 0.3, 0.4]), 4)
x1 = np.array([2, 3])
x2 = np.array([4, 2, 3])
print(neuron1.feedforward1(x1))
print(neuron2.feedforward2(x2))
