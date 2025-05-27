package beautra.model;

import beautra.model.abstracts.User;

public class Seller extends User {
    @Override
    public String getRole() {
        return "seller";
    }
}
