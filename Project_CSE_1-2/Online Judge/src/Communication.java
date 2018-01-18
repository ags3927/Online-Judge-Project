
public class Communication {

    public static NetworkUtil nc;

    public Communication(NetworkUtil nc) {
        this.nc = nc;
    }

    public static NetworkUtil get(){
        return nc;
    }
}
