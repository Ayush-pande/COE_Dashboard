package com.amdocs.coe_dashboard.repositories;

import com.amdocs.coe_dashboard.models.Employee;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.client.java.query.QueryScanConsistency;

import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {

    @Override
    public boolean adminLogin(String email, String passwd) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`admin` WHERE email = $1 AND passwd = $2";
        QueryResult result =cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(email, passwd))
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS));

        boolean b = !result.rows().isEmpty();
        return b;

    }

    @Override
    public List<Employee> findByIdOrName(String input) {
        String statement = "SELECT * FROM `"
                + couchbaseConfig.getBucketName() + "`.`dashboard`.`employee` WHERE id = $1 OR name = $1";
        return cluster
                .query(statement,
                        QueryOptions.queryOptions().parameters(JsonArray.from(input))
                                .scanConsistency(QueryScanConsistency.REQUEST_PLUS))
                .rowsAs(Employee.class);
    }


    @Override
    public Employee addEmp(String id, Employee employee) {
        employeeCol.insert(id, employee);
        return employee;
    }

    @Override
    public Employee update(String id, Employee employee) {
        employeeCol.replace(id, employee);
        return employee;
    }

    @Override
    public Employee getEmpById(String id)
    {
        return employeeCol.get(id).contentAs(Employee.class);
    }
}
