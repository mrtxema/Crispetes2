package cat.mrtxema.crispetes.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonParserFactory {

    public static Gson getJsonParser() {
        return new GsonBuilder().registerTypeAdapter(Date.class, new NullSafeDateAdapter()).create();
    }

    private static class NullSafeDateAdapter extends TypeAdapter<Date> {
        private static final DateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public void write(JsonWriter out, Date value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(FORMATTER.format(value));
            }
        }

        @Override
        public Date read(JsonReader in) throws IOException {
            Date result = null;
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
            } else {
                String dateStr = in.nextString();
                try {
                    result = FORMATTER.parse(dateStr);
                } catch (ParseException e) {
                    Log.e(getClass().getSimpleName(), "Invalid date: " + dateStr, e);
                }
            }
            return result;
        }
    }
}
