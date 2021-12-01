package fraction;

import java.util.Enumeration;

import static org.junit.Assert.assertEquals;

public class FractionImpl implements Fraction {

    private int numerator;
    private int denominator;

    //gcd helper to calculate greatest common divisor based on Euclidean algorithm

    private int gcd(int a, int b){
        a = Math.abs(a);
        b = Math.abs(b);

        if (b == 0) {
            return a;
        }
        return gcd(b,a % b);
    }

    //normalise method which uses gcd to create normalised version of fraction

    private void normalise(int a, int b){
        int gcd = gcd(a,b);

        if((b < 0 && a > 0) || (b < 0 && a < 0)){
            a *= -1;
            b *= -1;
        }

        this.numerator = a / gcd;
        this.denominator = b / gcd;
    }
    /**
     * Parameters are the <em>numerator</em> and the <em>denominator</em>.
     * Normalize the fraction as you create it.
     * For instance, if the parameters are <pre>(8, -12)</pre>, create a <pre>Fraction</pre> with numerator
     * <pre>-2</pre> and denominator <pre>3</pre>.
     *
     * The constructor should throw an <pre>ArithmeticException</pre> if the denominator is zero.
     *
     * @param numerator
     * @param denominator
     */
    public FractionImpl(int numerator, int denominator){
        if(denominator == 0){
            throw new ArithmeticException("Denominator cannot be zero");
        }
        else{
            this.numerator = numerator;
            this.denominator = denominator;
            normalise(this.numerator,this.denominator);
        }
     }

    /**
     * The parameter is the numerator and <pre>1</pre> is the implicit denominator.
     *
     * @param wholeNumber representing the numerator
     */
    public FractionImpl(int wholeNumber) {
        this.numerator = wholeNumber;
        this.denominator = 1;
    }

    /**
     * The parameter is a <pre>String</pre> containing either a whole number, such as `5` or `-3`, or a fraction,
     * such as "8/-12".
     * Allow blanks around (but not within) integers.
     * The constructor should throw an <pre>ArithmeticException</pre>
     * if given a string representing a fraction whose denominator is zero.
     * <p>
     * You may find it helpful to look at the available String API methods in the Java API.
     *
     * @param fraction the string representation of the fraction
     */
    public FractionImpl(String fraction) {
        //create string array list from string entered removing any leading or trailing zeroes with trim() and splitting with /
        fraction = fraction.trim();
        String[] s = fraction.split("/");

        try {
            //if length of array is 1, this means the fraction is a whole number so denominator can be 1
            if (s.length == 1) {
                this.numerator = Integer.parseInt(fraction);
                this.denominator = 1;
            }
            //if length of array is 2, the fraction was entered correctly
            else if (s.length == 2) {
                this.numerator = Integer.parseInt(s[0].trim());
                this.denominator = Integer.parseInt(s[1].trim());
            }

            //denominator in a fraction cannot be zero
            if (this.denominator == 0) {
                throw new ArithmeticException("Denominator cannot be zero");
            }
        }
        //any arrays that are not of size 1 or 2 generates an exception
        catch (NumberFormatException e){
            throw new NumberFormatException("Please enter a fraction or whole number in the correct format");
        }
        normalise(this.numerator, this.denominator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction add(Fraction f) {
        FractionImpl fraction = new FractionImpl(f.toString());
        int numerator = (this.numerator * fraction.denominator) + (fraction.numerator * this.denominator);
        int denominator = this.denominator * fraction.denominator;

        Fraction result = new FractionImpl(numerator, denominator);
        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction subtract(Fraction f) {
        FractionImpl fraction = new FractionImpl(f.toString());
        int numerator = (this.numerator * fraction.denominator) - (fraction.numerator * this.denominator);
        int denominator = this.denominator * fraction.denominator;

        Fraction result = new FractionImpl(numerator, denominator);
        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction multiply(Fraction f) {
        FractionImpl fraction = new FractionImpl(f.toString());
        int numerator = this.numerator * fraction.numerator;
        int denominator = this.denominator * fraction.denominator;

        Fraction result = new FractionImpl(numerator, denominator);
        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction divide(Fraction f) {
        FractionImpl fraction = new FractionImpl(f.toString());
        int numerator = this.numerator * fraction.denominator;
        int denominator = this.denominator * fraction.numerator;

        Fraction result = new FractionImpl(numerator, denominator);
        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction abs() {
        Fraction result;

        if(numerator < 0) {
            result = new FractionImpl(numerator * -1, denominator);
        }
        else{
            result = new FractionImpl(numerator, denominator);
        }
        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction negate() {
        Fraction result = new FractionImpl(numerator * -1, denominator);
        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Fraction){
            Fraction f = (Fraction) obj;
            FractionImpl fraction = new FractionImpl(f.toString());
            return (this.numerator == fraction.numerator) && (this.denominator == fraction.denominator);
        }
        else{
            return false;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction inverse() {
        Fraction result = new FractionImpl(denominator,numerator);
        return  result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(Fraction o) {
        FractionImpl o1 = new FractionImpl(o.toString());

        if(this.equals(o1)){
            return 0;
        }

        if ((this.numerator / this.denominator) - (o1.numerator / o1.denominator) > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        if(denominator == 1){
                return Integer.toString(numerator);
        }
        else {
            return numerator + "/" + denominator;
        }
    }
}