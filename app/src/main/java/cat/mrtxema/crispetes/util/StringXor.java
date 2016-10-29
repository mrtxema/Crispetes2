package cat.mrtxema.crispetes.util;

import android.util.Base64;
import java.nio.charset.Charset;

public class StringXor {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public String encode(String s) {
        return Base64.encodeToString(xorWithKey(s.getBytes(CHARSET), getClass().getSimpleName().getBytes(CHARSET)), Base64.NO_WRAP);
    }

    public String decode(String s) {
        return new String(xorWithKey(Base64.decode(s, Base64.NO_WRAP), getClass().getSimpleName().getBytes(CHARSET)), CHARSET);
    }

    private byte[] xorWithKey(byte[] a, byte[] key) {
        byte[] out = a;
        for (int i = 0; i < a.length; i++) {
            out[i] = (byte) (a[i] ^ key[i%key.length]);
        }
        return out;
    }
}
