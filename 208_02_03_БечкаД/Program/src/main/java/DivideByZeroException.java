public class DivideByZeroException extends Exception {
    public DivideByZeroException(){
        super("Divide by zero exception!");
    }

    public DivideByZeroException(String massage){
        super(massage);
    }
}
