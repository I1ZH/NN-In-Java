import java.util.ArrayList;
import java.util.List;

//***Matrix Library***

//Create a Matrix class and add the necessary functionalities to it, such as Addition, Subtraction, Transpose, and Multiplication of Matrices

class Matrix {
    double [][]data;
    int rows, cols;


//Constructor for initializing our matrix object with random values between -1 and 1 by passing rows and cols as parameters

public Matrix(int rows, int cols) {
    data = new double[rows][cols];
    this.rows = rows;
    this.cols = cols;
    for(int  i=0;i<rows;i++) {
        for(int j=0;j<cols;j++) {
            data[i][j]=Math.random()*2-1;
        }
    }
}

//Function which is overloaded with 2 parameters, a double and a Matrix object which does element-wise addition

public void add(double scaler) {
    for(int  i=0;i<rows;i++) {
        for(int j=0;j<cols;j++) {
            this.data[i][j]+=scaler;
        }
    }
}

public void add(Matrix m) {
    if(cols!=m.cols || rows!=m.rows) {
        System.out.println("Share Mismatch");
        return;
    }
    for(int  i=0;i<rows;i++) {
        for(int j=0;j<cols;j++) {
            this.data[i][j]+=m.data[i][j];
        }
    }
}

//Two functions for calculating the subtraction and transpose of the matrices sent as parameters to the class static functions. These functions return the new Matrix object.

public static Matrix subtract(Matrix a, Matrix b) {
    Matrix temp = new Matrix(a.rows, a.cols);
    for(int i=0; i<a.rows;i++) {
        for(int j=0;j<a.cols;j++) {
            temp.data[i][j]=a.data[i][j]-b.data[i][j];            
        }
    }
    return temp;
}

public static Matrix transpose(Matrix a) {
    Matrix temp = new Matrix(a.cols, a.rows);
    for(int i=0; i<a.rows;i++) {
        for(int j=0;j<a.cols;j++) {
            temp.data[j][i]=a.data[i][j];            
        }
    }
    return temp;
}

/*
The first multiply function takes 2 Matrix objects and does the dot product operation on respective matrices and returns a new Matrix object/
The second function does the element-wise multiplication of the matrices.
The last one scales or multiplies the whole matrix by a scaler.
*/

public static Matrix multiply(Matrix a, Matrix b) {
    Matrix temp = new Matrix(a.rows, b.cols);
    for(int i=0; i<temp.rows;i++) {
        for(int j=0;j<temp.cols;j++) {
            double sum=0;
            for(int k=0;k<a.cols;k++) {
                sum+=a.data[i][k]*b.data[k][j];
            }
            temp.data[i][j]=sum;            
        }
    }
    return temp;
}

public void multiply(Matrix a) {
    for(int i=0; i<a.rows;i++) {
        for(int j=0;j<a.cols;j++) {
            this.data[i][j]*=a.data[i][j];            
        }
    }
}

public void multiply(double a) {
    for(int i=0; i<rows;i++) {
        for(int j=0;j<cols;j++) {
            this.data[i][j]*=a;            
        }
    }
}

//We are using the Sigmoid activation function for our AI

public void sigmoid() {
    for(int i=0; i<rows;i++) {
        for(int j=0;j<cols;j++) {
            this.data[i][j]=1/(1+Math.exp(-this.data[i][j]));            
        }
    }
}

//Functions apply sigmoid and derivative of sigmoid to the elements of the matrix. The derivative of Sigmoid is required while calculating gradients for backpropagation.

public Matrix dsigmoid() {
    Matrix temp = new Matrix(rows, cols);
    for(int i=0; i<rows;i++) {
        for(int j=0;j<cols;j++) {
            temp.data[i][j]=this.data[i][j]*(1-this.data[i][j]);            
        }
    }
    return temp;
}

//The helper functions for converting the matrix object to and from the array.

public static Matrix fromArray(double []x) {
    Matrix temp = new Matrix(x.length, 1);
    for(int i=0;i<x.length;i++) {
        temp.data[i][0]=x[i];
    }
    return temp;
}

public List<Double> toArray() {
    List<Double> temp = new ArrayList<Double>();
    for(int i=0;i<rows;i++) {
        for(int j=0;j<cols;j++) {
            temp.add(data[i][j]);
        }
    }
    return temp;
}
}