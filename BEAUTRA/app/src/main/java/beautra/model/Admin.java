package beautra.model;

import beautra.model.abstracts.User;

public class Admin extends User {
    @Override
    public String getRole() {
        return "admin";
    }
}
