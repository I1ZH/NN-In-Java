import java.util.List;

//***Testing our Program***

//We will make a simple 2d double array from the data of XOR Logic Gate

public class Driver {
   
    static double [][]X = {{0,0}, {1,0}, {0,1}, {1,1}};
    static double [][]Y = {{0}, {1}, {1}, {0}};
    
    public static void main(String[] args) {
        
       /*We will create a Neural Network object that we will train on the dataset.
       We used 50000 epochs as the dataset is very small and contains only 4 samples.
       */

       NeuralNetwork nn = new NeuralNetwork(2, 10, 1);
       List<Double> output;
       nn.fit(X, Y, 50000);

       //Now we test the Neural Network with our input in the form of a double array.

       double [][]input = {{0,0}, {1,0}, {0,1}, {1,1}};
       for(double d[]: input) {
           output = nn.predict(d);
           System.out.println(output.toString());
        }
    }
}
