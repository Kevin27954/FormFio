package com.formkio.formfio.services;

import com.formkio.formfio.dto.FormsDTO;
import com.formkio.formfio.exceptions.FormError;
import com.formkio.formfio.exceptions.InternalError;
import com.formkio.formfio.exceptions.MissingValueError;
import com.formkio.formfio.exceptions.NotUniqueUUIDError;
import com.formkio.formfio.model.UsersModel;
import com.formkio.formfio.repository.EndpointsTable;
import com.formkio.formfio.repository.FormsTable;
import com.formkio.formfio.repository.drivers.DBDriver;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

@Component
public class FormService {

    DBDriver dbDriver;
    EndpointsTable endpointsTable;
    FormsTable formsTable;

    public FormService(DBDriver dbDriver, EndpointsTable endpointsTable, FormsTable formsTable) {
        this.dbDriver = dbDriver;
        this.endpointsTable = endpointsTable;
        this.formsTable = formsTable;
    }

    public void save(FormsDTO formsDTO) {
        try {
            dbDriver.beginTransaction();

            try {
                String endpoint = createEndpoint();
                formsDTO.setEndpoint(endpoint);
                this.formsTable.createNewForm(formsDTO);
            } catch (FormError e) {
                dbDriver.rollback();
            }

            dbDriver.commit();
        } catch (SQLException e) {
            System.out.println("void save(FormsDTO):" + e);
            throw new InternalError();
        }
    }

    public boolean getEndpoint(String endpoint) {
        return this.endpointsTable.getEndpoint(endpoint);
    }

    public String createEndpoint() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        try {
            while (endpointsTable.getEndpoint(uuidString)) {
                uuid = UUID.randomUUID();
                uuidString = uuid.toString();
            }
        } catch (InternalError e) {
            System.out.println("String createNewEndpoint() uuid: " + e);
            throw new NotUniqueUUIDError("uuid " + uuidString + " was not unique.");
        }

        endpointsTable.createNewEndpoint(uuidString);
        return uuidString;
    }

    public FormsDTO createFormsDTO(UsersModel usersModel, Map<String, String> submission) {
        FormsDTO formsDTO = new FormsDTO();

        formsDTO.setUsersModel(usersModel);
        if (!submission.containsKey("name")) {
            throw new MissingValueError("Form <name> field is required.");
        }
        formsDTO.setName(submission.get("name"));
        formsDTO.setDescription(submission.getOrDefault("description", ""));

        return formsDTO;
    }
}
