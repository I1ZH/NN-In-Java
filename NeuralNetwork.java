import java.util.List;

//***Building The Neural Network***

/*
weights_ih - the weights matrix for the input and hidden layer;
weights_ho - the weights matrix for the hidden and output layer;
bias_h - the bias matrix for the hidden layer;
bias_o - the bias matrix for the output layer;
l_rate - the learning rate, a hyper-parameter used to control the learning steps during optimization of weights;
*/

public class NeuralNetwork {
    Matrix weights_ih, weights_ho, bias_h, bias_o;
    double l_rate=0.01;

//The constructor initializing all the variables

public NeuralNetwork(int i, int h, int o) {
    weights_ih = new Matrix(h, i);
    weights_ho = new Matrix(o, h);
    bias_h = new Matrix(h, 1);
    bias_o = new Matrix(o, 1);
}

/*
Forward Propagation is a computational step in Neural Networks where the input (Matrix) is multiplied with the weights (Matrix) of the hidden layer,
then bias (column Matrix) is added to the dot product of the previous step finally,
the result is then processed element-wise by the activation function.
This is preformed on each layer with the result of preceding layer as the input for the next layer.
This step is also called as Forward Pass and is used for generating prediction from the network.
*/

/*The predict function which does a forward pass or forward propagation on the neural network.
The function accepts a double array of inputs and then converts them to a column matrix
by our helper function the forward pass is then calculated on both the layers
and the output is then flattened into a list by another helper function.
*/

public List<Double> predict(double []X) {
    
    Matrix input = Matrix.fromArray(X);
    Matrix hidden = Matrix.multiply(weights_ih, input);
    hidden.add(bias_h);
    hidden.sigmoid();

    Matrix output = Matrix.multiply(weights_ho, hidden);
    output.add(bias_o);
    output.sigmoid();
    return output.toArray();
}

/*
Backpropagation is just the reverse of the forward pass in which we take the transpose of the weight matrices
and then multiply them with the gradient calculated from the errors,
which in turn return the deltas that are used to adjust the weights in the current layer.
The bias are updated using the gradients.
*/

/*
In train function, we get X and Y as double arrays, we convert them to matrices, the output from the forward pass is subtracted from the target that is Y in matrix form.
The result of the subtraction is the error of the current sample passed.
This error is used to calculate gradients for the backpropagation.
The derivative of the sigmoid function is applied element-wise on the output matrix,
which returns the gradient matrix then it is multiplied by the output errors and the learning rate which decides the learning step.
We repeat the same steps for the previous layers in the network as a back prop runs from the output layers to the input layers.
Finally, we have updated the weights of all the layers for the current sample which completes 1 step on the dataset.
*/

public void train(double []X, double []Y) {
    
    Matrix input = Matrix.fromArray(X);
    Matrix hidden = Matrix.multiply(weights_ih, input);
    hidden.add(bias_h);
    hidden.sigmoid();

    Matrix output = Matrix.multiply(weights_ho, hidden);
    output.add(bias_o);
    output.sigmoid();

    Matrix target = Matrix.fromArray(Y);

    Matrix error = Matrix.subtract(target, output);
    Matrix gradient = output.dsigmoid();
    gradient.multiply(error);
    gradient.multiply(l_rate);

    Matrix hidden_T = Matrix.transpose(hidden);
    Matrix who_delta = Matrix.multiply(gradient, hidden_T);

    weights_ho.add(who_delta);
    bias_o.add(gradient);

    Matrix who_T = Matrix.transpose(weights_ho);
    Matrix hidden_errors = Matrix.multiply(who_T, error);

    Matrix h_gradient = hidden.dsigmoid();
    h_gradient.multiply(hidden_errors);
    h_gradient.multiply(l_rate);

    Matrix i_T = Matrix.transpose(input);
    Matrix wih_delta = Matrix.multiply(h_gradient, i_T);

    weights_ih.add(wih_delta);
    bias_h.add(h_gradient);
}

/*
The fit function takes in 2 2d arrays namely X and Y, also the number of epochs i.e. how many times do we have to iterate over the dataset.
Here we repeatedly call the train function with random data points from the dataset so that we can generalize the network over the dataset.
*/

public void fit(double [][]X, double [][]Y, int epochs) {
    for(int i=0;i<epochs;i++) {
        int sampleN = (int)(Math.random()*X.length);
        this.train(X[sampleN], Y[sampleN]);
    }
}
}