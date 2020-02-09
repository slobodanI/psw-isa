package rs.ac.uns.ftn.informatika.jpa.service;

import java.io.IOException;
import java.io.InputStream;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;


@Service
public class JsonValidation 
{
	private final String SCHEMA_PATH_PREFIX = "/static/schema/";

    void validateJSON(String jsonString, String schema_file) throws IOException, ValidationException 
    {
        InputStream inputStream = new ClassPathResource(SCHEMA_PATH_PREFIX + schema_file).getInputStream();
        Schema schema = SchemaLoader.load(new JSONObject(new JSONTokener(inputStream)));
        schema.validate(new JSONObject(jsonString));
    }

    public String getSCHEMA_PATH_PREFIX() {
        return SCHEMA_PATH_PREFIX;
    }
}
