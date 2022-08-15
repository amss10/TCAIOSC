public class ColorsConfig {
    public static String RGBToHex(int R,int G, int B){
        String Hex = String.format("#%02X%02X%02X", R, G, B);
        return Hex;
    }
}
