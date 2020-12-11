package be.sfpd.blog.service;

import be.sfpd.blog.repository.MockDatabase;

public class ManagementService {

    public void resetDB() {
        MockDatabase.resetDB();
    }
}
