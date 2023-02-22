package org.cst.TransactionManagment.controller;

import org.testcontainers.containers.MySQLContainer;

public class AbstractContainerBaseTest {
    static final MySQLContainer MY_SQL_CONTAINER;

    static {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");
        MY_SQL_CONTAINER.start();
    }
}
