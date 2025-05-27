package beautra.model;

import beautra.model.abstracts.User;

public class Buyer extends User {
    @Override
    public String getRole() {
        return "buyer";
    }
}
