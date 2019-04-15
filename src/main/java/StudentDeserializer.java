import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class StudentDeserializer extends StdDeserializer<Students> {
    public StudentDeserializer(Class<?> vc) {
        super(vc);
    }

    public StudentDeserializer()
    {
        this(null);
    }

    @Override
    public Students deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        long studentId = Long.valueOf(jsonNode.get("StudentId").asText());
        String firstName = jsonNode.get("FirstName").asText();
        String lastName = jsonNode.get("LastName").asText();
        String mobilePhone = jsonNode.get("MobilePhone").asText();
        long groupId = Long.valueOf(jsonNode.get("GroupId").asText());
        return new Students(studentId, firstName, lastName, mobilePhone, groupId);
    }
}
