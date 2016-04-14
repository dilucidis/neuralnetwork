public class Perceptron {
  //if weighted inputs sum to this value, fire
  double threshold;
  private boolean fire;
  private final double defaultweight=0.34;
  private double learningRate=0.1;
  Hashmap<Perceptron, double> inputs_and_weights;
  
  public Perceptron(Perceptron[] inputs){
    threshold=1.0
    inputs_and_weights = new Hashmap<Perceptron, double>();
    for(Perceptron p  : inputs)
      addInput(p);
    fire = false;
  }
  public boolean activationFunction(){
    int sum;
    for(Map.Entry<Perceptron, double> entry : inputs_and_weights){
      if(entry.key().checkFire()){
        sum+=entry.value();
      }
    }
    if(sum>=threshold)
      fire=true;
  }
  public boolean checkFire(){
    return fire;
  }
  public boolean resetFire(){
    manualFire(false)
  }
  public boolean manualFire(boolean active){
    fire=active;
  }
  public void addInput(Perceptron I){
    inputs_and_weights.put(I, defaultweight);
  }
}
