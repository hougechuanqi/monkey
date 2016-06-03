package pub.vrtech.common.serialize.support.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import pub.vrtech.common.URL;
import pub.vrtech.common.serialize.ObjectInput;
import pub.vrtech.common.serialize.ObjectOutput;
import pub.vrtech.common.serialize.Serialization;


/**
 * FastJsonSerialization
 * 
 * @author william.liangf
 */
public class FastJsonSerialization implements Serialization {

    public byte getContentTypeId() {
        return 6;
    }

    public String getContentType() {
        return "text/json";
    }
    
    public ObjectOutput serialize(URL url, OutputStream output) throws IOException {
        return new FastJsonObjectOutput(output);
    }

    public ObjectInput deserialize(URL url, InputStream input) throws IOException {
        return new FastJsonObjectInput(input);
    }

}