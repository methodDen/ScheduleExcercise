import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class StudentSerializer extends StdSerializer<Students> {

    public StudentSerializer(Class<Students> t) {
        super(t);
    }

    public StudentSerializer() {
        this(null);
    }

    @Override
    public void serialize(Students students, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("StudentId", students.getStudentId());
        jsonGenerator.writeStringField("FirstName", students.getFirstName());
        jsonGenerator.writeStringField("LastName", students.getLastName());
        jsonGenerator.writeStringField("MobilePhone", students.getMobilePhone());
        jsonGenerator.writeNumberField("GroupId", students.getGroupId());
        jsonGenerator.writeEndObject();
    }
}
