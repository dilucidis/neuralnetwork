package perceptual_network;

public class Layer(){
  Perceptron[] bank;
  public Layer(int size){
    bank = new Perceptron[size];
  }
}
